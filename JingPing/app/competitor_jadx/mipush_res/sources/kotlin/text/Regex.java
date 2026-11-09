package kotlin.text;

import defpackage.b10;
import defpackage.eo0;
import defpackage.fo0;
import defpackage.go0;
import defpackage.ho0;
import defpackage.ic0;
import defpackage.io0;
import defpackage.jc0;
import defpackage.l21;
import defpackage.lu0;
import defpackage.ou0;
import defpackage.q40;
import defpackage.qx0;
import defpackage.t40;
import defpackage.wn;
import io.nats.client.support.ApiConstants;
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
    public static final eo0 Companion = new eo0();
    public final Pattern E;
    public Set F;

    public Regex(Pattern pattern) {
        t40.n(pattern, "nativePattern");
        this.E = pattern;
    }

    public static /* synthetic */ ic0 find$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.find(charSequence, i);
    }

    public static /* synthetic */ lu0 findAll$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
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

    public static /* synthetic */ lu0 splitToSequence$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.splitToSequence(charSequence, i);
    }

    private final Object writeReplace() {
        Pattern pattern = this.E;
        String strPattern = pattern.pattern();
        t40.m(strPattern, "pattern(...)");
        return new fo0(strPattern, pattern.flags());
    }

    public final boolean containsMatchIn(CharSequence charSequence) {
        t40.n(charSequence, "input");
        return this.E.matcher(charSequence).find();
    }

    public final ic0 find(CharSequence charSequence, int i) {
        t40.n(charSequence, "input");
        Matcher matcher = this.E.matcher(charSequence);
        t40.m(matcher, "matcher(...)");
        if (matcher.find(i)) {
            return new jc0(matcher, charSequence);
        }
        return null;
    }

    public final lu0 findAll(CharSequence charSequence, int i) {
        t40.n(charSequence, "input");
        if (i >= 0 && i <= charSequence.length()) {
            go0 go0Var = new go0(this, charSequence, i);
            ho0 ho0Var = ho0.M;
            t40.n(ho0Var, "nextFunction");
            return new wn(go0Var, ho0Var);
        }
        throw new IndexOutOfBoundsException("Start index out of bounds: " + i + ", input length: " + charSequence.length());
    }

    public final Set<RegexOption> getOptions() {
        Set<RegexOption> set = this.F;
        if (set != null) {
            return set;
        }
        final int iFlags = this.E.flags();
        EnumSet enumSetAllOf = EnumSet.allOf(RegexOption.class);
        t40.k(enumSetAllOf);
        b10 b10Var = new b10() { // from class: kotlin.text.Regex$special$$inlined$fromInt$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // defpackage.b10
            public final Boolean invoke(RegexOption regexOption) {
                RegexOption regexOption2 = regexOption;
                return Boolean.valueOf((regexOption2.getMask() & iFlags) == regexOption2.getValue());
            }
        };
        Iterator it = enumSetAllOf.iterator();
        while (it.hasNext()) {
            if (!((Boolean) b10Var.invoke(it.next())).booleanValue()) {
                it.remove();
            }
        }
        Set<RegexOption> setUnmodifiableSet = Collections.unmodifiableSet(enumSetAllOf);
        t40.m(setUnmodifiableSet, "unmodifiableSet(...)");
        this.F = setUnmodifiableSet;
        return setUnmodifiableSet;
    }

    public final String getPattern() {
        String strPattern = this.E.pattern();
        t40.m(strPattern, "pattern(...)");
        return strPattern;
    }

    public final ic0 matchAt(CharSequence charSequence, int i) {
        t40.n(charSequence, "input");
        Matcher matcherRegion = this.E.matcher(charSequence).useAnchoringBounds(false).useTransparentBounds(true).region(i, charSequence.length());
        if (matcherRegion.lookingAt()) {
            return new jc0(matcherRegion, charSequence);
        }
        return null;
    }

    public final ic0 matchEntire(CharSequence charSequence) {
        t40.n(charSequence, "input");
        Matcher matcher = this.E.matcher(charSequence);
        t40.m(matcher, "matcher(...)");
        if (matcher.matches()) {
            return new jc0(matcher, charSequence);
        }
        return null;
    }

    public final boolean matches(CharSequence charSequence) {
        t40.n(charSequence, "input");
        return this.E.matcher(charSequence).matches();
    }

    public final boolean matchesAt(CharSequence charSequence, int i) {
        t40.n(charSequence, "input");
        return this.E.matcher(charSequence).useAnchoringBounds(false).useTransparentBounds(true).region(i, charSequence.length()).lookingAt();
    }

    public final String replace(CharSequence charSequence, String str) {
        t40.n(charSequence, "input");
        t40.n(str, "replacement");
        String strReplaceAll = this.E.matcher(charSequence).replaceAll(str);
        t40.m(strReplaceAll, "replaceAll(...)");
        return strReplaceAll;
    }

    public final String replaceFirst(CharSequence charSequence, String str) {
        t40.n(charSequence, "input");
        t40.n(str, "replacement");
        String strReplaceFirst = this.E.matcher(charSequence).replaceFirst(str);
        t40.m(strReplaceFirst, "replaceFirst(...)");
        return strReplaceFirst;
    }

    public final List<String> split(CharSequence charSequence, int i) {
        t40.n(charSequence, "input");
        qx0.r0(i);
        Matcher matcher = this.E.matcher(charSequence);
        if (i == 1 || !matcher.find()) {
            return l21.g0(charSequence.toString());
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

    public final lu0 splitToSequence(CharSequence charSequence, int i) {
        t40.n(charSequence, "input");
        qx0.r0(i);
        return new ou0(0, new io0(this, charSequence, i, null));
    }

    public final Pattern toPattern() {
        return this.E;
    }

    public String toString() {
        String string = this.E.toString();
        t40.m(string, "toString(...)");
        return string;
    }

    public final String replace(CharSequence charSequence, b10 b10Var) {
        t40.n(charSequence, "input");
        t40.n(b10Var, "transform");
        int i = 0;
        Object objFind$default = find$default(this, charSequence, 0, 2, null);
        if (objFind$default == null) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        do {
            jc0 jc0Var = (jc0) objFind$default;
            Matcher matcher = jc0Var.a;
            int iStart = matcher.start();
            int iEnd = matcher.end();
            sb.append(charSequence, i, (iEnd <= Integer.MIN_VALUE ? q40.H : new q40(iStart, iEnd - 1, 1)).E);
            sb.append((CharSequence) b10Var.invoke(objFind$default));
            int iStart2 = matcher.start();
            int iEnd2 = matcher.end();
            i = (iEnd2 <= Integer.MIN_VALUE ? q40.H : new q40(iStart2, iEnd2 - 1, 1)).F + 1;
            objFind$default = jc0Var.a();
            if (i >= length) {
                break;
            }
        } while (objFind$default != null);
        if (i < length) {
            sb.append(charSequence, i, length);
        }
        String string = sb.toString();
        t40.m(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str) {
        t40.n(str, "pattern");
        Pattern patternCompile = Pattern.compile(str);
        t40.m(patternCompile, "compile(...)");
        this(patternCompile);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str, RegexOption regexOption) {
        t40.n(str, "pattern");
        t40.n(regexOption, "option");
        eo0 eo0Var = Companion;
        int value = regexOption.getValue();
        eo0Var.getClass();
        Pattern patternCompile = Pattern.compile(str, (value & 2) != 0 ? value | 64 : value);
        t40.m(patternCompile, "compile(...)");
        this(patternCompile);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str, Set<? extends RegexOption> set) {
        t40.n(str, "pattern");
        t40.n(set, ApiConstants.OPTIONS);
        eo0 eo0Var = Companion;
        Iterator<T> it = set.iterator();
        int value = 0;
        while (it.hasNext()) {
            value |= ((RegexOption) it.next()).getValue();
        }
        eo0Var.getClass();
        Pattern patternCompile = Pattern.compile(str, (value & 2) != 0 ? value | 64 : value);
        t40.m(patternCompile, "compile(...)");
        this(patternCompile);
    }
}
