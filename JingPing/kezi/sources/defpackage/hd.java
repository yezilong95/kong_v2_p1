package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class hd implements kh {
    public final float a;

    public hd(float f) {
        this.a = f;
    }

    @Override // defpackage.kh
    public final float a(RectF rectF) {
        return Math.min(this.a, Math.min(rectF.width() / 2.0f, rectF.height() / 2.0f));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof hd) && this.a == ((hd) obj).a;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.a)});
    }
}
