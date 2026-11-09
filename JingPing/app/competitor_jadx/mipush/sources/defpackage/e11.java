package defpackage;

import androidx.appcompat.widget.l;

/* JADX INFO: loaded from: classes.dex */
public final class e11 extends s90 {
    public final /* synthetic */ int w;
    public boolean x;
    public int y;
    public final /* synthetic */ Object z;

    public e11(i61 i61Var) {
        this.w = 1;
        this.z = i61Var;
        this.x = false;
        this.y = 0;
    }

    @Override // defpackage.j61
    public final void a() {
        switch (this.w) {
            case 0:
                if (!this.x) {
                    ((l) this.z).a.setVisibility(this.y);
                }
                break;
            default:
                int i = this.y + 1;
                this.y = i;
                i61 i61Var = (i61) this.z;
                if (i == i61Var.a.size()) {
                    s90 s90Var = i61Var.d;
                    if (s90Var != null) {
                        s90Var.a();
                    }
                    this.y = 0;
                    this.x = false;
                    i61Var.e = false;
                }
                break;
        }
    }

    @Override // defpackage.s90, defpackage.j61
    public void b() {
        switch (this.w) {
            case 0:
                this.x = true;
                break;
        }
    }

    @Override // defpackage.s90, defpackage.j61
    public final void e() {
        switch (this.w) {
            case 0:
                ((l) this.z).a.setVisibility(0);
                break;
            default:
                if (!this.x) {
                    this.x = true;
                    s90 s90Var = ((i61) this.z).d;
                    if (s90Var != null) {
                        s90Var.e();
                    }
                    break;
                }
                break;
        }
    }

    public e11(l lVar, int i) {
        this.w = 0;
        this.z = lVar;
        this.y = i;
        this.x = false;
    }
}
