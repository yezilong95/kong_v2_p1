package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class v9 {
    public final Context a;
    public final ExtendedFloatingActionButton b;
    public final ArrayList c = new ArrayList();
    public final p50 d;
    public rf0 e;
    public rf0 f;

    public v9(ExtendedFloatingActionButton extendedFloatingActionButton, p50 p50Var) {
        this.b = extendedFloatingActionButton;
        this.a = extendedFloatingActionButton.getContext();
        this.d = p50Var;
    }

    public AnimatorSet a() {
        rf0 rf0Var = this.f;
        if (rf0Var == null) {
            if (this.e == null) {
                this.e = rf0.b(this.a, c());
            }
            rf0Var = this.e;
            rf0Var.getClass();
        }
        return b(rf0Var);
    }

    public final AnimatorSet b(rf0 rf0Var) {
        ArrayList arrayList = new ArrayList();
        boolean zG = rf0Var.g("opacity");
        ExtendedFloatingActionButton extendedFloatingActionButton = this.b;
        if (zG) {
            arrayList.add(rf0Var.d("opacity", extendedFloatingActionButton, View.ALPHA));
        }
        if (rf0Var.g("scale")) {
            arrayList.add(rf0Var.d("scale", extendedFloatingActionButton, View.SCALE_Y));
            arrayList.add(rf0Var.d("scale", extendedFloatingActionButton, View.SCALE_X));
        }
        if (rf0Var.g("width")) {
            arrayList.add(rf0Var.d("width", extendedFloatingActionButton, ExtendedFloatingActionButton.r0));
        }
        if (rf0Var.g("height")) {
            arrayList.add(rf0Var.d("height", extendedFloatingActionButton, ExtendedFloatingActionButton.s0));
        }
        if (rf0Var.g("paddingStart")) {
            arrayList.add(rf0Var.d("paddingStart", extendedFloatingActionButton, ExtendedFloatingActionButton.t0));
        }
        if (rf0Var.g("paddingEnd")) {
            arrayList.add(rf0Var.d("paddingEnd", extendedFloatingActionButton, ExtendedFloatingActionButton.u0));
        }
        if (rf0Var.g("labelOpacity")) {
            arrayList.add(rf0Var.d("labelOpacity", extendedFloatingActionButton, new u9(this)));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        j31.I(animatorSet, arrayList);
        return animatorSet;
    }

    public abstract int c();

    public void d() {
        this.d.F = null;
    }

    public abstract void e();

    public abstract void f(Animator animator);

    public abstract void g();

    public abstract boolean h();
}
