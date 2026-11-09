package defpackage;

import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class xi0 extends wi0 {
    public boolean f = false;
    public float g = 0.0f;

    public xi0(FrameLayout frameLayout) {
        c(frameLayout);
    }

    private void c(View view) {
        view.setOutlineProvider(new jc(1, this));
    }

    @Override // defpackage.wi0
    public final void a(FrameLayout frameLayout) {
        ki0 ki0Var;
        ki0 ki0Var2;
        RectF rectF;
        ki0 ki0Var3 = this.c;
        this.g = (ki0Var3 == null || (rectF = this.d) == null) ? 0.0f : ki0Var3.f.a(rectF);
        boolean z = false;
        if ((this.d.isEmpty() || (ki0Var2 = this.c) == null) ? false : ki0Var2.f(this.d)) {
            z = true;
        } else if (!this.d.isEmpty() && (ki0Var = this.c) != null && this.b && !ki0Var.f(this.d)) {
            ki0 ki0Var4 = this.c;
            if ((ki0Var4.a instanceof yf0) && (ki0Var4.b instanceof yf0) && (ki0Var4.d instanceof yf0) && (ki0Var4.c instanceof yf0)) {
                float fA = ki0Var4.e.a(this.d);
                float fA2 = this.c.f.a(this.d);
                float fA3 = this.c.h.a(this.d);
                float fA4 = this.c.g.a(this.d);
                if (fA == 0.0f && fA3 == 0.0f && fA2 == fA4) {
                    RectF rectF2 = this.d;
                    rectF2.set(rectF2.left - fA2, rectF2.top, rectF2.right, rectF2.bottom);
                    this.g = fA2;
                } else if (fA == 0.0f && fA2 == 0.0f && fA3 == fA4) {
                    RectF rectF3 = this.d;
                    rectF3.set(rectF3.left, rectF3.top - fA3, rectF3.right, rectF3.bottom);
                    this.g = fA3;
                } else if (fA2 == 0.0f && fA4 == 0.0f && fA == fA3) {
                    RectF rectF4 = this.d;
                    rectF4.set(rectF4.left, rectF4.top, rectF4.right + fA, rectF4.bottom);
                    this.g = fA;
                } else if (fA3 == 0.0f && fA4 == 0.0f && fA == fA2) {
                    RectF rectF5 = this.d;
                    rectF5.set(rectF5.left, rectF5.top, rectF5.right, rectF5.bottom + fA);
                    this.g = fA;
                }
                z = true;
            }
        }
        this.f = z;
        frameLayout.setClipToOutline(!b());
        if (b()) {
            frameLayout.invalidate();
        } else {
            frameLayout.invalidateOutline();
        }
    }

    @Override // defpackage.wi0
    public final boolean b() {
        return !this.f || this.a;
    }
}
