package kotlinx.coroutines.selects;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class TrySelectDetailedResult {
    public static final TrySelectDetailedResult ALREADY_SELECTED;
    public static final TrySelectDetailedResult CANCELLED;
    public static final /* synthetic */ TrySelectDetailedResult[] E;
    public static final /* synthetic */ hu F;
    public static final TrySelectDetailedResult REREGISTER;
    public static final TrySelectDetailedResult SUCCESSFUL;

    static {
        TrySelectDetailedResult trySelectDetailedResult = new TrySelectDetailedResult("SUCCESSFUL", 0);
        SUCCESSFUL = trySelectDetailedResult;
        TrySelectDetailedResult trySelectDetailedResult2 = new TrySelectDetailedResult("REREGISTER", 1);
        REREGISTER = trySelectDetailedResult2;
        TrySelectDetailedResult trySelectDetailedResult3 = new TrySelectDetailedResult("CANCELLED", 2);
        CANCELLED = trySelectDetailedResult3;
        TrySelectDetailedResult trySelectDetailedResult4 = new TrySelectDetailedResult("ALREADY_SELECTED", 3);
        ALREADY_SELECTED = trySelectDetailedResult4;
        TrySelectDetailedResult[] trySelectDetailedResultArr = {trySelectDetailedResult, trySelectDetailedResult2, trySelectDetailedResult3, trySelectDetailedResult4};
        E = trySelectDetailedResultArr;
        F = new hu(trySelectDetailedResultArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static TrySelectDetailedResult valueOf(String str) {
        return (TrySelectDetailedResult) Enum.valueOf(TrySelectDetailedResult.class, str);
    }

    public static TrySelectDetailedResult[] values() {
        return (TrySelectDetailedResult[]) E.clone();
    }
}
