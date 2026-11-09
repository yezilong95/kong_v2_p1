package defpackage;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class k3 extends Animatable2.AnimationCallback {
    public final /* synthetic */ d8 a;

    public k3(d8 d8Var) {
        this.a = d8Var;
    }

    @Override // android.graphics.drawable.Animatable2.AnimationCallback
    public final void onAnimationEnd(Drawable drawable) {
        this.a.a(drawable);
    }

    @Override // android.graphics.drawable.Animatable2.AnimationCallback
    public final void onAnimationStart(Drawable drawable) {
        this.a.b(drawable);
    }
}
