package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class o40 implements Iterable {
    public final int E;
    public final int F;
    public final int G;

    public o40(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i3 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.E = i;
        if (i3 > 0) {
            if (i < i2) {
                int i4 = i2 % i3;
                int i5 = i % i3;
                int i6 = ((i4 < 0 ? i4 + i3 : i4) - (i5 < 0 ? i5 + i3 : i5)) % i3;
                i2 -= i6 < 0 ? i6 + i3 : i6;
            }
        } else {
            if (i3 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (i > i2) {
                int i7 = -i3;
                int i8 = i % i7;
                int i9 = i2 % i7;
                int i10 = ((i8 < 0 ? i8 + i7 : i8) - (i9 < 0 ? i9 + i7 : i9)) % i7;
                i2 += i10 < 0 ? i10 + i7 : i10;
            }
        }
        this.F = i2;
        this.G = i3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof o40)) {
            return false;
        }
        if (isEmpty() && ((o40) obj).isEmpty()) {
            return true;
        }
        o40 o40Var = (o40) obj;
        return this.E == o40Var.E && this.F == o40Var.F && this.G == o40Var.G;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.E * 31) + this.F) * 31) + this.G;
    }

    public boolean isEmpty() {
        int i = this.G;
        int i2 = this.F;
        int i3 = this.E;
        return i > 0 ? i3 > i2 : i3 < i2;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new p40(this.E, this.F, this.G);
    }

    public String toString() {
        StringBuilder sb;
        int i = this.F;
        int i2 = this.E;
        int i3 = this.G;
        if (i3 > 0) {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append("..");
            sb.append(i);
            sb.append(" step ");
            sb.append(i3);
        } else {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append(" downTo ");
            sb.append(i);
            sb.append(" step ");
            sb.append(-i3);
        }
        return sb.toString();
    }
}
