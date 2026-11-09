package defpackage;

import android.os.Build;
import io.nats.client.BaseConsumeOptions;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class rb {
    static {
        int i = Build.VERSION.SDK_INT;
        qb qbVar = qb.a;
        if (i >= 30) {
            qbVar.a(30);
        }
        if (i >= 30) {
            qbVar.a(31);
        }
        if (i >= 30) {
            qbVar.a(33);
        }
        if (i >= 30) {
            qbVar.a(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT_WHEN_BYTES);
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
        t40.m(str, "CODENAME");
        if ("REL".equals(str)) {
            return false;
        }
        Locale locale = Locale.ROOT;
        String upperCase = str.toUpperCase(locale);
        t40.m(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        String upperCase2 = "Tiramisu".toUpperCase(locale);
        t40.m(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return upperCase.compareTo(upperCase2) >= 0;
    }
}
