package org.bouncycastle.jce.provider;

import defpackage.bv;
import defpackage.c30;
import defpackage.d0;
import defpackage.i6;
import defpackage.jx0;
import defpackage.t;
import defpackage.vc;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes.dex */
public class X509CertificateObject extends X509Certificate {
    public boolean E;
    public int F;
    public final i6 G = new i6(24);

    public X509CertificateObject(vc vcVar) throws CertificateParsingException {
        try {
            throw null;
        } catch (Exception e) {
            throw new CertificateParsingException("cannot construct BasicConstraints: " + e);
        }
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity() throws CertificateNotYetValidException, CertificateExpiredException {
        checkValidity(new Date());
    }

    @Override // java.security.cert.Certificate
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Certificate)) {
            return false;
        }
        try {
            return Arrays.equals(getEncoded(), ((Certificate) obj).getEncoded());
        } catch (CertificateEncodingException unused) {
            return false;
        }
    }

    public t getBagAttribute(d0 d0Var) {
        return this.G.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.G.G).elements();
    }

    @Override // java.security.cert.X509Certificate
    public int getBasicConstraints() {
        return -1;
    }

    @Override // java.security.cert.X509Extension
    public Set getCriticalExtensionOIDs() {
        if (getVersion() != 3) {
            return null;
        }
        new HashSet();
        throw null;
    }

    @Override // java.security.cert.Certificate
    public byte[] getEncoded() throws CertificateEncodingException {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public List getExtendedKeyUsage() throws CertificateParsingException {
        throw null;
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public Collection getIssuerAlternativeNames() throws CertificateParsingException {
        bv.F.r();
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public Principal getIssuerDN() {
        return getIssuerX500Principal();
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getIssuerUniqueID() {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getIssuerX500Principal() {
        try {
            throw null;
        } catch (IOException unused) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getKeyUsage() {
        return null;
    }

    @Override // java.security.cert.X509Extension
    public Set getNonCriticalExtensionOIDs() {
        if (getVersion() != 3) {
            return null;
        }
        new HashSet();
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotAfter() {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotBefore() {
        throw null;
    }

    @Override // java.security.cert.Certificate
    public PublicKey getPublicKey() {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public BigInteger getSerialNumber() {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgName() {
        Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider != null) {
            String property = provider.getProperty("Alg.Alias.Signature." + getSigAlgOID());
            if (property != null) {
                return property;
            }
        }
        Provider[] providers = Security.getProviders();
        for (int i = 0; i != providers.length; i++) {
            String property2 = providers[i].getProperty("Alg.Alias.Signature." + getSigAlgOID());
            if (property2 != null) {
                return property2;
            }
        }
        return getSigAlgOID();
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgOID() {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSigAlgParams() {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSignature() {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public Collection getSubjectAlternativeNames() throws CertificateParsingException {
        bv.E.r();
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public Principal getSubjectDN() {
        return getSubjectX500Principal();
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getSubjectUniqueID() {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getSubjectX500Principal() {
        try {
            throw null;
        } catch (IOException unused) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getTBSCertificate() throws CertificateEncodingException {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public int getVersion() {
        throw null;
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        if (getVersion() != 3) {
            return false;
        }
        throw null;
    }

    @Override // java.security.cert.Certificate
    public synchronized int hashCode() {
        try {
            if (!this.E) {
                int i = 0;
                try {
                    byte[] encoded = getEncoded();
                    int i2 = 0;
                    for (int i3 = 1; i3 < encoded.length; i3++) {
                        i2 += encoded[i3] * i3;
                    }
                    i = i2;
                } catch (CertificateEncodingException unused) {
                }
                this.F = i;
                this.E = true;
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.F;
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.G.N(d0Var, tVar);
    }

    @Override // java.security.cert.Certificate
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("  [0]         Version: ");
        String str = jx0.a;
        stringBuffer.append(getVersion());
        stringBuffer.append(str);
        stringBuffer.append("         SerialNumber: ");
        stringBuffer.append(getSerialNumber());
        stringBuffer.append(str);
        stringBuffer.append("             IssuerDN: ");
        stringBuffer.append(getIssuerDN());
        stringBuffer.append(str);
        stringBuffer.append("           Start Date: ");
        stringBuffer.append(getNotBefore());
        stringBuffer.append(str);
        stringBuffer.append("           Final Date: ");
        stringBuffer.append(getNotAfter());
        stringBuffer.append(str);
        stringBuffer.append("            SubjectDN: ");
        stringBuffer.append(getSubjectDN());
        stringBuffer.append(str);
        stringBuffer.append("           Public Key: ");
        stringBuffer.append(getPublicKey());
        stringBuffer.append(str);
        stringBuffer.append("  Signature Algorithm: ");
        stringBuffer.append(getSigAlgName());
        stringBuffer.append(str);
        byte[] signature = getSignature();
        stringBuffer.append("            Signature: ");
        stringBuffer.append(new String(c30.c(signature, 0, 20)));
        stringBuffer.append(str);
        int i = 20;
        while (i < signature.length) {
            int length = signature.length - 20;
            stringBuffer.append("                       ");
            stringBuffer.append(i < length ? new String(c30.c(signature, i, 20)) : new String(c30.c(signature, i, signature.length - i)));
            stringBuffer.append(str);
            i += 20;
        }
        throw null;
    }

    @Override // java.security.cert.Certificate
    public final void verify(PublicKey publicKey) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, NoSuchProviderException {
        throw null;
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity(Date date) throws CertificateNotYetValidException, CertificateExpiredException {
        if (date.getTime() > getNotAfter().getTime()) {
            throw null;
        }
        if (date.getTime() < getNotBefore().getTime()) {
            throw null;
        }
    }

    @Override // java.security.cert.Certificate
    public final void verify(PublicKey publicKey, String str) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, NoSuchProviderException {
        throw null;
    }

    @Override // java.security.cert.X509Certificate, java.security.cert.Certificate
    public final void verify(PublicKey publicKey, Provider provider) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException {
        throw null;
    }
}
