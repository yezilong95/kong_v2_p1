package defpackage;

import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class r01 extends s01 {
    public boolean g;

    @Override // defpackage.s01
    public final boolean b(float f, long j, z8 z8Var, View view) {
        Method method;
        r01 r01Var;
        float fA;
        if (view instanceof MotionLayout) {
            float fA2 = a(f, j, z8Var, view);
            r01Var = this;
            ((MotionLayout) view).setProgress(fA2);
        } else {
            if (this.g) {
                return false;
            }
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.g = true;
                method = null;
            }
            if (method != null) {
                try {
                    fA = a(f, j, z8Var, view);
                    r01Var = this;
                } catch (IllegalAccessException e) {
                    e = e;
                    r01Var = this;
                } catch (InvocationTargetException e2) {
                    e = e2;
                    r01Var = this;
                }
                try {
                    method.invoke(view, Float.valueOf(fA));
                } catch (IllegalAccessException e3) {
                    e = e3;
                    Log.e("SplineSet", "unable to setProgress", e);
                } catch (InvocationTargetException e4) {
                    e = e4;
                    Log.e("SplineSet", "unable to setProgress", e);
                }
            } else {
                r01Var = this;
            }
        }
        return r01Var.d;
    }
}
