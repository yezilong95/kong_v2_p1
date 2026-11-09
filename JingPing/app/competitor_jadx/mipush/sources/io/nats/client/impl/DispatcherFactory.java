package io.nats.client.impl;

import io.nats.client.MessageHandler;

/* JADX INFO: loaded from: classes.dex */
public class DispatcherFactory {
    public NatsDispatcher createDispatcher(NatsConnection natsConnection, MessageHandler messageHandler) {
        return natsConnection.getOptions().useDispatcherWithExecutor() ? new NatsDispatcherWithExecutor(natsConnection, messageHandler) : new NatsDispatcher(natsConnection, messageHandler);
    }
}
