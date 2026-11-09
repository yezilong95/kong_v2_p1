package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ky implements Iterable, wz {
    public final int b;
    public final int c;
    public final int d;

    public ky(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i3 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.b = i;
        this.c = xi.A(i, i2, i3);
        this.d = i3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ky)) {
            return false;
        }
        if (isEmpty() && ((ky) obj).isEmpty()) {
            return true;
        }
        ky kyVar = (ky) obj;
        return this.b == kyVar.b && this.c == kyVar.c && this.d == kyVar.d;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.b * 31) + this.c) * 31) + this.d;
    }

    public boolean isEmpty() {
        int i = this.d;
        int i2 = this.c;
        int i3 = this.b;
        return i > 0 ? i3 > i2 : i3 < i2;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new ly(this.b, this.c, this.d);
    }

    public String toString() {
        StringBuilder sb;
        int i = this.c;
        int i2 = this.b;
        int i3 = this.d;
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
