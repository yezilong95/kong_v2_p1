package defpackage;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public abstract class jp {
    public static int a(Drawable drawable) {
        return drawable.getLayoutDirection();
    }

    public static boolean b(Drawable drawable, int i) {
        return drawable.setLayoutDirection(i);
    }
}
