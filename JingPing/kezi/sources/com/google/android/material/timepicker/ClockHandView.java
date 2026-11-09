package com.google.android.material.timepicker;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.work.impl.Scheduler;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.k9;
import defpackage.rp0;
import defpackage.t3;
import defpackage.td;
import defpackage.ud;
import defpackage.xr0;
import defpackage.za0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class ClockHandView extends View {
    public final ValueAnimator b;
    public boolean c;
    public final ArrayList d;
    public final int e;
    public final float f;
    public final Paint g;
    public final RectF h;
    public final int i;
    public float j;
    public boolean k;
    public double l;
    public int m;
    public int n;

    public ClockHandView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialClockStyle);
    }

    public final int a(int i) {
        return i == 2 ? Math.round(this.m * 0.66f) : this.m;
    }

    public void addOnRotateListener(ud udVar) {
        this.d.add(udVar);
    }

    public final void b(float f) {
        ValueAnimator valueAnimator = this.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        float f2 = f % 360.0f;
        this.j = f2;
        this.l = Math.toRadians(f2 - 90.0f);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float fA = a(this.n);
        float fCos = (((float) Math.cos(this.l)) * fA) + width;
        float fSin = (fA * ((float) Math.sin(this.l))) + height;
        float f3 = this.e;
        this.h.set(fCos - f3, fSin - f3, fCos + f3, fSin + f3);
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ClockFaceView clockFaceView = (ClockFaceView) ((ud) it.next());
            if (Math.abs(clockFaceView.H - f2) > 0.001f) {
                clockFaceView.H = f2;
                clockFaceView.p();
            }
        }
        invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float f = width;
        float fA = a(this.n);
        float fCos = (((float) Math.cos(this.l)) * fA) + f;
        float f2 = height;
        float fSin = (fA * ((float) Math.sin(this.l))) + f2;
        Paint paint = this.g;
        paint.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.e, paint);
        double dSin = Math.sin(this.l);
        paint.setStrokeWidth(this.i);
        canvas.drawLine(f, f2, width + ((int) (Math.cos(this.l) * d)), height + ((int) (d * dSin)), paint);
        canvas.drawCircle(f, f2, this.f, paint);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.b.isRunning()) {
            return;
        }
        b(this.j);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean z3 = false;
        if (actionMasked == 0) {
            this.k = false;
            z = true;
            z2 = false;
        } else if (actionMasked == 1 || actionMasked == 2) {
            z2 = this.k;
            if (this.c) {
                this.n = za0.n((float) (getWidth() / 2), (float) (getHeight() / 2), x, y) <= ((float) a(2)) + rp0.t(getContext(), 12) ? 2 : 1;
            }
            z = false;
        } else {
            z2 = false;
            z = false;
        }
        boolean z4 = this.k;
        int degrees = (int) Math.toDegrees(Math.atan2(y - (getHeight() / 2), x - (getWidth() / 2)));
        int i = degrees + 90;
        if (i < 0) {
            i = degrees + 450;
        }
        float f = i;
        boolean z5 = this.j != f;
        if (z && z5) {
            z3 = true;
        } else if (z5 || z2) {
            b(f);
            z3 = true;
        }
        this.k = z4 | z3;
        return true;
    }

    public ClockHandView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new ValueAnimator();
        this.d = new ArrayList();
        Paint paint = new Paint();
        this.g = paint;
        this.h = new RectF();
        this.n = 1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockHandView, i, R$style.Widget_MaterialComponents_TimePicker_Clock);
        k9.E(context, R$attr.motionDurationLong2, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
        k9.F(context, R$attr.motionEasingEmphasizedInterpolator, t3.b);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ClockHandView_materialCircleRadius, 0);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ClockHandView_selectorSize, 0);
        this.i = getResources().getDimensionPixelSize(R$dimen.material_clock_hand_stroke_width);
        this.f = r7.getDimensionPixelSize(R$dimen.material_clock_hand_center_dot_radius);
        int color = typedArrayObtainStyledAttributes.getColor(R$styleable.ClockHandView_clockHandColor, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        b(0.0f);
        ViewConfiguration.get(context).getScaledTouchSlop();
        WeakHashMap weakHashMap = xr0.a;
        setImportantForAccessibility(2);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void setOnActionUpListener(td tdVar) {
    }
}
