package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class jg0 extends kg0 implements Iterator {
    public ig0 b;
    public boolean c = true;
    public final /* synthetic */ lg0 d;

    public jg0(lg0 lg0Var) {
        this.d = lg0Var;
    }

    @Override // defpackage.kg0
    public final void a(ig0 ig0Var) {
        ig0 ig0Var2 = this.b;
        if (ig0Var == ig0Var2) {
            ig0 ig0Var3 = ig0Var2.e;
            this.b = ig0Var3;
            this.c = ig0Var3 == null;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.c) {
            return this.d.b != null;
        }
        ig0 ig0Var = this.b;
        return (ig0Var == null || ig0Var.d == null) ? false : true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.c) {
            this.c = false;
            this.b = this.d.b;
        } else {
            ig0 ig0Var = this.b;
            this.b = ig0Var != null ? ig0Var.d : null;
        }
        return this.b;
    }
}
