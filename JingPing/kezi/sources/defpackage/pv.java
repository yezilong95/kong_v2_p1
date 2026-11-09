package defpackage;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import okhttp3.TlsVersion;

/* JADX INFO: loaded from: classes.dex */
public final class pv {
    public final TlsVersion a;
    public final rc b;
    public final List c;
    public final im0 d;

    public pv(TlsVersion tlsVersion, rc rcVar, List list, ou ouVar) {
        oy.j(tlsVersion, "tlsVersion");
        oy.j(list, "localCertificates");
        this.a = tlsVersion;
        this.b = rcVar;
        this.c = list;
        this.d = oy.G(new ju(ouVar));
    }

    public final List a() {
        return (List) this.d.getValue();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof pv)) {
            return false;
        }
        pv pvVar = (pv) obj;
        return pvVar.a == this.a && oy.c(pvVar.b, this.b) && oy.c(pvVar.a(), a()) && oy.c(pvVar.c, this.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((a().hashCode() + ((this.b.hashCode() + ((this.a.hashCode() + 527) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String type;
        String type2;
        List<Certificate> listA = a();
        ArrayList arrayList = new ArrayList(ce.Z(listA));
        for (Certificate certificate : listA) {
            if (certificate instanceof X509Certificate) {
                type2 = ((X509Certificate) certificate).getSubjectDN().toString();
            } else {
                type2 = certificate.getType();
                oy.i(type2, "type");
            }
            arrayList.add(type2);
        }
        String string = arrayList.toString();
        StringBuilder sb = new StringBuilder("Handshake{tlsVersion=");
        sb.append(this.a);
        sb.append(" cipherSuite=");
        sb.append(this.b);
        sb.append(" peerCertificates=");
        sb.append(string);
        sb.append(" localCertificates=");
        List<Certificate> list = this.c;
        ArrayList arrayList2 = new ArrayList(ce.Z(list));
        for (Certificate certificate2 : list) {
            if (certificate2 instanceof X509Certificate) {
                type = ((X509Certificate) certificate2).getSubjectDN().toString();
            } else {
                type = certificate2.getType();
                oy.i(type, "type");
            }
            arrayList2.add(type);
        }
        sb.append(arrayList2);
        sb.append('}');
        return sb.toString();
    }
}
