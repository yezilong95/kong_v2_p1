package defpackage;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class f30 {
    public static final f30 b = new f30(new g30(e30.a(new Locale[0])));
    public final g30 a;

    public f30(g30 g30Var) {
        this.a = g30Var;
    }

    public static f30 a(String str) {
        if (str == null || str.isEmpty()) {
            return b;
        }
        String[] strArrSplit = str.split(",", -1);
        int length = strArrSplit.length;
        Locale[] localeArr = new Locale[length];
        for (int i = 0; i < length; i++) {
            localeArr[i] = d30.a(strArrSplit[i]);
        }
        return new f30(new g30(e30.a(localeArr)));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof f30) {
            return this.a.equals(((f30) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.a.hashCode();
    }

    public final String toString() {
        return this.a.a.toString();
    }
}
