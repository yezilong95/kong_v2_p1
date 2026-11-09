package com.google.android.material.imageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.ki0;
import defpackage.li0;
import defpackage.mi0;
import defpackage.rp0;
import defpackage.u50;
import defpackage.vi0;
import defpackage.xi;
import defpackage.y50;
import defpackage.zi0;

/* JADX INFO: loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements vi0 {
    public static final int w = R$style.Widget_MaterialComponents_ShapeableImageView;
    public final mi0 e;
    public final RectF f;
    public final RectF g;
    public final Paint h;
    public final Paint i;
    public final Path j;
    public ColorStateList k;
    public u50 l;
    public ki0 m;
    public float n;
    public final Path o;
    public final int p;
    public final int q;
    public final int r;
    public final int s;
    public final int t;
    public final int u;
    public boolean v;

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public final boolean c() {
        return getLayoutDirection() == 1;
    }

    public final void d(int i, int i2) {
        RectF rectF = this.f;
        rectF.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
        ki0 ki0Var = this.m;
        Path path = this.j;
        this.e.a(ki0Var, 1.0f, rectF, null, path);
        Path path2 = this.o;
        path2.rewind();
        path2.addPath(path);
        RectF rectF2 = this.g;
        rectF2.set(0.0f, 0.0f, i, i2);
        path2.addRect(rectF2, Path.Direction.CCW);
    }

    public int getContentPaddingBottom() {
        return this.s;
    }

    public final int getContentPaddingEnd() {
        int i = this.u;
        return i != Integer.MIN_VALUE ? i : c() ? this.p : this.r;
    }

    public int getContentPaddingLeft() {
        int i;
        int i2;
        if (this.t != Integer.MIN_VALUE || this.u != Integer.MIN_VALUE) {
            if (c() && (i2 = this.u) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!c() && (i = this.t) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.p;
    }

    public int getContentPaddingRight() {
        int i;
        int i2;
        if (this.t != Integer.MIN_VALUE || this.u != Integer.MIN_VALUE) {
            if (c() && (i2 = this.t) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!c() && (i = this.u) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.r;
    }

    public final int getContentPaddingStart() {
        int i = this.t;
        return i != Integer.MIN_VALUE ? i : c() ? this.r : this.p;
    }

    public int getContentPaddingTop() {
        return this.q;
    }

    @Override // android.view.View
    public int getPaddingBottom() {
        return super.getPaddingBottom() - getContentPaddingBottom();
    }

    @Override // android.view.View
    public int getPaddingEnd() {
        return super.getPaddingEnd() - getContentPaddingEnd();
    }

    @Override // android.view.View
    public int getPaddingLeft() {
        return super.getPaddingLeft() - getContentPaddingLeft();
    }

    @Override // android.view.View
    public int getPaddingRight() {
        return super.getPaddingRight() - getContentPaddingRight();
    }

    @Override // android.view.View
    public int getPaddingStart() {
        return super.getPaddingStart() - getContentPaddingStart();
    }

    @Override // android.view.View
    public int getPaddingTop() {
        return super.getPaddingTop() - getContentPaddingTop();
    }

    public ki0 getShapeAppearanceModel() {
        return this.m;
    }

    public ColorStateList getStrokeColor() {
        return this.k;
    }

    public float getStrokeWidth() {
        return this.n;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.o, this.i);
        if (this.k == null) {
            return;
        }
        Paint paint = this.h;
        paint.setStrokeWidth(this.n);
        int colorForState = this.k.getColorForState(getDrawableState(), this.k.getDefaultColor());
        if (this.n <= 0.0f || colorForState == 0) {
            return;
        }
        paint.setColor(colorForState);
        canvas.drawPath(this.j, paint);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!this.v && isLayoutDirectionResolved()) {
            this.v = true;
            if (!isPaddingRelative() && this.t == Integer.MIN_VALUE && this.u == Integer.MIN_VALUE) {
                setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
            } else {
                setPaddingRelative(super.getPaddingStart(), super.getPaddingTop(), super.getPaddingEnd(), super.getPaddingBottom());
            }
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        d(i, i2);
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(getContentPaddingLeft() + i, getContentPaddingTop() + i2, getContentPaddingRight() + i3, getContentPaddingBottom() + i4);
    }

    @Override // android.view.View
    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(getContentPaddingStart() + i, getContentPaddingTop() + i2, getContentPaddingEnd() + i3, getContentPaddingBottom() + i4);
    }

    @Override // defpackage.vi0
    public void setShapeAppearanceModel(ki0 ki0Var) {
        this.m = ki0Var;
        u50 u50Var = this.l;
        if (u50Var != null) {
            u50Var.setShapeAppearanceModel(ki0Var);
        }
        d(getWidth(), getHeight());
        invalidate();
        invalidateOutline();
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.k = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(int i) {
        setStrokeColor(rp0.w(getContext(), i));
    }

    public void setStrokeWidth(float f) {
        if (this.n != f) {
            this.n = f;
            invalidate();
        }
    }

    public void setStrokeWidthResource(int i) {
        setStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public ShapeableImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ShapeableImageView(Context context, AttributeSet attributeSet, int i) {
        int i2 = w;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.e = li0.a;
        this.j = new Path();
        this.v = false;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.i = paint;
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f = new RectF();
        this.g = new RectF();
        this.o = new Path();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.ShapeableImageView, i, i2);
        setLayerType(2, null);
        this.k = xi.s(context2, typedArrayObtainStyledAttributes, R$styleable.ShapeableImageView_strokeColor);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_strokeWidth, 0);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPadding, 0);
        this.p = dimensionPixelSize;
        this.q = dimensionPixelSize;
        this.r = dimensionPixelSize;
        this.s = dimensionPixelSize;
        this.p = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingLeft, dimensionPixelSize);
        this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingTop, dimensionPixelSize);
        this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingRight, dimensionPixelSize);
        this.s = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingBottom, dimensionPixelSize);
        this.t = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingStart, Integer.MIN_VALUE);
        this.u = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingEnd, Integer.MIN_VALUE);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint2 = new Paint();
        this.h = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.m = ki0.c(context2, attributeSet, i, i2).a();
        setOutlineProvider(new zi0(this));
    }
}
