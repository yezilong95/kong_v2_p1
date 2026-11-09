package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

/* JADX INFO: loaded from: classes.dex */
public final class xz extends AnimationSet implements Runnable {
    public final ViewGroup E;
    public final View F;
    public boolean G;
    public boolean H;
    public boolean I;

    public xz(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.I = true;
        this.E = viewGroup;
        this.F = view;
        addAnimation(animation);
        viewGroup.post(this);
    }

    @Override // android.view.animation.AnimationSet, android.view.animation.Animation
    public final boolean getTransformation(long j, Transformation transformation) {
        this.I = true;
        if (this.G) {
            return !this.H;
        }
        if (!super.getTransformation(j, transformation)) {
            this.G = true;
            mj0.a(this.E, this);
        }
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = this.G;
        ViewGroup viewGroup = this.E;
        if (z || !this.I) {
            viewGroup.endViewTransition(this.F);
            this.H = true;
        } else {
            this.I = false;
            viewGroup.post(this);
        }
    }

    @Override // android.view.animation.Animation
    public final boolean getTransformation(long j, Transformation transformation, float f) {
        this.I = true;
        if (this.G) {
            return !this.H;
        }
        if (!super.getTransformation(j, transformation, f)) {
            this.G = true;
            mj0.a(this.E, this);
        }
        return true;
    }
}
