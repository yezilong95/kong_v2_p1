package defpackage;

import android.app.ApplicationExitInfo;
import android.graphics.Insets;
import android.view.WindowInsetsAnimation;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class g0 {
    public static /* bridge */ /* synthetic */ ApplicationExitInfo g(Object obj) {
        return (ApplicationExitInfo) obj;
    }

    public static /* synthetic */ WindowInsetsAnimation.Bounds k(Insets insets, Insets insets2) {
        return new WindowInsetsAnimation.Bounds(insets, insets2);
    }

    public static /* synthetic */ WindowInsetsAnimation l(int i, Interpolator interpolator, long j) {
        return new WindowInsetsAnimation(i, interpolator, j);
    }

    public static /* bridge */ /* synthetic */ WindowInsetsAnimation m(Object obj) {
        return (WindowInsetsAnimation) obj;
    }

    public static /* synthetic */ void q() {
    }
}
