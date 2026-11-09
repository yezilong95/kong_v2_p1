package io.nats.client.impl;

import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return NatsSubscription.lambda$setBeforeQueueProcessor$0((NatsMessage) obj);
    }
}
