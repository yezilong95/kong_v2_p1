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
import defpackage.ev;
import defpackage.f51;
import defpackage.fa0;
import defpackage.fp0;
import defpackage.gv;
import defpackage.hv;
import defpackage.id0;
import defpackage.no;
import defpackage.qe;
import defpackage.rp;
import defpackage.sv;
import defpackage.t40;
import defpackage.wp;
import defpackage.y4;
import defpackage.z91;
import defpackage.zm0;
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
    public final id0 m;
    public AnimatorSet n;
    public SearchBar o;

    public g(SearchView searchView) {
        this.a = searchView;
        this.b = searchView.E;
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = searchView.F;
        this.c = clippableRoundedCornerLayout;
        this.d = searchView.I;
        this.e = searchView.J;
        this.f = searchView.K;
        this.g = searchView.L;
        this.h = searchView.M;
        this.i = searchView.N;
        this.j = searchView.O;
        this.k = searchView.P;
        this.l = searchView.Q;
        this.m = new id0(clippableRoundedCornerLayout);
    }

    public static void a(g gVar, float f) {
        ActionMenuView actionMenuViewU;
        gVar.j.setAlpha(f);
        gVar.k.setAlpha(f);
        gVar.l.setAlpha(f);
        if (!gVar.a.d0 || (actionMenuViewU = t40.U(gVar.f)) == null) {
            return;
        }
        actionMenuViewU.setAlpha(f);
    }

    public final void b(AnimatorSet animatorSet) {
        ImageButton imageButtonH0 = t40.h0(this.f);
        if (imageButtonH0 == null) {
            return;
        }
        Drawable drawableV0 = z91.v0(imageButtonH0.getDrawable());
        if (!this.a.c0) {
            if (drawableV0 instanceof rp) {
                rp rpVar = (rp) drawableV0;
                if (rpVar.i != 1.0f) {
                    rpVar.i = 1.0f;
                    rpVar.invalidateSelf();
                }
            }
            if (drawableV0 instanceof gv) {
                ((gv) drawableV0).a(1.0f);
                return;
            }
            return;
        }
        if (drawableV0 instanceof rp) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.addUpdateListener(new wp(4, (rp) drawableV0));
            animatorSet.playTogether(valueAnimatorOfFloat);
        }
        if (drawableV0 instanceof gv) {
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat2.addUpdateListener(new wp(5, (gv) drawableV0));
            animatorSet.playTogether(valueAnimatorOfFloat2);
        }
    }

    public final AnimatorSet c(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        MaterialToolbar materialToolbar = this.f;
        ImageButton imageButtonH0 = t40.h0(materialToolbar);
        if (imageButtonH0 != null) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f(imageButtonH0), 0.0f);
            valueAnimatorOfFloat.addUpdateListener(new qe(new no(19), new View[]{imageButtonH0}));
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(g(), 0.0f);
            valueAnimatorOfFloat2.addUpdateListener(qe.a(imageButtonH0));
            animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        }
        ActionMenuView actionMenuViewU = t40.U(materialToolbar);
        if (actionMenuViewU != null) {
            ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(e(actionMenuViewU), 0.0f);
            valueAnimatorOfFloat3.addUpdateListener(new qe(new no(19), new View[]{actionMenuViewU}));
            ValueAnimator valueAnimatorOfFloat4 = ValueAnimator.ofFloat(g(), 0.0f);
            valueAnimatorOfFloat4.addUpdateListener(qe.a(actionMenuViewU));
            animatorSet.playTogether(valueAnimatorOfFloat3, valueAnimatorOfFloat4);
        }
        animatorSet.setDuration(z ? 300L : 250L);
        animatorSet.setInterpolator(fp0.a(z, y4.b));
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
            animatorSet2.setInterpolator(fp0.a(z, y4.b));
            animatorSet.playTogether(animatorSet2, c(z));
        }
        Interpolator interpolator = z ? y4.a : y4.b;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(z ? 300L : 250L);
        valueAnimatorOfFloat.setInterpolator(fp0.a(z, interpolator));
        valueAnimatorOfFloat.addUpdateListener(new qe(new no(22), new View[]{this.b}));
        id0 id0Var = this.m;
        Rect rect = id0Var.j;
        Rect rectN = id0Var.k;
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
        if (rectN == null) {
            rectN = fa0.n(clippableRoundedCornerLayout, this.o);
        }
        final Rect rect2 = new Rect(rectN);
        final float cornerSize = this.o.getCornerSize();
        final float fMax = Math.max(clippableRoundedCornerLayout.getCornerRadius(), id0Var.c());
        ValueAnimator valueAnimatorOfObject = ValueAnimator.ofObject(new zm0(rect2), rectN, rect);
        valueAnimatorOfObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: is0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                g gVar = this.a;
                gVar.getClass();
                float fA = y4.a(cornerSize, fMax, valueAnimator.getAnimatedFraction());
                ClippableRoundedCornerLayout clippableRoundedCornerLayout2 = gVar.c;
                clippableRoundedCornerLayout2.getClass();
                Rect rect3 = rect2;
                clippableRoundedCornerLayout2.a(rect3.left, rect3.top, rect3.right, rect3.bottom, fA);
            }
        });
        valueAnimatorOfObject.setDuration(z ? 300L : 250L);
        sv svVar = y4.b;
        valueAnimatorOfObject.setInterpolator(fp0.a(z, svVar));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat2.setDuration(z ? 50L : 42L);
        valueAnimatorOfFloat2.setStartDelay(z ? 250L : 0L);
        LinearInterpolator linearInterpolator = y4.a;
        valueAnimatorOfFloat2.setInterpolator(fp0.a(z, linearInterpolator));
        View[] viewArr = new View[1];
        viewArr[c] = this.j;
        valueAnimatorOfFloat2.addUpdateListener(new qe(new no(22), viewArr));
        AnimatorSet animatorSet3 = new AnimatorSet();
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat3.setDuration(z ? 150L : 83L);
        valueAnimatorOfFloat3.setStartDelay(z ? 75L : 0L);
        valueAnimatorOfFloat3.setInterpolator(fp0.a(z, linearInterpolator));
        View view = this.k;
        TouchObserverFrameLayout touchObserverFrameLayout = this.l;
        View[] viewArr2 = new View[2];
        viewArr2[c] = view;
        viewArr2[1] = touchObserverFrameLayout;
        valueAnimatorOfFloat3.addUpdateListener(new qe(new no(22), viewArr2));
        float[] fArr = new float[2];
        fArr[c] = (touchObserverFrameLayout.getHeight() * 0.050000012f) / 2.0f;
        fArr[1] = f;
        ValueAnimator valueAnimatorOfFloat4 = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat4.setDuration(z ? 300L : 250L);
        valueAnimatorOfFloat4.setInterpolator(fp0.a(z, svVar));
        valueAnimatorOfFloat4.addUpdateListener(qe.a(view));
        ValueAnimator valueAnimatorOfFloat5 = ValueAnimator.ofFloat(0.95f, 1.0f);
        valueAnimatorOfFloat5.setDuration(z ? 300L : 250L);
        valueAnimatorOfFloat5.setInterpolator(fp0.a(z, svVar));
        View[] viewArr3 = new View[1];
        viewArr3[c] = touchObserverFrameLayout;
        valueAnimatorOfFloat5.addUpdateListener(new qe(new no(21), viewArr3));
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
        valueAnimatorOfFloat6.setInterpolator(fp0.a(z, svVar));
        if (searchView.d0) {
            valueAnimatorOfFloat6.addUpdateListener(new hv(t40.U(toolbar), t40.U(this.f)));
        }
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfObject, valueAnimatorOfFloat2, animatorSet3, animatorI, animatorI2, valueAnimatorOfFloat6, i(z, true, this.i), i(z, true, this.h));
        animatorSet.addListener(new ev(this, z));
        return animatorSet;
    }

    public final int e(View view) {
        int marginEnd = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginEnd();
        return fa0.d0(this.o) ? this.o.getLeft() - marginEnd : (this.o.getRight() - this.a.getWidth()) + marginEnd;
    }

    public final int f(View view) {
        int marginStart = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginStart();
        SearchBar searchBar = this.o;
        WeakHashMap weakHashMap = f51.a;
        int paddingStart = searchBar.getPaddingStart();
        return fa0.d0(this.o) ? ((this.o.getWidth() - this.o.getRight()) + marginStart) - paddingStart : (this.o.getLeft() - marginStart) + paddingStart;
    }

    public final int g() {
        FrameLayout frameLayout = this.e;
        return ((this.o.getBottom() + this.o.getTop()) / 2) - ((frameLayout.getBottom() + frameLayout.getTop()) / 2);
    }

    public final AnimatorSet h(boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.c;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(clippableRoundedCornerLayout.getHeight(), 0.0f);
        valueAnimatorOfFloat.addUpdateListener(qe.a(clippableRoundedCornerLayout));
        animatorSet.playTogether(valueAnimatorOfFloat);
        b(animatorSet);
        animatorSet.setInterpolator(fp0.a(z, y4.b));
        animatorSet.setDuration(z ? 350L : 300L);
        return animatorSet;
    }

    public final AnimatorSet i(boolean z, boolean z2, View view) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(z2 ? f(view) : e(view), 0.0f);
        valueAnimatorOfFloat.addUpdateListener(new qe(new no(19), new View[]{view}));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(g(), 0.0f);
        valueAnimatorOfFloat2.addUpdateListener(qe.a(view));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        animatorSet.setDuration(z ? 300L : 250L);
        animatorSet.setInterpolator(fp0.a(z, y4.b));
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
