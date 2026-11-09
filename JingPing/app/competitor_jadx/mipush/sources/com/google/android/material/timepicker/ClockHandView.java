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
import defpackage.f51;
import defpackage.fa0;
import defpackage.o80;
import defpackage.pf;
import defpackage.qf;
import defpackage.s90;
import defpackage.y4;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class ClockHandView extends View {
    public final ValueAnimator E;
    public boolean F;
    public final ArrayList G;
    public final int H;
    public final float I;
    public final Paint J;
    public final RectF K;
    public final int L;
    public float M;
    public boolean N;
    public double O;
    public int P;
    public int Q;

    public ClockHandView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialClockStyle);
    }

    public final int a(int i) {
        return i == 2 ? Math.round(this.P * 0.66f) : this.P;
    }

    public void addOnRotateListener(qf qfVar) {
        this.G.add(qfVar);
    }

    public final void b(float f) {
        ValueAnimator valueAnimator = this.E;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        float f2 = f % 360.0f;
        this.M = f2;
        this.O = Math.toRadians(f2 - 90.0f);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float fA = a(this.Q);
        float fCos = (((float) Math.cos(this.O)) * fA) + width;
        float fSin = (fA * ((float) Math.sin(this.O))) + height;
        float f3 = this.H;
        this.K.set(fCos - f3, fSin - f3, fCos + f3, fSin + f3);
        Iterator it = this.G.iterator();
        while (it.hasNext()) {
            ClockFaceView clockFaceView = (ClockFaceView) ((qf) it.next());
            if (Math.abs(clockFaceView.n0 - f2) > 0.001f) {
                clockFaceView.n0 = f2;
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
        float fA = a(this.Q);
        float fCos = (((float) Math.cos(this.O)) * fA) + f;
        float f2 = height;
        float fSin = (fA * ((float) Math.sin(this.O))) + f2;
        Paint paint = this.J;
        paint.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.H, paint);
        double dSin = Math.sin(this.O);
        paint.setStrokeWidth(this.L);
        canvas.drawLine(f, f2, width + ((int) (Math.cos(this.O) * d)), height + ((int) (d * dSin)), paint);
        canvas.drawCircle(f, f2, this.I, paint);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.E.isRunning()) {
            return;
        }
        b(this.M);
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
            this.N = false;
            z = true;
            z2 = false;
        } else if (actionMasked == 1 || actionMasked == 2) {
            z2 = this.N;
            if (this.F) {
                this.Q = o80.v((float) (getWidth() / 2), (float) (getHeight() / 2), x, y) <= ((float) a(2)) + fa0.C(getContext(), 12) ? 2 : 1;
            }
            z = false;
        } else {
            z2 = false;
            z = false;
        }
        boolean z4 = this.N;
        int degrees = (int) Math.toDegrees(Math.atan2(y - (getHeight() / 2), x - (getWidth() / 2)));
        int i = degrees + 90;
        if (i < 0) {
            i = degrees + 450;
        }
        float f = i;
        boolean z5 = this.M != f;
        if (z && z5) {
            z3 = true;
        } else if (z5 || z2) {
            b(f);
            z3 = true;
        }
        this.N = z4 | z3;
        return true;
    }

    public ClockHandView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.E = new ValueAnimator();
        this.G = new ArrayList();
        Paint paint = new Paint();
        this.J = paint;
        this.K = new RectF();
        this.Q = 1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockHandView, i, R$style.Widget_MaterialComponents_TimePicker_Clock);
        s90.U(context, R$attr.motionDurationLong2, Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
        s90.V(context, R$attr.motionEasingEmphasizedInterpolator, y4.b);
        this.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ClockHandView_materialCircleRadius, 0);
        this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ClockHandView_selectorSize, 0);
        this.L = getResources().getDimensionPixelSize(R$dimen.material_clock_hand_stroke_width);
        this.I = r7.getDimensionPixelSize(R$dimen.material_clock_hand_center_dot_radius);
        int color = typedArrayObtainStyledAttributes.getColor(R$styleable.ClockHandView_clockHandColor, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        b(0.0f);
        ViewConfiguration.get(context).getScaledTouchSlop();
        WeakHashMap weakHashMap = f51.a;
        setImportantForAccessibility(2);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void setOnActionUpListener(pf pfVar) {
    }
}
