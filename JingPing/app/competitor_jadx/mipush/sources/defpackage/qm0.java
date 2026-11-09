package defpackage;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class qm0 implements Serializable {
    public static final qm0 E = new qm0();
    private static final long serialVersionUID = 0;

    private final Object readResolve() {
        return rm0.Default;
    }
}
