package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class wq0 extends xq0 implements Iterator {
    public vq0 E;
    public boolean F = true;
    public final /* synthetic */ yq0 G;

    public wq0(yq0 yq0Var) {
        this.G = yq0Var;
    }

    @Override // defpackage.xq0
    public final void a(vq0 vq0Var) {
        vq0 vq0Var2 = this.E;
        if (vq0Var == vq0Var2) {
            vq0 vq0Var3 = vq0Var2.H;
            this.E = vq0Var3;
            this.F = vq0Var3 == null;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.F) {
            return this.G.E != null;
        }
        vq0 vq0Var = this.E;
        return (vq0Var == null || vq0Var.G == null) ? false : true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.F) {
            this.F = false;
            this.E = this.G.E;
        } else {
            vq0 vq0Var = this.E;
            this.E = vq0Var != null ? vq0Var.G : null;
        }
        return this.E;
    }
}
