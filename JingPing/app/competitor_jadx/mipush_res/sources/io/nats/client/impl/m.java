package io.nats.client.impl;

import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m implements BiConsumer {
    public final /* synthetic */ int a;

    public /* synthetic */ m(int i) {
        this.a = i;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        String str = (String) obj;
        switch (this.a) {
            case 0:
                NatsConnection.lambda$reconnectImpl$2(str, (NatsDispatcher) obj2);
                break;
            case 1:
                ((NatsDispatcher) obj2).stop(false);
                break;
            default:
                ((NatsSubscription) obj2).invalidate();
                break;
        }
    }
}
