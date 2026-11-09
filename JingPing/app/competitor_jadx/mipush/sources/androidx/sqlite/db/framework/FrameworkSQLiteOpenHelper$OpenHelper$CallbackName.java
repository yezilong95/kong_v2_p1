package androidx.sqlite.db.framework;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class FrameworkSQLiteOpenHelper$OpenHelper$CallbackName {
    public static final /* synthetic */ FrameworkSQLiteOpenHelper$OpenHelper$CallbackName[] E;
    public static final FrameworkSQLiteOpenHelper$OpenHelper$CallbackName ON_CONFIGURE;
    public static final FrameworkSQLiteOpenHelper$OpenHelper$CallbackName ON_CREATE;
    public static final FrameworkSQLiteOpenHelper$OpenHelper$CallbackName ON_DOWNGRADE;
    public static final FrameworkSQLiteOpenHelper$OpenHelper$CallbackName ON_OPEN;
    public static final FrameworkSQLiteOpenHelper$OpenHelper$CallbackName ON_UPGRADE;

    static {
        FrameworkSQLiteOpenHelper$OpenHelper$CallbackName frameworkSQLiteOpenHelper$OpenHelper$CallbackName = new FrameworkSQLiteOpenHelper$OpenHelper$CallbackName("ON_CONFIGURE", 0);
        ON_CONFIGURE = frameworkSQLiteOpenHelper$OpenHelper$CallbackName;
        FrameworkSQLiteOpenHelper$OpenHelper$CallbackName frameworkSQLiteOpenHelper$OpenHelper$CallbackName2 = new FrameworkSQLiteOpenHelper$OpenHelper$CallbackName("ON_CREATE", 1);
        ON_CREATE = frameworkSQLiteOpenHelper$OpenHelper$CallbackName2;
        FrameworkSQLiteOpenHelper$OpenHelper$CallbackName frameworkSQLiteOpenHelper$OpenHelper$CallbackName3 = new FrameworkSQLiteOpenHelper$OpenHelper$CallbackName("ON_UPGRADE", 2);
        ON_UPGRADE = frameworkSQLiteOpenHelper$OpenHelper$CallbackName3;
        FrameworkSQLiteOpenHelper$OpenHelper$CallbackName frameworkSQLiteOpenHelper$OpenHelper$CallbackName4 = new FrameworkSQLiteOpenHelper$OpenHelper$CallbackName("ON_DOWNGRADE", 3);
        ON_DOWNGRADE = frameworkSQLiteOpenHelper$OpenHelper$CallbackName4;
        FrameworkSQLiteOpenHelper$OpenHelper$CallbackName frameworkSQLiteOpenHelper$OpenHelper$CallbackName5 = new FrameworkSQLiteOpenHelper$OpenHelper$CallbackName("ON_OPEN", 4);
        ON_OPEN = frameworkSQLiteOpenHelper$OpenHelper$CallbackName5;
        E = new FrameworkSQLiteOpenHelper$OpenHelper$CallbackName[]{frameworkSQLiteOpenHelper$OpenHelper$CallbackName, frameworkSQLiteOpenHelper$OpenHelper$CallbackName2, frameworkSQLiteOpenHelper$OpenHelper$CallbackName3, frameworkSQLiteOpenHelper$OpenHelper$CallbackName4, frameworkSQLiteOpenHelper$OpenHelper$CallbackName5};
    }

    public static FrameworkSQLiteOpenHelper$OpenHelper$CallbackName valueOf(String str) {
        return (FrameworkSQLiteOpenHelper$OpenHelper$CallbackName) Enum.valueOf(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.class, str);
    }

    public static FrameworkSQLiteOpenHelper$OpenHelper$CallbackName[] values() {
        return (FrameworkSQLiteOpenHelper$OpenHelper$CallbackName[]) E.clone();
    }
}
