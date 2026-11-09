package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public abstract class z3 {
    /* JADX INFO: Access modifiers changed from: private */
    public static ColorStateList b(Drawable drawable) {
        if (f0.r(drawable)) {
            return f0.c(drawable).getColorStateList();
        }
        return null;
    }
}
