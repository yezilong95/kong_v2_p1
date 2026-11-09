package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.R$id;

/* JADX INFO: loaded from: classes.dex */
public final class hv extends ViewGroup implements ev {
    public static final /* synthetic */ int h = 0;
    public ViewGroup b;
    public View c;
    public final View d;
    public int e;
    public Matrix f;
    public final jh g;

    public hv(View view) {
        super(view.getContext());
        this.g = new jh(2, this);
        this.d = view;
        setWillNotDraw(false);
        setLayerType(2, null);
    }

    @Override // defpackage.ev
    public final void a(ViewGroup viewGroup, View view) {
        this.b = viewGroup;
        this.c = view;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i = R$id.ghost_view;
        View view = this.d;
        view.setTag(i, this);
        view.getViewTreeObserver().addOnPreDrawListener(this.g);
        jt0.c(view, 4);
        if (view.getParent() != null) {
            ((View) view.getParent()).invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        View view = this.d;
        view.getViewTreeObserver().removeOnPreDrawListener(this.g);
        jt0.c(view, 0);
        view.setTag(R$id.ghost_view, null);
        if (view.getParent() != null) {
            ((View) view.getParent()).invalidate();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        k9.m(canvas, true);
        canvas.setMatrix(this.f);
        View view = this.d;
        jt0.c(view, 0);
        view.invalidate();
        jt0.c(view, 4);
        drawChild(canvas, view, getDrawingTime());
        k9.m(canvas, false);
    }

    @Override // android.view.View, defpackage.ev
    public final void setVisibility(int i) {
        super.setVisibility(i);
        int i2 = R$id.ghost_view;
        View view = this.d;
        if (((hv) view.getTag(i2)) == this) {
            jt0.c(view, i == 0 ? 4 : 0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }
}
