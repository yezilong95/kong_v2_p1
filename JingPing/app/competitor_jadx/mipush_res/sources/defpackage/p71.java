package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class p71 {
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
            a[ConstraintAnchor$Type.BASELINE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[ConstraintAnchor$Type.BOTTOM.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
