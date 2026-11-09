package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class th0 {
    public final byte[] a;
    public int b;
    public int c;
    public boolean d;
    public final boolean e;
    public th0 f;
    public th0 g;

    public th0() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    public final th0 a() {
        th0 th0Var = this.f;
        if (th0Var == this) {
            th0Var = null;
        }
        th0 th0Var2 = this.g;
        oy.g(th0Var2);
        th0Var2.f = this.f;
        th0 th0Var3 = this.f;
        oy.g(th0Var3);
        th0Var3.g = this.g;
        this.f = null;
        this.g = null;
        return th0Var;
    }

    public final void b(th0 th0Var) {
        oy.j(th0Var, "segment");
        th0Var.g = this;
        th0Var.f = this.f;
        th0 th0Var2 = this.f;
        oy.g(th0Var2);
        th0Var2.g = th0Var;
        this.f = th0Var;
    }

    public final th0 c() {
        this.d = true;
        return new th0(this.a, this.b, this.c, true);
    }

    public final void d(th0 th0Var, int i) {
        oy.j(th0Var, "sink");
        if (!th0Var.e) {
            throw new IllegalStateException("only owner can write");
        }
        int i2 = th0Var.c;
        int i3 = i2 + i;
        byte[] bArr = th0Var.a;
        if (i3 > 8192) {
            if (th0Var.d) {
                throw new IllegalArgumentException();
            }
            int i4 = th0Var.b;
            if (i3 - i4 > 8192) {
                throw new IllegalArgumentException();
            }
            g7.X(bArr, 0, bArr, i4, i2);
            th0Var.c -= th0Var.b;
            th0Var.b = 0;
        }
        int i5 = th0Var.c;
        int i6 = this.b;
        g7.X(this.a, i5, bArr, i6, i6 + i);
        th0Var.c += i;
        this.b += i;
    }

    public th0(byte[] bArr, int i, int i2, boolean z) {
        oy.j(bArr, "data");
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = false;
    }
}
