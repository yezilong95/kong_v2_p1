package kotlin.contracts;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class InvocationKind {
    public static final InvocationKind AT_LEAST_ONCE;
    public static final InvocationKind AT_MOST_ONCE;
    public static final InvocationKind EXACTLY_ONCE;
    public static final InvocationKind UNKNOWN;
    public static final /* synthetic */ InvocationKind[] b;
    public static final /* synthetic */ ko c;

    static {
        InvocationKind invocationKind = new InvocationKind("AT_MOST_ONCE", 0);
        AT_MOST_ONCE = invocationKind;
        InvocationKind invocationKind2 = new InvocationKind("AT_LEAST_ONCE", 1);
        AT_LEAST_ONCE = invocationKind2;
        InvocationKind invocationKind3 = new InvocationKind("EXACTLY_ONCE", 2);
        EXACTLY_ONCE = invocationKind3;
        InvocationKind invocationKind4 = new InvocationKind("UNKNOWN", 3);
        UNKNOWN = invocationKind4;
        InvocationKind[] invocationKindArr = {invocationKind, invocationKind2, invocationKind3, invocationKind4};
        b = invocationKindArr;
        c = new ko(invocationKindArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static InvocationKind valueOf(String str) {
        return (InvocationKind) Enum.valueOf(InvocationKind.class, str);
    }

    public static InvocationKind[] values() {
        return (InvocationKind[]) b.clone();
    }
}
