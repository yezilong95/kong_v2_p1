package defpackage;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public abstract class zb {
    public static final Charset a;
    public static volatile Charset b;
    public static volatile Charset c;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        oy.i(charsetForName, "forName(...)");
        a = charsetForName;
        oy.i(Charset.forName("UTF-16"), "forName(...)");
        oy.i(Charset.forName("UTF-16BE"), "forName(...)");
        oy.i(Charset.forName("UTF-16LE"), "forName(...)");
        oy.i(Charset.forName("US-ASCII"), "forName(...)");
        oy.i(Charset.forName("ISO-8859-1"), "forName(...)");
    }
}
