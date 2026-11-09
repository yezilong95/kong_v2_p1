package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transformation.ExpandableBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class wo implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ View b;
    public final /* synthetic */ int c;
    public final /* synthetic */ xo d;
    public final /* synthetic */ ExpandableBehavior e;

    public wo(ExpandableBehavior expandableBehavior, View view, int i, xo xoVar) {
        this.e = expandableBehavior;
        this.b = view;
        this.c = i;
        this.d = xoVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        View view = this.b;
        view.getViewTreeObserver().removeOnPreDrawListener(this);
        ExpandableBehavior expandableBehavior = this.e;
        if (expandableBehavior.b == this.c) {
            Object obj = this.d;
            expandableBehavior.w((View) obj, view, ((FloatingActionButton) obj).p.a, false);
        }
        return false;
    }
}
