package io.nats.client;

import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import defpackage.pj0;
import defpackage.to;
import defpackage.zn;
import io.nats.client.BaseConsumeOptions;
import io.nats.client.Options;
import io.nats.client.impl.DataPort;
import io.nats.client.impl.DispatcherFactory;
import io.nats.client.impl.ErrorListenerLoggerImpl;
import io.nats.client.impl.SSLContextFactory;
import io.nats.client.impl.SSLContextFactoryProperties;
import io.nats.client.impl.SocketDataPort;
import io.nats.client.impl.SocketDataPortWithWriteTimeout;
import io.nats.client.support.Encoding;
import io.nats.client.support.HttpRequest;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsUri;
import io.nats.client.support.SSLUtils;
import io.nats.client.support.Validator;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import javax.net.ssl.SSLContext;

/* JADX INFO: loaded from: classes.dex */
public class Options {
    public static final int DEFAULT_BUFFER_SIZE = 65536;
    public static final boolean DEFAULT_DISCARD_MESSAGES_WHEN_OUTGOING_QUEUE_FULL = false;
    public static final String DEFAULT_INBOX_PREFIX = "_INBOX.";
    public static final int DEFAULT_MAX_CONTROL_LINE = 4096;
    public static final int DEFAULT_MAX_MESSAGES_IN_OUTGOING_QUEUE = 5000;
    public static final int DEFAULT_MAX_PINGS_OUT = 2;
    public static final int DEFAULT_MAX_RECONNECT = 60;
    public static final int DEFAULT_PORT = 4222;
    public static final int DEFAULT_RECONNECT_BUF_SIZE = 8388608;
    public static final String DEFAULT_SSL_PROTOCOL = "TLSv1.2";
    public static final String DEFAULT_THREAD_NAME_PREFIX = "nats";
    public static final String DEFAULT_URL = "nats://localhost:4222";
    public static final int MAX_MESSAGES_IN_NETWORK_BUFFER = 1000;
    public static final long MINIMUM_SOCKET_READ_TIMEOUT_GT_CONNECTION_TIMEOUT = 100;
    public static final long MINIMUM_SOCKET_WRITE_TIMEOUT_GT_CONNECTION_TIMEOUT = 100;
    static final String OPTION_AUTH_TOKEN = "auth_token";
    static final String OPTION_ECHO = "echo";
    static final String OPTION_HEADERS = "headers";
    static final String OPTION_JWT = "jwt";
    static final String OPTION_LANG = "lang";
    static final String OPTION_NAME = "name";
    static final String OPTION_NKEY = "nkey";
    static final String OPTION_NORESPONDERS = "no_responders";
    static final String OPTION_PASSWORD = "pass";
    static final String OPTION_PEDANTIC = "pedantic";
    static final String OPTION_PROTOCOL = "protocol";
    static final String OPTION_SIG = "sig";
    static final String OPTION_TLS_REQUIRED = "tls_required";
    static final String OPTION_USER = "user";
    static final String OPTION_VERBOSE = "verbose";
    static final String OPTION_VERSION = "version";
    static final String PFX = "io.nats.client.";
    public static final String PROP_CALLBACK_THREAD_FACTORY_CLASS = "callback.thread.factory.class";
    public static final String PROP_CLEANUP_INTERVAL = "io.nats.client.cleanupinterval";
    public static final String PROP_CLIENT_SIDE_LIMIT_CHECKS = "io.nats.client.clientsidelimitchecks";
    public static final String PROP_CONNECTION_CB = "io.nats.client.callback.connection";
    public static final String PROP_CONNECTION_NAME = "io.nats.client.name";
    public static final String PROP_CONNECTION_TIMEOUT = "io.nats.client.timeout";
    public static final String PROP_CONNECT_THREAD_FACTORY_CLASS = "connect.thread.factory.class";
    public static final String PROP_CREDENTIAL_PATH = "io.nats.client.credential.path";
    public static final String PROP_DATA_PORT_TYPE = "io.nats.client.dataport.type";
    public static final String PROP_DISCARD_MESSAGES_WHEN_OUTGOING_QUEUE_FULL = "io.nats.client.outgoingqueue.discardwhenfull";
    public static final String PROP_DISPATCHER_FACTORY_CLASS = "dispatcher.factory.class";
    public static final String PROP_ERROR_LISTENER = "io.nats.client.callback.error";
    public static final String PROP_EXECUTOR_SERVICE_CLASS = "executor.service.class";
    public static final String PROP_FORCE_FLUSH_ON_REQUEST = "io.nats.client.force.flush.on.request";
    public static final String PROP_IGNORE_DISCOVERED_SERVERS = "ignore_discovered_servers";
    public static final String PROP_IGNORE_DISCOVERED_SERVERS_PREFERRED = "ignore.discovered.servers";
    public static final String PROP_INBOX_PREFIX = "inbox.prefix";
    public static final String PROP_KEYSTORE = "io.nats.client.keyStore";
    public static final String PROP_KEYSTORE_PASSWORD = "io.nats.client.keyStorePassword";
    public static final String PROP_MAX_CONTROL_LINE = "max.control.line";
    public static final String PROP_MAX_MESSAGES_IN_OUTGOING_QUEUE = "io.nats.client.outgoingqueue.maxmessages";
    public static final String PROP_MAX_PINGS = "io.nats.client.maxpings";
    public static final String PROP_MAX_RECONNECT = "io.nats.client.reconnect.max";
    public static final String PROP_NORANDOMIZE = "io.nats.client.norandomize";
    public static final String PROP_NO_ECHO = "io.nats.client.noecho";
    public static final String PROP_NO_HEADERS = "io.nats.client.noheaders";
    public static final String PROP_NO_NORESPONDERS = "io.nats.client.nonoresponders";
    public static final String PROP_NO_RESOLVE_HOSTNAMES = "io.nats.client.noResolveHostnames";
    public static final String PROP_OPENTLS = "io.nats.client.opentls";
    public static final String PROP_PASSWORD = "io.nats.client.password";
    public static final String PROP_PEDANTIC = "io.nats.client.pedantic";
    public static final String PROP_PING_INTERVAL = "io.nats.client.pinginterval";
    public static final String PROP_READ_LISTENER_CLASS = "read.listener.class";
    public static final String PROP_RECONNECT_BUF_SIZE = "io.nats.client.reconnect.buffer.size";
    public static final String PROP_RECONNECT_JITTER = "io.nats.client.reconnect.jitter";
    public static final String PROP_RECONNECT_JITTER_TLS = "io.nats.client.reconnect.jitter.tls";
    public static final String PROP_RECONNECT_WAIT = "io.nats.client.reconnect.wait";
    public static final String PROP_REPORT_NO_RESPONDERS = "io.nats.client.reportNoResponders";
    public static final String PROP_SCHEDULED_EXECUTOR_SERVICE_CLASS = "scheduled.executor.service.class";
    public static final String PROP_SECURE = "io.nats.client.secure";
    public static final String PROP_SERVERS = "io.nats.client.servers";
    public static final String PROP_SERVERS_POOL_IMPLEMENTATION_CLASS = "servers_pool_implementation_class";
    public static final String PROP_SERVERS_POOL_IMPLEMENTATION_CLASS_PREFERRED = "servers.pool.implementation.class";
    public static final String PROP_SOCKET_READ_TIMEOUT_MS = "io.nats.client.socket.read.timeout.ms";
    public static final String PROP_SOCKET_SO_LINGER = "io.nats.client.socket.so.linger";
    public static final String PROP_SOCKET_WRITE_TIMEOUT = "io.nats.client.socket.write.timeout";
    public static final String PROP_SSL_CONTEXT_FACTORY_CLASS = "ssl.context.factory.class";
    public static final String PROP_STATISTICS_COLLECTOR = "io.nats.client.statisticscollector";
    public static final String PROP_TIME_TRACE_LOGGER = "io.nats.client.time.trace";
    public static final String PROP_TLS_ALGORITHM = "io.nats.client.tls.algorithm";
    public static final String PROP_TLS_FIRST = "io.nats.client.tls.first";
    public static final String PROP_TOKEN = "io.nats.client.token";
    public static final String PROP_TOKEN_SUPPLIER = "io.nats.client.token.supplier";
    public static final String PROP_TRUSTSTORE = "io.nats.client.trustStore";
    public static final String PROP_TRUSTSTORE_PASSWORD = "io.nats.client.trustStorePassword";
    public static final String PROP_URL = "io.nats.client.url";
    public static final String PROP_USERNAME = "io.nats.client.username";
    public static final String PROP_USE_DISPATCHER_WITH_EXECUTOR = "io.nats.client.use.dispatcher.with.executor";
    public static final String PROP_USE_OLD_REQUEST_STYLE = "use.old.request.style";
    public static final String PROP_USE_TIMEOUT_EXCEPTION = "io.nats.client.use.timeout.exception";
    public static final String PROP_UTF8_SUBJECTS = "allow.utf8.subjects";
    public static final String PROP_VERBOSE = "io.nats.client.verbose";
    private final AuthHandler authHandler;
    private final int bufferSize;
    private final ThreadFactory callbackThreadFactory;
    private final boolean clientSideLimitChecks;
    private final ThreadFactory connectThreadFactory;
    private final ConnectionListener connectionListener;
    private final String connectionName;
    private final Duration connectionTimeout;
    private final String dataPortType;
    private final boolean discardMessagesWhenOutgoingQueueFull;
    private final DispatcherFactory dispatcherFactory;
    private final ErrorListener errorListener;
    private final ExecutorService executor;
    private final boolean forceFlushOnRequest;
    private final List<java.util.function.Consumer<HttpRequest>> httpRequestInterceptors;
    private final boolean ignoreDiscoveredServers;
    private final String inboxPrefix;
    private final int maxControlLine;
    private final int maxMessagesInOutgoingQueue;
    private final int maxPingsOut;
    private final int maxReconnect;
    private final List<NatsUri> natsServerUris;
    private final boolean noEcho;
    private final boolean noHeaders;
    private final boolean noNoResponders;
    private final boolean noRandomize;
    private final boolean noResolveHostnames;
    private final char[] password;
    private final boolean pedantic;
    private final Duration pingInterval;
    private final Proxy proxy;
    private final ReadListener readListener;
    private final long reconnectBufferSize;
    private final ReconnectDelayHandler reconnectDelayHandler;
    private final Duration reconnectJitter;
    private final Duration reconnectJitterTls;
    private final Duration reconnectWait;
    private final boolean reportNoResponders;
    private final Duration requestCleanupInterval;
    private final ScheduledExecutorService scheduledExecutor;
    private final ServerPool serverPool;
    private final int socketReadTimeoutMillis;
    private final int socketSoLinger;
    private final Duration socketWriteTimeout;
    private final SSLContext sslContext;
    private final StatisticsCollector statisticsCollector;
    private final boolean supportUTF8Subjects;
    private final TimeTraceLogger timeTraceLogger;
    private final boolean tlsFirst;
    private final Supplier<char[]> tokenSupplier;
    private final boolean traceConnection;
    private final boolean trackAdvancedStats;
    private final List<String> unprocessedServers;
    private final boolean useDispatcherWithExecutor;
    private boolean useOldRequestStyle;
    private final boolean useTimeoutException;
    private final char[] username;
    private final boolean verbose;
    public static final Duration DEFAULT_RECONNECT_WAIT = Duration.ofMillis(2000);
    public static final Duration DEFAULT_RECONNECT_JITTER = Duration.ofMillis(100);
    public static final Duration DEFAULT_RECONNECT_JITTER_TLS = Duration.ofMillis(1000);
    public static final Duration DEFAULT_CONNECTION_TIMEOUT = Duration.ofSeconds(2);
    public static final Duration DEFAULT_SOCKET_WRITE_TIMEOUT = Duration.ofMinutes(1);
    public static final Duration DEFAULT_PING_INTERVAL = Duration.ofMinutes(2);
    public static final Duration DEFAULT_REQUEST_CLEANUP_INTERVAL = Duration.ofSeconds(5);
    public static final String DEFAULT_DATA_PORT_TYPE = SocketDataPort.class.getCanonicalName();
    public static final Supplier<ExecutorService> DEFAULT_SINGLE_THREAD_EXECUTOR = new pj0();
    static final int PFX_LEN = 15;

    public static class Builder {
        private AuthHandler authHandler;
        private int bufferSize;
        private ThreadFactory callbackThreadFactory;
        private boolean clientSideLimitChecks;
        private ThreadFactory connectThreadFactory;
        private ConnectionListener connectionListener;
        private String connectionName;
        private Duration connectionTimeout;
        private String credentialPath;
        private String dataPortType;
        private boolean discardMessagesWhenOutgoingQueueFull;
        private DispatcherFactory dispatcherFactory;
        private ErrorListener errorListener;
        private ExecutorService executor;
        private boolean forceFlushOnRequest;
        private List<java.util.function.Consumer<HttpRequest>> httpRequestInterceptors;
        private boolean ignoreDiscoveredServers;
        private String inboxPrefix;
        private String keystore;
        private char[] keystorePassword;
        private int maxControlLine;
        private int maxMessagesInOutgoingQueue;
        private int maxPingsOut;
        private int maxReconnect;
        private final List<NatsUri> natsServerUris;
        private boolean noEcho;
        private boolean noHeaders;
        private boolean noNoResponders;
        private boolean noRandomize;
        private boolean noResolveHostnames;
        private char[] password;
        private boolean pedantic;
        private Duration pingInterval;
        private Proxy proxy;
        private ReadListener readListener;
        private long reconnectBufferSize;
        private ReconnectDelayHandler reconnectDelayHandler;
        private Duration reconnectJitter;
        private Duration reconnectJitterTls;
        private Duration reconnectWait;
        private boolean reportNoResponders;
        private Duration requestCleanupInterval;
        private ScheduledExecutorService scheduledExecutor;
        private ServerPool serverPool;
        private int socketReadTimeoutMillis;
        private int socketSoLinger;
        private Duration socketWriteTimeout;
        private SSLContext sslContext;
        private SSLContextFactory sslContextFactory;
        private StatisticsCollector statisticsCollector;
        private boolean supportUTF8Subjects;
        private TimeTraceLogger timeTraceLogger;
        private String tlsAlgorithm;
        private boolean tlsFirst;
        private Supplier<char[]> tokenSupplier;
        private boolean traceConnection;
        private boolean trackAdvancedStats;
        private String truststore;
        private char[] truststorePassword;
        private final List<String> unprocessedServers;
        private boolean useDefaultTls;
        private boolean useDispatcherWithExecutor;
        private boolean useOldRequestStyle;
        private boolean useTimeoutException;
        private boolean useTrustAllTls;
        private char[] username;
        private boolean verbose;

        public Builder() {
            this.natsServerUris = new ArrayList();
            this.unprocessedServers = new ArrayList();
            this.noRandomize = false;
            this.noResolveHostnames = false;
            this.reportNoResponders = false;
            this.connectionName = null;
            this.verbose = false;
            this.pedantic = false;
            this.sslContext = null;
            this.sslContextFactory = null;
            this.maxControlLine = Options.DEFAULT_MAX_CONTROL_LINE;
            this.maxReconnect = 60;
            this.reconnectWait = Options.DEFAULT_RECONNECT_WAIT;
            this.reconnectJitter = Options.DEFAULT_RECONNECT_JITTER;
            this.reconnectJitterTls = Options.DEFAULT_RECONNECT_JITTER_TLS;
            this.connectionTimeout = Options.DEFAULT_CONNECTION_TIMEOUT;
            this.socketReadTimeoutMillis = 0;
            this.socketWriteTimeout = Options.DEFAULT_SOCKET_WRITE_TIMEOUT;
            this.socketSoLinger = -1;
            this.pingInterval = Options.DEFAULT_PING_INTERVAL;
            this.requestCleanupInterval = Options.DEFAULT_REQUEST_CLEANUP_INTERVAL;
            this.maxPingsOut = 2;
            this.reconnectBufferSize = 8388608L;
            this.username = null;
            this.password = null;
            this.tokenSupplier = new DefaultTokenSupplier();
            this.useOldRequestStyle = false;
            this.bufferSize = Options.DEFAULT_BUFFER_SIZE;
            this.trackAdvancedStats = false;
            this.traceConnection = false;
            this.noEcho = false;
            this.noHeaders = false;
            this.noNoResponders = false;
            this.clientSideLimitChecks = true;
            this.supportUTF8Subjects = false;
            this.inboxPrefix = Options.DEFAULT_INBOX_PREFIX;
            this.maxMessagesInOutgoingQueue = Options.DEFAULT_MAX_MESSAGES_IN_OUTGOING_QUEUE;
            this.discardMessagesWhenOutgoingQueueFull = false;
            this.ignoreDiscoveredServers = false;
            this.tlsFirst = false;
            this.useTimeoutException = false;
            this.useDispatcherWithExecutor = false;
            this.forceFlushOnRequest = true;
            this.serverPool = null;
            this.dispatcherFactory = null;
            this.errorListener = null;
            this.timeTraceLogger = null;
            this.connectionListener = null;
            this.readListener = null;
            this.statisticsCollector = null;
            this.dataPortType = Options.DEFAULT_DATA_PORT_TYPE;
            this.tlsAlgorithm = SSLUtils.DEFAULT_TLS_ALGORITHM;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$build$59(String str, Object[] objArr) {
            DateTimeFormatter unused = DateTimeFormatter.ISO_TIME;
            String str2 = DateTimeFormatter.ISO_TIME.format(LocalDateTime.now());
            System.out.println("[" + str2 + "] connect trace: " + String.format(str, objArr));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$build$60(String str, Object[] objArr) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$0(String str) {
            servers(str.trim().split(",\\s*"));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$1(char[] cArr) {
            this.username = cArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$10(String str) {
            this.truststore = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$11(char[] cArr) {
            this.truststorePassword = cArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$12(String str) {
            this.tlsAlgorithm = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$13(String str) {
            this.credentialPath = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$14(String str) {
            this.connectionName = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$15(Boolean bool) {
            this.noRandomize = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$16(Boolean bool) {
            this.noResolveHostnames = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$17(Boolean bool) {
            this.reportNoResponders = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$18(String str) {
            this.connectionName = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$19(Boolean bool) {
            this.verbose = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$2(char[] cArr) {
            this.password = cArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$20(Boolean bool) {
            this.noEcho = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$21(Boolean bool) {
            this.noHeaders = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$22(Boolean bool) {
            this.noNoResponders = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$23(Boolean bool) {
            this.clientSideLimitChecks = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$24(Boolean bool) {
            this.supportUTF8Subjects = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$25(Boolean bool) {
            this.pedantic = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$26(Integer num) {
            this.maxReconnect = num.intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$27(Duration duration) {
            this.reconnectWait = duration;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$28(Duration duration) {
            this.reconnectJitter = duration;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$29(Duration duration) {
            this.reconnectJitterTls = duration;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$3(char[] cArr) {
            this.tokenSupplier = new DefaultTokenSupplier(cArr);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$30(Long l) {
            this.reconnectBufferSize = l.longValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$31(Duration duration) {
            this.connectionTimeout = duration;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$32(Integer num) {
            this.socketReadTimeoutMillis = num.intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$33(Duration duration) {
            this.socketWriteTimeout = duration;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$34(Integer num) {
            this.socketSoLinger = num.intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$35(Integer num) {
            this.maxControlLine = num.intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$36(Duration duration) {
            this.pingInterval = duration;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$37(Duration duration) {
            this.requestCleanupInterval = duration;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$38(Integer num) {
            this.maxPingsOut = num.intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$39(Boolean bool) {
            this.useOldRequestStyle = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$4(Object obj) {
            this.tokenSupplier = (Supplier) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$40(Object obj) {
            this.errorListener = (ErrorListener) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$41(Object obj) {
            this.timeTraceLogger = (TimeTraceLogger) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$42(Object obj) {
            this.connectionListener = (ConnectionListener) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$43(Object obj) {
            this.readListener = (ReadListener) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$44(Object obj) {
            this.statisticsCollector = (StatisticsCollector) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$45(String str) {
            this.dataPortType = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$46(Integer num) {
            this.maxMessagesInOutgoingQueue = num.intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$47(Boolean bool) {
            this.discardMessagesWhenOutgoingQueueFull = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$48(Boolean bool) {
            this.ignoreDiscoveredServers = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$49(Boolean bool) {
            this.tlsFirst = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$5(Boolean bool) {
            this.useDefaultTls = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$50(Boolean bool) {
            this.useTimeoutException = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$51(Boolean bool) {
            this.useDispatcherWithExecutor = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$52(Boolean bool) {
            this.forceFlushOnRequest = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$53(Object obj) {
            this.serverPool = (ServerPool) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$54(Object obj) {
            this.dispatcherFactory = (DispatcherFactory) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$55(Object obj) {
            this.executor = (ExecutorService) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$56(Object obj) {
            this.scheduledExecutor = (ScheduledExecutorService) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$57(Object obj) {
            this.connectThreadFactory = (ThreadFactory) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$58(Object obj) {
            this.callbackThreadFactory = (ThreadFactory) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$6(Boolean bool) {
            this.useTrustAllTls = bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$7(Object obj) {
            this.sslContextFactory = (SSLContextFactory) obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$8(String str) {
            this.keystore = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$properties$9(char[] cArr) {
            this.keystorePassword = cArr;
        }

        public Builder authHandler(AuthHandler authHandler) {
            this.authHandler = authHandler;
            return this;
        }

        public Builder bufferSize(int i) {
            this.bufferSize = i;
            return this;
        }

        public Options build() throws IllegalStateException {
            boolean z;
            final int i = 0;
            final int i2 = 1;
            if (this.username != null && this.tokenSupplier.get() != null) {
                throw new IllegalStateException("Options can't have token and username");
            }
            if (this.inboxPrefix == null) {
                this.inboxPrefix = Options.DEFAULT_INBOX_PREFIX;
            }
            if (this.natsServerUris.isEmpty()) {
                server(Options.DEFAULT_URL);
                z = false;
            } else {
                z = true;
            }
            if (this.sslContext == null) {
                SSLContextFactory sSLContextFactory = this.sslContextFactory;
                if (sSLContextFactory != null) {
                    this.sslContext = sSLContextFactory.createSSLContext(new SSLContextFactoryProperties.Builder().keystore(this.keystore).keystorePassword(this.keystorePassword).truststore(this.truststore).truststorePassword(this.truststorePassword).tlsAlgorithm(this.tlsAlgorithm).build());
                } else {
                    String str = this.keystore;
                    if (str == null && this.truststore == null) {
                        if (!this.useDefaultTls && !this.useTrustAllTls && z) {
                            for (int i3 = 0; this.sslContext == null && i3 < this.natsServerUris.size(); i3++) {
                                String scheme = this.natsServerUris.get(i3).getScheme();
                                scheme.getClass();
                                switch (scheme) {
                                    case "opentls":
                                        this.useTrustAllTls = true;
                                        break;
                                    case "tls":
                                    case "wss":
                                        this.useDefaultTls = true;
                                        break;
                                }
                            }
                        }
                        if (this.useTrustAllTls) {
                            try {
                                this.sslContext = SSLUtils.createTrustAllTlsContext();
                            } catch (GeneralSecurityException e) {
                                throw new IllegalStateException("Unable to create SSL context", e);
                            }
                        } else if (this.useDefaultTls) {
                            try {
                                this.sslContext = SSLContext.getDefault();
                            } catch (NoSuchAlgorithmException e2) {
                                throw new IllegalStateException("Unable to create default SSL context", e2);
                            }
                        }
                    } else {
                        try {
                            this.sslContext = SSLUtils.createSSLContext(str, this.keystorePassword, this.truststore, this.truststorePassword, this.tlsAlgorithm);
                        } catch (Exception e3) {
                            throw new IllegalStateException("Unable to create SSL context", e3);
                        }
                    }
                }
            }
            if (this.tlsFirst && this.sslContext == null) {
                throw new IllegalStateException("SSL context required for tls handshake first");
            }
            if (this.credentialPath != null) {
                this.authHandler = Nats.credentials(new File(this.credentialPath).getAbsoluteFile().toString());
            }
            if (this.executor == null) {
                this.executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 500L, TimeUnit.MILLISECONDS, new SynchronousQueue(), new DefaultThreadFactory(Validator.nullOrEmpty(this.connectionName) ? "nats" : this.connectionName));
            }
            if (this.scheduledExecutor == null) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3, new DefaultThreadFactory(Validator.nullOrEmpty(this.connectionName) ? "nats" : this.connectionName));
                scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
                scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
                this.scheduledExecutor = scheduledThreadPoolExecutor;
            }
            if (this.socketReadTimeoutMillis > 0) {
                if (this.socketReadTimeoutMillis < this.pingInterval.toMillis() + 100) {
                    throw new IllegalStateException("Socket Read Timeout must be at least 100 milliseconds greater than the Ping Interval");
                }
            }
            Duration duration = this.socketWriteTimeout;
            if (duration == null || duration.toMillis() < 1) {
                this.socketWriteTimeout = null;
            } else if (this.socketWriteTimeout.toMillis() < this.connectionTimeout.toMillis() + 100) {
                throw new IllegalStateException("Socket Write Timeout must be at least 100 milliseconds greater than the Connection Timeout");
            }
            if (this.socketSoLinger < 0) {
                this.socketSoLinger = -1;
            }
            if (this.errorListener == null) {
                this.errorListener = new ErrorListenerLoggerImpl();
            }
            if (this.timeTraceLogger != null) {
                this.traceConnection = true;
            } else if (this.traceConnection) {
                this.timeTraceLogger = new TimeTraceLogger() { // from class: tj0
                    @Override // io.nats.client.TimeTraceLogger
                    public final void trace(String str2, Object[] objArr) {
                        switch (i) {
                            case 0:
                                Options.Builder.lambda$build$59(str2, objArr);
                                break;
                            default:
                                Options.Builder.lambda$build$60(str2, objArr);
                                break;
                        }
                    }
                };
            } else {
                this.timeTraceLogger = new TimeTraceLogger() { // from class: tj0
                    @Override // io.nats.client.TimeTraceLogger
                    public final void trace(String str2, Object[] objArr) {
                        switch (i2) {
                            case 0:
                                Options.Builder.lambda$build$59(str2, objArr);
                                break;
                            default:
                                Options.Builder.lambda$build$60(str2, objArr);
                                break;
                        }
                    }
                };
            }
            return new Options(this);
        }

        public Builder callbackThreadFactory(ThreadFactory threadFactory) {
            this.callbackThreadFactory = threadFactory;
            return this;
        }

        public Builder clientSideLimitChecks(boolean z) {
            this.clientSideLimitChecks = z;
            return this;
        }

        public Builder connectThreadFactory(ThreadFactory threadFactory) {
            this.connectThreadFactory = threadFactory;
            return this;
        }

        public Builder connectionListener(ConnectionListener connectionListener) {
            this.connectionListener = connectionListener;
            return this;
        }

        public Builder connectionName(String str) {
            this.connectionName = str;
            return this;
        }

        public Builder connectionTimeout(Duration duration) {
            this.connectionTimeout = duration;
            return this;
        }

        public Builder credentialPath(String str) {
            this.credentialPath = Validator.emptyAsNull(str);
            return this;
        }

        public Builder dataPortType(String str) {
            if (str == null) {
                str = Options.DEFAULT_DATA_PORT_TYPE;
            }
            this.dataPortType = str;
            return this;
        }

        public Builder discardMessagesWhenOutgoingQueueFull() {
            this.discardMessagesWhenOutgoingQueueFull = true;
            return this;
        }

        public Builder dispatcherFactory(DispatcherFactory dispatcherFactory) {
            this.dispatcherFactory = dispatcherFactory;
            return this;
        }

        public Builder dontForceFlushOnRequest() {
            this.forceFlushOnRequest = false;
            return this;
        }

        public Builder errorListener(ErrorListener errorListener) {
            this.errorListener = errorListener;
            return this;
        }

        public Builder executor(ExecutorService executorService) {
            this.executor = executorService;
            return this;
        }

        public Builder httpRequestInterceptor(java.util.function.Consumer<HttpRequest> consumer) {
            if (this.httpRequestInterceptors == null) {
                this.httpRequestInterceptors = new ArrayList();
            }
            this.httpRequestInterceptors.add(consumer);
            return this;
        }

        public Builder httpRequestInterceptors(Collection<? extends java.util.function.Consumer<HttpRequest>> collection) {
            this.httpRequestInterceptors = new ArrayList(collection);
            return this;
        }

        public Builder ignoreDiscoveredServers() {
            this.ignoreDiscoveredServers = true;
            return this;
        }

        public Builder inboxPrefix(String str) {
            this.inboxPrefix = str;
            if (!str.endsWith(NatsConstants.DOT)) {
                this.inboxPrefix = zn.o(new StringBuilder(), this.inboxPrefix, NatsConstants.DOT);
            }
            return this;
        }

        public Builder keystorePassword(char[] cArr) {
            if (cArr == null || cArr.length == 0) {
                cArr = null;
            }
            this.keystorePassword = cArr;
            return this;
        }

        public Builder keystorePath(String str) {
            this.keystore = Validator.emptyAsNull(str);
            return this;
        }

        public Builder maxControlLine(int i) {
            if (i < 0) {
                i = Options.DEFAULT_MAX_CONTROL_LINE;
            }
            this.maxControlLine = i;
            return this;
        }

        public Builder maxMessagesInOutgoingQueue(int i) {
            if (i < 0) {
                i = Options.DEFAULT_MAX_MESSAGES_IN_OUTGOING_QUEUE;
            }
            this.maxMessagesInOutgoingQueue = i;
            return this;
        }

        public Builder maxPingsOut(int i) {
            this.maxPingsOut = i;
            return this;
        }

        public Builder maxReconnects(int i) {
            this.maxReconnect = i;
            return this;
        }

        public Builder noEcho() {
            this.noEcho = true;
            return this;
        }

        public Builder noHeaders() {
            this.noHeaders = true;
            return this;
        }

        public Builder noNoResponders() {
            this.noNoResponders = true;
            return this;
        }

        public Builder noRandomize() {
            this.noRandomize = true;
            return this;
        }

        public Builder noReconnect() {
            this.maxReconnect = 0;
            return this;
        }

        public Builder noResolveHostnames() {
            this.noResolveHostnames = true;
            return this;
        }

        public Builder oldRequestStyle() {
            this.useOldRequestStyle = true;
            return this;
        }

        public Builder opentls() throws NoSuchAlgorithmException {
            this.useTrustAllTls = true;
            return this;
        }

        public Builder pedantic() {
            this.pedantic = true;
            return this;
        }

        public Builder pingInterval(Duration duration) {
            if (duration == null) {
                duration = Options.DEFAULT_PING_INTERVAL;
            }
            this.pingInterval = duration;
            return this;
        }

        public Builder properties(Properties properties) {
            if (properties == null) {
                throw new IllegalArgumentException("Properties cannot be null");
            }
            final int i = 13;
            Options.stringProperty(properties, Options.PROP_URL, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i2 = 6;
            Options.stringProperty(properties, Options.PROP_SERVERS, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i2) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i3 = 15;
            Options.charArrayProperty(properties, Options.PROP_USERNAME, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i3) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i4 = 21;
            Options.charArrayProperty(properties, Options.PROP_PASSWORD, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i4) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i5 = 2;
            Options.charArrayProperty(properties, Options.PROP_TOKEN, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i5) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i6 = 14;
            Options.classnameProperty(properties, Options.PROP_TOKEN_SUPPLIER, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i6) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i7 = 16;
            Options.booleanProperty(properties, Options.PROP_SECURE, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i7) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i8 = 17;
            Options.booleanProperty(properties, Options.PROP_OPENTLS, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i8) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i9 = 18;
            Options.classnameProperty(properties, Options.PROP_SSL_CONTEXT_FACTORY_CLASS, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i9) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i10 = 19;
            Options.stringProperty(properties, Options.PROP_KEYSTORE, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i10) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i11 = 20;
            Options.charArrayProperty(properties, Options.PROP_KEYSTORE_PASSWORD, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i11) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i12 = 21;
            Options.stringProperty(properties, Options.PROP_TRUSTSTORE, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i12) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i13 = 22;
            Options.charArrayProperty(properties, Options.PROP_TRUSTSTORE_PASSWORD, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i13) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i14 = 23;
            Options.stringProperty(properties, Options.PROP_TLS_ALGORITHM, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i14) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i15 = 0;
            Options.stringProperty(properties, Options.PROP_CREDENTIAL_PATH, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i15) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i16 = 1;
            Options.stringProperty(properties, Options.PROP_CONNECTION_NAME, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i16) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i17 = 2;
            Options.booleanProperty(properties, Options.PROP_NORANDOMIZE, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i17) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i18 = 3;
            Options.booleanProperty(properties, Options.PROP_NO_RESOLVE_HOSTNAMES, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i18) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i19 = 4;
            Options.booleanProperty(properties, Options.PROP_REPORT_NO_RESPONDERS, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i19) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i20 = 5;
            Options.stringProperty(properties, Options.PROP_CONNECTION_NAME, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i20) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i21 = 7;
            Options.booleanProperty(properties, Options.PROP_VERBOSE, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i21) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i22 = 8;
            Options.booleanProperty(properties, Options.PROP_NO_ECHO, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i22) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i23 = 9;
            Options.booleanProperty(properties, Options.PROP_NO_HEADERS, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i23) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i24 = 10;
            Options.booleanProperty(properties, Options.PROP_NO_NORESPONDERS, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i24) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i25 = 11;
            Options.booleanProperty(properties, Options.PROP_CLIENT_SIDE_LIMIT_CHECKS, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i25) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i26 = 12;
            Options.booleanProperty(properties, Options.PROP_UTF8_SUBJECTS, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i26) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i27 = 13;
            Options.booleanProperty(properties, Options.PROP_PEDANTIC, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i27) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i28 = 14;
            Options.intProperty(properties, Options.PROP_MAX_RECONNECT, 60, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i28) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i29 = 0;
            Options.durationProperty(properties, Options.PROP_RECONNECT_WAIT, Options.DEFAULT_RECONNECT_WAIT, new java.util.function.Consumer(this) { // from class: rj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i29) {
                        case 0:
                            this.b.lambda$properties$27((Duration) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$28((Duration) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$29((Duration) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$31((Duration) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$33((Duration) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$36((Duration) obj);
                            break;
                        default:
                            this.b.lambda$properties$37((Duration) obj);
                            break;
                    }
                }
            });
            final int i30 = 1;
            Options.durationProperty(properties, Options.PROP_RECONNECT_JITTER, Options.DEFAULT_RECONNECT_JITTER, new java.util.function.Consumer(this) { // from class: rj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i30) {
                        case 0:
                            this.b.lambda$properties$27((Duration) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$28((Duration) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$29((Duration) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$31((Duration) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$33((Duration) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$36((Duration) obj);
                            break;
                        default:
                            this.b.lambda$properties$37((Duration) obj);
                            break;
                    }
                }
            });
            final int i31 = 2;
            Options.durationProperty(properties, Options.PROP_RECONNECT_JITTER_TLS, Options.DEFAULT_RECONNECT_JITTER_TLS, new java.util.function.Consumer(this) { // from class: rj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i31) {
                        case 0:
                            this.b.lambda$properties$27((Duration) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$28((Duration) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$29((Duration) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$31((Duration) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$33((Duration) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$36((Duration) obj);
                            break;
                        default:
                            this.b.lambda$properties$37((Duration) obj);
                            break;
                    }
                }
            });
            final int i32 = 16;
            Options.longProperty(properties, Options.PROP_RECONNECT_BUF_SIZE, 8388608L, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i32) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i33 = 3;
            Options.durationProperty(properties, Options.PROP_CONNECTION_TIMEOUT, Options.DEFAULT_CONNECTION_TIMEOUT, new java.util.function.Consumer(this) { // from class: rj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i33) {
                        case 0:
                            this.b.lambda$properties$27((Duration) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$28((Duration) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$29((Duration) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$31((Duration) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$33((Duration) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$36((Duration) obj);
                            break;
                        default:
                            this.b.lambda$properties$37((Duration) obj);
                            break;
                    }
                }
            });
            final int i34 = 17;
            Options.intProperty(properties, Options.PROP_SOCKET_READ_TIMEOUT_MS, -1, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i34) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i35 = 4;
            Options.durationProperty(properties, Options.PROP_SOCKET_WRITE_TIMEOUT, Options.DEFAULT_SOCKET_WRITE_TIMEOUT, new java.util.function.Consumer(this) { // from class: rj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i35) {
                        case 0:
                            this.b.lambda$properties$27((Duration) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$28((Duration) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$29((Duration) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$31((Duration) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$33((Duration) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$36((Duration) obj);
                            break;
                        default:
                            this.b.lambda$properties$37((Duration) obj);
                            break;
                    }
                }
            });
            final int i36 = 18;
            Options.intProperty(properties, Options.PROP_SOCKET_SO_LINGER, -1, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i36) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i37 = 19;
            Options.intGtEqZeroProperty(properties, Options.PROP_MAX_CONTROL_LINE, Options.DEFAULT_MAX_CONTROL_LINE, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i37) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i38 = 5;
            Options.durationProperty(properties, Options.PROP_PING_INTERVAL, Options.DEFAULT_PING_INTERVAL, new java.util.function.Consumer(this) { // from class: rj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i38) {
                        case 0:
                            this.b.lambda$properties$27((Duration) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$28((Duration) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$29((Duration) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$31((Duration) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$33((Duration) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$36((Duration) obj);
                            break;
                        default:
                            this.b.lambda$properties$37((Duration) obj);
                            break;
                    }
                }
            });
            final int i39 = 6;
            Options.durationProperty(properties, Options.PROP_CLEANUP_INTERVAL, Options.DEFAULT_REQUEST_CLEANUP_INTERVAL, new java.util.function.Consumer(this) { // from class: rj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i39) {
                        case 0:
                            this.b.lambda$properties$27((Duration) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$28((Duration) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$29((Duration) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$31((Duration) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$33((Duration) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$36((Duration) obj);
                            break;
                        default:
                            this.b.lambda$properties$37((Duration) obj);
                            break;
                    }
                }
            });
            final int i40 = 20;
            Options.intProperty(properties, Options.PROP_MAX_PINGS, 2, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i40) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i41 = 22;
            Options.booleanProperty(properties, Options.PROP_USE_OLD_REQUEST_STYLE, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i41) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i42 = 23;
            Options.classnameProperty(properties, Options.PROP_ERROR_LISTENER, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i42) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i43 = 24;
            Options.classnameProperty(properties, Options.PROP_TIME_TRACE_LOGGER, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i43) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i44 = 25;
            Options.classnameProperty(properties, Options.PROP_CONNECTION_CB, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i44) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i45 = 26;
            Options.classnameProperty(properties, Options.PROP_READ_LISTENER_CLASS, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i45) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i46 = 27;
            Options.classnameProperty(properties, Options.PROP_STATISTICS_COLLECTOR, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i46) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i47 = 28;
            Options.stringProperty(properties, Options.PROP_DATA_PORT_TYPE, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i47) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i48 = 29;
            Options.stringProperty(properties, Options.PROP_INBOX_PREFIX, new java.util.function.Consumer(this) { // from class: qj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i48) {
                        case 0:
                            this.b.lambda$properties$13((String) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$14((String) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$15((Boolean) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$16((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$17((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$18((String) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$0((String) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$19((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$20((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$21((Boolean) obj);
                            break;
                        case 10:
                            this.b.lambda$properties$22((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$23((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$24((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.lambda$properties$25((Boolean) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$26((Integer) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$1((char[]) obj);
                            break;
                        case 16:
                            this.b.lambda$properties$30((Long) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$32((Integer) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$34((Integer) obj);
                            break;
                        case 19:
                            this.b.lambda$properties$35((Integer) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$38((Integer) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$2((char[]) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$39((Boolean) obj);
                            break;
                        case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                            this.b.lambda$properties$40(obj);
                            break;
                        case 24:
                            this.b.lambda$properties$41(obj);
                            break;
                        case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                            this.b.lambda$properties$42(obj);
                            break;
                        case 26:
                            this.b.lambda$properties$43(obj);
                            break;
                        case 27:
                            this.b.lambda$properties$44(obj);
                            break;
                        case 28:
                            this.b.lambda$properties$45((String) obj);
                            break;
                        default:
                            this.b.inboxPrefix((String) obj);
                            break;
                    }
                }
            });
            final int i49 = 0;
            Options.intGtEqZeroProperty(properties, Options.PROP_MAX_MESSAGES_IN_OUTGOING_QUEUE, Options.DEFAULT_MAX_MESSAGES_IN_OUTGOING_QUEUE, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i49) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i50 = 1;
            Options.booleanProperty(properties, Options.PROP_DISCARD_MESSAGES_WHEN_OUTGOING_QUEUE_FULL, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i50) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i51 = 3;
            Options.booleanProperty(properties, Options.PROP_IGNORE_DISCOVERED_SERVERS, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i51) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i52 = 4;
            Options.booleanProperty(properties, Options.PROP_TLS_FIRST, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i52) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i53 = 5;
            Options.booleanProperty(properties, Options.PROP_USE_TIMEOUT_EXCEPTION, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i53) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i54 = 6;
            Options.booleanProperty(properties, Options.PROP_USE_DISPATCHER_WITH_EXECUTOR, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i54) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i55 = 7;
            Options.booleanProperty(properties, Options.PROP_FORCE_FLUSH_ON_REQUEST, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i55) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i56 = 8;
            Options.classnameProperty(properties, Options.PROP_SERVERS_POOL_IMPLEMENTATION_CLASS, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i56) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i57 = 9;
            Options.classnameProperty(properties, Options.PROP_DISPATCHER_FACTORY_CLASS, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i57) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i58 = 10;
            Options.classnameProperty(properties, Options.PROP_EXECUTOR_SERVICE_CLASS, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i58) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i59 = 11;
            Options.classnameProperty(properties, Options.PROP_SCHEDULED_EXECUTOR_SERVICE_CLASS, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i59) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i60 = 12;
            Options.classnameProperty(properties, Options.PROP_CONNECT_THREAD_FACTORY_CLASS, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i60) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            final int i61 = 15;
            Options.classnameProperty(properties, Options.PROP_CALLBACK_THREAD_FACTORY_CLASS, new java.util.function.Consumer(this) { // from class: sj0
                public final /* synthetic */ Options.Builder b;

                {
                    this.b = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i61) {
                        case 0:
                            this.b.lambda$properties$46((Integer) obj);
                            break;
                        case 1:
                            this.b.lambda$properties$47((Boolean) obj);
                            break;
                        case 2:
                            this.b.lambda$properties$3((char[]) obj);
                            break;
                        case 3:
                            this.b.lambda$properties$48((Boolean) obj);
                            break;
                        case 4:
                            this.b.lambda$properties$49((Boolean) obj);
                            break;
                        case 5:
                            this.b.lambda$properties$50((Boolean) obj);
                            break;
                        case 6:
                            this.b.lambda$properties$51((Boolean) obj);
                            break;
                        case 7:
                            this.b.lambda$properties$52((Boolean) obj);
                            break;
                        case 8:
                            this.b.lambda$properties$53(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_9 /* 9 */:
                            this.b.lambda$properties$54(obj);
                            break;
                        case 10:
                            this.b.lambda$properties$55(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_11 /* 11 */:
                            this.b.lambda$properties$56(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_12 /* 12 */:
                            this.b.lambda$properties$57(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_13 /* 13 */:
                            this.b.server((String) obj);
                            break;
                        case WorkDatabaseVersions.VERSION_14 /* 14 */:
                            this.b.lambda$properties$4(obj);
                            break;
                        case WorkDatabaseVersions.VERSION_15 /* 15 */:
                            this.b.lambda$properties$58(obj);
                            break;
                        case 16:
                            this.b.lambda$properties$5((Boolean) obj);
                            break;
                        case 17:
                            this.b.lambda$properties$6((Boolean) obj);
                            break;
                        case 18:
                            this.b.lambda$properties$7(obj);
                            break;
                        case 19:
                            this.b.lambda$properties$8((String) obj);
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            this.b.lambda$properties$9((char[]) obj);
                            break;
                        case 21:
                            this.b.lambda$properties$10((String) obj);
                            break;
                        case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                            this.b.lambda$properties$11((char[]) obj);
                            break;
                        default:
                            this.b.lambda$properties$12((String) obj);
                            break;
                    }
                }
            });
            return this;
        }

        public Builder proxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        public Builder readListener(ReadListener readListener) {
            this.readListener = readListener;
            return this;
        }

        public Builder reconnectBufferSize(long j) {
            this.reconnectBufferSize = j;
            return this;
        }

        public Builder reconnectDelayHandler(ReconnectDelayHandler reconnectDelayHandler) {
            this.reconnectDelayHandler = reconnectDelayHandler;
            return this;
        }

        public Builder reconnectJitter(Duration duration) {
            this.reconnectJitter = duration;
            return this;
        }

        public Builder reconnectJitterTls(Duration duration) {
            this.reconnectJitterTls = duration;
            return this;
        }

        public Builder reconnectWait(Duration duration) {
            this.reconnectWait = duration;
            return this;
        }

        public Builder reportNoResponders() {
            this.reportNoResponders = true;
            return this;
        }

        public Builder requestCleanupInterval(Duration duration) {
            this.requestCleanupInterval = duration;
            return this;
        }

        public Builder scheduledExecutor(ScheduledExecutorService scheduledExecutorService) {
            this.scheduledExecutor = scheduledExecutorService;
            return this;
        }

        public Builder secure() throws NoSuchAlgorithmException {
            this.useDefaultTls = true;
            return this;
        }

        public Builder server(String str) {
            return servers(str.trim().split(","));
        }

        public Builder serverPool(ServerPool serverPool) {
            this.serverPool = serverPool;
            return this;
        }

        public Builder servers(String[] strArr) {
            for (String str : strArr) {
                if (str != null && !str.isEmpty()) {
                    try {
                        String strTrim = str.trim();
                        NatsUri natsUri = new NatsUri(strTrim);
                        if (!this.natsServerUris.contains(natsUri)) {
                            this.natsServerUris.add(natsUri);
                            this.unprocessedServers.add(strTrim);
                        }
                    } catch (URISyntaxException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }
            return this;
        }

        public Builder socketReadTimeoutMillis(int i) {
            this.socketReadTimeoutMillis = i;
            return this;
        }

        public Builder socketSoLinger(int i) {
            this.socketSoLinger = i;
            return this;
        }

        public Builder socketWriteTimeout(long j) {
            this.socketWriteTimeout = Duration.ofMillis(j);
            return this;
        }

        public Builder sslContext(SSLContext sSLContext) {
            this.sslContext = sSLContext;
            return this;
        }

        public Builder sslContextFactory(SSLContextFactory sSLContextFactory) {
            this.sslContextFactory = sSLContextFactory;
            return this;
        }

        public Builder statisticsCollector(StatisticsCollector statisticsCollector) {
            this.statisticsCollector = statisticsCollector;
            return this;
        }

        public Builder supportUTF8Subjects() {
            this.supportUTF8Subjects = true;
            return this;
        }

        public Builder timeTraceLogger(TimeTraceLogger timeTraceLogger) {
            this.timeTraceLogger = timeTraceLogger;
            return this;
        }

        public Builder tlsAlgorithm(String str) {
            this.tlsAlgorithm = Validator.emptyOrNullAs(str, SSLUtils.DEFAULT_TLS_ALGORITHM);
            return this;
        }

        public Builder tlsFirst() {
            this.tlsFirst = true;
            return this;
        }

        @Deprecated
        public Builder token(String str) {
            this.tokenSupplier = new DefaultTokenSupplier(str);
            return this;
        }

        public Builder tokenSupplier(Supplier<char[]> supplier) {
            if (supplier == null) {
                supplier = new DefaultTokenSupplier();
            }
            this.tokenSupplier = supplier;
            return this;
        }

        public Builder traceConnection() {
            this.traceConnection = true;
            return this;
        }

        public Builder truststorePassword(char[] cArr) {
            if (cArr == null || cArr.length == 0) {
                cArr = null;
            }
            this.truststorePassword = cArr;
            return this;
        }

        public Builder truststorePath(String str) {
            this.truststore = Validator.emptyAsNull(str);
            return this;
        }

        public Builder turnOnAdvancedStats() {
            this.trackAdvancedStats = true;
            return this;
        }

        public Builder useDispatcherWithExecutor() {
            this.useDispatcherWithExecutor = true;
            return this;
        }

        public Builder useTimeoutException() {
            this.useTimeoutException = true;
            return this;
        }

        public Builder userInfo(String str, String str2) {
            this.username = str.toCharArray();
            this.password = str2.toCharArray();
            return this;
        }

        public Builder verbose() {
            this.verbose = true;
            return this;
        }

        public Builder connectionTimeout(long j) {
            this.connectionTimeout = Duration.ofMillis(j);
            return this;
        }

        public Builder socketWriteTimeout(Duration duration) {
            this.socketWriteTimeout = duration;
            return this;
        }

        public Builder token(char[] cArr) {
            this.tokenSupplier = new DefaultTokenSupplier(cArr);
            return this;
        }

        public Builder userInfo(char[] cArr, char[] cArr2) {
            this.username = cArr;
            this.password = cArr2;
            return this;
        }

        public Builder(Properties properties) throws IllegalArgumentException {
            this.natsServerUris = new ArrayList();
            this.unprocessedServers = new ArrayList();
            this.noRandomize = false;
            this.noResolveHostnames = false;
            this.reportNoResponders = false;
            this.connectionName = null;
            this.verbose = false;
            this.pedantic = false;
            this.sslContext = null;
            this.sslContextFactory = null;
            this.maxControlLine = Options.DEFAULT_MAX_CONTROL_LINE;
            this.maxReconnect = 60;
            this.reconnectWait = Options.DEFAULT_RECONNECT_WAIT;
            this.reconnectJitter = Options.DEFAULT_RECONNECT_JITTER;
            this.reconnectJitterTls = Options.DEFAULT_RECONNECT_JITTER_TLS;
            this.connectionTimeout = Options.DEFAULT_CONNECTION_TIMEOUT;
            this.socketReadTimeoutMillis = 0;
            this.socketWriteTimeout = Options.DEFAULT_SOCKET_WRITE_TIMEOUT;
            this.socketSoLinger = -1;
            this.pingInterval = Options.DEFAULT_PING_INTERVAL;
            this.requestCleanupInterval = Options.DEFAULT_REQUEST_CLEANUP_INTERVAL;
            this.maxPingsOut = 2;
            this.reconnectBufferSize = 8388608L;
            this.username = null;
            this.password = null;
            this.tokenSupplier = new DefaultTokenSupplier();
            this.useOldRequestStyle = false;
            this.bufferSize = Options.DEFAULT_BUFFER_SIZE;
            this.trackAdvancedStats = false;
            this.traceConnection = false;
            this.noEcho = false;
            this.noHeaders = false;
            this.noNoResponders = false;
            this.clientSideLimitChecks = true;
            this.supportUTF8Subjects = false;
            this.inboxPrefix = Options.DEFAULT_INBOX_PREFIX;
            this.maxMessagesInOutgoingQueue = Options.DEFAULT_MAX_MESSAGES_IN_OUTGOING_QUEUE;
            this.discardMessagesWhenOutgoingQueueFull = false;
            this.ignoreDiscoveredServers = false;
            this.tlsFirst = false;
            this.useTimeoutException = false;
            this.useDispatcherWithExecutor = false;
            this.forceFlushOnRequest = true;
            this.serverPool = null;
            this.dispatcherFactory = null;
            this.errorListener = null;
            this.timeTraceLogger = null;
            this.connectionListener = null;
            this.readListener = null;
            this.statisticsCollector = null;
            this.dataPortType = Options.DEFAULT_DATA_PORT_TYPE;
            this.tlsAlgorithm = SSLUtils.DEFAULT_TLS_ALGORITHM;
            properties(properties);
        }

        public Builder(String str) throws IOException {
            this.natsServerUris = new ArrayList();
            this.unprocessedServers = new ArrayList();
            this.noRandomize = false;
            this.noResolveHostnames = false;
            this.reportNoResponders = false;
            this.connectionName = null;
            this.verbose = false;
            this.pedantic = false;
            this.sslContext = null;
            this.sslContextFactory = null;
            this.maxControlLine = Options.DEFAULT_MAX_CONTROL_LINE;
            this.maxReconnect = 60;
            this.reconnectWait = Options.DEFAULT_RECONNECT_WAIT;
            this.reconnectJitter = Options.DEFAULT_RECONNECT_JITTER;
            this.reconnectJitterTls = Options.DEFAULT_RECONNECT_JITTER_TLS;
            this.connectionTimeout = Options.DEFAULT_CONNECTION_TIMEOUT;
            this.socketReadTimeoutMillis = 0;
            this.socketWriteTimeout = Options.DEFAULT_SOCKET_WRITE_TIMEOUT;
            this.socketSoLinger = -1;
            this.pingInterval = Options.DEFAULT_PING_INTERVAL;
            this.requestCleanupInterval = Options.DEFAULT_REQUEST_CLEANUP_INTERVAL;
            this.maxPingsOut = 2;
            this.reconnectBufferSize = 8388608L;
            this.username = null;
            this.password = null;
            this.tokenSupplier = new DefaultTokenSupplier();
            this.useOldRequestStyle = false;
            this.bufferSize = Options.DEFAULT_BUFFER_SIZE;
            this.trackAdvancedStats = false;
            this.traceConnection = false;
            this.noEcho = false;
            this.noHeaders = false;
            this.noNoResponders = false;
            this.clientSideLimitChecks = true;
            this.supportUTF8Subjects = false;
            this.inboxPrefix = Options.DEFAULT_INBOX_PREFIX;
            this.maxMessagesInOutgoingQueue = Options.DEFAULT_MAX_MESSAGES_IN_OUTGOING_QUEUE;
            this.discardMessagesWhenOutgoingQueueFull = false;
            this.ignoreDiscoveredServers = false;
            this.tlsFirst = false;
            this.useTimeoutException = false;
            this.useDispatcherWithExecutor = false;
            this.forceFlushOnRequest = true;
            this.serverPool = null;
            this.dispatcherFactory = null;
            this.errorListener = null;
            this.timeTraceLogger = null;
            this.connectionListener = null;
            this.readListener = null;
            this.statisticsCollector = null;
            this.dataPortType = Options.DEFAULT_DATA_PORT_TYPE;
            this.tlsAlgorithm = SSLUtils.DEFAULT_TLS_ALGORITHM;
            Properties properties = new Properties();
            properties.load(Files.newInputStream(Paths.get(str, new String[0]), new OpenOption[0]));
            properties(properties);
        }

        public Builder(Options options) {
            ArrayList arrayList = new ArrayList();
            this.natsServerUris = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.unprocessedServers = arrayList2;
            this.noRandomize = false;
            this.noResolveHostnames = false;
            this.reportNoResponders = false;
            this.connectionName = null;
            this.verbose = false;
            this.pedantic = false;
            this.sslContext = null;
            this.sslContextFactory = null;
            this.maxControlLine = Options.DEFAULT_MAX_CONTROL_LINE;
            this.maxReconnect = 60;
            this.reconnectWait = Options.DEFAULT_RECONNECT_WAIT;
            this.reconnectJitter = Options.DEFAULT_RECONNECT_JITTER;
            this.reconnectJitterTls = Options.DEFAULT_RECONNECT_JITTER_TLS;
            this.connectionTimeout = Options.DEFAULT_CONNECTION_TIMEOUT;
            this.socketReadTimeoutMillis = 0;
            this.socketWriteTimeout = Options.DEFAULT_SOCKET_WRITE_TIMEOUT;
            this.socketSoLinger = -1;
            this.pingInterval = Options.DEFAULT_PING_INTERVAL;
            this.requestCleanupInterval = Options.DEFAULT_REQUEST_CLEANUP_INTERVAL;
            this.maxPingsOut = 2;
            this.reconnectBufferSize = 8388608L;
            this.username = null;
            this.password = null;
            this.tokenSupplier = new DefaultTokenSupplier();
            this.useOldRequestStyle = false;
            this.bufferSize = Options.DEFAULT_BUFFER_SIZE;
            this.trackAdvancedStats = false;
            this.traceConnection = false;
            this.noEcho = false;
            this.noHeaders = false;
            this.noNoResponders = false;
            this.clientSideLimitChecks = true;
            this.supportUTF8Subjects = false;
            this.inboxPrefix = Options.DEFAULT_INBOX_PREFIX;
            this.maxMessagesInOutgoingQueue = Options.DEFAULT_MAX_MESSAGES_IN_OUTGOING_QUEUE;
            this.discardMessagesWhenOutgoingQueueFull = false;
            this.ignoreDiscoveredServers = false;
            this.tlsFirst = false;
            this.useTimeoutException = false;
            this.useDispatcherWithExecutor = false;
            this.forceFlushOnRequest = true;
            this.serverPool = null;
            this.dispatcherFactory = null;
            this.errorListener = null;
            this.timeTraceLogger = null;
            this.connectionListener = null;
            this.readListener = null;
            this.statisticsCollector = null;
            this.dataPortType = Options.DEFAULT_DATA_PORT_TYPE;
            this.tlsAlgorithm = SSLUtils.DEFAULT_TLS_ALGORITHM;
            if (options != null) {
                arrayList.addAll(options.natsServerUris);
                arrayList2.addAll(options.unprocessedServers);
                this.noRandomize = options.noRandomize;
                this.noResolveHostnames = options.noResolveHostnames;
                this.reportNoResponders = options.reportNoResponders;
                this.connectionName = options.connectionName;
                this.verbose = options.verbose;
                this.pedantic = options.pedantic;
                this.sslContext = options.sslContext;
                this.maxReconnect = options.maxReconnect;
                this.reconnectWait = options.reconnectWait;
                this.reconnectJitter = options.reconnectJitter;
                this.reconnectJitterTls = options.reconnectJitterTls;
                this.connectionTimeout = options.connectionTimeout;
                this.socketReadTimeoutMillis = options.socketReadTimeoutMillis;
                this.socketWriteTimeout = options.socketWriteTimeout;
                this.socketSoLinger = options.socketSoLinger;
                this.pingInterval = options.pingInterval;
                this.requestCleanupInterval = options.requestCleanupInterval;
                this.maxPingsOut = options.maxPingsOut;
                this.reconnectBufferSize = options.reconnectBufferSize;
                this.username = options.username;
                this.password = options.password;
                this.tokenSupplier = options.tokenSupplier;
                this.useOldRequestStyle = options.useOldRequestStyle;
                this.maxControlLine = options.maxControlLine;
                this.bufferSize = options.bufferSize;
                this.noEcho = options.noEcho;
                this.noHeaders = options.noHeaders;
                this.noNoResponders = options.noNoResponders;
                this.clientSideLimitChecks = options.clientSideLimitChecks;
                this.supportUTF8Subjects = options.supportUTF8Subjects;
                this.inboxPrefix = options.inboxPrefix;
                this.traceConnection = options.traceConnection;
                this.maxMessagesInOutgoingQueue = options.maxMessagesInOutgoingQueue;
                this.discardMessagesWhenOutgoingQueueFull = options.discardMessagesWhenOutgoingQueueFull;
                this.authHandler = options.authHandler;
                this.reconnectDelayHandler = options.reconnectDelayHandler;
                this.errorListener = options.errorListener;
                this.timeTraceLogger = options.timeTraceLogger;
                this.connectionListener = options.connectionListener;
                this.readListener = options.readListener;
                this.statisticsCollector = options.statisticsCollector;
                this.dataPortType = options.dataPortType;
                this.trackAdvancedStats = options.trackAdvancedStats;
                this.executor = options.executor;
                this.scheduledExecutor = options.scheduledExecutor;
                this.callbackThreadFactory = options.callbackThreadFactory;
                this.connectThreadFactory = options.connectThreadFactory;
                this.httpRequestInterceptors = options.httpRequestInterceptors;
                this.proxy = options.proxy;
                this.ignoreDiscoveredServers = options.ignoreDiscoveredServers;
                this.tlsFirst = options.tlsFirst;
                this.useTimeoutException = options.useTimeoutException;
                this.useDispatcherWithExecutor = options.useDispatcherWithExecutor;
                this.forceFlushOnRequest = options.forceFlushOnRequest;
                this.serverPool = options.serverPool;
                this.dispatcherFactory = options.dispatcherFactory;
                return;
            }
            throw new IllegalArgumentException("Options cannot be null");
        }
    }

    public static class DefaultThreadFactory implements ThreadFactory {
        String name;
        AtomicInteger threadNo = new AtomicInteger(0);

        public DefaultThreadFactory(String str) {
            this.name = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.name + ":" + this.threadNo.incrementAndGet());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public static class DefaultTokenSupplier implements Supplier<char[]> {
        final char[] token;

        public DefaultTokenSupplier() {
            this.token = null;
        }

        @Override // java.util.function.Supplier
        public char[] get() {
            return this.token;
        }

        public DefaultTokenSupplier(char[] cArr) {
            this.token = (cArr == null || cArr.length == 0) ? null : cArr;
        }

        public DefaultTokenSupplier(String str) {
            String strEmptyAsNull = Validator.emptyAsNull(str);
            this.token = strEmptyAsNull == null ? null : strEmptyAsNull.toCharArray();
        }
    }

    private static void _appendOptionEnd(CharBuffer charBuffer, boolean z) {
        if (z) {
            charBuffer.append('\"');
        }
    }

    private static void _appendStart(CharBuffer charBuffer, String str, boolean z, boolean z2) {
        if (z2) {
            charBuffer.append(',');
        }
        charBuffer.append('\"');
        charBuffer.append((CharSequence) str);
        charBuffer.append('\"');
        charBuffer.append(':');
        _appendOptionEnd(charBuffer, z);
    }

    private static void appendOption(CharBuffer charBuffer, String str, String str2, boolean z, boolean z2) {
        _appendStart(charBuffer, str, z, z2);
        charBuffer.append((CharSequence) str2);
        _appendOptionEnd(charBuffer, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void booleanProperty(Properties properties, String str, java.util.function.Consumer<Boolean> consumer) {
        String propertyValue = getPropertyValue(properties, str);
        if (propertyValue != null) {
            consumer.accept(Boolean.valueOf(Boolean.parseBoolean(propertyValue)));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void charArrayProperty(Properties properties, String str, java.util.function.Consumer<char[]> consumer) {
        String propertyValue = getPropertyValue(properties, str);
        if (propertyValue != null) {
            consumer.accept(propertyValue.toCharArray());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void classnameProperty(Properties properties, String str, java.util.function.Consumer<Object> consumer) {
        stringProperty(properties, str, new to(3, consumer));
    }

    private static Object createInstanceOf(String str) {
        try {
            return Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void durationProperty(Properties properties, String str, Duration duration, java.util.function.Consumer<Duration> consumer) {
        String propertyValue = getPropertyValue(properties, str);
        if (propertyValue == null) {
            consumer.accept(duration);
            return;
        }
        try {
            Duration duration2 = Duration.parse(propertyValue);
            if (duration2.toNanos() < 0) {
                consumer.accept(duration);
            } else {
                consumer.accept(duration2);
            }
        } catch (DateTimeParseException unused) {
            int i = Integer.parseInt(propertyValue);
            if (i < 0) {
                consumer.accept(duration);
            } else {
                consumer.accept(Duration.ofMillis(i));
            }
        }
    }

    private static String getPropertyValue(Properties properties, String str) {
        String strEmptyAsNull = Validator.emptyAsNull(properties.getProperty(str));
        if (strEmptyAsNull != null) {
            return strEmptyAsNull;
        }
        if (str.startsWith(PFX)) {
            return Validator.emptyAsNull(properties.getProperty(str.substring(PFX_LEN)));
        }
        String strEmptyAsNull2 = Validator.emptyAsNull(properties.getProperty(PFX.concat(str)));
        return (strEmptyAsNull2 == null && str.contains("_")) ? getPropertyValue(properties, str.replace("_", NatsConstants.DOT)) : strEmptyAsNull2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void intGtEqZeroProperty(Properties properties, String str, int i, java.util.function.Consumer<Integer> consumer) {
        String propertyValue = getPropertyValue(properties, str);
        if (propertyValue == null) {
            consumer.accept(Integer.valueOf(i));
            return;
        }
        int i2 = Integer.parseInt(propertyValue);
        if (i2 < 0) {
            consumer.accept(Integer.valueOf(i));
        } else {
            consumer.accept(Integer.valueOf(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void intProperty(Properties properties, String str, int i, java.util.function.Consumer<Integer> consumer) {
        String propertyValue = getPropertyValue(properties, str);
        if (propertyValue == null) {
            consumer.accept(Integer.valueOf(i));
        } else {
            consumer.accept(Integer.valueOf(Integer.parseInt(propertyValue)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$classnameProperty$0(java.util.function.Consumer consumer, String str) {
        consumer.accept(createInstanceOf(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void longProperty(Properties properties, String str, long j, java.util.function.Consumer<Long> consumer) {
        String propertyValue = getPropertyValue(properties, str);
        if (propertyValue == null) {
            consumer.accept(Long.valueOf(j));
        } else {
            consumer.accept(Long.valueOf(Long.parseLong(propertyValue)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void stringProperty(Properties properties, String str, java.util.function.Consumer<String> consumer) {
        String propertyValue = getPropertyValue(properties, str);
        if (propertyValue != null) {
            consumer.accept(propertyValue);
        }
    }

    public DataPort buildDataPort() {
        DataPort socketDataPort = this.dataPortType.equals(DEFAULT_DATA_PORT_TYPE) ? this.socketWriteTimeout == null ? new SocketDataPort() : new SocketDataPortWithWriteTimeout() : (DataPort) createInstanceOf(this.dataPortType);
        socketDataPort.afterConstruct(this);
        return socketDataPort;
    }

    public CharBuffer buildProtocolConnectOptionsString(String str, boolean z, byte[] bArr) {
        String strUriDecode;
        String strUriDecode2;
        String strUriDecode3;
        String rawUserInfo;
        CharBuffer charBufferAllocate = CharBuffer.allocate(this.maxControlLine);
        charBufferAllocate.append((CharSequence) "{");
        appendOption(charBufferAllocate, OPTION_LANG, Nats.CLIENT_LANGUAGE, true, false);
        appendOption(charBufferAllocate, "version", Nats.CLIENT_VERSION, true, true);
        String str2 = this.connectionName;
        if (str2 != null) {
            appendOption(charBufferAllocate, "name", str2, true, true);
        }
        appendOption(charBufferAllocate, OPTION_PROTOCOL, "1", false, true);
        appendOption(charBufferAllocate, OPTION_VERBOSE, String.valueOf(isVerbose()), false, true);
        appendOption(charBufferAllocate, OPTION_PEDANTIC, String.valueOf(isPedantic()), false, true);
        appendOption(charBufferAllocate, "tls_required", String.valueOf(isTLSRequired()), false, true);
        appendOption(charBufferAllocate, OPTION_ECHO, String.valueOf(!isNoEcho()), false, true);
        appendOption(charBufferAllocate, "headers", String.valueOf(!isNoHeaders()), false, true);
        appendOption(charBufferAllocate, OPTION_NORESPONDERS, String.valueOf(!isNoNoResponders()), false, true);
        if (z) {
            if (bArr != null && getAuthHandler() != null) {
                char[] id = getAuthHandler().getID();
                byte[] bArrSign = getAuthHandler().sign(bArr);
                char[] jwt = getAuthHandler().getJWT();
                if (bArrSign == null) {
                    bArrSign = new byte[0];
                }
                if (jwt == null) {
                    jwt = new char[0];
                }
                if (id == null) {
                    id = new char[0];
                }
                String strBase64UrlEncodeToString = Encoding.base64UrlEncodeToString(bArrSign);
                appendOption(charBufferAllocate, OPTION_NKEY, id, true);
                appendOption(charBufferAllocate, OPTION_SIG, strBase64UrlEncodeToString, true, true);
                appendOption(charBufferAllocate, OPTION_JWT, jwt, true);
            }
            String str3 = null;
            try {
                rawUserInfo = createURIForServer(str).getRawUserInfo();
            } catch (URISyntaxException unused) {
                strUriDecode = null;
            }
            if (rawUserInfo != null) {
                int iIndexOf = rawUserInfo.indexOf(":");
                if (iIndexOf == -1) {
                    strUriDecode3 = Encoding.uriDecode(rawUserInfo);
                    strUriDecode2 = null;
                } else {
                    strUriDecode = Encoding.uriDecode(rawUserInfo.substring(0, iIndexOf));
                    try {
                        strUriDecode2 = Encoding.uriDecode(rawUserInfo.substring(iIndexOf + 1));
                        strUriDecode3 = null;
                    } catch (URISyntaxException unused2) {
                        strUriDecode2 = null;
                        strUriDecode3 = null;
                    }
                    str3 = strUriDecode;
                }
            } else {
                strUriDecode2 = null;
                strUriDecode3 = null;
            }
            if (str3 != null) {
                appendOption(charBufferAllocate, OPTION_USER, Encoding.jsonEncode(str3), true, true);
            } else {
                char[] cArr = this.username;
                if (cArr != null) {
                    appendOption(charBufferAllocate, OPTION_USER, Encoding.jsonEncode(cArr), true, true);
                }
            }
            if (strUriDecode2 != null) {
                appendOption(charBufferAllocate, OPTION_PASSWORD, Encoding.jsonEncode(strUriDecode2), true, true);
            } else {
                char[] cArr2 = this.password;
                if (cArr2 != null) {
                    appendOption(charBufferAllocate, OPTION_PASSWORD, Encoding.jsonEncode(cArr2), true, true);
                }
            }
            if (strUriDecode3 != null) {
                appendOption(charBufferAllocate, OPTION_AUTH_TOKEN, strUriDecode3, true, true);
            } else {
                char[] cArr3 = this.tokenSupplier.get();
                if (cArr3 != null) {
                    appendOption(charBufferAllocate, OPTION_AUTH_TOKEN, cArr3, true);
                }
            }
        }
        charBufferAllocate.append((CharSequence) JsonUtils.CLOSE);
        charBufferAllocate.flip();
        return charBufferAllocate;
    }

    public boolean clientSideLimitChecks() {
        return this.clientSideLimitChecks;
    }

    public URI createURIForServer(String str) throws URISyntaxException {
        return new NatsUri(str).getUri();
    }

    public boolean forceFlushOnRequest() {
        return this.forceFlushOnRequest;
    }

    public AuthHandler getAuthHandler() {
        return this.authHandler;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public ExecutorService getCallbackExecutor() {
        ThreadFactory threadFactory = this.callbackThreadFactory;
        return threadFactory == null ? DEFAULT_SINGLE_THREAD_EXECUTOR.get() : Executors.newSingleThreadExecutor(threadFactory);
    }

    public ExecutorService getConnectExecutor() {
        ThreadFactory threadFactory = this.connectThreadFactory;
        return threadFactory == null ? DEFAULT_SINGLE_THREAD_EXECUTOR.get() : Executors.newSingleThreadExecutor(threadFactory);
    }

    public ConnectionListener getConnectionListener() {
        return this.connectionListener;
    }

    public String getConnectionName() {
        return this.connectionName;
    }

    public Duration getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public String getDataPortType() {
        return this.dataPortType;
    }

    public DispatcherFactory getDispatcherFactory() {
        return this.dispatcherFactory;
    }

    public ErrorListener getErrorListener() {
        return this.errorListener;
    }

    public ExecutorService getExecutor() {
        return this.executor;
    }

    public List<java.util.function.Consumer<HttpRequest>> getHttpRequestInterceptors() {
        List<java.util.function.Consumer<HttpRequest>> list = this.httpRequestInterceptors;
        return list == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(list);
    }

    public String getInboxPrefix() {
        return this.inboxPrefix;
    }

    public int getMaxControlLine() {
        return this.maxControlLine;
    }

    public int getMaxMessagesInOutgoingQueue() {
        return this.maxMessagesInOutgoingQueue;
    }

    public int getMaxPingsOut() {
        return this.maxPingsOut;
    }

    public int getMaxReconnect() {
        return this.maxReconnect;
    }

    public List<NatsUri> getNatsServerUris() {
        return this.natsServerUris;
    }

    @Deprecated
    public String getPassword() {
        char[] cArr = this.password;
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    public char[] getPasswordChars() {
        return this.password;
    }

    public Duration getPingInterval() {
        return this.pingInterval;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public ReadListener getReadListener() {
        return this.readListener;
    }

    public long getReconnectBufferSize() {
        return this.reconnectBufferSize;
    }

    public ReconnectDelayHandler getReconnectDelayHandler() {
        return this.reconnectDelayHandler;
    }

    public Duration getReconnectJitter() {
        return this.reconnectJitter;
    }

    public Duration getReconnectJitterTls() {
        return this.reconnectJitterTls;
    }

    public Duration getReconnectWait() {
        return this.reconnectWait;
    }

    public Duration getRequestCleanupInterval() {
        return this.requestCleanupInterval;
    }

    public ScheduledExecutorService getScheduledExecutor() {
        return this.scheduledExecutor;
    }

    public ServerPool getServerPool() {
        return this.serverPool;
    }

    public List<URI> getServers() {
        ArrayList arrayList = new ArrayList();
        Iterator<NatsUri> it = this.natsServerUris.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getUri());
        }
        return arrayList;
    }

    public int getSocketReadTimeoutMillis() {
        return this.socketReadTimeoutMillis;
    }

    public int getSocketSoLinger() {
        return this.socketSoLinger;
    }

    public Duration getSocketWriteTimeout() {
        return this.socketWriteTimeout;
    }

    public SSLContext getSslContext() {
        return this.sslContext;
    }

    public StatisticsCollector getStatisticsCollector() {
        return this.statisticsCollector;
    }

    public TimeTraceLogger getTimeTraceLogger() {
        return this.timeTraceLogger;
    }

    @Deprecated
    public String getToken() {
        char[] cArr = this.tokenSupplier.get();
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    public char[] getTokenChars() {
        return this.tokenSupplier.get();
    }

    public List<String> getUnprocessedServers() {
        return this.unprocessedServers;
    }

    @Deprecated
    public String getUsername() {
        char[] cArr = this.username;
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    public char[] getUsernameChars() {
        return this.username;
    }

    public boolean isDiscardMessagesWhenOutgoingQueueFull() {
        return this.discardMessagesWhenOutgoingQueueFull;
    }

    public boolean isIgnoreDiscoveredServers() {
        return this.ignoreDiscoveredServers;
    }

    public boolean isNoEcho() {
        return this.noEcho;
    }

    public boolean isNoHeaders() {
        return this.noHeaders;
    }

    public boolean isNoNoResponders() {
        return this.noNoResponders;
    }

    public boolean isNoRandomize() {
        return this.noRandomize;
    }

    public boolean isNoResolveHostnames() {
        return this.noResolveHostnames;
    }

    public boolean isOldRequestStyle() {
        return this.useOldRequestStyle;
    }

    public boolean isPedantic() {
        return this.pedantic;
    }

    public boolean isReportNoResponders() {
        return this.reportNoResponders;
    }

    public boolean isTLSRequired() {
        return this.sslContext != null;
    }

    public boolean isTlsFirst() {
        return this.tlsFirst;
    }

    public boolean isTraceConnection() {
        return this.traceConnection;
    }

    public boolean isTrackAdvancedStats() {
        return this.trackAdvancedStats;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    @Deprecated
    public void setOldRequestStyle(boolean z) {
        this.useOldRequestStyle = z;
    }

    public boolean supportUTF8Subjects() {
        return this.supportUTF8Subjects;
    }

    public boolean useDispatcherWithExecutor() {
        return this.useDispatcherWithExecutor;
    }

    public boolean useTimeoutException() {
        return this.useTimeoutException;
    }

    private Options(Builder builder) {
        this.natsServerUris = Collections.unmodifiableList(builder.natsServerUris);
        this.unprocessedServers = Collections.unmodifiableList(builder.unprocessedServers);
        this.noRandomize = builder.noRandomize;
        this.noResolveHostnames = builder.noResolveHostnames;
        this.reportNoResponders = builder.reportNoResponders;
        this.connectionName = builder.connectionName;
        this.verbose = builder.verbose;
        this.pedantic = builder.pedantic;
        this.sslContext = builder.sslContext;
        this.maxReconnect = builder.maxReconnect;
        this.reconnectWait = builder.reconnectWait;
        this.reconnectJitter = builder.reconnectJitter;
        this.reconnectJitterTls = builder.reconnectJitterTls;
        this.connectionTimeout = builder.connectionTimeout;
        this.socketReadTimeoutMillis = builder.socketReadTimeoutMillis;
        this.socketWriteTimeout = builder.socketWriteTimeout;
        this.socketSoLinger = builder.socketSoLinger;
        this.pingInterval = builder.pingInterval;
        this.requestCleanupInterval = builder.requestCleanupInterval;
        this.maxPingsOut = builder.maxPingsOut;
        this.reconnectBufferSize = builder.reconnectBufferSize;
        this.username = builder.username;
        this.password = builder.password;
        this.tokenSupplier = builder.tokenSupplier;
        this.useOldRequestStyle = builder.useOldRequestStyle;
        this.maxControlLine = builder.maxControlLine;
        this.bufferSize = builder.bufferSize;
        this.noEcho = builder.noEcho;
        this.noHeaders = builder.noHeaders;
        this.noNoResponders = builder.noNoResponders;
        this.clientSideLimitChecks = builder.clientSideLimitChecks;
        this.supportUTF8Subjects = builder.supportUTF8Subjects;
        this.inboxPrefix = builder.inboxPrefix;
        this.traceConnection = builder.traceConnection;
        this.maxMessagesInOutgoingQueue = builder.maxMessagesInOutgoingQueue;
        this.discardMessagesWhenOutgoingQueueFull = builder.discardMessagesWhenOutgoingQueueFull;
        this.authHandler = builder.authHandler;
        this.reconnectDelayHandler = builder.reconnectDelayHandler;
        this.errorListener = builder.errorListener;
        this.timeTraceLogger = builder.timeTraceLogger;
        this.connectionListener = builder.connectionListener;
        this.readListener = builder.readListener;
        this.statisticsCollector = builder.statisticsCollector;
        this.dataPortType = builder.dataPortType;
        this.trackAdvancedStats = builder.trackAdvancedStats;
        this.executor = builder.executor;
        this.scheduledExecutor = builder.scheduledExecutor;
        this.callbackThreadFactory = builder.callbackThreadFactory;
        this.connectThreadFactory = builder.connectThreadFactory;
        this.httpRequestInterceptors = builder.httpRequestInterceptors;
        this.proxy = builder.proxy;
        this.ignoreDiscoveredServers = builder.ignoreDiscoveredServers;
        this.tlsFirst = builder.tlsFirst;
        this.useTimeoutException = builder.useTimeoutException;
        this.useDispatcherWithExecutor = builder.useDispatcherWithExecutor;
        this.forceFlushOnRequest = builder.forceFlushOnRequest;
        this.serverPool = builder.serverPool;
        this.dispatcherFactory = builder.dispatcherFactory;
    }

    private static void appendOption(CharBuffer charBuffer, String str, char[] cArr, boolean z) {
        _appendStart(charBuffer, str, true, z);
        charBuffer.put(cArr);
        _appendOptionEnd(charBuffer, true);
    }
}
