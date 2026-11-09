package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class f6 {
    public static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    public static f6 c;
    public uo0 a;

    public static synchronized f6 a() {
        try {
            if (c == null) {
                d();
            }
        } catch (Throwable th) {
            throw th;
        }
        return c;
    }

    public static synchronized PorterDuffColorFilter c(int i, PorterDuff.Mode mode) {
        return uo0.e(i, mode);
    }

    public static synchronized void d() {
        if (c == null) {
            f6 f6Var = new f6();
            c = f6Var;
            f6Var.a = uo0.b();
            uo0 uo0Var = c.a;
            e6 e6Var = new e6();
            synchronized (uo0Var) {
                uo0Var.e = e6Var;
            }
        }
    }

    public static void e(Drawable drawable, x01 x01Var, int[] iArr) {
        PorterDuff.Mode mode = uo0.f;
        int[] state = drawable.getState();
        int[] iArr2 = op.a;
        if (drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
            drawable.setState(new int[0]);
            drawable.setState(state);
        }
        boolean z = x01Var.d;
        if (!z && !x01Var.c) {
            drawable.clearColorFilter();
            return;
        }
        PorterDuffColorFilter porterDuffColorFilterE = null;
        ColorStateList colorStateList = z ? x01Var.a : null;
        PorterDuff.Mode mode2 = x01Var.c ? x01Var.b : uo0.f;
        if (colorStateList != null && mode2 != null) {
            porterDuffColorFilterE = uo0.e(colorStateList.getColorForState(iArr, 0), mode2);
        }
        drawable.setColorFilter(porterDuffColorFilterE);
    }

    public final synchronized Drawable b(Context context, int i) {
        return this.a.c(context, i);
    }
}
