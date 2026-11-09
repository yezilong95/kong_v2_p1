package kotlin;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class RequiresOptIn$Level {
    public static final RequiresOptIn$Level ERROR;
    public static final RequiresOptIn$Level WARNING;
    public static final /* synthetic */ RequiresOptIn$Level[] b;
    public static final /* synthetic */ ko c;

    static {
        RequiresOptIn$Level requiresOptIn$Level = new RequiresOptIn$Level("WARNING", 0);
        WARNING = requiresOptIn$Level;
        RequiresOptIn$Level requiresOptIn$Level2 = new RequiresOptIn$Level("ERROR", 1);
        ERROR = requiresOptIn$Level2;
        RequiresOptIn$Level[] requiresOptIn$LevelArr = {requiresOptIn$Level, requiresOptIn$Level2};
        b = requiresOptIn$LevelArr;
        c = new ko(requiresOptIn$LevelArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static RequiresOptIn$Level valueOf(String str) {
        return (RequiresOptIn$Level) Enum.valueOf(RequiresOptIn$Level.class, str);
    }

    public static RequiresOptIn$Level[] values() {
        return (RequiresOptIn$Level[]) b.clone();
    }
}
