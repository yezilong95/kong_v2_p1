package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class hg0 extends kg0 implements Iterator {
    public ig0 b;
    public ig0 c;
    public final /* synthetic */ int d;

    public hg0(ig0 ig0Var, ig0 ig0Var2, int i) {
        this.d = i;
        this.b = ig0Var2;
        this.c = ig0Var;
    }

    @Override // defpackage.kg0
    public final void a(ig0 ig0Var) {
        ig0 ig0Var2;
        ig0 ig0VarB = null;
        if (this.b == ig0Var && ig0Var == this.c) {
            this.c = null;
            this.b = null;
        }
        ig0 ig0Var3 = this.b;
        if (ig0Var3 == ig0Var) {
            switch (this.d) {
                case 0:
                    ig0Var2 = ig0Var3.e;
                    break;
                default:
                    ig0Var2 = ig0Var3.d;
                    break;
            }
            this.b = ig0Var2;
        }
        ig0 ig0Var4 = this.c;
        if (ig0Var4 == ig0Var) {
            ig0 ig0Var5 = this.b;
            if (ig0Var4 != ig0Var5 && ig0Var5 != null) {
                ig0VarB = b(ig0Var4);
            }
            this.c = ig0VarB;
        }
    }

    public final ig0 b(ig0 ig0Var) {
        switch (this.d) {
            case 0:
                return ig0Var.d;
            default:
                return ig0Var.e;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        ig0 ig0Var = this.c;
        ig0 ig0Var2 = this.b;
        this.c = (ig0Var == ig0Var2 || ig0Var2 == null) ? null : b(ig0Var);
        return ig0Var;
    }
}
