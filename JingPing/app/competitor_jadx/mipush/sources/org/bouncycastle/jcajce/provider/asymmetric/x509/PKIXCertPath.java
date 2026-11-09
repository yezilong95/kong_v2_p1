package org.bouncycastle.jcajce.provider.asymmetric.x509;

import defpackage.d0;
import defpackage.jx0;
import defpackage.sk0;
import defpackage.vj0;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.security.cert.CertPath;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PKIXCertPath extends CertPath {
    public static final List E;

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add("PkiPath");
        arrayList.add("PEM");
        arrayList.add("PKCS7");
        E = Collections.unmodifiableList(arrayList);
    }

    @Override // java.security.cert.CertPath
    public List getCertificates() {
        return Collections.unmodifiableList(new ArrayList((Collection) null));
    }

    @Override // java.security.cert.CertPath
    public byte[] getEncoded() throws CertificateEncodingException {
        Iterator encodings = getEncodings();
        if (!encodings.hasNext()) {
            return null;
        }
        Object next = encodings.next();
        if (next instanceof String) {
            return getEncoded((String) next);
        }
        return null;
    }

    @Override // java.security.cert.CertPath
    public Iterator getEncodings() {
        return E.iterator();
    }

    @Override // java.security.cert.CertPath
    public byte[] getEncoded(String str) throws CertificateEncodingException {
        if (str.equalsIgnoreCase("PkiPath")) {
            throw null;
        }
        if (str.equalsIgnoreCase("PKCS7")) {
            d0 d0Var = vj0.a;
            throw null;
        }
        if (!str.equalsIgnoreCase("PEM")) {
            throw new CertificateEncodingException("unsupported encoding: ".concat(str));
        }
        new sk0(new OutputStreamWriter(new ByteArrayOutputStream()));
        String str2 = jx0.a;
        try {
            throw null;
        } catch (Exception unused) {
            throw new CertificateEncodingException("can't encode certificate for PEM encoded path");
        }
    }
}
