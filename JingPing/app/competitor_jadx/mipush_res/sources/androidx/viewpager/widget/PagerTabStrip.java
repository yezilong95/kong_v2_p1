package androidx.viewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;
import defpackage.ak0;
import defpackage.qi;

/* JADX INFO: loaded from: classes.dex */
public class PagerTabStrip extends PagerTitleStrip {
    public int T;
    public final int U;
    public final int V;
    public final int W;
    public final int a0;
    public final int b0;
    public final Paint c0;
    public final Rect d0;
    public int e0;
    public boolean f0;
    public boolean g0;
    public final int h0;
    public boolean i0;
    public float j0;
    public float k0;
    public final int l0;

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public final void c(float f, int i, boolean z) {
        int height = getHeight();
        TextView textView = this.G;
        int left = textView.getLeft();
        int i2 = this.b0;
        int right = textView.getRight() + i2;
        int i3 = height - this.U;
        Rect rect = this.d0;
        rect.set(left - i2, i3, right, height);
        super.c(f, i, z);
        this.e0 = (int) (Math.abs(f - 0.5f) * 2.0f * 255.0f);
        rect.union(textView.getLeft() - i2, i3, textView.getRight() + i2, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.f0;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.a0);
    }

    public int getTabIndicatorColor() {
        return this.T;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        TextView textView = this.G;
        int left = textView.getLeft();
        int i = this.b0;
        int i2 = left - i;
        int right = textView.getRight() + i;
        int i3 = height - this.U;
        Paint paint = this.c0;
        paint.setColor((this.e0 << 24) | (this.T & 16777215));
        float f = height;
        canvas.drawRect(i2, i3, right, f, paint);
        if (this.f0) {
            paint.setColor((this.T & 16777215) | (-16777216));
            canvas.drawRect(getPaddingLeft(), height - this.h0, getWidth() - getPaddingRight(), f, paint);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.i0) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.j0 = x;
            this.k0 = y;
            this.i0 = false;
            return true;
        }
        if (action == 1) {
            int left = this.G.getLeft();
            int i = this.b0;
            if (x < left - i) {
                ViewPager viewPager = this.E;
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                return true;
            }
            if (x > r5.getRight() + i) {
                ViewPager viewPager2 = this.E;
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        } else if (action == 2) {
            float fAbs = Math.abs(x - this.j0);
            float f = this.l0;
            if (fAbs > f || Math.abs(y - this.k0) > f) {
                this.i0 = true;
                return true;
            }
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        if (this.g0) {
            return;
        }
        this.f0 = (i & (-16777216)) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.g0) {
            return;
        }
        this.f0 = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.g0) {
            return;
        }
        this.f0 = i == 0;
    }

    public void setDrawFullUnderline(boolean z) {
        this.f0 = z;
        this.g0 = true;
        invalidate();
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        int i5 = this.V;
        if (i4 < i5) {
            i4 = i5;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setTabIndicatorColor(int i) {
        this.T = i;
        this.c0.setColor(i);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i) {
        setTabIndicatorColor(qi.a(getContext(), i));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i) {
        int i2 = this.W;
        if (i < i2) {
            i = i2;
        }
        super.setTextSpacing(i);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.c0 = paint;
        this.d0 = new Rect();
        this.e0 = 255;
        this.f0 = false;
        this.g0 = false;
        int i = this.Q;
        this.T = i;
        paint.setColor(i);
        float f = context.getResources().getDisplayMetrics().density;
        this.U = (int) ((3.0f * f) + 0.5f);
        this.V = (int) ((6.0f * f) + 0.5f);
        this.W = (int) (64.0f * f);
        this.b0 = (int) ((16.0f * f) + 0.5f);
        this.h0 = (int) ((1.0f * f) + 0.5f);
        this.a0 = (int) ((f * 32.0f) + 0.5f);
        this.l0 = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.F.setFocusable(true);
        this.F.setOnClickListener(new ak0(this, 0));
        this.H.setFocusable(true);
        this.H.setOnClickListener(new ak0(this, 1));
        if (getBackground() == null) {
            this.f0 = true;
        }
    }
}
