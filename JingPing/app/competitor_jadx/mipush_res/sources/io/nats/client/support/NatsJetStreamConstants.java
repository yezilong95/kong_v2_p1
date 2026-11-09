package io.nats.client.support;

/* JADX INFO: loaded from: classes.dex */
public interface NatsJetStreamConstants {
    public static final String CONSUMER_STALLED_HDR = "Nats-Consumer-Stalled";
    public static final String DEFAULT_API_PREFIX = "$JS.API.";
    public static final String EXPECTED_LAST_MSG_ID_HDR = "Nats-Expected-Last-Msg-Id";
    public static final String EXPECTED_LAST_SEQ_HDR = "Nats-Expected-Last-Sequence";
    public static final String EXPECTED_LAST_SUB_SEQ_HDR = "Nats-Expected-Last-Subject-Sequence";
    public static final String EXPECTED_STREAM_HDR = "Nats-Expected-Stream";
    public static final String JSAPI_ACCOUNT_INFO = "INFO";
    public static final String JSAPI_CONSUMER_CREATE = "CONSUMER.CREATE.%s";
    public static final String JSAPI_CONSUMER_CREATE_V290 = "CONSUMER.CREATE.%s.%s";
    public static final String JSAPI_CONSUMER_CREATE_V290_W_FILTER = "CONSUMER.CREATE.%s.%s.%s";
    public static final String JSAPI_CONSUMER_DELETE = "CONSUMER.DELETE.%s.%s";
    public static final String JSAPI_CONSUMER_INFO = "CONSUMER.INFO.%s.%s";
    public static final String JSAPI_CONSUMER_LIST = "CONSUMER.LIST.%s";
    public static final String JSAPI_CONSUMER_MSG_NEXT = "CONSUMER.MSG.NEXT.%s.%s";
    public static final String JSAPI_CONSUMER_NAMES = "CONSUMER.NAMES.%s";
    public static final String JSAPI_CONSUMER_PAUSE = "CONSUMER.PAUSE.%s.%s";
    public static final String JSAPI_DIRECT_GET = "DIRECT.GET.%s";
    public static final String JSAPI_DIRECT_GET_LAST = "DIRECT.GET.%s.%s";
    public static final String JSAPI_DURABLE_CREATE = "CONSUMER.DURABLE.CREATE.%s.%s";
    public static final String JSAPI_MSG_DELETE = "STREAM.MSG.DELETE.%s";
    public static final String JSAPI_MSG_GET = "STREAM.MSG.GET.%s";
    public static final String JSAPI_STREAM_CREATE = "STREAM.CREATE.%s";
    public static final String JSAPI_STREAM_DELETE = "STREAM.DELETE.%s";
    public static final String JSAPI_STREAM_INFO = "STREAM.INFO.%s";
    public static final String JSAPI_STREAM_LIST = "STREAM.LIST";
    public static final String JSAPI_STREAM_NAMES = "STREAM.NAMES";
    public static final String JSAPI_STREAM_PURGE = "STREAM.PURGE.%s";
    public static final String JSAPI_STREAM_UPDATE = "STREAM.UPDATE.%s";
    public static final String JS_ACK_SUBJECT_PREFIX = "$JS.ACK.";
    public static final int JS_CONSUMER_NOT_FOUND_ERR = 10014;
    public static final int JS_NO_MESSAGE_FOUND_ERR = 10037;
    public static final int JS_WRONG_LAST_SEQUENCE = 10071;
    public static final String LAST_CONSUMER_HDR = "Nats-Last-Consumer";
    public static final String LAST_STREAM_HDR = "Nats-Last-Stream";
    public static final int MAX_HISTORY_PER_KEY = 64;

    @Deprecated
    public static final int MAX_PULL_SIZE = 256;
    public static final String MSG_ID_HDR = "Nats-Msg-Id";
    public static final String MSG_SIZE_HDR = "Nats-Msg-Size";
    public static final String MSG_TTL_HDR = "Nats-TTL";
    public static final String NATS_MARKER_REASON_HDR = "Nats-Marker-Reason";
    public static final String NATS_PENDING_BYTES = "Nats-Pending-Bytes";
    public static final String NATS_PENDING_MESSAGES = "Nats-Pending-Messages";
    public static final String PREFIX_API = "API";
    public static final String PREFIX_DOLLAR_JS_DOT = "$JS.";
    public static final String ROLLUP_HDR = "Nats-Rollup";
    public static final String ROLLUP_HDR_ALL = "all";
    public static final String ROLLUP_HDR_SUBJECT = "sub";
    public static final long SERVER_DEFAULT_DUPLICATE_WINDOW_MS = 120000;
    public static final String NATS_SUBJECT = "Nats-Subject";
    public static final String NATS_SEQUENCE = "Nats-Sequence";
    public static final String NATS_TIMESTAMP = "Nats-Time-Stamp";
    public static final String NATS_STREAM = "Nats-Stream";
    public static final String NATS_LAST_SEQUENCE = "Nats-Last-Sequence";
    public static final String NATS_NUM_PENDING = "Nats-Num-Pending";
    public static final String[] MESSAGE_INFO_HEADERS = {NATS_SUBJECT, NATS_SEQUENCE, NATS_TIMESTAMP, NATS_STREAM, NATS_LAST_SEQUENCE, NATS_NUM_PENDING};
}
