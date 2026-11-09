package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.SpinnerAdapter;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.widget.LinearLayoutCompat;
import defpackage.bh0;
import defpackage.ch0;
import defpackage.g1;
import defpackage.m1;
import defpackage.pp;

/* JADX INFO: loaded from: classes.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    public m1 b;
    public final LinearLayoutCompat c;
    public AppCompatSpinner d;
    public boolean e;
    public int f;
    public int g;
    public int h;
    public int i;

    static {
        new DecelerateInterpolator();
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        new pp(1, this);
        setHorizontalScrollBarEnabled(false);
        g1 g1VarB = g1.b(context);
        setContentHeight(g1VarB.d());
        this.g = g1VarB.a.getResources().getDimensionPixelSize(R$dimen.abc_action_bar_stacked_tab_max_width);
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, R$attr.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        this.c = linearLayoutCompat;
        addView(linearLayoutCompat, new ViewGroup.LayoutParams(-2, -1));
    }

    public final void a() {
        AppCompatSpinner appCompatSpinner = this.d;
        if (appCompatSpinner != null && appCompatSpinner.getParent() == this) {
            removeView(this.d);
            addView(this.c, new ViewGroup.LayoutParams(-2, -1));
            setTabSelected(this.d.getSelectedItemPosition());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        m1 m1Var = this.b;
        if (m1Var != null) {
            post(m1Var);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        g1 g1VarB = g1.b(getContext());
        setContentHeight(g1VarB.d());
        this.g = g1VarB.a.getResources().getDimensionPixelSize(R$dimen.abc_action_bar_stacked_tab_max_width);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m1 m1Var = this.b;
        if (m1Var != null) {
            removeCallbacks(m1Var);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        ((ch0) view).getClass();
        throw null;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        LinearLayoutCompat linearLayoutCompat = this.c;
        int childCount = linearLayoutCompat.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f = -1;
        } else {
            if (childCount > 2) {
                this.f = (int) (View.MeasureSpec.getSize(i) * 0.4f);
            } else {
                this.f = View.MeasureSpec.getSize(i) / 2;
            }
            this.f = Math.min(this.f, this.g);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.h, 1073741824);
        if (z || !this.e) {
            a();
        } else {
            linearLayoutCompat.measure(0, iMakeMeasureSpec);
            if (linearLayoutCompat.getMeasuredWidth() > View.MeasureSpec.getSize(i)) {
                AppCompatSpinner appCompatSpinner = this.d;
                if (appCompatSpinner == null || appCompatSpinner.getParent() != this) {
                    if (this.d == null) {
                        AppCompatSpinner appCompatSpinner2 = new AppCompatSpinner(getContext(), null, R$attr.actionDropDownStyle);
                        appCompatSpinner2.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
                        appCompatSpinner2.setOnItemSelectedListener(this);
                        this.d = appCompatSpinner2;
                    }
                    removeView(linearLayoutCompat);
                    addView(this.d, new ViewGroup.LayoutParams(-2, -1));
                    if (this.d.getAdapter() == null) {
                        this.d.setAdapter((SpinnerAdapter) new bh0(this));
                    }
                    Runnable runnable = this.b;
                    if (runnable != null) {
                        removeCallbacks(runnable);
                        this.b = null;
                    }
                    this.d.setSelection(this.i);
                }
            } else {
                a();
            }
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, iMakeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.i);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.e = z;
    }

    public void setContentHeight(int i) {
        this.h = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.i = i;
        LinearLayoutCompat linearLayoutCompat = this.c;
        int childCount = linearLayoutCompat.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = linearLayoutCompat.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                View childAt2 = linearLayoutCompat.getChildAt(i);
                Runnable runnable = this.b;
                if (runnable != null) {
                    removeCallbacks(runnable);
                }
                m1 m1Var = new m1(this, 8, childAt2);
                this.b = m1Var;
                post(m1Var);
            }
            i2++;
        }
        AppCompatSpinner appCompatSpinner = this.d;
        if (appCompatSpinner == null || i < 0) {
            return;
        }
        appCompatSpinner.setSelection(i);
    }
}
