package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.transition.Transition;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class b01 extends Transition {
    @Override // androidx.transition.Transition
    public final void d(a21 a21Var) {
        View view = a21Var.b;
        if (view instanceof TextView) {
            a21Var.a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // androidx.transition.Transition
    public final void g(a21 a21Var) {
        View view = a21Var.b;
        if (view instanceof TextView) {
            a21Var.a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // androidx.transition.Transition
    public final Animator k(ViewGroup viewGroup, a21 a21Var, a21 a21Var2) {
        if (a21Var == null || a21Var2 == null || !(a21Var.b instanceof TextView)) {
            return null;
        }
        View view = a21Var2.b;
        if (!(view instanceof TextView)) {
            return null;
        }
        TextView textView = (TextView) view;
        HashMap map = a21Var.a;
        HashMap map2 = a21Var2.a;
        float fFloatValue = map.get("android:textscale:scale") != null ? ((Float) map.get("android:textscale:scale")).floatValue() : 1.0f;
        float fFloatValue2 = map2.get("android:textscale:scale") != null ? ((Float) map2.get("android:textscale:scale")).floatValue() : 1.0f;
        if (fFloatValue == fFloatValue2) {
            return null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, fFloatValue2);
        valueAnimatorOfFloat.addUpdateListener(new bb(6, textView));
        return valueAnimatorOfFloat;
    }
}
