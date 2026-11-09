package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ed0;
import defpackage.jt0;
import defpackage.kb;
import defpackage.mp0;
import defpackage.xr0;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeClipBounds extends Transition {
    public static final String[] y = {"android:clipBounds:clip"};

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void F(mp0 mp0Var) {
        View view = mp0Var.b;
        if (view.getVisibility() == 8) {
            return;
        }
        WeakHashMap weakHashMap = xr0.a;
        Rect clipBounds = view.getClipBounds();
        HashMap map = mp0Var.a;
        map.put("android:clipBounds:clip", clipBounds);
        if (clipBounds == null) {
            map.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
        }
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
        if (mp0Var == null || mp0Var2 == null) {
            return null;
        }
        HashMap map = mp0Var.a;
        if (!map.containsKey("android:clipBounds:clip")) {
            return null;
        }
        HashMap map2 = mp0Var2.a;
        if (!map2.containsKey("android:clipBounds:clip")) {
            return null;
        }
        Rect rect = (Rect) map.get("android:clipBounds:clip");
        Rect rect2 = (Rect) map2.get("android:clipBounds:clip");
        boolean z = rect2 == null;
        if (rect == null && rect2 == null) {
            return null;
        }
        if (rect == null) {
            rect = (Rect) map.get("android:clipBounds:bounds");
        } else if (rect2 == null) {
            rect2 = (Rect) map2.get("android:clipBounds:bounds");
        }
        if (rect.equals(rect2)) {
            return null;
        }
        WeakHashMap weakHashMap = xr0.a;
        View view = mp0Var2.b;
        view.setClipBounds(rect);
        Rect rect3 = new Rect();
        ed0 ed0Var = new ed0();
        ed0Var.b = rect3;
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, jt0.c, ed0Var, rect, rect2);
        if (z) {
            objectAnimatorOfObject.addListener(new kb(view, 0));
        }
        return objectAnimatorOfObject;
    }

    @Override // androidx.transition.Transition
    public final String[] o() {
        return y;
    }
}
