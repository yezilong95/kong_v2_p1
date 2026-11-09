package defpackage;

import android.os.Build;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class y9 {
    static {
        int i = Build.VERSION.SDK_INT;
        x9 x9Var = x9.a;
        if (i >= 30) {
            x9Var.a(30);
        }
        if (i >= 30) {
            x9Var.a(31);
        }
        if (i >= 30) {
            x9Var.a(33);
        }
        if (i >= 30) {
            x9Var.a(1000000);
        }
    }

    public static final boolean a() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 33) {
            return true;
        }
        if (i < 32) {
            return false;
        }
        String str = Build.VERSION.CODENAME;
        oy.i(str, "CODENAME");
        if ("REL".equals(str)) {
            return false;
        }
        Locale locale = Locale.ROOT;
        String upperCase = str.toUpperCase(locale);
        oy.i(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        String upperCase2 = "Tiramisu".toUpperCase(locale);
        oy.i(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return upperCase.compareTo(upperCase2) >= 0;
    }
}
