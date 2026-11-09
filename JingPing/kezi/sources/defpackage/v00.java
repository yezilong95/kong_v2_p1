package defpackage;

import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class v00 extends w00 {
    public boolean d;

    @Override // defpackage.w00
    public final void c(View view, float f) {
        Method method;
        if (view instanceof MotionLayout) {
            ((MotionLayout) view).setProgress(a(f));
            return;
        }
        if (this.d) {
            return;
        }
        try {
            method = view.getClass().getMethod("setProgress", Float.TYPE);
        } catch (NoSuchMethodException unused) {
            this.d = true;
            method = null;
        }
        if (method != null) {
            try {
                method.invoke(view, Float.valueOf(a(f)));
            } catch (IllegalAccessException e) {
                Log.e("KeyCycleOscillator", "unable to setProgress", e);
            } catch (InvocationTargetException e2) {
                Log.e("KeyCycleOscillator", "unable to setProgress", e2);
            }
        }
    }
}
