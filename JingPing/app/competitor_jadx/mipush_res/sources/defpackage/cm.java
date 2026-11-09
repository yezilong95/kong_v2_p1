package defpackage;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class cm {
    public final byte[] a;
    public final int b;

    public cm(byte[] bArr, int i) {
        this.a = z91.l(bArr);
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof cm)) {
            return false;
        }
        cm cmVar = (cm) obj;
        if (cmVar.b != this.b) {
            return false;
        }
        return Arrays.equals(this.a, cmVar.a);
    }

    public final int hashCode() {
        return z91.E(this.a) ^ this.b;
    }
}
