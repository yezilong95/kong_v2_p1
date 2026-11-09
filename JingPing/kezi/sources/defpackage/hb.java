package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class hb extends AnimatorListenerAdapter {
    public boolean a;
    public final /* synthetic */ View b;
    public final /* synthetic */ Rect c;
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    public hb(View view, Rect rect, int i, int i2, int i3, int i4) {
        this.b = view;
        this.c = rect;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = i4;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.a) {
            return;
        }
        WeakHashMap weakHashMap = xr0.a;
        Rect rect = this.c;
        View view = this.b;
        view.setClipBounds(rect);
        jt0.a(view, this.d, this.e, this.f, this.g);
    }
}
