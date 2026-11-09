package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class dl0 extends cl0 {
    public static boolean e0(String str, String str2) {
        oy.j(str, "<this>");
        oy.j(str2, "suffix");
        return str.endsWith(str2);
    }

    public static boolean f0(String str, String str2) {
        return str == null ? str2 == null : str.equalsIgnoreCase(str2);
    }

    public static final boolean g0(int i, int i2, int i3, String str, String str2, boolean z) {
        oy.j(str, "<this>");
        oy.j(str2, "other");
        return !z ? str.regionMatches(i, str2, i2, i3) : str.regionMatches(z, i, str2, i2, i3);
    }

    public static String h0(String str, String str2, String str3) {
        oy.j(str, "<this>");
        int iN0 = vk0.n0(str, str2, 0, false);
        if (iN0 < 0) {
            return str;
        }
        int length = str2.length();
        int i = length >= 1 ? length : 1;
        int length2 = str3.length() + (str.length() - length);
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        int i2 = 0;
        do {
            sb.append((CharSequence) str, i2, iN0);
            sb.append(str3);
            i2 = iN0 + length;
            if (iN0 >= str.length()) {
                break;
            }
            iN0 = vk0.n0(str, str2, iN0 + i, false);
        } while (iN0 > 0);
        sb.append((CharSequence) str, i2, str.length());
        String string = sb.toString();
        oy.i(string, "toString(...)");
        return string;
    }

    public static boolean i0(String str, String str2, int i, boolean z) {
        oy.j(str, "<this>");
        return !z ? str.startsWith(str2, i) : g0(i, 0, str2.length(), str, str2, z);
    }

    public static boolean j0(String str, String str2, boolean z) {
        oy.j(str, "<this>");
        oy.j(str2, "prefix");
        return !z ? str.startsWith(str2) : g0(0, 0, str2.length(), str, str2, z);
    }
}
