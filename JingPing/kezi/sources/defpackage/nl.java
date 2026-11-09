package defpackage;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class nl extends Drawable implements Animatable {
    public static final fb l = new fb(Float.class, "growFraction", 10);
    public final Context b;
    public final e8 c;
    public ObjectAnimator e;
    public ObjectAnimator f;
    public ArrayList g;
    public boolean h;
    public float i;
    public int k;
    public final Paint j = new Paint();
    public u3 d = new u3();

    public nl(Context context, e8 e8Var) {
        this.b = context;
        this.c = e8Var;
        setAlpha(255);
    }

    public final float b() {
        e8 e8Var = this.c;
        if (e8Var.e == 0 && e8Var.f == 0) {
            return 1.0f;
        }
        return this.i;
    }

    public final boolean c() {
        ObjectAnimator objectAnimator = this.f;
        return objectAnimator != null && objectAnimator.isRunning();
    }

    public final boolean d() {
        ObjectAnimator objectAnimator = this.e;
        return objectAnimator != null && objectAnimator.isRunning();
    }

    public final boolean e(boolean z, boolean z2, boolean z3) {
        u3 u3Var = this.d;
        ContentResolver contentResolver = this.b.getContentResolver();
        u3Var.getClass();
        return f(z, z2, z3 && Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) > 0.0f);
    }

    public boolean f(boolean z, boolean z2, boolean z3) {
        int i = 1;
        int i2 = 0;
        ObjectAnimator objectAnimator = this.e;
        fb fbVar = l;
        if (objectAnimator == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, fbVar, 0.0f, 1.0f);
            this.e = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(500L);
            this.e.setInterpolator(t3.b);
            ObjectAnimator objectAnimator2 = this.e;
            if (objectAnimator2 != null && objectAnimator2.isRunning()) {
                throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
            }
            this.e = objectAnimator2;
            objectAnimator2.addListener(new ml(this, i2));
        }
        if (this.f == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, fbVar, 1.0f, 0.0f);
            this.f = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(500L);
            this.f.setInterpolator(t3.b);
            ObjectAnimator objectAnimator3 = this.f;
            if (objectAnimator3 != null && objectAnimator3.isRunning()) {
                throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
            }
            this.f = objectAnimator3;
            objectAnimator3.addListener(new ml(this, i));
        }
        if (isVisible() || z) {
            ObjectAnimator objectAnimator4 = z ? this.e : this.f;
            ObjectAnimator objectAnimator5 = z ? this.f : this.e;
            if (!z3) {
                if (objectAnimator5.isRunning()) {
                    boolean z4 = this.h;
                    this.h = true;
                    new ValueAnimator[]{objectAnimator5}[0].cancel();
                    this.h = z4;
                }
                if (objectAnimator4.isRunning()) {
                    objectAnimator4.end();
                } else {
                    boolean z5 = this.h;
                    this.h = true;
                    new ValueAnimator[]{objectAnimator4}[0].end();
                    this.h = z5;
                }
                return super.setVisible(z, false);
            }
            if (!z3 || !objectAnimator4.isRunning()) {
                boolean z6 = !z || super.setVisible(z, false);
                e8 e8Var = this.c;
                if (!z ? e8Var.f != 0 : e8Var.e != 0) {
                    boolean z7 = this.h;
                    this.h = true;
                    new ValueAnimator[]{objectAnimator4}[0].end();
                    this.h = z7;
                    return z6;
                }
                if (z2 || !objectAnimator4.isPaused()) {
                    objectAnimator4.start();
                    return z6;
                }
                objectAnimator4.resume();
                return z6;
            }
        }
        return false;
    }

    public final void g(d8 d8Var) {
        ArrayList arrayList = this.g;
        if (arrayList == null || !arrayList.contains(d8Var)) {
            return;
        }
        this.g.remove(d8Var);
        if (this.g.isEmpty()) {
            this.g = null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.k;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return d() || c();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.k = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.j.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        return e(z, z2, true);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        f(true, true, false);
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        f(false, true, false);
    }
}
