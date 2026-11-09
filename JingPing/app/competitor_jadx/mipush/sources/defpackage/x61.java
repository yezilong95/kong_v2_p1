package defpackage;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class x61 extends w61 {
    @Override // defpackage.la0
    public final float H(View view) {
        return view.getTransitionAlpha();
    }

    @Override // defpackage.la0
    public final void f0(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // defpackage.w61, defpackage.la0
    public final void g0(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // defpackage.w61
    public final void n0(View view, Matrix matrix) {
        view.setAnimationMatrix(matrix);
    }

    @Override // defpackage.w61
    public final void o0(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // defpackage.w61
    public final void p0(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // defpackage.w61
    public final void q0(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
