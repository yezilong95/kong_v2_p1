package com.arialyy.aria.orm;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ActionPolicy {
    public static final ActionPolicy CASCADE;
    public static final ActionPolicy NO_ACTION;
    public static final ActionPolicy RESTRICT;
    public static final ActionPolicy SET_DEFAULT;
    public static final ActionPolicy SET_NULL;
    public static final /* synthetic */ ActionPolicy[] b;

    static {
        ActionPolicy actionPolicy = new ActionPolicy("NO_ACTION", 0);
        NO_ACTION = actionPolicy;
        ActionPolicy actionPolicy2 = new ActionPolicy("RESTRICT", 1);
        RESTRICT = actionPolicy2;
        ActionPolicy actionPolicy3 = new ActionPolicy("SET_NULL", 2);
        SET_NULL = actionPolicy3;
        ActionPolicy actionPolicy4 = new ActionPolicy("SET_DEFAULT", 3);
        SET_DEFAULT = actionPolicy4;
        ActionPolicy actionPolicy5 = new ActionPolicy("CASCADE", 4);
        CASCADE = actionPolicy5;
        b = new ActionPolicy[]{actionPolicy, actionPolicy2, actionPolicy3, actionPolicy4, actionPolicy5};
    }

    public static ActionPolicy valueOf(String str) {
        return (ActionPolicy) Enum.valueOf(ActionPolicy.class, str);
    }

    public static ActionPolicy[] values() {
        return (ActionPolicy[]) b.clone();
    }
}
