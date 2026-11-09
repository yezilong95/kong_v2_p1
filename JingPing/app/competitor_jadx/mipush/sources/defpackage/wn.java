package defpackage;

import java.util.Iterator;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class wn implements lu0 {
    public final /* synthetic */ int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ wn(Object obj, Lambda lambda, int i) {
        this.a = i;
        this.b = obj;
        this.c = lambda;
    }

    @Override // defpackage.lu0
    public final Iterator iterator() {
        switch (this.a) {
            case 0:
                return new vn(this);
            case 1:
                return new c20(this);
            default:
                return new p11(this);
        }
    }

    public wn(go0 go0Var, ho0 ho0Var) {
        this.a = 1;
        t40.n(ho0Var, "getNextValue");
        this.b = go0Var;
        this.c = ho0Var;
    }
}
