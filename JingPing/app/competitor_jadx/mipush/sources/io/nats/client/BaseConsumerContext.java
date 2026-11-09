package io.nats.client;

import java.io.IOException;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface BaseConsumerContext {
    MessageConsumer consume(ConsumeOptions consumeOptions, Dispatcher dispatcher, MessageHandler messageHandler) throws IOException, JetStreamApiException;

    MessageConsumer consume(ConsumeOptions consumeOptions, MessageHandler messageHandler) throws IOException, JetStreamApiException;

    MessageConsumer consume(Dispatcher dispatcher, MessageHandler messageHandler) throws IOException, JetStreamApiException;

    MessageConsumer consume(MessageHandler messageHandler) throws IOException, JetStreamApiException;

    FetchConsumer fetch(FetchConsumeOptions fetchConsumeOptions) throws IOException, JetStreamApiException;

    FetchConsumer fetchBytes(int i) throws IOException, JetStreamApiException;

    FetchConsumer fetchMessages(int i) throws IOException, JetStreamApiException;

    String getConsumerName();

    IterableConsumer iterate() throws IOException, JetStreamApiException;

    IterableConsumer iterate(ConsumeOptions consumeOptions) throws IOException, JetStreamApiException;

    Message next() throws JetStreamStatusCheckedException, InterruptedException, IOException, JetStreamApiException;

    Message next(long j) throws JetStreamStatusCheckedException, InterruptedException, IOException, JetStreamApiException;

    Message next(Duration duration) throws JetStreamStatusCheckedException, InterruptedException, IOException, JetStreamApiException;
}
