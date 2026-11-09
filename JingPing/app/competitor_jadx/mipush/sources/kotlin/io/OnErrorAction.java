package kotlin.io;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class OnErrorAction {
    public static final /* synthetic */ OnErrorAction[] E;
    public static final /* synthetic */ hu F;
    public static final OnErrorAction SKIP;
    public static final OnErrorAction TERMINATE;

    static {
        OnErrorAction onErrorAction = new OnErrorAction("SKIP", 0);
        SKIP = onErrorAction;
        OnErrorAction onErrorAction2 = new OnErrorAction("TERMINATE", 1);
        TERMINATE = onErrorAction2;
        OnErrorAction[] onErrorActionArr = {onErrorAction, onErrorAction2};
        E = onErrorActionArr;
        F = new hu(onErrorActionArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static OnErrorAction valueOf(String str) {
        return (OnErrorAction) Enum.valueOf(OnErrorAction.class, str);
    }

    public static OnErrorAction[] values() {
        return (OnErrorAction[]) E.clone();
    }
}
