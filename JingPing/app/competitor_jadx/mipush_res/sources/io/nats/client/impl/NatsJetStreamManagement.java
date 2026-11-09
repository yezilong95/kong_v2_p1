package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamManagement;
import io.nats.client.JetStreamOptions;
import io.nats.client.Message;
import io.nats.client.PurgeOptions;
import io.nats.client.api.AccountStatistics;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerCreateRequest;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.api.ConsumerPauseRequest;
import io.nats.client.api.ConsumerPauseResponse;
import io.nats.client.api.Error;
import io.nats.client.api.MessageDeleteRequest;
import io.nats.client.api.MessageGetRequest;
import io.nats.client.api.MessageInfo;
import io.nats.client.api.PurgeResponse;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StreamInfo;
import io.nats.client.api.StreamInfoOptions;
import io.nats.client.api.SuccessApiResponse;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NatsJetStreamManagement extends NatsJetStreamImpl implements JetStreamManagement {
    private NatsJetStream js;

    public NatsJetStreamManagement(NatsConnection natsConnection, JetStreamOptions jetStreamOptions) throws IOException {
        super(natsConnection, jetStreamOptions);
    }

    private MessageInfo _getMessage(String str, MessageGetRequest messageGetRequest) throws IOException, JetStreamApiException {
        byte[] bArrSerialize;
        String str2;
        Validator.validateNotNull(messageGetRequest, "Message Get Request");
        if (!getCachedStreamInfo(str).allowDirect) {
            return new MessageInfo(makeRequestResponseRequired(zn.v("STREAM.MSG.GET.", str), messageGetRequest.serialize(), getTimeout()), str, false).throwOnHasError();
        }
        if (messageGetRequest.isLastBySubject()) {
            str2 = "DIRECT.GET." + str + NatsConstants.DOT + messageGetRequest.getLastBySubject();
            bArrSerialize = null;
        } else {
            String strV = zn.v("DIRECT.GET.", str);
            bArrSerialize = messageGetRequest.serialize();
            str2 = strV;
        }
        Message messageMakeRequestResponseRequired = makeRequestResponseRequired(str2, bArrSerialize, getTimeout());
        if (messageMakeRequestResponseRequired.isStatusMessage()) {
            throw new JetStreamApiException(Error.convert(messageMakeRequestResponseRequired.getStatus()));
        }
        return new MessageInfo(messageMakeRequestResponseRequired, str, true);
    }

    private StreamInfo addOrUpdateStream(StreamConfiguration streamConfiguration, String str) throws IOException, JetStreamApiException {
        Validator.validateNotNull(streamConfiguration, "Configuration");
        String name = streamConfiguration.getName();
        if (Validator.nullOrEmpty(name)) {
            throw new IllegalArgumentException("Configuration must have a valid stream name");
        }
        return createAndCacheStreamInfoThrowOnError(name, makeRequestResponseRequired(String.format(str, name), streamConfiguration.toJson().getBytes(StandardCharsets.UTF_8), getTimeout()));
    }

    @Override // io.nats.client.JetStreamManagement
    public ConsumerInfo addOrUpdateConsumer(String str, ConsumerConfiguration consumerConfiguration) throws IOException, JetStreamApiException {
        Validator.validateStreamName(str, true);
        Validator.validateNotNull(consumerConfiguration, "Config");
        return _createConsumer(str, consumerConfiguration, ConsumerCreateRequest.Action.CreateOrUpdate);
    }

    @Override // io.nats.client.JetStreamManagement
    public StreamInfo addStream(StreamConfiguration streamConfiguration) throws IOException, JetStreamApiException {
        return addOrUpdateStream(streamConfiguration, NatsJetStreamConstants.JSAPI_STREAM_CREATE);
    }

    @Override // io.nats.client.JetStreamManagement
    public ConsumerInfo createConsumer(String str, ConsumerConfiguration consumerConfiguration) throws IOException, JetStreamApiException {
        Validator.validateStreamName(str, true);
        Validator.validateNotNull(consumerConfiguration, "Config");
        return _createConsumer(str, consumerConfiguration, ConsumerCreateRequest.Action.Create);
    }

    @Override // io.nats.client.JetStreamManagement
    public boolean deleteConsumer(String str, String str2) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "Stream Name");
        Validator.validateNotNull(str2, "Consumer Name");
        return new SuccessApiResponse(makeRequestResponseRequired("CONSUMER.DELETE." + str + NatsConstants.DOT + str2, null, getTimeout())).throwOnHasError().getSuccess();
    }

    @Override // io.nats.client.JetStreamManagement
    public boolean deleteMessage(String str, long j) throws IOException, JetStreamApiException {
        return deleteMessage(str, j, true);
    }

    @Override // io.nats.client.JetStreamManagement
    public boolean deleteStream(String str) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "Stream Name");
        return new SuccessApiResponse(makeRequestResponseRequired("STREAM.DELETE." + str, null, getTimeout())).throwOnHasError().getSuccess();
    }

    @Override // io.nats.client.JetStreamManagement
    public AccountStatistics getAccountStatistics() throws IOException, JetStreamApiException {
        return new AccountStatistics(makeRequestResponseRequired("INFO", null, getTimeout())).throwOnHasError();
    }

    @Override // io.nats.client.JetStreamManagement
    public ConsumerInfo getConsumerInfo(String str, String str2) throws IOException, JetStreamApiException {
        return super._getConsumerInfo(str, str2);
    }

    @Override // io.nats.client.JetStreamManagement
    public List<String> getConsumerNames(String str) throws IOException, JetStreamApiException {
        return getConsumerNames(str, null);
    }

    @Override // io.nats.client.JetStreamManagement
    public List<ConsumerInfo> getConsumers(String str) throws IOException, JetStreamApiException {
        String strV = zn.v("CONSUMER.LIST.", str);
        ConsumerListReader consumerListReader = new ConsumerListReader();
        while (consumerListReader.hasMore()) {
            consumerListReader.process(makeRequestResponseRequired(strV, consumerListReader.nextJson(), getTimeout()));
        }
        return consumerListReader.getConsumers();
    }

    @Override // io.nats.client.JetStreamManagement
    public MessageInfo getFirstMessage(String str, String str2) throws IOException, JetStreamApiException {
        return _getMessage(str, MessageGetRequest.firstForSubject(str2));
    }

    @Override // io.nats.client.JetStreamManagement
    public MessageInfo getLastMessage(String str, String str2) throws IOException, JetStreamApiException {
        return _getMessage(str, MessageGetRequest.lastForSubject(str2));
    }

    @Override // io.nats.client.JetStreamManagement
    public MessageInfo getMessage(String str, long j) throws IOException, JetStreamApiException {
        return _getMessage(str, MessageGetRequest.forSequence(j));
    }

    @Override // io.nats.client.JetStreamManagement
    public MessageInfo getNextMessage(String str, long j, String str2) throws IOException, JetStreamApiException {
        return _getMessage(str, MessageGetRequest.nextForSubject(j, str2));
    }

    @Override // io.nats.client.JetStreamManagement
    public StreamInfo getStreamInfo(String str) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "Stream Name");
        return _getStreamInfo(str, null);
    }

    @Override // io.nats.client.JetStreamManagement
    public List<String> getStreamNames() throws IOException, JetStreamApiException {
        return _getStreamNames(null);
    }

    @Override // io.nats.client.JetStreamManagement
    public List<StreamInfo> getStreams() throws IOException, JetStreamApiException {
        return getStreams(null);
    }

    @Override // io.nats.client.JetStreamManagement
    public JetStream jetStream() {
        if (this.js == null) {
            this.js = new NatsJetStream(this);
        }
        return this.js;
    }

    @Override // io.nats.client.JetStreamManagement
    public ConsumerPauseResponse pauseConsumer(String str, String str2, ZonedDateTime zonedDateTime) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "Stream Name");
        Validator.validateNotNull(str2, "Consumer Name");
        return new ConsumerPauseResponse(makeRequestResponseRequired("CONSUMER.PAUSE." + str + NatsConstants.DOT + str2, new ConsumerPauseRequest(zonedDateTime).serialize(), getTimeout())).throwOnHasError();
    }

    @Override // io.nats.client.JetStreamManagement
    public PurgeResponse purgeStream(String str) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "Stream Name");
        return new PurgeResponse(makeRequestResponseRequired("STREAM.PURGE." + str, null, getTimeout())).throwOnHasError();
    }

    @Override // io.nats.client.JetStreamManagement
    public boolean resumeConsumer(String str, String str2) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "Stream Name");
        Validator.validateNotNull(str2, "Consumer Name");
        return !new ConsumerPauseResponse(makeRequestResponseRequired("CONSUMER.PAUSE." + str + NatsConstants.DOT + str2, null, getTimeout())).throwOnHasError().isPaused();
    }

    @Override // io.nats.client.JetStreamManagement
    public ConsumerInfo updateConsumer(String str, ConsumerConfiguration consumerConfiguration) throws IOException, JetStreamApiException {
        Validator.validateStreamName(str, true);
        Validator.validateNotNull(consumerConfiguration, "Config");
        return _createConsumer(str, consumerConfiguration, ConsumerCreateRequest.Action.Update);
    }

    @Override // io.nats.client.JetStreamManagement
    public StreamInfo updateStream(StreamConfiguration streamConfiguration) throws IOException, JetStreamApiException {
        return addOrUpdateStream(streamConfiguration, NatsJetStreamConstants.JSAPI_STREAM_UPDATE);
    }

    private List<String> getConsumerNames(String str, String str2) throws IOException, JetStreamApiException {
        String strV = zn.v("CONSUMER.NAMES.", str);
        ConsumerNamesReader consumerNamesReader = new ConsumerNamesReader();
        while (consumerNamesReader.hasMore()) {
            consumerNamesReader.process(makeRequestResponseRequired(strV, consumerNamesReader.nextJson(str2), getTimeout()));
        }
        return consumerNamesReader.getStrings();
    }

    @Override // io.nats.client.JetStreamManagement
    public boolean deleteMessage(String str, long j, boolean z) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "Stream Name");
        return new SuccessApiResponse(makeRequestResponseRequired("STREAM.MSG.DELETE." + str, new MessageDeleteRequest(j, z).serialize(), getTimeout())).throwOnHasError().getSuccess();
    }

    @Override // io.nats.client.JetStreamManagement
    public MessageInfo getFirstMessage(String str, ZonedDateTime zonedDateTime) throws IOException, JetStreamApiException {
        return _getMessage(str, MessageGetRequest.firstForStartTime(zonedDateTime));
    }

    @Override // io.nats.client.JetStreamManagement
    public MessageInfo getMessage(String str, MessageGetRequest messageGetRequest) throws IOException, JetStreamApiException {
        return _getMessage(str, messageGetRequest);
    }

    @Override // io.nats.client.JetStreamManagement
    public List<String> getStreamNames(String str) throws IOException, JetStreamApiException {
        return _getStreamNames(str);
    }

    @Override // io.nats.client.JetStreamManagement
    public List<StreamInfo> getStreams(String str) throws IOException, JetStreamApiException {
        StreamListReader streamListReader = new StreamListReader();
        while (streamListReader.hasMore()) {
            streamListReader.process(makeRequestResponseRequired(NatsJetStreamConstants.JSAPI_STREAM_LIST, streamListReader.nextJson(str), getTimeout()));
        }
        return cacheStreamInfo(streamListReader.getStreams());
    }

    @Override // io.nats.client.JetStreamManagement
    public MessageInfo getFirstMessage(String str, ZonedDateTime zonedDateTime, String str2) throws IOException, JetStreamApiException {
        return _getMessage(str, MessageGetRequest.firstForStartTimeAndSubject(zonedDateTime, str2));
    }

    @Override // io.nats.client.JetStreamManagement
    public StreamInfo getStreamInfo(String str, StreamInfoOptions streamInfoOptions) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "Stream Name");
        return _getStreamInfo(str, streamInfoOptions);
    }

    @Override // io.nats.client.JetStreamManagement
    public PurgeResponse purgeStream(String str, PurgeOptions purgeOptions) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "Stream Name");
        Validator.validateNotNull(purgeOptions, "Purge Options");
        return new PurgeResponse(makeRequestResponseRequired("STREAM.PURGE." + str, purgeOptions.toJson().getBytes(StandardCharsets.UTF_8), getTimeout())).throwOnHasError();
    }
}
