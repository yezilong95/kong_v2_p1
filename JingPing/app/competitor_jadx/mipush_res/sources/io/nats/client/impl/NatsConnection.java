package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.AuthenticationException;
import io.nats.client.Connection;
import io.nats.client.ConnectionListener;
import io.nats.client.Consumer;
import io.nats.client.ConsumerContext;
import io.nats.client.Dispatcher;
import io.nats.client.ErrorListener;
import io.nats.client.ForceReconnectOptions;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamManagement;
import io.nats.client.JetStreamOptions;
import io.nats.client.JetStreamStatusException;
import io.nats.client.KeyValue;
import io.nats.client.KeyValueManagement;
import io.nats.client.KeyValueOptions;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.NUID;
import io.nats.client.NatsSystemClock;
import io.nats.client.ObjectStore;
import io.nats.client.ObjectStoreManagement;
import io.nats.client.ObjectStoreOptions;
import io.nats.client.Options;
import io.nats.client.ServerPool;
import io.nats.client.Statistics;
import io.nats.client.StatisticsCollector;
import io.nats.client.StreamContext;
import io.nats.client.Subscription;
import io.nats.client.TimeTraceLogger;
import io.nats.client.api.ServerInfo;
import io.nats.client.support.ByteArrayBuilder;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsRequestCompletableFuture;
import io.nats.client.support.NatsUri;
import io.nats.client.support.ScheduledTask;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
class NatsConnection implements Connection {
    public static final double NANOS_PER_SECOND = 1.0E9d;
    private static final ProtocolMessage PING_PROTO = new ProtocolMessage(NatsConstants.OP_PING_BYTES);
    private static final ProtocolMessage PONG_PROTO = new ProtocolMessage(NatsConstants.OP_PONG_BYTES);
    private final boolean advancedTracking;
    private final AtomicBoolean blockPublishForDrain;
    private final ExecutorService callbackRunner;
    final NatsRequestCompletableFuture.CancelAction cancelAction;
    private ScheduledTask cleanupTask;
    final ReentrantLock closeSocketLock;
    private boolean closing;
    private final AtomicReference<String> connectError;
    private final ExecutorService connectExecutor;
    private boolean connecting;
    private final Collection<ConnectionListener> connectionListeners;
    private NatsUri currentServer;
    private DataPort dataPort;
    private CompletableFuture<DataPort> dataPortFuture;
    private boolean disconnecting;
    private final DispatcherFactory dispatcherFactory;
    private final Map<String, NatsDispatcher> dispatchers;
    private final AtomicReference<CompletableFuture<Boolean>> draining;
    private Exception exceptionDuringConnectChange;
    private final ExecutorService executor;
    final boolean forceFlushOnRequest;
    private final AtomicReference<NatsDispatcher> inboxDispatcher;
    private final ReentrantLock inboxDispatcherLock;
    private final AtomicReference<String> lastError;
    private final String mainInbox;
    private final AtomicBoolean needPing;
    private final AtomicLong nextSid;
    private final NUID nuid;
    private final Options options;
    private ScheduledTask pingTask;
    private final ConcurrentLinkedDeque<CompletableFuture<Boolean>> pongQueue;
    private NatsConnectionReader reader;
    private CompletableFuture<Boolean> reconnectWaiter;
    private final Map<String, NatsRequestCompletableFuture> responsesAwaiting;
    private final Map<String, NatsRequestCompletableFuture> responsesRespondedTo;
    private final ScheduledExecutorService scheduledExecutor;
    private final HashMap<NatsUri, String> serverAuthErrors;
    private final AtomicReference<ServerInfo> serverInfo;
    private final ServerPool serverPool;
    private final StatisticsCollector statistics;
    private Connection.Status status;
    private final Condition statusChanged;
    private final ReentrantLock statusLock;
    private final Map<String, NatsSubscription> subscribers;
    private final TimeTraceLogger timeTraceLogger;
    private final boolean trace;
    private final AtomicBoolean tryingToConnect;
    private NatsConnectionWriter writer;

    /* JADX INFO: renamed from: io.nats.client.impl.NatsConnection$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$nats$client$support$NatsRequestCompletableFuture$CancelAction;

        static {
            int[] iArr = new int[NatsRequestCompletableFuture.CancelAction.values().length];
            $SwitchMap$io$nats$client$support$NatsRequestCompletableFuture$CancelAction = iArr;
            try {
                iArr[NatsRequestCompletableFuture.CancelAction.COMPLETE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$nats$client$support$NatsRequestCompletableFuture$CancelAction[NatsRequestCompletableFuture.CancelAction.REPORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$nats$client$support$NatsRequestCompletableFuture$CancelAction[NatsRequestCompletableFuture.CancelAction.CANCEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public interface ErrorListenerCaller {
        void call(Connection connection, ErrorListener errorListener);
    }

    public NatsConnection(Options options) {
        this.trace = options.isTraceConnection();
        TimeTraceLogger timeTraceLogger = options.getTimeTraceLogger();
        this.timeTraceLogger = timeTraceLogger;
        timeTraceLogger.trace("creating connection object", new Object[0]);
        this.options = options;
        this.forceFlushOnRequest = options.forceFlushOnRequest();
        boolean zIsTrackAdvancedStats = options.isTrackAdvancedStats();
        this.advancedTracking = zIsTrackAdvancedStats;
        StatisticsCollector natsStatistics = options.getStatisticsCollector() == null ? new NatsStatistics() : options.getStatisticsCollector();
        this.statistics = natsStatistics;
        natsStatistics.setAdvancedTracking(zIsTrackAdvancedStats);
        this.closeSocketLock = new ReentrantLock();
        ReentrantLock reentrantLock = new ReentrantLock();
        this.statusLock = reentrantLock;
        this.statusChanged = reentrantLock.newCondition();
        this.status = Connection.Status.DISCONNECTED;
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        this.reconnectWaiter = completableFuture;
        completableFuture.complete(Boolean.TRUE);
        this.connectionListeners = ConcurrentHashMap.newKeySet();
        if (options.getConnectionListener() != null) {
            addConnectionListener(options.getConnectionListener());
        }
        this.dispatchers = new ConcurrentHashMap();
        this.subscribers = new ConcurrentHashMap();
        this.responsesAwaiting = new ConcurrentHashMap();
        this.responsesRespondedTo = new ConcurrentHashMap();
        this.serverAuthErrors = new HashMap<>();
        this.nextSid = new AtomicLong(1L);
        timeTraceLogger.trace("creating NUID", new Object[0]);
        this.nuid = new NUID();
        this.mainInbox = zn.o(new StringBuilder(), createInbox(), ".*");
        this.lastError = new AtomicReference<>();
        this.connectError = new AtomicReference<>();
        this.serverInfo = new AtomicReference<>();
        this.inboxDispatcher = new AtomicReference<>();
        this.inboxDispatcherLock = new ReentrantLock();
        this.pongQueue = new ConcurrentLinkedDeque<>();
        this.draining = new AtomicReference<>();
        this.blockPublishForDrain = new AtomicBoolean();
        this.tryingToConnect = new AtomicBoolean();
        timeTraceLogger.trace("creating executors", new Object[0]);
        this.executor = options.getExecutor();
        this.callbackRunner = options.getCallbackExecutor();
        this.connectExecutor = options.getConnectExecutor();
        this.scheduledExecutor = options.getScheduledExecutor();
        timeTraceLogger.trace("creating reader and writer", new Object[0]);
        this.reader = new NatsConnectionReader(this);
        this.writer = new NatsConnectionWriter(this, null);
        this.needPing = new AtomicBoolean(true);
        ServerPool natsServerPool = options.getServerPool() == null ? new NatsServerPool() : options.getServerPool();
        this.serverPool = natsServerPool;
        natsServerPool.initialize(options);
        this.dispatcherFactory = options.getDispatcherFactory() == null ? new DispatcherFactory() : options.getDispatcherFactory();
        this.cancelAction = options.isReportNoResponders() ? NatsRequestCompletableFuture.CancelAction.REPORT : NatsRequestCompletableFuture.CancelAction.CANCEL;
        timeTraceLogger.trace("connection object created", new Object[0]);
    }

    private void checkPayloadSize(byte[] bArr) {
        if (!this.options.clientSideLimitChecks() || bArr == null || bArr.length <= getMaxPayload() || getMaxPayload() <= 0) {
            return;
        }
        throw new IllegalArgumentException("Message payload size exceed server configuration " + bArr.length + " vs " + getMaxPayload());
    }

    private void ensureNotClosing() throws IOException {
        if (isClosing() || isClosed()) {
            throw new IOException("A JetStream context can't be established during close.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$drain$17(NatsSubscription natsSubscription) {
        return natsSubscription.getDispatcher() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$drain$18(CompletableFuture completableFuture, NatsConsumer natsConsumer) {
        natsConsumer.markDraining(completableFuture);
        natsConsumer.sendUnsubForDrain();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:16:0x002b
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:226)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:196)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:63)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    public /* synthetic */ void lambda$drain$19(java.time.Duration r6, java.util.HashSet r7, java.time.Instant r8, java.util.concurrent.CompletableFuture r9) throws java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsConnection.lambda$drain$19(java.time.Duration, java.util.HashSet, java.time.Instant, java.util.concurrent.CompletableFuture):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeCallback$13(ErrorListenerCaller errorListenerCaller) {
        errorListenerCaller.call(this, this.options.getErrorListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$forceReconnectImpl$0(ForceReconnectOptions forceReconnectOptions, DataPort dataPort) {
        if (forceReconnectOptions != null) {
            try {
                if (forceReconnectOptions.isForceClose()) {
                    dataPort.forceClose();
                    return;
                }
            } catch (IOException unused) {
                return;
            }
        }
        dataPort.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleCommunicationIssue$6() throws ExecutionException, InterruptedException, URISyntaxException, TimeoutException, IOException {
        if (this.tryingToConnect.get()) {
            return;
        }
        try {
            this.tryingToConnect.set(true);
            closeSocket(true, true);
        } catch (InterruptedException e) {
            processException(e);
            Thread.currentThread().interrupt();
        } finally {
            this.tryingToConnect.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processConnectionEvent$14(ConnectionListener connectionListener, ConnectionListener.Events events) {
        try {
            connectionListener.connectionEvent(this, events);
        } catch (Exception unused) {
            this.statistics.incrementExceptionCount();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processError$12(String str) {
        try {
            this.options.getErrorListener().errorOccurred(this, str);
        } catch (Exception unused) {
            this.statistics.incrementExceptionCount();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processException$11(Exception exc) {
        try {
            this.options.getErrorListener().exceptionOccurred(this, exc);
        } catch (Exception unused) {
            this.statistics.incrementExceptionCount();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processSlowConsumer$10(Consumer consumer) {
        try {
            this.options.getErrorListener().slowConsumerDetected(this, consumer);
        } catch (Exception unused) {
            this.statistics.incrementExceptionCount();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reconnectImpl$1(String str, NatsSubscription natsSubscription) {
        if (natsSubscription.getDispatcher() != null || natsSubscription.isDraining()) {
            return;
        }
        sendSubscriptionMessage(natsSubscription.getSID(), natsSubscription.getSubject(), natsSubscription.getQueueName(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$reconnectImpl$2(String str, NatsDispatcher natsDispatcher) {
        if (natsDispatcher.isDraining()) {
            return;
        }
        natsDispatcher.resendSubscriptions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestFutureInternal$9(NatsDispatcher natsDispatcher, String str, Message message, Throwable th) {
        if (th instanceof CancellationException) {
            natsDispatcher.unsubscribe(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$tryToConnect$3(NatsUri natsUri, boolean z) throws Exception {
        if (!this.options.isTlsFirst()) {
            readInitialInfo();
            checkVersionRequirements();
        }
        long jNanoTime = NatsSystemClock.nanoTime();
        upgradeToSecureIfNeeded(natsUri);
        if (z && this.options.isTLSRequired()) {
            this.timeTraceLogger.trace("TLS upgrade took: %.3f (s)", Double.valueOf((NatsSystemClock.nanoTime() - jNanoTime) / 1.0E9d));
        }
        if (!this.options.isTlsFirst()) {
            return null;
        }
        readInitialInfo();
        checkVersionRequirements();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$tryToConnect$4() {
        if (!isConnected() || isClosing()) {
            return;
        }
        try {
            softPing();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$tryToConnect$5() throws ExecutionException, InterruptedException {
        cleanResponses(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$waitForConnectOrClose$16(Void r1) {
        return (isConnected() || isClosed()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$waitForDisconnectOrClose$15(Void r1) {
        return isDisconnecting() && !isClosed();
    }

    @Override // io.nats.client.Connection
    public Duration RTT() throws IOException {
        if (!isConnectedOrConnecting()) {
            throw new IOException("Must be connected to do RTT.");
        }
        long millis = this.options.getConnectionTimeout().toMillis();
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        this.pongQueue.add(completableFuture);
        try {
            long jNanoTime = NatsSystemClock.nanoTime();
            this.writer.queueInternalMessage(new ProtocolMessage(PING_PROTO));
            completableFuture.get(millis, TimeUnit.MILLISECONDS);
            return Duration.ofNanos(NatsSystemClock.nanoTime() - jNanoTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException(e);
        } catch (ExecutionException e2) {
            throw new IOException(e2.getCause());
        } catch (TimeoutException e3) {
            throw new IOException(e3);
        }
    }

    @Override // io.nats.client.Connection
    public void addConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.add(connectionListener);
    }

    public void checkVersionRequirements() throws IOException {
        Options options = getOptions();
        ServerInfo info = getInfo();
        if (options.isNoEcho() && info.getProtocolVersion() < 1) {
            throw new IOException("Server does not support no echo.");
        }
    }

    public void cleanResponses(boolean z) throws ExecutionException, InterruptedException {
        boolean z2;
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, NatsRequestCompletableFuture> entry : this.responsesAwaiting.entrySet()) {
            NatsRequestCompletableFuture value = entry.getValue();
            if (value.hasExceededTimeout()) {
                value.cancelTimedOut();
            } else if (z) {
                value.cancelClosing();
            } else if (value.isDone()) {
                try {
                    value.get();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    z2 = true;
                } catch (Throwable unused2) {
                }
            }
            arrayList.add(entry.getKey());
            this.statistics.decrementOutstandingRequests();
        }
        z2 = false;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.responsesAwaiting.remove((String) it.next());
        }
        if (!this.advancedTracking || z2) {
            return;
        }
        arrayList.clear();
        for (Map.Entry<String, NatsRequestCompletableFuture> entry2 : this.responsesRespondedTo.entrySet()) {
            NatsRequestCompletableFuture value2 = entry2.getValue();
            if (value2.hasExceededTimeout()) {
                arrayList.add(entry2.getKey());
                value2.cancelTimedOut();
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.responsesRespondedTo.remove((String) it2.next());
        }
    }

    public void cleanUpPongQueue() {
        while (true) {
            CompletableFuture<Boolean> completableFuturePoll = this.pongQueue.poll();
            if (completableFuturePoll == null) {
                return;
            } else {
                completableFuturePoll.cancel(true);
            }
        }
    }

    public void cleanupDispatcher(NatsDispatcher natsDispatcher) {
        natsDispatcher.stop(true);
        this.dispatchers.remove(natsDispatcher.getId());
    }

    @Override // io.nats.client.Connection
    public void clearLastError() {
        this.lastError.set("");
    }

    @Override // io.nats.client.Connection, java.lang.AutoCloseable
    public void close() throws ExecutionException, InterruptedException, TimeoutException, IOException {
        close(true, false);
    }

    @Override // io.nats.client.Connection
    public void closeDispatcher(Dispatcher dispatcher) {
        if (isClosed()) {
            throw new IllegalStateException("Connection is Closed");
        }
        if (!(dispatcher instanceof NatsDispatcher)) {
            throw new IllegalArgumentException("Connection can only manage its own dispatchers");
        }
        NatsDispatcher natsDispatcher = (NatsDispatcher) dispatcher;
        if (natsDispatcher.isDraining()) {
            return;
        }
        if (!this.dispatchers.containsKey(natsDispatcher.getId())) {
            throw new IllegalArgumentException("Dispatcher is already closed.");
        }
        cleanupDispatcher(natsDispatcher);
    }

    public void closeSocket(boolean z, boolean z2) throws ExecutionException, InterruptedException, URISyntaxException, TimeoutException, IOException {
        this.closeSocketLock.lock();
        try {
            this.statusLock.lock();
            try {
                if (isDisconnectingOrClosed()) {
                    waitForDisconnectOrClose(this.options.getConnectionTimeout());
                } else {
                    boolean z3 = true;
                    this.disconnecting = true;
                    this.exceptionDuringConnectChange = null;
                    if (this.status != Connection.Status.CONNECTED) {
                        z3 = false;
                    }
                    this.statusChanged.signalAll();
                    this.statusLock.unlock();
                    closeSocketImpl(z2);
                    this.statusLock.lock();
                    try {
                        updateStatus(Connection.Status.DISCONNECTED);
                        this.exceptionDuringConnectChange = null;
                        this.disconnecting = false;
                        this.statusChanged.signalAll();
                        this.statusLock.unlock();
                        if (isClosing()) {
                            close();
                        } else if (z3 && z) {
                            reconnectImpl();
                        }
                    } finally {
                    }
                }
            } finally {
            }
        } finally {
            this.closeSocketLock.unlock();
        }
    }

    public void closeSocketImpl(boolean z) throws ExecutionException, InterruptedException, TimeoutException, IOException {
        this.currentServer = null;
        Future<Boolean> futureStop = this.reader.stop();
        Future<Boolean> futureStop2 = this.writer.stop();
        try {
            futureStop.get(1L, TimeUnit.SECONDS);
        } catch (Exception unused) {
        }
        try {
            futureStop2.get(1L, TimeUnit.SECONDS);
        } catch (Exception unused2) {
        }
        CompletableFuture<DataPort> completableFuture = this.dataPortFuture;
        if (completableFuture != null) {
            completableFuture.cancel(true);
            this.dataPortFuture = null;
        }
        try {
            DataPort dataPort = this.dataPort;
            if (dataPort != null) {
                if (z) {
                    dataPort.forceClose();
                } else {
                    dataPort.close();
                }
            }
        } catch (IOException e) {
            processException(e);
        }
        cleanUpPongQueue();
        try {
            this.reader.stop().get(10L, TimeUnit.SECONDS);
        } catch (Exception e2) {
            processException(e2);
        }
        try {
            this.writer.stop().get(10L, TimeUnit.SECONDS);
        } catch (Exception e3) {
            processException(e3);
        }
    }

    public void connect(boolean z) throws InterruptedException, IOException {
        if (this.tryingToConnect.get()) {
            return;
        }
        try {
            this.tryingToConnect.set(true);
            connectImpl(z);
        } finally {
            this.tryingToConnect.set(false);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void connectImpl(boolean z) throws ExecutionException, InterruptedException, URISyntaxException, TimeoutException, IOException {
        if (this.options.getServers().isEmpty()) {
            throw new IllegalArgumentException("No servers provided in options");
        }
        boolean zIsTraceConnection = this.options.isTraceConnection();
        long jNanoTime = NatsSystemClock.nanoTime();
        this.lastError.set("");
        this.timeTraceLogger.trace("starting connect loop", new Object[0]);
        HashSet hashSet = new HashSet();
        boolean z2 = true;
        Object obj = null;
        while (z2) {
            NatsUri natsUriPeekNextServer = this.serverPool.peekNextServer();
            if (natsUriPeekNextServer == null) {
                break;
            }
            if (obj != null) {
                boolean zEquals = natsUriPeekNextServer.equals(obj);
                obj = obj;
                if (zEquals) {
                    break;
                }
            } else {
                obj = natsUriPeekNextServer;
            }
            this.serverPool.nextServer();
            for (NatsUri natsUri : resolveHost(natsUriPeekNextServer)) {
                if (!isClosed()) {
                    this.connectError.set("");
                    this.timeTraceLogger.trace("setting status to connecting", new Object[0]);
                    updateStatus(Connection.Status.CONNECTING);
                    this.timeTraceLogger.trace("trying to connect to %s", natsUriPeekNextServer);
                    tryToConnect(natsUriPeekNextServer, natsUri, NatsSystemClock.nanoTime());
                    if (isConnected()) {
                        this.serverPool.connectSucceeded(natsUriPeekNextServer);
                    } else {
                        this.timeTraceLogger.trace("setting status to disconnected", new Object[0]);
                        updateStatus(Connection.Status.DISCONNECTED);
                        hashSet.add(natsUriPeekNextServer);
                        this.serverPool.connectFailed(natsUriPeekNextServer);
                        String str = this.connectError.get();
                        if (isAuthenticationError(str)) {
                            this.serverAuthErrors.put(natsUri, str);
                        }
                    }
                }
                z2 = false;
            }
        }
        if (isConnected() || isClosed()) {
            if (zIsTraceConnection) {
                this.timeTraceLogger.trace("connect complete in %.3f seconds", Double.valueOf((NatsSystemClock.nanoTime() - jNanoTime) / 1.0E9d));
                return;
            }
            return;
        }
        if (z) {
            this.timeTraceLogger.trace("trying to reconnect on connect", new Object[0]);
            reconnectImpl();
            return;
        }
        this.timeTraceLogger.trace("connection failed, closing to cleanup", new Object[0]);
        close();
        String str2 = this.connectError.get();
        if (isAuthenticationError(str2)) {
            throw new AuthenticationException(zn.v("Authentication error connecting to NATS server: ", str2));
        }
        throw new IOException("Unable to connect to NATS servers: " + hashSet);
    }

    @Override // io.nats.client.Connection
    public Dispatcher createDispatcher() {
        return createDispatcher(null);
    }

    @Override // io.nats.client.Connection
    public String createInbox() {
        return this.options.getInboxPrefix() + this.nuid.next();
    }

    public String createResponseInbox(String str) {
        return str.substring(0, getRespInboxLength()) + this.nuid.next();
    }

    public NatsSubscription createSubscription(String str, String str2, NatsDispatcher natsDispatcher, NatsSubscriptionFactory natsSubscriptionFactory) {
        NatsConnection natsConnection;
        String str3;
        String str4;
        NatsSubscription natsSubscriptionCreateNatsSubscription;
        if (isClosed()) {
            throw new IllegalStateException("Connection is Closed");
        }
        if (isDraining() && (natsDispatcher == null || natsDispatcher != this.inboxDispatcher.get())) {
            throw new IllegalStateException("Connection is Draining");
        }
        String nextSid = getNextSid();
        if (natsSubscriptionFactory == null) {
            natsConnection = this;
            str3 = str;
            str4 = str2;
            natsSubscriptionCreateNatsSubscription = new NatsSubscription(nextSid, str3, str4, natsConnection, natsDispatcher);
        } else {
            natsConnection = this;
            str3 = str;
            str4 = str2;
            natsSubscriptionCreateNatsSubscription = natsSubscriptionFactory.createNatsSubscription(nextSid, str3, str4, natsConnection, natsDispatcher);
        }
        natsConnection.subscribers.put(nextSid, natsSubscriptionCreateNatsSubscription);
        sendSubscriptionMessage(nextSid, str3, str4, false);
        return natsSubscriptionCreateNatsSubscription;
    }

    public void deliverMessage(NatsMessage natsMessage) {
        this.needPing.set(false);
        this.statistics.incrementInMsgs();
        this.statistics.incrementInBytes(natsMessage.getSizeInBytes());
        NatsSubscription natsSubscription = this.subscribers.get(natsMessage.getSID());
        if (natsSubscription != null) {
            natsMessage.setSubscription(natsSubscription);
            NatsDispatcher natsDispatcher = natsSubscription.getNatsDispatcher();
            NatsSubscription natsSubscription2 = natsDispatcher == null ? natsSubscription : natsDispatcher;
            MessageQueue messageQueue = natsDispatcher == null ? natsSubscription.getMessageQueue() : natsDispatcher.getMessageQueue();
            if (natsSubscription2.hasReachedPendingLimits()) {
                this.statistics.incrementDroppedCount();
                natsSubscription2.incrementDroppedCount();
                if (natsSubscription2.isMarkedSlow()) {
                    return;
                }
                natsSubscription2.markSlow();
                processSlowConsumer(natsSubscription2);
                return;
            }
            if (messageQueue != null) {
                natsSubscription2.markNotSlow();
                if (natsSubscription.getBeforeQueueProcessor().apply(natsMessage).booleanValue()) {
                    messageQueue.push(natsMessage);
                }
            }
        }
    }

    public void deliverReply(Message message) {
        int i;
        boolean zIsOldRequestStyle = this.options.isOldRequestStyle();
        String subject = message.getSubject();
        String responseToken = getResponseToken(subject);
        if (zIsOldRequestStyle) {
            responseToken = message.getSID();
        }
        NatsRequestCompletableFuture natsRequestCompletableFutureRemove = this.responsesAwaiting.remove(responseToken);
        if (natsRequestCompletableFutureRemove == null) {
            if (zIsOldRequestStyle || subject.startsWith(this.mainInbox) || !this.advancedTracking) {
                return;
            }
            if (this.responsesRespondedTo.get(responseToken) != null) {
                this.statistics.incrementDuplicateRepliesReceived();
                return;
            } else {
                this.statistics.incrementOrphanRepliesReceived();
                return;
            }
        }
        if (this.advancedTracking) {
            this.responsesRespondedTo.put(responseToken, natsRequestCompletableFutureRemove);
        }
        this.statistics.decrementOutstandingRequests();
        if (!message.isStatusMessage() || message.getStatus().getCode() != 503 || (i = AnonymousClass1.$SwitchMap$io$nats$client$support$NatsRequestCompletableFuture$CancelAction[natsRequestCompletableFutureRemove.getCancelAction().ordinal()]) == 1) {
            natsRequestCompletableFutureRemove.complete(message);
        } else if (i != 2) {
            natsRequestCompletableFutureRemove.cancel(true);
        } else {
            natsRequestCompletableFutureRemove.completeExceptionally(new JetStreamStatusException(message.getStatus()));
        }
        this.statistics.incrementRepliesReceived();
    }

    @Override // io.nats.client.Connection
    public CompletableFuture<Boolean> drain(final Duration duration) throws Exception {
        NatsConnection natsConnection;
        Throwable th;
        if (isClosing() || isClosed()) {
            throw new IllegalStateException("A connection can't be drained during close.");
        }
        this.statusLock.lock();
        try {
            if (!isDraining()) {
                this.draining.set(new CompletableFuture<>());
                this.statusLock.unlock();
                final CompletableFuture<Boolean> completableFuture = this.draining.get();
                final Instant instantNow = Instant.now();
                HashSet hashSet = new HashSet(this.subscribers.values());
                hashSet.removeIf(new f(0));
                final HashSet hashSet2 = new HashSet();
                hashSet2.addAll(hashSet);
                hashSet2.addAll(this.dispatchers.values());
                NatsDispatcher natsDispatcher = this.inboxDispatcher.get();
                if (natsDispatcher != null) {
                    hashSet2.add(natsDispatcher);
                }
                hashSet2.forEach(new java.util.function.Consumer() { // from class: io.nats.client.impl.g
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        NatsConnection.lambda$drain$18(completableFuture, (NatsConsumer) obj);
                    }
                });
                try {
                    flush(duration);
                    hashSet2.forEach(new h(0));
                    this.executor.submit(new Runnable() { // from class: io.nats.client.impl.i
                        @Override // java.lang.Runnable
                        public final void run() throws ExecutionException, TimeoutException, IOException {
                            this.E.lambda$drain$19(duration, hashSet2, instantNow, completableFuture);
                        }
                    });
                    return completableFuture;
                } catch (Exception e) {
                    close(false, false);
                    throw e;
                }
            }
            try {
                CompletableFuture<Boolean> completableFuture2 = this.draining.get();
                this.statusLock.unlock();
                return completableFuture2;
            } catch (Throwable th2) {
                th = th2;
                natsConnection = this;
            }
        } catch (Throwable th3) {
            natsConnection = this;
            th = th3;
        }
        natsConnection.statusLock.unlock();
        throw th;
    }

    public ByteBuffer enlargeBuffer(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.capacity() * 2);
        byteBuffer.flip();
        byteBufferAllocate.put(byteBuffer);
        return byteBufferAllocate;
    }

    public void executeCallback(ErrorListenerCaller errorListenerCaller) {
        if (this.callbackRunner.isShutdown()) {
            return;
        }
        try {
            this.callbackRunner.execute(new b(this, 0, errorListenerCaller));
        } catch (RejectedExecutionException unused) {
        }
    }

    @Override // io.nats.client.Connection
    public void flush(Duration duration) throws ExecutionException, InterruptedException, TimeoutException {
        Instant instantNow = Instant.now();
        waitForConnectOrClose(duration);
        if (isClosed()) {
            throw new TimeoutException("Attempted to flush while closed");
        }
        if (duration == null) {
            duration = Duration.ZERO;
        }
        Duration durationBetween = Duration.between(instantNow, Instant.now());
        Duration unused = Duration.ZERO;
        if (!duration.equals(Duration.ZERO) && durationBetween.compareTo(duration) >= 0) {
            throw new TimeoutException("Timeout out waiting for connection before flush.");
        }
        try {
            CompletableFuture<Boolean> completableFutureSendPing = sendPing();
            if (completableFutureSendPing == null) {
                return;
            }
            long nanos = duration.toNanos();
            if (nanos > 0) {
                long nanos2 = nanos - durationBetween.toNanos();
                if (nanos2 <= 0) {
                    nanos2 = 1;
                }
                completableFutureSendPing.get(nanos2, TimeUnit.NANOSECONDS);
            } else {
                completableFutureSendPing.get();
            }
            this.statistics.incrementFlushCounter();
        } catch (CancellationException e) {
            e = e;
            throw new TimeoutException(e.toString());
        } catch (ExecutionException e2) {
            e = e2;
            throw new TimeoutException(e.toString());
        }
    }

    @Override // io.nats.client.Connection
    public void flushBuffer() throws IOException {
        if (!isConnected()) {
            throw new IllegalStateException("Connection is not active.");
        }
        this.writer.flushBuffer();
    }

    @Override // io.nats.client.Connection
    public void forceReconnect() throws InterruptedException, IOException {
        forceReconnect(null);
    }

    public void forceReconnectImpl(ForceReconnectOptions forceReconnectOptions) throws ExecutionException, InterruptedException, URISyntaxException, TimeoutException, IOException {
        if (forceReconnectOptions != null && forceReconnectOptions.getFlushWait() != null) {
            try {
                flush(forceReconnectOptions.getFlushWait());
            } catch (TimeoutException unused) {
            }
        }
        this.closeSocketLock.lock();
        try {
            updateStatus(Connection.Status.DISCONNECTED);
            CompletableFuture<DataPort> completableFuture = this.dataPortFuture;
            if (completableFuture != null) {
                completableFuture.cancel(true);
                this.dataPortFuture = null;
            }
            DataPort dataPort = this.dataPort;
            if (dataPort != null) {
                this.dataPort = null;
                this.executor.submit(new b(forceReconnectOptions, 4, dataPort));
            }
            try {
                this.reader.stop(false).get(100L, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                processException(e);
            }
            try {
                this.writer.stop().get(100L, TimeUnit.MILLISECONDS);
            } catch (Exception e2) {
                processException(e2);
            }
            this.reader = new NatsConnectionReader(this);
            this.writer = new NatsConnectionWriter(this, this.writer);
            this.closeSocketLock.unlock();
            reconnectImpl();
            this.writer.setReconnectMode(false);
        } catch (Throwable th) {
            this.closeSocketLock.unlock();
            throw th;
        }
    }

    @Override // io.nats.client.Connection
    public InetAddress getClientInetAddress() {
        try {
            return InetAddress.getByName(getInfo().getClientIp());
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // io.nats.client.Connection
    public String getConnectedUrl() {
        NatsUri natsUri = this.currentServer;
        if (natsUri == null) {
            return null;
        }
        return natsUri.toString();
    }

    @Override // io.nats.client.Connection
    public ConsumerContext getConsumerContext(String str, String str2) throws IOException, JetStreamApiException {
        return getStreamContext(str).getConsumerContext(str2);
    }

    public int getConsumerCount() {
        return this.dispatchers.size() + this.subscribers.size();
    }

    public DataPort getDataPort() {
        return this.dataPort;
    }

    public Future<DataPort> getDataPortFuture() {
        return this.dataPortFuture;
    }

    public Map<String, Dispatcher> getDispatchers() {
        return Collections.unmodifiableMap(this.dispatchers);
    }

    public ExecutorService getExecutor() {
        return this.executor;
    }

    public ServerInfo getInfo() {
        return this.serverInfo.get();
    }

    @Override // io.nats.client.Connection
    public String getLastError() {
        return this.lastError.get();
    }

    @Override // io.nats.client.Connection
    public long getMaxPayload() {
        ServerInfo serverInfo = this.serverInfo.get();
        if (serverInfo == null) {
            return -1L;
        }
        return serverInfo.getMaxPayload();
    }

    public StatisticsCollector getNatsStatistics() {
        return this.statistics;
    }

    public String getNextSid() {
        return Long.toString(this.nextSid.getAndIncrement());
    }

    @Override // io.nats.client.Connection
    public Options getOptions() {
        return this.options;
    }

    public NatsConnectionReader getReader() {
        return this.reader;
    }

    public int getRespInboxLength() {
        return this.options.getInboxPrefix().length() + 23;
    }

    public String getResponseToken(String str) {
        int respInboxLength = getRespInboxLength();
        return str.length() <= respInboxLength ? str : str.substring(respInboxLength);
    }

    public ScheduledExecutorService getScheduledExecutor() {
        return this.scheduledExecutor;
    }

    @Override // io.nats.client.Connection
    public ServerInfo getServerInfo() {
        return getInfo();
    }

    @Override // io.nats.client.Connection
    public Collection<String> getServers() {
        return this.serverPool.getServerList();
    }

    @Override // io.nats.client.Connection
    public Statistics getStatistics() {
        return this.statistics;
    }

    @Override // io.nats.client.Connection
    public Connection.Status getStatus() {
        return this.status;
    }

    @Override // io.nats.client.Connection
    public StreamContext getStreamContext(String str) throws IOException, JetStreamApiException {
        Validator.validateStreamName(str, true);
        ensureNotClosing();
        return new NatsStreamContext(str, null, this, null);
    }

    public NatsConnectionWriter getWriter() {
        return this.writer;
    }

    public void handleCommunicationIssue(Exception exc) {
        this.statusLock.lock();
        try {
            if (this.connecting || this.disconnecting || this.status == Connection.Status.CLOSED || isDraining()) {
                this.exceptionDuringConnectChange = exc;
                return;
            }
            this.statusLock.unlock();
            processException(exc);
            this.executor.submit(new e(2, this));
        } finally {
            this.statusLock.unlock();
        }
    }

    public void handleInfo(String str) {
        ServerInfo serverInfo = new ServerInfo(str);
        this.serverInfo.set(serverInfo);
        List<String> connectURLs = this.serverInfo.get().getConnectURLs();
        if (connectURLs != null && !connectURLs.isEmpty() && this.serverPool.acceptDiscoveredUrls(connectURLs)) {
            processConnectionEvent(ConnectionListener.Events.DISCOVERED_SERVERS);
        }
        if (serverInfo.isLameDuckMode()) {
            processConnectionEvent(ConnectionListener.Events.LAME_DUCK);
        }
    }

    public void handlePong() {
        CompletableFuture<Boolean> completableFuturePollFirst = this.pongQueue.pollFirst();
        if (completableFuturePollFirst != null) {
            completableFuturePollFirst.complete(Boolean.TRUE);
        }
    }

    public void invalidate(NatsSubscription natsSubscription) {
        remove(natsSubscription);
        natsSubscription.invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void invokeReconnectDelayHandler(long r8) throws java.util.concurrent.ExecutionException, java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            r7 = this;
            io.nats.client.Options r0 = r7.options
            io.nats.client.ReconnectDelayHandler r0 = r0.getReconnectDelayHandler()
            r1 = 0
            if (r0 != 0) goto L3b
            io.nats.client.Options r8 = r7.options
            java.time.Duration r8 = r8.getReconnectWait()
            if (r8 == 0) goto L46
            long r8 = defpackage.oj0.a(r8)
            io.nats.client.ServerPool r0 = r7.serverPool
            boolean r0 = r0.hasSecureServer()
            if (r0 == 0) goto L25
            io.nats.client.Options r0 = r7.options
            java.time.Duration r0 = r0.getReconnectJitterTls()
            goto L2b
        L25:
            io.nats.client.Options r0 = r7.options
            java.time.Duration r0 = r0.getReconnectJitter()
        L2b:
            if (r0 == 0) goto L47
            java.util.concurrent.ThreadLocalRandom r3 = java.util.concurrent.ThreadLocalRandom.current()
            long r4 = defpackage.oj0.a(r0)
            long r3 = r3.nextLong(r4)
            long r8 = r8 + r3
            goto L47
        L3b:
            java.time.Duration r8 = r0.getWaitTime(r8)
            if (r8 == 0) goto L46
            long r8 = defpackage.oj0.a(r8)
            goto L47
        L46:
            r8 = r1
        L47:
            java.util.concurrent.CompletableFuture r0 = new java.util.concurrent.CompletableFuture
            r0.<init>()
            r7.reconnectWaiter = r0
            long r3 = io.nats.client.NatsSystemClock.nanoTime()
        L52:
            int r0 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r0 <= 0) goto L7a
            boolean r0 = r7.isDisconnectingOrClosed()
            if (r0 != 0) goto L7a
            boolean r0 = r7.isConnected()
            if (r0 != 0) goto L7a
            java.util.concurrent.CompletableFuture<java.lang.Boolean> r0 = r7.reconnectWaiter
            boolean r0 = r0.isDone()
            if (r0 != 0) goto L7a
            java.util.concurrent.CompletableFuture<java.lang.Boolean> r0 = r7.reconnectWaiter     // Catch: java.lang.Exception -> L71
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch: java.lang.Exception -> L71
            r0.get(r8, r5)     // Catch: java.lang.Exception -> L71
        L71:
            long r5 = io.nats.client.NatsSystemClock.nanoTime()
            long r3 = r5 - r3
            long r8 = r8 - r3
            r3 = r5
            goto L52
        L7a:
            java.util.concurrent.CompletableFuture<java.lang.Boolean> r8 = r7.reconnectWaiter
            java.lang.Boolean r9 = java.lang.Boolean.TRUE
            r8.complete(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsConnection.invokeReconnectDelayHandler(long):void");
    }

    public boolean isAuthenticationError(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.startsWith("user authentication") || lowerCase.contains("authorization violation") || lowerCase.startsWith("account authentication expired");
    }

    public boolean isClosed() {
        return this.status == Connection.Status.CLOSED;
    }

    public boolean isClosing() {
        return this.closing;
    }

    public boolean isConnected() {
        return this.status == Connection.Status.CONNECTED;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isConnectedOrConnecting() {
        /*
            r2 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r2.statusLock
            r0.lock()
            io.nats.client.Connection$Status r0 = r2.status     // Catch: java.lang.Throwable -> L12
            io.nats.client.Connection$Status r1 = io.nats.client.Connection.Status.CONNECTED     // Catch: java.lang.Throwable -> L12
            if (r0 == r1) goto L14
            boolean r0 = r2.connecting     // Catch: java.lang.Throwable -> L12
            if (r0 == 0) goto L10
            goto L14
        L10:
            r0 = 0
            goto L15
        L12:
            r0 = move-exception
            goto L1b
        L14:
            r0 = 1
        L15:
            java.util.concurrent.locks.ReentrantLock r1 = r2.statusLock
            r1.unlock()
            return r0
        L1b:
            java.util.concurrent.locks.ReentrantLock r1 = r2.statusLock
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsConnection.isConnectedOrConnecting():boolean");
    }

    public boolean isDisconnected() {
        return this.status == Connection.Status.DISCONNECTED;
    }

    public boolean isDisconnecting() {
        this.statusLock.lock();
        try {
            return this.disconnecting;
        } finally {
            this.statusLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isDisconnectingOrClosed() {
        /*
            r2 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r2.statusLock
            r0.lock()
            io.nats.client.Connection$Status r0 = r2.status     // Catch: java.lang.Throwable -> L12
            io.nats.client.Connection$Status r1 = io.nats.client.Connection.Status.CLOSED     // Catch: java.lang.Throwable -> L12
            if (r0 == r1) goto L14
            boolean r0 = r2.disconnecting     // Catch: java.lang.Throwable -> L12
            if (r0 == 0) goto L10
            goto L14
        L10:
            r0 = 0
            goto L15
        L12:
            r0 = move-exception
            goto L1b
        L14:
            r0 = 1
        L15:
            java.util.concurrent.locks.ReentrantLock r1 = r2.statusLock
            r1.unlock()
            return r0
        L1b:
            java.util.concurrent.locks.ReentrantLock r1 = r2.statusLock
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsConnection.isDisconnectingOrClosed():boolean");
    }

    public boolean isDrained() {
        CompletableFuture<Boolean> completableFuture = this.draining.get();
        if (completableFuture == null) {
            return false;
        }
        try {
            return completableFuture.getNow(Boolean.FALSE).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isDraining() {
        return this.draining.get() != null;
    }

    @Override // io.nats.client.Connection
    public JetStream jetStream() throws IOException {
        ensureNotClosing();
        return new NatsJetStream(this, null);
    }

    @Override // io.nats.client.Connection
    public JetStreamManagement jetStreamManagement() throws IOException {
        ensureNotClosing();
        return new NatsJetStreamManagement(this, null);
    }

    @Override // io.nats.client.Connection
    public KeyValue keyValue(String str) throws IOException {
        Validator.validateBucketName(str, true);
        ensureNotClosing();
        return new NatsKeyValue(this, str, null);
    }

    @Override // io.nats.client.Connection
    public KeyValueManagement keyValueManagement() throws IOException {
        ensureNotClosing();
        return new NatsKeyValueManagement(this, null);
    }

    @Override // io.nats.client.Connection
    public ObjectStore objectStore(String str) throws IOException {
        Validator.validateBucketName(str, true);
        ensureNotClosing();
        return new NatsObjectStore(this, str, null);
    }

    @Override // io.nats.client.Connection
    public ObjectStoreManagement objectStoreManagement() throws IOException {
        ensureNotClosing();
        return new NatsObjectStoreManagement(this, null);
    }

    public void processConnectionEvent(ConnectionListener.Events events) {
        if (this.callbackRunner.isShutdown()) {
            return;
        }
        try {
            Iterator<ConnectionListener> it = this.connectionListeners.iterator();
            while (it.hasNext()) {
                this.callbackRunner.execute(new l(this, it.next(), events, 0));
            }
        } catch (RejectedExecutionException unused) {
        }
    }

    public void processError(String str) {
        NatsUri natsUri;
        this.statistics.incrementErrCount();
        this.lastError.set(str);
        this.connectError.set(str);
        if (isConnected() && isAuthenticationError(str) && (natsUri = this.currentServer) != null) {
            this.serverAuthErrors.put(natsUri, str);
        }
        if (this.callbackRunner.isShutdown()) {
            return;
        }
        try {
            this.callbackRunner.execute(new b(this, 2, str));
        } catch (RejectedExecutionException unused) {
        }
    }

    public void processException(Exception exc) {
        this.statistics.incrementExceptionCount();
        if (this.callbackRunner.isShutdown()) {
            return;
        }
        try {
            this.callbackRunner.execute(new b(this, 1, exc));
        } catch (RejectedExecutionException unused) {
        }
    }

    public void processOK() {
        this.statistics.incrementOkCount();
    }

    public void processSlowConsumer(Consumer consumer) {
        if (this.callbackRunner.isShutdown()) {
            return;
        }
        try {
            this.callbackRunner.execute(new b(this, 3, consumer));
        } catch (RejectedExecutionException unused) {
        }
    }

    @Override // io.nats.client.Connection
    public void publish(String str, byte[] bArr) {
        publishInternal(str, null, null, bArr, true, false);
    }

    public void publishInternal(String str, String str2, Headers headers, byte[] bArr, boolean z, boolean z2) {
        checkPayloadSize(bArr);
        NatsPublishableMessage natsPublishableMessage = new NatsPublishableMessage(str, str2, headers, bArr, z, z2);
        if (natsPublishableMessage.hasHeaders && !this.serverInfo.get().isHeadersSupported()) {
            throw new IllegalArgumentException("Headers are not supported by the server, version: " + this.serverInfo.get().getVersion());
        }
        if (isClosed()) {
            throw new IllegalStateException("Connection is Closed");
        }
        if (this.blockPublishForDrain.get()) {
            throw new IllegalStateException("Connection is Draining");
        }
        Connection.Status status = this.status;
        if ((status != Connection.Status.RECONNECTING && status != Connection.Status.DISCONNECTED) || this.writer.canQueueDuringReconnect(natsPublishableMessage)) {
            queueOutgoing(natsPublishableMessage);
        } else {
            throw new IllegalStateException("Unable to queue any more messages during reconnect, max buffer is " + this.options.getReconnectBufferSize());
        }
    }

    public void queueInternalOutgoing(NatsMessage natsMessage) {
        if (natsMessage.getControlLineLength() > this.options.getMaxControlLine()) {
            throw new IllegalArgumentException("Control line is too long");
        }
        this.writer.queueInternalMessage(natsMessage);
    }

    public void queueOutgoing(NatsMessage natsMessage) {
        if (natsMessage.getControlLineLength() > this.options.getMaxControlLine()) {
            throw new IllegalArgumentException("Control line is too long");
        }
        if (this.writer.queue(natsMessage)) {
            return;
        }
        this.options.getErrorListener().messageDiscarded(this, natsMessage);
    }

    public String reSubscribe(NatsSubscription natsSubscription, String str, String str2) {
        String nextSid = getNextSid();
        sendSubscriptionMessage(nextSid, str, str2, false);
        this.subscribers.put(nextSid, natsSubscription);
        return nextSid;
    }

    public void readInitialInfo() throws IOException {
        int bufferSize = this.options.getBufferSize();
        byte[] bArr = new byte[bufferSize];
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.options.getBufferSize());
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            int i = this.dataPort.read(bArr, 0, bufferSize);
            if (i < 0) {
                break;
            }
            int i2 = 0;
            while (true) {
                if (i2 < i) {
                    int i3 = i2 + 1;
                    byte b = bArr[i2];
                    if (!z2) {
                        if (b == 13) {
                            z2 = true;
                        } else {
                            if (!byteBufferAllocate.hasRemaining()) {
                                byteBufferAllocate = enlargeBuffer(byteBufferAllocate);
                            }
                            byteBufferAllocate.put(b);
                        }
                        i2 = i3;
                    } else {
                        if (b != 10) {
                            throw new IOException("Missed LF after CR waiting for INFO.");
                        }
                        if (i3 < i) {
                            throw new IOException("Read past initial info message.");
                        }
                        z = true;
                    }
                }
            }
        }
        if (!z) {
            throw new IOException("Failed to read initial info message.");
        }
        byteBufferAllocate.flip();
        String strTrim = StandardCharsets.UTF_8.decode(byteBufferAllocate).toString().trim();
        if (!"INFO".equals(strTrim.split("\\s")[0].toUpperCase())) {
            throw new IOException("Received non-info initial message.");
        }
        handleInfo(strTrim);
    }

    public void reconnect() throws InterruptedException {
        if (this.tryingToConnect.get()) {
            return;
        }
        try {
            this.tryingToConnect.set(true);
            reconnectImpl();
        } finally {
            this.tryingToConnect.set(false);
        }
    }

    public void reconnectImpl() throws ExecutionException, InterruptedException, URISyntaxException, TimeoutException, IOException {
        if (isClosed()) {
            return;
        }
        if (this.options.getMaxReconnect() == 0) {
            close();
            return;
        }
        boolean z = true;
        this.writer.setReconnectMode(true);
        if (!isConnected() && !isClosed() && !isClosing()) {
            NatsUri natsUri = null;
            int i = 0;
            while (z) {
                NatsUri natsUriNextServer = this.serverPool.nextServer();
                if (natsUriNextServer == null) {
                    break;
                }
                if (natsUri == null) {
                    natsUri = natsUriNextServer;
                } else if (natsUri.equals(natsUriNextServer)) {
                    i++;
                    invokeReconnectDelayHandler(i);
                }
                for (NatsUri natsUri2 : resolveHost(natsUriNextServer)) {
                    if (!isClosed()) {
                        this.connectError.set("");
                        if (!isDisconnectingOrClosed() && !isClosing()) {
                            updateStatus(Connection.Status.RECONNECTING);
                            this.timeTraceLogger.trace("reconnecting to server %s", natsUriNextServer);
                            tryToConnect(natsUriNextServer, natsUri2, NatsSystemClock.nanoTime());
                            if (isConnected()) {
                                this.serverPool.connectSucceeded(natsUriNextServer);
                                this.statistics.incrementReconnects();
                            } else {
                                this.serverPool.connectFailed(natsUriNextServer);
                                String str = this.connectError.get();
                                if (isAuthenticationError(str)) {
                                    if (!str.equals(this.serverAuthErrors.get(natsUri2))) {
                                        this.serverAuthErrors.put(natsUri2, str);
                                    }
                                }
                            }
                        }
                    }
                    z = false;
                }
            }
        }
        if (!isConnected()) {
            close();
            return;
        }
        this.subscribers.forEach(new n(6, this));
        this.dispatchers.forEach(new m(0));
        try {
            flush(this.options.getConnectionTimeout());
        } catch (Exception e) {
            processException(e);
        }
        processConnectionEvent(ConnectionListener.Events.RESUBSCRIBED);
        this.writer.setReconnectMode(false);
    }

    public void remove(NatsSubscription natsSubscription) {
        this.subscribers.remove(natsSubscription.getSID());
        if (natsSubscription.getNatsDispatcher() != null) {
            natsSubscription.getNatsDispatcher().remove(natsSubscription);
        }
    }

    @Override // io.nats.client.Connection
    public void removeConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.remove(connectionListener);
    }

    @Override // io.nats.client.Connection
    public Message request(String str, byte[] bArr, Duration duration) throws InterruptedException {
        return requestInternal(str, null, bArr, duration, this.cancelAction, true, this.forceFlushOnRequest);
    }

    public CompletableFuture<Message> requestFutureInternal(String str, Headers headers, byte[] bArr, Duration duration, NatsRequestCompletableFuture.CancelAction cancelAction, boolean z, boolean z2) {
        checkPayloadSize(bArr);
        if (isClosed()) {
            throw new IllegalStateException("Connection is Closed");
        }
        if (isDraining()) {
            throw new IllegalStateException("Connection is Draining");
        }
        if (this.inboxDispatcher.get() == null) {
            this.inboxDispatcherLock.lock();
            try {
                if (this.inboxDispatcher.get() == null) {
                    NatsDispatcher natsDispatcherCreateDispatcher = this.dispatcherFactory.createDispatcher(this, new j(this, 0));
                    String next = this.nuid.next();
                    this.dispatchers.put(next, natsDispatcherCreateDispatcher);
                    natsDispatcherCreateDispatcher.start(next);
                    natsDispatcherCreateDispatcher.subscribe(this.mainInbox);
                    this.inboxDispatcher.set(natsDispatcherCreateDispatcher);
                }
                this.inboxDispatcherLock.unlock();
            } catch (Throwable th) {
                this.inboxDispatcherLock.unlock();
                throw th;
            }
        }
        boolean zIsOldRequestStyle = this.options.isOldRequestStyle();
        final String strCreateInbox = zIsOldRequestStyle ? createInbox() : createResponseInbox(this.mainInbox);
        String responseToken = getResponseToken(strCreateInbox);
        if (duration == null) {
            duration = this.options.getRequestCleanupInterval();
        }
        NatsRequestCompletableFuture natsRequestCompletableFuture = new NatsRequestCompletableFuture(cancelAction, duration, this.options.useTimeoutException());
        if (!zIsOldRequestStyle) {
            this.responsesAwaiting.put(responseToken, natsRequestCompletableFuture);
        }
        this.statistics.incrementOutstandingRequests();
        if (zIsOldRequestStyle) {
            final NatsDispatcher natsDispatcher = this.inboxDispatcher.get();
            NatsSubscription natsSubscriptionSubscribeReturningSubscription = natsDispatcher.subscribeReturningSubscription(strCreateInbox);
            natsDispatcher.unsubscribe(strCreateInbox, 1);
            natsRequestCompletableFuture.whenComplete(new BiConsumer() { // from class: io.nats.client.impl.k
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    NatsConnection.lambda$requestFutureInternal$9(natsDispatcher, strCreateInbox, (Message) obj, (Throwable) obj2);
                }
            });
            this.responsesAwaiting.put(natsSubscriptionSubscribeReturningSubscription.getSID(), natsRequestCompletableFuture);
        }
        publishInternal(str, strCreateInbox, headers, bArr, z, z2);
        this.statistics.incrementRequestsSent();
        return natsRequestCompletableFuture;
    }

    public Message requestInternal(String str, Headers headers, byte[] bArr, Duration duration, NatsRequestCompletableFuture.CancelAction cancelAction, boolean z, boolean z2) throws InterruptedException {
        try {
            return requestFutureInternal(str, headers, bArr, duration, cancelAction, z, z2).get(duration.toNanos(), TimeUnit.NANOSECONDS);
        } catch (CancellationException | ExecutionException | TimeoutException unused) {
            return null;
        }
    }

    @Override // io.nats.client.Connection
    public CompletableFuture<Message> requestWithTimeout(String str, byte[] bArr, Duration duration) {
        return requestFutureInternal(str, null, bArr, duration, this.cancelAction, true, this.forceFlushOnRequest);
    }

    public List<NatsUri> resolveHost(NatsUri natsUri) throws URISyntaxException {
        List<String> listResolveHostToIps;
        ArrayList arrayList = new ArrayList();
        if (!natsUri.hostIsIpAddress() && !natsUri.isWebsocket() && (listResolveHostToIps = this.serverPool.resolveHostToIps(natsUri.getHost())) != null) {
            Iterator<String> it = listResolveHostToIps.iterator();
            while (it.hasNext()) {
                try {
                    arrayList.add(natsUri.reHost(it.next()));
                } catch (URISyntaxException unused) {
                }
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(natsUri);
        }
        return arrayList;
    }

    public void sendConnect(NatsUri natsUri) throws IOException {
        try {
            CharBuffer charBufferBuildProtocolConnectOptionsString = this.options.buildProtocolConnectOptionsString(natsUri.toString(), true, this.serverInfo.get().getNonce());
            queueInternalOutgoing(new ProtocolMessage(new ByteArrayBuilder(NatsConstants.OP_CONNECT_SP_LEN + charBufferBuildProtocolConnectOptionsString.limit(), StandardCharsets.UTF_8).append(NatsConstants.CONNECT_SP_BYTES).append(charBufferBuildProtocolConnectOptionsString), false));
        } catch (Exception e) {
            throw new IOException("Error sending connect string", e);
        }
    }

    public CompletableFuture<Boolean> sendPing() {
        return sendPing(true);
    }

    public void sendPong() {
        queueInternalOutgoing(new ProtocolMessage(PONG_PROTO));
    }

    public void sendSubscriptionMessage(String str, String str2, String str3, boolean z) {
        if (isConnected()) {
            ByteArrayBuilder byteArrayBuilderAppend = new ByteArrayBuilder(StandardCharsets.UTF_8).append(NatsConstants.SUB_SP_BYTES).append(str2);
            if (str3 != null) {
                byteArrayBuilderAppend.append(NatsConstants.SP).append(str3);
            }
            byteArrayBuilderAppend.append(NatsConstants.SP).append(str);
            ProtocolMessage protocolMessage = new ProtocolMessage(byteArrayBuilderAppend, true);
            if (z) {
                queueInternalOutgoing(protocolMessage);
            } else {
                queueOutgoing(protocolMessage);
            }
        }
    }

    public void sendUnsub(NatsSubscription natsSubscription, int i) {
        ByteArrayBuilder byteArrayBuilderAppend = new ByteArrayBuilder().append(NatsConstants.UNSUB_SP_BYTES).append(natsSubscription.getSID());
        if (i > 0) {
            byteArrayBuilderAppend.append(NatsConstants.SP).append(i);
        }
        queueOutgoing(new ProtocolMessage(byteArrayBuilderAppend, true));
    }

    public CompletableFuture<Boolean> softPing() {
        return sendPing(false);
    }

    @Override // io.nats.client.Connection
    public Subscription subscribe(String str) {
        Validator.validateSubject(str, true);
        return createSubscription(str, null, null, null);
    }

    public long timeCheck(long j, String str) throws TimeoutException {
        long jNanoTime = j - NatsSystemClock.nanoTime();
        if (this.trace) {
            traceTimeCheck(str, jNanoTime);
        }
        if (jNanoTime >= 0) {
            return jNanoTime;
        }
        throw new TimeoutException("connection timed out");
    }

    public void traceTimeCheck(String str, long j) {
        if (j < 0) {
            if (j > -1000000) {
                TimeTraceLogger timeTraceLogger = this.timeTraceLogger;
                StringBuilder sbP = zn.p(str);
                sbP.append(String.format(", %d (ns) beyond timeout", Long.valueOf(-j)));
                timeTraceLogger.trace(sbP.toString(), new Object[0]);
                return;
            }
            if (j <= -1000000000) {
                TimeTraceLogger timeTraceLogger2 = this.timeTraceLogger;
                StringBuilder sbP2 = zn.p(str);
                sbP2.append(String.format(", %.3f (s) beyond timeout", Double.valueOf((-j) / 1.0E9d)));
                timeTraceLogger2.trace(sbP2.toString(), new Object[0]);
                return;
            }
            long j2 = (-j) / NatsConstants.NANOS_PER_MILLI;
            TimeTraceLogger timeTraceLogger3 = this.timeTraceLogger;
            StringBuilder sbP3 = zn.p(str);
            sbP3.append(String.format(", %d (ms) beyond timeout", Long.valueOf(j2)));
            timeTraceLogger3.trace(sbP3.toString(), new Object[0]);
            return;
        }
        if (j < NatsConstants.NANOS_PER_MILLI) {
            TimeTraceLogger timeTraceLogger4 = this.timeTraceLogger;
            StringBuilder sbP4 = zn.p(str);
            sbP4.append(String.format(", %d (ns) remaining", Long.valueOf(j)));
            timeTraceLogger4.trace(sbP4.toString(), new Object[0]);
            return;
        }
        if (j >= 1000000000) {
            TimeTraceLogger timeTraceLogger5 = this.timeTraceLogger;
            StringBuilder sbP5 = zn.p(str);
            sbP5.append(String.format(", %.3f (s) remaining", Double.valueOf(j / 1.0E9d)));
            timeTraceLogger5.trace(sbP5.toString(), new Object[0]);
            return;
        }
        long j3 = j / NatsConstants.NANOS_PER_MILLI;
        TimeTraceLogger timeTraceLogger6 = this.timeTraceLogger;
        StringBuilder sbP6 = zn.p(str);
        sbP6.append(String.format(", %d (ms) remaining", Long.valueOf(j3)));
        timeTraceLogger6.trace(sbP6.toString(), new Object[0]);
    }

    public void tryToConnect(NatsUri natsUri, final NatsUri natsUri2, long j) throws ExecutionException, InterruptedException, TimeoutException, IOException {
        this.currentServer = null;
        try {
            try {
                Duration connectionTimeout = this.options.getConnectionTimeout();
                final boolean zIsTraceConnection = this.options.isTraceConnection();
                long nanos = j + connectionTimeout.toNanos();
                timeCheck(nanos, "starting connection attempt");
                this.statusLock.lock();
                try {
                    if (this.connecting) {
                        this.statusLock.unlock();
                        this.statusLock.lock();
                        try {
                            this.connecting = false;
                            this.statusChanged.signalAll();
                        } finally {
                        }
                    } else {
                        this.connecting = true;
                        this.statusChanged.signalAll();
                        this.statusLock.unlock();
                        this.dataPortFuture = new CompletableFuture<>();
                        long jTimeCheck = timeCheck(nanos, "waiting for reader");
                        if (this.reader.isRunning()) {
                            this.reader.stop().get(jTimeCheck, TimeUnit.NANOSECONDS);
                        }
                        long jTimeCheck2 = timeCheck(nanos, "waiting for writer");
                        if (this.writer.isRunning()) {
                            this.writer.stop().get(jTimeCheck2, TimeUnit.NANOSECONDS);
                        }
                        timeCheck(nanos, "cleaning pong queue");
                        cleanUpPongQueue();
                        long jTimeCheck3 = timeCheck(nanos, "connecting data port");
                        DataPort dataPortBuildDataPort = this.options.buildDataPort();
                        dataPortBuildDataPort.connect(natsUri2.toString(), this, jTimeCheck3);
                        this.dataPort = dataPortBuildDataPort;
                        this.dataPortFuture.complete(dataPortBuildDataPort);
                        Callable callable = new Callable() { // from class: io.nats.client.impl.d
                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                return this.a.lambda$tryToConnect$3(natsUri2, zIsTraceConnection);
                            }
                        };
                        long jTimeCheck4 = timeCheck(nanos, "reading info, version and upgrading to secure if necessary");
                        Future futureSubmit = this.connectExecutor.submit(callable);
                        try {
                            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                            futureSubmit.get(jTimeCheck4, timeUnit);
                            futureSubmit.cancel(true);
                            timeCheck(nanos, "starting reader");
                            this.reader.start(this.dataPortFuture);
                            timeCheck(nanos, "starting writer");
                            this.writer.start(this.dataPortFuture);
                            timeCheck(nanos, "sending connect message");
                            sendConnect(natsUri2);
                            long jTimeCheck5 = timeCheck(nanos, "sending initial ping");
                            CompletableFuture<Boolean> completableFutureSendPing = sendPing();
                            if (completableFutureSendPing != null) {
                                completableFutureSendPing.get(jTimeCheck5, timeUnit);
                            }
                            if (this.pingTask == null) {
                                timeCheck(nanos, "starting ping and cleanup timers");
                                long millis = this.options.getPingInterval().toMillis();
                                if (millis > 0) {
                                    this.pingTask = new ScheduledTask(this.scheduledExecutor, millis, new e(0, this));
                                }
                                long millis2 = this.options.getRequestCleanupInterval().toMillis();
                                if (millis2 > 0) {
                                    this.cleanupTask = new ScheduledTask(this.scheduledExecutor, millis2, new e(1, this));
                                }
                            }
                            timeCheck(nanos, "updating status to connected");
                            this.statusLock.lock();
                            try {
                                this.connecting = false;
                                Exception exc = this.exceptionDuringConnectChange;
                                if (exc != null) {
                                    throw exc;
                                }
                                this.currentServer = natsUri;
                                this.serverAuthErrors.clear();
                                updateStatus(Connection.Status.CONNECTED);
                                this.statusLock.unlock();
                                this.timeTraceLogger.trace("status updated", new Object[0]);
                                this.statusLock.lock();
                                try {
                                    this.connecting = false;
                                    this.statusChanged.signalAll();
                                } finally {
                                }
                            } finally {
                            }
                        } catch (Throwable th) {
                            futureSubmit.cancel(true);
                            throw th;
                        }
                    }
                } finally {
                }
            } catch (Throwable th2) {
                this.statusLock.lock();
                try {
                    this.connecting = false;
                    this.statusChanged.signalAll();
                    throw th2;
                } finally {
                }
            }
        } catch (Exception e) {
            processException(e);
            try {
                closeSocket(false, true);
            } catch (InterruptedException e2) {
                processException(e2);
                Thread.currentThread().interrupt();
            }
            this.statusLock.lock();
            try {
                this.connecting = false;
                this.statusChanged.signalAll();
            } finally {
            }
        }
    }

    public void unsubscribe(NatsSubscription natsSubscription, int i) {
        if (isClosed()) {
            throw new IllegalStateException("Connection is Closed");
        }
        if (i <= 0) {
            invalidate(natsSubscription);
        } else {
            natsSubscription.setUnsubLimit(i);
            if (natsSubscription.reachedUnsubLimit()) {
                natsSubscription.invalidate();
            }
        }
        if (isConnected()) {
            sendUnsub(natsSubscription, i);
        }
    }

    public void updateStatus(Connection.Status status) {
        Connection.Status status2 = this.status;
        this.statusLock.lock();
        try {
            Connection.Status status3 = Connection.Status.CLOSED;
            if (status2 != status3 && status != status2) {
                this.status = status;
                this.statusChanged.signalAll();
                this.statusLock.unlock();
                Connection.Status status4 = this.status;
                if (status4 == Connection.Status.DISCONNECTED) {
                    processConnectionEvent(ConnectionListener.Events.DISCONNECTED);
                    return;
                }
                if (status4 == status3) {
                    processConnectionEvent(ConnectionListener.Events.CLOSED);
                    return;
                }
                if (status2 == Connection.Status.RECONNECTING && status4 == Connection.Status.CONNECTED) {
                    processConnectionEvent(ConnectionListener.Events.RECONNECTED);
                } else if (status4 == Connection.Status.CONNECTED) {
                    processConnectionEvent(ConnectionListener.Events.CONNECTED);
                }
            }
        } finally {
            this.statusChanged.signalAll();
            this.statusLock.unlock();
        }
    }

    public void upgradeToSecureIfNeeded(NatsUri natsUri) throws IOException {
        if (natsUri.isWebsocket()) {
            return;
        }
        if (this.options.isTlsFirst()) {
            this.dataPort.upgradeToSecure();
            return;
        }
        ServerInfo info = getInfo();
        if (!this.options.isTLSRequired()) {
            if (info.isTLSRequired()) {
                throw new IOException("SSL required by server.");
            }
        } else {
            if (!info.isTLSRequired() && !info.isTLSAvailable()) {
                throw new IOException("SSL connection wanted by client.");
            }
            this.dataPort.upgradeToSecure();
        }
    }

    public void waitFor(Duration duration, Predicate<Void> predicate) throws InterruptedException {
        long nanos;
        this.statusLock.lock();
        if (duration != null) {
            try {
                nanos = duration.toNanos();
            } finally {
                this.statusLock.unlock();
            }
        } else {
            nanos = -1;
        }
        long jNanoTime = NatsSystemClock.nanoTime();
        while (nanos >= 0) {
            if (!predicate.test(null)) {
                break;
            }
            if (nanos > 0) {
                this.statusChanged.await(nanos, TimeUnit.NANOSECONDS);
                long jNanoTime2 = NatsSystemClock.nanoTime();
                nanos -= jNanoTime2 - jNanoTime;
                if (nanos > 0) {
                    jNanoTime = jNanoTime2;
                }
            } else {
                this.statusChanged.await();
            }
        }
    }

    public void waitForConnectOrClose(Duration duration) throws InterruptedException {
        waitFor(duration, new c(this, 0));
    }

    public void waitForDisconnectOrClose(Duration duration) throws InterruptedException {
        waitFor(duration, new c(this, 1));
    }

    public void close(boolean z, boolean z2) throws ExecutionException, InterruptedException, TimeoutException, IOException {
        this.statusLock.lock();
        if (z) {
            try {
                if (isDraining()) {
                    waitForDisconnectOrClose(this.options.getConnectionTimeout());
                    return;
                }
            } finally {
            }
        }
        this.closing = true;
        if (isDisconnectingOrClosed()) {
            waitForDisconnectOrClose(this.options.getConnectionTimeout());
            return;
        }
        this.disconnecting = true;
        this.exceptionDuringConnectChange = null;
        this.statusChanged.signalAll();
        this.statusLock.unlock();
        CompletableFuture<Boolean> completableFuture = this.reconnectWaiter;
        if (completableFuture != null) {
            completableFuture.cancel(true);
        }
        closeSocketImpl(z2);
        this.dispatchers.forEach(new m(1));
        this.subscribers.forEach(new m(2));
        this.dispatchers.clear();
        this.subscribers.clear();
        ScheduledTask scheduledTask = this.pingTask;
        if (scheduledTask != null) {
            scheduledTask.shutdown();
            this.pingTask = null;
        }
        ScheduledTask scheduledTask2 = this.cleanupTask;
        if (scheduledTask2 != null) {
            scheduledTask2.shutdown();
            this.cleanupTask = null;
        }
        cleanResponses(true);
        cleanUpPongQueue();
        this.statusLock.lock();
        try {
            updateStatus(Connection.Status.CLOSED);
            this.statusLock.unlock();
            this.callbackRunner.shutdown();
            try {
                this.callbackRunner.awaitTermination(this.options.getConnectionTimeout().toNanos(), TimeUnit.NANOSECONDS);
                this.callbackRunner.shutdownNow();
                this.connectExecutor.shutdownNow();
                this.statusLock.lock();
                try {
                    this.disconnecting = false;
                    this.statusChanged.signalAll();
                } finally {
                }
            } catch (Throwable th) {
                this.callbackRunner.shutdownNow();
                throw th;
            }
        } finally {
        }
    }

    @Override // io.nats.client.Connection
    public Dispatcher createDispatcher(MessageHandler messageHandler) {
        if (isClosed()) {
            throw new IllegalStateException("Connection is Closed");
        }
        if (isDraining()) {
            throw new IllegalStateException("Connection is Draining");
        }
        NatsDispatcher natsDispatcherCreateDispatcher = this.dispatcherFactory.createDispatcher(this, messageHandler);
        String next = this.nuid.next();
        this.dispatchers.put(next, natsDispatcherCreateDispatcher);
        natsDispatcherCreateDispatcher.start(next);
        return natsDispatcherCreateDispatcher;
    }

    @Override // io.nats.client.Connection
    public void forceReconnect(ForceReconnectOptions forceReconnectOptions) throws InterruptedException, IOException {
        if (this.tryingToConnect.get()) {
            return;
        }
        try {
            this.tryingToConnect.set(true);
            forceReconnectImpl(forceReconnectOptions);
        } finally {
            this.tryingToConnect.set(false);
        }
    }

    @Override // io.nats.client.Connection
    public ConsumerContext getConsumerContext(String str, String str2, JetStreamOptions jetStreamOptions) throws IOException, JetStreamApiException {
        return getStreamContext(str, jetStreamOptions).getConsumerContext(str2);
    }

    @Override // io.nats.client.Connection
    public void publish(String str, Headers headers, byte[] bArr) {
        publishInternal(str, null, headers, bArr, true, false);
    }

    @Override // io.nats.client.Connection
    public Message request(String str, Headers headers, byte[] bArr, Duration duration) throws InterruptedException {
        return requestInternal(str, headers, bArr, duration, this.cancelAction, true, this.forceFlushOnRequest);
    }

    @Override // io.nats.client.Connection
    public CompletableFuture<Message> requestWithTimeout(String str, Headers headers, byte[] bArr, Duration duration) {
        return requestFutureInternal(str, headers, bArr, duration, this.cancelAction, true, this.forceFlushOnRequest);
    }

    public CompletableFuture<Boolean> sendPing(boolean z) {
        if (!isConnectedOrConnecting()) {
            CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
            completableFuture.complete(Boolean.FALSE);
            return completableFuture;
        }
        if (!z && !this.needPing.get()) {
            CompletableFuture<Boolean> completableFuture2 = new CompletableFuture<>();
            completableFuture2.complete(Boolean.TRUE);
            this.needPing.set(true);
            return completableFuture2;
        }
        int maxPingsOut = this.options.getMaxPingsOut();
        if (maxPingsOut > 0 && this.pongQueue.size() + 1 > maxPingsOut) {
            handleCommunicationIssue(new IllegalStateException("Max outgoing Ping count exceeded."));
            return null;
        }
        CompletableFuture<Boolean> completableFuture3 = new CompletableFuture<>();
        this.pongQueue.add(completableFuture3);
        if (z) {
            queueInternalOutgoing(new ProtocolMessage(PING_PROTO));
        } else {
            queueOutgoing(new ProtocolMessage(PING_PROTO));
        }
        this.needPing.set(true);
        this.statistics.incrementPingCount();
        return completableFuture3;
    }

    @Override // io.nats.client.Connection
    public JetStream jetStream(JetStreamOptions jetStreamOptions) throws IOException {
        ensureNotClosing();
        return new NatsJetStream(this, jetStreamOptions);
    }

    @Override // io.nats.client.Connection
    public JetStreamManagement jetStreamManagement(JetStreamOptions jetStreamOptions) throws IOException {
        ensureNotClosing();
        return new NatsJetStreamManagement(this, jetStreamOptions);
    }

    @Override // io.nats.client.Connection
    public KeyValueManagement keyValueManagement(KeyValueOptions keyValueOptions) throws IOException {
        ensureNotClosing();
        return new NatsKeyValueManagement(this, keyValueOptions);
    }

    @Override // io.nats.client.Connection
    public ObjectStoreManagement objectStoreManagement(ObjectStoreOptions objectStoreOptions) throws IOException {
        ensureNotClosing();
        return new NatsObjectStoreManagement(this, objectStoreOptions);
    }

    @Override // io.nats.client.Connection
    public void publish(String str, String str2, byte[] bArr) {
        publishInternal(str, str2, null, bArr, true, false);
    }

    @Override // io.nats.client.Connection
    public Message request(Message message, Duration duration) throws InterruptedException {
        Validator.validateNotNull(message, "Message");
        return requestInternal(message.getSubject(), message.getHeaders(), message.getData(), duration, this.cancelAction, false, this.forceFlushOnRequest);
    }

    @Override // io.nats.client.Connection
    public CompletableFuture<Message> requestWithTimeout(Message message, Duration duration) {
        Validator.validateNotNull(message, "Message");
        return requestFutureInternal(message.getSubject(), message.getHeaders(), message.getData(), duration, this.cancelAction, false, this.forceFlushOnRequest);
    }

    @Override // io.nats.client.Connection
    public Subscription subscribe(String str, String str2) {
        Validator.validateSubject(str, true);
        Validator.validateQueueName(str2, true);
        return createSubscription(str, str2, null, null);
    }

    @Override // io.nats.client.Connection
    public StreamContext getStreamContext(String str, JetStreamOptions jetStreamOptions) throws IOException, JetStreamApiException {
        Validator.validateStreamName(str, true);
        ensureNotClosing();
        return new NatsStreamContext(str, null, this, jetStreamOptions);
    }

    @Override // io.nats.client.Connection
    public KeyValue keyValue(String str, KeyValueOptions keyValueOptions) throws IOException {
        Validator.validateBucketName(str, true);
        ensureNotClosing();
        return new NatsKeyValue(this, str, keyValueOptions);
    }

    @Override // io.nats.client.Connection
    public ObjectStore objectStore(String str, ObjectStoreOptions objectStoreOptions) throws IOException {
        Validator.validateBucketName(str, true);
        ensureNotClosing();
        return new NatsObjectStore(this, str, objectStoreOptions);
    }

    @Override // io.nats.client.Connection
    public void publish(String str, String str2, Headers headers, byte[] bArr) {
        publishInternal(str, str2, headers, bArr, true, false);
    }

    @Override // io.nats.client.Connection
    public void publish(Message message) {
        Validator.validateNotNull(message, "Message");
        publishInternal(message.getSubject(), message.getReplyTo(), message.getHeaders(), message.getData(), false, false);
    }

    @Override // io.nats.client.Connection
    public CompletableFuture<Message> request(String str, byte[] bArr) {
        return requestFutureInternal(str, null, bArr, null, this.cancelAction, true, this.forceFlushOnRequest);
    }

    @Override // io.nats.client.Connection
    public CompletableFuture<Message> request(String str, Headers headers, byte[] bArr) {
        return requestFutureInternal(str, headers, bArr, null, this.cancelAction, true, this.forceFlushOnRequest);
    }

    @Override // io.nats.client.Connection
    public CompletableFuture<Message> request(Message message) {
        Validator.validateNotNull(message, "Message");
        return requestFutureInternal(message.getSubject(), message.getHeaders(), message.getData(), null, this.cancelAction, false, this.forceFlushOnRequest);
    }
}
