package io.nats.client.impl;

import io.nats.client.Dispatcher;
import io.nats.client.JetStreamApiException;
import io.nats.client.MessageHandler;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
interface SimplifiedSubscriptionMaker {
    NatsJetStreamPullSubscription subscribe(MessageHandler messageHandler, Dispatcher dispatcher, PullMessageManager pullMessageManager, Long l) throws IOException, JetStreamApiException;
}
