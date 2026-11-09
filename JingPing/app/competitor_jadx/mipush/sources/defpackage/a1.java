package defpackage;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class a1 {
    public static final a1 b;
    public static final a1 c;
    public final CancellationException a;

    static {
        if (g1.d) {
            c = null;
            b = null;
        } else {
            c = new a1(false, null);
            b = new a1(true, null);
        }
    }

    public a1(boolean z, CancellationException cancellationException) {
        this.a = cancellationException;
    }
}
