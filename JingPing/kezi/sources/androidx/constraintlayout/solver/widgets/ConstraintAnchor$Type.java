package androidx.constraintlayout.solver.widgets;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ConstraintAnchor$Type {
    public static final ConstraintAnchor$Type BASELINE;
    public static final ConstraintAnchor$Type BOTTOM;
    public static final ConstraintAnchor$Type CENTER;
    public static final ConstraintAnchor$Type CENTER_X;
    public static final ConstraintAnchor$Type CENTER_Y;
    public static final ConstraintAnchor$Type LEFT;
    public static final ConstraintAnchor$Type NONE;
    public static final ConstraintAnchor$Type RIGHT;
    public static final ConstraintAnchor$Type TOP;
    public static final /* synthetic */ ConstraintAnchor$Type[] b;

    static {
        ConstraintAnchor$Type constraintAnchor$Type = new ConstraintAnchor$Type("NONE", 0);
        NONE = constraintAnchor$Type;
        ConstraintAnchor$Type constraintAnchor$Type2 = new ConstraintAnchor$Type("LEFT", 1);
        LEFT = constraintAnchor$Type2;
        ConstraintAnchor$Type constraintAnchor$Type3 = new ConstraintAnchor$Type("TOP", 2);
        TOP = constraintAnchor$Type3;
        ConstraintAnchor$Type constraintAnchor$Type4 = new ConstraintAnchor$Type("RIGHT", 3);
        RIGHT = constraintAnchor$Type4;
        ConstraintAnchor$Type constraintAnchor$Type5 = new ConstraintAnchor$Type("BOTTOM", 4);
        BOTTOM = constraintAnchor$Type5;
        ConstraintAnchor$Type constraintAnchor$Type6 = new ConstraintAnchor$Type("BASELINE", 5);
        BASELINE = constraintAnchor$Type6;
        ConstraintAnchor$Type constraintAnchor$Type7 = new ConstraintAnchor$Type("CENTER", 6);
        CENTER = constraintAnchor$Type7;
        ConstraintAnchor$Type constraintAnchor$Type8 = new ConstraintAnchor$Type("CENTER_X", 7);
        CENTER_X = constraintAnchor$Type8;
        ConstraintAnchor$Type constraintAnchor$Type9 = new ConstraintAnchor$Type("CENTER_Y", 8);
        CENTER_Y = constraintAnchor$Type9;
        b = new ConstraintAnchor$Type[]{constraintAnchor$Type, constraintAnchor$Type2, constraintAnchor$Type3, constraintAnchor$Type4, constraintAnchor$Type5, constraintAnchor$Type6, constraintAnchor$Type7, constraintAnchor$Type8, constraintAnchor$Type9};
    }

    public static ConstraintAnchor$Type valueOf(String str) {
        return (ConstraintAnchor$Type) Enum.valueOf(ConstraintAnchor$Type.class, str);
    }

    public static ConstraintAnchor$Type[] values() {
        return (ConstraintAnchor$Type[]) b.clone();
    }
}
