package io.nats.client.impl;

import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamSubscription;
import io.nats.client.MessageHandler;
import io.nats.client.PushSubscribeOptions;
import io.nats.client.api.AckPolicy;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.DeliverPolicy;
import io.nats.client.api.Watcher;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NatsWatchSubscription<T> implements AutoCloseable {
    private NatsDispatcher dispatcher;
    private final JetStream js;
    private JetStreamSubscription sub;

    public static abstract class WatchMessageHandler<T> implements MessageHandler {
        boolean endOfDataSent;
        private final Watcher<T> watcher;

        public WatchMessageHandler(Watcher<T> watcher) {
            this.watcher = watcher;
        }

        public void sendEndOfData() {
            this.endOfDataSent = true;
            this.watcher.endOfData();
        }
    }

    public NatsWatchSubscription(JetStream jetStream) {
        this.js = jetStream;
    }

    @Override // java.lang.AutoCloseable
    public void close() throws Exception {
        unsubscribe();
    }

    public void finishInit(NatsFeatureBase natsFeatureBase, List<String> list, DeliverPolicy deliverPolicy, boolean z, long j, WatchMessageHandler<T> watchMessageHandler, String str) throws IOException, JetStreamApiException {
        if (j > 0) {
            deliverPolicy = DeliverPolicy.ByStartSequence;
        } else {
            if (deliverPolicy == DeliverPolicy.New) {
                watchMessageHandler.sendEndOfData();
            }
            j = 0;
        }
        PushSubscribeOptions pushSubscribeOptionsBuild = PushSubscribeOptions.builder().stream(natsFeatureBase.getStreamName()).ordered(true).configuration(ConsumerConfiguration.builder().name(str).ackPolicy(AckPolicy.None).deliverPolicy(deliverPolicy).startSequence(j).headersOnly(Boolean.valueOf(z)).filterSubjects(list).build()).build();
        NatsDispatcher natsDispatcher = (NatsDispatcher) ((NatsJetStream) this.js).conn.createDispatcher();
        this.dispatcher = natsDispatcher;
        JetStreamSubscription jetStreamSubscriptionSubscribe = this.js.subscribe(null, natsDispatcher, watchMessageHandler, false, pushSubscribeOptionsBuild);
        this.sub = jetStreamSubscriptionSubscribe;
        if (watchMessageHandler.endOfDataSent || jetStreamSubscriptionSubscribe.getConsumerInfo().getCalculatedPending() != 0) {
            return;
        }
        watchMessageHandler.sendEndOfData();
    }

    public void unsubscribe() {
        NatsDispatcher natsDispatcher = this.dispatcher;
        if (natsDispatcher != null) {
            natsDispatcher.unsubscribe(this.sub);
            if (this.dispatcher.getSubscriptionHandlers().size() == 0) {
                NatsDispatcher natsDispatcher2 = this.dispatcher;
                natsDispatcher2.connection.closeDispatcher(natsDispatcher2);
                this.dispatcher = null;
            }
        }
    }
}
