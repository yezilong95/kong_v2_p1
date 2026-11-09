package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.lp0;
import defpackage.mp0;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeScroll extends Transition {
    public static final String[] y = {"android:changeScroll:x", "android:changeScroll:y"};

    public ChangeScroll(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void F(mp0 mp0Var) {
        HashMap map = mp0Var.a;
        View view = mp0Var.b;
        map.put("android:changeScroll:x", Integer.valueOf(view.getScrollX()));
        map.put("android:changeScroll:y", Integer.valueOf(view.getScrollY()));
    }

    @Override // androidx.transition.Transition
    public final void c(mp0 mp0Var) {
        F(mp0Var);
    }

    @Override // androidx.transition.Transition
    public final void f(mp0 mp0Var) {
        F(mp0Var);
    }

    @Override // androidx.transition.Transition
    public final Animator j(ViewGroup viewGroup, mp0 mp0Var, mp0 mp0Var2) {
        ObjectAnimator objectAnimatorOfInt;
        ObjectAnimator objectAnimatorOfInt2 = null;
        if (mp0Var == null || mp0Var2 == null) {
            return null;
        }
        HashMap map = mp0Var.a;
        int iIntValue = ((Integer) map.get("android:changeScroll:x")).intValue();
        HashMap map2 = mp0Var2.a;
        int iIntValue2 = ((Integer) map2.get("android:changeScroll:x")).intValue();
        int iIntValue3 = ((Integer) map.get("android:changeScroll:y")).intValue();
        int iIntValue4 = ((Integer) map2.get("android:changeScroll:y")).intValue();
        View view = mp0Var2.b;
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
        boolean z = lp0.a;
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
    public final String[] o() {
        return y;
    }
}
