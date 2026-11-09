package defpackage;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* JADX INFO: loaded from: classes.dex */
public abstract class y4 {
    public static final LinearInterpolator a = new LinearInterpolator();
    public static final sv b = new sv(1);
    public static final sv c = new sv(0);
    public static final sv d = new sv(sv.e);
    public static final DecelerateInterpolator e = new DecelerateInterpolator();

    public static float a(float f, float f2, float f3) {
        return zn.d(f2, f, f3, f);
    }

    public static float b(float f, float f2, float f3, float f4, float f5) {
        return f5 <= f3 ? f : f5 >= f4 ? f2 : a(f, f2, (f5 - f3) / (f4 - f3));
    }

    public static int c(int i, float f, int i2) {
        return Math.round(f * (i2 - i)) + i;
    }
}
