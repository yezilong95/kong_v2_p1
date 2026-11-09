package io.nats.client.support;

import defpackage.zn;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Status {
    public static final int BAD_JS_REQUEST_CODE = 408;
    public static String BAD_REQUEST = "Bad Request";
    public static final int BAD_REQUEST_CODE = 400;
    public static String BATCH_COMPLETED = "Batch Completed";
    private static final Map<Integer, String> CODE_TO_TEXT;
    public static final int CONFLICT_CODE = 409;
    public static String CONSUMER_DELETED = "Consumer Deleted";
    public static String CONSUMER_IS_PUSH_BASED = "Consumer is push based";
    public static String EXCEEDED_MAX_PREFIX = "Exceeded Max";
    public static String EXCEEDED_MAX_REQUEST_BATCH = "Exceeded MaxRequestBatch";
    public static String EXCEEDED_MAX_REQUEST_EXPIRES = "Exceeded MaxRequestExpires";
    public static String EXCEEDED_MAX_REQUEST_MAX_BYTES = "Exceeded MaxRequestMaxBytes";
    public static String EXCEEDED_MAX_WAITING = "Exceeded MaxWaiting";
    public static final String FLOW_CONTROL_TEXT = "FlowControl Request";
    public static final int FLOW_OR_HEARTBEAT_STATUS_CODE = 100;
    public static final String HEARTBEAT_TEXT = "Idle Heartbeat";
    public static String LEADERSHIP_CHANGE = "Leadership Change";
    public static String MESSAGE_SIZE_EXCEEDS_MAX_BYTES = "Message Size Exceeds MaxBytes";
    public static String NO_MESSAGES = "No Messages";
    public static final int NO_RESPONDERS_CODE = 503;
    public static final String NO_RESPONDERS_TEXT = "No Responders Available For Request";
    public static final int REQUEST_TIMEOUT_CODE = 408;
    public static String SERVER_SHUTDOWN = "Server Shutdown";
    private final int code;
    private final String message;
    public static final int EOB_CODE = 204;
    public static final String EOB_TEXT = "EOB";
    public static final Status EOB = new Status(EOB_CODE, EOB_TEXT);
    public static final int NOT_FOUND_CODE = 404;
    public static final Status TIMEOUT_OR_NO_MESSAGES = new Status(NOT_FOUND_CODE, "Timeout or No Messages");

    static {
        HashMap map = new HashMap();
        CODE_TO_TEXT = map;
        map.put(Integer.valueOf(NO_RESPONDERS_CODE), NO_RESPONDERS_TEXT);
    }

    public Status(int i, String str) {
        this.code = i;
        this.message = str == null ? makeMessage(i) : str;
    }

    private static int extractCode(Token token) {
        try {
            return Integer.parseInt(token.getValue());
        } catch (Exception unused) {
            throw new IllegalArgumentException(NatsConstants.INVALID_HEADER_STATUS_CODE);
        }
    }

    private static String extractMessage(Token token) {
        if (token.hasValue()) {
            return token.getValue();
        }
        return null;
    }

    private String makeMessage(int i) {
        String str = CODE_TO_TEXT.get(Integer.valueOf(i));
        return str == null ? zn.j(i, "Server Status Message: ") : str;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMessageWithCode() {
        return this.code + NatsConstants.SPACE + this.message;
    }

    public boolean isEob() {
        return this.code == 204 && this.message.equals(EOB_TEXT);
    }

    public boolean isFlowControl() {
        return this.code == 100 && this.message.equals(FLOW_CONTROL_TEXT);
    }

    public boolean isHeartbeat() {
        return this.code == 100 && this.message.equals(HEARTBEAT_TEXT);
    }

    public boolean isNoResponders() {
        return this.code == 503 && this.message.equals(NO_RESPONDERS_TEXT);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Status{code=");
        sb.append(this.code);
        sb.append(", message='");
        return zn.o(sb, this.message, "'}");
    }

    public Status(Token token, Token token2) {
        this(extractCode(token), extractMessage(token2));
    }
}
