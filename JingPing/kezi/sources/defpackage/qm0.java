package defpackage;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class qm0 {
    public final String a;
    public final String b;
    public final boolean c;
    public final int d;
    public final String e;
    public final int f;
    public final int g;

    public qm0(String str, String str2, boolean z, int i, String str3, int i2) {
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = i;
        this.e = str3;
        this.f = i2;
        Locale locale = Locale.US;
        oy.i(locale, "US");
        String upperCase = str2.toUpperCase(locale);
        oy.i(upperCase, "this as java.lang.String).toUpperCase(locale)");
        this.g = vk0.l0(upperCase, "INT") ? 3 : (vk0.l0(upperCase, "CHAR") || vk0.l0(upperCase, "CLOB") || vk0.l0(upperCase, "TEXT")) ? 2 : vk0.l0(upperCase, "BLOB") ? 5 : (vk0.l0(upperCase, "REAL") || vk0.l0(upperCase, "FLOA") || vk0.l0(upperCase, "DOUB")) ? 4 : 1;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof qm0)) {
                return false;
            }
            qm0 qm0Var = (qm0) obj;
            if (this.d != qm0Var.d) {
                return false;
            }
            if (!this.a.equals(qm0Var.a) || this.c != qm0Var.c) {
                return false;
            }
            int i = qm0Var.f;
            String str = qm0Var.e;
            String str2 = this.e;
            int i2 = this.f;
            if (i2 == 1 && i == 2 && str2 != null && !nv0.m(str2, str)) {
                return false;
            }
            if (i2 == 2 && i == 1 && str != null && !nv0.m(str, str2)) {
                return false;
            }
            if (i2 != 0 && i2 == i) {
                if (str2 != null) {
                    if (!nv0.m(str2, str)) {
                        return false;
                    }
                } else if (str != null) {
                    return false;
                }
            }
            if (this.g != qm0Var.g) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return (((((this.a.hashCode() * 31) + this.g) * 31) + (this.c ? 1231 : 1237)) * 31) + this.d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Column{name='");
        sb.append(this.a);
        sb.append("', type='");
        sb.append(this.b);
        sb.append("', affinity='");
        sb.append(this.g);
        sb.append("', notNull=");
        sb.append(this.c);
        sb.append(", primaryKeyPosition=");
        sb.append(this.d);
        sb.append(", defaultValue='");
        String str = this.e;
        if (str == null) {
            str = "undefined";
        }
        return hk.k(sb, str, "'}");
    }
}
