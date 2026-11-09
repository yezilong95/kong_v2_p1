package kotlin.text;

import defpackage.jo;
import defpackage.ko;
import kotlin.uuid.Uuid;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class RegexOption {
    public static final RegexOption CANON_EQ;
    public static final RegexOption COMMENTS;
    public static final RegexOption DOT_MATCHES_ALL;
    public static final RegexOption IGNORE_CASE;
    public static final RegexOption LITERAL;
    public static final RegexOption MULTILINE;
    public static final RegexOption UNIX_LINES;
    public static final /* synthetic */ RegexOption[] d;
    public static final /* synthetic */ ko e;
    public final int b;
    public final int c;

    static {
        RegexOption regexOption = new RegexOption("IGNORE_CASE", 0, 2);
        IGNORE_CASE = regexOption;
        RegexOption regexOption2 = new RegexOption("MULTILINE", 1, 8);
        MULTILINE = regexOption2;
        RegexOption regexOption3 = new RegexOption("LITERAL", 2, 16);
        LITERAL = regexOption3;
        RegexOption regexOption4 = new RegexOption("UNIX_LINES", 3, 1);
        UNIX_LINES = regexOption4;
        RegexOption regexOption5 = new RegexOption("COMMENTS", 4, 4);
        COMMENTS = regexOption5;
        RegexOption regexOption6 = new RegexOption("DOT_MATCHES_ALL", 5, 32);
        DOT_MATCHES_ALL = regexOption6;
        RegexOption regexOption7 = new RegexOption("CANON_EQ", 6, Uuid.SIZE_BITS);
        CANON_EQ = regexOption7;
        RegexOption[] regexOptionArr = {regexOption, regexOption2, regexOption3, regexOption4, regexOption5, regexOption6, regexOption7};
        d = regexOptionArr;
        e = new ko(regexOptionArr);
    }

    public RegexOption(String str, int i, int i2) {
        this.b = i2;
        this.c = i2;
    }

    public static jo getEntries() {
        return e;
    }

    public static RegexOption valueOf(String str) {
        return (RegexOption) Enum.valueOf(RegexOption.class, str);
    }

    public static RegexOption[] values() {
        return (RegexOption[]) d.clone();
    }

    public int getMask() {
        return this.c;
    }

    public int getValue() {
        return this.b;
    }
}
