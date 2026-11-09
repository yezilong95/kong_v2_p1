package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.impl.Headers;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.DateTimeUtils;
import io.nats.client.support.IncomingHeadersProcessor;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.Status;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes.dex */
public class MessageInfo extends ApiResponse<MessageInfo> {
    private final byte[] data;
    private final Headers headers;
    private final long lastSeq;
    private final long numPending;
    private final long seq;
    private final Status status;
    private final String stream;
    private final String subject;
    private final ZonedDateTime time;

    @Deprecated
    public MessageInfo(Message message) {
        this(message, null, null, false);
    }

    public byte[] getData() {
        return this.data;
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public long getLastSeq() {
        return this.lastSeq;
    }

    public long getNumPending() {
        return this.numPending;
    }

    public long getSeq() {
        return this.seq;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getStream() {
        return this.stream;
    }

    public String getSubject() {
        return this.subject;
    }

    public ZonedDateTime getTime() {
        return this.time;
    }

    public boolean isEobStatus() {
        Status status = this.status;
        return status != null && status.isEob();
    }

    public boolean isErrorStatus() {
        Status status = this.status;
        return (status == null || status.isEob()) ? false : true;
    }

    public boolean isMessage() {
        return this.status == null && !hasError();
    }

    public boolean isStatus() {
        return this.status != null;
    }

    @Override // io.nats.client.api.ApiResponse
    public String toString() {
        StringBuilder sbBeginJsonPrefixed = JsonUtils.beginJsonPrefixed("\"MessageInfo\":");
        Status status = this.status;
        if (status != null) {
            JsonUtils.addField(sbBeginJsonPrefixed, "status_code", Integer.valueOf(status.getCode()));
            JsonUtils.addField(sbBeginJsonPrefixed, "status_message", this.status.getMessage());
        } else if (hasError()) {
            JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.ERROR, getError());
        } else {
            JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.SEQ, Long.valueOf(this.seq));
            JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.LAST_SEQ, Long.valueOf(this.lastSeq));
            JsonUtils.addFieldWhenGteMinusOne(sbBeginJsonPrefixed, ApiConstants.NUM_PENDING, Long.valueOf(this.numPending));
            JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.STREAM, this.stream);
            JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.SUBJECT, this.subject);
            JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.TIME, this.time);
            byte[] bArr = this.data;
            if (bArr == null) {
                JsonUtils.addRawJson(sbBeginJsonPrefixed, ApiConstants.DATA, "null");
            } else {
                JsonUtils.addField(sbBeginJsonPrefixed, "data_length", Integer.valueOf(bArr.length));
            }
            JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.HDRS, this.headers);
        }
        return JsonUtils.endJson(sbBeginJsonPrefixed).toString();
    }

    public MessageInfo(Message message, String str, boolean z) {
        this(message, null, str, z);
    }

    public MessageInfo(Status status, String str) {
        this(null, status, str, false);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private MessageInfo(Message message, Status status, String str, boolean z) {
        String string;
        byte[] base64;
        ZonedDateTime date;
        String last;
        Headers headers;
        long j;
        long jSafeParseLong;
        super(z ? null : message);
        Status status2 = null;
        if (status != null) {
            last = str;
            string = null;
            base64 = null;
            date = null;
            headers = null;
            j = -1;
            jSafeParseLong = -1;
            status2 = status;
        } else if (z) {
            Headers headers2 = message.getHeaders();
            string = headers2.getLast(NatsJetStreamConstants.NATS_SUBJECT);
            base64 = message.getData();
            long j2 = Long.parseLong(headers2.getLast(NatsJetStreamConstants.NATS_SEQUENCE));
            date = DateTimeUtils.parseDateTime(headers2.getLast(NatsJetStreamConstants.NATS_TIMESTAMP));
            last = headers2.getLast(NatsJetStreamConstants.NATS_STREAM);
            String last2 = headers2.getLast(NatsJetStreamConstants.NATS_LAST_SEQUENCE);
            jSafeParseLong = last2 != null ? JsonUtils.safeParseLong(last2, -1L) : -1L;
            String last3 = headers2.getLast(NatsJetStreamConstants.NATS_NUM_PENDING);
            j = last3 != null ? Long.parseLong(last3) - 1 : -1L;
            headers = new Headers(headers2, true, NatsJetStreamConstants.MESSAGE_INFO_HEADERS);
            j = j;
            j = j2;
        } else if (hasError()) {
            string = null;
            base64 = null;
            date = null;
            last = null;
            headers = null;
            j = -1;
            jSafeParseLong = -1;
        } else {
            JsonValue value = JsonValueUtils.readValue(this.jv, ApiConstants.MESSAGE);
            string = JsonValueUtils.readString(value, ApiConstants.SUBJECT);
            base64 = JsonValueUtils.readBase64(value, ApiConstants.DATA);
            long j3 = JsonValueUtils.readLong(value, ApiConstants.SEQ, 0L);
            date = JsonValueUtils.readDate(value, ApiConstants.TIME);
            byte[] base642 = JsonValueUtils.readBase64(value, ApiConstants.HDRS);
            last = str;
            jSafeParseLong = -1;
            headers = base642 == null ? null : new IncomingHeadersProcessor(base642).getHeaders();
            j = j3;
            j = -1;
        }
        this.subject = string;
        this.data = base64;
        this.seq = j;
        this.time = date;
        this.headers = headers;
        this.stream = last;
        this.lastSeq = jSafeParseLong;
        this.numPending = j;
        this.status = status2;
    }
}
