package kotlin.io.path;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CopyActionResult {
    public static final CopyActionResult CONTINUE;
    public static final CopyActionResult SKIP_SUBTREE;
    public static final CopyActionResult TERMINATE;
    public static final /* synthetic */ CopyActionResult[] b;
    public static final /* synthetic */ ko c;

    static {
        CopyActionResult copyActionResult = new CopyActionResult("CONTINUE", 0);
        CONTINUE = copyActionResult;
        CopyActionResult copyActionResult2 = new CopyActionResult("SKIP_SUBTREE", 1);
        SKIP_SUBTREE = copyActionResult2;
        CopyActionResult copyActionResult3 = new CopyActionResult("TERMINATE", 2);
        TERMINATE = copyActionResult3;
        CopyActionResult[] copyActionResultArr = {copyActionResult, copyActionResult2, copyActionResult3};
        b = copyActionResultArr;
        c = new ko(copyActionResultArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static CopyActionResult valueOf(String str) {
        return (CopyActionResult) Enum.valueOf(CopyActionResult.class, str);
    }

    public static CopyActionResult[] values() {
        return (CopyActionResult[]) b.clone();
    }
}
