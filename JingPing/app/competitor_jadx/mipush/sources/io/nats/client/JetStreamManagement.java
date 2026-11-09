package io.nats.client;

import io.nats.client.api.AccountStatistics;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.api.ConsumerPauseResponse;
import io.nats.client.api.MessageGetRequest;
import io.nats.client.api.MessageInfo;
import io.nats.client.api.PurgeResponse;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StreamInfo;
import io.nats.client.api.StreamInfoOptions;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface JetStreamManagement {
    ConsumerInfo addOrUpdateConsumer(String str, ConsumerConfiguration consumerConfiguration) throws IOException, JetStreamApiException;

    StreamInfo addStream(StreamConfiguration streamConfiguration) throws IOException, JetStreamApiException;

    ConsumerInfo createConsumer(String str, ConsumerConfiguration consumerConfiguration) throws IOException, JetStreamApiException;

    boolean deleteConsumer(String str, String str2) throws IOException, JetStreamApiException;

    boolean deleteMessage(String str, long j) throws IOException, JetStreamApiException;

    boolean deleteMessage(String str, long j, boolean z) throws IOException, JetStreamApiException;

    boolean deleteStream(String str) throws IOException, JetStreamApiException;

    AccountStatistics getAccountStatistics() throws IOException, JetStreamApiException;

    ConsumerInfo getConsumerInfo(String str, String str2) throws IOException, JetStreamApiException;

    List<String> getConsumerNames(String str) throws IOException, JetStreamApiException;

    List<ConsumerInfo> getConsumers(String str) throws IOException, JetStreamApiException;

    MessageInfo getFirstMessage(String str, String str2) throws IOException, JetStreamApiException;

    MessageInfo getFirstMessage(String str, ZonedDateTime zonedDateTime) throws IOException, JetStreamApiException;

    MessageInfo getFirstMessage(String str, ZonedDateTime zonedDateTime, String str2) throws IOException, JetStreamApiException;

    MessageInfo getLastMessage(String str, String str2) throws IOException, JetStreamApiException;

    MessageInfo getMessage(String str, long j) throws IOException, JetStreamApiException;

    MessageInfo getMessage(String str, MessageGetRequest messageGetRequest) throws IOException, JetStreamApiException;

    MessageInfo getNextMessage(String str, long j, String str2) throws IOException, JetStreamApiException;

    StreamInfo getStreamInfo(String str) throws IOException, JetStreamApiException;

    StreamInfo getStreamInfo(String str, StreamInfoOptions streamInfoOptions) throws IOException, JetStreamApiException;

    List<String> getStreamNames() throws IOException, JetStreamApiException;

    List<String> getStreamNames(String str) throws IOException, JetStreamApiException;

    List<StreamInfo> getStreams() throws IOException, JetStreamApiException;

    List<StreamInfo> getStreams(String str) throws IOException, JetStreamApiException;

    JetStream jetStream();

    ConsumerPauseResponse pauseConsumer(String str, String str2, ZonedDateTime zonedDateTime) throws IOException, JetStreamApiException;

    PurgeResponse purgeStream(String str) throws IOException, JetStreamApiException;

    PurgeResponse purgeStream(String str, PurgeOptions purgeOptions) throws IOException, JetStreamApiException;

    boolean resumeConsumer(String str, String str2) throws IOException, JetStreamApiException;

    ConsumerInfo updateConsumer(String str, ConsumerConfiguration consumerConfiguration) throws IOException, JetStreamApiException;

    StreamInfo updateStream(StreamConfiguration streamConfiguration) throws IOException, JetStreamApiException;
}
