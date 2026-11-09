package kotlin;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class DeprecationLevel {
    public static final DeprecationLevel ERROR;
    public static final DeprecationLevel HIDDEN;
    public static final DeprecationLevel WARNING;
    public static final /* synthetic */ DeprecationLevel[] b;
    public static final /* synthetic */ ko c;

    static {
        DeprecationLevel deprecationLevel = new DeprecationLevel("WARNING", 0);
        WARNING = deprecationLevel;
        DeprecationLevel deprecationLevel2 = new DeprecationLevel("ERROR", 1);
        ERROR = deprecationLevel2;
        DeprecationLevel deprecationLevel3 = new DeprecationLevel("HIDDEN", 2);
        HIDDEN = deprecationLevel3;
        DeprecationLevel[] deprecationLevelArr = {deprecationLevel, deprecationLevel2, deprecationLevel3};
        b = deprecationLevelArr;
        c = new ko(deprecationLevelArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static DeprecationLevel valueOf(String str) {
        return (DeprecationLevel) Enum.valueOf(DeprecationLevel.class, str);
    }

    public static DeprecationLevel[] values() {
        return (DeprecationLevel[]) b.clone();
    }
}
