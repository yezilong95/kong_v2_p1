package io.nats.client.impl;

import io.nats.client.ConsumeOptions;
import io.nats.client.IterableConsumer;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamStatusCheckedException;
import io.nats.client.JetStreamStatusException;
import io.nats.client.Message;
import io.nats.client.api.ConsumerInfo;
import java.io.IOException;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
class NatsIterableConsumer extends NatsMessageConsumer implements IterableConsumer {
    public NatsIterableConsumer(SimplifiedSubscriptionMaker simplifiedSubscriptionMaker, ConsumerInfo consumerInfo, ConsumeOptions consumeOptions) throws IOException, JetStreamApiException {
        super(simplifiedSubscriptionMaker, consumerInfo, consumeOptions, null, null);
    }

    @Override // io.nats.client.IterableConsumer
    public Message nextMessage(Duration duration) throws JetStreamStatusCheckedException, InterruptedException {
        try {
            Message messageNextMessage = this.sub.nextMessage(duration);
            if (messageNextMessage == null || !this.stopped.get() || !this.pmm.noMorePending()) {
                return messageNextMessage;
            }
            this.finished.set(true);
            return messageNextMessage;
        } catch (JetStreamStatusException e) {
            throw new JetStreamStatusCheckedException(e);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    @Override // io.nats.client.IterableConsumer
    public Message nextMessage(long j) throws JetStreamStatusCheckedException, InterruptedException {
        return nextMessage(Duration.ofMillis(j));
    }
}
