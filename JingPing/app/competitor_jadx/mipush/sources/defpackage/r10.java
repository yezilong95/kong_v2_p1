package defpackage;

import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public final class r10 implements AlgorithmParameterSpec, t10 {
    public final v10 a;
    public final String b;
    public final String c;
    public final String d;

    public r10(v10 v10Var) {
        this.a = v10Var;
        this.c = rj.d.r();
        this.d = null;
    }

    public final boolean equals(Object obj) {
        String str;
        String str2;
        if (obj instanceof r10) {
            r10 r10Var = (r10) obj;
            if (this.a.equals(r10Var.a) && ((str = this.c) == (str2 = r10Var.c) || (str != null && str.equals(str2)))) {
                String str3 = this.d;
                String str4 = r10Var.d;
                if (str3 == str4) {
                    return true;
                }
                if (str3 != null && str3.equals(str4)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() ^ this.c.hashCode();
        String str = this.d;
        return iHashCode ^ (str != null ? str.hashCode() : 0);
    }

    public r10(String str, String str2, String str3) {
        q10 q10Var;
        try {
            q10Var = (q10) p10.b.get(new d0(str));
        } catch (IllegalArgumentException unused) {
            d0 d0Var = (d0) p10.a.get(str);
            if (d0Var != null) {
                str = d0Var.r();
                q10Var = (q10) p10.b.get(d0Var);
            } else {
                q10Var = null;
            }
        }
        if (q10Var == null) {
            throw new IllegalArgumentException("no key parameter set for passed in name/OID.");
        }
        this.a = new v10(q10Var.E.p(), q10Var.F.p(), q10Var.G.p());
        this.b = str;
        this.c = str2;
        this.d = str3;
    }
}
