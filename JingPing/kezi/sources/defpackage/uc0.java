package defpackage;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class uc0 extends WeakReference {
    public final Object a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public uc0(wc0 wc0Var, Object obj) {
        super(wc0Var);
        oy.j(wc0Var, "referent");
        this.a = obj;
    }
}
