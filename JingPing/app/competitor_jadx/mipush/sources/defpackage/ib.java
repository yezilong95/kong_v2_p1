package defpackage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.crypto.CryptoServiceConstraintsException;
import org.bouncycastle.crypto.a;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public final class ib implements PrivilegedAction {
    public final /* synthetic */ int a;
    public final /* synthetic */ Serializable b;

    public /* synthetic */ ib(int i, Serializable serializable) {
        this.a = i;
        this.b = serializable;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        String str;
        String str2;
        switch (this.a) {
            case 0:
                String[] strArr = BouncyCastleProvider.O;
                BouncyCastleProvider bouncyCastleProvider = (BouncyCastleProvider) this.b;
                BouncyCastleProvider.c("org.bouncycastle.jcajce.provider.digest.", strArr);
                BouncyCastleProvider.c("org.bouncycastle.jcajce.provider.symmetric.", BouncyCastleProvider.J);
                BouncyCastleProvider.c("org.bouncycastle.jcajce.provider.symmetric.", BouncyCastleProvider.K);
                int i = 0;
                while (true) {
                    sj[] sjVarArr = BouncyCastleProvider.L;
                    if (i == sjVarArr.length) {
                        BouncyCastleProvider.c("org.bouncycastle.jcajce.provider.asymmetric.", BouncyCastleProvider.M);
                        BouncyCastleProvider.c("org.bouncycastle.jcajce.provider.asymmetric.", BouncyCastleProvider.N);
                        BouncyCastleProvider.c("org.bouncycastle.jcajce.provider.keystore.", BouncyCastleProvider.P);
                        BouncyCastleProvider.c("org.bouncycastle.jcajce.provider.drbg.", BouncyCastleProvider.Q);
                        if (BouncyCastleProvider.I != null) {
                            str = "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi_8";
                            bouncyCastleProvider.put("CertPathValidator.RFC5280", "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi_8");
                            str2 = "org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi_8";
                        } else {
                            str = "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi";
                            bouncyCastleProvider.put("CertPathValidator.RFC5280", "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi");
                            str2 = "org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi";
                        }
                        bouncyCastleProvider.put("CertPathBuilder.RFC5280", str2);
                        bouncyCastleProvider.put("CertPathValidator.RFC3280", str);
                        bouncyCastleProvider.put("CertPathBuilder.RFC3280", str2);
                        bouncyCastleProvider.put("CertPathValidator.PKIX", str);
                        bouncyCastleProvider.put("CertPathBuilder.PKIX", str2);
                        bouncyCastleProvider.put("CertStore.Collection", "org.bouncycastle.jce.provider.CertStoreCollectionSpi");
                        bouncyCastleProvider.put("CertStore.LDAP", "org.bouncycastle.jce.provider.X509LDAPCertStoreSpi");
                        bouncyCastleProvider.put("CertStore.Multi", "org.bouncycastle.jce.provider.MultiCertStoreSpi");
                        bouncyCastleProvider.put("Alg.Alias.CertStore.X509LDAP", "LDAP");
                        bouncyCastleProvider.getService("SecureRandom", "DEFAULT");
                        return null;
                    }
                    sj sjVar = sjVarArr[i];
                    try {
                        a.a();
                        BouncyCastleProvider.d("org.bouncycastle.jcajce.provider.symmetric.", sjVar.a());
                    } catch (CryptoServiceConstraintsException unused) {
                        Level level = Level.FINE;
                        Logger logger = BouncyCastleProvider.F;
                        if (logger.isLoggable(level)) {
                            logger.fine("service for " + sjVar.a() + " ignored due to constraints");
                        }
                    }
                    i++;
                }
                break;
            default:
                try {
                    return ((URL) this.b).openStream();
                } catch (IOException unused2) {
                    throw new IllegalStateException("unable to open random source");
                }
        }
    }
}
