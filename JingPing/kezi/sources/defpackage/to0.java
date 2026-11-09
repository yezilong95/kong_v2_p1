package defpackage;

import androidx.appcompat.widget.l;

/* JADX INFO: loaded from: classes.dex */
public final class to0 extends za0 {
    public final /* synthetic */ int p;
    public boolean q;
    public int r;
    public final /* synthetic */ Object s;

    public to0(ct0 ct0Var) {
        this.p = 1;
        this.s = ct0Var;
        this.q = false;
        this.r = 0;
    }

    @Override // defpackage.dt0
    public final void a() {
        switch (this.p) {
            case 0:
                if (!this.q) {
                    ((l) this.s).a.setVisibility(this.r);
                }
                break;
            default:
                int i = this.r + 1;
                this.r = i;
                ct0 ct0Var = (ct0) this.s;
                if (i == ct0Var.a.size()) {
                    za0 za0Var = ct0Var.d;
                    if (za0Var != null) {
                        za0Var.a();
                    }
                    this.r = 0;
                    this.q = false;
                    ct0Var.e = false;
                }
                break;
        }
    }

    @Override // defpackage.za0, defpackage.dt0
    public void b() {
        switch (this.p) {
            case 0:
                this.q = true;
                break;
        }
    }

    @Override // defpackage.za0, defpackage.dt0
    public final void c() {
        switch (this.p) {
            case 0:
                ((l) this.s).a.setVisibility(0);
                break;
            default:
                if (!this.q) {
                    this.q = true;
                    za0 za0Var = ((ct0) this.s).d;
                    if (za0Var != null) {
                        za0Var.c();
                    }
                    break;
                }
                break;
        }
    }

    public to0(l lVar, int i) {
        this.p = 0;
        this.s = lVar;
        this.r = i;
        this.q = false;
    }
}
