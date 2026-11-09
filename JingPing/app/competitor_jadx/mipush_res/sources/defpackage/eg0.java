package defpackage;

import io.nats.client.impl.NatsMessage;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class eg0 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((NatsMessage) obj).isProtocolFilterOnStop();
    }
}
