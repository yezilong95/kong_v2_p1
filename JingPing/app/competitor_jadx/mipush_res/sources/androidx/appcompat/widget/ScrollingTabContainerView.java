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
import defpackage.ev;
import defpackage.h3;
import defpackage.p50;
import defpackage.tr0;
import defpackage.ur0;

/* JADX INFO: loaded from: classes.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    public h3 E;
    public final LinearLayoutCompat F;
    public AppCompatSpinner G;
    public boolean H;
    public int I;
    public int J;
    public int K;
    public int L;

    static {
        new DecelerateInterpolator();
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        new ev(2, this);
        setHorizontalScrollBarEnabled(false);
        p50 p50VarT = p50.t(context);
        setContentHeight(p50VarT.A());
        this.J = ((Context) p50VarT.F).getResources().getDimensionPixelSize(R$dimen.abc_action_bar_stacked_tab_max_width);
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, R$attr.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        this.F = linearLayoutCompat;
        addView(linearLayoutCompat, new ViewGroup.LayoutParams(-2, -1));
    }

    public final void a() {
        AppCompatSpinner appCompatSpinner = this.G;
        if (appCompatSpinner != null && appCompatSpinner.getParent() == this) {
            removeView(this.G);
            addView(this.F, new ViewGroup.LayoutParams(-2, -1));
            setTabSelected(this.G.getSelectedItemPosition());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        h3 h3Var = this.E;
        if (h3Var != null) {
            post(h3Var);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        p50 p50VarT = p50.t(getContext());
        setContentHeight(p50VarT.A());
        this.J = ((Context) p50VarT.F).getResources().getDimensionPixelSize(R$dimen.abc_action_bar_stacked_tab_max_width);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h3 h3Var = this.E;
        if (h3Var != null) {
            removeCallbacks(h3Var);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        ((ur0) view).getClass();
        throw null;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        LinearLayoutCompat linearLayoutCompat = this.F;
        int childCount = linearLayoutCompat.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.I = -1;
        } else {
            if (childCount > 2) {
                this.I = (int) (View.MeasureSpec.getSize(i) * 0.4f);
            } else {
                this.I = View.MeasureSpec.getSize(i) / 2;
            }
            this.I = Math.min(this.I, this.J);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.K, 1073741824);
        if (z || !this.H) {
            a();
        } else {
            linearLayoutCompat.measure(0, iMakeMeasureSpec);
            if (linearLayoutCompat.getMeasuredWidth() > View.MeasureSpec.getSize(i)) {
                AppCompatSpinner appCompatSpinner = this.G;
                if (appCompatSpinner == null || appCompatSpinner.getParent() != this) {
                    if (this.G == null) {
                        AppCompatSpinner appCompatSpinner2 = new AppCompatSpinner(getContext(), null, R$attr.actionDropDownStyle);
                        appCompatSpinner2.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
                        appCompatSpinner2.setOnItemSelectedListener(this);
                        this.G = appCompatSpinner2;
                    }
                    removeView(linearLayoutCompat);
                    addView(this.G, new ViewGroup.LayoutParams(-2, -1));
                    if (this.G.getAdapter() == null) {
                        this.G.setAdapter((SpinnerAdapter) new tr0(this));
                    }
                    Runnable runnable = this.E;
                    if (runnable != null) {
                        removeCallbacks(runnable);
                        this.E = null;
                    }
                    this.G.setSelection(this.L);
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
        setTabSelected(this.L);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.H = z;
    }

    public void setContentHeight(int i) {
        this.K = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.L = i;
        LinearLayoutCompat linearLayoutCompat = this.F;
        int childCount = linearLayoutCompat.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = linearLayoutCompat.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                View childAt2 = linearLayoutCompat.getChildAt(i);
                Runnable runnable = this.E;
                if (runnable != null) {
                    removeCallbacks(runnable);
                }
                h3 h3Var = new h3(this, 8, childAt2);
                this.E = h3Var;
                post(h3Var);
            }
            i2++;
        }
        AppCompatSpinner appCompatSpinner = this.G;
        if (appCompatSpinner == null || i < 0) {
            return;
        }
        appCompatSpinner.setSelection(i);
    }
}
