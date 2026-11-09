package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class iq0 implements dl0 {
    public final /* synthetic */ int E;
    public final int F;

    public /* synthetic */ iq0(int i, int i2) {
        this.E = i2;
        this.F = i;
    }

    @Override // defpackage.dl0
    public el0 a(el0 el0Var) {
        c71 c71Var = el0Var instanceof c71 ? (c71) el0Var : null;
        int i = this.F;
        if (c71Var != null && c71Var.a == i) {
            return c71Var;
        }
        c71 c71Var2 = new c71();
        c71Var2.a = i;
        return c71Var2;
    }

    public int b() {
        switch (this.E) {
        }
        return this.F;
    }
}
