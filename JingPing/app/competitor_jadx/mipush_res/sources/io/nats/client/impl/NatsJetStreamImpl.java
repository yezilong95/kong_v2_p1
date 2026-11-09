package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamOptions;
import io.nats.client.Message;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerCreateRequest;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.api.DeliverPolicy;
import io.nats.client.api.StreamInfo;
import io.nats.client.api.StreamInfoOptions;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsJetStreamClientError;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.NatsJetStreamUtil;
import io.nats.client.support.NatsRequestCompletableFuture;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
class NatsJetStreamImpl implements NatsJetStreamConstants {
    private static final ConcurrentHashMap<String, CachedStreamInfo> CACHED_STREAM_INFO_MAP = new ConcurrentHashMap<>();
    final NatsConnection conn;
    final boolean consumerCreate290Available;
    final boolean directBatchGet211Available;
    final JetStreamOptions jso;
    final boolean multipleSubjectFilter210Available;
    final Duration timeout;

    public static class CachedStreamInfo {
        public final boolean allowDirect;

        public CachedStreamInfo(StreamInfo streamInfo) {
            this.allowDirect = streamInfo.getConfiguration().getAllowDirect();
        }
    }

    public NatsJetStreamImpl(NatsConnection natsConnection, JetStreamOptions jetStreamOptions) {
        this.conn = natsConnection;
        Duration connectionTimeout = (jetStreamOptions == null || jetStreamOptions.getRequestTimeout() == null) ? natsConnection.getOptions().getConnectionTimeout() : jetStreamOptions.getRequestTimeout();
        this.timeout = connectionTimeout;
        JetStreamOptions jetStreamOptionsBuild = JetStreamOptions.builder(jetStreamOptions).requestTimeout(connectionTimeout).build();
        this.jso = jetStreamOptionsBuild;
        this.consumerCreate290Available = natsConnection.getInfo().isSameOrNewerThanVersion("2.9.0") && !jetStreamOptionsBuild.isOptOut290ConsumerCreate();
        this.multipleSubjectFilter210Available = natsConnection.getInfo().isNewerVersionThan("2.9.99");
        this.directBatchGet211Available = natsConnection.getInfo().isNewerVersionThan("2.10.99");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$cacheStreamInfo$0(StreamInfo streamInfo) {
        CACHED_STREAM_INFO_MAP.put(streamInfo.getConfiguration().getName(), new CachedStreamInfo(streamInfo));
    }

    public ConsumerInfo _createConsumer(String str, ConsumerConfiguration consumerConfiguration, ConsumerCreateRequest.Action action) throws IOException, JetStreamApiException {
        String strV;
        String name = consumerConfiguration.getName();
        if (name != null && !this.consumerCreate290Available) {
            throw NatsJetStreamClientError.JsConsumerCreate290NotAvailable.instance();
        }
        boolean zHasMultipleFilterSubjects = consumerConfiguration.hasMultipleFilterSubjects();
        if (zHasMultipleFilterSubjects && !this.multipleSubjectFilter210Available) {
            throw NatsJetStreamClientError.JsMultipleFilterSubjects210NotAvailable.instance();
        }
        String durable = consumerConfiguration.getDurable();
        if (this.consumerCreate290Available && !zHasMultipleFilterSubjects) {
            if (name == null) {
                name = durable == null ? NatsJetStreamUtil.generateConsumerName() : durable;
            }
            String filterSubject = consumerConfiguration.getFilterSubject();
            if (filterSubject == null || filterSubject.equals(NatsConstants.GREATER_THAN)) {
                strV = "CONSUMER.CREATE." + str + NatsConstants.DOT + name;
            } else {
                strV = "CONSUMER.CREATE." + str + NatsConstants.DOT + name + NatsConstants.DOT + filterSubject;
            }
        } else if (durable == null) {
            strV = zn.v("CONSUMER.CREATE.", str);
        } else {
            strV = "CONSUMER.DURABLE.CREATE." + str + NatsConstants.DOT + durable;
        }
        return new ConsumerInfo(makeRequestResponseRequired(strV, new ConsumerCreateRequest(str, consumerConfiguration, action).serialize(), getTimeout())).throwOnHasError();
    }

    public void _createConsumerUnsubscribeOnException(String str, ConsumerConfiguration consumerConfiguration, NatsJetStreamSubscription natsJetStreamSubscription) throws IOException, JetStreamApiException {
        try {
            natsJetStreamSubscription.setConsumerName(_createConsumer(str, consumerConfiguration, ConsumerCreateRequest.Action.CreateOrUpdate).getName());
        } catch (JetStreamApiException | IOException e) {
            if (natsJetStreamSubscription.getDispatcher() == null) {
                natsJetStreamSubscription.unsubscribe();
            } else {
                natsJetStreamSubscription.getDispatcher().unsubscribe(natsJetStreamSubscription);
            }
            throw e;
        }
    }

    public ConsumerInfo _getConsumerInfo(String str, String str2) throws IOException, JetStreamApiException {
        return new ConsumerInfo(makeRequestResponseRequired("CONSUMER.INFO." + str + NatsConstants.DOT + str2, null, getTimeout())).throwOnHasError();
    }

    public StreamInfo _getStreamInfo(String str, StreamInfoOptions streamInfoOptions) throws IOException, JetStreamApiException {
        String strV = zn.v("STREAM.INFO.", str);
        StreamInfoReader streamInfoReader = new StreamInfoReader();
        while (streamInfoReader.hasMore()) {
            streamInfoReader.process(makeRequestResponseRequired(strV, streamInfoReader.nextJson(streamInfoOptions), getTimeout()));
        }
        return cacheStreamInfo(str, streamInfoReader.getStreamInfo());
    }

    public List<String> _getStreamNames(String str) throws IOException, JetStreamApiException {
        StreamNamesReader streamNamesReader = new StreamNamesReader();
        while (streamNamesReader.hasMore()) {
            streamNamesReader.process(makeRequestResponseRequired(NatsJetStreamConstants.JSAPI_STREAM_NAMES, streamNamesReader.nextJson(str), getTimeout()));
        }
        return streamNamesReader.getStrings();
    }

    public StreamInfo cacheStreamInfo(String str, StreamInfo streamInfo) {
        CACHED_STREAM_INFO_MAP.put(str, new CachedStreamInfo(streamInfo));
        return streamInfo;
    }

    public ConsumerConfiguration.Builder consumerConfigurationForOrdered(ConsumerConfiguration consumerConfiguration, long j, String str, Long l) {
        ConsumerConfiguration.Builder builder = ConsumerConfiguration.builder(consumerConfiguration);
        if (str != null) {
            builder.deliverSubject(str);
        }
        if (j > 0) {
            builder.deliverPolicy(DeliverPolicy.ByStartSequence).startSequence(Math.max(1L, j + 1)).startTime(null);
        }
        if (l != null) {
            builder.inactiveThreshold(l.longValue());
        }
        return builder;
    }

    public StreamInfo createAndCacheStreamInfoThrowOnError(String str, Message message) throws JetStreamApiException {
        return cacheStreamInfo(str, new StreamInfo(message).throwOnHasError());
    }

    public CachedStreamInfo getCachedStreamInfo(String str) throws IOException, JetStreamApiException {
        ConcurrentHashMap<String, CachedStreamInfo> concurrentHashMap = CACHED_STREAM_INFO_MAP;
        CachedStreamInfo cachedStreamInfo = concurrentHashMap.get(str);
        if (cachedStreamInfo != null) {
            return cachedStreamInfo;
        }
        _getStreamInfo(str, null);
        return concurrentHashMap.get(str);
    }

    public Duration getTimeout() {
        return this.timeout;
    }

    public ConsumerInfo lookupConsumerInfo(String str, String str2) throws IOException, JetStreamApiException {
        try {
            return _getConsumerInfo(str, str2);
        } catch (JetStreamApiException e) {
            if (e.getApiErrorCode() == 10014) {
                return null;
            }
            if (e.getErrorCode() == 404 && e.getErrorDescription().contains("consumer")) {
                return null;
            }
            throw e;
        }
    }

    public String lookupStreamBySubject(String str) throws IOException, JetStreamApiException {
        List<String> list_getStreamNames = _getStreamNames(str);
        if (list_getStreamNames.size() == 1) {
            return list_getStreamNames.get(0);
        }
        return null;
    }

    public Message makeInternalRequestResponseRequired(String str, Headers headers, byte[] bArr, Duration duration, NatsRequestCompletableFuture.CancelAction cancelAction, boolean z, boolean z2) throws IOException {
        try {
            return responseRequired(this.conn.requestInternal(str, headers, bArr, duration, cancelAction, z, z2));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException(e);
        }
    }

    public Message makeRequestResponseRequired(String str, byte[] bArr, Duration duration) throws IOException {
        try {
            return responseRequired(this.conn.request(prependPrefix(str), bArr, duration));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException(e);
        }
    }

    public String prependPrefix(String str) {
        return this.jso.getPrefix() + str;
    }

    public Message responseRequired(Message message) throws IOException {
        if (message != null) {
            return message;
        }
        throw new IOException("Timeout or no response waiting for NATS JetStream server");
    }

    public List<StreamInfo> cacheStreamInfo(List<StreamInfo> list) {
        list.forEach(new h(1));
        return list;
    }

    public NatsJetStreamImpl(NatsJetStreamImpl natsJetStreamImpl) {
        this.conn = natsJetStreamImpl.conn;
        this.jso = natsJetStreamImpl.jso;
        this.timeout = natsJetStreamImpl.timeout;
        this.consumerCreate290Available = natsJetStreamImpl.consumerCreate290Available;
        this.multipleSubjectFilter210Available = natsJetStreamImpl.multipleSubjectFilter210Available;
        this.directBatchGet211Available = natsJetStreamImpl.directBatchGet211Available;
    }
}
