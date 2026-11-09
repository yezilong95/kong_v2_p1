package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class bf implements yi {
    public final float a;

    public bf(float f) {
        this.a = f;
    }

    @Override // defpackage.yi
    public final float a(RectF rectF) {
        return Math.min(this.a, Math.min(rectF.width() / 2.0f, rectF.height() / 2.0f));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof bf) && this.a == ((bf) obj).a;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.a)});
    }
}
