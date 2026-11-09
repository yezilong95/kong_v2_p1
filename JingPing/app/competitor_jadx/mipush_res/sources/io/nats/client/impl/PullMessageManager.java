package io.nats.client.impl;

import io.nats.client.Connection;
import io.nats.client.ErrorListener;
import io.nats.client.Message;
import io.nats.client.PullRequestOptions;
import io.nats.client.SubscribeOptions;
import io.nats.client.impl.MessageManager;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.Status;

/* JADX INFO: loaded from: classes.dex */
class PullMessageManager extends MessageManager {
    protected long pendingBytes;
    protected int pendingMessages;
    protected PullManagerObserver pullManagerObserver;
    protected boolean raiseStatusWarnings;
    protected boolean trackingBytes;

    public PullMessageManager(NatsConnection natsConnection, SubscribeOptions subscribeOptions, boolean z) {
        super(natsConnection, subscribeOptions, z);
        resetTracking();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$manageStatus$0(Status status, Connection connection, ErrorListener errorListener) {
        errorListener.pullStatusWarning(connection, this.sub, status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$manageStatus$1(Status status, Connection connection, ErrorListener errorListener) {
        errorListener.pullStatusWarning(connection, this.sub, status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$manageStatus$2(Status status, Connection connection, ErrorListener errorListener) {
        errorListener.pullStatusError(connection, this.sub, status);
    }

    private void trackIncoming(int i, long j) {
        this.stateChangeLock.lock();
        try {
            updateLastMessageReceived();
            if (i != Integer.MIN_VALUE) {
                int i2 = this.pendingMessages - i;
                this.pendingMessages = i2;
                boolean z = i2 < 1;
                if (this.trackingBytes) {
                    long j2 = this.pendingBytes - j;
                    this.pendingBytes = j2;
                    z |= j2 < 1;
                }
                if (z) {
                    resetTracking();
                }
                PullManagerObserver pullManagerObserver = this.pullManagerObserver;
                if (pullManagerObserver != null) {
                    pullManagerObserver.pendingUpdated();
                }
            }
            this.stateChangeLock.unlock();
        } catch (Throwable th) {
            this.stateChangeLock.unlock();
            throw th;
        }
    }

    @Override // io.nats.client.impl.MessageManager
    public Boolean beforeQueueProcessorImpl(NatsMessage natsMessage) {
        Status status = natsMessage.getStatus();
        if (status == null) {
            trackIncoming(1, natsMessage.consumeByteCount());
            return Boolean.TRUE;
        }
        if (status.isHeartbeat()) {
            updateLastMessageReceived();
            return Boolean.FALSE;
        }
        Headers headers = natsMessage.getHeaders();
        int i = Integer.MIN_VALUE;
        long j = Long.MIN_VALUE;
        if (headers != null) {
            try {
                int i2 = Integer.parseInt(headers.getFirst(NatsJetStreamConstants.NATS_PENDING_MESSAGES));
                j = Long.parseLong(headers.getFirst(NatsJetStreamConstants.NATS_PENDING_BYTES));
                i = i2;
            } catch (NumberFormatException unused) {
            }
        }
        trackIncoming(i, j);
        return Boolean.TRUE;
    }

    @Override // io.nats.client.impl.MessageManager
    public void handleHeartbeatError() {
        super.handleHeartbeatError();
        resetTracking();
        PullManagerObserver pullManagerObserver = this.pullManagerObserver;
        if (pullManagerObserver != null) {
            pullManagerObserver.heartbeatError();
        }
    }

    @Override // io.nats.client.impl.MessageManager
    public MessageManager.ManageResult manage(Message message) {
        if (message.getStatus() != null) {
            return manageStatus(message);
        }
        trackJsMessage(message);
        return MessageManager.ManageResult.MESSAGE;
    }

    public MessageManager.ManageResult manageStatus(Message message) {
        Status status = message.getStatus();
        int code = status.getCode();
        if (code == 404 || code == 503 || code == 408) {
            if (this.raiseStatusWarnings) {
                this.conn.executeCallback(new t(this, status, 0));
            }
            return MessageManager.ManageResult.STATUS_TERMINUS;
        }
        if (code == 409) {
            String message2 = status.getMessage();
            if (message2.startsWith(Status.EXCEEDED_MAX_PREFIX) || message2.equals(Status.SERVER_SHUTDOWN)) {
                if (this.raiseStatusWarnings) {
                    this.conn.executeCallback(new t(this, status, 1));
                }
                return MessageManager.ManageResult.STATUS_HANDLED;
            }
            if (message2.equals(Status.BATCH_COMPLETED) || message2.equals(Status.LEADERSHIP_CHANGE) || message2.equals(Status.MESSAGE_SIZE_EXCEEDS_MAX_BYTES)) {
                return MessageManager.ManageResult.STATUS_TERMINUS;
            }
        }
        this.conn.executeCallback(new t(this, status, 2));
        return MessageManager.ManageResult.STATUS_ERROR;
    }

    public boolean noMorePending() {
        return this.pendingMessages < 1 || (this.trackingBytes && this.pendingBytes < 1);
    }

    public void resetTracking() {
        this.pendingMessages = 0;
        this.pendingBytes = 0L;
        this.trackingBytes = false;
        updateLastMessageReceived();
    }

    @Override // io.nats.client.impl.MessageManager
    public void startPullRequest(String str, PullRequestOptions pullRequestOptions, boolean z, PullManagerObserver pullManagerObserver) {
        this.stateChangeLock.lock();
        try {
            this.raiseStatusWarnings = z;
            this.pullManagerObserver = pullManagerObserver;
            this.pendingMessages += pullRequestOptions.getBatchSize();
            long maxBytes = this.pendingBytes + pullRequestOptions.getMaxBytes();
            this.pendingBytes = maxBytes;
            this.trackingBytes = maxBytes > 0;
            configureIdleHeartbeat(pullRequestOptions.getIdleHeartbeat(), -1L);
            if (this.hb.get()) {
                initOrResetHeartbeatTimer();
            } else {
                shutdownHeartbeatTimer();
            }
            this.stateChangeLock.unlock();
        } catch (Throwable th) {
            this.stateChangeLock.unlock();
            throw th;
        }
    }

    @Override // io.nats.client.impl.MessageManager
    public void startup(NatsJetStreamSubscription natsJetStreamSubscription) {
        super.startup(natsJetStreamSubscription);
        natsJetStreamSubscription.setBeforeQueueProcessor(new u(this, 0));
    }
}
