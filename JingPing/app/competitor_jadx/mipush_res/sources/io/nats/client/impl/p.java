package io.nats.client.impl;

import io.nats.client.Connection;
import io.nats.client.ErrorListener;
import io.nats.client.SubscribeOptions;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.impl.NatsConnection;
import io.nats.client.impl.NatsJetStream;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements NatsJetStream.MessageManagerFactory, NatsConnection.ErrorListenerCaller {
    public final /* synthetic */ int a;

    public /* synthetic */ p(int i) {
        this.a = i;
    }

    @Override // io.nats.client.impl.NatsConnection.ErrorListenerCaller
    public void call(Connection connection, ErrorListener errorListener) {
        errorListener.socketWriteTimeout(connection);
    }

    @Override // io.nats.client.impl.NatsJetStream.MessageManagerFactory
    public MessageManager createMessageManager(NatsConnection natsConnection, NatsJetStream natsJetStream, String str, SubscribeOptions subscribeOptions, ConsumerConfiguration consumerConfiguration, boolean z, boolean z2) {
        switch (this.a) {
            case 0:
                return new PushMessageManager(natsConnection, natsJetStream, str, subscribeOptions, consumerConfiguration, z, z2);
            case 1:
                return new OrderedMessageManager(natsConnection, natsJetStream, str, subscribeOptions, consumerConfiguration, z, z2);
            case 2:
                return NatsJetStream.lambda$new$1(natsConnection, natsJetStream, str, subscribeOptions, consumerConfiguration, z, z2);
            default:
                return NatsJetStream.lambda$new$2(natsConnection, natsJetStream, str, subscribeOptions, consumerConfiguration, z, z2);
        }
    }
}
