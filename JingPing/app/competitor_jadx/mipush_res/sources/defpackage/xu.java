package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.google.android.material.R$animator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class xu extends v9 {
    public final av g;
    public final boolean h;
    public final /* synthetic */ ExtendedFloatingActionButton i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public xu(ExtendedFloatingActionButton extendedFloatingActionButton, p50 p50Var, av avVar, boolean z) {
        super(extendedFloatingActionButton, p50Var);
        this.i = extendedFloatingActionButton;
        this.g = avVar;
        this.h = z;
    }

    @Override // defpackage.v9
    public final AnimatorSet a() {
        rf0 rf0Var = this.f;
        if (rf0Var == null) {
            if (this.e == null) {
                this.e = rf0.b(this.a, c());
            }
            rf0Var = this.e;
            rf0Var.getClass();
        }
        boolean zG = rf0Var.g("width");
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        av avVar = this.g;
        if (zG) {
            PropertyValuesHolder[] propertyValuesHolderArrE = rf0Var.e("width");
            propertyValuesHolderArrE[0].setFloatValues(extendedFloatingActionButton.getWidth(), avVar.getWidth());
            rf0Var.h("width", propertyValuesHolderArrE);
        }
        if (rf0Var.g("height")) {
            PropertyValuesHolder[] propertyValuesHolderArrE2 = rf0Var.e("height");
            propertyValuesHolderArrE2[0].setFloatValues(extendedFloatingActionButton.getHeight(), avVar.getHeight());
            rf0Var.h("height", propertyValuesHolderArrE2);
        }
        if (rf0Var.g("paddingStart")) {
            PropertyValuesHolder[] propertyValuesHolderArrE3 = rf0Var.e("paddingStart");
            PropertyValuesHolder propertyValuesHolder = propertyValuesHolderArrE3[0];
            WeakHashMap weakHashMap = f51.a;
            propertyValuesHolder.setFloatValues(extendedFloatingActionButton.getPaddingStart(), avVar.g());
            rf0Var.h("paddingStart", propertyValuesHolderArrE3);
        }
        if (rf0Var.g("paddingEnd")) {
            PropertyValuesHolder[] propertyValuesHolderArrE4 = rf0Var.e("paddingEnd");
            PropertyValuesHolder propertyValuesHolder2 = propertyValuesHolderArrE4[0];
            WeakHashMap weakHashMap2 = f51.a;
            propertyValuesHolder2.setFloatValues(extendedFloatingActionButton.getPaddingEnd(), avVar.c());
            rf0Var.h("paddingEnd", propertyValuesHolderArrE4);
        }
        if (rf0Var.g("labelOpacity")) {
            PropertyValuesHolder[] propertyValuesHolderArrE5 = rf0Var.e("labelOpacity");
            boolean z = this.h;
            propertyValuesHolderArrE5[0].setFloatValues(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
            rf0Var.h("labelOpacity", propertyValuesHolderArrE5);
        }
        return b(rf0Var);
    }

    @Override // defpackage.v9
    public final int c() {
        return this.h ? R$animator.mtrl_extended_fab_change_size_expand_motion_spec : R$animator.mtrl_extended_fab_change_size_collapse_motion_spec;
    }

    @Override // defpackage.v9
    public final void e() {
        this.d.F = null;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        extendedFloatingActionButton.l0 = false;
        extendedFloatingActionButton.setHorizontallyScrolling(false);
        ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        av avVar = this.g;
        layoutParams.width = avVar.p().width;
        layoutParams.height = avVar.p().height;
    }

    @Override // defpackage.v9
    public final void f(Animator animator) {
        p50 p50Var = this.d;
        Animator animator2 = (Animator) p50Var.F;
        if (animator2 != null) {
            animator2.cancel();
        }
        p50Var.F = animator;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        extendedFloatingActionButton.k0 = this.h;
        extendedFloatingActionButton.l0 = true;
        extendedFloatingActionButton.setHorizontallyScrolling(true);
    }

    @Override // defpackage.v9
    public final void g() {
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        boolean z = this.h;
        extendedFloatingActionButton.k0 = z;
        ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (!z) {
            extendedFloatingActionButton.o0 = layoutParams.width;
            extendedFloatingActionButton.p0 = layoutParams.height;
        }
        av avVar = this.g;
        layoutParams.width = avVar.p().width;
        layoutParams.height = avVar.p().height;
        int iG = avVar.g();
        int paddingTop = extendedFloatingActionButton.getPaddingTop();
        int iC = avVar.c();
        int paddingBottom = extendedFloatingActionButton.getPaddingBottom();
        WeakHashMap weakHashMap = f51.a;
        extendedFloatingActionButton.setPaddingRelative(iG, paddingTop, iC, paddingBottom);
        extendedFloatingActionButton.requestLayout();
    }

    @Override // defpackage.v9
    public final boolean h() {
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        return this.h == extendedFloatingActionButton.k0 || extendedFloatingActionButton.getIcon() == null || TextUtils.isEmpty(extendedFloatingActionButton.getText());
    }
}
