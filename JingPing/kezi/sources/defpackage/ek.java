package defpackage;

import java.util.Iterator;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class ek implements xh0 {
    public final /* synthetic */ int a = 2;
    public final Object b;
    public final Object c;

    public ek(ek ekVar, f fVar) {
        this.b = ekVar;
        this.c = fVar;
    }

    @Override // defpackage.xh0
    public final Iterator iterator() {
        switch (this.a) {
            case 0:
                return new dk(this);
            case 1:
                return new dv(this);
            default:
                return new dp0(this);
        }
    }

    public ek(ge0 ge0Var, he0 he0Var) {
        oy.j(he0Var, "getNextValue");
        this.b = ge0Var;
        this.c = he0Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ek(String str, ru ruVar) {
        oy.j(str, "input");
        this.b = str;
        this.c = (Lambda) ruVar;
    }
}
