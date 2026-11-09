package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class ph {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[ConstraintWidget$DimensionBehaviour.values().length];
        a = iArr;
        try {
            iArr[ConstraintWidget$DimensionBehaviour.FIXED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[ConstraintWidget$DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[ConstraintWidget$DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
