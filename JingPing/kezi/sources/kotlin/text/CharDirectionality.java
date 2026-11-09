package kotlin.text;

import defpackage.im0;
import defpackage.jo;
import defpackage.ko;
import defpackage.oy;
import defpackage.xb;
import defpackage.yb;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CharDirectionality {
    public static final CharDirectionality ARABIC_NUMBER;
    public static final CharDirectionality BOUNDARY_NEUTRAL;
    public static final CharDirectionality COMMON_NUMBER_SEPARATOR;
    public static final yb Companion;
    public static final CharDirectionality EUROPEAN_NUMBER;
    public static final CharDirectionality EUROPEAN_NUMBER_SEPARATOR;
    public static final CharDirectionality EUROPEAN_NUMBER_TERMINATOR;
    public static final CharDirectionality LEFT_TO_RIGHT;
    public static final CharDirectionality LEFT_TO_RIGHT_EMBEDDING;
    public static final CharDirectionality LEFT_TO_RIGHT_OVERRIDE;
    public static final CharDirectionality NONSPACING_MARK;
    public static final CharDirectionality OTHER_NEUTRALS;
    public static final CharDirectionality PARAGRAPH_SEPARATOR;
    public static final CharDirectionality POP_DIRECTIONAL_FORMAT;
    public static final CharDirectionality RIGHT_TO_LEFT;
    public static final CharDirectionality RIGHT_TO_LEFT_ARABIC;
    public static final CharDirectionality RIGHT_TO_LEFT_EMBEDDING;
    public static final CharDirectionality RIGHT_TO_LEFT_OVERRIDE;
    public static final CharDirectionality SEGMENT_SEPARATOR;
    public static final CharDirectionality UNDEFINED;
    public static final CharDirectionality WHITESPACE;
    public static final im0 c;
    public static final /* synthetic */ CharDirectionality[] d;
    public static final /* synthetic */ ko e;
    public final int b;

    static {
        CharDirectionality charDirectionality = new CharDirectionality("UNDEFINED", 0, -1);
        UNDEFINED = charDirectionality;
        CharDirectionality charDirectionality2 = new CharDirectionality("LEFT_TO_RIGHT", 1, 0);
        LEFT_TO_RIGHT = charDirectionality2;
        CharDirectionality charDirectionality3 = new CharDirectionality("RIGHT_TO_LEFT", 2, 1);
        RIGHT_TO_LEFT = charDirectionality3;
        CharDirectionality charDirectionality4 = new CharDirectionality("RIGHT_TO_LEFT_ARABIC", 3, 2);
        RIGHT_TO_LEFT_ARABIC = charDirectionality4;
        CharDirectionality charDirectionality5 = new CharDirectionality("EUROPEAN_NUMBER", 4, 3);
        EUROPEAN_NUMBER = charDirectionality5;
        CharDirectionality charDirectionality6 = new CharDirectionality("EUROPEAN_NUMBER_SEPARATOR", 5, 4);
        EUROPEAN_NUMBER_SEPARATOR = charDirectionality6;
        CharDirectionality charDirectionality7 = new CharDirectionality("EUROPEAN_NUMBER_TERMINATOR", 6, 5);
        EUROPEAN_NUMBER_TERMINATOR = charDirectionality7;
        CharDirectionality charDirectionality8 = new CharDirectionality("ARABIC_NUMBER", 7, 6);
        ARABIC_NUMBER = charDirectionality8;
        CharDirectionality charDirectionality9 = new CharDirectionality("COMMON_NUMBER_SEPARATOR", 8, 7);
        COMMON_NUMBER_SEPARATOR = charDirectionality9;
        CharDirectionality charDirectionality10 = new CharDirectionality("NONSPACING_MARK", 9, 8);
        NONSPACING_MARK = charDirectionality10;
        CharDirectionality charDirectionality11 = new CharDirectionality("BOUNDARY_NEUTRAL", 10, 9);
        BOUNDARY_NEUTRAL = charDirectionality11;
        CharDirectionality charDirectionality12 = new CharDirectionality("PARAGRAPH_SEPARATOR", 11, 10);
        PARAGRAPH_SEPARATOR = charDirectionality12;
        CharDirectionality charDirectionality13 = new CharDirectionality("SEGMENT_SEPARATOR", 12, 11);
        SEGMENT_SEPARATOR = charDirectionality13;
        CharDirectionality charDirectionality14 = new CharDirectionality("WHITESPACE", 13, 12);
        WHITESPACE = charDirectionality14;
        CharDirectionality charDirectionality15 = new CharDirectionality("OTHER_NEUTRALS", 14, 13);
        OTHER_NEUTRALS = charDirectionality15;
        CharDirectionality charDirectionality16 = new CharDirectionality("LEFT_TO_RIGHT_EMBEDDING", 15, 14);
        LEFT_TO_RIGHT_EMBEDDING = charDirectionality16;
        CharDirectionality charDirectionality17 = new CharDirectionality("LEFT_TO_RIGHT_OVERRIDE", 16, 15);
        LEFT_TO_RIGHT_OVERRIDE = charDirectionality17;
        CharDirectionality charDirectionality18 = new CharDirectionality("RIGHT_TO_LEFT_EMBEDDING", 17, 16);
        RIGHT_TO_LEFT_EMBEDDING = charDirectionality18;
        CharDirectionality charDirectionality19 = new CharDirectionality("RIGHT_TO_LEFT_OVERRIDE", 18, 17);
        RIGHT_TO_LEFT_OVERRIDE = charDirectionality19;
        CharDirectionality charDirectionality20 = new CharDirectionality("POP_DIRECTIONAL_FORMAT", 19, 18);
        POP_DIRECTIONAL_FORMAT = charDirectionality20;
        CharDirectionality[] charDirectionalityArr = {charDirectionality, charDirectionality2, charDirectionality3, charDirectionality4, charDirectionality5, charDirectionality6, charDirectionality7, charDirectionality8, charDirectionality9, charDirectionality10, charDirectionality11, charDirectionality12, charDirectionality13, charDirectionality14, charDirectionality15, charDirectionality16, charDirectionality17, charDirectionality18, charDirectionality19, charDirectionality20};
        d = charDirectionalityArr;
        e = new ko(charDirectionalityArr);
        Companion = new yb();
        c = oy.G(xb.c);
    }

    public CharDirectionality(String str, int i, int i2) {
        this.b = i2;
    }

    public static jo getEntries() {
        return e;
    }

    public static CharDirectionality valueOf(String str) {
        return (CharDirectionality) Enum.valueOf(CharDirectionality.class, str);
    }

    public static CharDirectionality[] values() {
        return (CharDirectionality[]) d.clone();
    }

    public final int getValue() {
        return this.b;
    }
}
