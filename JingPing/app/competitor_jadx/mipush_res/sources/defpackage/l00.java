package defpackage;

import androidx.lifecycle.Lifecycle$State;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class l00 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[Lifecycle$State.values().length];
        a = iArr;
        try {
            iArr[Lifecycle$State.RESUMED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[Lifecycle$State.STARTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[Lifecycle$State.CREATED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[Lifecycle$State.INITIALIZED.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
