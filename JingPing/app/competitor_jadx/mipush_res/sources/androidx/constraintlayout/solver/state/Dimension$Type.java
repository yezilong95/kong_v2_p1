package androidx.constraintlayout.solver.state;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Dimension$Type {
    public static final /* synthetic */ Dimension$Type[] E;
    public static final Dimension$Type FIXED;
    public static final Dimension$Type MATCH_CONSTRAINT;
    public static final Dimension$Type MATCH_PARENT;
    public static final Dimension$Type WRAP;

    static {
        Dimension$Type dimension$Type = new Dimension$Type("FIXED", 0);
        FIXED = dimension$Type;
        Dimension$Type dimension$Type2 = new Dimension$Type("WRAP", 1);
        WRAP = dimension$Type2;
        Dimension$Type dimension$Type3 = new Dimension$Type("MATCH_PARENT", 2);
        MATCH_PARENT = dimension$Type3;
        Dimension$Type dimension$Type4 = new Dimension$Type("MATCH_CONSTRAINT", 3);
        MATCH_CONSTRAINT = dimension$Type4;
        E = new Dimension$Type[]{dimension$Type, dimension$Type2, dimension$Type3, dimension$Type4};
    }

    public static Dimension$Type valueOf(String str) {
        return (Dimension$Type) Enum.valueOf(Dimension$Type.class, str);
    }

    public static Dimension$Type[] values() {
        return (Dimension$Type[]) E.clone();
    }
}
