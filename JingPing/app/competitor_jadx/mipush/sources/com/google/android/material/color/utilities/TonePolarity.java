package com.google.android.material.color.utilities;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class TonePolarity {
    public static final TonePolarity DARKER;
    public static final /* synthetic */ TonePolarity[] E;
    public static final TonePolarity FARTHER;
    public static final TonePolarity LIGHTER;
    public static final TonePolarity NEARER;

    static {
        TonePolarity tonePolarity = new TonePolarity("DARKER", 0);
        DARKER = tonePolarity;
        TonePolarity tonePolarity2 = new TonePolarity("LIGHTER", 1);
        LIGHTER = tonePolarity2;
        TonePolarity tonePolarity3 = new TonePolarity("NEARER", 2);
        NEARER = tonePolarity3;
        TonePolarity tonePolarity4 = new TonePolarity("FARTHER", 3);
        FARTHER = tonePolarity4;
        E = new TonePolarity[]{tonePolarity, tonePolarity2, tonePolarity3, tonePolarity4};
    }

    public static TonePolarity valueOf(String str) {
        return (TonePolarity) Enum.valueOf(TonePolarity.class, str);
    }

    public static TonePolarity[] values() {
        return (TonePolarity[]) E.clone();
    }
}
