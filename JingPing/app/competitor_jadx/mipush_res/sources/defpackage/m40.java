package defpackage;

import android.graphics.Insets;

/* JADX INFO: loaded from: classes.dex */
public final class m40 {
    public static final m40 e = new m40(0, 0, 0, 0);
    public final int a;
    public final int b;
    public final int c;
    public final int d;

    public m40(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public static m40 a(m40 m40Var, m40 m40Var2) {
        return b(Math.max(m40Var.a, m40Var2.a), Math.max(m40Var.b, m40Var2.b), Math.max(m40Var.c, m40Var2.c), Math.max(m40Var.d, m40Var2.d));
    }

    public static m40 b(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? e : new m40(i, i2, i3, i4);
    }

    public static m40 c(Insets insets) {
        return b(insets.left, insets.top, insets.right, insets.bottom);
    }

    public final Insets d() {
        return l40.a(this.a, this.b, this.c, this.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m40.class != obj.getClass()) {
            return false;
        }
        m40 m40Var = (m40) obj;
        return this.d == m40Var.d && this.a == m40Var.a && this.c == m40Var.c && this.b == m40Var.b;
    }

    public final int hashCode() {
        return (((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d;
    }

    public final String toString() {
        return "Insets{left=" + this.a + ", top=" + this.b + ", right=" + this.c + ", bottom=" + this.d + '}';
    }
}
