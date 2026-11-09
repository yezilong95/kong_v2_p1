package kotlin.io;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class OnErrorAction {
    public static final OnErrorAction SKIP;
    public static final OnErrorAction TERMINATE;
    public static final /* synthetic */ OnErrorAction[] b;
    public static final /* synthetic */ ko c;

    static {
        OnErrorAction onErrorAction = new OnErrorAction("SKIP", 0);
        SKIP = onErrorAction;
        OnErrorAction onErrorAction2 = new OnErrorAction("TERMINATE", 1);
        TERMINATE = onErrorAction2;
        OnErrorAction[] onErrorActionArr = {onErrorAction, onErrorAction2};
        b = onErrorActionArr;
        c = new ko(onErrorActionArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static OnErrorAction valueOf(String str) {
        return (OnErrorAction) Enum.valueOf(OnErrorAction.class, str);
    }

    public static OnErrorAction[] values() {
        return (OnErrorAction[]) b.clone();
    }
}
