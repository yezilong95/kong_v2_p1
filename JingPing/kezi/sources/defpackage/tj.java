package defpackage;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class tj extends u4 {
    public boolean c;
    public boolean d;
    public e5 e;

    public final e5 j(Context context) {
        if (this.d) {
            return this.e;
        }
        zj0 zj0Var = (zj0) this.a;
        e5 e5VarK = jq0.K(context, zj0Var.c, zj0Var.a == 2, this.c);
        this.e = e5VarK;
        this.d = true;
        return e5VarK;
    }
}
