package defpackage;

import android.os.Looper;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class uf0 extends qy {
    public final /* synthetic */ vf0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public uf0(String[] strArr, vf0 vf0Var) {
        super(strArr);
        this.b = vf0Var;
    }

    @Override // defpackage.qy
    public final void a(Set set) {
        oy.j(set, "tables");
        t6 t6VarW = t6.W();
        tf0 tf0Var = this.b.u;
        t6VarW.r.getClass();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            tf0Var.run();
        } else {
            t6VarW.X(tf0Var);
        }
    }
}
