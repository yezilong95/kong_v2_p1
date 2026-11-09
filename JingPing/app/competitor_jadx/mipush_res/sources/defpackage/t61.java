package defpackage;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class t61 {
    public static void a(View view, Matrix matrix) {
        view.setAnimationMatrix(matrix);
    }

    public static void b(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    public static void c(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
