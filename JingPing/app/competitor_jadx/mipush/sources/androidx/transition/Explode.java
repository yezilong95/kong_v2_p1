package androidx.transition;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import defpackage.a21;
import defpackage.fa0;
import defpackage.ue;

/* JADX INFO: loaded from: classes.dex */
public class Explode extends Visibility {
    public static final DecelerateInterpolator i0 = new DecelerateInterpolator();
    public static final AccelerateInterpolator j0 = new AccelerateInterpolator();
    public final int[] h0;

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h0 = new int[2];
        this.Z = new ue();
    }

    @Override // androidx.transition.Visibility
    public final Animator K(ViewGroup viewGroup, View view, a21 a21Var, a21 a21Var2) {
        if (a21Var2 == null) {
            return null;
        }
        Rect rect = (Rect) a21Var2.a.get("android:explode:screenBounds");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        N(viewGroup, rect, this.h0);
        return fa0.x(view, a21Var2, rect.left, rect.top, translationX + r0[0], translationY + r0[1], translationX, translationY, i0, this);
    }

    @Override // androidx.transition.Visibility
    public final Animator L(ViewGroup viewGroup, View view, a21 a21Var, a21 a21Var2) {
        float f;
        float f2;
        if (a21Var == null) {
            return null;
        }
        Rect rect = (Rect) a21Var.a.get("android:explode:screenBounds");
        int i = rect.left;
        int i2 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) a21Var.b.getTag(R$id.transition_position);
        if (iArr != null) {
            f = (r7 - rect.left) + translationX;
            f2 = (r0 - rect.top) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
        } else {
            f = translationX;
            f2 = translationY;
        }
        N(viewGroup, rect, this.h0);
        return fa0.x(view, a21Var, i, i2, translationX, translationY, f + r0[0], f2 + r0[1], j0, this);
    }

    public final void N(ViewGroup viewGroup, Rect rect, int[] iArr) {
        int[] iArr2 = this.h0;
        viewGroup.getLocationOnScreen(iArr2);
        int i = iArr2[0];
        int i2 = iArr2[1];
        int iRound = Math.round(viewGroup.getTranslationX()) + (viewGroup.getWidth() / 2) + i;
        int iRound2 = Math.round(viewGroup.getTranslationY()) + (viewGroup.getHeight() / 2) + i2;
        float fCenterX = rect.centerX() - iRound;
        float fCenterY = rect.centerY() - iRound2;
        if (fCenterX == 0.0f && fCenterY == 0.0f) {
            fCenterX = ((float) (Math.random() * 2.0d)) - 1.0f;
            fCenterY = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float fSqrt = (float) Math.sqrt((fCenterY * fCenterY) + (fCenterX * fCenterX));
        int i3 = iRound - i;
        int i4 = iRound2 - i2;
        float fMax = Math.max(i3, viewGroup.getWidth() - i3);
        float fMax2 = Math.max(i4, viewGroup.getHeight() - i4);
        float fSqrt2 = (float) Math.sqrt((fMax2 * fMax2) + (fMax * fMax));
        iArr[0] = Math.round((fCenterX / fSqrt) * fSqrt2);
        iArr[1] = Math.round(fSqrt2 * (fCenterY / fSqrt));
    }

    public final void O(a21 a21Var) {
        View view = a21Var.b;
        int[] iArr = this.h0;
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        a21Var.a.put("android:explode:screenBounds", new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2));
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void d(a21 a21Var) {
        Visibility.I(a21Var);
        O(a21Var);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void g(a21 a21Var) {
        Visibility.I(a21Var);
        O(a21Var);
    }
}
