package defpackage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class eu implements Runnable {
    public static final Logger I = Logger.getLogger(eu.class.getName());
    public final long E;
    public final AtomicBoolean F;
    public final AtomicReference G;
    public final y30 H;

    public eu(y30 y30Var, AtomicBoolean atomicBoolean, AtomicReference atomicReference) {
        this.H = y30Var;
        this.F = atomicBoolean;
        this.G = atomicReference;
        String strB = cm0.b("org.bouncycastle.drbg.gather_pause_secs");
        long j = 5000;
        if (strB != null) {
            try {
                j = Long.parseLong(strB) * 1000;
            } catch (Exception unused) {
            }
        }
        this.E = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.G.set(this.H.a(this.E));
            this.F.set(true);
        } catch (InterruptedException unused) {
            Level level = Level.FINE;
            Logger logger = I;
            if (logger.isLoggable(level)) {
                logger.fine("entropy request interrupted - exiting");
            }
            Thread.currentThread().interrupt();
        }
    }
}
