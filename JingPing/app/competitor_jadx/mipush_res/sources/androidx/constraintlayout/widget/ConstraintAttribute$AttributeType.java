package androidx.constraintlayout.widget;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ConstraintAttribute$AttributeType {
    public static final ConstraintAttribute$AttributeType BOOLEAN_TYPE;
    public static final ConstraintAttribute$AttributeType COLOR_DRAWABLE_TYPE;
    public static final ConstraintAttribute$AttributeType COLOR_TYPE;
    public static final ConstraintAttribute$AttributeType DIMENSION_TYPE;
    public static final /* synthetic */ ConstraintAttribute$AttributeType[] E;
    public static final ConstraintAttribute$AttributeType FLOAT_TYPE;
    public static final ConstraintAttribute$AttributeType INT_TYPE;
    public static final ConstraintAttribute$AttributeType STRING_TYPE;

    static {
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType = new ConstraintAttribute$AttributeType("INT_TYPE", 0);
        INT_TYPE = constraintAttribute$AttributeType;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType2 = new ConstraintAttribute$AttributeType("FLOAT_TYPE", 1);
        FLOAT_TYPE = constraintAttribute$AttributeType2;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType3 = new ConstraintAttribute$AttributeType("COLOR_TYPE", 2);
        COLOR_TYPE = constraintAttribute$AttributeType3;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType4 = new ConstraintAttribute$AttributeType("COLOR_DRAWABLE_TYPE", 3);
        COLOR_DRAWABLE_TYPE = constraintAttribute$AttributeType4;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType5 = new ConstraintAttribute$AttributeType("STRING_TYPE", 4);
        STRING_TYPE = constraintAttribute$AttributeType5;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType6 = new ConstraintAttribute$AttributeType("BOOLEAN_TYPE", 5);
        BOOLEAN_TYPE = constraintAttribute$AttributeType6;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType7 = new ConstraintAttribute$AttributeType("DIMENSION_TYPE", 6);
        DIMENSION_TYPE = constraintAttribute$AttributeType7;
        E = new ConstraintAttribute$AttributeType[]{constraintAttribute$AttributeType, constraintAttribute$AttributeType2, constraintAttribute$AttributeType3, constraintAttribute$AttributeType4, constraintAttribute$AttributeType5, constraintAttribute$AttributeType6, constraintAttribute$AttributeType7};
    }

    public static ConstraintAttribute$AttributeType valueOf(String str) {
        return (ConstraintAttribute$AttributeType) Enum.valueOf(ConstraintAttribute$AttributeType.class, str);
    }

    public static ConstraintAttribute$AttributeType[] values() {
        return (ConstraintAttribute$AttributeType[]) E.clone();
    }
}
