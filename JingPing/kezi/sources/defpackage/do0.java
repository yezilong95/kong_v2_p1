package defpackage;

import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class do0 extends eo0 {
    public boolean g;

    @Override // defpackage.eo0
    public final boolean b(float f, long j, o70 o70Var, View view) {
        Method method;
        do0 do0Var;
        if (view instanceof MotionLayout) {
            float fA = a(f, j, o70Var, view);
            do0Var = this;
            ((MotionLayout) view).setProgress(fA);
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
                    float fA2 = a(f, j, o70Var, view);
                    do0Var = this;
                    try {
                        method.invoke(view, Float.valueOf(fA2));
                    } catch (IllegalAccessException e) {
                        e = e;
                        Log.e("SplineSet", "unable to setProgress", e);
                    } catch (InvocationTargetException e2) {
                        e = e2;
                        Log.e("SplineSet", "unable to setProgress", e);
                    }
                } catch (IllegalAccessException e3) {
                    e = e3;
                    do0Var = this;
                } catch (InvocationTargetException e4) {
                    e = e4;
                    do0Var = this;
                }
            } else {
                do0Var = this;
            }
        }
        return do0Var.d;
    }
}
