package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.Consumer;
import io.nats.client.ConsumerContext;
import io.nats.client.Dispatcher;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamOptions;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.PublishOptions;
import io.nats.client.PullSubscribeOptions;
import io.nats.client.PushSubscribeOptions;
import io.nats.client.StreamContext;
import io.nats.client.SubscribeOptions;
import io.nats.client.api.AckPolicy;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.api.DeliverPolicy;
import io.nats.client.api.PriorityPolicy;
import io.nats.client.api.PublishAck;
import io.nats.client.api.ReplayPolicy;
import io.nats.client.impl.MessageManager;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.NatsJetStreamClientError;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.NatsJetStreamUtil;
import io.nats.client.support.NatsRequestCompletableFuture;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class NatsJetStream extends NatsJetStreamImpl implements JetStream {
    MessageManagerFactory _pullMessageManagerFactory;
    MessageManagerFactory _pullOrderedMessageManagerFactory;
    MessageManagerFactory _pushMessageManagerFactory;
    MessageManagerFactory _pushOrderedMessageManagerFactory;

    public static class AsyncMessageHandler implements MessageHandler {
        boolean autoAck;
        MessageManager manager;
        MessageHandler userHandler;

        public AsyncMessageHandler(MessageManager messageManager, MessageHandler messageHandler, boolean z, ConsumerConfiguration consumerConfiguration) {
            this.manager = messageManager;
            this.userHandler = messageHandler;
            this.autoAck = z && consumerConfiguration.getAckPolicy() != AckPolicy.None;
        }

        @Override // io.nats.client.MessageHandler
        public void onMessage(Message message) throws InterruptedException {
            if (this.manager.manage(message) == MessageManager.ManageResult.MESSAGE) {
                this.userHandler.onMessage(message);
                if (this.autoAck) {
                    message.ack();
                }
            }
        }
    }

    public static class ConsumerConfigurationComparer extends ConsumerConfiguration {
        public ConsumerConfigurationComparer(ConsumerConfiguration consumerConfiguration) {
            super(consumerConfiguration);
        }

        public List<String> getChanges(ConsumerConfiguration consumerConfiguration) {
            ConsumerConfigurationComparer consumerConfigurationComparer = new ConsumerConfigurationComparer(consumerConfiguration);
            ArrayList arrayList = new ArrayList();
            DeliverPolicy deliverPolicy = this.deliverPolicy;
            if (deliverPolicy != null && deliverPolicy != consumerConfigurationComparer.getDeliverPolicy()) {
                arrayList.add("deliverPolicy");
            }
            AckPolicy ackPolicy = this.ackPolicy;
            if (ackPolicy != null && ackPolicy != consumerConfigurationComparer.getAckPolicy()) {
                arrayList.add("ackPolicy");
            }
            ReplayPolicy replayPolicy = this.replayPolicy;
            if (replayPolicy != null && replayPolicy != consumerConfigurationComparer.getReplayPolicy()) {
                arrayList.add("replayPolicy");
            }
            Boolean bool = this.flowControl;
            if (bool != null && bool.booleanValue() != consumerConfigurationComparer.isFlowControl()) {
                arrayList.add("flowControl");
            }
            Boolean bool2 = this.headersOnly;
            if (bool2 != null && bool2.booleanValue() != consumerConfigurationComparer.isHeadersOnly()) {
                arrayList.add("headersOnly");
            }
            Boolean bool3 = this.memStorage;
            if (bool3 != null && bool3.booleanValue() != consumerConfigurationComparer.isMemStorage()) {
                arrayList.add("memStorage");
            }
            Long l = this.startSeq;
            if (l != null && !l.equals(Long.valueOf(consumerConfigurationComparer.getStartSequence()))) {
                arrayList.add("startSequence");
            }
            Long l2 = this.rateLimit;
            if (l2 != null && !l2.equals(Long.valueOf(consumerConfigurationComparer.getRateLimit()))) {
                arrayList.add("rateLimit");
            }
            if (this.maxDeliver != null && r1.intValue() != consumerConfigurationComparer.getMaxDeliver()) {
                arrayList.add("maxDeliver");
            }
            if (this.maxAckPending != null && r1.intValue() != consumerConfigurationComparer.getMaxAckPending()) {
                arrayList.add("maxAckPending");
            }
            if (this.maxPullWaiting != null && r1.intValue() != consumerConfigurationComparer.getMaxPullWaiting()) {
                arrayList.add("maxPullWaiting");
            }
            if (this.maxBatch != null && r1.intValue() != consumerConfigurationComparer.getMaxBatch()) {
                arrayList.add("maxBatch");
            }
            if (this.maxBytes != null && r1.intValue() != consumerConfigurationComparer.getMaxBytes()) {
                arrayList.add("maxBytes");
            }
            Integer num = this.numReplicas;
            if (num != null && !num.equals(consumerConfigurationComparer.numReplicas)) {
                arrayList.add("numReplicas");
            }
            ZonedDateTime zonedDateTime = this.pauseUntil;
            if (zonedDateTime != null && !zonedDateTime.equals(consumerConfigurationComparer.pauseUntil)) {
                arrayList.add("pauseUntil");
            }
            Duration duration = this.ackWait;
            if (duration != null && !duration.equals(ConsumerConfiguration.getOrUnset(consumerConfigurationComparer.ackWait))) {
                arrayList.add("ackWait");
            }
            Duration duration2 = this.idleHeartbeat;
            if (duration2 != null && !duration2.equals(ConsumerConfiguration.getOrUnset(consumerConfigurationComparer.idleHeartbeat))) {
                arrayList.add("idleHeartbeat");
            }
            Duration duration3 = this.maxExpires;
            if (duration3 != null && !duration3.equals(ConsumerConfiguration.getOrUnset(consumerConfigurationComparer.maxExpires))) {
                arrayList.add("maxExpires");
            }
            Duration duration4 = this.inactiveThreshold;
            if (duration4 != null && !duration4.equals(ConsumerConfiguration.getOrUnset(consumerConfigurationComparer.inactiveThreshold))) {
                arrayList.add("inactiveThreshold");
            }
            ZonedDateTime zonedDateTime2 = this.startTime;
            if (zonedDateTime2 != null && !zonedDateTime2.equals(consumerConfigurationComparer.startTime)) {
                arrayList.add("startTime");
            }
            String str = this.description;
            if (str != null && !str.equals(consumerConfigurationComparer.description)) {
                arrayList.add(ApiConstants.DESCRIPTION);
            }
            String str2 = this.sampleFrequency;
            if (str2 != null && !str2.equals(consumerConfigurationComparer.sampleFrequency)) {
                arrayList.add("sampleFrequency");
            }
            String str3 = this.deliverSubject;
            if (str3 != null && !str3.equals(consumerConfigurationComparer.deliverSubject)) {
                arrayList.add("deliverSubject");
            }
            String str4 = this.deliverGroup;
            if (str4 != null && !str4.equals(consumerConfigurationComparer.deliverGroup)) {
                arrayList.add("deliverGroup");
            }
            List<Duration> list = this.backoff;
            if (list != null && !Validator.listsAreEquivalent(list, consumerConfigurationComparer.backoff)) {
                arrayList.add(ApiConstants.BACKOFF);
            }
            Map<String, String> map = this.metadata;
            if (map != null && !Validator.mapsAreEquivalent(map, consumerConfigurationComparer.metadata)) {
                arrayList.add(ApiConstants.METADATA);
            }
            List<String> list2 = this.filterSubjects;
            if (list2 != null && !Validator.listsAreEquivalent(list2, consumerConfigurationComparer.filterSubjects)) {
                arrayList.add("filterSubjects");
            }
            List<String> list3 = this.priorityGroups;
            if (list3 != null && !Validator.listsAreEquivalent(list3, consumerConfigurationComparer.priorityGroups)) {
                arrayList.add("priorityGroups");
            }
            PriorityPolicy priorityPolicy = this.priorityPolicy;
            if (priorityPolicy != null && priorityPolicy != consumerConfigurationComparer.getPriorityPolicy()) {
                arrayList.add("priorityPolicy");
            }
            return arrayList;
        }
    }

    public interface MessageManagerFactory {
        MessageManager createMessageManager(NatsConnection natsConnection, NatsJetStream natsJetStream, String str, SubscribeOptions subscribeOptions, ConsumerConfiguration consumerConfiguration, boolean z, boolean z2);
    }

    public NatsJetStream(NatsConnection natsConnection, JetStreamOptions jetStreamOptions) throws IOException {
        super(natsConnection, jetStreamOptions);
        this._pushMessageManagerFactory = new p(0);
        this._pushOrderedMessageManagerFactory = new p(1);
        this._pullMessageManagerFactory = new p(2);
        this._pullOrderedMessageManagerFactory = new p(3);
    }

    private Headers _mergeNum(Headers headers, String str, String str2) {
        if (headers == null) {
            headers = new Headers();
        }
        return headers.add(str, str2);
    }

    private NatsStreamContext getNatsStreamContext(String str) throws IOException, JetStreamApiException {
        return new NatsStreamContext(str, this, this.conn, this.jso);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ NatsSubscription lambda$createSubscription$3(String str, String str2, MessageManager messageManager, String str3, String str4, String str5, NatsConnection natsConnection, NatsDispatcher natsDispatcher) {
        return new NatsJetStreamPullSubscription(str3, str4, natsConnection, natsDispatcher, this, str, str2, messageManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ NatsSubscription lambda$createSubscription$4(String str, String str2, MessageManager messageManager, SubscribeOptions subscribeOptions, String str3, String str4, String str5, NatsConnection natsConnection, NatsDispatcher natsDispatcher) {
        NatsJetStreamSubscription natsJetStreamSubscription = new NatsJetStreamSubscription(str3, str4, str5, natsConnection, natsDispatcher, this, str, str2, messageManager);
        if (natsDispatcher == null) {
            natsJetStreamSubscription.setPendingLimits(subscribeOptions.getPendingMessageLimit(), subscribeOptions.getPendingByteLimit());
        }
        return natsJetStreamSubscription;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ MessageManager lambda$new$1(NatsConnection natsConnection, NatsJetStream natsJetStream, String str, SubscribeOptions subscribeOptions, ConsumerConfiguration consumerConfiguration, boolean z, boolean z2) {
        return new PullMessageManager(natsConnection, subscribeOptions, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ MessageManager lambda$new$2(NatsConnection natsConnection, NatsJetStream natsJetStream, String str, SubscribeOptions subscribeOptions, ConsumerConfiguration consumerConfiguration, boolean z, boolean z2) {
        return new PullOrderedMessageManager(natsConnection, natsJetStream, str, subscribeOptions, consumerConfiguration, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletionStage lambda$publishAsyncInternal$0(PublishOptions publishOptions, Message message) {
        try {
            responseRequired(message);
            return CompletableFuture.completedFuture(processPublishResponse(message, publishOptions));
        } catch (JetStreamApiException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String lookupStreamSubject(String str) throws IOException, JetStreamApiException {
        List<String> subjects = _getStreamInfo(str, null).getConfiguration().getSubjects();
        if (subjects.size() == 1) {
            return subjects.get(0);
        }
        return null;
    }

    private Headers mergeNum(Headers headers, String str, long j) {
        return j > -1 ? _mergeNum(headers, str, Long.toString(j)) : headers;
    }

    private Headers mergePublishOptions(Headers headers, PublishOptions publishOptions) {
        Headers headers2 = headers == null ? null : new Headers(headers);
        return publishOptions != null ? mergeString(mergeString(mergeString(mergeString(mergeNum(mergeNum(headers2, NatsJetStreamConstants.EXPECTED_LAST_SEQ_HDR, publishOptions.getExpectedLastSequence()), NatsJetStreamConstants.EXPECTED_LAST_SUB_SEQ_HDR, publishOptions.getExpectedLastSubjectSequence()), NatsJetStreamConstants.EXPECTED_LAST_MSG_ID_HDR, publishOptions.getExpectedLastMsgId()), NatsJetStreamConstants.EXPECTED_STREAM_HDR, publishOptions.getExpectedStream()), NatsJetStreamConstants.MSG_ID_HDR, publishOptions.getMessageId()), NatsJetStreamConstants.MSG_TTL_HDR, publishOptions.getMessageTtl()) : headers2;
    }

    private Headers mergeString(Headers headers, String str, String str2) {
        return Validator.nullOrEmpty(str2) ? headers : _mergeNum(headers, str, str2);
    }

    private PublishAck processPublishResponse(Message message, PublishOptions publishOptions) throws IOException, JetStreamApiException {
        if (message.isStatusMessage()) {
            throw new IOException("Error Publishing: " + message.getStatus().getMessageWithCode());
        }
        PublishAck publishAck = new PublishAck(message);
        String stream = publishAck.getStream();
        String stream2 = publishOptions == null ? null : publishOptions.getStream();
        if (stream2 == null || stream2.equals(stream)) {
            return publishAck;
        }
        throw new IOException("Expected ack from stream " + stream2 + ", received from: " + stream);
    }

    private CompletableFuture<PublishAck> publishAsyncInternal(String str, Headers headers, byte[] bArr, final PublishOptions publishOptions, boolean z) {
        Headers headersMergePublishOptions = mergePublishOptions(headers, publishOptions);
        if (this.jso.isPublishNoAck()) {
            this.conn.publishInternal(str, null, headersMergePublishOptions, bArr, z, false);
            return null;
        }
        NatsConnection natsConnection = this.conn;
        return natsConnection.requestFutureInternal(str, headersMergePublishOptions, bArr, null, NatsRequestCompletableFuture.CancelAction.COMPLETE, z, natsConnection.forceFlushOnRequest).thenCompose(new Function() { // from class: kg0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.a.lambda$publishAsyncInternal$0(publishOptions, (Message) obj);
            }
        });
    }

    private PublishAck publishSyncInternal(String str, Headers headers, byte[] bArr, PublishOptions publishOptions, boolean z) throws IOException, JetStreamApiException {
        Headers headersMergePublishOptions = mergePublishOptions(headers, publishOptions);
        if (!this.jso.isPublishNoAck()) {
            return processPublishResponse(makeInternalRequestResponseRequired(str, headersMergePublishOptions, bArr, getTimeout(), NatsRequestCompletableFuture.CancelAction.COMPLETE, z, this.conn.forceFlushOnRequest), publishOptions);
        }
        this.conn.publishInternal(str, null, headersMergePublishOptions, bArr, z, false);
        return null;
    }

    public JetStreamSubscription createSubscription(String str, PushSubscribeOptions pushSubscribeOptions, PullSubscribeOptions pullSubscribeOptions, String str2, NatsDispatcher natsDispatcher, MessageHandler messageHandler, boolean z, PullMessageManager pullMessageManager) throws IOException, JetStreamApiException {
        SubscribeOptions subscribeOptions;
        String stream;
        ConsumerConfiguration consumerConfiguration;
        String str3;
        ConsumerConfiguration consumerConfigurationBuild;
        List<String> list;
        MessageManagerFactory messageManagerFactory;
        NatsConnection natsConnection;
        boolean z2;
        ConsumerConfiguration consumerConfiguration2;
        String str4;
        final String str5;
        NatsSubscriptionFactory natsSubscriptionFactory;
        final MessageManager messageManagerCreateMessageManager;
        ConsumerConfiguration consumerConfiguration3;
        List<String> filterSubjects;
        final NatsJetStream natsJetStream = this;
        boolean z3 = pullSubscribeOptions != null;
        if (z3) {
            String stream2 = pullSubscribeOptions.getStream();
            ConsumerConfiguration consumerConfiguration4 = pullSubscribeOptions.getConsumerConfiguration();
            Validator.validateNotSupplied(consumerConfiguration4.getDeliverGroup(), NatsJetStreamClientError.JsSubPullCantHaveDeliverGroup);
            Validator.validateNotSupplied(consumerConfiguration4.getDeliverSubject(), NatsJetStreamClientError.JsSubPullCantHaveDeliverSubject);
            consumerConfiguration = consumerConfiguration4;
            str3 = null;
            stream = stream2;
            subscribeOptions = pullSubscribeOptions;
        } else {
            subscribeOptions = pushSubscribeOptions == null ? PushSubscribeOptions.DEFAULT_PUSH_OPTS : pushSubscribeOptions;
            stream = subscribeOptions.getStream();
            consumerConfiguration = subscribeOptions.getConsumerConfiguration();
            if (consumerConfiguration.maxPullWaitingWasSet()) {
                throw NatsJetStreamClientError.JsSubPushCantHaveMaxPullWaiting.instance();
            }
            if (consumerConfiguration.maxBatchWasSet()) {
                throw NatsJetStreamClientError.JsSubPushCantHaveMaxBatch.instance();
            }
            if (consumerConfiguration.maxBytesWasSet()) {
                throw NatsJetStreamClientError.JsSubPushCantHaveMaxBytes.instance();
            }
            String strValidateMustMatchIfBothSupplied = Validator.validateMustMatchIfBothSupplied(consumerConfiguration.getDeliverGroup(), str2, NatsJetStreamClientError.JsSubQueueDeliverGroupMismatch);
            if (subscribeOptions.isOrdered() && strValidateMustMatchIfBothSupplied != null) {
                throw NatsJetStreamClientError.JsSubOrderedNotAllowOnQueues.instance();
            }
            if (natsDispatcher != null && (subscribeOptions.getPendingMessageLimit() != Consumer.DEFAULT_MAX_MESSAGES || subscribeOptions.getPendingByteLimit() != Consumer.DEFAULT_MAX_BYTES)) {
                throw NatsJetStreamClientError.JsSubPushAsyncCantSetPending.instance();
            }
            str3 = strValidateMustMatchIfBothSupplied;
        }
        if (consumerConfiguration.getIdleHeartbeat() != null && consumerConfiguration.getIdleHeartbeat().toMillis() > 0) {
            if (z3) {
                throw NatsJetStreamClientError.JsSubFcHbNotValidPull.instance();
            }
            if (str3 != null) {
                throw NatsJetStreamClientError.JsSubFcHbNotValidQueue.instance();
            }
        }
        String strEmptyAsNull = Validator.emptyAsNull(str);
        ArrayList arrayList = new ArrayList();
        if (consumerConfiguration.getFilterSubjects() != null) {
            arrayList.addAll(consumerConfiguration.getFilterSubjects());
            if (strEmptyAsNull != null && !arrayList.contains(strEmptyAsNull)) {
                throw NatsJetStreamClientError.JsSubSubjectDoesNotMatchFilter.instance();
            }
        } else if (strEmptyAsNull != null) {
            arrayList.add(strEmptyAsNull);
        }
        if (stream == null) {
            if (arrayList.isEmpty()) {
                throw NatsJetStreamClientError.JsSubSubjectNeededToLookupStream.instance();
            }
            stream = natsJetStream.lookupStreamBySubject((String) arrayList.get(0));
            if (stream == null) {
                throw NatsJetStreamClientError.JsSubNoMatchingStreamForSubject.instance();
            }
        }
        String durable = consumerConfiguration.getDurable();
        if (durable == null) {
            durable = consumerConfiguration.getName();
        }
        String deliverSubject = consumerConfiguration.getDeliverSubject();
        if (subscribeOptions.isFastBind() || subscribeOptions.isOrdered() || durable == null) {
            consumerConfigurationBuild = null;
            list = arrayList;
        } else {
            ConsumerInfo consumerInfoLookupConsumerInfo = natsJetStream.lookupConsumerInfo(stream, durable);
            if (consumerInfoLookupConsumerInfo != null) {
                ConsumerConfiguration consumerConfiguration5 = consumerInfoLookupConsumerInfo.getConsumerConfiguration();
                List<String> changes = new ConsumerConfigurationComparer(consumerConfiguration).getChanges(consumerConfiguration5);
                if (!changes.isEmpty()) {
                    throw NatsJetStreamClientError.JsSubExistingConsumerCannotBeModified.instance("Changed fields: " + changes);
                }
                if (z3) {
                    if (!Validator.nullOrEmpty(consumerConfiguration5.getDeliverSubject())) {
                        throw NatsJetStreamClientError.JsSubConsumerAlreadyConfiguredAsPush.instance();
                    }
                } else if (Validator.nullOrEmpty(consumerConfiguration5.getDeliverSubject())) {
                    throw NatsJetStreamClientError.JsSubConsumerAlreadyConfiguredAsPull.instance();
                }
                if (consumerConfiguration5.getDeliverGroup() == null) {
                    if (str3 != null) {
                        throw NatsJetStreamClientError.JsSubExistingConsumerNotQueue.instance();
                    }
                    if (consumerInfoLookupConsumerInfo.isPushBound()) {
                        throw NatsJetStreamClientError.JsSubConsumerAlreadyBound.instance();
                    }
                } else {
                    if (str3 == null) {
                        throw NatsJetStreamClientError.JsSubExistingConsumerIsQueue.instance();
                    }
                    if (!consumerConfiguration5.getDeliverGroup().equals(str3)) {
                        throw NatsJetStreamClientError.JsSubExistingQueueDoesNotMatchRequestedQueue.instance();
                    }
                }
                if (arrayList.isEmpty()) {
                    filterSubjects = arrayList;
                    if (consumerConfiguration5.getFilterSubjects() != null) {
                        filterSubjects = consumerConfiguration5.getFilterSubjects();
                    }
                } else {
                    boolean zListsAreEquivalent = Validator.listsAreEquivalent(arrayList, consumerConfiguration5.getFilterSubjects());
                    filterSubjects = arrayList;
                    if (!zListsAreEquivalent) {
                        throw NatsJetStreamClientError.JsSubSubjectDoesNotMatchFilter.instance();
                    }
                }
                consumerConfigurationBuild = consumerConfiguration5;
                deliverSubject = consumerConfiguration5.getDeliverSubject();
                list = filterSubjects;
            } else {
                if (subscribeOptions.isBind()) {
                    throw NatsJetStreamClientError.JsSubConsumerNotFoundRequiredInBind.instance();
                }
                consumerConfigurationBuild = null;
                list = arrayList;
            }
        }
        if (z3) {
            deliverSubject = zn.o(new StringBuilder(), natsJetStream.conn.createInbox(), ".*");
        } else if (deliverSubject == null) {
            deliverSubject = natsJetStream.conn.createInbox();
        }
        if (subscribeOptions.isFastBind() || consumerConfigurationBuild != null) {
            durable = subscribeOptions.getName();
        } else {
            ConsumerConfiguration.Builder builder = ConsumerConfiguration.builder(consumerConfiguration);
            if (!z3) {
                builder.deliverSubject(deliverSubject);
            }
            builder.filterSubjects(list);
            builder.deliverGroup(str3);
            if (!subscribeOptions.isOrdered()) {
                durable = null;
            } else if (durable != null) {
                durable = NatsJetStreamUtil.generateConsumerName(consumerConfiguration.getName());
                builder.name(durable);
            }
            consumerConfigurationBuild = builder.build();
        }
        final String str6 = durable;
        if (z3) {
            if (pullMessageManager == null) {
                str5 = stream;
                consumerConfiguration3 = consumerConfigurationBuild;
                messageManagerCreateMessageManager = (subscribeOptions.isOrdered() ? natsJetStream._pullOrderedMessageManagerFactory : natsJetStream._pullMessageManagerFactory).createMessageManager(natsJetStream.conn, natsJetStream, str5, subscribeOptions, consumerConfiguration3, false, natsDispatcher == null);
            } else {
                str5 = stream;
                consumerConfiguration3 = consumerConfigurationBuild;
                messageManagerCreateMessageManager = pullMessageManager;
            }
            natsSubscriptionFactory = new NatsSubscriptionFactory() { // from class: io.nats.client.impl.q
                @Override // io.nats.client.impl.NatsSubscriptionFactory
                public final NatsSubscription createNatsSubscription(String str7, String str8, String str9, NatsConnection natsConnection2, NatsDispatcher natsDispatcher2) {
                    return this.a.lambda$createSubscription$3(str5, str6, messageManagerCreateMessageManager, str7, str8, str9, natsConnection2, natsDispatcher2);
                }
            };
            consumerConfiguration2 = consumerConfiguration3;
            str4 = str6;
        } else {
            boolean z4 = false;
            final String str7 = stream;
            ConsumerConfiguration consumerConfiguration6 = consumerConfigurationBuild;
            MessageManagerFactory messageManagerFactory2 = subscribeOptions.isOrdered() ? natsJetStream._pushOrderedMessageManagerFactory : natsJetStream._pushMessageManagerFactory;
            NatsConnection natsConnection2 = natsJetStream.conn;
            if (str3 != null) {
                z4 = true;
            }
            if (natsDispatcher == null) {
                z2 = true;
                messageManagerFactory = messageManagerFactory2;
                natsConnection = natsConnection2;
            } else {
                messageManagerFactory = messageManagerFactory2;
                natsConnection = natsConnection2;
                z2 = false;
            }
            final MessageManager messageManagerCreateMessageManager2 = messageManagerFactory.createMessageManager(natsConnection, natsJetStream, str7, subscribeOptions, consumerConfiguration6, z4, z2);
            consumerConfiguration2 = consumerConfiguration6;
            final SubscribeOptions subscribeOptions2 = subscribeOptions;
            NatsSubscriptionFactory natsSubscriptionFactory2 = new NatsSubscriptionFactory() { // from class: io.nats.client.impl.r
                @Override // io.nats.client.impl.NatsSubscriptionFactory
                public final NatsSubscription createNatsSubscription(String str8, String str9, String str10, NatsConnection natsConnection3, NatsDispatcher natsDispatcher2) {
                    return this.a.lambda$createSubscription$4(str7, str6, messageManagerCreateMessageManager2, subscribeOptions2, str8, str9, str10, natsConnection3, natsDispatcher2);
                }
            };
            str4 = str6;
            str5 = str7;
            natsJetStream = this;
            subscribeOptions = subscribeOptions2;
            natsSubscriptionFactory = natsSubscriptionFactory2;
            messageManagerCreateMessageManager = messageManagerCreateMessageManager2;
        }
        NatsJetStreamSubscription natsJetStreamSubscription = natsDispatcher == null ? (NatsJetStreamSubscription) natsJetStream.conn.createSubscription(deliverSubject, str3, null, natsSubscriptionFactory) : (NatsJetStreamSubscription) natsDispatcher.subscribeImplJetStream(deliverSubject, str3, new AsyncMessageHandler(messageManagerCreateMessageManager, messageHandler, z, consumerConfiguration2), natsSubscriptionFactory);
        if (str4 == null || subscribeOptions.isOrdered()) {
            natsJetStream._createConsumerUnsubscribeOnException(str5, consumerConfiguration2, natsJetStreamSubscription);
            return natsJetStreamSubscription;
        }
        natsJetStreamSubscription.setConsumerName(str4);
        return natsJetStreamSubscription;
    }

    @Override // io.nats.client.JetStream
    public ConsumerContext getConsumerContext(String str, String str2) throws IOException, JetStreamApiException {
        Validator.validateStreamName(str, true);
        Validator.required(str2, "Consumer Name");
        return getNatsStreamContext(str).getConsumerContext(str2);
    }

    @Override // io.nats.client.JetStream
    public StreamContext getStreamContext(String str) throws IOException, JetStreamApiException {
        Validator.validateStreamName(str, true);
        return getNatsStreamContext(str);
    }

    @Override // io.nats.client.JetStream
    public PublishAck publish(String str, byte[] bArr) throws IOException, JetStreamApiException {
        return publishSyncInternal(str, null, bArr, null, true);
    }

    @Override // io.nats.client.JetStream
    public CompletableFuture<PublishAck> publishAsync(String str, byte[] bArr) {
        return publishAsyncInternal(str, null, bArr, null, true);
    }

    @Override // io.nats.client.JetStream
    public JetStreamSubscription subscribe(String str) throws IOException, JetStreamApiException {
        return createSubscription(Validator.validateSubject(str, true), null, null, null, null, null, false, null);
    }

    @Override // io.nats.client.JetStream
    public PublishAck publish(String str, Headers headers, byte[] bArr) throws IOException, JetStreamApiException {
        return publishSyncInternal(str, headers, bArr, null, true);
    }

    @Override // io.nats.client.JetStream
    public CompletableFuture<PublishAck> publishAsync(String str, Headers headers, byte[] bArr) {
        return publishAsyncInternal(str, headers, bArr, null, true);
    }

    @Override // io.nats.client.JetStream
    public PublishAck publish(String str, byte[] bArr, PublishOptions publishOptions) throws IOException, JetStreamApiException {
        return publishSyncInternal(str, null, bArr, publishOptions, true);
    }

    @Override // io.nats.client.JetStream
    public CompletableFuture<PublishAck> publishAsync(String str, byte[] bArr, PublishOptions publishOptions) {
        return publishAsyncInternal(str, null, bArr, publishOptions, true);
    }

    @Override // io.nats.client.JetStream
    public JetStreamSubscription subscribe(String str, PushSubscribeOptions pushSubscribeOptions) throws IOException, JetStreamApiException {
        return createSubscription(Validator.validateSubject(str, false), pushSubscribeOptions, null, null, null, null, false, null);
    }

    @Override // io.nats.client.JetStream
    public PublishAck publish(String str, Headers headers, byte[] bArr, PublishOptions publishOptions) throws IOException, JetStreamApiException {
        return publishSyncInternal(str, headers, bArr, publishOptions, true);
    }

    @Override // io.nats.client.JetStream
    public CompletableFuture<PublishAck> publishAsync(String str, Headers headers, byte[] bArr, PublishOptions publishOptions) {
        return publishAsyncInternal(str, headers, bArr, publishOptions, true);
    }

    @Override // io.nats.client.JetStream
    public PublishAck publish(Message message) throws IOException, JetStreamApiException {
        Validator.validateNotNull(message, "Message");
        return publishSyncInternal(message.getSubject(), message.getHeaders(), message.getData(), null, false);
    }

    @Override // io.nats.client.JetStream
    public CompletableFuture<PublishAck> publishAsync(Message message) {
        Validator.validateNotNull(message, "Message");
        return publishAsyncInternal(message.getSubject(), message.getHeaders(), message.getData(), null, false);
    }

    @Override // io.nats.client.JetStream
    public JetStreamSubscription subscribe(String str, String str2, PushSubscribeOptions pushSubscribeOptions) throws IOException, JetStreamApiException {
        String strValidateSubject = Validator.validateSubject(str, false);
        Validator.validateQueueName(str2, false);
        return createSubscription(strValidateSubject, pushSubscribeOptions, null, str2, null, null, false, null);
    }

    public NatsJetStream(NatsJetStreamImpl natsJetStreamImpl) {
        super(natsJetStreamImpl);
        this._pushMessageManagerFactory = new p(0);
        this._pushOrderedMessageManagerFactory = new p(1);
        this._pullMessageManagerFactory = new p(2);
        this._pullOrderedMessageManagerFactory = new p(3);
    }

    @Override // io.nats.client.JetStream
    public PublishAck publish(Message message, PublishOptions publishOptions) throws IOException, JetStreamApiException {
        Validator.validateNotNull(message, "Message");
        return publishSyncInternal(message.getSubject(), message.getHeaders(), message.getData(), publishOptions, false);
    }

    @Override // io.nats.client.JetStream
    public CompletableFuture<PublishAck> publishAsync(Message message, PublishOptions publishOptions) {
        Validator.validateNotNull(message, "Message");
        return publishAsyncInternal(message.getSubject(), message.getHeaders(), message.getData(), publishOptions, false);
    }

    @Override // io.nats.client.JetStream
    public JetStreamSubscription subscribe(String str, Dispatcher dispatcher, MessageHandler messageHandler, boolean z) throws IOException, JetStreamApiException {
        String strValidateSubject = Validator.validateSubject(str, false);
        Validator.validateNotNull(dispatcher, "Dispatcher");
        Validator.validateNotNull(messageHandler, "Handler");
        return createSubscription(strValidateSubject, null, null, null, (NatsDispatcher) dispatcher, messageHandler, z, null);
    }

    @Override // io.nats.client.JetStream
    public JetStreamSubscription subscribe(String str, Dispatcher dispatcher, MessageHandler messageHandler, boolean z, PushSubscribeOptions pushSubscribeOptions) throws IOException, JetStreamApiException {
        String strValidateSubject = Validator.validateSubject(str, false);
        Validator.validateNotNull(dispatcher, "Dispatcher");
        Validator.validateNotNull(messageHandler, "Handler");
        return createSubscription(strValidateSubject, pushSubscribeOptions, null, null, (NatsDispatcher) dispatcher, messageHandler, z, null);
    }

    @Override // io.nats.client.JetStream
    public JetStreamSubscription subscribe(String str, String str2, Dispatcher dispatcher, MessageHandler messageHandler, boolean z, PushSubscribeOptions pushSubscribeOptions) throws IOException, JetStreamApiException {
        String strValidateSubject = Validator.validateSubject(str, false);
        Validator.validateQueueName(str2, false);
        Validator.validateNotNull(dispatcher, "Dispatcher");
        Validator.validateNotNull(messageHandler, "Handler");
        return createSubscription(strValidateSubject, pushSubscribeOptions, null, str2, (NatsDispatcher) dispatcher, messageHandler, z, null);
    }

    @Override // io.nats.client.JetStream
    public JetStreamSubscription subscribe(String str, PullSubscribeOptions pullSubscribeOptions) throws IOException, JetStreamApiException {
        String strValidateSubject = Validator.validateSubject(str, false);
        Validator.validateNotNull(pullSubscribeOptions, "Pull Subscribe Options");
        return createSubscription(strValidateSubject, null, pullSubscribeOptions, null, null, null, false, null);
    }

    @Override // io.nats.client.JetStream
    public JetStreamSubscription subscribe(String str, Dispatcher dispatcher, MessageHandler messageHandler, PullSubscribeOptions pullSubscribeOptions) throws IOException, JetStreamApiException {
        String strValidateSubject = Validator.validateSubject(str, false);
        Validator.validateNotNull(dispatcher, "Dispatcher");
        Validator.validateNotNull(messageHandler, "Handler");
        Validator.validateNotNull(pullSubscribeOptions, "Pull Subscribe Options");
        return createSubscription(strValidateSubject, null, pullSubscribeOptions, null, (NatsDispatcher) dispatcher, messageHandler, false, null);
    }
}
