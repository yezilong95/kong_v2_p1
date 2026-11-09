package kotlin.text;

import defpackage.ee0;
import defpackage.ek;
import defpackage.fe0;
import defpackage.ge0;
import defpackage.he;
import defpackage.he0;
import defpackage.ie0;
import defpackage.oy;
import defpackage.pu;
import defpackage.s40;
import defpackage.t40;
import defpackage.vk0;
import defpackage.xh0;
import defpackage.xi;
import defpackage.za0;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class Regex implements Serializable {
    public static final ee0 Companion = new ee0();
    public final Pattern b;
    public Set c;

    public Regex(Pattern pattern) {
        oy.j(pattern, "nativePattern");
        this.b = pattern;
    }

    public static /* synthetic */ s40 find$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.find(charSequence, i);
    }

    public static /* synthetic */ xh0 findAll$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.findAll(charSequence, i);
    }

    public static /* synthetic */ List split$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.split(charSequence, i);
    }

    public static /* synthetic */ xh0 splitToSequence$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.splitToSequence(charSequence, i);
    }

    private final Object writeReplace() {
        Pattern pattern = this.b;
        String strPattern = pattern.pattern();
        oy.i(strPattern, "pattern(...)");
        return new fe0(strPattern, pattern.flags());
    }

    public final boolean containsMatchIn(CharSequence charSequence) {
        oy.j(charSequence, "input");
        return this.b.matcher(charSequence).find();
    }

    public final s40 find(CharSequence charSequence, int i) {
        oy.j(charSequence, "input");
        Matcher matcher = this.b.matcher(charSequence);
        oy.i(matcher, "matcher(...)");
        if (matcher.find(i)) {
            return new t40(matcher, charSequence);
        }
        return null;
    }

    public final xh0 findAll(CharSequence charSequence, int i) {
        oy.j(charSequence, "input");
        if (i >= 0 && i <= charSequence.length()) {
            ge0 ge0Var = new ge0(this, charSequence, i);
            he0 he0Var = he0.j;
            oy.j(he0Var, "nextFunction");
            return new ek(ge0Var, he0Var);
        }
        throw new IndexOutOfBoundsException("Start index out of bounds: " + i + ", input length: " + charSequence.length());
    }

    public final Set<RegexOption> getOptions() {
        Set<RegexOption> set = this.c;
        if (set != null) {
            return set;
        }
        final int iFlags = this.b.flags();
        EnumSet enumSetAllOf = EnumSet.allOf(RegexOption.class);
        oy.g(enumSetAllOf);
        pu puVar = new pu() { // from class: kotlin.text.Regex$special$$inlined$fromInt$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // defpackage.pu
            public final Boolean invoke(RegexOption regexOption) {
                RegexOption regexOption2 = regexOption;
                return Boolean.valueOf((regexOption2.getMask() & iFlags) == regexOption2.getValue());
            }
        };
        Iterator it = enumSetAllOf.iterator();
        while (it.hasNext()) {
            if (!((Boolean) puVar.invoke(it.next())).booleanValue()) {
                it.remove();
            }
        }
        Set<RegexOption> setUnmodifiableSet = Collections.unmodifiableSet(enumSetAllOf);
        oy.i(setUnmodifiableSet, "unmodifiableSet(...)");
        this.c = setUnmodifiableSet;
        return setUnmodifiableSet;
    }

    public final String getPattern() {
        String strPattern = this.b.pattern();
        oy.i(strPattern, "pattern(...)");
        return strPattern;
    }

    public final s40 matchAt(CharSequence charSequence, int i) {
        oy.j(charSequence, "input");
        Matcher matcherRegion = this.b.matcher(charSequence).useAnchoringBounds(false).useTransparentBounds(true).region(i, charSequence.length());
        if (matcherRegion.lookingAt()) {
            return new t40(matcherRegion, charSequence);
        }
        return null;
    }

    public final s40 matchEntire(CharSequence charSequence) {
        oy.j(charSequence, "input");
        Matcher matcher = this.b.matcher(charSequence);
        oy.i(matcher, "matcher(...)");
        if (matcher.matches()) {
            return new t40(matcher, charSequence);
        }
        return null;
    }

    public final boolean matches(CharSequence charSequence) {
        oy.j(charSequence, "input");
        return this.b.matcher(charSequence).matches();
    }

    public final boolean matchesAt(CharSequence charSequence, int i) {
        oy.j(charSequence, "input");
        return this.b.matcher(charSequence).useAnchoringBounds(false).useTransparentBounds(true).region(i, charSequence.length()).lookingAt();
    }

    public final String replace(CharSequence charSequence, String str) {
        oy.j(charSequence, "input");
        oy.j(str, "replacement");
        String strReplaceAll = this.b.matcher(charSequence).replaceAll(str);
        oy.i(strReplaceAll, "replaceAll(...)");
        return strReplaceAll;
    }

    public final String replaceFirst(CharSequence charSequence, String str) {
        oy.j(charSequence, "input");
        oy.j(str, "replacement");
        String strReplaceFirst = this.b.matcher(charSequence).replaceFirst(str);
        oy.i(strReplaceFirst, "replaceFirst(...)");
        return strReplaceFirst;
    }

    public final List<String> split(CharSequence charSequence, int i) {
        oy.j(charSequence, "input");
        vk0.w0(i);
        Matcher matcher = this.b.matcher(charSequence);
        if (i == 1 || !matcher.find()) {
            return xi.G(charSequence.toString());
        }
        int i2 = 10;
        if (i > 0 && i <= 10) {
            i2 = i;
        }
        ArrayList arrayList = new ArrayList(i2);
        int i3 = i - 1;
        int iEnd = 0;
        do {
            arrayList.add(charSequence.subSequence(iEnd, matcher.start()).toString());
            iEnd = matcher.end();
            if (i3 >= 0 && arrayList.size() == i3) {
                break;
            }
        } while (matcher.find());
        arrayList.add(charSequence.subSequence(iEnd, charSequence.length()).toString());
        return arrayList;
    }

    public final xh0 splitToSequence(CharSequence charSequence, int i) {
        oy.j(charSequence, "input");
        vk0.w0(i);
        return new he(1, new ie0(this, charSequence, i, null));
    }

    public final Pattern toPattern() {
        return this.b;
    }

    public String toString() {
        String string = this.b.toString();
        oy.i(string, "toString(...)");
        return string;
    }

    public final String replace(CharSequence charSequence, pu puVar) {
        oy.j(charSequence, "input");
        oy.j(puVar, "transform");
        int i = 0;
        Object objFind$default = find$default(this, charSequence, 0, 2, null);
        if (objFind$default == null) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        do {
            t40 t40Var = (t40) objFind$default;
            Matcher matcher = t40Var.a;
            sb.append(charSequence, i, za0.Q(matcher.start(), matcher.end()).b);
            sb.append((CharSequence) puVar.invoke(objFind$default));
            i = za0.Q(matcher.start(), matcher.end()).c + 1;
            objFind$default = t40Var.a();
            if (i >= length) {
                break;
            }
        } while (objFind$default != null);
        if (i < length) {
            sb.append(charSequence, i, length);
        }
        String string = sb.toString();
        oy.i(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str) {
        oy.j(str, "pattern");
        Pattern patternCompile = Pattern.compile(str);
        oy.i(patternCompile, "compile(...)");
        this(patternCompile);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str, RegexOption regexOption) {
        oy.j(str, "pattern");
        oy.j(regexOption, "option");
        ee0 ee0Var = Companion;
        int value = regexOption.getValue();
        ee0Var.getClass();
        Pattern patternCompile = Pattern.compile(str, (value & 2) != 0 ? value | 64 : value);
        oy.i(patternCompile, "compile(...)");
        this(patternCompile);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str, Set<? extends RegexOption> set) {
        oy.j(str, "pattern");
        oy.j(set, "options");
        ee0 ee0Var = Companion;
        Iterator<T> it = set.iterator();
        int value = 0;
        while (it.hasNext()) {
            value |= ((RegexOption) it.next()).getValue();
        }
        ee0Var.getClass();
        Pattern patternCompile = Pattern.compile(str, (value & 2) != 0 ? value | 64 : value);
        oy.i(patternCompile, "compile(...)");
        this(patternCompile);
    }
}
