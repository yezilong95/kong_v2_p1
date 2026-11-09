package defpackage;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class q {
    public static final q b;
    public static final q c;
    public final CancellationException a;

    static {
        if (w.d) {
            c = null;
            b = null;
        } else {
            c = new q(false, null);
            b = new q(true, null);
        }
    }

    public q(boolean z, CancellationException cancellationException) {
        this.a = cancellationException;
    }
}
