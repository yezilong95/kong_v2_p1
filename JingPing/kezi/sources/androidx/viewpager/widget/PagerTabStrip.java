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
import defpackage.bh;
import defpackage.gb0;

/* JADX INFO: loaded from: classes.dex */
public class PagerTabStrip extends PagerTitleStrip {
    public boolean A;
    public final int B;
    public boolean C;
    public float D;
    public float E;
    public final int F;
    public int q;
    public final int r;
    public final int s;
    public final int t;
    public final int u;
    public final int v;
    public final Paint w;
    public final Rect x;
    public int y;
    public boolean z;

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public final void c(float f, int i, boolean z) {
        int height = getHeight();
        TextView textView = this.d;
        int left = textView.getLeft();
        int i2 = this.v;
        int right = textView.getRight() + i2;
        int i3 = height - this.r;
        Rect rect = this.x;
        rect.set(left - i2, i3, right, height);
        super.c(f, i, z);
        this.y = (int) (Math.abs(f - 0.5f) * 2.0f * 255.0f);
        rect.union(textView.getLeft() - i2, i3, textView.getRight() + i2, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.z;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.u);
    }

    public int getTabIndicatorColor() {
        return this.q;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        TextView textView = this.d;
        int left = textView.getLeft();
        int i = this.v;
        int i2 = left - i;
        int right = textView.getRight() + i;
        int i3 = height - this.r;
        Paint paint = this.w;
        paint.setColor((this.y << 24) | (this.q & 16777215));
        float f = height;
        canvas.drawRect(i2, i3, right, f, paint);
        if (this.z) {
            paint.setColor((this.q & 16777215) | (-16777216));
            canvas.drawRect(getPaddingLeft(), height - this.B, getWidth() - getPaddingRight(), f, paint);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.C) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.D = x;
            this.E = y;
            this.C = false;
            return true;
        }
        if (action == 1) {
            int left = this.d.getLeft();
            int i = this.v;
            if (x < left - i) {
                ViewPager viewPager = this.b;
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                return true;
            }
            if (x > r5.getRight() + i) {
                ViewPager viewPager2 = this.b;
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        } else if (action == 2) {
            float fAbs = Math.abs(x - this.D);
            float f = this.F;
            if (fAbs > f || Math.abs(y - this.E) > f) {
                this.C = true;
                return true;
            }
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        if (this.A) {
            return;
        }
        this.z = (i & (-16777216)) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.A) {
            return;
        }
        this.z = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.A) {
            return;
        }
        this.z = i == 0;
    }

    public void setDrawFullUnderline(boolean z) {
        this.z = z;
        this.A = true;
        invalidate();
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        int i5 = this.s;
        if (i4 < i5) {
            i4 = i5;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setTabIndicatorColor(int i) {
        this.q = i;
        this.w.setColor(i);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i) {
        setTabIndicatorColor(bh.a(getContext(), i));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i) {
        int i2 = this.t;
        if (i < i2) {
            i = i2;
        }
        super.setTextSpacing(i);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.w = paint;
        this.x = new Rect();
        this.y = 255;
        this.z = false;
        this.A = false;
        int i = this.n;
        this.q = i;
        paint.setColor(i);
        float f = context.getResources().getDisplayMetrics().density;
        this.r = (int) ((3.0f * f) + 0.5f);
        this.s = (int) ((6.0f * f) + 0.5f);
        this.t = (int) (64.0f * f);
        this.v = (int) ((16.0f * f) + 0.5f);
        this.B = (int) ((1.0f * f) + 0.5f);
        this.u = (int) ((f * 32.0f) + 0.5f);
        this.F = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.c.setFocusable(true);
        this.c.setOnClickListener(new gb0(this, 0));
        this.e.setFocusable(true);
        this.e.setOnClickListener(new gb0(this, 1));
        if (getBackground() == null) {
            this.z = true;
        }
    }
}
