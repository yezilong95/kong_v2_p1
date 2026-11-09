package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class jt0 {
    public static final lt0 a;
    public static final fb b;
    public static final fb c;

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            a = new mt0();
        } else {
            a = new lt0();
        }
        b = new fb(Float.class, "translationAlpha", 18);
        c = new fb(Rect.class, "clipBounds", 19);
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        a.v0(view, i, i2, i3, i4);
    }

    public static void b(View view, float f) {
        a.e0(view, f);
    }

    public static void c(View view, int i) {
        a.f0(view, i);
    }
}
