package kotlin.text;

import defpackage.gu;
import defpackage.hu;
import defpackage.qd;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CharCategory {
    public static final CharCategory COMBINING_SPACING_MARK;
    public static final CharCategory CONNECTOR_PUNCTUATION;
    public static final CharCategory CONTROL;
    public static final CharCategory CURRENCY_SYMBOL;
    public static final qd Companion;
    public static final CharCategory DASH_PUNCTUATION;
    public static final CharCategory DECIMAL_DIGIT_NUMBER;
    public static final CharCategory ENCLOSING_MARK;
    public static final CharCategory END_PUNCTUATION;
    public static final CharCategory FINAL_QUOTE_PUNCTUATION;
    public static final CharCategory FORMAT;
    public static final /* synthetic */ CharCategory[] G;
    public static final /* synthetic */ hu H;
    public static final CharCategory INITIAL_QUOTE_PUNCTUATION;
    public static final CharCategory LETTER_NUMBER;
    public static final CharCategory LINE_SEPARATOR;
    public static final CharCategory LOWERCASE_LETTER;
    public static final CharCategory MATH_SYMBOL;
    public static final CharCategory MODIFIER_LETTER;
    public static final CharCategory MODIFIER_SYMBOL;
    public static final CharCategory NON_SPACING_MARK;
    public static final CharCategory OTHER_LETTER;
    public static final CharCategory OTHER_NUMBER;
    public static final CharCategory OTHER_PUNCTUATION;
    public static final CharCategory OTHER_SYMBOL;
    public static final CharCategory PARAGRAPH_SEPARATOR;
    public static final CharCategory PRIVATE_USE;
    public static final CharCategory SPACE_SEPARATOR;
    public static final CharCategory START_PUNCTUATION;
    public static final CharCategory SURROGATE;
    public static final CharCategory TITLECASE_LETTER;
    public static final CharCategory UNASSIGNED;
    public static final CharCategory UPPERCASE_LETTER;
    public final int E;
    public final String F;

    static {
        CharCategory charCategory = new CharCategory("UNASSIGNED", 0, 0, "Cn");
        UNASSIGNED = charCategory;
        CharCategory charCategory2 = new CharCategory("UPPERCASE_LETTER", 1, 1, "Lu");
        UPPERCASE_LETTER = charCategory2;
        CharCategory charCategory3 = new CharCategory("LOWERCASE_LETTER", 2, 2, "Ll");
        LOWERCASE_LETTER = charCategory3;
        CharCategory charCategory4 = new CharCategory("TITLECASE_LETTER", 3, 3, "Lt");
        TITLECASE_LETTER = charCategory4;
        CharCategory charCategory5 = new CharCategory("MODIFIER_LETTER", 4, 4, "Lm");
        MODIFIER_LETTER = charCategory5;
        CharCategory charCategory6 = new CharCategory("OTHER_LETTER", 5, 5, "Lo");
        OTHER_LETTER = charCategory6;
        CharCategory charCategory7 = new CharCategory("NON_SPACING_MARK", 6, 6, "Mn");
        NON_SPACING_MARK = charCategory7;
        CharCategory charCategory8 = new CharCategory("ENCLOSING_MARK", 7, 7, "Me");
        ENCLOSING_MARK = charCategory8;
        CharCategory charCategory9 = new CharCategory("COMBINING_SPACING_MARK", 8, 8, "Mc");
        COMBINING_SPACING_MARK = charCategory9;
        CharCategory charCategory10 = new CharCategory("DECIMAL_DIGIT_NUMBER", 9, 9, "Nd");
        DECIMAL_DIGIT_NUMBER = charCategory10;
        CharCategory charCategory11 = new CharCategory("LETTER_NUMBER", 10, 10, "Nl");
        LETTER_NUMBER = charCategory11;
        CharCategory charCategory12 = new CharCategory("OTHER_NUMBER", 11, 11, "No");
        OTHER_NUMBER = charCategory12;
        CharCategory charCategory13 = new CharCategory("SPACE_SEPARATOR", 12, 12, "Zs");
        SPACE_SEPARATOR = charCategory13;
        CharCategory charCategory14 = new CharCategory("LINE_SEPARATOR", 13, 13, "Zl");
        LINE_SEPARATOR = charCategory14;
        CharCategory charCategory15 = new CharCategory("PARAGRAPH_SEPARATOR", 14, 14, "Zp");
        PARAGRAPH_SEPARATOR = charCategory15;
        CharCategory charCategory16 = new CharCategory("CONTROL", 15, 15, "Cc");
        CONTROL = charCategory16;
        CharCategory charCategory17 = new CharCategory("FORMAT", 16, 16, "Cf");
        FORMAT = charCategory17;
        CharCategory charCategory18 = new CharCategory("PRIVATE_USE", 17, 18, "Co");
        PRIVATE_USE = charCategory18;
        CharCategory charCategory19 = new CharCategory("SURROGATE", 18, 19, "Cs");
        SURROGATE = charCategory19;
        CharCategory charCategory20 = new CharCategory("DASH_PUNCTUATION", 19, 20, "Pd");
        DASH_PUNCTUATION = charCategory20;
        CharCategory charCategory21 = new CharCategory("START_PUNCTUATION", 20, 21, "Ps");
        START_PUNCTUATION = charCategory21;
        CharCategory charCategory22 = new CharCategory("END_PUNCTUATION", 21, 22, "Pe");
        END_PUNCTUATION = charCategory22;
        CharCategory charCategory23 = new CharCategory("CONNECTOR_PUNCTUATION", 22, 23, "Pc");
        CONNECTOR_PUNCTUATION = charCategory23;
        CharCategory charCategory24 = new CharCategory("OTHER_PUNCTUATION", 23, 24, "Po");
        OTHER_PUNCTUATION = charCategory24;
        CharCategory charCategory25 = new CharCategory("MATH_SYMBOL", 24, 25, "Sm");
        MATH_SYMBOL = charCategory25;
        CharCategory charCategory26 = new CharCategory("CURRENCY_SYMBOL", 25, 26, "Sc");
        CURRENCY_SYMBOL = charCategory26;
        CharCategory charCategory27 = new CharCategory("MODIFIER_SYMBOL", 26, 27, "Sk");
        MODIFIER_SYMBOL = charCategory27;
        CharCategory charCategory28 = new CharCategory("OTHER_SYMBOL", 27, 28, "So");
        OTHER_SYMBOL = charCategory28;
        CharCategory charCategory29 = new CharCategory("INITIAL_QUOTE_PUNCTUATION", 28, 29, "Pi");
        INITIAL_QUOTE_PUNCTUATION = charCategory29;
        CharCategory charCategory30 = new CharCategory("FINAL_QUOTE_PUNCTUATION", 29, 30, "Pf");
        FINAL_QUOTE_PUNCTUATION = charCategory30;
        CharCategory[] charCategoryArr = {charCategory, charCategory2, charCategory3, charCategory4, charCategory5, charCategory6, charCategory7, charCategory8, charCategory9, charCategory10, charCategory11, charCategory12, charCategory13, charCategory14, charCategory15, charCategory16, charCategory17, charCategory18, charCategory19, charCategory20, charCategory21, charCategory22, charCategory23, charCategory24, charCategory25, charCategory26, charCategory27, charCategory28, charCategory29, charCategory30};
        G = charCategoryArr;
        H = new hu(charCategoryArr);
        Companion = new qd();
    }

    public CharCategory(String str, int i, int i2, String str2) {
        this.E = i2;
        this.F = str2;
    }

    public static gu getEntries() {
        return H;
    }

    public static CharCategory valueOf(String str) {
        return (CharCategory) Enum.valueOf(CharCategory.class, str);
    }

    public static CharCategory[] values() {
        return (CharCategory[]) G.clone();
    }

    public final boolean contains(char c) {
        return Character.getType(c) == this.E;
    }

    public final String getCode() {
        return this.F;
    }

    public final int getValue() {
        return this.E;
    }
}
