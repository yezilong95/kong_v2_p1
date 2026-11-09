package io.nats.client.impl;

import io.nats.client.FetchConsumeOptions;
import io.nats.client.FetchConsumer;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamStatusCheckedException;
import io.nats.client.JetStreamStatusException;
import io.nats.client.Message;
import io.nats.client.NatsSystemClock;
import io.nats.client.PullRequestOptions;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.support.NatsConstants;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class NatsFetchConsumer extends NatsMessageConsumerBase implements FetchConsumer, PullManagerObserver {
    private final boolean isNoWaitNoExpires;
    private final long maxWaitNanos;
    private final String pullSubject;
    private long startNanos;

    public NatsFetchConsumer(SimplifiedSubscriptionMaker simplifiedSubscriptionMaker, ConsumerInfo consumerInfo, FetchConsumeOptions fetchConsumeOptions) throws IOException, JetStreamApiException {
        long j;
        super(consumerInfo);
        boolean zIsNoWait = fetchConsumeOptions.isNoWait();
        long expiresInMillis = fetchConsumeOptions.getExpiresInMillis();
        this.isNoWaitNoExpires = zIsNoWait && expiresInMillis == -1;
        if (expiresInMillis == -1) {
            this.maxWaitNanos = 1000000000L;
            j = 1000;
        } else {
            this.maxWaitNanos = NatsConstants.NANOS_PER_MILLI * expiresInMillis;
            j = (110 * expiresInMillis) / 100;
        }
        PullRequestOptions pullRequestOptionsBuild = PullRequestOptions.builder(fetchConsumeOptions.getMaxMessages()).maxBytes(fetchConsumeOptions.getMaxBytes()).expiresIn(expiresInMillis).idleHeartbeat(fetchConsumeOptions.getIdleHeartbeat()).noWait(zIsNoWait).group(fetchConsumeOptions.getGroup()).minPending(fetchConsumeOptions.getMinPending()).minAckPending(fetchConsumeOptions.getMinAckPending()).build();
        initSub(simplifiedSubscriptionMaker.subscribe(null, null, null, Long.valueOf(j)));
        this.pullSubject = this.sub._pull(pullRequestOptionsBuild, fetchConsumeOptions.raiseStatusWarnings(), this);
        this.startNanos = -1L;
    }

    @Override // io.nats.client.impl.PullManagerObserver
    public void heartbeatError() {
        fullClose();
    }

    @Override // io.nats.client.FetchConsumer
    public Message nextMessage() throws JetStreamStatusCheckedException, InterruptedException {
        try {
            if (this.finished.get()) {
                return null;
            }
            if (this.pmm.noMorePending()) {
                Message message_nextUnmanagedNoWait = this.sub._nextUnmanagedNoWait(this.pullSubject);
                if (message_nextUnmanagedNoWait != null) {
                    return message_nextUnmanagedNoWait;
                }
                fullClose();
                return message_nextUnmanagedNoWait;
            }
            if (this.startNanos == -1) {
                this.startNanos = NatsSystemClock.nanoTime();
            }
            long jNanoTime = this.maxWaitNanos - (NatsSystemClock.nanoTime() - this.startNanos);
            if (jNanoTime < NatsConstants.NANOS_PER_MILLI) {
                Message message_nextUnmanagedNoWait2 = this.sub._nextUnmanagedNoWait(this.pullSubject);
                if (message_nextUnmanagedNoWait2 == null) {
                    fullClose();
                }
                return message_nextUnmanagedNoWait2;
            }
            Message message_nextUnmanaged = this.sub._nextUnmanaged(jNanoTime, this.pullSubject);
            if (message_nextUnmanaged == null && this.isNoWaitNoExpires) {
                fullClose();
            }
            return message_nextUnmanaged;
        } catch (JetStreamStatusException e) {
            throw new JetStreamStatusCheckedException(e);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    @Override // io.nats.client.impl.PullManagerObserver
    public void pendingUpdated() {
    }
}
