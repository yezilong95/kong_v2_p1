package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.search.g;
import defpackage.ed0;
import defpackage.jp;
import defpackage.kf0;
import defpackage.kp;
import defpackage.mp;
import defpackage.nv0;
import defpackage.ol;
import defpackage.pp;
import defpackage.r50;
import defpackage.rp0;
import defpackage.t3;
import defpackage.tl;
import defpackage.vk;
import defpackage.wc;
import defpackage.xr0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public final SearchView a;
    public final View b;
    public final ClippableRoundedCornerLayout c;
    public final FrameLayout d;
    public final FrameLayout e;
    public final MaterialToolbar f;
    public final Toolbar g;
    public final TextView h;
    public final EditText i;
    public final ImageButton j;
    public final View k;
    public final TouchObserverFrameLayout l;
    public final r50 m;
    public AnimatorSet n;
    public SearchBar o;

    public g(SearchView searchView) {
        this.a = searchView;
        this.b = searchView.b;
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = searchView.c;
        this.c = clippableRoundedCornerLayout;
        this.d = searchView.f;
        this.e = searchView.g;
        this.f = searchView.h;
        this.g = searchView.i;
        this.h = searchView.j;
        this.i = searchView.k;
        this.j = searchView.l;
        this.k = searchView.m;
        this.l = searchView.n;
        this.m = new r50(clippableRoundedCornerLayout);
    }

    public static void a(g gVar, float f) {
        ActionMenuView actionMenuViewV;
        gVar.j.setAlpha(f);
        gVar.k.setAlpha(f);
        gVar.l.setAlpha(f);
        if (!gVar.a.x || (actionMenuViewV = rp0.v(gVar.f)) == null) {
            return;
        }
        actionMenuViewV.setAlpha(f);
    }

    public final void b(AnimatorSet animatorSet) {
        ImageButton imageButtonB = rp0.B(this.f);
        if (imageButtonB == null) {
            return;
        }
        Drawable drawableQ = nv0.Q(imageButtonB.getDrawable());
        if (!this.a.w) {
            if (drawableQ instanceof ol) {
                ol olVar = (ol) drawableQ;
                if (olVar.i != 1.0f) {
                    olVar.i = 1.0f;
                    olVar.invalidateSelf();
                }
            }
            if (drawableQ instanceof jp) {
                ((jp) drawableQ).a(1.0f);
                return;
            }
            return;
        }
        if (drawableQ instanceof ol) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.addUpdateListener(new tl(4, (ol) drawableQ));
            animatorSet.playTogether(valueAnimatorOfFloat);
        }
        if (drawableQ instanceof jp) {
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat2.addUpdateListener(new tl(5, (jp) drawableQ));
            animatorSet.playTogether(valueAnimatorOfFloat2);
        }
    }

    public final AnimatorSet c(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        MaterialToolbar materialToolbar = this.f;
        ImageButton imageButtonB = rp0.B(materialToolbar);
        if (imageButtonB != null) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f(imageButtonB), 0.0f);
            valueAnimatorOfFloat.addUpdateListener(new wc(new vk(4), new View[]{imageButtonB}));
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(g(), 0.0f);
            valueAnimatorOfFloat2.addUpdateListener(wc.a(imageButtonB));
            animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        }
        ActionMenuView actionMenuViewV = rp0.v(materialToolbar);
        if (actionMenuViewV != null) {
            ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(e(actionMenuViewV), 0.0f);
            valueAnimatorOfFloat3.addUpdateListener(new wc(new vk(4), new View[]{actionMenuViewV}));
            ValueAnimator valueAnimatorOfFloat4 = ValueAnimator.ofFloat(g(), 0.0f);
            valueAnimatorOfFloat4.addUpdateListener(wc.a(actionMenuViewV));
            animatorSet.playTogether(valueAnimatorOfFloat3, valueAnimatorOfFloat4);
        }
        animatorSet.setDuration(z ? 300L : 250L);
        animatorSet.setInterpolator(kf0.a(z, t3.b));
        return animatorSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AnimatorSet d(boolean z) {
        float f;
        char c;
        AnimatorSet animatorSet = new AnimatorSet();
        if (this.n == null) {
            AnimatorSet animatorSet2 = new AnimatorSet();
            b(animatorSet2);
            animatorSet2.setDuration(z ? 300L : 250L);
            animatorSet2.setInterpolator(kf0.a(z, t3.b));
            animatorSet.playTogether(animatorSet2, c(z));
        }
        Interpolator interpolator = z ? t3.a : t3.b;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(z ? 300L : 250L);
        valueAnimatorOfFloat.setInterpolator(kf0.a(z, interpolator));
        valueAnimatorOfFloat.addUpdateListener(new wc(new vk(7), new View[]{this.b}));
        r50 r50Var = this.m;
        Rect rect = r50Var.j;
        Rect rectH = r50Var.k;
        SearchView searchView = this.a;
        if (rect != null) {
            c = 0;
            f = 0.0f;
        } else {
            f = 0.0f;
            c = 0;
            rect = new Rect(searchView.getLeft(), searchView.getTop(), searchView.getRight(), searchView.getBottom());
        }
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.c;
        if (rectH == null) {
            rectH = rp0.h(clippableRoundedCornerLayout, this.o);
        }
        final Rect rect2 = new Rect(rectH);
        final float cornerSize = this.o.getCornerSize();
        final float fMax = Math.max(clippableRoundedCornerLayout.getCornerRadius(), r50Var.c());
        ValueAnimator valueAnimatorOfObject = ValueAnimator.ofObject(new ed0(rect2), rectH, rect);
        valueAnimatorOfObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: rh0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                g gVar = this.a;
                gVar.getClass();
                float fA = t3.a(cornerSize, fMax, valueAnimator.getAnimatedFraction());
                ClippableRoundedCornerLayout clippableRoundedCornerLayout2 = gVar.c;
                clippableRoundedCornerLayout2.getClass();
                Rect rect3 = rect2;
                clippableRoundedCornerLayout2.a(rect3.left, rect3.top, rect3.right, rect3.bottom, fA);
            }
        });
        valueAnimatorOfObject.setDuration(z ? 300L : 250L);
        mp mpVar = t3.b;
        valueAnimatorOfObject.setInterpolator(kf0.a(z, mpVar));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat2.setDuration(z ? 50L : 42L);
        valueAnimatorOfFloat2.setStartDelay(z ? 250L : 0L);
        LinearInterpolator linearInterpolator = t3.a;
        valueAnimatorOfFloat2.setInterpolator(kf0.a(z, linearInterpolator));
        View[] viewArr = new View[1];
        viewArr[c] = this.j;
        valueAnimatorOfFloat2.addUpdateListener(new wc(new vk(7), viewArr));
        AnimatorSet animatorSet3 = new AnimatorSet();
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat3.setDuration(z ? 150L : 83L);
        valueAnimatorOfFloat3.setStartDelay(z ? 75L : 0L);
        valueAnimatorOfFloat3.setInterpolator(kf0.a(z, linearInterpolator));
        View view = this.k;
        TouchObserverFrameLayout touchObserverFrameLayout = this.l;
        View[] viewArr2 = new View[2];
        viewArr2[c] = view;
        viewArr2[1] = touchObserverFrameLayout;
        valueAnimatorOfFloat3.addUpdateListener(new wc(new vk(7), viewArr2));
        float[] fArr = new float[2];
        fArr[c] = (touchObserverFrameLayout.getHeight() * 0.050000012f) / 2.0f;
        fArr[1] = f;
        ValueAnimator valueAnimatorOfFloat4 = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat4.setDuration(z ? 300L : 250L);
        valueAnimatorOfFloat4.setInterpolator(kf0.a(z, mpVar));
        valueAnimatorOfFloat4.addUpdateListener(wc.a(view));
        ValueAnimator valueAnimatorOfFloat5 = ValueAnimator.ofFloat(0.95f, 1.0f);
        valueAnimatorOfFloat5.setDuration(z ? 300L : 250L);
        valueAnimatorOfFloat5.setInterpolator(kf0.a(z, mpVar));
        View[] viewArr3 = new View[1];
        viewArr3[c] = touchObserverFrameLayout;
        valueAnimatorOfFloat5.addUpdateListener(new wc(new vk(6), viewArr3));
        Animator[] animatorArr = new Animator[3];
        animatorArr[c] = valueAnimatorOfFloat3;
        animatorArr[1] = valueAnimatorOfFloat4;
        animatorArr[2] = valueAnimatorOfFloat5;
        animatorSet3.playTogether(animatorArr);
        boolean z2 = c;
        Animator animatorI = i(z, z2, this.d);
        Toolbar toolbar = this.g;
        Animator animatorI2 = i(z, z2, toolbar);
        ValueAnimator valueAnimatorOfFloat6 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat6.setDuration(z ? 300L : 250L);
        valueAnimatorOfFloat6.setInterpolator(kf0.a(z, mpVar));
        if (searchView.x) {
            valueAnimatorOfFloat6.addUpdateListener(new kp(rp0.v(toolbar), rp0.v(this.f)));
        }
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfObject, valueAnimatorOfFloat2, animatorSet3, animatorI, animatorI2, valueAnimatorOfFloat6, i(z, true, this.i), i(z, true, this.h));
        animatorSet.addListener(new pp(this, z));
        return animatorSet;
    }

    public final int e(View view) {
        int marginEnd = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginEnd();
        return rp0.I(this.o) ? this.o.getLeft() - marginEnd : (this.o.getRight() - this.a.getWidth()) + marginEnd;
    }

    public final int f(View view) {
        int marginStart = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginStart();
        SearchBar searchBar = this.o;
        WeakHashMap weakHashMap = xr0.a;
        int paddingStart = searchBar.getPaddingStart();
        return rp0.I(this.o) ? ((this.o.getWidth() - this.o.getRight()) + marginStart) - paddingStart : (this.o.getLeft() - marginStart) + paddingStart;
    }

    public final int g() {
        FrameLayout frameLayout = this.e;
        return ((this.o.getBottom() + this.o.getTop()) / 2) - ((frameLayout.getBottom() + frameLayout.getTop()) / 2);
    }

    public final AnimatorSet h(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.c;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(clippableRoundedCornerLayout.getHeight(), 0.0f);
        valueAnimatorOfFloat.addUpdateListener(wc.a(clippableRoundedCornerLayout));
        animatorSet.playTogether(valueAnimatorOfFloat);
        b(animatorSet);
        animatorSet.setInterpolator(kf0.a(z, t3.b));
        animatorSet.setDuration(z ? 350L : 300L);
        return animatorSet;
    }

    public final AnimatorSet i(boolean z, boolean z2, View view) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(z2 ? f(view) : e(view), 0.0f);
        valueAnimatorOfFloat.addUpdateListener(new wc(new vk(4), new View[]{view}));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(g(), 0.0f);
        valueAnimatorOfFloat2.addUpdateListener(wc.a(view));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        animatorSet.setDuration(z ? 300L : 250L);
        animatorSet.setInterpolator(kf0.a(z, t3.b));
        return animatorSet;
    }

    public final AnimatorSet j() {
        SearchBar searchBar = this.o;
        SearchView searchView = this.a;
        if (searchBar != null) {
            if (searchView.g()) {
                searchView.f();
            }
            AnimatorSet animatorSetD = d(false);
            animatorSetD.addListener(new d(this));
            animatorSetD.start();
            return animatorSetD;
        }
        if (searchView.g()) {
            searchView.f();
        }
        AnimatorSet animatorSetH = h(false);
        animatorSetH.addListener(new f(this));
        animatorSetH.start();
        return animatorSetH;
    }
}
