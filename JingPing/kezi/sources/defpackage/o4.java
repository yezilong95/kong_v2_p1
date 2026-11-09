package defpackage;

import android.content.res.Configuration;
import android.os.LocaleList;

/* JADX INFO: loaded from: classes.dex */
public abstract class o4 {
    public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
        LocaleList locales = configuration.getLocales();
        LocaleList locales2 = configuration2.getLocales();
        if (locales.equals(locales2)) {
            return;
        }
        configuration3.setLocales(locales2);
        configuration3.locale = configuration2.locale;
    }

    public static f30 b(Configuration configuration) {
        return f30.a(configuration.getLocales().toLanguageTags());
    }

    public static void c(f30 f30Var) {
        LocaleList.setDefault(LocaleList.forLanguageTags(f30Var.a.a.toLanguageTags()));
    }

    public static void d(Configuration configuration, f30 f30Var) {
        configuration.setLocales(LocaleList.forLanguageTags(f30Var.a.a.toLanguageTags()));
    }
}
