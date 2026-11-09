package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class d60 implements ba0 {
    public final b30 a;
    public final ba0 b;
    public int c = -1;

    public d60(b30 b30Var, ba0 ba0Var) {
        this.a = b30Var;
        this.b = ba0Var;
    }

    @Override // defpackage.ba0
    public final void onChanged(Object obj) {
        int i = this.c;
        int i2 = this.a.g;
        if (i != i2) {
            this.c = i2;
            this.b.onChanged(obj);
        }
    }
}
