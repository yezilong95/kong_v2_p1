package io.nats.client.impl;

import io.nats.client.Dispatcher;
import io.nats.client.MessageHandler;
import io.nats.client.Subscription;
import io.nats.client.support.Validator;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
class NatsDispatcher extends NatsConsumer implements Dispatcher, Runnable {
    protected final MessageHandler defaultHandler;
    protected String id;
    protected final MessageQueue incoming;
    protected final AtomicBoolean running;
    protected final AtomicBoolean started;
    protected final Map<String, MessageHandler> subscriptionHandlers;
    protected final Map<String, NatsSubscription> subscriptionsUsingDefaultHandler;
    protected final Map<String, NatsSubscription> subscriptionsWithHandlers;
    protected Future<Boolean> thread;
    protected final Duration waitForMessage;

    public NatsDispatcher(NatsConnection natsConnection, MessageHandler messageHandler) {
        super(natsConnection);
        this.defaultHandler = messageHandler;
        this.incoming = new MessageQueue(true, natsConnection.getOptions().getRequestCleanupInterval());
        this.subscriptionsUsingDefaultHandler = new ConcurrentHashMap();
        this.subscriptionsWithHandlers = new ConcurrentHashMap();
        this.subscriptionHandlers = new ConcurrentHashMap();
        this.running = new AtomicBoolean(false);
        this.started = new AtomicBoolean(false);
        this.waitForMessage = Duration.ofMinutes(5L);
    }

    private NatsSubscription _subscribeImplHandlerProvided(String str, String str2, MessageHandler messageHandler, NatsSubscriptionFactory natsSubscriptionFactory) {
        NatsSubscription natsSubscriptionCreateSubscription = this.connection.createSubscription(str, str2, this, natsSubscriptionFactory);
        this.subscriptionsWithHandlers.put(natsSubscriptionCreateSubscription.getSID(), natsSubscriptionCreateSubscription);
        this.subscriptionHandlers.put(natsSubscriptionCreateSubscription.getSID(), messageHandler);
        return natsSubscriptionCreateSubscription;
    }

    private void checkBeforeSubImpl() {
        if (!this.running.get()) {
            throw new IllegalStateException("Dispatcher is closed");
        }
        if (isDraining()) {
            throw new IllegalStateException("Dispatcher is draining");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resendSubscriptions$2(String str, NatsSubscription natsSubscription) {
        this.connection.sendSubscriptionMessage(natsSubscription.getSID(), natsSubscription.getSubject(), natsSubscription.getQueueName(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resendSubscriptions$3(String str, NatsSubscription natsSubscription) {
        this.connection.sendSubscriptionMessage(natsSubscription.getSID(), natsSubscription.getSubject(), natsSubscription.getQueueName(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendUnsubForDrain$4(String str, NatsSubscription natsSubscription) {
        this.connection.sendUnsub(natsSubscription, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendUnsubForDrain$5(String str, NatsSubscription natsSubscription) {
        this.connection.sendUnsub(natsSubscription, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$stop$0(String str, NatsSubscription natsSubscription) {
        this.connection.unsubscribe(natsSubscription, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$stop$1(String str, NatsSubscription natsSubscription) {
        this.connection.unsubscribe(natsSubscription, -1);
    }

    public boolean breakRunLoop() {
        return this.incoming.isDrained();
    }

    @Override // io.nats.client.impl.NatsConsumer
    public void cleanUpAfterDrain() {
        this.connection.cleanupDispatcher(this);
    }

    public String getId() {
        return this.id;
    }

    @Override // io.nats.client.impl.NatsConsumer
    public MessageQueue getMessageQueue() {
        return this.incoming;
    }

    public Map<String, MessageHandler> getSubscriptionHandlers() {
        return this.subscriptionHandlers;
    }

    public void internalStart(String str, boolean z) {
        if (this.started.get()) {
            return;
        }
        this.id = str;
        this.running.set(true);
        this.started.set(true);
        if (z) {
            this.thread = this.connection.getExecutor().submit(this, Boolean.TRUE);
        }
    }

    @Override // io.nats.client.impl.NatsConsumer, io.nats.client.Consumer
    public boolean isActive() {
        return this.running.get();
    }

    @Override // io.nats.client.impl.NatsConsumer
    public boolean isDrained() {
        return !isActive() && super.isDrained();
    }

    public String reSubscribe(NatsSubscription natsSubscription, String str, String str2, MessageHandler messageHandler) {
        String strReSubscribe = this.connection.reSubscribe(natsSubscription, str, str2);
        this.subscriptionsWithHandlers.put(strReSubscribe, natsSubscription);
        this.subscriptionHandlers.put(strReSubscribe, messageHandler);
        return strReSubscribe;
    }

    public void remove(NatsSubscription natsSubscription) {
        if (this.subscriptionsWithHandlers.remove(natsSubscription.getSID()) != null) {
            this.subscriptionHandlers.remove(natsSubscription.getSID());
        } else if (this.subscriptionsUsingDefaultHandler.get(natsSubscription.getSubject()).getSID().equals(natsSubscription.getSID())) {
            this.subscriptionsUsingDefaultHandler.remove(natsSubscription.getSubject());
        }
    }

    public void resendSubscriptions() {
        this.subscriptionsUsingDefaultHandler.forEach(new n(4, this));
        this.subscriptionsWithHandlers.forEach(new n(5, this));
    }

    @Override // java.lang.Runnable
    public void run() {
        NatsSubscription natsSubscription;
        do {
            try {
                try {
                } catch (InterruptedException e) {
                    if (this.running.get()) {
                        this.connection.processException(e);
                    }
                    Thread.currentThread().interrupt();
                }
                if (!this.running.get() || Thread.interrupted()) {
                    this.running.set(false);
                    this.thread = null;
                    return;
                }
                NatsMessage natsMessagePop = this.incoming.pop(this.waitForMessage);
                if (natsMessagePop != null && (natsSubscription = natsMessagePop.getNatsSubscription()) != null && natsSubscription.isActive()) {
                    MessageHandler messageHandler = this.subscriptionHandlers.get(natsSubscription.getSID());
                    if (messageHandler == null) {
                        messageHandler = this.defaultHandler;
                    }
                    if (messageHandler != null) {
                        natsSubscription.incrementDeliveredCount();
                        incrementDeliveredCount();
                        try {
                            messageHandler.onMessage(natsMessagePop);
                        } catch (Error e2) {
                            this.connection.processException(new Exception(e2));
                        } catch (Exception e3) {
                            this.connection.processException(e3);
                        }
                        if (natsSubscription.reachedUnsubLimit()) {
                            this.connection.invalidate(natsSubscription);
                        }
                    }
                }
            } catch (Throwable th) {
                this.running.set(false);
                this.thread = null;
                throw th;
            }
        } while (!breakRunLoop());
        this.running.set(false);
        this.thread = null;
    }

    @Override // io.nats.client.impl.NatsConsumer
    public void sendUnsubForDrain() {
        this.subscriptionsUsingDefaultHandler.forEach(new n(2, this));
        this.subscriptionsWithHandlers.forEach(new n(3, this));
    }

    @Override // io.nats.client.Dispatcher
    public void start(String str) {
        internalStart(str, true);
    }

    public void stop(boolean z) {
        this.running.set(false);
        this.incoming.pause();
        Future<Boolean> future = this.thread;
        if (future != null) {
            try {
                if (!future.isCancelled()) {
                    this.thread.cancel(true);
                }
            } catch (Exception unused) {
            }
        }
        if (z) {
            this.subscriptionsUsingDefaultHandler.forEach(new n(0, this));
            this.subscriptionsWithHandlers.forEach(new n(1, this));
        }
        this.subscriptionsUsingDefaultHandler.clear();
        this.subscriptionsWithHandlers.clear();
        this.subscriptionHandlers.clear();
    }

    @Override // io.nats.client.Dispatcher
    public Dispatcher subscribe(String str) {
        Validator.validateSubject(str, true);
        subscribeImplCore(str, null, null);
        return this;
    }

    public NatsSubscription subscribeImplCore(String str, String str2, MessageHandler messageHandler) {
        checkBeforeSubImpl();
        if (messageHandler != null) {
            return _subscribeImplHandlerProvided(str, str2, messageHandler, null);
        }
        NatsSubscription natsSubscription = this.subscriptionsUsingDefaultHandler.get(str);
        if (natsSubscription != null) {
            return natsSubscription;
        }
        NatsSubscription natsSubscriptionCreateSubscription = this.connection.createSubscription(str, str2, this, null);
        if (this.subscriptionsUsingDefaultHandler.putIfAbsent(str, natsSubscriptionCreateSubscription) != null) {
            this.connection.unsubscribe(natsSubscriptionCreateSubscription, -1);
        }
        return natsSubscriptionCreateSubscription;
    }

    public NatsSubscription subscribeImplJetStream(String str, String str2, MessageHandler messageHandler, NatsSubscriptionFactory natsSubscriptionFactory) {
        checkBeforeSubImpl();
        return _subscribeImplHandlerProvided(str, str2, messageHandler, natsSubscriptionFactory);
    }

    public NatsSubscription subscribeReturningSubscription(String str) {
        Validator.validateSubject(str, true);
        return subscribeImplCore(str, null, null);
    }

    @Override // io.nats.client.Dispatcher
    public Dispatcher unsubscribe(String str) {
        return unsubscribe(str, -1);
    }

    @Override // io.nats.client.Dispatcher
    public Dispatcher unsubscribe(Subscription subscription) {
        return unsubscribe(subscription, -1);
    }

    @Override // io.nats.client.Dispatcher
    public Subscription subscribe(String str, MessageHandler messageHandler) {
        Validator.validateSubject(str, true);
        Validator.required(messageHandler, "Handler");
        return subscribeImplCore(str, null, messageHandler);
    }

    @Override // io.nats.client.Dispatcher
    public Dispatcher unsubscribe(String str, int i) {
        if (this.running.get()) {
            if (!isDraining()) {
                if (str != null && str.length() != 0) {
                    NatsSubscription natsSubscription = this.subscriptionsUsingDefaultHandler.get(str);
                    if (natsSubscription != null) {
                        this.connection.unsubscribe(natsSubscription, i);
                    }
                } else {
                    throw new IllegalArgumentException("Subject is required in unsubscribe");
                }
            }
            return this;
        }
        throw new IllegalStateException("Dispatcher is closed");
    }

    @Override // io.nats.client.Dispatcher
    public Dispatcher subscribe(String str, String str2) {
        Validator.validateSubject(str, true);
        Validator.validateQueueName(str2, true);
        subscribeImplCore(str, str2, null);
        return this;
    }

    @Override // io.nats.client.Dispatcher
    public Subscription subscribe(String str, String str2, MessageHandler messageHandler) {
        Validator.validateSubject(str, true);
        Validator.validateQueueName(str2, true);
        if (messageHandler != null) {
            return subscribeImplCore(str, str2, messageHandler);
        }
        throw new IllegalArgumentException("MessageHandler is required in subscribe");
    }

    @Override // io.nats.client.Dispatcher
    public Dispatcher unsubscribe(Subscription subscription, int i) {
        if (this.running.get()) {
            if (!isDraining()) {
                if (subscription.getDispatcher() == this) {
                    if (subscription instanceof NatsSubscription) {
                        NatsSubscription natsSubscription = this.subscriptionsWithHandlers.get(((NatsSubscription) subscription).getSID());
                        if (natsSubscription != null) {
                            this.connection.unsubscribe(natsSubscription, i);
                        }
                    } else {
                        throw new IllegalArgumentException("This Subscription implementation is not known by Dispatcher");
                    }
                } else {
                    throw new IllegalStateException("Subscription is not managed by this Dispatcher");
                }
            }
            return this;
        }
        throw new IllegalStateException("Dispatcher is closed");
    }
}
