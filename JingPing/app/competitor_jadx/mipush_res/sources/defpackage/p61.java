package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class p61 {
    public static final w61 a;
    public static final yc b;
    public static final yc c;

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            a = new x61();
        } else {
            a = new w61();
        }
        b = new yc(Float.class, "translationAlpha", 18);
        c = new yc(Rect.class, "clipBounds", 19);
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        a.o0(view, i, i2, i3, i4);
    }

    public static void b(View view, float f) {
        a.f0(view, f);
    }

    public static void c(View view, int i) {
        a.g0(view, i);
    }
}
