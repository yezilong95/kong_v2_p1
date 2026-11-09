package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class xa0 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public final View b;
    public ViewTreeObserver c;
    public final Runnable d;

    public xa0(View view, Runnable runnable) {
        this.b = view;
        this.c = view.getViewTreeObserver();
        this.d = runnable;
    }

    public static void a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        xa0 xa0Var = new xa0(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(xa0Var);
        view.addOnAttachStateChangeListener(xa0Var);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        boolean zIsAlive = this.c.isAlive();
        View view = this.b;
        if (zIsAlive) {
            this.c.removeOnPreDrawListener(this);
        } else {
            view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view.removeOnAttachStateChangeListener(this);
        this.d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        boolean zIsAlive = this.c.isAlive();
        View view2 = this.b;
        if (zIsAlive) {
            this.c.removeOnPreDrawListener(this);
        } else {
            view2.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view2.removeOnAttachStateChangeListener(this);
    }
}
