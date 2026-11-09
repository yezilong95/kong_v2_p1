package defpackage;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class w61 extends la0 {
    public static boolean k0 = true;
    public static boolean l0 = true;
    public static boolean m0 = true;
    public static boolean n0 = true;
    public static boolean o0 = true;

    @Override // defpackage.la0
    public void g0(View view, int i) {
        if (Build.VERSION.SDK_INT == 28) {
            super.g0(view, i);
        } else if (o0) {
            try {
                v61.a(view, i);
            } catch (NoSuchMethodError unused) {
                o0 = false;
            }
        }
    }

    public void n0(View view, Matrix matrix) {
        if (k0) {
            try {
                t61.a(view, matrix);
            } catch (NoSuchMethodError unused) {
                k0 = false;
            }
        }
    }

    public void o0(View view, int i, int i2, int i3, int i4) {
        if (n0) {
            try {
                u61.a(view, i, i2, i3, i4);
            } catch (NoSuchMethodError unused) {
                n0 = false;
            }
        }
    }

    public void p0(View view, Matrix matrix) {
        if (l0) {
            try {
                t61.b(view, matrix);
            } catch (NoSuchMethodError unused) {
                l0 = false;
            }
        }
    }

    public void q0(View view, Matrix matrix) {
        if (m0) {
            try {
                t61.c(view, matrix);
            } catch (NoSuchMethodError unused) {
                m0 = false;
            }
        }
    }
}
