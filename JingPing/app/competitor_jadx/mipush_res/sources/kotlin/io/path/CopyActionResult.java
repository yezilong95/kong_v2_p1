package kotlin.io.path;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CopyActionResult {
    public static final CopyActionResult CONTINUE;
    public static final /* synthetic */ CopyActionResult[] E;
    public static final /* synthetic */ hu F;
    public static final CopyActionResult SKIP_SUBTREE;
    public static final CopyActionResult TERMINATE;

    static {
        CopyActionResult copyActionResult = new CopyActionResult("CONTINUE", 0);
        CONTINUE = copyActionResult;
        CopyActionResult copyActionResult2 = new CopyActionResult("SKIP_SUBTREE", 1);
        SKIP_SUBTREE = copyActionResult2;
        CopyActionResult copyActionResult3 = new CopyActionResult("TERMINATE", 2);
        TERMINATE = copyActionResult3;
        CopyActionResult[] copyActionResultArr = {copyActionResult, copyActionResult2, copyActionResult3};
        E = copyActionResultArr;
        F = new hu(copyActionResultArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static CopyActionResult valueOf(String str) {
        return (CopyActionResult) Enum.valueOf(CopyActionResult.class, str);
    }

    public static CopyActionResult[] values() {
        return (CopyActionResult[]) E.clone();
    }
}
