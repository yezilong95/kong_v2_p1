package defpackage;

import java.security.AccessControlException;
import java.security.AccessController;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class cm0 {
    public static final ThreadLocal a = new ThreadLocal();

    public static int a(int i, String str) {
        String strB = b(str);
        return strB != null ? Integer.parseInt(strB) : i;
    }

    public static String b(String str) {
        String str2;
        String str3 = (String) AccessController.doPrivileged(new ef(str, 2));
        if (str3 != null) {
            return str3;
        }
        Map map = (Map) a.get();
        return (map == null || (str2 = (String) map.get(str)) == null) ? (String) AccessController.doPrivileged(new ef(str, 3)) : str2;
    }

    public static boolean c(String str) {
        try {
            String strB = b(str);
            if (strB != null && strB.length() == 4 && ((strB.charAt(0) == 't' || strB.charAt(0) == 'T') && ((strB.charAt(1) == 'r' || strB.charAt(1) == 'R') && (strB.charAt(2) == 'u' || strB.charAt(2) == 'U')))) {
                if (strB.charAt(3) != 'e') {
                    if (strB.charAt(3) == 'E') {
                    }
                }
                return true;
            }
        } catch (AccessControlException unused) {
        }
        return false;
    }
}
