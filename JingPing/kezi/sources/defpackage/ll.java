package defpackage;

import android.R;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.work.impl.WorkDatabaseVersions;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public abstract class ll {
    public static final int[] a = {R.attr.state_checked};
    public static final int[] b = new int[0];
    public static final Rect c = new Rect();

    public static void a(Drawable drawable) {
        String name = drawable.getClass().getName();
        int i = Build.VERSION.SDK_INT;
        if (i < 29 || i >= 31 || !"android.graphics.drawable.ColorStateListDrawable".equals(name)) {
            return;
        }
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(a);
        } else {
            drawable.setState(b);
        }
        drawable.setState(state);
    }

    public static Rect b(Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            Insets insetsA = kl.a(drawable);
            return new Rect(insetsA.left, insetsA.top, insetsA.right, insetsA.bottom);
        }
        Drawable drawableQ = nv0.Q(drawable);
        if (i >= 29) {
            boolean z = jl.a;
        } else if (jl.a) {
            try {
                Object objInvoke = jl.b.invoke(drawableQ, new Object[0]);
                if (objInvoke != null) {
                    return new Rect(jl.c.getInt(objInvoke), jl.d.getInt(objInvoke), jl.e.getInt(objInvoke), jl.f.getInt(objInvoke));
                }
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
        return c;
    }

    public static PorterDuff.Mode c(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return PorterDuff.Mode.MULTIPLY;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
