package defpackage;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.card.MaterialCardView;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ad0 {
    public static final double y = Math.cos(Math.toRadians(45.0d));
    public static final ColorDrawable z;
    public final MaterialCardView a;
    public final Rect b;
    public final ld0 c;
    public final ld0 d;
    public int e;
    public int f;
    public int g;
    public int h;
    public Drawable i;
    public Drawable j;
    public ColorStateList k;
    public ColorStateList l;
    public zu0 m;
    public ColorStateList n;
    public RippleDrawable o;
    public LayerDrawable p;
    public ld0 q;
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

    public ad0(MaterialCardView materialCardView, AttributeSet attributeSet, int i) {
        int i2 = MaterialCardView.S;
        this.b = new Rect();
        this.r = false;
        this.x = 0.0f;
        this.a = materialCardView;
        ld0 ld0Var = new ld0(materialCardView.getContext(), attributeSet, i, i2);
        this.c = ld0Var;
        ld0Var.k(materialCardView.getContext());
        ld0Var.p();
        xu0 xu0VarG = ld0Var.E.a.g();
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R$style.CardView);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.CardView_cardCornerRadius)) {
            xu0VarG.c(typedArrayObtainStyledAttributes.getDimension(R$styleable.CardView_cardCornerRadius, 0.0f));
        }
        this.d = new ld0();
        h(xu0VarG.a());
        this.u = s90.V(materialCardView.getContext(), R$attr.motionEasingLinearInterpolator, y4.a);
        this.v = s90.U(materialCardView.getContext(), R$attr.motionDurationShort2, 300);
        this.w = s90.U(materialCardView.getContext(), R$attr.motionDurationShort1, 300);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static float b(z91 z91Var, float f) {
        if (z91Var instanceof up0) {
            return (float) ((1.0d - y) * ((double) f));
        }
        if (z91Var instanceof hk) {
            return f / 2.0f;
        }
        return 0.0f;
    }

    public final float a() {
        z91 z91Var = this.m.a;
        ld0 ld0Var = this.c;
        return Math.max(Math.max(b(z91Var, ld0Var.i()), b(this.m.b, ld0Var.E.a.f.a(ld0Var.g()))), Math.max(b(this.m.c, ld0Var.E.a.g.a(ld0Var.g())), b(this.m.d, ld0Var.E.a.h.a(ld0Var.g()))));
    }

    public final LayerDrawable c() {
        if (this.o == null) {
            int[] iArr = gp0.a;
            this.q = new ld0(this.m);
            this.o = new RippleDrawable(this.k, null, this.q);
        }
        if (this.p == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.o, this.d, this.j});
            this.p = layerDrawable;
            layerDrawable.setId(2, R$id.mtrl_card_checked_layer_id);
        }
        return this.p;
    }

    public final zc0 d(Drawable drawable) {
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
        return new zc0(drawable, iCeil, i, iCeil, i);
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
            WeakHashMap weakHashMap = f51.a;
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
            valueAnimatorOfFloat.addUpdateListener(new wp(2, this));
            this.t.setInterpolator(this.u);
            this.t.setDuration((long) ((z2 ? this.v : this.w) * f2));
            this.t.start();
        }
    }

    public final void g(Drawable drawable) {
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.j = drawableMutate;
            ip.h(drawableMutate, this.l);
            f(this.a.N, false);
        } else {
            this.j = z;
        }
        LayerDrawable layerDrawable = this.p;
        if (layerDrawable != null) {
            layerDrawable.setDrawableByLayerId(R$id.mtrl_card_checked_layer_id, this.j);
        }
    }

    public final void h(zu0 zu0Var) {
        this.m = zu0Var;
        ld0 ld0Var = this.c;
        ld0Var.setShapeAppearanceModel(zu0Var);
        ld0Var.a0 = !ld0Var.E.a.f(ld0Var.g());
        ld0 ld0Var2 = this.d;
        if (ld0Var2 != null) {
            ld0Var2.setShapeAppearanceModel(zu0Var);
        }
        ld0 ld0Var3 = this.q;
        if (ld0Var3 != null) {
            ld0Var3.setShapeAppearanceModel(zu0Var);
        }
    }

    public final boolean i() {
        MaterialCardView materialCardView = this.a;
        if (!materialCardView.getPreventCornerOverlap()) {
            return false;
        }
        ld0 ld0Var = this.c;
        return ld0Var.E.a.f(ld0Var.g()) && materialCardView.getUseCompatPadding();
    }

    public final boolean j() {
        View view = this.a;
        if (view.isClickable()) {
            return true;
        }
        while (view.isDuplicateParentStateEnabled() && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        return view.isClickable();
    }

    public final void k() {
        Drawable drawable = this.i;
        Drawable drawableC = j() ? c() : this.d;
        this.i = drawableC;
        if (drawable != drawableC) {
            MaterialCardView materialCardView = this.a;
            if (materialCardView.getForeground() instanceof InsetDrawable) {
                ((InsetDrawable) materialCardView.getForeground()).setDrawable(drawableC);
            } else {
                materialCardView.setForeground(d(drawableC));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l() {
        /*
            r7 = this;
            com.google.android.material.card.MaterialCardView r0 = r7.a
            boolean r1 = r0.getPreventCornerOverlap()
            r2 = 0
            if (r1 == 0) goto L1b
            ld0 r1 = r7.c
            kd0 r3 = r1.E
            zu0 r3 = r3.a
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
            double r5 = defpackage.ad0.y
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
            android.graphics.Rect r1 = r0.G
            r1.set(r4, r5, r6, r3)
            i6 r0 = r0.I
            java.lang.Object r1 = r0.G
            androidx.cardview.widget.CardView r1 = (androidx.cardview.widget.CardView) r1
            boolean r1 = r1.getUseCompatPadding()
            if (r1 != 0) goto L69
            r0.P(r2, r2, r2, r2)
            return
        L69:
            java.lang.Object r1 = r0.F
            android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
            sp0 r1 = (defpackage.sp0) r1
            float r2 = r1.e
            float r1 = r1.a
            java.lang.Object r3 = r0.G
            androidx.cardview.widget.CardView r3 = (androidx.cardview.widget.CardView) r3
            boolean r4 = r3.getPreventCornerOverlap()
            float r4 = defpackage.tp0.a(r2, r1, r4)
            double r4 = (double) r4
            double r4 = java.lang.Math.ceil(r4)
            int r4 = (int) r4
            boolean r3 = r3.getPreventCornerOverlap()
            float r1 = defpackage.tp0.b(r2, r1, r3)
            double r1 = (double) r1
            double r1 = java.lang.Math.ceil(r1)
            int r1 = (int) r1
            r0.P(r4, r1, r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ad0.l():void");
    }

    public final void m() {
        boolean z2 = this.r;
        MaterialCardView materialCardView = this.a;
        if (!z2) {
            materialCardView.setBackgroundInternal(d(this.c));
        }
        materialCardView.setForeground(d(this.i));
    }
}
