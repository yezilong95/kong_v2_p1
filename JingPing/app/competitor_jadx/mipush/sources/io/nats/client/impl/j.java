package io.nats.client.impl;

import io.nats.client.Message;
import io.nats.client.MessageHandler;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j implements MessageHandler {
    public final /* synthetic */ int a;
    public final /* synthetic */ AutoCloseable b;

    public /* synthetic */ j(AutoCloseable autoCloseable, int i) {
        this.a = i;
        this.b = autoCloseable;
    }

    @Override // io.nats.client.MessageHandler
    public final void onMessage(Message message) throws InterruptedException {
        switch (this.a) {
            case 0:
                ((NatsConnection) this.b).deliverReply(message);
                break;
            default:
                ((NatsMessageConsumer) this.b).lambda$doSub$0(message);
                break;
        }
    }
}
