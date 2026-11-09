package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class z7 {
    public final Context a;
    public final ExtendedFloatingActionButton b;
    public final ArrayList c = new ArrayList();
    public final v3 d;
    public x70 e;
    public x70 f;

    public z7(ExtendedFloatingActionButton extendedFloatingActionButton, v3 v3Var) {
        this.b = extendedFloatingActionButton;
        this.a = extendedFloatingActionButton.getContext();
        this.d = v3Var;
    }

    public AnimatorSet a() {
        x70 x70Var = this.f;
        if (x70Var == null) {
            if (this.e == null) {
                this.e = x70.b(this.a, c());
            }
            x70Var = this.e;
            x70Var.getClass();
        }
        return b(x70Var);
    }

    public final AnimatorSet b(x70 x70Var) {
        ArrayList arrayList = new ArrayList();
        boolean zG = x70Var.g("opacity");
        ExtendedFloatingActionButton extendedFloatingActionButton = this.b;
        if (zG) {
            arrayList.add(x70Var.d("opacity", extendedFloatingActionButton, View.ALPHA));
        }
        if (x70Var.g("scale")) {
            arrayList.add(x70Var.d("scale", extendedFloatingActionButton, View.SCALE_Y));
            arrayList.add(x70Var.d("scale", extendedFloatingActionButton, View.SCALE_X));
        }
        if (x70Var.g("width")) {
            arrayList.add(x70Var.d("width", extendedFloatingActionButton, ExtendedFloatingActionButton.L));
        }
        if (x70Var.g("height")) {
            arrayList.add(x70Var.d("height", extendedFloatingActionButton, ExtendedFloatingActionButton.M));
        }
        if (x70Var.g("paddingStart")) {
            arrayList.add(x70Var.d("paddingStart", extendedFloatingActionButton, ExtendedFloatingActionButton.N));
        }
        if (x70Var.g("paddingEnd")) {
            arrayList.add(x70Var.d("paddingEnd", extendedFloatingActionButton, ExtendedFloatingActionButton.O));
        }
        if (x70Var.g("labelOpacity")) {
            arrayList.add(x70Var.d("labelOpacity", extendedFloatingActionButton, new y7(this)));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        xi.O(animatorSet, arrayList);
        return animatorSet;
    }

    public abstract int c();

    public void d() {
        this.d.b = null;
    }

    public abstract void e();

    public abstract void f(Animator animator);

    public abstract void g();

    public abstract boolean h();
}
