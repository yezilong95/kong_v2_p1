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
public final class zw extends xw {
    public StateListAnimator N;

    @Override // defpackage.xw
    public final float e() {
        return this.v.getElevation();
    }

    @Override // defpackage.xw
    public final void f(Rect rect) {
        if (((FloatingActionButton) this.w.F).O) {
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

    @Override // defpackage.xw
    public final void g(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable layerDrawable;
        zu0 zu0Var = this.a;
        zu0Var.getClass();
        yw ywVar = new yw(zu0Var);
        this.b = ywVar;
        ywVar.setTintList(colorStateList);
        if (mode != null) {
            this.b.setTintMode(mode);
        }
        ld0 ld0Var = this.b;
        FloatingActionButton floatingActionButton = this.v;
        ld0Var.k(floatingActionButton.getContext());
        if (i > 0) {
            Context context = floatingActionButton.getContext();
            zu0 zu0Var2 = this.a;
            zu0Var2.getClass();
            qa qaVar = new qa(zu0Var2);
            int iA = qi.a(context, R$color.design_fab_stroke_top_outer_color);
            int iA2 = qi.a(context, R$color.design_fab_stroke_top_inner_color);
            int iA3 = qi.a(context, R$color.design_fab_stroke_end_inner_color);
            int iA4 = qi.a(context, R$color.design_fab_stroke_end_outer_color);
            qaVar.i = iA;
            qaVar.j = iA2;
            qaVar.k = iA3;
            qaVar.l = iA4;
            float f = i;
            if (qaVar.h != f) {
                qaVar.h = f;
                qaVar.b.setStrokeWidth(f * 1.3333f);
                qaVar.n = true;
                qaVar.invalidateSelf();
            }
            if (colorStateList != null) {
                qaVar.m = colorStateList.getColorForState(qaVar.getState(), qaVar.m);
            }
            qaVar.p = colorStateList;
            qaVar.n = true;
            qaVar.invalidateSelf();
            this.d = qaVar;
            qa qaVar2 = this.d;
            qaVar2.getClass();
            ld0 ld0Var2 = this.b;
            ld0Var2.getClass();
            layerDrawable = new LayerDrawable(new Drawable[]{qaVar2, ld0Var2});
        } else {
            this.d = null;
            layerDrawable = this.b;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(gp0.c(colorStateList2), layerDrawable, null);
        this.c = rippleDrawable;
        this.e = rippleDrawable;
    }

    @Override // defpackage.xw
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
    @Override // defpackage.xw
    public final void k(float f, float f2, float f3) {
        int i = Build.VERSION.SDK_INT;
        FloatingActionButton floatingActionButton = this.v;
        if (floatingActionButton.getStateListAnimator() == this.N) {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(xw.H, s(f, f3));
            stateListAnimator.addState(xw.I, s(f, f2));
            stateListAnimator.addState(xw.J, s(f, f2));
            stateListAnimator.addState(xw.K, s(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, "elevation", f).setDuration(0L));
            if (i <= 24) {
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(xw.C);
            stateListAnimator.addState(xw.L, animatorSet);
            stateListAnimator.addState(xw.M, s(0.0f, 0.0f));
            this.N = stateListAnimator;
            floatingActionButton.setStateListAnimator(stateListAnimator);
        }
        if (p()) {
            r();
        }
    }

    @Override // defpackage.xw
    public final void n(ColorStateList colorStateList) {
        Drawable drawable = this.c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(gp0.c(colorStateList));
        } else {
            super.n(colorStateList);
        }
    }

    @Override // defpackage.xw
    public final boolean p() {
        if (((FloatingActionButton) this.w.F).O) {
            return true;
        }
        return this.f && this.v.getSizeDimension() < this.k;
    }

    public final AnimatorSet s(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        float[] fArr = {f};
        FloatingActionButton floatingActionButton = this.v;
        animatorSet.play(ObjectAnimator.ofFloat(floatingActionButton, "elevation", fArr).setDuration(0L)).with(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f2).setDuration(100L));
        animatorSet.setInterpolator(xw.C);
        return animatorSet;
    }

    @Override // defpackage.xw
    public final void h() {
    }

    @Override // defpackage.xw
    public final void q() {
    }

    @Override // defpackage.xw
    public final void j(int[] iArr) {
    }
}
