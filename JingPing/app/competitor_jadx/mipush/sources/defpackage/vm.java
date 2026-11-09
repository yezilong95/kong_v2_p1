package defpackage;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
public final class vm extends mu implements Runnable {
    public static final vm M;
    public static final long N;
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    static {
        Long l;
        vm vmVar = new vm();
        M = vmVar;
        vmVar.A(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l = 1000L;
        }
        N = timeUnit.toNanos(l.longValue());
    }

    @Override // defpackage.mu, defpackage.nu
    public final void C() {
        debugStatus = 4;
        super.C();
    }

    @Override // defpackage.mu
    public final void D(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.D(runnable);
    }

    public final synchronized void G() {
        int i = debugStatus;
        if (i == 2 || i == 3) {
            debugStatus = 3;
            mu.J.set(this, null);
            mu.K.set(this, null);
            notifyAll();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zE;
        l01.a.set(this);
        try {
            synchronized (this) {
                int i = debugStatus;
                if (i == 2 || i == 3) {
                    if (zE) {
                        return;
                    } else {
                        return;
                    }
                }
                debugStatus = 1;
                notifyAll();
                long j = Long.MAX_VALUE;
                while (true) {
                    Thread.interrupted();
                    long jF = F();
                    if (jF == Long.MAX_VALUE) {
                        long jNanoTime = System.nanoTime();
                        if (j == Long.MAX_VALUE) {
                            j = N + jNanoTime;
                        }
                        long j2 = j - jNanoTime;
                        if (j2 <= 0) {
                            _thread = null;
                            G();
                            if (E()) {
                                return;
                            }
                            z();
                            return;
                        }
                        if (jF > j2) {
                            jF = j2;
                        }
                    } else {
                        j = Long.MAX_VALUE;
                    }
                    if (jF > 0) {
                        int i2 = debugStatus;
                        if (i2 == 2 || i2 == 3) {
                            _thread = null;
                            G();
                            if (E()) {
                                return;
                            }
                            z();
                            return;
                        }
                        LockSupport.parkNanos(this, jF);
                    }
                }
            }
        } finally {
            _thread = null;
            G();
            if (!E()) {
                z();
            }
        }
    }

    @Override // defpackage.nu
    public final Thread z() {
        Thread thread;
        Thread thread2 = _thread;
        if (thread2 != null) {
            return thread2;
        }
        synchronized (this) {
            thread = _thread;
            if (thread == null) {
                thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                _thread = thread;
                thread.setContextClassLoader(vm.class.getClassLoader());
                thread.setDaemon(true);
                thread.start();
            }
        }
        return thread;
    }
}
