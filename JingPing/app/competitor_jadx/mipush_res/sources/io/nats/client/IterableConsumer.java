package io.nats.client;

import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface IterableConsumer extends MessageConsumer {
    Message nextMessage(long j) throws JetStreamStatusCheckedException, InterruptedException;

    Message nextMessage(Duration duration) throws JetStreamStatusCheckedException, InterruptedException;
}
