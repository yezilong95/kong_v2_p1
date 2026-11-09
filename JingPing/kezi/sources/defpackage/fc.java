package defpackage;

import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class fc {
    public final /* synthetic */ int a;
    public long b;
    public Object c;

    public fc(sc0 sc0Var) {
        this.a = 1;
        oy.j(sc0Var, "source");
        this.c = sc0Var;
        this.b = 262144L;
    }

    public void a(int i) {
        if (i < 64) {
            this.b &= ~(1 << i);
            return;
        }
        fc fcVar = (fc) this.c;
        if (fcVar != null) {
            fcVar.a(i - 64);
        }
    }

    public int b(int i) {
        fc fcVar = (fc) this.c;
        if (fcVar == null) {
            return i >= 64 ? Long.bitCount(this.b) : Long.bitCount(this.b & ((1 << i) - 1));
        }
        if (i < 64) {
            return Long.bitCount(this.b & ((1 << i) - 1));
        }
        return Long.bitCount(this.b) + fcVar.b(i - 64);
    }

    public void c() {
        if (((fc) this.c) == null) {
            this.c = new fc();
        }
    }

    public boolean d(int i) {
        if (i < 64) {
            return (this.b & (1 << i)) != 0;
        }
        c();
        return ((fc) this.c).d(i - 64);
    }

    public void e(int i, boolean z) {
        if (i >= 64) {
            c();
            ((fc) this.c).e(i - 64, z);
            return;
        }
        long j = this.b;
        boolean z2 = (Long.MIN_VALUE & j) != 0;
        long j2 = (1 << i) - 1;
        this.b = ((j & (~j2)) << 1) | (j & j2);
        if (z) {
            i(i);
        } else {
            a(i);
        }
        if (z2 || ((fc) this.c) != null) {
            c();
            ((fc) this.c).e(0, z2);
        }
    }

    public tv f() {
        o70 o70Var = new o70(23);
        while (true) {
            String strI = ((sc0) this.c).I(this.b);
            this.b -= (long) strI.length();
            if (strI.length() == 0) {
                return o70Var.t();
            }
            int iO0 = vk0.o0(strI, ':', 1, 4);
            if (iO0 != -1) {
                String strSubstring = strI.substring(0, iO0);
                oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                String strSubstring2 = strI.substring(iO0 + 1);
                oy.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
                o70Var.s(strSubstring, strSubstring2);
            } else if (strI.charAt(0) == ':') {
                String strSubstring3 = strI.substring(1);
                oy.i(strSubstring3, "this as java.lang.String).substring(startIndex)");
                o70Var.s(ExtensionRequestData.EMPTY_VALUE, strSubstring3);
            } else {
                o70Var.s(ExtensionRequestData.EMPTY_VALUE, strI);
            }
        }
    }

    public boolean g(int i) {
        if (i >= 64) {
            c();
            return ((fc) this.c).g(i - 64);
        }
        long j = 1 << i;
        long j2 = this.b;
        boolean z = (j2 & j) != 0;
        long j3 = j2 & (~j);
        this.b = j3;
        long j4 = j - 1;
        this.b = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
        fc fcVar = (fc) this.c;
        if (fcVar != null) {
            if (fcVar.d(0)) {
                i(63);
            }
            ((fc) this.c).g(0);
        }
        return z;
    }

    public void h() {
        this.b = 0L;
        fc fcVar = (fc) this.c;
        if (fcVar != null) {
            fcVar.h();
        }
    }

    public void i(int i) {
        if (i < 64) {
            this.b |= 1 << i;
        } else {
            c();
            ((fc) this.c).i(i - 64);
        }
    }

    public String toString() {
        switch (this.a) {
            case 0:
                if (((fc) this.c) == null) {
                    return Long.toBinaryString(this.b);
                }
                return ((fc) this.c).toString() + "xx" + Long.toBinaryString(this.b);
            default:
                return super.toString();
        }
    }

    public fc() {
        this.a = 0;
        this.b = 0L;
    }
}
