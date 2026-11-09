package defpackage;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public abstract class ud {
    public static final Charset a;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        t40.m(charsetForName, "forName(...)");
        a = charsetForName;
        t40.m(Charset.forName("UTF-16"), "forName(...)");
        t40.m(Charset.forName("UTF-16BE"), "forName(...)");
        t40.m(Charset.forName("UTF-16LE"), "forName(...)");
        t40.m(Charset.forName("US-ASCII"), "forName(...)");
        t40.m(Charset.forName("ISO-8859-1"), "forName(...)");
    }
}
