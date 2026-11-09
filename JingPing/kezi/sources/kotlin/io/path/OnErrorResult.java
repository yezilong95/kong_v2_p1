package kotlin.io.path;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class OnErrorResult {
    public static final OnErrorResult SKIP_SUBTREE;
    public static final OnErrorResult TERMINATE;
    public static final /* synthetic */ OnErrorResult[] b;
    public static final /* synthetic */ ko c;

    static {
        OnErrorResult onErrorResult = new OnErrorResult("SKIP_SUBTREE", 0);
        SKIP_SUBTREE = onErrorResult;
        OnErrorResult onErrorResult2 = new OnErrorResult("TERMINATE", 1);
        TERMINATE = onErrorResult2;
        OnErrorResult[] onErrorResultArr = {onErrorResult, onErrorResult2};
        b = onErrorResultArr;
        c = new ko(onErrorResultArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static OnErrorResult valueOf(String str) {
        return (OnErrorResult) Enum.valueOf(OnErrorResult.class, str);
    }

    public static OnErrorResult[] values() {
        return (OnErrorResult[]) b.clone();
    }
}
