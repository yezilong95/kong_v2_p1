package defpackage;

import android.graphics.Path;
import androidx.transition.PathMotion;

/* JADX INFO: loaded from: classes.dex */
public final class ep0 extends PathMotion {
    public final Path a(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(f, f2);
        path.lineTo(f3, f4);
        return path;
    }
}
