package defpackage;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class du implements Runnable {
    public static final Logger F = Logger.getLogger(du.class.getName());
    public final LinkedList E = new LinkedList();

    @Override // java.lang.Runnable
    public final void run() {
        Runnable runnable;
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this.E) {
                runnable = (Runnable) this.E.poll();
            }
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable unused) {
                }
            } else {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException unused2) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        Logger logger = F;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("entropy thread interrupted - exiting");
        }
    }
}
