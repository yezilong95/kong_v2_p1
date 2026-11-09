package io.nats.client;

import io.nats.client.api.ConsumerInfo;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public interface MessageConsumer extends AutoCloseable {
    @Override // java.lang.AutoCloseable
    void close() throws Exception;

    ConsumerInfo getCachedConsumerInfo();

    ConsumerInfo getConsumerInfo() throws IOException, JetStreamApiException;

    String getConsumerName();

    boolean isFinished();

    boolean isStopped();

    void stop();
}
