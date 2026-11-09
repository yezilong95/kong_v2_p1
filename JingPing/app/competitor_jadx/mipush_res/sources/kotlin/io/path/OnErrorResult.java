package kotlin.io.path;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class OnErrorResult {
    public static final /* synthetic */ OnErrorResult[] E;
    public static final /* synthetic */ hu F;
    public static final OnErrorResult SKIP_SUBTREE;
    public static final OnErrorResult TERMINATE;

    static {
        OnErrorResult onErrorResult = new OnErrorResult("SKIP_SUBTREE", 0);
        SKIP_SUBTREE = onErrorResult;
        OnErrorResult onErrorResult2 = new OnErrorResult("TERMINATE", 1);
        TERMINATE = onErrorResult2;
        OnErrorResult[] onErrorResultArr = {onErrorResult, onErrorResult2};
        E = onErrorResultArr;
        F = new hu(onErrorResultArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static OnErrorResult valueOf(String str) {
        return (OnErrorResult) Enum.valueOf(OnErrorResult.class, str);
    }

    public static OnErrorResult[] values() {
        return (OnErrorResult[]) E.clone();
    }
}
