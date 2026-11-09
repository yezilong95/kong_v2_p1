package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ClippableRoundedCornerLayout extends FrameLayout {
    public Path b;
    public float c;

    public ClippableRoundedCornerLayout(Context context) {
        super(context);
    }

    public final void a(float f, float f2, float f3, float f4, float f5) {
        RectF rectF = new RectF(f, f2, f3, f4);
        if (this.b == null) {
            this.b = new Path();
        }
        this.c = f5;
        this.b.reset();
        this.b.addRoundRect(rectF, f5, f5, Path.Direction.CW);
        this.b.close();
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.b == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int iSave = canvas.save();
        canvas.clipPath(this.b);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(iSave);
    }

    public float getCornerRadius() {
        return this.c;
    }

    public ClippableRoundedCornerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ClippableRoundedCornerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
