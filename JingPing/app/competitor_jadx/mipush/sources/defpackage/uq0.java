package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class uq0 extends xq0 implements Iterator {
    public vq0 E;
    public vq0 F;
    public final /* synthetic */ int G;

    public uq0(vq0 vq0Var, vq0 vq0Var2, int i) {
        this.G = i;
        this.E = vq0Var2;
        this.F = vq0Var;
    }

    @Override // defpackage.xq0
    public final void a(vq0 vq0Var) {
        vq0 vq0Var2;
        vq0 vq0VarB = null;
        if (this.E == vq0Var && vq0Var == this.F) {
            this.F = null;
            this.E = null;
        }
        vq0 vq0Var3 = this.E;
        if (vq0Var3 == vq0Var) {
            switch (this.G) {
                case 0:
                    vq0Var2 = vq0Var3.H;
                    break;
                default:
                    vq0Var2 = vq0Var3.G;
                    break;
            }
            this.E = vq0Var2;
        }
        vq0 vq0Var4 = this.F;
        if (vq0Var4 == vq0Var) {
            vq0 vq0Var5 = this.E;
            if (vq0Var4 != vq0Var5 && vq0Var5 != null) {
                vq0VarB = b(vq0Var4);
            }
            this.F = vq0VarB;
        }
    }

    public final vq0 b(vq0 vq0Var) {
        switch (this.G) {
            case 0:
                return vq0Var.G;
            default:
                return vq0Var.H;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.F != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        vq0 vq0Var = this.F;
        vq0 vq0Var2 = this.E;
        this.F = (vq0Var == vq0Var2 || vq0Var2 == null) ? null : b(vq0Var);
        return vq0Var;
    }
}
