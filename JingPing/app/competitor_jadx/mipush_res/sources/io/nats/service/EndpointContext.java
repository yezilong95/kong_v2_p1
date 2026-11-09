package io.nats.service;

import io.nats.client.BaseConsumeOptions;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.NatsSystemClock;
import io.nats.client.support.DateTimeUtils;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
class EndpointContext {
    private final Connection conn;
    private final Dispatcher dispatcher;
    private final ServiceMessageHandler handler;
    private final boolean internalDispatcher;
    private String lastError;
    private final AtomicLong numErrors;
    private final AtomicLong numRequests;
    private final AtomicLong processingTime;
    private final String qGroup;
    private final boolean recordStats;
    private boolean running;
    private final ServiceEndpoint se;
    private ZonedDateTime started;

    public EndpointContext(Connection connection, Dispatcher dispatcher, boolean z, ServiceEndpoint serviceEndpoint) {
        this.conn = connection;
        this.se = serviceEndpoint;
        this.handler = serviceEndpoint.getHandler();
        this.recordStats = !z;
        this.qGroup = z ? null : serviceEndpoint.getQueueGroup();
        this.running = false;
        if (serviceEndpoint.getDispatcher() == null) {
            this.dispatcher = dispatcher;
            this.internalDispatcher = true;
        } else {
            this.dispatcher = serviceEndpoint.getDispatcher();
            this.internalDispatcher = false;
        }
        this.numRequests = new AtomicLong();
        this.numErrors = new AtomicLong();
        this.processingTime = new AtomicLong();
        this.started = DateTimeUtils.gmtNow();
    }

    public CompletableFuture<Boolean> drain(Duration duration) throws InterruptedException {
        return this.dispatcher.drain(duration);
    }

    public EndpointStats getEndpointStats() {
        return new EndpointStats(this.se.getEndpoint().getName(), this.se.getSubject(), this.se.getQueueGroup(), this.numRequests.get(), this.numErrors.get(), this.processingTime.get(), this.lastError, this.se.getStatsDataSupplier() == null ? null : this.se.getStatsDataSupplier().get(), this.started);
    }

    public boolean isNotInternalDispatcher() {
        return !this.internalDispatcher;
    }

    public void onMessage(Message message) throws InterruptedException {
        long jNanoTime = NatsSystemClock.nanoTime();
        ServiceMessage serviceMessage = new ServiceMessage(message);
        try {
            if (this.recordStats) {
                this.numRequests.incrementAndGet();
            }
            this.handler.onMessage(serviceMessage);
        } catch (Throwable th) {
            try {
                if (this.recordStats) {
                    this.numErrors.incrementAndGet();
                    this.lastError = th.toString();
                }
                try {
                    serviceMessage.respondStandardError(this.conn, this.lastError, BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
                } catch (RuntimeException unused) {
                }
                if (this.recordStats) {
                    this.processingTime.addAndGet(NatsSystemClock.nanoTime() - jNanoTime);
                }
            } finally {
                if (this.recordStats) {
                    this.processingTime.addAndGet(NatsSystemClock.nanoTime() - jNanoTime);
                }
            }
        }
    }

    public void reset() {
        this.numRequests.set(0L);
        this.numErrors.set(0L);
        this.processingTime.set(0L);
        this.lastError = null;
        this.started = DateTimeUtils.gmtNow();
    }

    public void start() {
        if (this.running) {
            return;
        }
        if (this.qGroup == null) {
            this.dispatcher.subscribe(this.se.getSubject(), new MessageHandler() { // from class: io.nats.service.a
                @Override // io.nats.client.MessageHandler
                public final void onMessage(Message message) throws InterruptedException {
                    this.a.onMessage(message);
                }
            });
        } else {
            this.dispatcher.subscribe(this.se.getSubject(), this.qGroup, new MessageHandler() { // from class: io.nats.service.a
                @Override // io.nats.client.MessageHandler
                public final void onMessage(Message message) throws InterruptedException {
                    this.a.onMessage(message);
                }
            });
        }
        this.started = DateTimeUtils.gmtNow();
        this.running = true;
    }
}
