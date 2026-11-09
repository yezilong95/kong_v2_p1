package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class td0 implements ti0 {
    public final ea0 a;
    public final ti0 b;
    public int c = -1;

    public td0(ea0 ea0Var, ti0 ti0Var) {
        this.a = ea0Var;
        this.b = ti0Var;
    }

    @Override // defpackage.ti0
    public final void onChanged(Object obj) {
        int i = this.c;
        int i2 = this.a.g;
        if (i != i2) {
            this.c = i2;
            this.b.onChanged(obj);
        }
    }
}
