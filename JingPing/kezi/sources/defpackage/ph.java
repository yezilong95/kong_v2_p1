package defpackage;

import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class ph implements mh {
    public final Lambda b;
    public final mh c;

    /* JADX WARN: Multi-variable type inference failed */
    public ph(mh mhVar, pu puVar) {
        oy.j(mhVar, "baseKey");
        oy.j(puVar, "safeCast");
        this.b = (Lambda) puVar;
        this.c = mhVar instanceof ph ? ((ph) mhVar).c : mhVar;
    }
}
