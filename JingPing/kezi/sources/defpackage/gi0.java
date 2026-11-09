package defpackage;

import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class gi0 {
    public int a;
    public final int[] b = new int[10];

    public final int a() {
        if ((this.a & Uuid.SIZE_BITS) != 0) {
            return this.b[7];
        }
        return 65535;
    }

    public final void b(gi0 gi0Var) {
        oy.j(gi0Var, "other");
        for (int i = 0; i < 10; i++) {
            if (((1 << i) & gi0Var.a) != 0) {
                c(i, gi0Var.b[i]);
            }
        }
    }

    public final void c(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.b;
            if (i >= iArr.length) {
                return;
            }
            this.a = (1 << i) | this.a;
            iArr[i] = i2;
        }
    }
}
