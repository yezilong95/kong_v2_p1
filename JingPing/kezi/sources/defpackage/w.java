package defpackage;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class w implements x20 {
    public static final boolean d = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Logger e = Logger.getLogger(w.class.getName());
    public static final rp0 f;
    public static final Object g;
    public volatile Object a;
    public volatile s b;
    public volatile v c;

    static {
        rp0 uVar;
        try {
            uVar = new t(AtomicReferenceFieldUpdater.newUpdater(v.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(v.class, v.class, "b"), AtomicReferenceFieldUpdater.newUpdater(w.class, v.class, "c"), AtomicReferenceFieldUpdater.newUpdater(w.class, s.class, "b"), AtomicReferenceFieldUpdater.newUpdater(w.class, Object.class, "a"));
            th = null;
        } catch (Throwable th) {
            th = th;
            uVar = new u();
        }
        f = uVar;
        if (th != null) {
            e.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        g = new Object();
    }

    public static void b(w wVar) {
        v vVar;
        s sVar;
        s sVar2;
        s sVar3;
        do {
            vVar = wVar.c;
        } while (!f.l(wVar, vVar, v.c));
        while (true) {
            sVar = null;
            if (vVar == null) {
                break;
            }
            Thread thread = vVar.a;
            if (thread != null) {
                vVar.a = null;
                LockSupport.unpark(thread);
            }
            vVar = vVar.b;
        }
        do {
            sVar2 = wVar.b;
        } while (!f.j(wVar, sVar2, s.d));
        while (true) {
            sVar3 = sVar;
            sVar = sVar2;
            if (sVar == null) {
                break;
            }
            sVar2 = sVar.c;
            sVar.c = sVar3;
        }
        while (sVar3 != null) {
            s sVar4 = sVar3.c;
            c(sVar3.a, sVar3.b);
            sVar3 = sVar4;
        }
    }

    public static void c(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            e.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e2);
        }
    }

    public static Object d(Object obj) throws ExecutionException {
        if (obj instanceof q) {
            CancellationException cancellationException = ((q) obj).a;
            CancellationException cancellationException2 = new CancellationException("Task was cancelled.");
            cancellationException2.initCause(cancellationException);
            throw cancellationException2;
        }
        if (obj instanceof r) {
            ((r) obj).getClass();
            throw new ExecutionException((Throwable) null);
        }
        if (obj == g) {
            return null;
        }
        return obj;
    }

    public static Object e(w wVar) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = wVar.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public final void a(StringBuilder sb) {
        try {
            Object objE = e(this);
            sb.append("SUCCESS, result=[");
            sb.append(objE == this ? "this future" : String.valueOf(objE));
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e3) {
            sb.append("FAILURE, cause=[");
            sb.append(e3.getCause());
            sb.append("]");
        }
    }

    @Override // defpackage.x20
    public final void addListener(Runnable runnable, Executor executor) {
        executor.getClass();
        s sVar = this.b;
        s sVar2 = s.d;
        if (sVar != sVar2) {
            s sVar3 = new s(runnable, executor);
            do {
                sVar3.c = sVar;
                if (f.j(this, sVar, sVar3)) {
                    return;
                } else {
                    sVar = this.b;
                }
            } while (sVar != sVar2);
        }
        c(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object obj = this.a;
        if (obj != null) {
            return false;
        }
        if (!f.k(this, obj, d ? new q(z, new CancellationException("Future.cancel() was called.")) : z ? q.b : q.c)) {
            return false;
        }
        b(this);
        return true;
    }

    public final void f(v vVar) {
        vVar.a = null;
        while (true) {
            v vVar2 = this.c;
            if (vVar2 == v.c) {
                return;
            }
            v vVar3 = null;
            while (vVar2 != null) {
                v vVar4 = vVar2.b;
                if (vVar2.a != null) {
                    vVar3 = vVar2;
                } else if (vVar3 != null) {
                    vVar3.b = vVar4;
                    if (vVar3.a == null) {
                        break;
                    }
                } else if (!f.l(this, vVar2, vVar4)) {
                    break;
                }
                vVar2 = vVar4;
            }
            return;
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.a;
        if (obj != null) {
            return d(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            v vVar = this.c;
            v vVar2 = v.c;
            if (vVar != vVar2) {
                v vVar3 = new v();
                do {
                    rp0 rp0Var = f;
                    rp0Var.P(vVar3, vVar);
                    if (rp0Var.l(this, vVar, vVar3)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                f(vVar3);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.a;
                            if (obj2 != null) {
                                return d(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        f(vVar3);
                    } else {
                        vVar = this.c;
                    }
                } while (vVar != vVar2);
            }
            return d(this.a);
        }
        while (nanos > 0) {
            Object obj3 = this.a;
            if (obj3 != null) {
                return d(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        String strI = "Waited " + j + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String strI2 = hk.i(strI, " (plus ");
            long j2 = -nanos;
            long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
            long nanos2 = j2 - timeUnit.toNanos(jConvert);
            boolean z = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String strI3 = strI2 + jConvert + " " + lowerCase;
                if (z) {
                    strI3 = hk.i(strI3, ",");
                }
                strI2 = hk.i(strI3, " ");
            }
            if (z) {
                strI2 = strI2 + nanos2 + " nanoseconds ";
            }
            strI = hk.i(strI2, "delay)");
        }
        if (isDone()) {
            throw new TimeoutException(hk.i(strI, " but future completed as timeout expired"));
        }
        throw new TimeoutException(strI + " for " + string);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.a instanceof q;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.a != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.a instanceof q) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                if (this instanceof ScheduledFuture) {
                    str = "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
                } else {
                    str = null;
                }
            } catch (RuntimeException e2) {
                str = "Exception thrown from implementation: " + e2.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.a;
            if (obj2 != null) {
                return d(obj2);
            }
            v vVar = this.c;
            v vVar2 = v.c;
            if (vVar != vVar2) {
                v vVar3 = new v();
                do {
                    rp0 rp0Var = f;
                    rp0Var.P(vVar3, vVar);
                    if (rp0Var.l(this, vVar, vVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.a;
                            } else {
                                f(vVar3);
                                throw new InterruptedException();
                            }
                        } while (obj == null);
                        return d(obj);
                    }
                    vVar = this.c;
                } while (vVar != vVar2);
            }
            return d(this.a);
        }
        throw new InterruptedException();
    }
}
