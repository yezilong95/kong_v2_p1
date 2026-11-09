package defpackage;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import kotlin.text.Regex;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class fx {
    public static final char[] j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final int e;
    public final ArrayList f;
    public final String g;
    public final String h;
    public final boolean i;

    public fx(String str, String str2, String str3, String str4, int i, ArrayList arrayList, ArrayList arrayList2, String str5, String str6) {
        oy.j(str, "scheme");
        oy.j(str4, "host");
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = i;
        this.f = arrayList2;
        this.g = str5;
        this.h = str6;
        this.i = str.equals("https");
    }

    public final String a() {
        if (this.c.length() == 0) {
            return ExtensionRequestData.EMPTY_VALUE;
        }
        int length = this.a.length() + 3;
        String str = this.h;
        String strSubstring = str.substring(vk0.o0(str, ':', length, 4) + 1, vk0.o0(str, '@', 0, 6));
        oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String b() {
        int length = this.a.length() + 3;
        String str = this.h;
        int iO0 = vk0.o0(str, '/', length, 4);
        String strSubstring = str.substring(iO0, kq0.f(iO0, str.length(), str, "?#"));
        oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final ArrayList c() {
        int length = this.a.length() + 3;
        String str = this.h;
        int iO0 = vk0.o0(str, '/', length, 4);
        int iF = kq0.f(iO0, str.length(), str, "?#");
        ArrayList arrayList = new ArrayList();
        while (iO0 < iF) {
            int i = iO0 + 1;
            int iG = kq0.g(str, '/', i, iF);
            String strSubstring = str.substring(i, iG);
            oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            arrayList.add(strSubstring);
            iO0 = iG;
        }
        return arrayList;
    }

    public final String d() {
        if (this.f == null) {
            return null;
        }
        String str = this.h;
        int iO0 = vk0.o0(str, '?', 0, 6) + 1;
        String strSubstring = str.substring(iO0, kq0.g(str, '#', iO0, str.length()));
        oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String e() {
        if (this.b.length() == 0) {
            return ExtensionRequestData.EMPTY_VALUE;
        }
        int length = this.a.length() + 3;
        String str = this.h;
        String strSubstring = str.substring(length, kq0.f(length, str.length(), str, ":@"));
        oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof fx) && oy.c(((fx) obj).h, this.h);
    }

    public final String f() {
        ex exVar;
        try {
            exVar = new ex();
            exVar.c(this, "/...");
        } catch (IllegalArgumentException unused) {
            exVar = null;
        }
        oy.g(exVar);
        exVar.b = yo.j(ExtensionRequestData.EMPTY_VALUE, 0, 0, " \"':;<=>@[]^`{}|/\\?#", 251);
        exVar.c = yo.j(ExtensionRequestData.EMPTY_VALUE, 0, 0, " \"':;<=>@[]^`{}|/\\?#", 251);
        return exVar.a().h;
    }

    public final URI g() {
        String strSubstring;
        ex exVar = new ex();
        String str = this.a;
        exVar.a = str;
        exVar.b = e();
        exVar.c = a();
        exVar.d = this.d;
        oy.j(str, "scheme");
        int i = str.equals("http") ? 80 : str.equals("https") ? WebSocketImpl.DEFAULT_WSS_PORT : -1;
        int i2 = this.e;
        exVar.e = i2 != i ? i2 : -1;
        ArrayList arrayList = exVar.f;
        arrayList.clear();
        arrayList.addAll(c());
        String strD = d();
        exVar.g = strD != null ? yo.r(yo.j(strD, 0, 0, " \"'<>#", 211)) : null;
        if (this.g == null) {
            strSubstring = null;
        } else {
            String str2 = this.h;
            strSubstring = str2.substring(vk0.o0(str2, '#', 0, 6) + 1);
            oy.i(strSubstring, "this as java.lang.String).substring(startIndex)");
        }
        exVar.h = strSubstring;
        String str3 = exVar.d;
        exVar.d = str3 != null ? new Regex("[\"<>^`{|}]").replace(str3, ExtensionRequestData.EMPTY_VALUE) : null;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            arrayList.set(i3, yo.j((String) arrayList.get(i3), 0, 0, "[]", 227));
        }
        ArrayList arrayList2 = exVar.g;
        if (arrayList2 != null) {
            int size2 = arrayList2.size();
            for (int i4 = 0; i4 < size2; i4++) {
                String str4 = (String) arrayList2.get(i4);
                arrayList2.set(i4, str4 != null ? yo.j(str4, 0, 0, "\\^`{|}", 195) : null);
            }
        }
        String str5 = exVar.h;
        exVar.h = str5 != null ? yo.j(str5, 0, 0, " \"#<>\\^`{|}", 163) : null;
        String string = exVar.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e) {
            try {
                URI uriCreate = URI.create(new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").replace(string, ExtensionRequestData.EMPTY_VALUE));
                oy.i(uriCreate, "{\n      // Unlikely edge…Unexpected!\n      }\n    }");
                return uriCreate;
            } catch (Exception unused) {
                throw new RuntimeException(e);
            }
        }
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    public final String toString() {
        return this.h;
    }
}
