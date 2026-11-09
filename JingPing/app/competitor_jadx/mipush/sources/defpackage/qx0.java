package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class qx0 extends px0 {
    public static boolean k0(String str, String str2) {
        return n0(str, str2, 0, 2) >= 0;
    }

    public static final int l0(CharSequence charSequence) {
        t40.n(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int m0(CharSequence charSequence, String str, int i, boolean z) {
        t40.n(charSequence, "<this>");
        t40.n(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(str, i);
        }
        int length = charSequence.length();
        if (i < 0) {
            i = 0;
        }
        int length2 = charSequence.length();
        if (length > length2) {
            length = length2;
        }
        q40 q40Var = new q40(i, length, 1);
        boolean z2 = charSequence instanceof String;
        int i2 = q40Var.G;
        int i3 = q40Var.F;
        int i4 = q40Var.E;
        if (!z2 || str == null) {
            if ((i2 <= 0 || i4 > i3) && (i2 >= 0 || i3 > i4)) {
                return -1;
            }
            while (!q0(str, charSequence, i4, str.length(), z)) {
                if (i4 == i3) {
                    return -1;
                }
                i4 += i2;
            }
            return i4;
        }
        if ((i2 <= 0 || i4 > i3) && (i2 >= 0 || i3 > i4)) {
            return -1;
        }
        int i5 = i4;
        while (true) {
            String str2 = str;
            boolean z3 = z;
            if (p0(0, i5, str.length(), str2, (String) charSequence, z3)) {
                return i5;
            }
            if (i5 == i3) {
                return -1;
            }
            i5 += i2;
            str = str2;
            z = z3;
        }
    }

    public static /* synthetic */ int n0(CharSequence charSequence, String str, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m0(charSequence, str, i, false);
    }

    public static boolean o0(String str) {
        t40.n(str, "<this>");
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (!Character.isWhitespace(cCharAt) && !Character.isSpaceChar(cCharAt)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean p0(int i, int i2, int i3, String str, String str2, boolean z) {
        t40.n(str, "<this>");
        t40.n(str2, "other");
        return !z ? str.regionMatches(i, str2, i2, i3) : str.regionMatches(z, i, str2, i2, i3);
    }

    public static final boolean q0(String str, CharSequence charSequence, int i, int i2, boolean z) {
        char upperCase;
        char upperCase2;
        t40.n(str, "<this>");
        t40.n(charSequence, "other");
        if (i >= 0 && str.length() - i2 >= 0 && i <= charSequence.length() - i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char cCharAt = str.charAt(i3);
                char cCharAt2 = charSequence.charAt(i + i3);
                if (cCharAt == cCharAt2 || (z && ((upperCase = Character.toUpperCase(cCharAt)) == (upperCase2 = Character.toUpperCase(cCharAt2)) || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2)))) {
                }
            }
            return true;
        }
        return false;
    }

    public static final void r0(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(zn.j(i, "Limit must be non-negative, but was ").toString());
        }
    }

    public static String s0(String str, String str2) {
        t40.n(str2, "delimiter");
        int iN0 = n0(str, str2, 0, 6);
        if (iN0 == -1) {
            return str;
        }
        String strSubstring = str.substring(str2.length() + iN0, str.length());
        t40.m(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String t0(String str) {
        t40.n(str, "<this>");
        t40.n(str, "missingDelimiterValue");
        int iLastIndexOf = str.lastIndexOf(46, l0(str));
        if (iLastIndexOf == -1) {
            return str;
        }
        String strSubstring = str.substring(iLastIndexOf + 1, str.length());
        t40.m(strSubstring, "substring(...)");
        return strSubstring;
    }
}
