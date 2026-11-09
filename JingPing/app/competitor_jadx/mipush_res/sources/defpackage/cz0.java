package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.a;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class cz0 extends LinearLayout {
    public static final /* synthetic */ int G = 0;
    public ValueAnimator E;
    public final /* synthetic */ TabLayout F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cz0(TabLayout tabLayout, Context context) {
        super(context);
        this.F = tabLayout;
        setWillNotDraw(false);
    }

    public final void a(int i) {
        TabLayout tabLayout = this.F;
        if (tabLayout.z0 == 0 || (tabLayout.getTabSelectedIndicator().getBounds().left == -1 && tabLayout.getTabSelectedIndicator().getBounds().right == -1)) {
            View childAt = getChildAt(i);
            a aVar = tabLayout.p0;
            Drawable drawable = tabLayout.S;
            aVar.getClass();
            RectF rectFA = a.a(tabLayout, childAt);
            drawable.setBounds((int) rectFA.left, drawable.getBounds().top, (int) rectFA.right, drawable.getBounds().bottom);
            tabLayout.E = i;
        }
    }

    public final void b(int i) {
        TabLayout tabLayout = this.F;
        Rect bounds = tabLayout.S.getBounds();
        tabLayout.S.setBounds(bounds.left, 0, bounds.right, i);
        requestLayout();
    }

    public final void c(View view, View view2, float f) {
        TabLayout tabLayout = this.F;
        if (view == null || view.getWidth() <= 0) {
            Drawable drawable = tabLayout.S;
            drawable.setBounds(-1, drawable.getBounds().top, -1, tabLayout.S.getBounds().bottom);
        } else {
            tabLayout.p0.b(tabLayout, view, view2, f, tabLayout.S);
        }
        WeakHashMap weakHashMap = f51.a;
        postInvalidateOnAnimation();
    }

    public final void d(int i, int i2, boolean z) {
        TabLayout tabLayout = this.F;
        if (tabLayout.E == i) {
            return;
        }
        View childAt = getChildAt(tabLayout.getSelectedTabPosition());
        View childAt2 = getChildAt(i);
        if (childAt2 == null) {
            a(tabLayout.getSelectedTabPosition());
            return;
        }
        tabLayout.E = i;
        hv hvVar = new hv(this, childAt, childAt2);
        if (!z) {
            this.E.removeAllUpdateListeners();
            this.E.addUpdateListener(hvVar);
            return;
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        this.E = valueAnimator;
        valueAnimator.setInterpolator(tabLayout.q0);
        valueAnimator.setDuration(i2);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.addUpdateListener(hvVar);
        valueAnimator.start();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int height;
        TabLayout tabLayout = this.F;
        int iHeight = tabLayout.S.getBounds().height();
        if (iHeight < 0) {
            iHeight = tabLayout.S.getIntrinsicHeight();
        }
        int i = tabLayout.i0;
        if (i == 0) {
            height = getHeight() - iHeight;
            iHeight = getHeight();
        } else if (i != 1) {
            height = 0;
            if (i != 2) {
                iHeight = i != 3 ? 0 : getHeight();
            }
        } else {
            height = (getHeight() - iHeight) / 2;
            iHeight = (getHeight() + iHeight) / 2;
        }
        if (tabLayout.S.getBounds().width() > 0) {
            Rect bounds = tabLayout.S.getBounds();
            tabLayout.S.setBounds(bounds.left, height, bounds.right, iHeight);
            tabLayout.S.draw(canvas);
        }
        super.draw(canvas);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ValueAnimator valueAnimator = this.E;
        TabLayout tabLayout = this.F;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            d(tabLayout.getSelectedTabPosition(), -1, false);
            return;
        }
        if (tabLayout.E == -1) {
            tabLayout.E = tabLayout.getSelectedTabPosition();
        }
        a(tabLayout.E);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            return;
        }
        TabLayout tabLayout = this.F;
        boolean z = true;
        if (tabLayout.g0 == 1 || tabLayout.j0 == 2) {
            int childCount = getChildCount();
            int iMax = 0;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    iMax = Math.max(iMax, childAt.getMeasuredWidth());
                }
            }
            if (iMax <= 0) {
                return;
            }
            if (iMax * childCount <= getMeasuredWidth() - (((int) fa0.C(getContext(), 16)) * 2)) {
                boolean z2 = false;
                for (int i4 = 0; i4 < childCount; i4++) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i4).getLayoutParams();
                    if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                        layoutParams.width = iMax;
                        layoutParams.weight = 0.0f;
                        z2 = true;
                    }
                }
                z = z2;
            } else {
                tabLayout.g0 = 0;
                tabLayout.j(false);
            }
            if (z) {
                super.onMeasure(i, i2);
            }
        }
    }
}
