package defpackage;

import androidx.constraintlayout.widget.ConstraintAttribute$AttributeType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class nh {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[ConstraintAttribute$AttributeType.values().length];
        a = iArr;
        try {
            iArr[ConstraintAttribute$AttributeType.COLOR_TYPE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[ConstraintAttribute$AttributeType.COLOR_DRAWABLE_TYPE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[ConstraintAttribute$AttributeType.INT_TYPE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[ConstraintAttribute$AttributeType.FLOAT_TYPE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[ConstraintAttribute$AttributeType.STRING_TYPE.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[ConstraintAttribute$AttributeType.BOOLEAN_TYPE.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[ConstraintAttribute$AttributeType.DIMENSION_TYPE.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
    }
}
