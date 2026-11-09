package androidx.constraintlayout.solver.widgets;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ConstraintWidget$DimensionBehaviour {
    public static final ConstraintWidget$DimensionBehaviour FIXED;
    public static final ConstraintWidget$DimensionBehaviour MATCH_CONSTRAINT;
    public static final ConstraintWidget$DimensionBehaviour MATCH_PARENT;
    public static final ConstraintWidget$DimensionBehaviour WRAP_CONTENT;
    public static final /* synthetic */ ConstraintWidget$DimensionBehaviour[] b;

    static {
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = new ConstraintWidget$DimensionBehaviour("FIXED", 0);
        FIXED = constraintWidget$DimensionBehaviour;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = new ConstraintWidget$DimensionBehaviour("WRAP_CONTENT", 1);
        WRAP_CONTENT = constraintWidget$DimensionBehaviour2;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = new ConstraintWidget$DimensionBehaviour("MATCH_CONSTRAINT", 2);
        MATCH_CONSTRAINT = constraintWidget$DimensionBehaviour3;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = new ConstraintWidget$DimensionBehaviour("MATCH_PARENT", 3);
        MATCH_PARENT = constraintWidget$DimensionBehaviour4;
        b = new ConstraintWidget$DimensionBehaviour[]{constraintWidget$DimensionBehaviour, constraintWidget$DimensionBehaviour2, constraintWidget$DimensionBehaviour3, constraintWidget$DimensionBehaviour4};
    }

    public static ConstraintWidget$DimensionBehaviour valueOf(String str) {
        return (ConstraintWidget$DimensionBehaviour) Enum.valueOf(ConstraintWidget$DimensionBehaviour.class, str);
    }

    public static ConstraintWidget$DimensionBehaviour[] values() {
        return (ConstraintWidget$DimensionBehaviour[]) b.clone();
    }
}
