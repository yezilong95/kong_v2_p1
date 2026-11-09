package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class d20 implements ew {
    public final il0 a;
    public final l10 b;

    public d20(il0 il0Var, l10 l10Var) {
        this.a = il0Var;
        this.b = l10Var;
    }

    @Override // defpackage.ew
    public final int a() {
        this.a.getClass();
        return this.b.a[r0.length - 1];
    }

    @Override // defpackage.ew
    public final BigInteger b() {
        return this.a.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d20)) {
            return false;
        }
        d20 d20Var = (d20) obj;
        return this.a.equals(d20Var.a) && this.b.equals(d20Var.b);
    }

    public final int hashCode() {
        return this.a.a.hashCode() ^ Integer.rotateLeft(this.b.hashCode(), 16);
    }
}
