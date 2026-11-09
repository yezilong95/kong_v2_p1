package defpackage;

import android.os.Looper;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class pp0 extends v40 {
    public final /* synthetic */ qp0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public pp0(String[] strArr, qp0 qp0Var) {
        super(strArr);
        this.b = qp0Var;
    }

    @Override // defpackage.v40
    public final void a(Set set) {
        t40.n(set, "tables");
        e8 e8VarK0 = e8.k0();
        op0 op0Var = this.b.u;
        e8VarK0.j.getClass();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            op0Var.run();
        } else {
            e8VarK0.l0(op0Var);
        }
    }
}
