package defpackage;

import java.io.Serializable;
import java.util.regex.Pattern;
import kotlin.text.Regex;

/* JADX INFO: loaded from: classes.dex */
public final class fo0 implements Serializable {
    private static final long serialVersionUID = 0;
    public final String E;
    public final int F;

    public fo0(String str, int i) {
        this.E = str;
        this.F = i;
    }

    private final Object readResolve() {
        Pattern patternCompile = Pattern.compile(this.E, this.F);
        t40.m(patternCompile, "compile(...)");
        return new Regex(patternCompile);
    }
}
