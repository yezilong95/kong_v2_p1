package io.nats.client.impl;

import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.Subscription;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
class NatsSubscription extends NatsConsumer implements Subscription {
    private Function<NatsMessage, Boolean> beforeQueueProcessor;
    private NatsDispatcher dispatcher;
    private MessageQueue incoming;
    private final String queueName;
    private String sid;
    private String subject;
    private final AtomicLong unSubMessageLimit;

    public NatsSubscription(String str, String str2, String str3, NatsConnection natsConnection, NatsDispatcher natsDispatcher) {
        super(natsConnection);
        this.subject = str2;
        this.queueName = str3;
        this.sid = str;
        this.dispatcher = natsDispatcher;
        this.unSubMessageLimit = new AtomicLong(-1L);
        if (this.dispatcher == null) {
            this.incoming = new MessageQueue(false, natsConnection.getOptions().getRequestCleanupInterval());
        }
        setBeforeQueueProcessor(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$setBeforeQueueProcessor$0(NatsMessage natsMessage) {
        return Boolean.TRUE;
    }

    @Override // io.nats.client.impl.NatsConsumer
    public void cleanUpAfterDrain() {
        this.connection.invalidate(this);
    }

    public Function<NatsMessage, Boolean> getBeforeQueueProcessor() {
        return this.beforeQueueProcessor;
    }

    public Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    @Override // io.nats.client.impl.NatsConsumer
    public MessageQueue getMessageQueue() {
        return this.incoming;
    }

    public NatsDispatcher getNatsDispatcher() {
        return this.dispatcher;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public String getSID() {
        return this.sid;
    }

    public String getSubject() {
        return this.subject;
    }

    public void invalidate() {
        MessageQueue messageQueue = this.incoming;
        if (messageQueue != null) {
            messageQueue.pause();
        }
        this.dispatcher = null;
        this.incoming = null;
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public boolean isActive() {
        return (this.dispatcher == null && this.incoming == null) ? false : true;
    }

    public Message nextMessage(long j) throws IllegalStateException, InterruptedException {
        return nextMessageInternal(Duration.ofMillis(j));
    }

    public NatsMessage nextMessageInternal(Duration duration) throws InterruptedException {
        if (this.dispatcher != null) {
            throw new IllegalStateException("Subscriptions that belong to a dispatcher cannot respond to nextMessage directly.");
        }
        MessageQueue messageQueue = this.incoming;
        if (messageQueue == null) {
            throw new IllegalStateException("This subscription is inactive.");
        }
        NatsMessage natsMessagePop = messageQueue.pop(duration);
        MessageQueue messageQueue2 = this.incoming;
        if (messageQueue2 == null || !messageQueue2.isRunning()) {
            throw new IllegalStateException("This subscription became inactive.");
        }
        if (natsMessagePop != null) {
            incrementDeliveredCount();
        }
        if (reachedUnsubLimit()) {
            this.connection.invalidate(this);
        }
        return natsMessagePop;
    }

    public void reSubscribe(String str) {
        this.connection.sendUnsub(this, 0);
        NatsDispatcher natsDispatcher = this.dispatcher;
        if (natsDispatcher == null) {
            this.connection.remove(this);
            this.sid = this.connection.reSubscribe(this, str, this.queueName);
        } else {
            MessageHandler messageHandler = natsDispatcher.getSubscriptionHandlers().get(this.sid);
            this.dispatcher.remove(this);
            this.sid = this.dispatcher.reSubscribe(this, str, this.queueName, messageHandler);
        }
        this.subject = str;
    }

    public boolean reachedUnsubLimit() {
        long j = this.unSubMessageLimit.get();
        return j > 0 && j <= getDeliveredCount();
    }

    @Override // io.nats.client.impl.NatsConsumer
    public void sendUnsubForDrain() {
        this.connection.sendUnsub(this, -1);
    }

    public void setBeforeQueueProcessor(Function<NatsMessage, Boolean> function) {
        if (function == null) {
            function = new s();
        }
        this.beforeQueueProcessor = function;
    }

    public void setUnsubLimit(long j) {
        this.unSubMessageLimit.set(j);
    }

    public void unsubscribe() {
        if (this.dispatcher != null) {
            throw new IllegalStateException("Subscriptions that belong to a dispatcher cannot respond to unsubscribe directly.");
        }
        if (this.incoming == null) {
            throw new IllegalStateException("This subscription is inactive.");
        }
        if (isDraining()) {
            return;
        }
        this.connection.unsubscribe(this, -1);
    }

    public Message nextMessage(Duration duration) throws IllegalStateException, InterruptedException {
        return nextMessageInternal(duration);
    }

    public Subscription unsubscribe(int i) {
        if (this.dispatcher == null) {
            if (this.incoming != null) {
                if (isDraining()) {
                    return this;
                }
                this.connection.unsubscribe(this, i);
                return this;
            }
            throw new IllegalStateException("This subscription is inactive.");
        }
        throw new IllegalStateException("Subscriptions that belong to a dispatcher cannot respond to unsubscribe directly.");
    }
}
