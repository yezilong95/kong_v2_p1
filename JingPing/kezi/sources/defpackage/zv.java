package defpackage;

import java.io.IOException;
import java.util.ArrayList;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class zv {
    public final sc0 c;
    public int f;
    public int g;
    public int a = 4096;
    public final ArrayList b = new ArrayList();
    public sv[] d = new sv[8];
    public int e = 7;

    public zv(ww wwVar) {
        this.c = new sc0(wwVar);
    }

    public final int a(int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            int length = this.d.length;
            while (true) {
                length--;
                i2 = this.e;
                if (length < i2 || i <= 0) {
                    break;
                }
                sv svVar = this.d[length];
                oy.g(svVar);
                int i4 = svVar.c;
                i -= i4;
                this.g -= i4;
                this.f--;
                i3++;
            }
            sv[] svVarArr = this.d;
            System.arraycopy(svVarArr, i2 + 1, svVarArr, i2 + 1 + i3, this.f);
            this.e += i3;
        }
        return i3;
    }

    public final ByteString b(int i) throws IOException {
        if (i >= 0) {
            sv[] svVarArr = bw.a;
            if (i <= svVarArr.length - 1) {
                return svVarArr[i].a;
            }
        }
        int length = this.e + 1 + (i - bw.a.length);
        if (length >= 0) {
            sv[] svVarArr2 = this.d;
            if (length < svVarArr2.length) {
                sv svVar = svVarArr2[length];
                oy.g(svVar);
                return svVar.a;
            }
        }
        throw new IOException("Header index too large " + (i + 1));
    }

    public final void c(sv svVar) {
        this.b.add(svVar);
        int i = this.a;
        int i2 = svVar.c;
        if (i2 > i) {
            sv[] svVarArr = this.d;
            g7.b0(svVarArr, null, 0, svVarArr.length);
            this.e = this.d.length - 1;
            this.f = 0;
            this.g = 0;
            return;
        }
        a((this.g + i2) - i);
        int i3 = this.f + 1;
        sv[] svVarArr2 = this.d;
        if (i3 > svVarArr2.length) {
            sv[] svVarArr3 = new sv[svVarArr2.length * 2];
            System.arraycopy(svVarArr2, 0, svVarArr3, svVarArr2.length, svVarArr2.length);
            this.e = this.d.length - 1;
            this.d = svVarArr3;
        }
        int i4 = this.e;
        this.e = i4 - 1;
        this.d[i4] = svVar;
        this.f++;
        this.g += i2;
    }

    public final ByteString d() {
        int i;
        sc0 sc0Var = this.c;
        byte bC = sc0Var.C();
        byte[] bArr = kq0.a;
        int i2 = bC & 255;
        int i3 = 0;
        boolean z = (bC & 128) == 128;
        long jE = e(i2, 127);
        if (!z) {
            return sc0Var.D(jE);
        }
        p9 p9Var = new p9();
        int[] iArr = hx.a;
        oy.j(sc0Var, "source");
        gx gxVar = hx.c;
        gx gxVar2 = gxVar;
        int i4 = 0;
        for (long j = 0; j < jE; j++) {
            byte bC2 = sc0Var.C();
            byte[] bArr2 = kq0.a;
            i3 = (i3 << 8) | (bC2 & 255);
            i4 += 8;
            while (i4 >= 8) {
                gx[] gxVarArr = (gx[]) gxVar2.d;
                oy.g(gxVarArr);
                gxVar2 = gxVarArr[(i3 >>> (i4 - 8)) & 255];
                oy.g(gxVar2);
                if (((gx[]) gxVar2.d) == null) {
                    p9Var.O(gxVar2.b);
                    i4 -= gxVar2.c;
                    gxVar2 = gxVar;
                } else {
                    i4 -= 8;
                }
            }
        }
        while (i4 > 0) {
            gx[] gxVarArr2 = (gx[]) gxVar2.d;
            oy.g(gxVarArr2);
            gx gxVar3 = gxVarArr2[(i3 << (8 - i4)) & 255];
            oy.g(gxVar3);
            if (((gx[]) gxVar3.d) != null || (i = gxVar3.c) > i4) {
                break;
            }
            p9Var.O(gxVar3.b);
            i4 -= i;
            gxVar2 = gxVar;
        }
        return p9Var.E(p9Var.c);
    }

    public final int e(int i, int i2) {
        int i3 = i & i2;
        if (i3 < i2) {
            return i3;
        }
        int i4 = 0;
        while (true) {
            byte bC = this.c.C();
            byte[] bArr = kq0.a;
            int i5 = bC & 255;
            if ((bC & 128) == 0) {
                return i2 + (i5 << i4);
            }
            i2 += (bC & 127) << i4;
            i4 += 7;
        }
    }
}
