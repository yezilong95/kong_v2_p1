package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class d4 implements yi {
    public final yi a;
    public final float b;

    public d4(float f, yi yiVar) {
        while (yiVar instanceof d4) {
            yiVar = ((d4) yiVar).a;
            f += ((d4) yiVar).b;
        }
        this.a = yiVar;
        this.b = f;
    }

    @Override // defpackage.yi
    public final float a(RectF rectF) {
        return Math.max(0.0f, this.a.a(rectF) + this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d4)) {
            return false;
        }
        d4 d4Var = (d4) obj;
        return this.a.equals(d4Var.a) && this.b == d4Var.b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, Float.valueOf(this.b)});
    }
}
