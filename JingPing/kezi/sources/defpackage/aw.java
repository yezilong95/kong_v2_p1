package defpackage;

import java.io.EOFException;
import java.util.Arrays;
import kotlin.uuid.Uuid;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class aw {
    public final p9 a;
    public boolean c;
    public int g;
    public int h;
    public int b = Integer.MAX_VALUE;
    public int d = 4096;
    public sv[] e = new sv[8];
    public int f = 7;

    public aw(p9 p9Var) {
        this.a = p9Var;
    }

    public final void a(int i) {
        int i2;
        if (i > 0) {
            int length = this.e.length - 1;
            int i3 = 0;
            while (true) {
                i2 = this.f;
                if (length < i2 || i <= 0) {
                    break;
                }
                sv svVar = this.e[length];
                oy.g(svVar);
                i -= svVar.c;
                int i4 = this.h;
                sv svVar2 = this.e[length];
                oy.g(svVar2);
                this.h = i4 - svVar2.c;
                this.g--;
                i3++;
                length--;
            }
            sv[] svVarArr = this.e;
            int i5 = i2 + 1;
            System.arraycopy(svVarArr, i5, svVarArr, i5 + i3, this.g);
            sv[] svVarArr2 = this.e;
            int i6 = this.f + 1;
            Arrays.fill(svVarArr2, i6, i6 + i3, (Object) null);
            this.f += i3;
        }
    }

    public final void b(sv svVar) {
        int i = this.d;
        int i2 = svVar.c;
        if (i2 > i) {
            sv[] svVarArr = this.e;
            g7.b0(svVarArr, null, 0, svVarArr.length);
            this.f = this.e.length - 1;
            this.g = 0;
            this.h = 0;
            return;
        }
        a((this.h + i2) - i);
        int i3 = this.g + 1;
        sv[] svVarArr2 = this.e;
        if (i3 > svVarArr2.length) {
            sv[] svVarArr3 = new sv[svVarArr2.length * 2];
            System.arraycopy(svVarArr2, 0, svVarArr3, svVarArr2.length, svVarArr2.length);
            this.f = this.e.length - 1;
            this.e = svVarArr3;
        }
        int i4 = this.f;
        this.f = i4 - 1;
        this.e[i4] = svVar;
        this.g++;
        this.h += i2;
    }

    public final void c(ByteString byteString) throws EOFException {
        oy.j(byteString, "data");
        p9 p9Var = this.a;
        int[] iArr = hx.a;
        int size = byteString.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            byte b = byteString.getByte(i);
            byte[] bArr = kq0.a;
            j += (long) hx.b[b & 255];
        }
        if (((int) ((j + ((long) 7)) >> 3)) >= byteString.size()) {
            e(byteString.size(), 127, 0);
            p9Var.L(byteString);
            return;
        }
        p9 p9Var2 = new p9();
        int[] iArr2 = hx.a;
        int size2 = byteString.size();
        long j2 = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < size2; i3++) {
            byte b2 = byteString.getByte(i3);
            byte[] bArr2 = kq0.a;
            int i4 = b2 & 255;
            int i5 = hx.a[i4];
            byte b3 = hx.b[i4];
            j2 = (j2 << b3) | ((long) i5);
            i2 += b3;
            while (i2 >= 8) {
                i2 -= 8;
                p9Var2.O((int) (j2 >> i2));
            }
        }
        if (i2 > 0) {
            p9Var2.O((int) ((255 >>> i2) | (j2 << (8 - i2))));
        }
        ByteString byteStringE = p9Var2.E(p9Var2.c);
        e(byteStringE.size(), 127, Uuid.SIZE_BITS);
        p9Var.L(byteStringE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(java.util.ArrayList r14) throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aw.d(java.util.ArrayList):void");
    }

    public final void e(int i, int i2, int i3) {
        p9 p9Var = this.a;
        if (i < i2) {
            p9Var.O(i | i3);
            return;
        }
        p9Var.O(i3 | i2);
        int i4 = i - i2;
        while (i4 >= 128) {
            p9Var.O(128 | (i4 & 127));
            i4 >>>= 7;
        }
        p9Var.O(i4);
    }
}
