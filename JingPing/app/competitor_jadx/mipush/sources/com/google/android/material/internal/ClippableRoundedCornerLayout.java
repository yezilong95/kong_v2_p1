package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ClippableRoundedCornerLayout extends FrameLayout {
    public Path E;
    public float F;

    public ClippableRoundedCornerLayout(Context context) {
        super(context);
    }

    public final void a(float f, float f2, float f3, float f4, float f5) {
        RectF rectF = new RectF(f, f2, f3, f4);
        if (this.E == null) {
            this.E = new Path();
        }
        this.F = f5;
        this.E.reset();
        this.E.addRoundRect(rectF, f5, f5, Path.Direction.CW);
        this.E.close();
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.E == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int iSave = canvas.save();
        canvas.clipPath(this.E);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(iSave);
    }

    public float getCornerRadius() {
        return this.F;
    }

    public ClippableRoundedCornerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ClippableRoundedCornerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
