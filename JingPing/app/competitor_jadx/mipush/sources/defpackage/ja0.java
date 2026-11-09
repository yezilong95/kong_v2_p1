package defpackage;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class ja0 {
    public static final ja0 b = new ja0(new ka0(ia0.a(new Locale[0])));
    public final ka0 a;

    public ja0(ka0 ka0Var) {
        this.a = ka0Var;
    }

    public static ja0 a(String str) {
        if (str == null || str.isEmpty()) {
            return b;
        }
        String[] strArrSplit = str.split(",", -1);
        int length = strArrSplit.length;
        Locale[] localeArr = new Locale[length];
        for (int i = 0; i < length; i++) {
            localeArr[i] = ha0.a(strArrSplit[i]);
        }
        return new ja0(new ka0(ia0.a(localeArr)));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ja0) {
            return this.a.equals(((ja0) obj).a);
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
