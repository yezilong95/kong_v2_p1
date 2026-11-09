package org.bouncycastle.jce.provider;

import defpackage.d0;
import defpackage.em0;
import defpackage.ib;
import defpackage.jb;
import defpackage.jx0;
import defpackage.kb;
import defpackage.o70;
import defpackage.ol0;
import defpackage.r8;
import defpackage.sj;
import defpackage.sx0;
import defpackage.uj;
import defpackage.vr;
import defpackage.z91;
import defpackage.zn;
import io.nats.client.support.NatsConstants;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import org.bouncycastle.crypto.a;

/* JADX INFO: loaded from: classes.dex */
public final class BouncyCastleProvider extends Provider {
    public static final em0 CONFIGURATION;
    public static final Logger F = Logger.getLogger(BouncyCastleProvider.class.getName());
    public static final String G;
    public static final HashMap H;
    public static final Class I;
    public static final String[] J;
    public static final String[] K;
    public static final sj[] L;
    public static final String[] M;
    public static final String[] N;
    public static final String[] O;
    public static final String[] P;
    public static final String PROVIDER_NAME = "BC";
    public static final String[] Q;
    public final ConcurrentHashMap E;

    static {
        uj ujVar = a.a;
        G = "BouncyCastle APIs (LTS edition) v2.73.7".replace("APIs", "Security Provider");
        kb kbVar = new kb();
        kbVar.a = new ThreadLocal();
        kbVar.b = new ThreadLocal();
        kbVar.e = new HashSet();
        kbVar.f = new HashMap();
        CONFIGURATION = kbVar;
        H = new HashMap();
        I = z91.P(BouncyCastleProvider.class, "java.security.cert.PKIXRevocationChecker");
        J = new String[]{"PBEPBKDF1", "PBEPBKDF2", "PBEPKCS12", "TLSKDF", "SCRYPT"};
        K = new String[]{"SipHash", "SipHash128", "Poly1305"};
        L = new sj[]{new vr("AES", 1), new vr("ARC4", 1), new vr("ARIA", 1), new vr("Blowfish", 1), new vr("Camellia", 1), new vr("CAST5", 1), new vr("CAST6", 1), new vr("ChaCha", 1), new vr("DES", 1), new vr("DESede", 1), new vr("GOST28147", 1), new vr("Grainv1", 1), new vr("Grain128", 1), new vr("HC128", 1), new vr("HC256", 1), new vr("IDEA", 1), new vr("Noekeon", 1), new vr("RC2", 1), new vr("RC5", 1), new vr("RC6", 1), new vr("Rijndael", 1), new vr("Salsa20", 1), new vr("SEED", 1), new vr("Serpent", 1), new vr("Shacal2", 1), new vr("Skipjack", 1), new vr("SM4", 1), new vr("TEA", 1), new vr("Twofish", 1), new vr("Threefish", 1), new vr("VMPC", 1), new vr("VMPCKSA3", 1), new vr("XTEA", 1), new vr("XSalsa20", 1), new vr("OpenSSLPBKDF", 1), new vr("DSTU7624", 1), new vr("GOST3412_2015", 1), new vr("Zuc", 1)};
        M = new String[]{"X509", "IES", "COMPOSITE", "EXTERNAL", "CompositeSignatures"};
        N = new String[]{"DSA", "DH", "EC", "RSA", "GOST", "ECGOST", "ElGamal", "DSTU4145", "GM", "EdEC", "LMS", "CONTEXT", "SLHDSA", "MLDSA", "MLKEM"};
        O = new String[]{"GOST3411", "Keccak", "MD2", "MD4", "MD5", "SHA1", "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "SHA224", "SHA256", "SHA384", "SHA512", "SHA3", "Skein", "SM3", "Tiger", "Whirlpool", "Blake2b", "Blake2s", "DSTU7564", "Haraka", "Blake3"};
        P = new String[]{PROVIDER_NAME, "BCFKS", "PKCS12"};
        Q = new String[]{"DRBG"};
    }

    public BouncyCastleProvider() {
        super(PROVIDER_NAME, 2.7307d, G);
        this.E = new ConcurrentHashMap();
        AccessController.doPrivileged(new ib(0, this));
    }

    public static void c(String str, String[] strArr) {
        for (int i = 0; i != strArr.length; i++) {
            d(str, strArr[i]);
        }
    }

    public static void d(String str, String str2) {
        Class clsP = z91.P(BouncyCastleProvider.class, str + str2 + "$Mappings");
        if (clsP == null) {
            return;
        }
        try {
            if (clsP.newInstance() != null) {
                throw new ClassCastException();
            }
            throw null;
        } catch (Exception e) {
            throw new InternalError("cannot create instance of " + str + str2 + "$Mappings : " + e);
        }
    }

    public static PrivateKey getPrivateKey(ol0 ol0Var) throws IOException {
        r8 r8Var;
        d0 d0Var = ol0Var.F.E;
        HashMap map = H;
        synchronized (map) {
            r8Var = (r8) map.get(d0Var);
        }
        if (r8Var == null) {
            return null;
        }
        return ((o70) r8Var).a(ol0Var);
    }

    public static PublicKey getPublicKey(sx0 sx0Var) throws IOException {
        r8 r8Var;
        d0 d0Var = sx0Var.E.E;
        HashMap map = H;
        synchronized (map) {
            r8Var = (r8) map.get(d0Var);
        }
        if (r8Var == null) {
            return null;
        }
        return ((o70) r8Var).b(sx0Var);
    }

    public void addAlgorithm(String str, d0 d0Var, String str2) {
        addAlgorithm(str + NatsConstants.DOT + d0Var, str2);
        addAlgorithm(str + ".OID." + d0Var, str2);
    }

    public void addAttributes(String str, Map<String, String> map) {
        put(str + " ImplementedIn", "Software");
        for (String str2 : map.keySet()) {
            String str3 = str + NatsConstants.SPACE + str2;
            if (containsKey(str3)) {
                throw new IllegalStateException(zn.n("duplicate provider attribute key (", str3, ") found"));
            }
            put(str3, map.get(str2));
        }
    }

    public void addKeyInfoConverter(d0 d0Var, r8 r8Var) {
        HashMap map = H;
        synchronized (map) {
            map.put(d0Var, r8Var);
        }
    }

    public r8 getKeyInfoConverter(d0 d0Var) {
        return (r8) H.get(d0Var);
    }

    @Override // java.security.Provider
    public final Provider.Service getService(String str, String str2) {
        Provider.Service service;
        String str3 = str + NatsConstants.DOT + jx0.e(str2);
        Provider.Service service2 = (Provider.Service) this.E.get(str3);
        if (service2 != null) {
            return service2;
        }
        synchronized (this) {
            try {
                service = (Provider.Service) (!this.E.containsKey(str3) ? AccessController.doPrivileged(new jb(this, str, str2, str3)) : this.E.get(str3));
            } catch (Throwable th) {
                throw th;
            }
        }
        return service;
    }

    public boolean hasAlgorithm(String str, String str2) {
        if (containsKey(str + NatsConstants.DOT + str2)) {
            return true;
        }
        StringBuilder sb = new StringBuilder("Alg.Alias.");
        sb.append(str);
        sb.append(NatsConstants.DOT);
        sb.append(str2);
        return containsKey(sb.toString());
    }

    public void setParameter(String str, Object obj) {
        em0 em0Var = CONFIGURATION;
        synchronized (em0Var) {
            ((kb) em0Var).b(str, obj);
        }
    }

    public void addAlgorithm(String str, d0 d0Var, String str2, Map<String, String> map) {
        addAlgorithm(str, d0Var, str2);
        addAttributes(str + NatsConstants.DOT + d0Var, map);
        addAttributes(str + ".OID." + d0Var, map);
    }

    public void addAlgorithm(String str, String str2) {
        if (containsKey(str)) {
            throw new IllegalStateException(zn.n("duplicate provider key (", str, ") found"));
        }
        put(str, str2);
    }

    public void addAlgorithm(String str, String str2, Map<String, String> map) {
        addAlgorithm(str, str2);
        addAttributes(str, map);
    }
}
