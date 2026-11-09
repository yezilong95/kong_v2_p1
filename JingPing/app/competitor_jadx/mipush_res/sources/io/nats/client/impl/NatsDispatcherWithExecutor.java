package io.nats.client.impl;

import io.nats.client.MessageHandler;

/* JADX INFO: loaded from: classes.dex */
class NatsDispatcherWithExecutor extends NatsDispatcher {
    public NatsDispatcherWithExecutor(NatsConnection natsConnection, MessageHandler messageHandler) {
        super(natsConnection, messageHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$run$0(MessageHandler messageHandler, NatsMessage natsMessage, NatsSubscription natsSubscription) {
        try {
            messageHandler.onMessage(natsMessage);
        } catch (Error e) {
            this.connection.processException(new Exception(e));
        } catch (Exception e2) {
            this.connection.processException(e2);
        }
        if (natsSubscription.reachedUnsubLimit()) {
            this.connection.invalidate(natsSubscription);
        }
    }

    @Override // io.nats.client.impl.NatsDispatcher, java.lang.Runnable
    public void run() {
        final NatsSubscription natsSubscription;
        while (this.running.get() && !Thread.interrupted()) {
            try {
                try {
                    final NatsMessage natsMessagePop = this.incoming.pop(this.waitForMessage);
                    if (natsMessagePop != null && (natsSubscription = natsMessagePop.getNatsSubscription()) != null && natsSubscription.isActive()) {
                        final MessageHandler messageHandler = this.subscriptionHandlers.get(natsSubscription.getSID());
                        if (messageHandler == null) {
                            messageHandler = this.defaultHandler;
                        }
                        if (messageHandler != null) {
                            natsSubscription.incrementDeliveredCount();
                            incrementDeliveredCount();
                            this.connection.getExecutor().execute(new Runnable() { // from class: io.nats.client.impl.o
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.E.lambda$run$0(messageHandler, natsMessagePop, natsSubscription);
                                }
                            });
                        }
                    }
                    if (breakRunLoop()) {
                        break;
                    }
                } catch (InterruptedException e) {
                    if (this.running.get()) {
                        this.connection.processException(e);
                    }
                    Thread.currentThread().interrupt();
                    this.running.set(false);
                    this.thread = null;
                    return;
                }
            } catch (Throwable th) {
                this.running.set(false);
                this.thread = null;
                throw th;
            }
        }
        this.running.set(false);
        this.thread = null;
    }
}
