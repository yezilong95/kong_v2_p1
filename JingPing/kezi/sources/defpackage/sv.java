package defpackage;

import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class sv {
    public static final ByteString d;
    public static final ByteString e;
    public static final ByteString f;
    public static final ByteString g;
    public static final ByteString h;
    public static final ByteString i;
    public final ByteString a;
    public final ByteString b;
    public final int c;

    static {
        ByteString.Companion.getClass();
        d = aa.c(":");
        e = aa.c(":status");
        f = aa.c(":method");
        g = aa.c(":path");
        h = aa.c(":scheme");
        i = aa.c(":authority");
    }

    public sv(ByteString byteString, ByteString byteString2) {
        oy.j(byteString, "name");
        oy.j(byteString2, "value");
        this.a = byteString;
        this.b = byteString2;
        this.c = byteString2.size() + byteString.size() + 32;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof sv)) {
            return false;
        }
        sv svVar = (sv) obj;
        return oy.c(this.a, svVar.a) && oy.c(this.b, svVar.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return this.a.utf8() + ": " + this.b.utf8();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public sv(String str, String str2) {
        this(aa.c(str), aa.c(str2));
        oy.j(str, "name");
        oy.j(str2, "value");
        ByteString.Companion.getClass();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public sv(String str, ByteString byteString) {
        this(byteString, aa.c(str));
        oy.j(byteString, "name");
        oy.j(str, "value");
        ByteString.Companion.getClass();
    }
}
