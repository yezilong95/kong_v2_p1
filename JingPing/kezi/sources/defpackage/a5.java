package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class a5 {
    public static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    public static a5 c;
    public ve0 a;

    public static synchronized a5 a() {
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
        return ve0.e(i, mode);
    }

    public static synchronized void d() {
        if (c == null) {
            a5 a5Var = new a5();
            c = a5Var;
            a5Var.a = ve0.b();
            ve0 ve0Var = c.a;
            z4 z4Var = new z4();
            synchronized (ve0Var) {
                ve0Var.e = z4Var;
            }
        }
    }

    public static void e(Drawable drawable, qf qfVar, int[] iArr) {
        PorterDuff.Mode mode = ve0.f;
        int[] state = drawable.getState();
        int[] iArr2 = ll.a;
        if (drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
            drawable.setState(new int[0]);
            drawable.setState(state);
        }
        boolean z = qfVar.b;
        if (!z && !qfVar.a) {
            drawable.clearColorFilter();
            return;
        }
        PorterDuffColorFilter porterDuffColorFilterE = null;
        ColorStateList colorStateList = z ? (ColorStateList) qfVar.c : null;
        PorterDuff.Mode mode2 = qfVar.a ? (PorterDuff.Mode) qfVar.d : ve0.f;
        if (colorStateList != null && mode2 != null) {
            porterDuffColorFilterE = ve0.e(colorStateList.getColorForState(iArr, 0), mode2);
        }
        drawable.setColorFilter(porterDuffColorFilterE);
    }

    public final synchronized Drawable b(Context context, int i) {
        return this.a.c(context, i);
    }
}
