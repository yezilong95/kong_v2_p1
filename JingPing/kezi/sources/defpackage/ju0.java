package defpackage;

import android.os.Build;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public final class ju0 {
    public iu0 a;

    public ju0(int i, Interpolator interpolator, long j) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.a = new hu0(g0.l(i, interpolator, j));
        } else {
            this.a = new fu0(i, interpolator, j);
        }
    }
}
