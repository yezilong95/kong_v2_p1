package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.transition.Transition;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class nn0 extends Transition {
    @Override // androidx.transition.Transition
    public final void c(mp0 mp0Var) {
        View view = mp0Var.b;
        if (view instanceof TextView) {
            mp0Var.a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // androidx.transition.Transition
    public final void f(mp0 mp0Var) {
        View view = mp0Var.b;
        if (view instanceof TextView) {
            mp0Var.a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // androidx.transition.Transition
    public final Animator j(ViewGroup viewGroup, mp0 mp0Var, mp0 mp0Var2) {
        if (mp0Var == null || mp0Var2 == null || !(mp0Var.b instanceof TextView)) {
            return null;
        }
        View view = mp0Var2.b;
        if (!(view instanceof TextView)) {
            return null;
        }
        TextView textView = (TextView) view;
        HashMap map = mp0Var.a;
        HashMap map2 = mp0Var2.a;
        float fFloatValue = map.get("android:textscale:scale") != null ? ((Float) map.get("android:textscale:scale")).floatValue() : 1.0f;
        float fFloatValue2 = map2.get("android:textscale:scale") != null ? ((Float) map2.get("android:textscale:scale")).floatValue() : 1.0f;
        if (fFloatValue == fFloatValue2) {
            return null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, fFloatValue2);
        valueAnimatorOfFloat.addUpdateListener(new d9(6, textView));
        return valueAnimatorOfFloat;
    }
}
