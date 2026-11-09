package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class il0 implements ew {
    public final BigInteger a;

    public il0(BigInteger bigInteger) {
        this.a = bigInteger;
    }

    @Override // defpackage.ew
    public final int a() {
        return 1;
    }

    @Override // defpackage.ew
    public final BigInteger b() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof il0) {
            return this.a.equals(((il0) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
