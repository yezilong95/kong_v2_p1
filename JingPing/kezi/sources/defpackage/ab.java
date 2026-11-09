package defpackage;

import okio.ByteString;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public final class ab {
    public final String a;
    public final String b;
    public final ByteString c;

    public ab(String str, String str2) {
        oy.j(str2, "pin");
        if ((!dl0.j0(str, "*.", false) || vk0.p0(str, Marker.ANY_MARKER, 1, 4) != -1) && ((!dl0.j0(str, "**.", false) || vk0.p0(str, Marker.ANY_MARKER, 2, 4) != -1) && vk0.p0(str, Marker.ANY_MARKER, 0, 6) != -1)) {
            throw new IllegalArgumentException("Unexpected pattern: ".concat(str).toString());
        }
        String strP = za0.P(str);
        if (strP == null) {
            throw new IllegalArgumentException("Invalid pattern: ".concat(str));
        }
        this.a = strP;
        if (dl0.j0(str2, "sha1/", false)) {
            this.b = "sha1";
            aa aaVar = ByteString.Companion;
            String strSubstring = str2.substring(5);
            oy.i(strSubstring, "this as java.lang.String).substring(startIndex)");
            aaVar.getClass();
            ByteString byteStringA = aa.a(strSubstring);
            if (byteStringA == null) {
                throw new IllegalArgumentException("Invalid pin hash: ".concat(str2));
            }
            this.c = byteStringA;
            return;
        }
        if (!dl0.j0(str2, "sha256/", false)) {
            throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': ".concat(str2));
        }
        this.b = "sha256";
        aa aaVar2 = ByteString.Companion;
        String strSubstring2 = str2.substring(7);
        oy.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
        aaVar2.getClass();
        ByteString byteStringA2 = aa.a(strSubstring2);
        if (byteStringA2 == null) {
            throw new IllegalArgumentException("Invalid pin hash: ".concat(str2));
        }
        this.c = byteStringA2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ab)) {
            return false;
        }
        ab abVar = (ab) obj;
        return oy.c(this.a, abVar.a) && oy.c(this.b, abVar.b) && oy.c(this.c, abVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return this.b + '/' + this.c.base64();
    }
}
