package defpackage;

import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class t00 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.values().length];
        try {
            iArr[FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_CONFIGURE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_CREATE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_UPGRADE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_DOWNGRADE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_OPEN.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        a = iArr;
    }
}
