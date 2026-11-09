package io.nats.client.impl;

import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n implements BiConsumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ n(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        String str = (String) obj;
        NatsSubscription natsSubscription = (NatsSubscription) obj2;
        switch (this.a) {
            case 0:
                ((NatsDispatcher) this.b).lambda$stop$0(str, natsSubscription);
                break;
            case 1:
                ((NatsDispatcher) this.b).lambda$stop$1(str, natsSubscription);
                break;
            case 2:
                ((NatsDispatcher) this.b).lambda$sendUnsubForDrain$4(str, natsSubscription);
                break;
            case 3:
                ((NatsDispatcher) this.b).lambda$sendUnsubForDrain$5(str, natsSubscription);
                break;
            case 4:
                ((NatsDispatcher) this.b).lambda$resendSubscriptions$2(str, natsSubscription);
                break;
            case 5:
                ((NatsDispatcher) this.b).lambda$resendSubscriptions$3(str, natsSubscription);
                break;
            default:
                ((NatsConnection) this.b).lambda$reconnectImpl$1(str, natsSubscription);
                break;
        }
    }
}
