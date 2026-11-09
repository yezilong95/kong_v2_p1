package io.nats.client.impl;

import io.nats.client.ConsumeOptions;
import io.nats.client.Dispatcher;
import io.nats.client.FetchConsumeOptions;
import io.nats.client.FetchConsumer;
import io.nats.client.IterableConsumer;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamStatusCheckedException;
import io.nats.client.Message;
import io.nats.client.MessageConsumer;
import io.nats.client.MessageHandler;
import io.nats.client.OrderedConsumerContext;
import io.nats.client.api.OrderedConsumerConfiguration;
import java.io.IOException;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public class NatsOrderedConsumerContext implements OrderedConsumerContext {
    private final NatsConsumerContext impl;

    public NatsOrderedConsumerContext(NatsStreamContext natsStreamContext, OrderedConsumerConfiguration orderedConsumerConfiguration) {
        this.impl = new NatsConsumerContext(natsStreamContext, null, orderedConsumerConfiguration);
    }

    @Override // io.nats.client.BaseConsumerContext
    public MessageConsumer consume(MessageHandler messageHandler) throws IOException, JetStreamApiException {
        return this.impl.consume(messageHandler);
    }

    @Override // io.nats.client.BaseConsumerContext
    public FetchConsumer fetch(FetchConsumeOptions fetchConsumeOptions) throws IOException, JetStreamApiException {
        return this.impl.fetch(fetchConsumeOptions);
    }

    @Override // io.nats.client.BaseConsumerContext
    public FetchConsumer fetchBytes(int i) throws IOException, JetStreamApiException {
        return this.impl.fetchBytes(i);
    }

    @Override // io.nats.client.BaseConsumerContext
    public FetchConsumer fetchMessages(int i) throws IOException, JetStreamApiException {
        return this.impl.fetchMessages(i);
    }

    @Override // io.nats.client.BaseConsumerContext
    public String getConsumerName() {
        return this.impl.getConsumerName();
    }

    @Override // io.nats.client.BaseConsumerContext
    public IterableConsumer iterate() throws IOException, JetStreamApiException {
        return this.impl.iterate();
    }

    @Override // io.nats.client.BaseConsumerContext
    public Message next() throws JetStreamStatusCheckedException, InterruptedException, IOException, JetStreamApiException {
        return this.impl.next();
    }

    @Override // io.nats.client.BaseConsumerContext
    public MessageConsumer consume(Dispatcher dispatcher, MessageHandler messageHandler) throws IOException, JetStreamApiException {
        return this.impl.consume(dispatcher, messageHandler);
    }

    @Override // io.nats.client.BaseConsumerContext
    public IterableConsumer iterate(ConsumeOptions consumeOptions) throws IOException, JetStreamApiException {
        return this.impl.iterate(consumeOptions);
    }

    @Override // io.nats.client.BaseConsumerContext
    public Message next(Duration duration) throws JetStreamStatusCheckedException, InterruptedException, IOException, JetStreamApiException {
        return this.impl.next(duration);
    }

    @Override // io.nats.client.BaseConsumerContext
    public MessageConsumer consume(ConsumeOptions consumeOptions, MessageHandler messageHandler) throws IOException, JetStreamApiException {
        return this.impl.consume(consumeOptions, messageHandler);
    }

    @Override // io.nats.client.BaseConsumerContext
    public Message next(long j) throws JetStreamStatusCheckedException, InterruptedException, IOException, JetStreamApiException {
        return this.impl.next(j);
    }

    @Override // io.nats.client.BaseConsumerContext
    public MessageConsumer consume(ConsumeOptions consumeOptions, Dispatcher dispatcher, MessageHandler messageHandler) throws IOException, JetStreamApiException {
        return this.impl.consume(consumeOptions, dispatcher, messageHandler);
    }
}
