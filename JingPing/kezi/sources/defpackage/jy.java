package defpackage;

import android.graphics.Insets;

/* JADX INFO: loaded from: classes.dex */
public final class jy {
    public static final jy e = new jy(0, 0, 0, 0);
    public final int a;
    public final int b;
    public final int c;
    public final int d;

    public jy(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public static jy a(jy jyVar, jy jyVar2) {
        return b(Math.max(jyVar.a, jyVar2.a), Math.max(jyVar.b, jyVar2.b), Math.max(jyVar.c, jyVar2.c), Math.max(jyVar.d, jyVar2.d));
    }

    public static jy b(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? e : new jy(i, i2, i3, i4);
    }

    public static jy c(Insets insets) {
        return b(insets.left, insets.top, insets.right, insets.bottom);
    }

    public final Insets d() {
        return iy.a(this.a, this.b, this.c, this.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || jy.class != obj.getClass()) {
            return false;
        }
        jy jyVar = (jy) obj;
        return this.d == jyVar.d && this.a == jyVar.a && this.c == jyVar.c && this.b == jyVar.b;
    }

    public final int hashCode() {
        return (((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d;
    }

    public final String toString() {
        return "Insets{left=" + this.a + ", top=" + this.b + ", right=" + this.c + ", bottom=" + this.d + '}';
    }
}
