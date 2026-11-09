package defpackage;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.card.MaterialCardView;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k50 {
    public static final double y = Math.cos(Math.toRadians(45.0d));
    public static final ColorDrawable z;
    public final MaterialCardView a;
    public final Rect b;
    public final u50 c;
    public final u50 d;
    public int e;
    public int f;
    public int g;
    public int h;
    public Drawable i;
    public Drawable j;
    public ColorStateList k;
    public ColorStateList l;
    public ki0 m;
    public ColorStateList n;
    public RippleDrawable o;
    public LayerDrawable p;
    public u50 q;
    public boolean r;
    public boolean s;
    public ValueAnimator t;
    public final TimeInterpolator u;
    public final int v;
    public final int w;
    public float x;

    static {
        z = Build.VERSION.SDK_INT <= 28 ? new ColorDrawable() : null;
    }

    public k50(MaterialCardView materialCardView, AttributeSet attributeSet, int i) {
        int i2 = MaterialCardView.p;
        this.b = new Rect();
        this.r = false;
        this.x = 0.0f;
        this.a = materialCardView;
        u50 u50Var = new u50(materialCardView.getContext(), attributeSet, i, i2);
        this.c = u50Var;
        u50Var.k(materialCardView.getContext());
        u50Var.p();
        ii0 ii0VarG = u50Var.b.a.g();
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R$style.CardView);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CardView_cardCornerRadius)) {
            ii0VarG.c(typedArrayObtainStyledAttributes.getDimension(R$styleable.CardView_cardCornerRadius, 0.0f));
        }
        this.d = new u50();
        h(ii0VarG.a());
        this.u = k9.F(materialCardView.getContext(), R$attr.motionEasingLinearInterpolator, t3.a);
        this.v = k9.E(materialCardView.getContext(), R$attr.motionDurationShort2, 300);
        this.w = k9.E(materialCardView.getContext(), R$attr.motionDurationShort1, 300);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static float b(nv0 nv0Var, float f) {
        if (nv0Var instanceof yf0) {
            return (float) ((1.0d - y) * ((double) f));
        }
        if (nv0Var instanceof ii) {
            return f / 2.0f;
        }
        return 0.0f;
    }

    public final float a() {
        nv0 nv0Var = this.m.a;
        u50 u50Var = this.c;
        return Math.max(Math.max(b(nv0Var, u50Var.i()), b(this.m.b, u50Var.b.a.f.a(u50Var.g()))), Math.max(b(this.m.c, u50Var.b.a.g.a(u50Var.g())), b(this.m.d, u50Var.b.a.h.a(u50Var.g()))));
    }

    public final LayerDrawable c() {
        if (this.o == null) {
            int[] iArr = lf0.a;
            this.q = new u50(this.m);
            this.o = new RippleDrawable(this.k, null, this.q);
        }
        if (this.p == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.o, this.d, this.j});
            this.p = layerDrawable;
            layerDrawable.setId(2, R$id.mtrl_card_checked_layer_id);
        }
        return this.p;
    }

    public final j50 d(Drawable drawable) {
        int iCeil;
        int i;
        if (this.a.getUseCompatPadding()) {
            int iCeil2 = (int) Math.ceil((r0.getMaxCardElevation() * 1.5f) + (i() ? a() : 0.0f));
            iCeil = (int) Math.ceil(r0.getMaxCardElevation() + (i() ? a() : 0.0f));
            i = iCeil2;
        } else {
            iCeil = 0;
            i = 0;
        }
        return new j50(drawable, iCeil, i, iCeil, i);
    }

    public final void e(int i, int i2) {
        int iCeil;
        int iCeil2;
        int i3;
        int i4;
        if (this.p != null) {
            MaterialCardView materialCardView = this.a;
            if (materialCardView.getUseCompatPadding()) {
                iCeil = (int) Math.ceil(((materialCardView.getMaxCardElevation() * 1.5f) + (i() ? a() : 0.0f)) * 2.0f);
                iCeil2 = (int) Math.ceil((materialCardView.getMaxCardElevation() + (i() ? a() : 0.0f)) * 2.0f);
            } else {
                iCeil = 0;
                iCeil2 = 0;
            }
            int i5 = this.g;
            int i6 = (i5 & 8388613) == 8388613 ? ((i - this.e) - this.f) - iCeil2 : this.e;
            int i7 = (i5 & 80) == 80 ? this.e : ((i2 - this.e) - this.f) - iCeil;
            int i8 = (i5 & 8388613) == 8388613 ? this.e : ((i - this.e) - this.f) - iCeil2;
            int i9 = (i5 & 80) == 80 ? ((i2 - this.e) - this.f) - iCeil : this.e;
            WeakHashMap weakHashMap = xr0.a;
            if (materialCardView.getLayoutDirection() == 1) {
                i4 = i8;
                i3 = i6;
            } else {
                i3 = i8;
                i4 = i6;
            }
            this.p.setLayerInset(2, i4, i9, i3, i7);
        }
    }

    public final void f(boolean z2, boolean z3) {
        Drawable drawable = this.j;
        if (drawable != null) {
            if (!z3) {
                drawable.setAlpha(z2 ? 255 : 0);
                this.x = z2 ? 1.0f : 0.0f;
                return;
            }
            float f = z2 ? 1.0f : 0.0f;
            float f2 = z2 ? 1.0f - this.x : this.x;
            ValueAnimator valueAnimator = this.t;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                this.t = null;
            }
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.x, f);
            this.t = valueAnimatorOfFloat;
            valueAnimatorOfFloat.addUpdateListener(new tl(2, this));
            this.t.setInterpolator(this.u);
            this.t.setDuration((long) ((z2 ? this.v : this.w) * f2));
            this.t.start();
        }
    }

    public final void g(Drawable drawable) {
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.j = drawableMutate;
            hl.h(drawableMutate, this.l);
            f(this.a.k, false);
        } else {
            this.j = z;
        }
        LayerDrawable layerDrawable = this.p;
        if (layerDrawable != null) {
            layerDrawable.setDrawableByLayerId(R$id.mtrl_card_checked_layer_id, this.j);
        }
    }

    public final void h(ki0 ki0Var) {
        this.m = ki0Var;
        u50 u50Var = this.c;
        u50Var.setShapeAppearanceModel(ki0Var);
        u50Var.w = !u50Var.b.a.f(u50Var.g());
        u50 u50Var2 = this.d;
        if (u50Var2 != null) {
            u50Var2.setShapeAppearanceModel(ki0Var);
        }
        u50 u50Var3 = this.q;
        if (u50Var3 != null) {
            u50Var3.setShapeAppearanceModel(ki0Var);
        }
    }

    public final boolean i() {
        MaterialCardView materialCardView = this.a;
        if (!materialCardView.getPreventCornerOverlap()) {
            return false;
        }
        u50 u50Var = this.c;
        return u50Var.b.a.f(u50Var.g()) && materialCardView.getUseCompatPadding();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j() {
        /*
            r7 = this;
            com.google.android.material.card.MaterialCardView r0 = r7.a
            boolean r1 = r0.getPreventCornerOverlap()
            r2 = 0
            if (r1 == 0) goto L1b
            u50 r1 = r7.c
            t50 r3 = r1.b
            ki0 r3 = r3.a
            android.graphics.RectF r1 = r1.g()
            boolean r1 = r3.f(r1)
            if (r1 != 0) goto L1b
            r1 = 1
            goto L1c
        L1b:
            r1 = r2
        L1c:
            r3 = 0
            if (r1 != 0) goto L28
            boolean r1 = r7.i()
            if (r1 == 0) goto L26
            goto L28
        L26:
            r1 = r3
            goto L2c
        L28:
            float r1 = r7.a()
        L2c:
            boolean r4 = r0.getPreventCornerOverlap()
            if (r4 == 0) goto L44
            boolean r4 = r0.getUseCompatPadding()
            if (r4 == 0) goto L44
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r5 = defpackage.k50.y
            double r3 = r3 - r5
            float r5 = r0.getCardViewRadius()
            double r5 = (double) r5
            double r3 = r3 * r5
            float r3 = (float) r3
        L44:
            float r1 = r1 - r3
            int r1 = (int) r1
            android.graphics.Rect r3 = r7.b
            int r4 = r3.left
            int r4 = r4 + r1
            int r5 = r3.top
            int r5 = r5 + r1
            int r6 = r3.right
            int r6 = r6 + r1
            int r3 = r3.bottom
            int r3 = r3 + r1
            android.graphics.Rect r1 = r0.d
            r1.set(r4, r5, r6, r3)
            e5 r0 = r0.f
            java.lang.Object r1 = r0.d
            androidx.cardview.widget.CardView r1 = (androidx.cardview.widget.CardView) r1
            boolean r1 = r1.getUseCompatPadding()
            if (r1 != 0) goto L69
            r0.R(r2, r2, r2, r2)
            return
        L69:
            java.lang.Object r1 = r0.c
            android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
            wf0 r1 = (defpackage.wf0) r1
            float r2 = r1.e
            float r1 = r1.a
            java.lang.Object r3 = r0.d
            androidx.cardview.widget.CardView r3 = (androidx.cardview.widget.CardView) r3
            boolean r4 = r3.getPreventCornerOverlap()
            float r4 = defpackage.xf0.a(r2, r1, r4)
            double r4 = (double) r4
            double r4 = java.lang.Math.ceil(r4)
            int r4 = (int) r4
            boolean r3 = r3.getPreventCornerOverlap()
            float r1 = defpackage.xf0.b(r2, r1, r3)
            double r1 = (double) r1
            double r1 = java.lang.Math.ceil(r1)
            int r1 = (int) r1
            r0.R(r4, r1, r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.k50.j():void");
    }

    public final void k() {
        boolean z2 = this.r;
        MaterialCardView materialCardView = this.a;
        if (!z2) {
            materialCardView.setBackgroundInternal(d(this.c));
        }
        materialCardView.setForeground(d(this.i));
    }
}
