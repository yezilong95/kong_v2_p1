package kotlin;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class DeprecationLevel {
    public static final /* synthetic */ DeprecationLevel[] E;
    public static final DeprecationLevel ERROR;
    public static final /* synthetic */ hu F;
    public static final DeprecationLevel HIDDEN;
    public static final DeprecationLevel WARNING;

    static {
        DeprecationLevel deprecationLevel = new DeprecationLevel("WARNING", 0);
        WARNING = deprecationLevel;
        DeprecationLevel deprecationLevel2 = new DeprecationLevel("ERROR", 1);
        ERROR = deprecationLevel2;
        DeprecationLevel deprecationLevel3 = new DeprecationLevel("HIDDEN", 2);
        HIDDEN = deprecationLevel3;
        DeprecationLevel[] deprecationLevelArr = {deprecationLevel, deprecationLevel2, deprecationLevel3};
        E = deprecationLevelArr;
        F = new hu(deprecationLevelArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static DeprecationLevel valueOf(String str) {
        return (DeprecationLevel) Enum.valueOf(DeprecationLevel.class, str);
    }

    public static DeprecationLevel[] values() {
        return (DeprecationLevel[]) E.clone();
    }
}
