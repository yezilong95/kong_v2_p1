package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class mj0 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public final View E;
    public ViewTreeObserver F;
    public final Runnable G;

    public mj0(View view, Runnable runnable) {
        this.E = view;
        this.F = view.getViewTreeObserver();
        this.G = runnable;
    }

    public static void a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        mj0 mj0Var = new mj0(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(mj0Var);
        view.addOnAttachStateChangeListener(mj0Var);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        boolean zIsAlive = this.F.isAlive();
        View view = this.E;
        if (zIsAlive) {
            this.F.removeOnPreDrawListener(this);
        } else {
            view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view.removeOnAttachStateChangeListener(this);
        this.G.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.F = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        boolean zIsAlive = this.F.isAlive();
        View view2 = this.E;
        if (zIsAlive) {
            this.F.removeOnPreDrawListener(this);
        } else {
            view2.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view2.removeOnAttachStateChangeListener(this);
    }
}
