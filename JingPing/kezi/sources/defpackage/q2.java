package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class q2 implements kh {
    public final kh a;
    public final float b;

    public q2(float f, kh khVar) {
        while (khVar instanceof q2) {
            khVar = ((q2) khVar).a;
            f += ((q2) khVar).b;
        }
        this.a = khVar;
        this.b = f;
    }

    @Override // defpackage.kh
    public final float a(RectF rectF) {
        return Math.max(0.0f, this.a.a(rectF) + this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q2)) {
            return false;
        }
        q2 q2Var = (q2) obj;
        return this.a.equals(q2Var.a) && this.b == q2Var.b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, Float.valueOf(this.b)});
    }
}
