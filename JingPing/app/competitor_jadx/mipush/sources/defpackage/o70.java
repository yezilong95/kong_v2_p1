package defpackage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.bouncycastle.jcajce.CompositePrivateKey;
import org.bouncycastle.jcajce.CompositePublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public final class o70 extends s9 {
    public static final HashMap b;
    public z8 a = null;

    static {
        l4 l4Var = new l4(yf0.n);
        l4 l4Var2 = new l4(yf0.o);
        l4 l4Var3 = new l4(yf0.p);
        d0 d0Var = a9.a;
        l4 l4Var4 = new l4(fs.a);
        d0 d0Var2 = w91.e;
        l4 l4Var5 = new l4(d0Var2, new q91(aq0.G));
        l4 l4Var6 = new l4(d0Var2, new q91(oz0.j));
        l4 l4Var7 = new l4(vj0.a);
        l4 l4Var8 = new l4(fs.b);
        l4 l4Var9 = new l4(d0Var2, new q91(aq0.z));
        l4 l4Var10 = new l4(d0Var2, new q91(oz0.n));
        HashMap map = new HashMap();
        b = map;
        map.put(af0.c, new l4[]{l4Var, l4Var7});
        map.put(af0.d, new l4[]{l4Var, l4Var7});
        map.put(af0.e, new l4[]{l4Var, l4Var4});
        map.put(af0.f, new l4[]{l4Var, l4Var5});
        map.put(af0.g, new l4[]{l4Var2, l4Var7});
        map.put(af0.h, new l4[]{l4Var2, l4Var7});
        map.put(af0.i, new l4[]{l4Var2, l4Var7});
        map.put(af0.j, new l4[]{l4Var2, l4Var7});
        map.put(af0.k, new l4[]{l4Var2, l4Var9});
        map.put(af0.l, new l4[]{l4Var2, l4Var6});
        map.put(af0.m, new l4[]{l4Var2, l4Var4});
        map.put(af0.n, new l4[]{l4Var3, l4Var9});
        map.put(af0.o, new l4[]{l4Var3, l4Var10});
        map.put(af0.p, new l4[]{l4Var3, l4Var8});
        map.put(af0.q, new l4[]{l4Var, l4Var7});
        map.put(af0.r, new l4[]{l4Var, l4Var7});
        map.put(af0.s, new l4[]{l4Var, l4Var4});
        map.put(af0.t, new l4[]{l4Var, l4Var5});
        map.put(af0.u, new l4[]{l4Var2, l4Var7});
        map.put(af0.v, new l4[]{l4Var2, l4Var7});
        map.put(af0.w, new l4[]{l4Var2, l4Var7});
        map.put(af0.x, new l4[]{l4Var2, l4Var7});
        map.put(af0.y, new l4[]{l4Var2, l4Var9});
        map.put(af0.z, new l4[]{l4Var2, l4Var6});
        map.put(af0.A, new l4[]{l4Var2, l4Var4});
        map.put(af0.B, new l4[]{l4Var3, l4Var9});
        map.put(af0.C, new l4[]{l4Var3, l4Var10});
        map.put(af0.D, new l4[]{l4Var3, l4Var8});
    }

    public static X509EncodedKeySpec[] d(d0 d0Var, p[] pVarArr) throws IOException {
        X509EncodedKeySpec[] x509EncodedKeySpecArr = new X509EncodedKeySpec[pVarArr.length];
        sx0[] sx0VarArr = new sx0[pVarArr.length];
        l4[] l4VarArr = (l4[]) b.get(d0Var);
        if (l4VarArr == null) {
            throw new IOException("Cannot create key specs. Unsupported algorithm identifier.");
        }
        sx0VarArr[0] = new sx0(l4VarArr[0], pVarArr[0]);
        sx0VarArr[1] = new sx0(l4VarArr[1], pVarArr[1]);
        x509EncodedKeySpecArr[0] = new X509EncodedKeySpec(sx0VarArr[0].getEncoded());
        x509EncodedKeySpecArr[1] = new X509EncodedKeySpec(sx0VarArr[1].getEncoded());
        return x509EncodedKeySpecArr;
    }

    public final CompositePrivateKey a(ol0 ol0Var) throws IOException {
        if (this.a == null) {
            this.a = new z8(0);
        }
        j0 j0VarQ = j0.q(ol0Var.i());
        d0 d0Var = ol0Var.F.E;
        int i = 0;
        if (af0.a.k(d0Var) || af0.b.k(d0Var)) {
            PrivateKey[] privateKeyArr = new PrivateKey[j0VarQ.size()];
            while (i != j0VarQ.size()) {
                ol0 ol0VarG = ol0.g(j0.q(j0VarQ.r(i)));
                try {
                    privateKeyArr[i] = KeyFactory.getInstance(ol0VarG.F.E.r(), (BouncyCastleProvider) this.a.F).generatePrivate(new PKCS8EncodedKeySpec(ol0VarG.getEncoded()));
                    i++;
                } catch (Exception e) {
                    throw new IOException("cannot decode generic composite: " + e.getMessage(), e);
                }
            }
            return new CompositePrivateKey(privateKeyArr);
        }
        try {
            List listC = c(d0Var);
            PrivateKey[] privateKeyArr2 = new PrivateKey[j0VarQ.size()];
            l4[] l4VarArr = (l4[]) b.get(d0Var);
            while (i < j0VarQ.size()) {
                if (j0VarQ.r(i) instanceof e0) {
                    u uVar = new u(2);
                    uVar.c(ol0Var.E);
                    uVar.c(l4VarArr[i]);
                    uVar.c(j0VarQ.r(i));
                    wk wkVar = new wk(uVar, 0);
                    wkVar.H = -1;
                    privateKeyArr2[i] = ((KeyFactory) listC.get(i)).generatePrivate(new PKCS8EncodedKeySpec(ol0.g(wkVar).getEncoded()));
                } else {
                    privateKeyArr2[i] = ((KeyFactory) listC.get(i)).generatePrivate(new PKCS8EncodedKeySpec(ol0.g(j0.q(j0VarQ.r(i))).getEncoded()));
                }
                i++;
            }
            return new CompositePrivateKey(d0Var, privateKeyArr2);
        } catch (GeneralSecurityException e2) {
            throw new IOException(e2.getMessage(), e2);
        }
    }

    public final CompositePublicKey b(sx0 sx0Var) throws IOException {
        if (this.a == null) {
            this.a = new z8(0);
        }
        j0 j0VarQ = j0.q(sx0Var.F.p());
        d0 d0Var = sx0Var.E.E;
        int i = 0;
        if (af0.a.k(d0Var) || af0.b.k(d0Var)) {
            j0 j0VarQ2 = j0.q(sx0Var.F.p());
            PublicKey[] publicKeyArr = new PublicKey[j0VarQ2.size()];
            while (i != j0VarQ2.size()) {
                sx0 sx0VarG = sx0.g(j0VarQ2.r(i));
                try {
                    publicKeyArr[i] = KeyFactory.getInstance(sx0VarG.E.E.r(), (BouncyCastleProvider) this.a.F).generatePublic(new X509EncodedKeySpec(sx0VarG.getEncoded()));
                    i++;
                } catch (Exception e) {
                    throw new IOException("cannot decode generic composite: " + e.getMessage(), e);
                }
            }
            return new CompositePublicKey(publicKeyArr);
        }
        try {
            List listC = c(d0Var);
            p[] pVarArr = new p[j0VarQ.size()];
            for (int i2 = 0; i2 < j0VarQ.size(); i2++) {
                if (j0VarQ.r(i2) instanceof tk) {
                    pVarArr[i2] = new lk(((tk) j0VarQ.r(i2)).E, 0);
                } else {
                    pVarArr[i2] = (lk) j0VarQ.r(i2);
                }
            }
            X509EncodedKeySpec[] x509EncodedKeySpecArrD = d(d0Var, pVarArr);
            PublicKey[] publicKeyArr2 = new PublicKey[j0VarQ.size()];
            while (i < j0VarQ.size()) {
                publicKeyArr2[i] = ((KeyFactory) listC.get(i)).generatePublic(x509EncodedKeySpecArrD[i]);
                i++;
            }
            return new CompositePublicKey(d0Var, publicKeyArr2);
        } catch (GeneralSecurityException e2) {
            throw new IOException(e2.getMessage(), e2);
        }
    }

    public final List c(d0 d0Var) throws NoSuchAlgorithmException {
        ArrayList arrayList = new ArrayList();
        new ArrayList();
        String[] strArr = (String[]) yg.a.get(d0Var);
        if (strArr == null) {
            throw new NoSuchAlgorithmException("Cannot create KeyFactories. Unsupported algorithm identifier.");
        }
        z8 z8Var = this.a;
        String str = strArr[0];
        if (str.indexOf("RSA") >= 0) {
            str = "RSA";
        } else if (str.indexOf("ECDSA") >= 0) {
            str = "EC";
        }
        arrayList.add(KeyFactory.getInstance(str, (BouncyCastleProvider) z8Var.F));
        z8 z8Var2 = this.a;
        String str2 = strArr[1];
        arrayList.add(KeyFactory.getInstance(str2.indexOf("RSA") < 0 ? str2.indexOf("ECDSA") >= 0 ? "EC" : str2 : "RSA", (BouncyCastleProvider) z8Var2.F));
        return Collections.unmodifiableList(arrayList);
    }

    @Override // java.security.KeyFactorySpi
    public final Key engineTranslateKey(Key key) throws InvalidKeyException {
        if (this.a == null) {
            this.a = new z8(0);
        }
        try {
            if (key instanceof PrivateKey) {
                return a(ol0.g(key.getEncoded()));
            }
            if (key instanceof PublicKey) {
                return b(sx0.g(key.getEncoded()));
            }
            throw new InvalidKeyException("Key not recognized");
        } catch (IOException e) {
            throw new InvalidKeyException("Key could not be parsed: " + e.getMessage());
        }
    }
}
