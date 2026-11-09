package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a21;
import defpackage.z11;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeScroll extends Transition {
    public static final String[] f0 = {"android:changeScroll:x", "android:changeScroll:y"};

    public ChangeScroll(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void I(a21 a21Var) {
        HashMap map = a21Var.a;
        View view = a21Var.b;
        map.put("android:changeScroll:x", Integer.valueOf(view.getScrollX()));
        map.put("android:changeScroll:y", Integer.valueOf(view.getScrollY()));
    }

    @Override // androidx.transition.Transition
    public final void d(a21 a21Var) {
        I(a21Var);
    }

    @Override // androidx.transition.Transition
    public final void g(a21 a21Var) {
        I(a21Var);
    }

    @Override // androidx.transition.Transition
    public final Animator k(ViewGroup viewGroup, a21 a21Var, a21 a21Var2) {
        ObjectAnimator objectAnimatorOfInt;
        ObjectAnimator objectAnimatorOfInt2 = null;
        if (a21Var == null || a21Var2 == null) {
            return null;
        }
        HashMap map = a21Var.a;
        int iIntValue = ((Integer) map.get("android:changeScroll:x")).intValue();
        HashMap map2 = a21Var2.a;
        int iIntValue2 = ((Integer) map2.get("android:changeScroll:x")).intValue();
        int iIntValue3 = ((Integer) map.get("android:changeScroll:y")).intValue();
        int iIntValue4 = ((Integer) map2.get("android:changeScroll:y")).intValue();
        View view = a21Var2.b;
        if (iIntValue != iIntValue2) {
            view.setScrollX(iIntValue);
            objectAnimatorOfInt = ObjectAnimator.ofInt(view, "scrollX", iIntValue, iIntValue2);
        } else {
            objectAnimatorOfInt = null;
        }
        if (iIntValue3 != iIntValue4) {
            view.setScrollY(iIntValue3);
            objectAnimatorOfInt2 = ObjectAnimator.ofInt(view, "scrollY", iIntValue3, iIntValue4);
        }
        boolean z = z11.a;
        if (objectAnimatorOfInt == null) {
            return objectAnimatorOfInt2;
        }
        if (objectAnimatorOfInt2 == null) {
            return objectAnimatorOfInt;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfInt, objectAnimatorOfInt2);
        return animatorSet;
    }

    @Override // androidx.transition.Transition
    public final String[] q() {
        return f0;
    }
}
