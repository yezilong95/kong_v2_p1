package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class vk0 extends dl0 {
    public static String A0(int i, String str) {
        if (i < 0) {
            throw new IllegalArgumentException(hk.h(i, "Requested character count ", " is less than zero.").toString());
        }
        int length = str.length();
        if (i > length) {
            i = length;
        }
        String strSubstring = str.substring(0, i);
        oy.i(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static CharSequence B0(String str) {
        oy.j(str, "<this>");
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean zJ = jq0.J(str.charAt(!z ? i : length));
            if (z) {
                if (!zJ) {
                    break;
                }
                length--;
            } else if (zJ) {
                i++;
            } else {
                z = true;
            }
        }
        return str.subSequence(i, length + 1);
    }

    public static boolean k0(CharSequence charSequence, char c) {
        oy.j(charSequence, "<this>");
        return o0(charSequence, c, 0, 2) >= 0;
    }

    public static boolean l0(CharSequence charSequence, String str) {
        oy.j(charSequence, "<this>");
        return p0(charSequence, str, 0, 2) >= 0;
    }

    public static final int m0(CharSequence charSequence) {
        oy.j(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int n0(CharSequence charSequence, String str, int i, boolean z) {
        oy.j(charSequence, "<this>");
        oy.j(str, "string");
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
        my myVar = new my(i, length, 1);
        boolean z2 = charSequence instanceof String;
        int i2 = myVar.d;
        int i3 = myVar.c;
        int i4 = myVar.b;
        if (!z2 || str == null) {
            boolean z3 = z;
            if ((i2 <= 0 || i4 > i3) && (i2 >= 0 || i3 > i4)) {
                return -1;
            }
            while (true) {
                CharSequence charSequence2 = charSequence;
                boolean z4 = z3;
                z3 = z4;
                if (u0(str, 0, charSequence2, i4, str.length(), z4)) {
                    return i4;
                }
                if (i4 == i3) {
                    return -1;
                }
                i4 += i2;
                charSequence = charSequence2;
            }
        } else {
            if ((i2 <= 0 || i4 > i3) && (i2 >= 0 || i3 > i4)) {
                return -1;
            }
            int i5 = i4;
            while (true) {
                String str2 = str;
                boolean z5 = z;
                if (dl0.g0(0, i5, str.length(), str2, (String) charSequence, z5)) {
                    return i5;
                }
                if (i5 == i3) {
                    return -1;
                }
                i5 += i2;
                str = str2;
                z = z5;
            }
        }
    }

    public static int o0(CharSequence charSequence, char c, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        oy.j(charSequence, "<this>");
        return !(charSequence instanceof String) ? q0(charSequence, new char[]{c}, i, false) : ((String) charSequence).indexOf(c, i);
    }

    public static /* synthetic */ int p0(CharSequence charSequence, String str, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return n0(charSequence, str, i, false);
    }

    public static final int q0(CharSequence charSequence, char[] cArr, int i, boolean z) {
        oy.j(charSequence, "<this>");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(g7.e0(cArr), i);
        }
        if (i < 0) {
            i = 0;
        }
        int iM0 = m0(charSequence);
        if (i > iM0) {
            return -1;
        }
        while (true) {
            char cCharAt = charSequence.charAt(i);
            for (char c : cArr) {
                if (jq0.o(c, cCharAt, z)) {
                    return i;
                }
            }
            if (i == iM0) {
                return -1;
            }
            i++;
        }
    }

    public static boolean r0(String str) {
        oy.j(str, "<this>");
        for (int i = 0; i < str.length(); i++) {
            if (!jq0.J(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int s0(String str, char c, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = m0(str);
        }
        oy.j(str, "<this>");
        return str.lastIndexOf(c, i);
    }

    public static final List t0(String str) {
        oy.j(str, "<this>");
        w0(0);
        return zh0.d0(new ek(new ek(str, new fg0(2, g7.W(new String[]{"\r\n", "\n", "\r"}))), new f(1, str)));
    }

    public static final boolean u0(String str, int i, CharSequence charSequence, int i2, int i3, boolean z) {
        oy.j(str, "<this>");
        oy.j(charSequence, "other");
        if (i2 >= 0 && i >= 0 && i <= str.length() - i3 && i2 <= charSequence.length() - i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                if (jq0.o(str.charAt(i + i4), charSequence.charAt(i2 + i4), z)) {
                }
            }
            return true;
        }
        return false;
    }

    public static String v0(String str, String str2) {
        if (!dl0.j0(str, str2, false)) {
            return str;
        }
        String strSubstring = str.substring(str2.length());
        oy.i(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final void w0(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(hk.g(i, "Limit must be non-negative, but was ").toString());
        }
    }

    public static List x0(String str, char[] cArr) {
        oy.j(str, "<this>");
        if (cArr.length == 1) {
            String strValueOf = String.valueOf(cArr[0]);
            w0(0);
            int iN0 = n0(str, strValueOf, 0, false);
            if (iN0 == -1) {
                return xi.G(str.toString());
            }
            ArrayList arrayList = new ArrayList(10);
            int length = 0;
            do {
                arrayList.add(str.subSequence(length, iN0).toString());
                length = strValueOf.length() + iN0;
                iN0 = n0(str, strValueOf, length, false);
            } while (iN0 != -1);
            arrayList.add(str.subSequence(length, str.length()).toString());
            return arrayList;
        }
        w0(0);
        bi0 bi0Var = new bi0(new ek(str, new fg0(1, cArr)));
        ArrayList arrayList2 = new ArrayList(ce.Z(bi0Var));
        Iterator it = bi0Var.iterator();
        while (true) {
            dk dkVar = (dk) it;
            if (!dkVar.hasNext()) {
                return arrayList2;
            }
            my myVar = (my) dkVar.next();
            oy.j(myVar, "range");
            arrayList2.add(str.subSequence(myVar.b, myVar.c + 1).toString());
        }
    }

    public static String y0(String str, String str2) {
        oy.j(str2, "delimiter");
        int iP0 = p0(str, str2, 0, 6);
        if (iP0 == -1) {
            return str;
        }
        String strSubstring = str.substring(str2.length() + iP0, str.length());
        oy.i(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String z0(String str) {
        oy.j(str, "<this>");
        oy.j(str, "missingDelimiterValue");
        int iS0 = s0(str, '.', 0, 6);
        if (iS0 == -1) {
            return str;
        }
        String strSubstring = str.substring(iS0 + 1, str.length());
        oy.i(strSubstring, "substring(...)");
        return strSubstring;
    }
}
