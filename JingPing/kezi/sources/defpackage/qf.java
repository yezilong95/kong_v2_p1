package defpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.TlsVersion;

/* JADX INFO: loaded from: classes.dex */
public final class qf {
    public boolean a = true;
    public boolean b;
    public Object c;
    public Serializable d;

    public rf a() {
        return new rf(this.a, this.b, (String[]) this.c, (String[]) this.d);
    }

    public void b(rc... rcVarArr) {
        oy.j(rcVarArr, "cipherSuites");
        if (!this.a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections");
        }
        ArrayList arrayList = new ArrayList(rcVarArr.length);
        for (rc rcVar : rcVarArr) {
            arrayList.add(rcVar.a);
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        c((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public void c(String... strArr) {
        oy.j(strArr, "cipherSuites");
        if (!this.a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
        this.c = (String[]) strArr.clone();
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.Serializable, java.lang.String[]] */
    public void d(String... strArr) {
        oy.j(strArr, "tlsVersions");
        if (!this.a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one TLS version is required");
        }
        this.d = (String[]) strArr.clone();
    }

    public void e(TlsVersion... tlsVersionArr) {
        if (!this.a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections");
        }
        ArrayList arrayList = new ArrayList(tlsVersionArr.length);
        for (TlsVersion tlsVersion : tlsVersionArr) {
            arrayList.add(tlsVersion.javaName());
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        d((String[]) Arrays.copyOf(strArr, strArr.length));
    }
}
