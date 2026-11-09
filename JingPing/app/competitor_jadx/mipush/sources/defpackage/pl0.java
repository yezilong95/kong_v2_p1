package defpackage;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class pl0 {
    static {
        HashSet hashSet = new HashSet(5);
        hashSet.add(rj.h);
        hashSet.add(rj.i);
        hashSet.add(rj.j);
        hashSet.add(rj.k);
        hashSet.add(rj.l);
    }

    public static ol0 a(s8 s8Var, k0 k0Var) throws IOException {
        q91 q91Var;
        boolean z = s8Var instanceof mm0;
        rk rkVar = rk.E;
        if (z) {
            nm0 nm0Var = (nm0) s8Var;
            return new ol0(new l4(vj0.a, rkVar), new om0(nm0Var.F, nm0Var.J, nm0Var.G, nm0Var.K, nm0Var.L, nm0Var.M, nm0Var.N, nm0Var.O), k0Var, null);
        }
        if (s8Var instanceof qr) {
            qr qrVar = (qr) s8Var;
            yq yqVar = (yq) qrVar.F;
            boolean z2 = yqVar instanceof jr;
            or orVar = yqVar.h;
            BigInteger bigInteger = yqVar.i;
            if (z2) {
                q91Var = new q91(((jr) yqVar).k);
            } else {
                u91 u91Var = new u91(orVar, false);
                byte[] bArrL = z91.l(yqVar.g);
                q91Var = new q91(new s91(yqVar.f, u91Var, yqVar.i, yqVar.j, bArrL));
            }
            int iBitLength = bigInteger.bitLength();
            BigInteger bigInteger2 = qrVar.G;
            return new ol0(new l4(w91.e, q91Var), new pr(iBitLength, bigInteger2, new lk(z91.Q(orVar, bigInteger2).h(false), 0), q91Var), k0Var, null);
        }
        if (s8Var instanceof l91) {
            l91 l91Var = (l91) s8Var;
            return new ol0(new l4(es.b), new tk(z91.l(l91Var.F)), k0Var, z91.l(l91Var.a().F));
        }
        if (s8Var instanceof j91) {
            j91 j91Var = (j91) s8Var;
            return new ol0(new l4(es.a), new tk(z91.l(j91Var.F)), k0Var, z91.l(j91Var.a().F));
        }
        if (!(s8Var instanceof cs)) {
            if (!(s8Var instanceof zr)) {
                throw new IOException("key parameters not recognized");
            }
            zr zrVar = (zr) s8Var;
            return new ol0(new l4(es.c), new tk(z91.l(zrVar.F)), k0Var, zrVar.a().getEncoded());
        }
        cs csVar = (cs) s8Var;
        l4 l4Var = new l4(es.d);
        tk tkVar = new tk(z91.l(csVar.F));
        byte[] bArr = new byte[57];
        csVar.a().a(0, bArr);
        return new ol0(l4Var, tkVar, k0Var, bArr);
    }
}
