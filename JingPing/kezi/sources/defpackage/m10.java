package defpackage;

import androidx.lifecycle.Lifecycle$State;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class m10 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[Lifecycle$State.values().length];
        try {
            iArr[Lifecycle$State.CREATED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[Lifecycle$State.STARTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[Lifecycle$State.RESUMED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[Lifecycle$State.DESTROYED.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[Lifecycle$State.INITIALIZED.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        a = iArr;
    }
}
