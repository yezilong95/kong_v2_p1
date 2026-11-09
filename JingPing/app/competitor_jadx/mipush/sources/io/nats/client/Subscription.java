package io.nats.client;

import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface Subscription extends Consumer {
    Dispatcher getDispatcher();

    String getQueueName();

    String getSubject();

    Message nextMessage(long j) throws IllegalStateException, InterruptedException;

    Message nextMessage(Duration duration) throws IllegalStateException, InterruptedException;

    Subscription unsubscribe(int i);

    void unsubscribe();
}
