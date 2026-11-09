package defpackage;

import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class dj implements aj {
    public final Lambda E;
    public final aj F;

    /* JADX WARN: Multi-variable type inference failed */
    public dj(aj ajVar, b10 b10Var) {
        t40.n(ajVar, "baseKey");
        t40.n(b10Var, "safeCast");
        this.E = (Lambda) b10Var;
        this.F = ajVar instanceof dj ? ((dj) ajVar).F : ajVar;
    }
}
