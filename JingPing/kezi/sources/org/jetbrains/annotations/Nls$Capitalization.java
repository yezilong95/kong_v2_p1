package org.jetbrains.annotations;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Nls$Capitalization {
    public static final Nls$Capitalization NotSpecified;
    public static final Nls$Capitalization Sentence;
    public static final Nls$Capitalization Title;
    public static final /* synthetic */ Nls$Capitalization[] b;

    static {
        Nls$Capitalization nls$Capitalization = new Nls$Capitalization("NotSpecified", 0);
        NotSpecified = nls$Capitalization;
        Nls$Capitalization nls$Capitalization2 = new Nls$Capitalization("Title", 1);
        Title = nls$Capitalization2;
        Nls$Capitalization nls$Capitalization3 = new Nls$Capitalization("Sentence", 2);
        Sentence = nls$Capitalization3;
        b = new Nls$Capitalization[]{nls$Capitalization, nls$Capitalization2, nls$Capitalization3};
    }

    public static Nls$Capitalization valueOf(String str) {
        return (Nls$Capitalization) Enum.valueOf(Nls$Capitalization.class, str);
    }

    public static Nls$Capitalization[] values() {
        return (Nls$Capitalization[]) b.clone();
    }
}
