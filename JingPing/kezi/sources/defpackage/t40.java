package defpackage;

import java.util.regex.Matcher;

/* JADX INFO: loaded from: classes.dex */
public final class t40 implements s40 {
    public final Matcher a;
    public final CharSequence b;

    public t40(Matcher matcher, CharSequence charSequence) {
        this.a = matcher;
        this.b = charSequence;
    }

    public final t40 a() {
        Matcher matcher = this.a;
        int iEnd = matcher.end() + (matcher.end() == matcher.start() ? 1 : 0);
        CharSequence charSequence = this.b;
        if (iEnd > charSequence.length()) {
            return null;
        }
        Matcher matcher2 = matcher.pattern().matcher(charSequence);
        oy.i(matcher2, "matcher(...)");
        if (matcher2.find(iEnd)) {
            return new t40(matcher2, charSequence);
        }
        return null;
    }
}
