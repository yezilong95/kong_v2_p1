package defpackage;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
public final class cj extends no implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;
    public static final cj j;
    public static final long k;

    static {
        Long l;
        cj cjVar = new cj();
        j = cjVar;
        cjVar.H(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l = 1000L;
        }
        k = timeUnit.toNanos(l.longValue());
    }

    @Override // defpackage.oo
    public final Thread G() {
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
                thread.setDaemon(true);
                thread.start();
            }
        }
        return thread;
    }

    @Override // defpackage.no, defpackage.oo
    public final void J() {
        debugStatus = 4;
        super.J();
    }

    @Override // defpackage.no
    public final void K(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.K(runnable);
    }

    public final synchronized void N() {
        int i = debugStatus;
        if (i == 2 || i == 3) {
            debugStatus = 3;
            no.g.set(this, null);
            no.h.set(this, null);
            notifyAll();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zL;
        xn0.a.set(this);
        try {
            synchronized (this) {
                int i = debugStatus;
                if (i == 2 || i == 3) {
                    if (zL) {
                        return;
                    } else {
                        return;
                    }
                }
                debugStatus = 1;
                notifyAll();
                long j2 = Long.MAX_VALUE;
                while (true) {
                    Thread.interrupted();
                    long jM = M();
                    if (jM == Long.MAX_VALUE) {
                        long jNanoTime = System.nanoTime();
                        if (j2 == Long.MAX_VALUE) {
                            j2 = k + jNanoTime;
                        }
                        long j3 = j2 - jNanoTime;
                        if (j3 <= 0) {
                            _thread = null;
                            N();
                            if (L()) {
                                return;
                            }
                            G();
                            return;
                        }
                        if (jM > j3) {
                            jM = j3;
                        }
                    } else {
                        j2 = Long.MAX_VALUE;
                    }
                    if (jM > 0) {
                        int i2 = debugStatus;
                        if (i2 == 2 || i2 == 3) {
                            _thread = null;
                            N();
                            if (L()) {
                                return;
                            }
                            G();
                            return;
                        }
                        LockSupport.parkNanos(this, jM);
                    }
                }
            }
        } finally {
            _thread = null;
            N();
            if (!L()) {
                G();
            }
        }
    }
}
