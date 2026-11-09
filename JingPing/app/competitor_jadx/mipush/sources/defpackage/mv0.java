package defpackage;

import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class mv0 extends lv0 {
    public boolean f = false;
    public float g = 0.0f;

    public mv0(FrameLayout frameLayout) {
        d(frameLayout);
    }

    private void d(View view) {
        view.setOutlineProvider(new ee(1, this));
    }

    @Override // defpackage.lv0
    public final void a(FrameLayout frameLayout) {
        zu0 zu0Var;
        zu0 zu0Var2;
        RectF rectF;
        zu0 zu0Var3 = this.c;
        this.g = (zu0Var3 == null || (rectF = this.d) == null) ? 0.0f : zu0Var3.f.a(rectF);
        boolean z = false;
        if ((this.d.isEmpty() || (zu0Var2 = this.c) == null) ? false : zu0Var2.f(this.d)) {
            z = true;
        } else if (!this.d.isEmpty() && (zu0Var = this.c) != null && this.b && !zu0Var.f(this.d)) {
            zu0 zu0Var4 = this.c;
            if ((zu0Var4.a instanceof up0) && (zu0Var4.b instanceof up0) && (zu0Var4.d instanceof up0) && (zu0Var4.c instanceof up0)) {
                float fA = zu0Var4.e.a(this.d);
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

    @Override // defpackage.lv0
    public final boolean b() {
        return !this.f || this.a;
    }
}
