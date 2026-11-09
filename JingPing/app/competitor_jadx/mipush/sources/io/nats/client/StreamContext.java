package io.nats.client;

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
public interface StreamContext {
    ConsumerContext createOrUpdateConsumer(ConsumerConfiguration consumerConfiguration) throws IOException, JetStreamApiException;

    OrderedConsumerContext createOrderedConsumer(OrderedConsumerConfiguration orderedConsumerConfiguration) throws IOException, JetStreamApiException;

    boolean deleteConsumer(String str) throws IOException, JetStreamApiException;

    boolean deleteMessage(long j) throws IOException, JetStreamApiException;

    boolean deleteMessage(long j, boolean z) throws IOException, JetStreamApiException;

    ConsumerContext getConsumerContext(String str) throws IOException, JetStreamApiException;

    ConsumerInfo getConsumerInfo(String str) throws IOException, JetStreamApiException;

    List<String> getConsumerNames() throws IOException, JetStreamApiException;

    List<ConsumerInfo> getConsumers() throws IOException, JetStreamApiException;

    MessageInfo getFirstMessage(String str) throws IOException, JetStreamApiException;

    MessageInfo getLastMessage(String str) throws IOException, JetStreamApiException;

    MessageInfo getMessage(long j) throws IOException, JetStreamApiException;

    MessageInfo getNextMessage(long j, String str) throws IOException, JetStreamApiException;

    StreamInfo getStreamInfo() throws IOException, JetStreamApiException;

    StreamInfo getStreamInfo(StreamInfoOptions streamInfoOptions) throws IOException, JetStreamApiException;

    String getStreamName();

    PurgeResponse purge() throws IOException, JetStreamApiException;

    PurgeResponse purge(PurgeOptions purgeOptions) throws IOException, JetStreamApiException;
}
