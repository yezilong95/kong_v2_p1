package com.mamimobi.videostream.view;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import defpackage.c5;
import defpackage.nk0;
import defpackage.qk0;
import defpackage.rk0;
import defpackage.xp;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class PatternLockView extends View {
    public static final /* synthetic */ int k0 = 0;
    public int E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public float J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public DotVerticalAlign T;
    public DisplayMode U;
    public final CopyOnWriteArrayList V;
    public final CopyOnWriteArrayList W;
    public boolean[][] a0;
    public rk0[][] b0;
    public final Paint c0;
    public final Paint d0;
    public final Path e0;
    public final Interpolator f0;
    public boolean g0;
    public float h0;
    public float i0;
    public final Vibrator j0;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class DisplayMode {
        public static final DisplayMode ANIMATE;
        public static final DisplayMode CORRECT;
        public static final /* synthetic */ DisplayMode[] E;
        public static final DisplayMode NORMAL;
        public static final DisplayMode WRONG;

        static {
            DisplayMode displayMode = new DisplayMode("NORMAL", 0);
            NORMAL = displayMode;
            DisplayMode displayMode2 = new DisplayMode("CORRECT", 1);
            CORRECT = displayMode2;
            DisplayMode displayMode3 = new DisplayMode("WRONG", 2);
            WRONG = displayMode3;
            DisplayMode displayMode4 = new DisplayMode("ANIMATE", 3);
            ANIMATE = displayMode4;
            E = new DisplayMode[]{displayMode, displayMode2, displayMode3, displayMode4};
        }

        public static DisplayMode valueOf(String str) {
            return (DisplayMode) Enum.valueOf(DisplayMode.class, str);
        }

        public static DisplayMode[] values() {
            return (DisplayMode[]) E.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class DotVerticalAlign {
        public static final DotVerticalAlign ALIGN_BOTTOM;
        public static final DotVerticalAlign ALIGN_CENTER;
        public static final DotVerticalAlign ALIGN_TOP;
        public static final /* synthetic */ DotVerticalAlign[] E;

        static {
            DotVerticalAlign dotVerticalAlign = new DotVerticalAlign("ALIGN_TOP", 0);
            ALIGN_TOP = dotVerticalAlign;
            DotVerticalAlign dotVerticalAlign2 = new DotVerticalAlign("ALIGN_CENTER", 1);
            ALIGN_CENTER = dotVerticalAlign2;
            DotVerticalAlign dotVerticalAlign3 = new DotVerticalAlign("ALIGN_BOTTOM", 2);
            ALIGN_BOTTOM = dotVerticalAlign3;
            E = new DotVerticalAlign[]{dotVerticalAlign, dotVerticalAlign2, dotVerticalAlign3};
        }

        public static DotVerticalAlign valueOf(String str) {
            return (DotVerticalAlign) Enum.valueOf(DotVerticalAlign.class, str);
        }

        public static DotVerticalAlign[] values() {
            return (DotVerticalAlign[]) E.clone();
        }

        public float calculateYOffset(float f, float f2) {
            int iOrdinal = ordinal();
            if (iOrdinal == 0) {
                return (-f2) * 0.3f;
            }
            if (iOrdinal != 2) {
                return 0.0f;
            }
            return f2 * 0.3f;
        }
    }

    public PatternLockView(Context context) {
        this(context, null);
    }

    private float getCellHeight() {
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        if (height > 0) {
            return (height * 1.0f) / this.E;
        }
        return 0.0f;
    }

    private float getCellWidth() {
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        if (width > 0) {
            return (width * 1.0f) / this.E;
        }
        return 0.0f;
    }

    private int getCurrentPathColor() {
        int iOrdinal = this.U.ordinal();
        return iOrdinal != 1 ? iOrdinal != 2 ? this.N : this.M : this.L;
    }

    public final void a() {
        ValueAnimator valueAnimator;
        for (int i = 0; i < this.E; i++) {
            for (int i2 = 0; i2 < this.E; i2++) {
                rk0 rk0Var = this.b0[i][i2];
                if (rk0Var != null && (valueAnimator = rk0Var.c) != null) {
                    valueAnimator.cancel();
                    rk0 rk0Var2 = this.b0[i][i2];
                    rk0Var2.c = null;
                    rk0Var2.b = false;
                }
            }
        }
    }

    public final void b() {
        a();
        this.W.clear();
        for (int i = 0; i < this.E; i++) {
            boolean[] zArr = this.a0[i];
            if (zArr != null) {
                Arrays.fill(zArr, false);
            }
        }
        this.g0 = false;
        this.h0 = -1.0f;
        this.i0 = -1.0f;
        invalidate();
        announceForAccessibility("图案已清除");
        Iterator it = this.V.iterator();
        while (it.hasNext()) {
            try {
                ((nk0) it.next()).getClass();
            } catch (Exception e) {
                Log.e("PatternLockView", "Error in onPatternCleared", e);
            }
        }
    }

    public final void c(float f, float f2) {
        Vibrator vibrator;
        int i;
        boolean z = false;
        int i2 = 2;
        int i3 = 1;
        this.h0 = f;
        this.i0 = f2;
        float cellWidth = getCellWidth();
        float cellHeight = getCellHeight();
        qk0 qk0Var = null;
        if (cellWidth > 0.0f && cellHeight > 0.0f) {
            int paddingLeft = (int) ((f - getPaddingLeft()) / cellWidth);
            int paddingTop = (int) ((f2 - getPaddingTop()) / cellHeight);
            if (paddingTop >= 0 && paddingTop < (i = this.E) && paddingLeft >= 0 && paddingLeft < i) {
                int i4 = (i * paddingTop) + paddingLeft + 1;
                getLocationOnScreen(new int[2]);
                qk0Var = new qk0(((paddingLeft + 0.5f) * cellWidth) + getPaddingLeft() + r0[0], this.T.calculateYOffset(cellHeight, this.Q) + ((paddingTop + 0.5f) * cellHeight) + getPaddingTop() + r0[1], paddingTop, paddingLeft, i4);
            }
        }
        if (qk0Var != null) {
            boolean[][] zArr = this.a0;
            int i5 = qk0Var.a;
            boolean[] zArr2 = zArr[i5];
            int i6 = qk0Var.b;
            boolean z2 = zArr2[i6];
            if (!z2) {
                int i7 = this.E;
                if (i5 < i7 && i6 < i7 && !z2) {
                    zArr2[i6] = true;
                    CopyOnWriteArrayList copyOnWriteArrayList = this.W;
                    copyOnWriteArrayList.add(qk0Var);
                    if (!this.G) {
                        rk0 rk0Var = this.b0[i5][i6];
                        ValueAnimator valueAnimator = rk0Var.c;
                        if (valueAnimator != null && valueAnimator.isRunning()) {
                            rk0Var.c.cancel();
                        }
                        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.P, this.Q);
                        valueAnimatorOfFloat.setDuration(this.R);
                        Interpolator interpolator = this.f0;
                        if (interpolator != null) {
                            valueAnimatorOfFloat.setInterpolator(interpolator);
                        }
                        valueAnimatorOfFloat.addUpdateListener(new c5(this, rk0Var, i3));
                        valueAnimatorOfFloat.addListener(new xp(i2, this, rk0Var, z));
                        rk0Var.c = valueAnimatorOfFloat;
                        valueAnimatorOfFloat.start();
                    }
                    announceForAccessibility("已添加点");
                    new ArrayList(copyOnWriteArrayList);
                    Iterator it = this.V.iterator();
                    while (it.hasNext()) {
                        try {
                            ((nk0) it.next()).getClass();
                        } catch (Exception e) {
                            Log.e("PatternLockView", "Error in onPatternProgress", e);
                        }
                    }
                }
                if (this.H && (vibrator = this.j0) != null && vibrator.hasVibrator()) {
                    performHapticFeedback(3);
                }
            }
        }
        invalidate();
    }

    public final void d(int i) {
        this.E = i;
        this.W.clear();
        int i2 = this.E;
        this.a0 = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, i2, i2);
        int i3 = this.E;
        this.b0 = (rk0[][]) Array.newInstance((Class<?>) rk0.class, i3, i3);
        for (int i4 = 0; i4 < this.E; i4++) {
            for (int i5 = 0; i5 < this.E; i5++) {
                rk0[] rk0VarArr = this.b0[i4];
                rk0 rk0Var = new rk0();
                rk0Var.a = 12.0f;
                ValueAnimator valueAnimator = rk0Var.c;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                    rk0Var.c = null;
                }
                rk0VarArr[i5] = rk0Var;
                rk0 rk0Var2 = this.b0[i4][i5];
                rk0Var2.a = this.P;
                rk0Var2.b = false;
            }
        }
    }

    public float getAspectRatio() {
        return this.J;
    }

    public int getCorrectColor() {
        return this.L;
    }

    public DisplayMode getDisplayMode() {
        return this.U;
    }

    public int getDotAnimationDuration() {
        return this.R;
    }

    public int getDotCount() {
        return this.E;
    }

    public DotVerticalAlign getDotVerticalAlign() {
        return this.T;
    }

    public int getNormalColor() {
        return this.K;
    }

    public int getNormalDotSize() {
        return this.P;
    }

    public int getPathColor() {
        return this.N;
    }

    public int getPathEndAnimationDuration() {
        return this.S;
    }

    public int getPathWidth() {
        return this.O;
    }

    public List<qk0> getPattern() {
        return new ArrayList(this.W);
    }

    public int getSelectedDotSize() {
        return this.Q;
    }

    public int getWrongColor() {
        return this.M;
    }

    @Override // android.view.View
    public final boolean isHapticFeedbackEnabled() {
        return this.H;
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
        this.V.clear();
        Vibrator vibrator = this.j0;
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cellWidth = getCellWidth();
        float cellHeight = getCellHeight();
        if (cellWidth <= 0.0f || cellHeight <= 0.0f) {
            return;
        }
        for (int i = 0; i < this.E; i++) {
            for (int i2 = 0; i2 < this.E; i2++) {
                float paddingLeft = ((i2 + 0.5f) * cellWidth) + getPaddingLeft();
                float fCalculateYOffset = this.T.calculateYOffset(cellHeight, this.Q) + ((i + 0.5f) * cellHeight) + getPaddingTop();
                this.c0.setColor(this.a0[i][i2] ? this.U == DisplayMode.WRONG ? this.M : this.L : this.K);
                rk0 rk0Var = this.b0[i][i2];
                canvas.drawCircle(paddingLeft, fCalculateYOffset, rk0Var != null ? rk0Var.a : this.P, this.c0);
            }
        }
        CopyOnWriteArrayList copyOnWriteArrayList = this.W;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        this.d0.setColor(getCurrentPathColor());
        Path path = this.e0;
        path.reset();
        qk0 qk0Var = (qk0) copyOnWriteArrayList.get(0);
        path.moveTo(((qk0Var.b + 0.5f) * cellWidth) + getPaddingLeft(), this.T.calculateYOffset(cellHeight, this.Q) + ((qk0Var.a + 0.5f) * cellHeight) + getPaddingTop());
        for (int i3 = 1; i3 < copyOnWriteArrayList.size(); i3++) {
            qk0 qk0Var2 = (qk0) copyOnWriteArrayList.get(i3);
            path.lineTo(((qk0Var2.b + 0.5f) * cellWidth) + getPaddingLeft(), this.T.calculateYOffset(cellHeight, this.Q) + ((qk0Var2.a + 0.5f) * cellHeight) + getPaddingTop());
        }
        if (this.g0) {
            float f = this.h0;
            if (f >= 0.0f) {
                float f2 = this.i0;
                if (f2 >= 0.0f) {
                    path.lineTo(f, f2);
                }
            }
        }
        canvas.drawPath(path, this.d0);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.I) {
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(Math.max((int) (View.MeasureSpec.getMode(i) == 1073741824 ? View.MeasureSpec.getSize(i) / this.J : getResources().getDisplayMetrics().density * 300.0f), 1), 1073741824));
        } else {
            super.onMeasure(i, i2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0038  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r11) {
        /*
            r10 = this;
            boolean r0 = r10.F
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            float r0 = r11.getX()
            float r2 = r11.getY()
            float r3 = r11.getRawX()
            float r4 = r11.getRawY()
            int r11 = r11.getAction()
            java.util.concurrent.CopyOnWriteArrayList r5 = r10.V
            r6 = 3
            java.lang.String r7 = "PatternLockView"
            r8 = 1
            if (r11 == 0) goto L79
            if (r11 == r8) goto L38
            r9 = 2
            if (r11 == r9) goto L2b
            if (r11 == r6) goto L38
            goto Lb7
        L2b:
            java.lang.String r11 = "m"
            defpackage.sa0.b(r11, r3, r4)
            boolean r11 = r10.g0
            if (r11 == 0) goto Lb7
            r10.c(r0, r2)
            return r8
        L38:
            java.util.ArrayList r11 = defpackage.sa0.b
            boolean r11 = r11.isEmpty()
            if (r11 != 0) goto L47
            android.content.Context r11 = r10.getContext()
            defpackage.sa0.e(r11)
        L47:
            boolean r11 = r10.g0
            if (r11 == 0) goto Lb7
            r10.g0 = r1
            r11 = -1082130432(0xffffffffbf800000, float:-1.0)
            r10.h0 = r11
            r10.i0 = r11
            java.util.ArrayList r11 = new java.util.ArrayList
            java.util.concurrent.CopyOnWriteArrayList r0 = r10.W
            r11.<init>(r0)
            java.util.Iterator r0 = r5.iterator()
        L5e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L75
            java.lang.Object r1 = r0.next()
            nk0 r1 = (defpackage.nk0) r1
            r1.a(r11)     // Catch: java.lang.Exception -> L6e
            goto L5e
        L6e:
            r1 = move-exception
            java.lang.String r2 = "Error in onPatternCompleted"
            android.util.Log.e(r7, r2, r1)
            goto L5e
        L75:
            r10.invalidate()
            goto Lb7
        L79:
            r10.b()
            r10.g0 = r8
            java.util.ArrayList r11 = defpackage.sa0.b
            r11.clear()
            java.lang.String r11 = "d"
            defpackage.sa0.b(r11, r3, r4)
            r10.c(r0, r2)
            java.util.Iterator r11 = r5.iterator()
        L8f:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto La6
            java.lang.Object r0 = r11.next()
            nk0 r0 = (defpackage.nk0) r0
            r0.getClass()     // Catch: java.lang.Exception -> L9f
            goto L8f
        L9f:
            r0 = move-exception
            java.lang.String r1 = "Error in onPatternStarted"
            android.util.Log.e(r7, r1, r0)
            goto L8f
        La6:
            boolean r11 = r10.H
            if (r11 == 0) goto Lb7
            android.os.Vibrator r11 = r10.j0
            if (r11 == 0) goto Lb7
            boolean r11 = r11.hasVibrator()
            if (r11 == 0) goto Lb7
            r10.performHapticFeedback(r6)
        Lb7:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mamimobi.videostream.view.PatternLockView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setAspectRatio(float f) {
        if (f <= 0.0f) {
            f = 1.0f;
        }
        this.J = f;
        requestLayout();
    }

    public void setAspectRatioEnabled(boolean z) {
        this.I = z;
        requestLayout();
    }

    public void setCorrectColor(int i) {
        this.L = i;
        invalidate();
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.U = displayMode;
        invalidate();
    }

    public void setDotAnimationDuration(int i) {
        this.R = Math.max(50, i);
    }

    public void setDotCount(int i) {
        if (i < 2 || i > 6) {
            throw new IllegalArgumentException("网格大小必须在 2~6 之间");
        }
        a();
        d(i);
        requestLayout();
        invalidate();
    }

    public void setDotVerticalAlign(DotVerticalAlign dotVerticalAlign) {
        this.T = dotVerticalAlign;
        invalidate();
    }

    @Override // android.view.View
    public void setHapticFeedbackEnabled(boolean z) {
        this.H = z;
    }

    public void setInputEnabled(boolean z) {
        this.F = z;
        if (z) {
            return;
        }
        b();
    }

    public void setNormalColor(int i) {
        this.K = i;
        invalidate();
    }

    public void setNormalDotSize(int i) {
        this.P = i;
        for (int i2 = 0; i2 < this.E; i2++) {
            for (int i3 = 0; i3 < this.E; i3++) {
                rk0 rk0Var = this.b0[i2][i3];
                if (rk0Var != null && !rk0Var.b) {
                    rk0Var.a = i;
                }
            }
        }
        invalidate();
    }

    public void setPathColor(int i) {
        this.N = i;
        invalidate();
    }

    public void setPathEndAnimationDuration(int i) {
        this.S = Math.max(50, i);
    }

    public void setPathWidth(int i) {
        this.O = i;
        Paint paint = this.d0;
        if (paint != null) {
            paint.setStrokeWidth(i);
        }
        invalidate();
    }

    public void setSelectedDotSize(int i) {
        this.Q = i;
        invalidate();
    }

    public void setStealthMode(boolean z) {
        this.G = z;
    }

    public void setWrongColor(int i) {
        this.M = i;
        invalidate();
    }

    public PatternLockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = 3;
        this.F = true;
        this.G = false;
        this.H = true;
        this.I = true;
        this.J = 1.0f;
        this.K = -5592406;
        this.L = -16725933;
        this.M = -765666;
        this.N = -12285185;
        this.O = 8;
        this.P = 12;
        this.Q = 28;
        this.R = 190;
        this.S = 100;
        this.T = DotVerticalAlign.ALIGN_CENTER;
        this.U = DisplayMode.NORMAL;
        this.V = new CopyOnWriteArrayList();
        this.W = new CopyOnWriteArrayList();
        this.e0 = new Path();
        this.g0 = false;
        this.h0 = -1.0f;
        this.i0 = -1.0f;
        d(3);
        this.c0 = new Paint(5);
        Paint paint = new Paint(5);
        this.d0 = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.d0.setStrokeJoin(Paint.Join.ROUND);
        this.d0.setStrokeCap(Paint.Cap.ROUND);
        this.d0.setStrokeWidth(this.O);
        if (!isInEditMode() && getContext() != null) {
            try {
                this.f0 = AnimationUtils.loadInterpolator(getContext(), R.interpolator.fast_out_slow_in);
                AnimationUtils.loadInterpolator(getContext(), R.interpolator.fast_out_linear_in);
            } catch (Exception e) {
                Log.e("PatternLockView", "Failed to load interpolators", e);
                this.f0 = null;
            }
        }
        if (getContext() != null) {
            this.j0 = (Vibrator) getContext().getSystemService("vibrator");
        }
    }
}
