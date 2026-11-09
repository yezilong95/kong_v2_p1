package io.nats.client;

import io.nats.client.api.ConsumerInfo;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public interface ConsumerContext extends BaseConsumerContext {
    ConsumerInfo getCachedConsumerInfo();

    ConsumerInfo getConsumerInfo() throws IOException, JetStreamApiException;
}
