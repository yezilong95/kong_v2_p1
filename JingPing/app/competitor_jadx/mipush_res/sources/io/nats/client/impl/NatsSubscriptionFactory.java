package io.nats.client.impl;

/* JADX INFO: loaded from: classes.dex */
interface NatsSubscriptionFactory {
    NatsSubscription createNatsSubscription(String str, String str2, String str3, NatsConnection natsConnection, NatsDispatcher natsDispatcher);
}
