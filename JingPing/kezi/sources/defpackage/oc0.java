package defpackage;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class oc0 implements Serializable {
    public static final oc0 b = new oc0();
    private static final long serialVersionUID = 0;

    private final Object readResolve() {
        return pc0.Default;
    }
}
