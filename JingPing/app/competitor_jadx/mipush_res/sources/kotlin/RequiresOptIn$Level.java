package kotlin;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class RequiresOptIn$Level {
    public static final /* synthetic */ RequiresOptIn$Level[] E;
    public static final RequiresOptIn$Level ERROR;
    public static final /* synthetic */ hu F;
    public static final RequiresOptIn$Level WARNING;

    static {
        RequiresOptIn$Level requiresOptIn$Level = new RequiresOptIn$Level("WARNING", 0);
        WARNING = requiresOptIn$Level;
        RequiresOptIn$Level requiresOptIn$Level2 = new RequiresOptIn$Level("ERROR", 1);
        ERROR = requiresOptIn$Level2;
        RequiresOptIn$Level[] requiresOptIn$LevelArr = {requiresOptIn$Level, requiresOptIn$Level2};
        E = requiresOptIn$LevelArr;
        F = new hu(requiresOptIn$LevelArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static RequiresOptIn$Level valueOf(String str) {
        return (RequiresOptIn$Level) Enum.valueOf(RequiresOptIn$Level.class, str);
    }

    public static RequiresOptIn$Level[] values() {
        return (RequiresOptIn$Level[]) E.clone();
    }
}
