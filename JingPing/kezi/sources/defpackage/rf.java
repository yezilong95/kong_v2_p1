package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import okhttp3.TlsVersion;

/* JADX INFO: loaded from: classes.dex */
public final class rf {
    public static final rf e;
    public static final rf f;
    public final boolean a;
    public final boolean b;
    public final String[] c;
    public final String[] d;

    static {
        rc rcVar = rc.r;
        rc rcVar2 = rc.s;
        rc rcVar3 = rc.t;
        rc rcVar4 = rc.l;
        rc rcVar5 = rc.n;
        rc rcVar6 = rc.m;
        rc rcVar7 = rc.o;
        rc rcVar8 = rc.q;
        rc rcVar9 = rc.p;
        rc[] rcVarArr = {rcVar, rcVar2, rcVar3, rcVar4, rcVar5, rcVar6, rcVar7, rcVar8, rcVar9};
        rc[] rcVarArr2 = {rcVar, rcVar2, rcVar3, rcVar4, rcVar5, rcVar6, rcVar7, rcVar8, rcVar9, rc.j, rc.k, rc.h, rc.i, rc.f, rc.g, rc.e};
        qf qfVar = new qf();
        qfVar.b((rc[]) Arrays.copyOf(rcVarArr, 9));
        TlsVersion tlsVersion = TlsVersion.TLS_1_3;
        TlsVersion tlsVersion2 = TlsVersion.TLS_1_2;
        qfVar.e(tlsVersion, tlsVersion2);
        if (!qfVar.a) {
            throw new IllegalArgumentException("no TLS extensions for cleartext connections");
        }
        qfVar.b = true;
        qfVar.a();
        qf qfVar2 = new qf();
        qfVar2.b((rc[]) Arrays.copyOf(rcVarArr2, 16));
        qfVar2.e(tlsVersion, tlsVersion2);
        if (!qfVar2.a) {
            throw new IllegalArgumentException("no TLS extensions for cleartext connections");
        }
        qfVar2.b = true;
        e = qfVar2.a();
        qf qfVar3 = new qf();
        qfVar3.b((rc[]) Arrays.copyOf(rcVarArr2, 16));
        qfVar3.e(tlsVersion, tlsVersion2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
        if (!qfVar3.a) {
            throw new IllegalArgumentException("no TLS extensions for cleartext connections");
        }
        qfVar3.b = true;
        qfVar3.a();
        f = new rf(false, false, null, null);
    }

    public rf(boolean z, boolean z2, String[] strArr, String[] strArr2) {
        this.a = z;
        this.b = z2;
        this.c = strArr;
        this.d = strArr2;
    }

    public final List a() {
        String[] strArr = this.c;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(rc.b.h(str));
        }
        return ae.f0(arrayList);
    }

    public final boolean b(SSLSocket sSLSocket) {
        if (!this.a) {
            return false;
        }
        String[] strArr = this.d;
        if (strArr != null && !kq0.j(strArr, sSLSocket.getEnabledProtocols(), j80.b)) {
            return false;
        }
        String[] strArr2 = this.c;
        return strArr2 == null || kq0.j(strArr2, sSLSocket.getEnabledCipherSuites(), rc.c);
    }

    public final List c() {
        String[] strArr = this.d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            TlsVersion.Companion.getClass();
            arrayList.add(no0.a(str));
        }
        return ae.f0(arrayList);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof rf)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        rf rfVar = (rf) obj;
        boolean z = rfVar.a;
        boolean z2 = this.a;
        if (z2 != z) {
            return false;
        }
        if (z2) {
            return Arrays.equals(this.c, rfVar.c) && Arrays.equals(this.d, rfVar.d) && this.b == rfVar.b;
        }
        return true;
    }

    public final int hashCode() {
        if (!this.a) {
            return 17;
        }
        String[] strArr = this.c;
        int iHashCode = (527 + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String[] strArr2 = this.d;
        return ((iHashCode + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31) + (!this.b ? 1 : 0);
    }

    public final String toString() {
        if (!this.a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + Objects.toString(a(), "[all enabled]") + ", tlsVersions=" + Objects.toString(c(), "[all enabled]") + ", supportsTlsExtensions=" + this.b + ')';
    }
}
