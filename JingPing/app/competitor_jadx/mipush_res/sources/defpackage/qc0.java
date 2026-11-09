package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public final class qc0 extends lc0 {
    public final float g;
    public final float h;

    public qc0(View view) {
        super(view);
        Resources resources = view.getResources();
        this.g = resources.getDimension(R$dimen.m3_back_progress_bottom_container_max_scale_x_distance);
        this.h = resources.getDimension(R$dimen.m3_back_progress_bottom_container_max_scale_y_distance);
    }

    public final AnimatorSet b() {
        AnimatorSet animatorSet = new AnimatorSet();
        View view = this.b;
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_Y, 1.0f));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                animatorSet.playTogether(ObjectAnimator.ofFloat(viewGroup.getChildAt(i), (Property<View, Float>) View.SCALE_Y, 1.0f));
            }
        }
        animatorSet.setInterpolator(new sv(1));
        return animatorSet;
    }

    public final void c(float f) {
        float interpolation = this.a.getInterpolation(f);
        View view = this.b;
        float width = view.getWidth();
        float height = view.getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float f2 = this.g / width;
        float f3 = this.h / height;
        float fA = 1.0f - y4.a(0.0f, f2, interpolation);
        float fA2 = 1.0f - y4.a(0.0f, f3, interpolation);
        view.setScaleX(fA);
        view.setPivotY(height);
        view.setScaleY(fA2);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                childAt.setPivotY(-childAt.getTop());
                childAt.setScaleY(fA2 != 0.0f ? fA / fA2 : 1.0f);
            }
        }
    }
}
