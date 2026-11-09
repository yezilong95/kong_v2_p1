package defpackage;

import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class p0 extends Lambda implements b10 {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p0(int i, Object obj) {
        super(1);
        this.E = i;
        this.F = obj;
    }

    @Override // defpackage.b10
    public final Object invoke(Object obj) {
        switch (this.E) {
            case 0:
                return obj == ((v0) this.F) ? "(this Collection)" : String.valueOf(obj);
            default:
                q40 q40Var = (q40) obj;
                t40.n(q40Var, "it");
                return ((String) this.F).subSequence(q40Var.E, q40Var.F + 1).toString();
        }
    }
}
