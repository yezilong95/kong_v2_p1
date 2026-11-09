package defpackage;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class he implements xh0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ he(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.xh0
    public final Iterator iterator() {
        switch (this.a) {
            case 0:
                return ((List) this.b).iterator();
            case 1:
                ie0 ie0Var = (ie0) this.b;
                yh0 yh0Var = new yh0();
                yh0Var.d = oy.r(ie0Var, yh0Var, yh0Var);
                return yh0Var;
            default:
                return (Iterator) this.b;
        }
    }
}
