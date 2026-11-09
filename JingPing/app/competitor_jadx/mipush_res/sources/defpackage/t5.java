package defpackage;

import android.content.res.Configuration;
import android.os.LocaleList;

/* JADX INFO: loaded from: classes.dex */
public abstract class t5 {
    public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
        LocaleList locales = configuration.getLocales();
        LocaleList locales2 = configuration2.getLocales();
        if (locales.equals(locales2)) {
            return;
        }
        configuration3.setLocales(locales2);
        configuration3.locale = configuration2.locale;
    }

    public static ja0 b(Configuration configuration) {
        return ja0.a(configuration.getLocales().toLanguageTags());
    }

    public static void c(ja0 ja0Var) {
        LocaleList.setDefault(LocaleList.forLanguageTags(ja0Var.a.a.toLanguageTags()));
    }

    public static void d(Configuration configuration, ja0 ja0Var) {
        configuration.setLocales(LocaleList.forLanguageTags(ja0Var.a.a.toLanguageTags()));
    }
}
