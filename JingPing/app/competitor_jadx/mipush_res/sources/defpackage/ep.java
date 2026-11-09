package defpackage;

import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class ep implements Runnable {
    public static final Logger E = Logger.getLogger(ep.class.getName());
    public static final ReferenceQueue F = new ReferenceQueue();
    public static final Set G = Collections.synchronizedSet(new HashSet());
    public static final ScheduledExecutorService H;
    public static final long I;

    static {
        new AtomicLong(Long.MIN_VALUE);
        ep epVar = new ep();
        I = cm0.a(5, "org.bouncycastle.native.cleanup_delay");
        H = Executors.newSingleThreadScheduledExecutor(new bp(0));
        Thread thread = new Thread(epVar, "BC Disposal Daemon");
        thread.setDaemon(true);
        thread.start();
        Runtime.getRuntime().addShutdownHook(new cp());
    }

    @Override // java.lang.Runnable
    public final void run() {
        while (true) {
            try {
                dp dpVar = (dp) F.remove();
                G.remove(dpVar);
                H.schedule(new f(9, dpVar), I, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (Throwable th) {
                E.warning("exception in disposal thread: " + th.getMessage());
            }
        }
    }
}
