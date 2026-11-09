package io.nats.client.impl;

import io.nats.client.ConsumeOptions;
import io.nats.client.ConsumerContext;
import io.nats.client.Dispatcher;
import io.nats.client.FetchConsumeOptions;
import io.nats.client.FetchConsumer;
import io.nats.client.IterableConsumer;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamStatusCheckedException;
import io.nats.client.Message;
import io.nats.client.MessageConsumer;
import io.nats.client.MessageHandler;
import io.nats.client.PullRequestOptions;
import io.nats.client.PullSubscribeOptions;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.api.OrderedConsumerConfiguration;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class NatsConsumerContext implements ConsumerContext, SimplifiedSubscriptionMaker {
    private final AtomicReference<ConsumerInfo> cachedConsumerInfo;
    private final AtomicReference<String> consumerName;
    private final AtomicReference<Dispatcher> defaultDispatcher;
    private final AtomicLong highestSeq;
    private final ConsumerConfiguration initialOrderedConsumerConfig;
    private final AtomicReference<NatsMessageConsumerBase> lastConsumer;
    private final boolean ordered;
    private final ReentrantLock stateLock = new ReentrantLock();
    private final NatsStreamContext streamCtx;
    private final PullSubscribeOptions unorderedBindPso;

    public static class OrderedPullSubscribeOptionsBuilder extends PullSubscribeOptions.Builder {
        public OrderedPullSubscribeOptionsBuilder(String str, ConsumerConfiguration consumerConfiguration) {
            stream(str);
            configuration(consumerConfiguration);
            this.ordered = true;
        }
    }

    public NatsConsumerContext(NatsStreamContext natsStreamContext, ConsumerInfo consumerInfo, OrderedConsumerConfiguration orderedConsumerConfiguration) {
        this.streamCtx = natsStreamContext;
        AtomicReference<ConsumerInfo> atomicReference = new AtomicReference<>();
        this.cachedConsumerInfo = atomicReference;
        AtomicReference<String> atomicReference2 = new AtomicReference<>();
        this.consumerName = atomicReference2;
        this.highestSeq = new AtomicLong();
        this.defaultDispatcher = new AtomicReference<>();
        this.lastConsumer = new AtomicReference<>();
        if (consumerInfo == null) {
            this.ordered = true;
            this.initialOrderedConsumerConfig = ConsumerConfiguration.builder().name(orderedConsumerConfiguration.getConsumerNamePrefix()).filterSubjects(orderedConsumerConfiguration.getFilterSubjects()).deliverPolicy(orderedConsumerConfiguration.getDeliverPolicy()).startSequence(orderedConsumerConfiguration.getStartSequence()).startTime(orderedConsumerConfiguration.getStartTime()).replayPolicy(orderedConsumerConfiguration.getReplayPolicy()).headersOnly(orderedConsumerConfiguration.getHeadersOnly()).build();
            this.unorderedBindPso = null;
        } else {
            this.ordered = false;
            this.initialOrderedConsumerConfig = null;
            atomicReference.set(consumerInfo);
            atomicReference2.set(consumerInfo.getName());
            this.unorderedBindPso = PullSubscribeOptions.fastBind(natsStreamContext.streamName, consumerInfo.getName());
        }
    }

    private void checkState() throws IOException {
        NatsMessageConsumerBase natsMessageConsumerBase = this.lastConsumer.get();
        if (natsMessageConsumerBase != null) {
            if (this.ordered && !natsMessageConsumerBase.finished.get()) {
                throw new IOException("The ordered consumer is already receiving messages. Ordered Consumer does not allow multiple instances at time.");
            }
            if (!natsMessageConsumerBase.finished.get() || natsMessageConsumerBase.stopped.get()) {
                return;
            }
            natsMessageConsumerBase.shutdownSub();
        }
    }

    private NatsMessageConsumerBase trackConsume(NatsMessageConsumerBase natsMessageConsumerBase) {
        this.lastConsumer.set(natsMessageConsumerBase);
        return natsMessageConsumerBase;
    }

    @Override // io.nats.client.BaseConsumerContext
    public MessageConsumer consume(MessageHandler messageHandler) throws IOException, JetStreamApiException {
        return consume(ConsumeOptions.DEFAULT_CONSUME_OPTIONS, null, messageHandler);
    }

    @Override // io.nats.client.BaseConsumerContext
    public FetchConsumer fetch(FetchConsumeOptions fetchConsumeOptions) throws IOException, JetStreamApiException {
        try {
            this.stateLock.lock();
            checkState();
            Validator.required(fetchConsumeOptions, "Fetch Consume Options");
            return (FetchConsumer) trackConsume(new NatsFetchConsumer(this, this.cachedConsumerInfo.get(), fetchConsumeOptions));
        } finally {
            this.stateLock.unlock();
        }
    }

    @Override // io.nats.client.BaseConsumerContext
    public FetchConsumer fetchBytes(int i) throws IOException, JetStreamApiException {
        return fetch(FetchConsumeOptions.builder().maxBytes(i).build());
    }

    @Override // io.nats.client.BaseConsumerContext
    public FetchConsumer fetchMessages(int i) throws IOException, JetStreamApiException {
        return fetch(FetchConsumeOptions.builder().maxMessages(i).build());
    }

    @Override // io.nats.client.ConsumerContext
    public ConsumerInfo getCachedConsumerInfo() {
        return this.cachedConsumerInfo.get();
    }

    @Override // io.nats.client.ConsumerContext
    public ConsumerInfo getConsumerInfo() throws IOException, JetStreamApiException {
        NatsStreamContext natsStreamContext = this.streamCtx;
        ConsumerInfo consumerInfo = natsStreamContext.jsm.getConsumerInfo(natsStreamContext.streamName, this.consumerName.get());
        this.cachedConsumerInfo.set(consumerInfo);
        this.consumerName.set(consumerInfo.getName());
        return consumerInfo;
    }

    @Override // io.nats.client.BaseConsumerContext
    public String getConsumerName() {
        return this.consumerName.get();
    }

    @Override // io.nats.client.BaseConsumerContext
    public IterableConsumer iterate() throws IOException, JetStreamApiException {
        return iterate(ConsumeOptions.DEFAULT_CONSUME_OPTIONS);
    }

    @Override // io.nats.client.BaseConsumerContext
    public Message next() throws JetStreamStatusCheckedException, InterruptedException, IOException, JetStreamApiException {
        return next(30000L);
    }

    @Override // io.nats.client.impl.SimplifiedSubscriptionMaker
    public NatsJetStreamPullSubscription subscribe(MessageHandler messageHandler, Dispatcher dispatcher, PullMessageManager pullMessageManager, Long l) throws IOException, JetStreamApiException {
        PullSubscribeOptions pullSubscribeOptionsBuild;
        NatsJetStreamPullSubscription natsJetStreamPullSubscription;
        if (this.ordered) {
            NatsMessageConsumerBase natsMessageConsumerBase = this.lastConsumer.get();
            if (natsMessageConsumerBase != null) {
                AtomicLong atomicLong = this.highestSeq;
                atomicLong.set(Math.max(atomicLong.get(), natsMessageConsumerBase.pmm.lastStreamSeq));
            }
            pullSubscribeOptionsBuild = new OrderedPullSubscribeOptionsBuilder(this.streamCtx.streamName, this.streamCtx.js.consumerConfigurationForOrdered(this.initialOrderedConsumerConfig, this.highestSeq.get(), null, l).build()).build();
        } else {
            pullSubscribeOptionsBuild = this.unorderedBindPso;
        }
        PullSubscribeOptions pullSubscribeOptions = pullSubscribeOptionsBuild;
        if (messageHandler == null) {
            natsJetStreamPullSubscription = (NatsJetStreamPullSubscription) this.streamCtx.js.createSubscription(null, null, pullSubscribeOptions, null, null, null, false, pullMessageManager);
        } else {
            if (dispatcher == null && (dispatcher = this.defaultDispatcher.get()) == null) {
                dispatcher = this.streamCtx.js.conn.createDispatcher();
                this.defaultDispatcher.set(dispatcher);
            }
            natsJetStreamPullSubscription = (NatsJetStreamPullSubscription) this.streamCtx.js.createSubscription(null, null, pullSubscribeOptions, null, (NatsDispatcher) dispatcher, messageHandler, false, pullMessageManager);
        }
        this.consumerName.set(natsJetStreamPullSubscription.getConsumerName());
        return natsJetStreamPullSubscription;
    }

    @Override // io.nats.client.BaseConsumerContext
    public MessageConsumer consume(Dispatcher dispatcher, MessageHandler messageHandler) throws IOException, JetStreamApiException {
        return consume(ConsumeOptions.DEFAULT_CONSUME_OPTIONS, dispatcher, messageHandler);
    }

    @Override // io.nats.client.BaseConsumerContext
    public IterableConsumer iterate(ConsumeOptions consumeOptions) throws IOException, JetStreamApiException {
        try {
            this.stateLock.lock();
            checkState();
            Validator.required(consumeOptions, "Consume Options");
            return (IterableConsumer) trackConsume(new NatsIterableConsumer(this, this.cachedConsumerInfo.get(), consumeOptions));
        } finally {
            this.stateLock.unlock();
        }
    }

    @Override // io.nats.client.BaseConsumerContext
    public Message next(Duration duration) throws JetStreamStatusCheckedException, InterruptedException, IOException, JetStreamApiException {
        return next(duration == null ? 30000L : duration.toMillis());
    }

    @Override // io.nats.client.BaseConsumerContext
    public MessageConsumer consume(ConsumeOptions consumeOptions, MessageHandler messageHandler) throws IOException, JetStreamApiException {
        return consume(consumeOptions, null, messageHandler);
    }

    @Override // io.nats.client.BaseConsumerContext
    public Message next(long j) throws JetStreamStatusCheckedException, InterruptedException, IOException, JetStreamApiException {
        NatsMessageConsumerBase natsMessageConsumerBase;
        if (j >= 1000) {
            try {
                this.stateLock.lock();
                checkState();
                try {
                    long j2 = (110 * j) / 100;
                    natsMessageConsumerBase = new NatsMessageConsumerBase(this.cachedConsumerInfo.get());
                    try {
                        natsMessageConsumerBase.initSub(subscribe(null, null, null, Long.valueOf(j2)));
                        natsMessageConsumerBase.setConsumerName(this.consumerName.get());
                        trackConsume(natsMessageConsumerBase);
                        natsMessageConsumerBase.sub._pull(PullRequestOptions.builder(1).expiresIn(j - 10).build(), false, null);
                        try {
                            return natsMessageConsumerBase.sub.nextMessage(j);
                        } finally {
                            try {
                                natsMessageConsumerBase.finished.set(true);
                                natsMessageConsumerBase.close();
                            } catch (Exception unused) {
                            }
                        }
                    } catch (Exception unused2) {
                        if (natsMessageConsumerBase != null) {
                            try {
                                natsMessageConsumerBase.close();
                            } catch (Exception unused3) {
                            }
                        }
                        return null;
                    }
                } catch (Exception unused4) {
                    natsMessageConsumerBase = null;
                }
            } finally {
                this.stateLock.unlock();
            }
        } else {
            throw new IllegalArgumentException("Max wait must be at least 1000 milliseconds.");
        }
    }

    @Override // io.nats.client.BaseConsumerContext
    public MessageConsumer consume(ConsumeOptions consumeOptions, Dispatcher dispatcher, MessageHandler messageHandler) throws Throwable {
        NatsConsumerContext natsConsumerContext;
        try {
            this.stateLock.lock();
            checkState();
            Validator.required(messageHandler, "Message Handler");
            Validator.required(consumeOptions, "Consume Options");
            natsConsumerContext = this;
        } catch (Throwable th) {
            th = th;
            natsConsumerContext = this;
        }
        try {
            NatsMessageConsumerBase natsMessageConsumerBaseTrackConsume = trackConsume(new NatsMessageConsumer(natsConsumerContext, this.cachedConsumerInfo.get(), consumeOptions, dispatcher, messageHandler));
            natsConsumerContext.stateLock.unlock();
            return natsMessageConsumerBaseTrackConsume;
        } catch (Throwable th2) {
            th = th2;
            Throwable th3 = th;
            natsConsumerContext.stateLock.unlock();
            throw th3;
        }
    }
}
