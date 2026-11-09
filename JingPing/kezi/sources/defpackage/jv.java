package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class jv {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[ConstraintAnchor$Type.values().length];
        a = iArr;
        try {
            iArr[ConstraintAnchor$Type.LEFT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[ConstraintAnchor$Type.RIGHT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[ConstraintAnchor$Type.TOP.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[ConstraintAnchor$Type.BOTTOM.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[ConstraintAnchor$Type.BASELINE.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[ConstraintAnchor$Type.CENTER.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[ConstraintAnchor$Type.CENTER_X.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            a[ConstraintAnchor$Type.CENTER_Y.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            a[ConstraintAnchor$Type.NONE.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
    }
}
