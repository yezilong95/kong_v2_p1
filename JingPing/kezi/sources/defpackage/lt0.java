package defpackage;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class lt0 extends jq0 {
    public static boolean s = true;
    public static boolean t = true;
    public static boolean u = true;
    public static boolean v = true;
    public static boolean w = true;

    @Override // defpackage.jq0
    public void f0(View view, int i) {
        if (Build.VERSION.SDK_INT == 28) {
            super.f0(view, i);
        } else if (w) {
            try {
                view.setTransitionVisibility(i);
            } catch (NoSuchMethodError unused) {
                w = false;
            }
        }
    }

    public void u0(View view, Matrix matrix) {
        if (s) {
            try {
                view.setAnimationMatrix(matrix);
            } catch (NoSuchMethodError unused) {
                s = false;
            }
        }
    }

    public void v0(View view, int i, int i2, int i3, int i4) {
        if (v) {
            try {
                view.setLeftTopRightBottom(i, i2, i3, i4);
            } catch (NoSuchMethodError unused) {
                v = false;
            }
        }
    }

    public void w0(View view, Matrix matrix) {
        if (t) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                t = false;
            }
        }
    }

    public void x0(View view, Matrix matrix) {
        if (u) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                u = false;
            }
        }
    }
}
