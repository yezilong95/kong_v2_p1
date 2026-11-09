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
public final class cp extends z7 {
    public final fp g;
    public final boolean h;
    public final /* synthetic */ ExtendedFloatingActionButton i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cp(ExtendedFloatingActionButton extendedFloatingActionButton, v3 v3Var, fp fpVar, boolean z) {
        super(extendedFloatingActionButton, v3Var);
        this.i = extendedFloatingActionButton;
        this.g = fpVar;
        this.h = z;
    }

    @Override // defpackage.z7
    public final AnimatorSet a() {
        x70 x70Var = this.f;
        if (x70Var == null) {
            if (this.e == null) {
                this.e = x70.b(this.a, c());
            }
            x70Var = this.e;
            x70Var.getClass();
        }
        boolean zG = x70Var.g("width");
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        fp fpVar = this.g;
        if (zG) {
            PropertyValuesHolder[] propertyValuesHolderArrE = x70Var.e("width");
            propertyValuesHolderArrE[0].setFloatValues(extendedFloatingActionButton.getWidth(), fpVar.getWidth());
            x70Var.h("width", propertyValuesHolderArrE);
        }
        if (x70Var.g("height")) {
            PropertyValuesHolder[] propertyValuesHolderArrE2 = x70Var.e("height");
            propertyValuesHolderArrE2[0].setFloatValues(extendedFloatingActionButton.getHeight(), fpVar.getHeight());
            x70Var.h("height", propertyValuesHolderArrE2);
        }
        if (x70Var.g("paddingStart")) {
            PropertyValuesHolder[] propertyValuesHolderArrE3 = x70Var.e("paddingStart");
            PropertyValuesHolder propertyValuesHolder = propertyValuesHolderArrE3[0];
            WeakHashMap weakHashMap = xr0.a;
            propertyValuesHolder.setFloatValues(extendedFloatingActionButton.getPaddingStart(), fpVar.f());
            x70Var.h("paddingStart", propertyValuesHolderArrE3);
        }
        if (x70Var.g("paddingEnd")) {
            PropertyValuesHolder[] propertyValuesHolderArrE4 = x70Var.e("paddingEnd");
            PropertyValuesHolder propertyValuesHolder2 = propertyValuesHolderArrE4[0];
            WeakHashMap weakHashMap2 = xr0.a;
            propertyValuesHolder2.setFloatValues(extendedFloatingActionButton.getPaddingEnd(), fpVar.c());
            x70Var.h("paddingEnd", propertyValuesHolderArrE4);
        }
        if (x70Var.g("labelOpacity")) {
            PropertyValuesHolder[] propertyValuesHolderArrE5 = x70Var.e("labelOpacity");
            boolean z = this.h;
            propertyValuesHolderArrE5[0].setFloatValues(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
            x70Var.h("labelOpacity", propertyValuesHolderArrE5);
        }
        return b(x70Var);
    }

    @Override // defpackage.z7
    public final int c() {
        return this.h ? R$animator.mtrl_extended_fab_change_size_expand_motion_spec : R$animator.mtrl_extended_fab_change_size_collapse_motion_spec;
    }

    @Override // defpackage.z7
    public final void e() {
        this.d.b = null;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        extendedFloatingActionButton.F = false;
        extendedFloatingActionButton.setHorizontallyScrolling(false);
        ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        fp fpVar = this.g;
        layoutParams.width = fpVar.g().width;
        layoutParams.height = fpVar.g().height;
    }

    @Override // defpackage.z7
    public final void f(Animator animator) {
        v3 v3Var = this.d;
        Animator animator2 = v3Var.b;
        if (animator2 != null) {
            animator2.cancel();
        }
        v3Var.b = animator;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        extendedFloatingActionButton.E = this.h;
        extendedFloatingActionButton.F = true;
        extendedFloatingActionButton.setHorizontallyScrolling(true);
    }

    @Override // defpackage.z7
    public final void g() {
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        boolean z = this.h;
        extendedFloatingActionButton.E = z;
        ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (!z) {
            extendedFloatingActionButton.I = layoutParams.width;
            extendedFloatingActionButton.J = layoutParams.height;
        }
        fp fpVar = this.g;
        layoutParams.width = fpVar.g().width;
        layoutParams.height = fpVar.g().height;
        int iF = fpVar.f();
        int paddingTop = extendedFloatingActionButton.getPaddingTop();
        int iC = fpVar.c();
        int paddingBottom = extendedFloatingActionButton.getPaddingBottom();
        WeakHashMap weakHashMap = xr0.a;
        extendedFloatingActionButton.setPaddingRelative(iF, paddingTop, iC, paddingBottom);
        extendedFloatingActionButton.requestLayout();
    }

    @Override // defpackage.z7
    public final boolean h() {
        ExtendedFloatingActionButton extendedFloatingActionButton = this.i;
        return this.h == extendedFloatingActionButton.E || extendedFloatingActionButton.getIcon() == null || TextUtils.isEmpty(extendedFloatingActionButton.getText());
    }
}
