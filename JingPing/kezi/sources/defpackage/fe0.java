package defpackage;

import java.io.Serializable;
import java.util.regex.Pattern;
import kotlin.text.Regex;

/* JADX INFO: loaded from: classes.dex */
public final class fe0 implements Serializable {
    private static final long serialVersionUID = 0;
    public final String b;
    public final int c;

    public fe0(String str, int i) {
        this.b = str;
        this.c = i;
    }

    private final Object readResolve() {
        Pattern patternCompile = Pattern.compile(this.b, this.c);
        oy.i(patternCompile, "compile(...)");
        return new Regex(patternCompile);
    }
}
