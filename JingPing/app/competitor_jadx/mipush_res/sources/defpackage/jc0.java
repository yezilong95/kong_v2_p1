package defpackage;

import java.util.regex.Matcher;

/* JADX INFO: loaded from: classes.dex */
public final class jc0 implements ic0 {
    public final Matcher a;
    public final CharSequence b;

    public jc0(Matcher matcher, CharSequence charSequence) {
        this.a = matcher;
        this.b = charSequence;
    }

    public final jc0 a() {
        Matcher matcher = this.a;
        int iEnd = matcher.end() + (matcher.end() == matcher.start() ? 1 : 0);
        CharSequence charSequence = this.b;
        if (iEnd > charSequence.length()) {
            return null;
        }
        Matcher matcher2 = matcher.pattern().matcher(charSequence);
        t40.m(matcher2, "matcher(...)");
        if (matcher2.find(iEnd)) {
            return new jc0(matcher2, charSequence);
        }
        return null;
    }
}
