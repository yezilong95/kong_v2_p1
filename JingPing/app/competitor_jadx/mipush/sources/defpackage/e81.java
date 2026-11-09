package defpackage;

import android.os.Build;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public final class e81 {
    public d81 a;

    public e81(int i, Interpolator interpolator, long j) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.a = new c81(b2.l(i, interpolator, j));
        } else {
            this.a = new a81(i, interpolator, j);
        }
    }
}
