package io.nats.client.impl;

import io.nats.client.FeatureOptions;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.PushSubscribeOptions;
import io.nats.client.api.AckPolicy;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.DeliverPolicy;
import io.nats.client.api.MessageInfo;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NatsFeatureBase {
    protected final NatsJetStream js;
    protected final NatsJetStreamManagement jsm;
    protected String streamName;

    public NatsFeatureBase(NatsConnection natsConnection, FeatureOptions featureOptions) throws IOException {
        if (featureOptions == null) {
            this.js = new NatsJetStream(natsConnection, null);
            this.jsm = new NatsJetStreamManagement(natsConnection, null);
        } else {
            this.js = new NatsJetStream(natsConnection, featureOptions.getJetStreamOptions());
            this.jsm = new NatsJetStreamManagement(natsConnection, featureOptions.getJetStreamOptions());
        }
    }

    public MessageInfo _getBySeq(long j) throws IOException, JetStreamApiException {
        try {
            return this.jsm.getMessage(this.streamName, j);
        } catch (JetStreamApiException e) {
            if (e.getApiErrorCode() == 10037) {
                return null;
            }
            throw e;
        }
    }

    public MessageInfo _getLast(String str) throws IOException, JetStreamApiException {
        try {
            return this.jsm.getLastMessage(this.streamName, str);
        } catch (JetStreamApiException e) {
            if (e.getApiErrorCode() == 10037) {
                return null;
            }
            throw e;
        }
    }

    public String getStreamName() {
        return this.streamName;
    }

    public void visitSubject(String str, DeliverPolicy deliverPolicy, boolean z, boolean z2, MessageHandler messageHandler) throws InterruptedException, IOException, JetStreamApiException {
        visitSubject(Collections.singletonList(str), deliverPolicy, z, z2, messageHandler);
    }

    public void visitSubject(List<String> list, DeliverPolicy deliverPolicy, boolean z, boolean z2, MessageHandler messageHandler) throws InterruptedException, IOException, JetStreamApiException {
        PushSubscribeOptions pushSubscribeOptionsBuild = PushSubscribeOptions.builder().stream(this.streamName).ordered(z2).configuration(ConsumerConfiguration.builder().ackPolicy(AckPolicy.None).deliverPolicy(deliverPolicy).headersOnly(Boolean.valueOf(z)).filterSubjects(list).build()).build();
        Duration timeout = this.js.getTimeout();
        JetStreamSubscription jetStreamSubscriptionSubscribe = this.js.subscribe((String) null, pushSubscribeOptionsBuild);
        try {
            long calculatedPending = jetStreamSubscriptionSubscribe.getConsumerInfo().getCalculatedPending();
            while (calculatedPending > 0) {
                Message messageNextMessage = jetStreamSubscriptionSubscribe.nextMessage(timeout);
                if (messageNextMessage == null) {
                    return;
                }
                messageHandler.onMessage(messageNextMessage);
                calculatedPending--;
                if (calculatedPending == 0) {
                    return;
                }
            }
        } finally {
            jetStreamSubscriptionSubscribe.unsubscribe();
        }
    }
}
