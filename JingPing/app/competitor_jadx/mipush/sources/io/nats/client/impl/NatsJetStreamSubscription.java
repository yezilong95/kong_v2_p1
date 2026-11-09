package io.nats.client.impl;

import io.nats.client.Dispatcher;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamReader;
import io.nats.client.JetStreamStatusException;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.NatsSystemClock;
import io.nats.client.PullRequestOptions;
import io.nats.client.Subscription;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.impl.MessageManager;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class NatsJetStreamSubscription extends NatsSubscription implements JetStreamSubscription, NatsJetStreamConstants {
    public static final long EXPIRE_ADJUSTMENT = 10;
    public static final long MIN_EXPIRE_MILLIS = 20;
    public static final String SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL = "Subscription type does not support pull.";
    protected String consumerName;
    protected final NatsJetStream js;
    protected MessageManager manager;
    protected String stream;

    /* JADX INFO: renamed from: io.nats.client.impl.NatsJetStreamSubscription$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$nats$client$impl$MessageManager$ManageResult;

        static {
            int[] iArr = new int[MessageManager.ManageResult.values().length];
            $SwitchMap$io$nats$client$impl$MessageManager$ManageResult = iArr;
            try {
                iArr[MessageManager.ManageResult.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$nats$client$impl$MessageManager$ManageResult[MessageManager.ManageResult.STATUS_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$nats$client$impl$MessageManager$ManageResult[MessageManager.ManageResult.STATUS_TERMINUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public NatsJetStreamSubscription(String str, String str2, String str3, NatsConnection natsConnection, NatsDispatcher natsDispatcher, NatsJetStream natsJetStream, String str4, String str5, MessageManager messageManager) {
        super(str, str2, str3, natsConnection, natsDispatcher);
        this.js = natsJetStream;
        this.stream = str4;
        this.consumerName = str5;
        this.manager = messageManager;
        messageManager.startup(this);
    }

    public Message _nextUnmanaged(long j, String str) throws InterruptedException {
        NatsMessage natsMessageNextMessageInternal;
        long jNanoTime = NatsSystemClock.nanoTime();
        long jNanoTime2 = j;
        while (jNanoTime2 > 0 && (natsMessageNextMessageInternal = nextMessageInternal(Duration.ofNanos(jNanoTime2))) != null) {
            int i = AnonymousClass1.$SwitchMap$io$nats$client$impl$MessageManager$ManageResult[this.manager.manage(natsMessageNextMessageInternal).ordinal()];
            if (i == 1) {
                return natsMessageNextMessageInternal;
            }
            if (i != 2) {
                if (i == 3 && (str == null || str.equals(natsMessageNextMessageInternal.getSubject()))) {
                    return null;
                }
            } else if (str == null || str.equals(natsMessageNextMessageInternal.getSubject())) {
                throw new JetStreamStatusException(natsMessageNextMessageInternal.getStatus(), this);
            }
            jNanoTime2 = j - (NatsSystemClock.nanoTime() - jNanoTime);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002c, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public io.nats.client.Message _nextUnmanagedNoWait(java.lang.String r5) throws java.lang.InterruptedException {
        /*
            r4 = this;
        L0:
            r0 = 0
            io.nats.client.impl.NatsMessage r1 = r4.nextMessageInternal(r0)
            if (r1 != 0) goto L8
            return r0
        L8:
            int[] r2 = io.nats.client.impl.NatsJetStreamSubscription.AnonymousClass1.$SwitchMap$io$nats$client$impl$MessageManager$ManageResult
            io.nats.client.impl.MessageManager r3 = r4.manager
            io.nats.client.impl.MessageManager$ManageResult r3 = r3.manage(r1)
            int r3 = r3.ordinal()
            r2 = r2[r3]
            r3 = 1
            if (r2 == r3) goto L44
            r3 = 2
            if (r2 == r3) goto L2d
            r3 = 3
            if (r2 == r3) goto L20
            goto L0
        L20:
            if (r5 == 0) goto L2c
            java.lang.String r1 = r1.getSubject()
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L0
        L2c:
            return r0
        L2d:
            if (r5 == 0) goto L3a
            java.lang.String r0 = r1.getSubject()
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L3a
            goto L0
        L3a:
            io.nats.client.JetStreamStatusException r5 = new io.nats.client.JetStreamStatusException
            io.nats.client.support.Status r0 = r1.getStatus()
            r5.<init>(r0, r4)
            throw r5
        L44:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsJetStreamSubscription._nextUnmanagedNoWait(java.lang.String):io.nats.client.Message");
    }

    public Message _nextUnmanagedWaitForever(String str) throws InterruptedException {
        NatsMessage natsMessageNextMessageInternal;
        while (true) {
            natsMessageNextMessageInternal = nextMessageInternal(Duration.ZERO);
            if (natsMessageNextMessageInternal != null) {
                int i = AnonymousClass1.$SwitchMap$io$nats$client$impl$MessageManager$ManageResult[this.manager.manage(natsMessageNextMessageInternal).ordinal()];
                if (i == 1) {
                    return natsMessageNextMessageInternal;
                }
                if (i == 2 && (str == null || str.equals(natsMessageNextMessageInternal.getSubject()))) {
                    break;
                }
            }
        }
        throw new JetStreamStatusException(natsMessageNextMessageInternal.getStatus(), this);
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ void clearDroppedCount() {
        super.clearDroppedCount();
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ CompletableFuture drain(Duration duration) throws InterruptedException {
        return super.drain(duration);
    }

    public List<Message> fetch(int i, long j) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    @Override // io.nats.client.impl.NatsSubscription
    public /* bridge */ /* synthetic */ Function getBeforeQueueProcessor() {
        return super.getBeforeQueueProcessor();
    }

    @Override // io.nats.client.JetStreamSubscription
    public ConsumerInfo getConsumerInfo() throws IOException, JetStreamApiException {
        return this.js.lookupConsumerInfo(this.stream, this.consumerName);
    }

    @Override // io.nats.client.JetStreamSubscription
    public String getConsumerName() {
        return this.consumerName;
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ long getDeliveredCount() {
        return super.getDeliveredCount();
    }

    @Override // io.nats.client.impl.NatsSubscription, io.nats.client.Subscription
    public /* bridge */ /* synthetic */ Dispatcher getDispatcher() {
        return super.getDispatcher();
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ long getDroppedCount() {
        return super.getDroppedCount();
    }

    public MessageManager getManager() {
        return this.manager;
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ long getPendingByteCount() {
        return super.getPendingByteCount();
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ long getPendingByteLimit() {
        return super.getPendingByteLimit();
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ long getPendingMessageCount() {
        return super.getPendingMessageCount();
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ long getPendingMessageLimit() {
        return super.getPendingMessageLimit();
    }

    @Override // io.nats.client.impl.NatsSubscription, io.nats.client.Subscription
    public /* bridge */ /* synthetic */ String getQueueName() {
        return super.getQueueName();
    }

    public String getStream() {
        return this.stream;
    }

    @Override // io.nats.client.impl.NatsSubscription, io.nats.client.Subscription
    public /* bridge */ /* synthetic */ String getSubject() {
        return super.getSubject();
    }

    @Override // io.nats.client.impl.NatsSubscription
    public void invalidate() {
        this.manager.shutdown();
        super.invalidate();
    }

    @Override // io.nats.client.impl.NatsSubscription, io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ boolean isActive() {
        return super.isActive();
    }

    public boolean isPullMode() {
        return false;
    }

    public Iterator<Message> iterate(int i, Duration duration) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    @Override // io.nats.client.impl.NatsSubscription, io.nats.client.Subscription
    public Message nextMessage(Duration duration) throws IllegalStateException, InterruptedException {
        if (duration == null) {
            return _nextUnmanagedNoWait(null);
        }
        long nanos = duration.toNanos();
        return nanos <= 0 ? _nextUnmanagedWaitForever(null) : _nextUnmanaged(nanos, null);
    }

    public void pull(int i) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    public void pullExpiresIn(int i, Duration duration) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    public void pullNoWait(int i) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    public JetStreamReader reader(int i, int i2) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    public void setConsumerName(String str) {
        this.consumerName = str;
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public /* bridge */ /* synthetic */ void setPendingLimits(long j, long j2) {
        super.setPendingLimits(j, j2);
    }

    public String toString() {
        return "NatsJetStreamSubscription{consumer='" + this.consumerName + "', stream='" + this.stream + "', deliver='" + getSubject() + "', isPullMode=" + isPullMode() + '}';
    }

    @Override // io.nats.client.impl.NatsSubscription, io.nats.client.Subscription
    public /* bridge */ /* synthetic */ Subscription unsubscribe(int i) {
        return super.unsubscribe(i);
    }

    public List<Message> fetch(int i, Duration duration) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    public Iterator<Message> iterate(int i, long j) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    public void pull(PullRequestOptions pullRequestOptions) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    public void pullExpiresIn(int i, long j) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    public void pullNoWait(int i, Duration duration) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    @Override // io.nats.client.impl.NatsSubscription, io.nats.client.Subscription
    public /* bridge */ /* synthetic */ void unsubscribe() {
        super.unsubscribe();
    }

    public void pullNoWait(int i, long j) {
        throw new IllegalStateException(SUBSCRIPTION_TYPE_DOES_NOT_SUPPORT_PULL);
    }

    @Override // io.nats.client.impl.NatsSubscription, io.nats.client.Subscription
    public Message nextMessage(long j) throws IllegalStateException, InterruptedException {
        if (j <= 0) {
            return _nextUnmanagedWaitForever(null);
        }
        return _nextUnmanaged(j * NatsConstants.NANOS_PER_MILLI, null);
    }
}
