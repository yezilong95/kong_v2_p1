package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a21;
import defpackage.dd;
import defpackage.p61;
import defpackage.zm0;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeClipBounds extends Transition {
    public static final String[] f0 = {"android:clipBounds:clip"};
    public static final Rect g0 = new Rect();

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void I(a21 a21Var, boolean z) {
        View view = a21Var.b;
        if (view.getVisibility() == 8) {
            return;
        }
        Rect clipBounds = z ? (Rect) view.getTag(R$id.transition_clip) : null;
        if (clipBounds == null) {
            clipBounds = view.getClipBounds();
        }
        Rect rect = clipBounds != g0 ? clipBounds : null;
        HashMap map = a21Var.a;
        map.put("android:clipBounds:clip", rect);
        if (rect == null) {
            map.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
        }
    }

    @Override // androidx.transition.Transition
    public final void d(a21 a21Var) {
        I(a21Var, false);
    }

    @Override // androidx.transition.Transition
    public final void g(a21 a21Var) {
        I(a21Var, true);
    }

    @Override // androidx.transition.Transition
    public final Animator k(ViewGroup viewGroup, a21 a21Var, a21 a21Var2) {
        if (a21Var == null || a21Var2 == null) {
            return null;
        }
        HashMap map = a21Var.a;
        if (!map.containsKey("android:clipBounds:clip")) {
            return null;
        }
        HashMap map2 = a21Var2.a;
        if (!map2.containsKey("android:clipBounds:clip")) {
            return null;
        }
        Rect rect = (Rect) map.get("android:clipBounds:clip");
        Rect rect2 = (Rect) map2.get("android:clipBounds:clip");
        if (rect == null && rect2 == null) {
            return null;
        }
        Rect rect3 = rect == null ? (Rect) map.get("android:clipBounds:bounds") : rect;
        Rect rect4 = rect2 == null ? (Rect) map2.get("android:clipBounds:bounds") : rect2;
        if (rect3.equals(rect4)) {
            return null;
        }
        View view = a21Var2.b;
        view.setClipBounds(rect);
        Rect rect5 = new Rect();
        zm0 zm0Var = new zm0();
        zm0Var.b = rect5;
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, p61.c, zm0Var, rect3, rect4);
        dd ddVar = new dd(rect, rect2, view);
        objectAnimatorOfObject.addListener(ddVar);
        a(ddVar);
        return objectAnimatorOfObject;
    }

    @Override // androidx.transition.Transition
    public final String[] q() {
        return f0;
    }
}
