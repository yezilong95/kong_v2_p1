package defpackage;

import io.nats.client.support.NatsConstants;
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
public abstract class g1 implements ca0 {
    public static final boolean d = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Logger e = Logger.getLogger(g1.class.getName());
    public static final z91 f;
    public static final Object g;
    public volatile Object a;
    public volatile c1 b;
    public volatile f1 c;

    static {
        z91 e1Var;
        try {
            e1Var = new d1(AtomicReferenceFieldUpdater.newUpdater(f1.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(f1.class, f1.class, "b"), AtomicReferenceFieldUpdater.newUpdater(g1.class, f1.class, "c"), AtomicReferenceFieldUpdater.newUpdater(g1.class, c1.class, "b"), AtomicReferenceFieldUpdater.newUpdater(g1.class, Object.class, "a"));
            th = null;
        } catch (Throwable th) {
            th = th;
            e1Var = new e1();
        }
        f = e1Var;
        if (th != null) {
            e.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        g = new Object();
    }

    public static void b(g1 g1Var) {
        f1 f1Var;
        c1 c1Var;
        c1 c1Var2;
        c1 c1Var3;
        do {
            f1Var = g1Var.c;
        } while (!f.k(g1Var, f1Var, f1.c));
        while (true) {
            c1Var = null;
            if (f1Var == null) {
                break;
            }
            Thread thread = f1Var.a;
            if (thread != null) {
                f1Var.a = null;
                LockSupport.unpark(thread);
            }
            f1Var = f1Var.b;
        }
        do {
            c1Var2 = g1Var.b;
        } while (!f.i(g1Var, c1Var2, c1.d));
        while (true) {
            c1Var3 = c1Var;
            c1Var = c1Var2;
            if (c1Var == null) {
                break;
            }
            c1Var2 = c1Var.c;
            c1Var.c = c1Var3;
        }
        while (c1Var3 != null) {
            c1 c1Var4 = c1Var3.c;
            c(c1Var3.a, c1Var3.b);
            c1Var3 = c1Var4;
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
        if (obj instanceof a1) {
            CancellationException cancellationException = ((a1) obj).a;
            CancellationException cancellationException2 = new CancellationException("Task was cancelled.");
            cancellationException2.initCause(cancellationException);
            throw cancellationException2;
        }
        if (obj instanceof b1) {
            ((b1) obj).getClass();
            throw new ExecutionException((Throwable) null);
        }
        if (obj == g) {
            return null;
        }
        return obj;
    }

    public static Object e(g1 g1Var) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = g1Var.get();
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

    @Override // defpackage.ca0
    public final void addListener(Runnable runnable, Executor executor) {
        executor.getClass();
        c1 c1Var = this.b;
        c1 c1Var2 = c1.d;
        if (c1Var != c1Var2) {
            c1 c1Var3 = new c1(runnable, executor);
            do {
                c1Var3.c = c1Var;
                if (f.i(this, c1Var, c1Var3)) {
                    return;
                } else {
                    c1Var = this.b;
                }
            } while (c1Var != c1Var2);
        }
        c(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object obj = this.a;
        if (obj != null) {
            return false;
        }
        if (!f.j(this, obj, d ? new a1(z, new CancellationException("Future.cancel() was called.")) : z ? a1.b : a1.c)) {
            return false;
        }
        b(this);
        return true;
    }

    public final void f(f1 f1Var) {
        f1Var.a = null;
        while (true) {
            f1 f1Var2 = this.c;
            if (f1Var2 == f1.c) {
                return;
            }
            f1 f1Var3 = null;
            while (f1Var2 != null) {
                f1 f1Var4 = f1Var2.b;
                if (f1Var2.a != null) {
                    f1Var3 = f1Var2;
                } else if (f1Var3 != null) {
                    f1Var3.b = f1Var4;
                    if (f1Var3.a == null) {
                        break;
                    }
                } else if (!f.k(this, f1Var2, f1Var4)) {
                    break;
                }
                f1Var2 = f1Var4;
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
            f1 f1Var = this.c;
            f1 f1Var2 = f1.c;
            if (f1Var != f1Var2) {
                f1 f1Var3 = new f1();
                do {
                    z91 z91Var = f;
                    z91Var.b0(f1Var3, f1Var);
                    if (z91Var.k(this, f1Var, f1Var3)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                f(f1Var3);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.a;
                            if (obj2 != null) {
                                return d(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        f(f1Var3);
                    } else {
                        f1Var = this.c;
                    }
                } while (f1Var != f1Var2);
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
        String strM = "Waited " + j + NatsConstants.SPACE + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String strM2 = zn.m(strM, " (plus ");
            long j2 = -nanos;
            long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
            long nanos2 = j2 - timeUnit.toNanos(jConvert);
            boolean z = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String strM3 = strM2 + jConvert + NatsConstants.SPACE + lowerCase;
                if (z) {
                    strM3 = zn.m(strM3, ",");
                }
                strM2 = zn.m(strM3, NatsConstants.SPACE);
            }
            if (z) {
                strM2 = strM2 + nanos2 + " nanoseconds ";
            }
            strM = zn.m(strM2, "delay)");
        }
        if (isDone()) {
            throw new TimeoutException(zn.m(strM, " but future completed as timeout expired"));
        }
        throw new TimeoutException(strM + " for " + string);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.a instanceof a1;
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
        if (this.a instanceof a1) {
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
            f1 f1Var = this.c;
            f1 f1Var2 = f1.c;
            if (f1Var != f1Var2) {
                f1 f1Var3 = new f1();
                do {
                    z91 z91Var = f;
                    z91Var.b0(f1Var3, f1Var);
                    if (z91Var.k(this, f1Var, f1Var3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.a;
                            } else {
                                f(f1Var3);
                                throw new InterruptedException();
                            }
                        } while (obj == null);
                        return d(obj);
                    }
                    f1Var = this.c;
                } while (f1Var != f1Var2);
            }
            return d(this.a);
        }
        throw new InterruptedException();
    }
}
