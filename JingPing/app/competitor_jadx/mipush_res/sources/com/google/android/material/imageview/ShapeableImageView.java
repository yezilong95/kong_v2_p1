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
import defpackage.av0;
import defpackage.bv0;
import defpackage.kv0;
import defpackage.la0;
import defpackage.ld0;
import defpackage.ov0;
import defpackage.pd0;
import defpackage.t40;
import defpackage.zu0;

/* JADX INFO: loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements kv0 {
    public static final int c0 = R$style.Widget_MaterialComponents_ShapeableImageView;
    public final bv0 H;
    public final RectF I;
    public final RectF J;
    public final Paint K;
    public final Paint L;
    public final Path M;
    public ColorStateList N;
    public ld0 O;
    public zu0 P;
    public float Q;
    public final Path R;
    public final int S;
    public final int T;
    public final int U;
    public final int V;
    public final int W;
    public final int a0;
    public boolean b0;

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public final boolean c() {
        return getLayoutDirection() == 1;
    }

    public final void d(int i, int i2) {
        RectF rectF = this.I;
        rectF.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
        zu0 zu0Var = this.P;
        Path path = this.M;
        this.H.a(zu0Var, 1.0f, rectF, null, path);
        Path path2 = this.R;
        path2.rewind();
        path2.addPath(path);
        RectF rectF2 = this.J;
        rectF2.set(0.0f, 0.0f, i, i2);
        path2.addRect(rectF2, Path.Direction.CCW);
    }

    public int getContentPaddingBottom() {
        return this.V;
    }

    public final int getContentPaddingEnd() {
        int i = this.a0;
        return i != Integer.MIN_VALUE ? i : c() ? this.S : this.U;
    }

    public int getContentPaddingLeft() {
        int i;
        int i2;
        if (this.W != Integer.MIN_VALUE || this.a0 != Integer.MIN_VALUE) {
            if (c() && (i2 = this.a0) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!c() && (i = this.W) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.S;
    }

    public int getContentPaddingRight() {
        int i;
        int i2;
        if (this.W != Integer.MIN_VALUE || this.a0 != Integer.MIN_VALUE) {
            if (c() && (i2 = this.W) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!c() && (i = this.a0) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.U;
    }

    public final int getContentPaddingStart() {
        int i = this.W;
        return i != Integer.MIN_VALUE ? i : c() ? this.U : this.S;
    }

    public int getContentPaddingTop() {
        return this.T;
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

    public zu0 getShapeAppearanceModel() {
        return this.P;
    }

    public ColorStateList getStrokeColor() {
        return this.N;
    }

    public float getStrokeWidth() {
        return this.Q;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.R, this.L);
        if (this.N == null) {
            return;
        }
        Paint paint = this.K;
        paint.setStrokeWidth(this.Q);
        int colorForState = this.N.getColorForState(getDrawableState(), this.N.getDefaultColor());
        if (this.Q <= 0.0f || colorForState == 0) {
            return;
        }
        paint.setColor(colorForState);
        canvas.drawPath(this.M, paint);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!this.b0 && isLayoutDirectionResolved()) {
            this.b0 = true;
            if (!isPaddingRelative() && this.W == Integer.MIN_VALUE && this.a0 == Integer.MIN_VALUE) {
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

    @Override // defpackage.kv0
    public void setShapeAppearanceModel(zu0 zu0Var) {
        this.P = zu0Var;
        ld0 ld0Var = this.O;
        if (ld0Var != null) {
            ld0Var.setShapeAppearanceModel(zu0Var);
        }
        d(getWidth(), getHeight());
        invalidate();
        invalidateOutline();
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.N = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(int i) {
        setStrokeColor(t40.Y(getContext(), i));
    }

    public void setStrokeWidth(float f) {
        if (this.Q != f) {
            this.Q = f;
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
        int i2 = c0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.H = av0.a;
        this.M = new Path();
        this.b0 = false;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.L = paint;
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.I = new RectF();
        this.J = new RectF();
        this.R = new Path();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.ShapeableImageView, i, i2);
        setLayerType(2, null);
        this.N = la0.p(context2, typedArrayObtainStyledAttributes, R$styleable.ShapeableImageView_strokeColor);
        this.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_strokeWidth, 0);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPadding, 0);
        this.S = dimensionPixelSize;
        this.T = dimensionPixelSize;
        this.U = dimensionPixelSize;
        this.V = dimensionPixelSize;
        this.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingLeft, dimensionPixelSize);
        this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingTop, dimensionPixelSize);
        this.U = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingRight, dimensionPixelSize);
        this.V = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingBottom, dimensionPixelSize);
        this.W = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingStart, Integer.MIN_VALUE);
        this.a0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingEnd, Integer.MIN_VALUE);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint2 = new Paint();
        this.K = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.P = zu0.c(context2, attributeSet, i, i2).a();
        setOutlineProvider(new ov0(this));
    }
}
