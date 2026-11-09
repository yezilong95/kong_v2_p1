package defpackage;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import io.nats.client.BaseConsumeOptions;

/* JADX INFO: loaded from: classes.dex */
public final class ba0 implements View.OnTouchListener {
    public static final int V = ViewConfiguration.getTapTimeout();
    public final x8 E;
    public final AccelerateInterpolator F;
    public final ListView G;
    public f H;
    public final float[] I;
    public final float[] J;
    public final int K;
    public final int L;
    public final float[] M;
    public final float[] N;
    public final float[] O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public final gq U;

    public ba0(gq gqVar) {
        x8 x8Var = new x8();
        x8Var.e = Long.MIN_VALUE;
        x8Var.g = -1L;
        x8Var.f = 0L;
        this.E = x8Var;
        this.F = new AccelerateInterpolator();
        float[] fArr = {0.0f, 0.0f};
        this.I = fArr;
        float[] fArr2 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.J = fArr2;
        float[] fArr3 = {0.0f, 0.0f};
        this.M = fArr3;
        float[] fArr4 = {0.0f, 0.0f};
        this.N = fArr4;
        float[] fArr5 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.O = fArr5;
        this.G = gqVar;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float f2 = ((int) ((1575.0f * f) + 0.5f)) / 1000.0f;
        fArr5[0] = f2;
        fArr5[1] = f2;
        float f3 = ((int) ((f * 315.0f) + 0.5f)) / 1000.0f;
        fArr4[0] = f3;
        fArr4[1] = f3;
        this.K = 1;
        fArr2[0] = Float.MAX_VALUE;
        fArr2[1] = Float.MAX_VALUE;
        fArr[0] = 0.2f;
        fArr[1] = 0.2f;
        fArr3[0] = 0.001f;
        fArr3[1] = 0.001f;
        this.L = V;
        x8Var.a = BaseConsumeOptions.DEFAULT_MESSAGE_COUNT;
        x8Var.b = BaseConsumeOptions.DEFAULT_MESSAGE_COUNT;
        this.U = gqVar;
    }

    public static float b(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float a(float r4, float r5, float r6, int r7) {
        /*
            r3 = this;
            float[] r0 = r3.I
            r0 = r0[r7]
            float[] r1 = r3.J
            r1 = r1[r7]
            float r0 = r0 * r5
            r2 = 0
            float r0 = b(r0, r2, r1)
            float r1 = r3.c(r4, r0)
            float r5 = r5 - r4
            float r4 = r3.c(r5, r0)
            float r4 = r4 - r1
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            android.view.animation.AccelerateInterpolator r0 = r3.F
            if (r5 >= 0) goto L25
            float r4 = -r4
            float r4 = r0.getInterpolation(r4)
            float r4 = -r4
            goto L2d
        L25:
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r5 <= 0) goto L36
            float r4 = r0.getInterpolation(r4)
        L2d:
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r4 = b(r4, r5, r0)
            goto L37
        L36:
            r4 = r2
        L37:
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r5 != 0) goto L3c
            return r2
        L3c:
            float[] r0 = r3.M
            r0 = r0[r7]
            float[] r1 = r3.N
            r1 = r1[r7]
            float[] r2 = r3.O
            r7 = r2[r7]
            float r0 = r0 * r6
            if (r5 <= 0) goto L51
            float r4 = r4 * r0
            float r4 = b(r4, r1, r7)
            return r4
        L51:
            float r4 = -r4
            float r4 = r4 * r0
            float r4 = b(r4, r1, r7)
            float r4 = -r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ba0.a(float, float, float, int):float");
    }

    public final float c(float f, float f2) {
        if (f2 != 0.0f) {
            int i = this.K;
            if (i == 0 || i == 1) {
                if (f < f2) {
                    if (f >= 0.0f) {
                        return 1.0f - (f / f2);
                    }
                    if (this.S && i == 1) {
                        return 1.0f;
                    }
                }
            } else if (i == 2 && f < 0.0f) {
                return f / (-f2);
            }
        }
        return 0.0f;
    }

    public final void d() {
        int i = 0;
        if (this.Q) {
            this.S = false;
            return;
        }
        x8 x8Var = this.E;
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i2 = (int) (jCurrentAnimationTimeMillis - x8Var.e);
        int i3 = x8Var.b;
        if (i2 > i3) {
            i = i3;
        } else if (i2 >= 0) {
            i = i2;
        }
        x8Var.i = i;
        x8Var.h = x8Var.a(jCurrentAnimationTimeMillis);
        x8Var.g = jCurrentAnimationTimeMillis;
    }

    public final boolean e() {
        gq gqVar;
        int count;
        x8 x8Var = this.E;
        float f = x8Var.d;
        int iAbs = (int) (f / Math.abs(f));
        Math.abs(x8Var.c);
        if (iAbs != 0 && (count = (gqVar = this.U).getCount()) != 0) {
            int childCount = gqVar.getChildCount();
            int firstVisiblePosition = gqVar.getFirstVisiblePosition();
            int i = firstVisiblePosition + childCount;
            if (iAbs <= 0 ? !(iAbs >= 0 || (firstVisiblePosition <= 0 && gqVar.getChildAt(0).getTop() >= 0)) : !(i >= count && gqVar.getChildAt(childCount - 1).getBottom() <= gqVar.getHeight())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0014, code lost:
    
        if (r0 != 3) goto L30;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
            r7 = this;
            boolean r0 = r7.T
            r1 = 0
            if (r0 != 0) goto L7
            goto L7d
        L7:
            int r0 = r9.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1b
            if (r0 == r2) goto L17
            r3 = 2
            if (r0 == r3) goto L1f
            r8 = 3
            if (r0 == r8) goto L17
            goto L7d
        L17:
            r7.d()
            return r1
        L1b:
            r7.R = r2
            r7.P = r1
        L1f:
            float r0 = r9.getX()
            int r3 = r8.getWidth()
            float r3 = (float) r3
            android.widget.ListView r4 = r7.G
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r0 = r7.a(r0, r3, r5, r1)
            float r9 = r9.getY()
            int r8 = r8.getHeight()
            float r8 = (float) r8
            int r3 = r4.getHeight()
            float r3 = (float) r3
            float r8 = r7.a(r9, r8, r3, r2)
            x8 r9 = r7.E
            r9.c = r0
            r9.d = r8
            boolean r8 = r7.S
            if (r8 != 0) goto L7d
            boolean r8 = r7.e()
            if (r8 == 0) goto L7d
            f r8 = r7.H
            if (r8 != 0) goto L61
            f r8 = new f
            r9 = 4
            r8.<init>(r9, r7)
            r7.H = r8
        L61:
            r7.S = r2
            r7.Q = r2
            boolean r8 = r7.P
            if (r8 != 0) goto L76
            int r8 = r7.L
            if (r8 <= 0) goto L76
            f r9 = r7.H
            long r5 = (long) r8
            java.util.WeakHashMap r8 = defpackage.f51.a
            r4.postOnAnimationDelayed(r9, r5)
            goto L7b
        L76:
            f r8 = r7.H
            r8.run()
        L7b:
            r7.P = r2
        L7d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ba0.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
