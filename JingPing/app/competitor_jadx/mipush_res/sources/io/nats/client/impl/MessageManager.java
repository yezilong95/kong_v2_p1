package io.nats.client.impl;

import io.nats.client.Connection;
import io.nats.client.ErrorListener;
import io.nats.client.Message;
import io.nats.client.NatsSystemClock;
import io.nats.client.PullRequestOptions;
import io.nats.client.SubscribeOptions;
import io.nats.client.impl.NatsConnection;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.ScheduledTask;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
abstract class MessageManager {
    protected static final int THRESHOLD = 3;
    protected final NatsConnection conn;
    protected final SubscribeOptions so;
    protected NatsJetStreamSubscription sub;
    protected final boolean syncMode;
    protected final ReentrantLock stateChangeLock = new ReentrantLock();
    protected long lastStreamSeq = 0;
    protected long lastConsumerSeq = 0;
    protected final AtomicBoolean hb = new AtomicBoolean(false);
    protected final AtomicLong idleHeartbeatSettingMillis = new AtomicLong();
    protected final AtomicLong alarmPeriodSettingNanos = new AtomicLong();
    protected AtomicLong lastMsgReceivedNanoTime = new AtomicLong(NatsSystemClock.nanoTime());
    protected final AtomicReference<ScheduledTask> heartbeatTaskRef = new AtomicReference<>();

    public enum ManageResult {
        MESSAGE,
        STATUS_HANDLED,
        STATUS_TERMINUS,
        STATUS_ERROR
    }

    public MessageManager(NatsConnection natsConnection, SubscribeOptions subscribeOptions, boolean z) {
        this.conn = natsConnection;
        this.so = subscribeOptions;
        this.syncMode = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleHeartbeatError$0(Connection connection, ErrorListener errorListener) {
        errorListener.heartbeatAlarm(connection, this.sub, this.lastStreamSeq, this.lastConsumerSeq);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initOrResetHeartbeatTimer$1() {
        if (NatsSystemClock.nanoTime() - this.lastMsgReceivedNanoTime.get() > this.alarmPeriodSettingNanos.get()) {
            handleHeartbeatError();
        }
    }

    public Boolean beforeQueueProcessorImpl(NatsMessage natsMessage) {
        return Boolean.TRUE;
    }

    public void configureIdleHeartbeat(Duration duration, long j) {
        long millis;
        this.stateChangeLock.lock();
        if (duration == null) {
            millis = 0;
        } else {
            try {
                millis = duration.toMillis();
            } catch (Throwable th) {
                this.stateChangeLock.unlock();
                throw th;
            }
        }
        this.idleHeartbeatSettingMillis.set(millis);
        if (millis <= 0) {
            this.alarmPeriodSettingNanos.set(0L);
            this.hb.set(false);
        } else {
            if (j < millis) {
                j = millis * 3;
            }
            this.alarmPeriodSettingNanos.set(j * NatsConstants.NANOS_PER_MILLI);
            this.hb.set(true);
        }
        this.stateChangeLock.unlock();
    }

    public long getAlarmPeriodSettingNanos() {
        return this.alarmPeriodSettingNanos.get();
    }

    public long getIdleHeartbeatSetting() {
        return this.idleHeartbeatSettingMillis.get();
    }

    public long getLastConsumerSequence() {
        return this.lastConsumerSeq;
    }

    public long getLastMsgReceivedNanoTime() {
        return this.lastMsgReceivedNanoTime.get();
    }

    public long getLastStreamSequence() {
        return this.lastStreamSeq;
    }

    public void handleHeartbeatError() {
        this.conn.executeCallback(new NatsConnection.ErrorListenerCaller() { // from class: io.nats.client.impl.a
            @Override // io.nats.client.impl.NatsConnection.ErrorListenerCaller
            public final void call(Connection connection, ErrorListener errorListener) {
                this.a.lambda$handleHeartbeatError$0(connection, errorListener);
            }
        });
    }

    public void initOrResetHeartbeatTimer() {
        this.stateChangeLock.lock();
        try {
            ScheduledTask scheduledTask = this.heartbeatTaskRef.get();
            if (scheduledTask != null) {
                scheduledTask.shutdown();
            }
            this.heartbeatTaskRef.set(new ScheduledTask(this.conn.getScheduledExecutor(), this.alarmPeriodSettingNanos.get(), TimeUnit.NANOSECONDS, new e(3, this)));
            updateLastMessageReceived();
            this.stateChangeLock.unlock();
        } catch (Throwable th) {
            this.stateChangeLock.unlock();
            throw th;
        }
    }

    public boolean isHb() {
        return this.hb.get();
    }

    public boolean isSyncMode() {
        return this.syncMode;
    }

    public abstract ManageResult manage(Message message);

    public void shutdown() {
        shutdownHeartbeatTimer();
    }

    public void shutdownHeartbeatTimer() {
        this.stateChangeLock.lock();
        try {
            ScheduledTask scheduledTask = this.heartbeatTaskRef.get();
            if (scheduledTask != null) {
                scheduledTask.shutdown();
                this.heartbeatTaskRef.set(null);
            }
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    public void startPullRequest(String str, PullRequestOptions pullRequestOptions, boolean z, PullManagerObserver pullManagerObserver) {
    }

    public void startup(NatsJetStreamSubscription natsJetStreamSubscription) {
        this.sub = natsJetStreamSubscription;
    }

    public void trackJsMessage(Message message) {
        this.stateChangeLock.lock();
        try {
            this.lastStreamSeq = message.metaData().streamSequence();
            this.lastConsumerSeq++;
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    public void updateLastMessageReceived() {
        this.lastMsgReceivedNanoTime.set(NatsSystemClock.nanoTime());
    }
}
