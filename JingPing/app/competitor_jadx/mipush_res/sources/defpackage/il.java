package defpackage;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class il {
    public final byte[] a;
    public final int b;

    public il(byte[] bArr, int i) {
        this.a = z91.l(bArr);
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof il)) {
            return false;
        }
        il ilVar = (il) obj;
        if (ilVar.b != this.b) {
            return false;
        }
        return Arrays.equals(this.a, ilVar.a);
    }

    public final int hashCode() {
        return z91.E(this.a) ^ this.b;
    }
}
