package defpackage;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class mn extends z5 {
    public boolean c;
    public boolean d;
    public i6 e;

    public final i6 q(Context context) {
        if (this.d) {
            return this.e;
        }
        nw0 nw0Var = (nw0) this.a;
        i6 i6VarC = j31.C(context, nw0Var.c, nw0Var.a == 2, this.c);
        this.e = i6VarC;
        this.d = true;
        return i6VarC;
    }
}
