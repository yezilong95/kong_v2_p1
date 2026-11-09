package defpackage;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class mt0 extends lt0 {
    @Override // defpackage.jq0
    public final float F(View view) {
        return view.getTransitionAlpha();
    }

    @Override // defpackage.jq0
    public final void e0(View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // defpackage.lt0, defpackage.jq0
    public final void f0(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // defpackage.lt0
    public final void u0(View view, Matrix matrix) {
        view.setAnimationMatrix(matrix);
    }

    @Override // defpackage.lt0
    public final void v0(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // defpackage.lt0
    public final void w0(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // defpackage.lt0
    public final void x0(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
