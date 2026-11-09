package androidx.core.graphics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BlendModeCompat {
    public static final BlendModeCompat CLEAR;
    public static final BlendModeCompat COLOR;
    public static final BlendModeCompat COLOR_BURN;
    public static final BlendModeCompat COLOR_DODGE;
    public static final BlendModeCompat DARKEN;
    public static final BlendModeCompat DIFFERENCE;
    public static final BlendModeCompat DST;
    public static final BlendModeCompat DST_ATOP;
    public static final BlendModeCompat DST_IN;
    public static final BlendModeCompat DST_OUT;
    public static final BlendModeCompat DST_OVER;
    public static final /* synthetic */ BlendModeCompat[] E;
    public static final BlendModeCompat EXCLUSION;
    public static final BlendModeCompat HARD_LIGHT;
    public static final BlendModeCompat HUE;
    public static final BlendModeCompat LIGHTEN;
    public static final BlendModeCompat LUMINOSITY;
    public static final BlendModeCompat MODULATE;
    public static final BlendModeCompat MULTIPLY;
    public static final BlendModeCompat OVERLAY;
    public static final BlendModeCompat PLUS;
    public static final BlendModeCompat SATURATION;
    public static final BlendModeCompat SCREEN;
    public static final BlendModeCompat SOFT_LIGHT;
    public static final BlendModeCompat SRC;
    public static final BlendModeCompat SRC_ATOP;
    public static final BlendModeCompat SRC_IN;
    public static final BlendModeCompat SRC_OUT;
    public static final BlendModeCompat SRC_OVER;
    public static final BlendModeCompat XOR;

    static {
        BlendModeCompat blendModeCompat = new BlendModeCompat("CLEAR", 0);
        CLEAR = blendModeCompat;
        BlendModeCompat blendModeCompat2 = new BlendModeCompat("SRC", 1);
        SRC = blendModeCompat2;
        BlendModeCompat blendModeCompat3 = new BlendModeCompat("DST", 2);
        DST = blendModeCompat3;
        BlendModeCompat blendModeCompat4 = new BlendModeCompat("SRC_OVER", 3);
        SRC_OVER = blendModeCompat4;
        BlendModeCompat blendModeCompat5 = new BlendModeCompat("DST_OVER", 4);
        DST_OVER = blendModeCompat5;
        BlendModeCompat blendModeCompat6 = new BlendModeCompat("SRC_IN", 5);
        SRC_IN = blendModeCompat6;
        BlendModeCompat blendModeCompat7 = new BlendModeCompat("DST_IN", 6);
        DST_IN = blendModeCompat7;
        BlendModeCompat blendModeCompat8 = new BlendModeCompat("SRC_OUT", 7);
        SRC_OUT = blendModeCompat8;
        BlendModeCompat blendModeCompat9 = new BlendModeCompat("DST_OUT", 8);
        DST_OUT = blendModeCompat9;
        BlendModeCompat blendModeCompat10 = new BlendModeCompat("SRC_ATOP", 9);
        SRC_ATOP = blendModeCompat10;
        BlendModeCompat blendModeCompat11 = new BlendModeCompat("DST_ATOP", 10);
        DST_ATOP = blendModeCompat11;
        BlendModeCompat blendModeCompat12 = new BlendModeCompat("XOR", 11);
        XOR = blendModeCompat12;
        BlendModeCompat blendModeCompat13 = new BlendModeCompat("PLUS", 12);
        PLUS = blendModeCompat13;
        BlendModeCompat blendModeCompat14 = new BlendModeCompat("MODULATE", 13);
        MODULATE = blendModeCompat14;
        BlendModeCompat blendModeCompat15 = new BlendModeCompat("SCREEN", 14);
        SCREEN = blendModeCompat15;
        BlendModeCompat blendModeCompat16 = new BlendModeCompat("OVERLAY", 15);
        OVERLAY = blendModeCompat16;
        BlendModeCompat blendModeCompat17 = new BlendModeCompat("DARKEN", 16);
        DARKEN = blendModeCompat17;
        BlendModeCompat blendModeCompat18 = new BlendModeCompat("LIGHTEN", 17);
        LIGHTEN = blendModeCompat18;
        BlendModeCompat blendModeCompat19 = new BlendModeCompat("COLOR_DODGE", 18);
        COLOR_DODGE = blendModeCompat19;
        BlendModeCompat blendModeCompat20 = new BlendModeCompat("COLOR_BURN", 19);
        COLOR_BURN = blendModeCompat20;
        BlendModeCompat blendModeCompat21 = new BlendModeCompat("HARD_LIGHT", 20);
        HARD_LIGHT = blendModeCompat21;
        BlendModeCompat blendModeCompat22 = new BlendModeCompat("SOFT_LIGHT", 21);
        SOFT_LIGHT = blendModeCompat22;
        BlendModeCompat blendModeCompat23 = new BlendModeCompat("DIFFERENCE", 22);
        DIFFERENCE = blendModeCompat23;
        BlendModeCompat blendModeCompat24 = new BlendModeCompat("EXCLUSION", 23);
        EXCLUSION = blendModeCompat24;
        BlendModeCompat blendModeCompat25 = new BlendModeCompat("MULTIPLY", 24);
        MULTIPLY = blendModeCompat25;
        BlendModeCompat blendModeCompat26 = new BlendModeCompat("HUE", 25);
        HUE = blendModeCompat26;
        BlendModeCompat blendModeCompat27 = new BlendModeCompat("SATURATION", 26);
        SATURATION = blendModeCompat27;
        BlendModeCompat blendModeCompat28 = new BlendModeCompat("COLOR", 27);
        COLOR = blendModeCompat28;
        BlendModeCompat blendModeCompat29 = new BlendModeCompat("LUMINOSITY", 28);
        LUMINOSITY = blendModeCompat29;
        E = new BlendModeCompat[]{blendModeCompat, blendModeCompat2, blendModeCompat3, blendModeCompat4, blendModeCompat5, blendModeCompat6, blendModeCompat7, blendModeCompat8, blendModeCompat9, blendModeCompat10, blendModeCompat11, blendModeCompat12, blendModeCompat13, blendModeCompat14, blendModeCompat15, blendModeCompat16, blendModeCompat17, blendModeCompat18, blendModeCompat19, blendModeCompat20, blendModeCompat21, blendModeCompat22, blendModeCompat23, blendModeCompat24, blendModeCompat25, blendModeCompat26, blendModeCompat27, blendModeCompat28, blendModeCompat29};
    }

    public static BlendModeCompat valueOf(String str) {
        return (BlendModeCompat) Enum.valueOf(BlendModeCompat.class, str);
    }

    public static BlendModeCompat[] values() {
        return (BlendModeCompat[]) E.clone();
    }
}
