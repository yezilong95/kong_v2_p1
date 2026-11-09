package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class lg {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[ConstraintWidget$DimensionBehaviour.values().length];
        b = iArr;
        try {
            iArr[ConstraintWidget$DimensionBehaviour.FIXED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            b[ConstraintWidget$DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            b[ConstraintWidget$DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            b[ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        int[] iArr2 = new int[ConstraintAnchor$Type.values().length];
        a = iArr2;
        try {
            iArr2[ConstraintAnchor$Type.LEFT.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[ConstraintAnchor$Type.TOP.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[ConstraintAnchor$Type.RIGHT.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            a[ConstraintAnchor$Type.BOTTOM.ordinal()] = 4;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            a[ConstraintAnchor$Type.BASELINE.ordinal()] = 5;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            a[ConstraintAnchor$Type.CENTER.ordinal()] = 6;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            a[ConstraintAnchor$Type.CENTER_X.ordinal()] = 7;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            a[ConstraintAnchor$Type.CENTER_Y.ordinal()] = 8;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            a[ConstraintAnchor$Type.NONE.ordinal()] = 9;
        } catch (NoSuchFieldError unused13) {
        }
    }
}
