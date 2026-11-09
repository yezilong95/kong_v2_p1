package com.google.android.material.color.utilities;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class TonePolarity {
    public static final TonePolarity DARKER;
    public static final TonePolarity LIGHTER;
    public static final TonePolarity NO_PREFERENCE;
    public static final /* synthetic */ TonePolarity[] b;

    static {
        TonePolarity tonePolarity = new TonePolarity("DARKER", 0);
        DARKER = tonePolarity;
        TonePolarity tonePolarity2 = new TonePolarity("LIGHTER", 1);
        LIGHTER = tonePolarity2;
        TonePolarity tonePolarity3 = new TonePolarity("NO_PREFERENCE", 2);
        NO_PREFERENCE = tonePolarity3;
        b = new TonePolarity[]{tonePolarity, tonePolarity2, tonePolarity3};
    }

    public static TonePolarity valueOf(String str) {
        return (TonePolarity) Enum.valueOf(TonePolarity.class, str);
    }

    public static TonePolarity[] values() {
        return (TonePolarity[]) b.clone();
    }
}
