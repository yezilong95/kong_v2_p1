package defpackage;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class cp extends Thread {
    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Logger logger = ep.E;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Shutdown hook started");
        }
        dp dpVar = (dp) ep.F.poll();
        while (dpVar != null) {
            ep.G.remove(dpVar);
            dpVar.a.run();
            dpVar = (dp) ep.F.poll();
            Logger logger2 = ep.E;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.fine("Shutdown hook disposed: " + dpVar);
            }
        }
    }
}
