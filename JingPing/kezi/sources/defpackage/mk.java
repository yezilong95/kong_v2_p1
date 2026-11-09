package defpackage;

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
public final class mk extends nl {
    public static final lk r = new lk();
    public final wl m;
    public final gk0 n;
    public final fk0 o;
    public float p;
    public boolean q;

    public mk(Context context, e8 e8Var, wl wlVar) {
        super(context, e8Var);
        this.q = false;
        this.m = wlVar;
        wlVar.b = this;
        gk0 gk0Var = new gk0();
        this.n = gk0Var;
        gk0Var.b = 1.0f;
        gk0Var.c = false;
        gk0Var.a = Math.sqrt(50.0f);
        gk0Var.c = false;
        fk0 fk0Var = new fk0(this);
        fk0Var.s = Float.MAX_VALUE;
        fk0Var.t = false;
        this.o = fk0Var;
        fk0Var.r = gk0Var;
        if (this.i != 1.0f) {
            this.i = 1.0f;
            invalidateSelf();
        }
    }

    public void addSpringAnimationEndListener(jm jmVar) {
        ArrayList arrayList = this.o.j;
        if (arrayList.contains(jmVar)) {
            return;
        }
        arrayList.add(jmVar);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            canvas.save();
            wl wlVar = this.m;
            Rect bounds = getBounds();
            float fB = b();
            wlVar.a.a();
            wlVar.a(canvas, bounds, fB);
            wl wlVar2 = this.m;
            Paint paint = this.j;
            wlVar2.c(canvas, paint);
            this.m.b(canvas, paint, 0.0f, this.p, k9.j(this.c.c[0], this.k));
            canvas.restore();
        }
    }

    @Override // defpackage.nl
    public final boolean f(boolean z, boolean z2, boolean z3) {
        boolean zF = super.f(z, z2, z3);
        u3 u3Var = this.d;
        ContentResolver contentResolver = this.b.getContentResolver();
        u3Var.getClass();
        float f = Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
        if (f == 0.0f) {
            this.q = true;
            return zF;
        }
        this.q = false;
        float f2 = 50.0f / f;
        gk0 gk0Var = this.n;
        gk0Var.getClass();
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        gk0Var.a = Math.sqrt(f2);
        gk0Var.c = false;
        return zF;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.m.d();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.m.e();
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        this.o.b();
        this.p = getLevel() / 10000.0f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i) {
        boolean z = this.q;
        fk0 fk0Var = this.o;
        if (z) {
            fk0Var.b();
            this.p = i / 10000.0f;
            invalidateSelf();
            return true;
        }
        fk0Var.b = this.p * 10000.0f;
        fk0Var.c = true;
        float f = i;
        if (fk0Var.f) {
            fk0Var.s = f;
            return true;
        }
        if (fk0Var.r == null) {
            fk0Var.r = new gk0(f);
        }
        gk0 gk0Var = fk0Var.r;
        double d = f;
        gk0Var.i = d;
        double d2 = (float) d;
        if (d2 > Float.MAX_VALUE) {
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        float f2 = fk0Var.g;
        if (d2 < f2) {
            throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
        }
        double dAbs = Math.abs(fk0Var.i * 0.75f);
        gk0Var.d = dAbs;
        gk0Var.e = dAbs * 62.5d;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        boolean z2 = fk0Var.f;
        if (!z2 && !z2) {
            fk0Var.f = true;
            if (!fk0Var.c) {
                fk0Var.e.getClass();
                fk0Var.b = fk0Var.d.p * 10000.0f;
            }
            float f3 = fk0Var.b;
            if (f3 > Float.MAX_VALUE || f3 < f2) {
                throw new IllegalArgumentException("Starting value need to be in between min value and max value");
            }
            ThreadLocal threadLocal = s3.f;
            if (threadLocal.get() == null) {
                threadLocal.set(new s3());
            }
            s3 s3Var = (s3) threadLocal.get();
            ArrayList arrayList = s3Var.b;
            if (arrayList.size() == 0) {
                if (s3Var.d == null) {
                    s3Var.d = new po(s3Var.c);
                }
                po poVar = s3Var.d;
                ((Choreographer) poVar.d).postFrameCallback((r3) poVar.e);
            }
            if (!arrayList.contains(fk0Var)) {
                arrayList.add(fk0Var);
                return true;
            }
        }
        return true;
    }

    public void removeSpringAnimationEndListener(jm jmVar) {
        this.o.removeEndListener(jmVar);
    }
}
