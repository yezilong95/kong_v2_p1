package io.nats.client.impl;

import io.nats.client.ConsumerContext;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamOptions;
import io.nats.client.OrderedConsumerContext;
import io.nats.client.PurgeOptions;
import io.nats.client.StreamContext;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.api.MessageInfo;
import io.nats.client.api.OrderedConsumerConfiguration;
import io.nats.client.api.PurgeResponse;
import io.nats.client.api.StreamInfo;
import io.nats.client.api.StreamInfoOptions;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class NatsStreamContext implements StreamContext {
    final NatsJetStream js;
    final NatsJetStreamManagement jsm;
    final String streamName;

    public NatsStreamContext(String str, NatsJetStream natsJetStream, NatsConnection natsConnection, JetStreamOptions jetStreamOptions) throws IOException, JetStreamApiException {
        this.streamName = str;
        this.js = natsJetStream == null ? new NatsJetStream(natsConnection, jetStreamOptions) : natsJetStream;
        NatsJetStreamManagement natsJetStreamManagement = new NatsJetStreamManagement(natsConnection, jetStreamOptions);
        this.jsm = natsJetStreamManagement;
        natsJetStreamManagement.getStreamInfo(str);
    }

    @Override // io.nats.client.StreamContext
    public ConsumerContext createOrUpdateConsumer(ConsumerConfiguration consumerConfiguration) throws IOException, JetStreamApiException {
        return new NatsConsumerContext(this, this.jsm.addOrUpdateConsumer(this.streamName, consumerConfiguration), null);
    }

    @Override // io.nats.client.StreamContext
    public OrderedConsumerContext createOrderedConsumer(OrderedConsumerConfiguration orderedConsumerConfiguration) throws IOException, JetStreamApiException {
        return new NatsOrderedConsumerContext(this, orderedConsumerConfiguration);
    }

    @Override // io.nats.client.StreamContext
    public boolean deleteConsumer(String str) throws IOException, JetStreamApiException {
        return this.jsm.deleteConsumer(this.streamName, str);
    }

    @Override // io.nats.client.StreamContext
    public boolean deleteMessage(long j) throws IOException, JetStreamApiException {
        return this.jsm.deleteMessage(this.streamName, j);
    }

    @Override // io.nats.client.StreamContext
    public ConsumerContext getConsumerContext(String str) throws IOException, JetStreamApiException {
        return new NatsConsumerContext(this, this.jsm.getConsumerInfo(this.streamName, str), null);
    }

    @Override // io.nats.client.StreamContext
    public ConsumerInfo getConsumerInfo(String str) throws IOException, JetStreamApiException {
        return this.jsm.getConsumerInfo(this.streamName, str);
    }

    @Override // io.nats.client.StreamContext
    public List<String> getConsumerNames() throws IOException, JetStreamApiException {
        return this.jsm.getConsumerNames(this.streamName);
    }

    @Override // io.nats.client.StreamContext
    public List<ConsumerInfo> getConsumers() throws IOException, JetStreamApiException {
        return this.jsm.getConsumers(this.streamName);
    }

    @Override // io.nats.client.StreamContext
    public MessageInfo getFirstMessage(String str) throws IOException, JetStreamApiException {
        return this.jsm.getFirstMessage(this.streamName, str);
    }

    @Override // io.nats.client.StreamContext
    public MessageInfo getLastMessage(String str) throws IOException, JetStreamApiException {
        return this.jsm.getLastMessage(this.streamName, str);
    }

    @Override // io.nats.client.StreamContext
    public MessageInfo getMessage(long j) throws IOException, JetStreamApiException {
        return this.jsm.getMessage(this.streamName, j);
    }

    @Override // io.nats.client.StreamContext
    public MessageInfo getNextMessage(long j, String str) throws IOException, JetStreamApiException {
        return this.jsm.getNextMessage(this.streamName, j, str);
    }

    @Override // io.nats.client.StreamContext
    public StreamInfo getStreamInfo() throws IOException, JetStreamApiException {
        return this.jsm.getStreamInfo(this.streamName);
    }

    @Override // io.nats.client.StreamContext
    public String getStreamName() {
        return this.streamName;
    }

    @Override // io.nats.client.StreamContext
    public PurgeResponse purge() throws IOException, JetStreamApiException {
        return this.jsm.purgeStream(this.streamName);
    }

    @Override // io.nats.client.StreamContext
    public boolean deleteMessage(long j, boolean z) throws IOException, JetStreamApiException {
        return this.jsm.deleteMessage(this.streamName, j, z);
    }

    @Override // io.nats.client.StreamContext
    public StreamInfo getStreamInfo(StreamInfoOptions streamInfoOptions) throws IOException, JetStreamApiException {
        return this.jsm.getStreamInfo(this.streamName, streamInfoOptions);
    }

    @Override // io.nats.client.StreamContext
    public PurgeResponse purge(PurgeOptions purgeOptions) throws IOException, JetStreamApiException {
        return this.jsm.purgeStream(this.streamName, purgeOptions);
    }
}
