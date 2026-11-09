package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transformation.ExpandableBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class tu implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ View E;
    public final /* synthetic */ int F;
    public final /* synthetic */ uu G;
    public final /* synthetic */ ExpandableBehavior H;

    public tu(ExpandableBehavior expandableBehavior, View view, int i, uu uuVar) {
        this.H = expandableBehavior;
        this.E = view;
        this.F = i;
        this.G = uuVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        View view = this.E;
        view.getViewTreeObserver().removeOnPreDrawListener(this);
        ExpandableBehavior expandableBehavior = this.H;
        if (expandableBehavior.E == this.F) {
            Object obj = this.G;
            expandableBehavior.w((View) obj, view, ((FloatingActionButton) obj).S.a, false);
        }
        return false;
    }
}
