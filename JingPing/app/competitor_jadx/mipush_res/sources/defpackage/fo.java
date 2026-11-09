package defpackage;

import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Looper;
import android.provider.Settings;
import android.util.AndroidRuntimeException;
import android.view.Choreographer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class fo extends qp {
    public static final eo U = new eo();
    public final aq P;
    public final uw0 Q;
    public final tw0 R;
    public final zp S;
    public boolean T;

    public fo(Context context, aa aaVar, aq aqVar) {
        super(context, aaVar);
        this.T = false;
        this.P = aqVar;
        this.S = new zp();
        uw0 uw0Var = new uw0();
        this.Q = uw0Var;
        uw0Var.b = 1.0f;
        uw0Var.c = false;
        uw0Var.a = Math.sqrt(50.0f);
        uw0Var.c = false;
        tw0 tw0Var = new tw0(this);
        tw0Var.s = Float.MAX_VALUE;
        tw0Var.t = false;
        this.R = tw0Var;
        tw0Var.r = uw0Var;
        if (this.L != 1.0f) {
            this.L = 1.0f;
            invalidateSelf();
        }
    }

    public void addSpringAnimationEndListener(mq mqVar) {
        ArrayList arrayList = this.R.j;
        if (arrayList.contains(mqVar)) {
            return;
        }
        arrayList.add(mqVar);
    }

    @Override // defpackage.qp
    public final boolean d(boolean z, boolean z2, boolean z3) {
        boolean zD = super.d(z, z2, z3);
        z4 z4Var = this.G;
        ContentResolver contentResolver = this.E.getContentResolver();
        z4Var.getClass();
        float f = Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
        if (f == 0.0f) {
            this.T = true;
            return zD;
        }
        this.T = false;
        float f2 = 50.0f / f;
        uw0 uw0Var = this.Q;
        uw0Var.getClass();
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        uw0Var.a = Math.sqrt(f2);
        uw0Var.c = false;
        return zD;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            canvas.save();
            aq aqVar = this.P;
            Rect bounds = getBounds();
            float fB = b();
            ObjectAnimator objectAnimator = this.H;
            boolean z = objectAnimator != null && objectAnimator.isRunning();
            ObjectAnimator objectAnimator2 = this.I;
            boolean z2 = objectAnimator2 != null && objectAnimator2.isRunning();
            aqVar.a.a();
            aqVar.a(canvas, bounds, fB, z, z2);
            Paint paint = this.M;
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            aa aaVar = this.F;
            int i = aaVar.c[0];
            zp zpVar = this.S;
            zpVar.c = i;
            int iL = aaVar.g;
            if (iL > 0) {
                if (!(this.P instanceof d90)) {
                    iL = (int) ((s90.l(zpVar.b, 0.0f, 0.01f) * iL) / 0.01f);
                }
                this.P.d(canvas, paint, zpVar.b, 1.0f, aaVar.d, this.N, iL);
            } else {
                this.P.d(canvas, paint, 0.0f, 1.0f, aaVar.d, this.N, 0);
            }
            this.P.c(canvas, paint, zpVar, this.N);
            this.P.b(canvas, paint, aaVar.c[0], this.N);
            canvas.restore();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.P.e();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.P.f();
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        this.R.b();
        this.S.b = getLevel() / 10000.0f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i) {
        boolean z = this.T;
        zp zpVar = this.S;
        tw0 tw0Var = this.R;
        if (z) {
            tw0Var.b();
            zpVar.b = i / 10000.0f;
            invalidateSelf();
            return true;
        }
        tw0Var.b = zpVar.b * 10000.0f;
        tw0Var.c = true;
        float f = i;
        if (tw0Var.f) {
            tw0Var.s = f;
            return true;
        }
        if (tw0Var.r == null) {
            tw0Var.r = new uw0(f);
        }
        uw0 uw0Var = tw0Var.r;
        double d = f;
        uw0Var.i = d;
        double d2 = (float) d;
        if (d2 > Float.MAX_VALUE) {
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        float f2 = tw0Var.g;
        if (d2 < f2) {
            throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
        }
        double dAbs = Math.abs(tw0Var.i * 0.75f);
        uw0Var.d = dAbs;
        uw0Var.e = dAbs * 62.5d;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        boolean z2 = tw0Var.f;
        if (!z2 && !z2) {
            tw0Var.f = true;
            if (!tw0Var.c) {
                tw0Var.e.getClass();
                tw0Var.b = tw0Var.d.S.b * 10000.0f;
            }
            float f3 = tw0Var.b;
            if (f3 > Float.MAX_VALUE || f3 < f2) {
                throw new IllegalArgumentException("Starting value need to be in between min value and max value");
            }
            ThreadLocal threadLocal = x4.f;
            if (threadLocal.get() == null) {
                threadLocal.set(new x4());
            }
            x4 x4Var = (x4) threadLocal.get();
            ArrayList arrayList = x4Var.b;
            if (arrayList.size() == 0) {
                if (x4Var.d == null) {
                    x4Var.d = new ou(x4Var.c);
                }
                ou ouVar = x4Var.d;
                ((Choreographer) ouVar.G).postFrameCallback((w4) ouVar.H);
            }
            if (!arrayList.contains(tw0Var)) {
                arrayList.add(tw0Var);
                return true;
            }
        }
        return true;
    }

    public void removeSpringAnimationEndListener(mq mqVar) {
        this.R.removeEndListener(mqVar);
    }
}
