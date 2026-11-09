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
public abstract class qp extends Drawable implements Animatable {
    public static final yc O = new yc(Float.class, "growFraction", 10);
    public final Context E;
    public final aa F;
    public ObjectAnimator H;
    public ObjectAnimator I;
    public ArrayList J;
    public boolean K;
    public float L;
    public int N;
    public final Paint M = new Paint();
    public z4 G = new z4();

    public qp(Context context, aa aaVar) {
        this.E = context;
        this.F = aaVar;
        setAlpha(255);
    }

    public final float b() {
        aa aaVar = this.F;
        if (aaVar.e == 0 && aaVar.f == 0) {
            return 1.0f;
        }
        return this.L;
    }

    public final boolean c(boolean z, boolean z2, boolean z3) {
        z4 z4Var = this.G;
        ContentResolver contentResolver = this.E.getContentResolver();
        z4Var.getClass();
        return d(z, z2, z3 && Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) > 0.0f);
    }

    public boolean d(boolean z, boolean z2, boolean z3) {
        int i = 1;
        int i2 = 0;
        ObjectAnimator objectAnimator = this.H;
        yc ycVar = O;
        if (objectAnimator == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, ycVar, 0.0f, 1.0f);
            this.H = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(500L);
            this.H.setInterpolator(y4.b);
            ObjectAnimator objectAnimator2 = this.H;
            if (objectAnimator2 != null && objectAnimator2.isRunning()) {
                throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
            }
            this.H = objectAnimator2;
            objectAnimator2.addListener(new pp(this, i2));
        }
        if (this.I == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, ycVar, 1.0f, 0.0f);
            this.I = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(500L);
            this.I.setInterpolator(y4.b);
            ObjectAnimator objectAnimator3 = this.I;
            if (objectAnimator3 != null && objectAnimator3.isRunning()) {
                throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
            }
            this.I = objectAnimator3;
            objectAnimator3.addListener(new pp(this, i));
        }
        if (isVisible() || z) {
            ObjectAnimator objectAnimator4 = z ? this.H : this.I;
            ObjectAnimator objectAnimator5 = z ? this.I : this.H;
            if (!z3) {
                if (objectAnimator5.isRunning()) {
                    boolean z4 = this.K;
                    this.K = true;
                    new ValueAnimator[]{objectAnimator5}[0].cancel();
                    this.K = z4;
                }
                if (objectAnimator4.isRunning()) {
                    objectAnimator4.end();
                } else {
                    boolean z5 = this.K;
                    this.K = true;
                    new ValueAnimator[]{objectAnimator4}[0].end();
                    this.K = z5;
                }
                return super.setVisible(z, false);
            }
            if (!objectAnimator4.isRunning()) {
                boolean z6 = !z || super.setVisible(z, false);
                aa aaVar = this.F;
                if (!z ? aaVar.f != 0 : aaVar.e != 0) {
                    boolean z7 = this.K;
                    this.K = true;
                    new ValueAnimator[]{objectAnimator4}[0].end();
                    this.K = z7;
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

    public final void e(z9 z9Var) {
        ArrayList arrayList = this.J;
        if (arrayList == null || !arrayList.contains(z9Var)) {
            return;
        }
        this.J.remove(z9Var);
        if (this.J.isEmpty()) {
            this.J = null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.N;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        ObjectAnimator objectAnimator = this.H;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            return true;
        }
        ObjectAnimator objectAnimator2 = this.I;
        return objectAnimator2 != null && objectAnimator2.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.N = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.M.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        return c(z, z2, true);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        d(true, true, false);
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        d(false, true, false);
    }
}
