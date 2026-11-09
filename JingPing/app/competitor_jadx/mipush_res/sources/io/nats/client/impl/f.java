package io.nats.client.impl;

import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f implements Predicate {
    public final /* synthetic */ int a;

    public /* synthetic */ f(int i) {
        this.a = i;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.a) {
            case 0:
                return NatsConnection.lambda$drain$17((NatsSubscription) obj);
            default:
                return ((NatsConsumer) obj).isDrained();
        }
    }
}
