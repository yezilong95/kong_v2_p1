package kotlin.contracts;

import defpackage.gu;
import defpackage.hu;
import io.nats.client.support.NatsConstants;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class InvocationKind {
    public static final InvocationKind AT_LEAST_ONCE;
    public static final InvocationKind AT_MOST_ONCE;
    public static final /* synthetic */ InvocationKind[] E;
    public static final InvocationKind EXACTLY_ONCE;
    public static final /* synthetic */ hu F;
    public static final InvocationKind UNKNOWN;

    static {
        InvocationKind invocationKind = new InvocationKind("AT_MOST_ONCE", 0);
        AT_MOST_ONCE = invocationKind;
        InvocationKind invocationKind2 = new InvocationKind("AT_LEAST_ONCE", 1);
        AT_LEAST_ONCE = invocationKind2;
        InvocationKind invocationKind3 = new InvocationKind("EXACTLY_ONCE", 2);
        EXACTLY_ONCE = invocationKind3;
        InvocationKind invocationKind4 = new InvocationKind(NatsConstants.UNKNOWN_OP, 3);
        UNKNOWN = invocationKind4;
        InvocationKind[] invocationKindArr = {invocationKind, invocationKind2, invocationKind3, invocationKind4};
        E = invocationKindArr;
        F = new hu(invocationKindArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static InvocationKind valueOf(String str) {
        return (InvocationKind) Enum.valueOf(InvocationKind.class, str);
    }

    public static InvocationKind[] values() {
        return (InvocationKind[]) E.clone();
    }
}
