package defpackage;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class c60 {
    public static final Pattern c = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    public static final Pattern d = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    public final String a;
    public final String[] b;

    public c60(String str, String[] strArr) {
        this.a = str;
        this.b = strArr;
    }

    public final Charset a(Charset charset) {
        String str;
        String[] strArr = this.b;
        int i = 0;
        int iA = xi.A(0, strArr.length - 1, 2);
        if (iA < 0) {
            str = null;
            break;
        }
        while (!dl0.f0(strArr[i], "charset")) {
            if (i == iA) {
                str = null;
                break;
            }
            i += 2;
        }
        str = strArr[i + 1];
        if (str == null) {
            return charset;
        }
        try {
            return Charset.forName(str);
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof c60) && oy.c(((c60) obj).a, this.a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a;
    }
}
