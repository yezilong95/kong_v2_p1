package kotlin.text;

import defpackage.jo;
import defpackage.ko;
import defpackage.wb;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CharCategory {
    public static final CharCategory COMBINING_SPACING_MARK;
    public static final CharCategory CONNECTOR_PUNCTUATION;
    public static final CharCategory CONTROL;
    public static final CharCategory CURRENCY_SYMBOL;
    public static final wb Companion;
    public static final CharCategory DASH_PUNCTUATION;
    public static final CharCategory DECIMAL_DIGIT_NUMBER;
    public static final CharCategory ENCLOSING_MARK;
    public static final CharCategory END_PUNCTUATION;
    public static final CharCategory FINAL_QUOTE_PUNCTUATION;
    public static final CharCategory FORMAT;
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
    public static final /* synthetic */ CharCategory[] d;
    public static final /* synthetic */ ko e;
    public final int b;
    public final String c;

    static {
        CharCategory charCategory = new CharCategory(0, 0, "UNASSIGNED", "Cn");
        UNASSIGNED = charCategory;
        CharCategory charCategory2 = new CharCategory(1, 1, "UPPERCASE_LETTER", "Lu");
        UPPERCASE_LETTER = charCategory2;
        CharCategory charCategory3 = new CharCategory(2, 2, "LOWERCASE_LETTER", "Ll");
        LOWERCASE_LETTER = charCategory3;
        CharCategory charCategory4 = new CharCategory(3, 3, "TITLECASE_LETTER", "Lt");
        TITLECASE_LETTER = charCategory4;
        CharCategory charCategory5 = new CharCategory(4, 4, "MODIFIER_LETTER", "Lm");
        MODIFIER_LETTER = charCategory5;
        CharCategory charCategory6 = new CharCategory(5, 5, "OTHER_LETTER", "Lo");
        OTHER_LETTER = charCategory6;
        CharCategory charCategory7 = new CharCategory(6, 6, "NON_SPACING_MARK", "Mn");
        NON_SPACING_MARK = charCategory7;
        CharCategory charCategory8 = new CharCategory(7, 7, "ENCLOSING_MARK", "Me");
        ENCLOSING_MARK = charCategory8;
        CharCategory charCategory9 = new CharCategory(8, 8, "COMBINING_SPACING_MARK", "Mc");
        COMBINING_SPACING_MARK = charCategory9;
        CharCategory charCategory10 = new CharCategory(9, 9, "DECIMAL_DIGIT_NUMBER", "Nd");
        DECIMAL_DIGIT_NUMBER = charCategory10;
        CharCategory charCategory11 = new CharCategory(10, 10, "LETTER_NUMBER", "Nl");
        LETTER_NUMBER = charCategory11;
        CharCategory charCategory12 = new CharCategory(11, 11, "OTHER_NUMBER", "No");
        OTHER_NUMBER = charCategory12;
        CharCategory charCategory13 = new CharCategory(12, 12, "SPACE_SEPARATOR", "Zs");
        SPACE_SEPARATOR = charCategory13;
        CharCategory charCategory14 = new CharCategory(13, 13, "LINE_SEPARATOR", "Zl");
        LINE_SEPARATOR = charCategory14;
        CharCategory charCategory15 = new CharCategory(14, 14, "PARAGRAPH_SEPARATOR", "Zp");
        PARAGRAPH_SEPARATOR = charCategory15;
        CharCategory charCategory16 = new CharCategory(15, 15, "CONTROL", "Cc");
        CONTROL = charCategory16;
        CharCategory charCategory17 = new CharCategory(16, 16, "FORMAT", "Cf");
        FORMAT = charCategory17;
        CharCategory charCategory18 = new CharCategory(17, 18, "PRIVATE_USE", "Co");
        PRIVATE_USE = charCategory18;
        CharCategory charCategory19 = new CharCategory(18, 19, "SURROGATE", "Cs");
        SURROGATE = charCategory19;
        CharCategory charCategory20 = new CharCategory(19, 20, "DASH_PUNCTUATION", "Pd");
        DASH_PUNCTUATION = charCategory20;
        CharCategory charCategory21 = new CharCategory(20, 21, "START_PUNCTUATION", "Ps");
        START_PUNCTUATION = charCategory21;
        CharCategory charCategory22 = new CharCategory(21, 22, "END_PUNCTUATION", "Pe");
        END_PUNCTUATION = charCategory22;
        CharCategory charCategory23 = new CharCategory(22, 23, "CONNECTOR_PUNCTUATION", "Pc");
        CONNECTOR_PUNCTUATION = charCategory23;
        CharCategory charCategory24 = new CharCategory(23, 24, "OTHER_PUNCTUATION", "Po");
        OTHER_PUNCTUATION = charCategory24;
        CharCategory charCategory25 = new CharCategory(24, 25, "MATH_SYMBOL", "Sm");
        MATH_SYMBOL = charCategory25;
        CharCategory charCategory26 = new CharCategory(25, 26, "CURRENCY_SYMBOL", "Sc");
        CURRENCY_SYMBOL = charCategory26;
        CharCategory charCategory27 = new CharCategory(26, 27, "MODIFIER_SYMBOL", "Sk");
        MODIFIER_SYMBOL = charCategory27;
        CharCategory charCategory28 = new CharCategory(27, 28, "OTHER_SYMBOL", "So");
        OTHER_SYMBOL = charCategory28;
        CharCategory charCategory29 = new CharCategory(28, 29, "INITIAL_QUOTE_PUNCTUATION", "Pi");
        INITIAL_QUOTE_PUNCTUATION = charCategory29;
        CharCategory charCategory30 = new CharCategory(29, 30, "FINAL_QUOTE_PUNCTUATION", "Pf");
        FINAL_QUOTE_PUNCTUATION = charCategory30;
        CharCategory[] charCategoryArr = {charCategory, charCategory2, charCategory3, charCategory4, charCategory5, charCategory6, charCategory7, charCategory8, charCategory9, charCategory10, charCategory11, charCategory12, charCategory13, charCategory14, charCategory15, charCategory16, charCategory17, charCategory18, charCategory19, charCategory20, charCategory21, charCategory22, charCategory23, charCategory24, charCategory25, charCategory26, charCategory27, charCategory28, charCategory29, charCategory30};
        d = charCategoryArr;
        e = new ko(charCategoryArr);
        Companion = new wb();
    }

    public CharCategory(int i, int i2, String str, String str2) {
        this.b = i2;
        this.c = str2;
    }

    public static jo getEntries() {
        return e;
    }

    public static CharCategory valueOf(String str) {
        return (CharCategory) Enum.valueOf(CharCategory.class, str);
    }

    public static CharCategory[] values() {
        return (CharCategory[]) d.clone();
    }

    public final boolean contains(char c) {
        return Character.getType(c) == this.b;
    }

    public final String getCode() {
        return this.c;
    }

    public final int getValue() {
        return this.b;
    }
}
