package defpackage;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class c0 {
    public final int a;
    public final byte[] b;

    public c0(byte[] bArr) {
        this.a = z91.E(bArr);
        this.b = bArr;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c0)) {
            return false;
        }
        return Arrays.equals(this.b, ((c0) obj).b);
    }

    public final int hashCode() {
        return this.a;
    }
}
