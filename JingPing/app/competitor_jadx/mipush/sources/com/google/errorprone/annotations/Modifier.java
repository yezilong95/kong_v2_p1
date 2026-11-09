package com.google.errorprone.annotations;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Modifier {
    public static final Modifier ABSTRACT;
    public static final Modifier DEFAULT;
    public static final /* synthetic */ Modifier[] E;
    public static final Modifier FINAL;
    public static final Modifier NATIVE;
    public static final Modifier PRIVATE;
    public static final Modifier PROTECTED;
    public static final Modifier PUBLIC;
    public static final Modifier STATIC;
    public static final Modifier STRICTFP;
    public static final Modifier SYNCHRONIZED;
    public static final Modifier TRANSIENT;
    public static final Modifier VOLATILE;

    static {
        Modifier modifier = new Modifier("PUBLIC", 0);
        PUBLIC = modifier;
        Modifier modifier2 = new Modifier("PROTECTED", 1);
        PROTECTED = modifier2;
        Modifier modifier3 = new Modifier("PRIVATE", 2);
        PRIVATE = modifier3;
        Modifier modifier4 = new Modifier("ABSTRACT", 3);
        ABSTRACT = modifier4;
        Modifier modifier5 = new Modifier("DEFAULT", 4);
        DEFAULT = modifier5;
        Modifier modifier6 = new Modifier("STATIC", 5);
        STATIC = modifier6;
        Modifier modifier7 = new Modifier("FINAL", 6);
        FINAL = modifier7;
        Modifier modifier8 = new Modifier("TRANSIENT", 7);
        TRANSIENT = modifier8;
        Modifier modifier9 = new Modifier("VOLATILE", 8);
        VOLATILE = modifier9;
        Modifier modifier10 = new Modifier("SYNCHRONIZED", 9);
        SYNCHRONIZED = modifier10;
        Modifier modifier11 = new Modifier("NATIVE", 10);
        NATIVE = modifier11;
        Modifier modifier12 = new Modifier("STRICTFP", 11);
        STRICTFP = modifier12;
        E = new Modifier[]{modifier, modifier2, modifier3, modifier4, modifier5, modifier6, modifier7, modifier8, modifier9, modifier10, modifier11, modifier12};
    }

    public static Modifier valueOf(String str) {
        return (Modifier) Enum.valueOf(Modifier.class, str);
    }

    public static Modifier[] values() {
        return (Modifier[]) E.clone();
    }
}
