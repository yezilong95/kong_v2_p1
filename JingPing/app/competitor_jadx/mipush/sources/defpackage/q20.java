package defpackage;

import io.nats.client.support.Digester;
import java.util.Hashtable;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class q20 {
    public final fq0 a;
    public vd0 d;
    public vd0 e;
    public final int b = 64;
    public final int c = Uuid.SIZE_BITS;
    public final byte[] f = new byte[Uuid.SIZE_BITS];
    public final byte[] g = new byte[192];

    static {
        Hashtable hashtable = new Hashtable();
        hashtable.put("GOST3411", 32);
        hashtable.put("MD2", 16);
        hashtable.put("MD4", 64);
        hashtable.put("MD5", 64);
        hashtable.put("RIPEMD128", 64);
        hashtable.put("RIPEMD160", 64);
        hashtable.put("SHA-1", 64);
        hashtable.put("SHA-224", 64);
        hashtable.put(Digester.DEFAULT_DIGEST_ALGORITHM, 64);
        hashtable.put("SHA-384", Integer.valueOf(Uuid.SIZE_BITS));
        hashtable.put("SHA-512", Integer.valueOf(Uuid.SIZE_BITS));
        hashtable.put("Tiger", 64);
        hashtable.put("Whirlpool", 64);
    }

    public q20(fq0 fq0Var) {
        this.a = fq0Var;
    }

    public final int a(byte[] bArr) {
        fq0 fq0Var = this.a;
        byte[] bArr2 = this.g;
        int i = this.c;
        fq0Var.d(i, bArr2);
        vd0 vd0Var = this.e;
        if (vd0Var != null) {
            fq0Var.g(vd0Var);
            fq0Var.e(bArr2, i, 64);
        } else {
            fq0Var.e(bArr2, 0, bArr2.length);
        }
        fq0Var.d(0, bArr);
        while (i < bArr2.length) {
            bArr2[i] = 0;
            i++;
        }
        vd0 vd0Var2 = this.d;
        if (vd0Var2 != null) {
            fq0Var.g(vd0Var2);
            return 64;
        }
        byte[] bArr3 = this.f;
        fq0Var.e(bArr3, 0, bArr3.length);
        return 64;
    }

    public final void b(q70 q70Var) {
        fq0 fq0Var = this.a;
        fq0Var.p();
        byte[] bArr = q70Var.E;
        int length = bArr.length;
        byte[] bArr2 = this.f;
        int i = this.c;
        if (length > i) {
            fq0Var.e(bArr, 0, length);
            fq0Var.d(0, bArr2);
            length = this.b;
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, length);
        }
        while (length < bArr2.length) {
            bArr2[length] = 0;
            length++;
        }
        byte[] bArr3 = this.g;
        System.arraycopy(bArr2, 0, bArr3, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            bArr2[i2] = (byte) (bArr2[i2] ^ 54);
        }
        for (int i3 = 0; i3 < i; i3++) {
            bArr3[i3] = (byte) (bArr3[i3] ^ 92);
        }
        vd0 vd0VarA = fq0Var.a();
        this.e = vd0VarA;
        ((po) vd0VarA).e(bArr3, 0, i);
        fq0Var.e(bArr2, 0, bArr2.length);
        this.d = fq0Var.a();
    }
}
