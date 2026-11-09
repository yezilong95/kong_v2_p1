package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.R$id;

/* JADX INFO: loaded from: classes.dex */
public final class h20 extends ViewGroup implements e20 {
    public static final /* synthetic */ int K = 0;
    public ViewGroup E;
    public View F;
    public final View G;
    public int H;
    public Matrix I;
    public final xi J;

    public h20(View view) {
        super(view.getContext());
        this.J = new xi(2, this);
        this.G = view;
        setWillNotDraw(false);
        setClipChildren(false);
        setLayerType(2, null);
    }

    @Override // defpackage.e20
    public final void e(ViewGroup viewGroup, View view) {
        this.E = viewGroup;
        this.F = view;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i = R$id.ghost_view;
        View view = this.G;
        view.setTag(i, this);
        view.getViewTreeObserver().addOnPreDrawListener(this.J);
        p61.c(view, 4);
        if (view.getParent() != null) {
            ((View) view.getParent()).invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        View view = this.G;
        view.getViewTreeObserver().removeOnPreDrawListener(this.J);
        p61.c(view, 0);
        view.setTag(R$id.ghost_view, null);
        if (view.getParent() != null) {
            ((View) view.getParent()).invalidate();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        qm.l(canvas, true);
        canvas.setMatrix(this.I);
        View view = this.G;
        p61.c(view, 0);
        view.invalidate();
        p61.c(view, 4);
        drawChild(canvas, view, getDrawingTime());
        qm.l(canvas, false);
    }

    @Override // android.view.View, defpackage.e20
    public final void setVisibility(int i) {
        super.setVisibility(i);
        int i2 = R$id.ghost_view;
        View view = this.G;
        if (((h20) view.getTag(i2)) == this) {
            p61.c(view, i == 0 ? 4 : 0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }
}
