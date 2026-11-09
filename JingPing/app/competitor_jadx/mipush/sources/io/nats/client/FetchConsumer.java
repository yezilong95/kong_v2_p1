package io.nats.client;

/* JADX INFO: loaded from: classes.dex */
public interface FetchConsumer extends MessageConsumer {
    Message nextMessage() throws JetStreamStatusCheckedException, InterruptedException;
}
