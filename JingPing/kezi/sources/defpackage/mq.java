package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import com.google.android.material.R$color;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class mq extends kq {
    public StateListAnimator N;

    @Override // defpackage.kq
    public final float e() {
        return this.v.getElevation();
    }

    @Override // defpackage.kq
    public final void f(Rect rect) {
        if (((FloatingActionButton) this.w.c).l) {
            super.f(rect);
            return;
        }
        if (this.f) {
            FloatingActionButton floatingActionButton = this.v;
            int sizeDimension = floatingActionButton.getSizeDimension();
            int i = this.k;
            if (sizeDimension < i) {
                int sizeDimension2 = (i - floatingActionButton.getSizeDimension()) / 2;
                rect.set(sizeDimension2, sizeDimension2, sizeDimension2, sizeDimension2);
                return;
            }
        }
        rect.set(0, 0, 0, 0);
    }

    @Override // defpackage.kq
    public final void g(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable layerDrawable;
        ki0 ki0Var = this.a;
        ki0Var.getClass();
        lq lqVar = new lq(ki0Var);
        this.b = lqVar;
        lqVar.setTintList(colorStateList);
        if (mode != null) {
            this.b.setTintMode(mode);
        }
        u50 u50Var = this.b;
        FloatingActionButton floatingActionButton = this.v;
        u50Var.k(floatingActionButton.getContext());
        if (i > 0) {
            Context context = floatingActionButton.getContext();
            ki0 ki0Var2 = this.a;
            ki0Var2.getClass();
            r8 r8Var = new r8(ki0Var2);
            int iA = bh.a(context, R$color.design_fab_stroke_top_outer_color);
            int iA2 = bh.a(context, R$color.design_fab_stroke_top_inner_color);
            int iA3 = bh.a(context, R$color.design_fab_stroke_end_inner_color);
            int iA4 = bh.a(context, R$color.design_fab_stroke_end_outer_color);
            r8Var.i = iA;
            r8Var.j = iA2;
            r8Var.k = iA3;
            r8Var.l = iA4;
            float f = i;
            if (r8Var.h != f) {
                r8Var.h = f;
                r8Var.b.setStrokeWidth(f * 1.3333f);
                r8Var.n = true;
                r8Var.invalidateSelf();
            }
            if (colorStateList != null) {
                r8Var.m = colorStateList.getColorForState(r8Var.getState(), r8Var.m);
            }
            r8Var.p = colorStateList;
            r8Var.n = true;
            r8Var.invalidateSelf();
            this.d = r8Var;
            r8 r8Var2 = this.d;
            r8Var2.getClass();
            u50 u50Var2 = this.b;
            u50Var2.getClass();
            layerDrawable = new LayerDrawable(new Drawable[]{r8Var2, u50Var2});
        } else {
            this.d = null;
            layerDrawable = this.b;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(lf0.c(colorStateList2), layerDrawable, null);
        this.c = rippleDrawable;
        this.e = rippleDrawable;
    }

    @Override // defpackage.kq
    public final void i() {
        r();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // defpackage.kq
    public final void k(float f, float f2, float f3) {
        int i = Build.VERSION.SDK_INT;
        FloatingActionButton floatingActionButton = this.v;
        if (floatingActionButton.getStateListAnimator() == this.N) {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(kq.H, s(f, f3));
            stateListAnimator.addState(kq.I, s(f, f2));
            stateListAnimator.addState(kq.J, s(f, f2));
            stateListAnimator.addState(kq.K, s(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, "elevation", f).setDuration(0L));
            if (i <= 24) {
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(kq.C);
            stateListAnimator.addState(kq.L, animatorSet);
            stateListAnimator.addState(kq.M, s(0.0f, 0.0f));
            this.N = stateListAnimator;
            floatingActionButton.setStateListAnimator(stateListAnimator);
        }
        if (p()) {
            r();
        }
    }

    @Override // defpackage.kq
    public final void n(ColorStateList colorStateList) {
        Drawable drawable = this.c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(lf0.c(colorStateList));
        } else {
            super.n(colorStateList);
        }
    }

    @Override // defpackage.kq
    public final boolean p() {
        if (((FloatingActionButton) this.w.c).l) {
            return true;
        }
        return this.f && this.v.getSizeDimension() < this.k;
    }

    public final AnimatorSet s(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        float[] fArr = {f};
        FloatingActionButton floatingActionButton = this.v;
        animatorSet.play(ObjectAnimator.ofFloat(floatingActionButton, "elevation", fArr).setDuration(0L)).with(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f2).setDuration(100L));
        animatorSet.setInterpolator(kq.C);
        return animatorSet;
    }

    @Override // defpackage.kq
    public final void h() {
    }

    @Override // defpackage.kq
    public final void q() {
    }

    @Override // defpackage.kq
    public final void j(int[] iArr) {
    }
}
