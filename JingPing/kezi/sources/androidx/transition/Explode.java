package androidx.transition;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import defpackage.ad;
import defpackage.mp0;
import defpackage.xi;

/* JADX INFO: loaded from: classes.dex */
public class Explode extends Visibility {
    public static final DecelerateInterpolator B = new DecelerateInterpolator();
    public static final AccelerateInterpolator C = new AccelerateInterpolator();
    public final int[] A;

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A = new int[2];
        this.t = new ad();
    }

    @Override // androidx.transition.Visibility
    public final Animator H(ViewGroup viewGroup, View view, mp0 mp0Var, mp0 mp0Var2) {
        if (mp0Var2 == null) {
            return null;
        }
        Rect rect = (Rect) mp0Var2.a.get("android:explode:screenBounds");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        K(viewGroup, rect, this.A);
        return xi.m(view, mp0Var2, rect.left, rect.top, translationX + r0[0], translationY + r0[1], translationX, translationY, B, this);
    }

    @Override // androidx.transition.Visibility
    public final Animator I(ViewGroup viewGroup, View view, mp0 mp0Var) {
        float f;
        float f2;
        if (mp0Var == null) {
            return null;
        }
        Rect rect = (Rect) mp0Var.a.get("android:explode:screenBounds");
        int i = rect.left;
        int i2 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) mp0Var.b.getTag(R$id.transition_position);
        if (iArr != null) {
            f = (r8 - rect.left) + translationX;
            f2 = (r1 - rect.top) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
        } else {
            f = translationX;
            f2 = translationY;
        }
        K(viewGroup, rect, this.A);
        return xi.m(view, mp0Var, i, i2, translationX, translationY, f + r1[0], f2 + r1[1], C, this);
    }

    public final void K(ViewGroup viewGroup, Rect rect, int[] iArr) {
        int[] iArr2 = this.A;
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

    public final void L(mp0 mp0Var) {
        View view = mp0Var.b;
        int[] iArr = this.A;
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        mp0Var.a.put("android:explode:screenBounds", new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2));
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void c(mp0 mp0Var) {
        Visibility.F(mp0Var);
        L(mp0Var);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void f(mp0 mp0Var) {
        Visibility.F(mp0Var);
        L(mp0Var);
    }
}
