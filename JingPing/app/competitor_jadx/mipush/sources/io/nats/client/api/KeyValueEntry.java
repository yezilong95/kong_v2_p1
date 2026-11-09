package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.impl.Headers;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.NatsKeyValueUtil;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes.dex */
public class KeyValueEntry {
    private final NatsKeyValueUtil.BucketAndKey bucketAndKey;
    private final ZonedDateTime created;
    private final long dataLen;
    private final long delta;
    private final KeyValueOperation op;
    private final long revision;
    private final byte[] value;

    public KeyValueEntry(MessageInfo messageInfo) {
        Headers headers = messageInfo.getHeaders();
        this.bucketAndKey = new NatsKeyValueUtil.BucketAndKey(messageInfo.getSubject());
        byte[] bArrExtractValue = extractValue(messageInfo.getData());
        this.value = bArrExtractValue;
        this.dataLen = calculateLength(bArrExtractValue, headers);
        this.created = messageInfo.getTime();
        this.revision = messageInfo.getSeq();
        this.delta = 0L;
        this.op = NatsKeyValueUtil.getOperation(headers);
    }

    private static long calculateLength(byte[] bArr, Headers headers) {
        if (bArr != null) {
            return bArr.length;
        }
        String first = headers == null ? null : headers.getFirst(NatsJetStreamConstants.MSG_SIZE_HDR);
        if (first == null) {
            return 0L;
        }
        return Long.parseLong(first);
    }

    private static byte[] extractValue(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            KeyValueEntry keyValueEntry = (KeyValueEntry) obj;
            if (this.bucketAndKey.equals(keyValueEntry.bucketAndKey) && this.revision == keyValueEntry.revision) {
                return true;
            }
        }
        return false;
    }

    public String getBucket() {
        return this.bucketAndKey.bucket;
    }

    public ZonedDateTime getCreated() {
        return this.created;
    }

    public long getDataLen() {
        return this.dataLen;
    }

    public long getDelta() {
        return this.delta;
    }

    public String getKey() {
        return this.bucketAndKey.key;
    }

    public KeyValueOperation getOperation() {
        return this.op;
    }

    public long getRevision() {
        return this.revision;
    }

    public byte[] getValue() {
        return this.value;
    }

    public Long getValueAsLong() {
        byte[] bArr = this.value;
        if (bArr == null) {
            return null;
        }
        return Long.valueOf(Long.parseLong(new String(bArr, StandardCharsets.US_ASCII)));
    }

    public String getValueAsString() {
        byte[] bArr = this.value;
        if (bArr == null) {
            return null;
        }
        return new String(bArr, StandardCharsets.UTF_8);
    }

    public int hashCode() {
        return Long.hashCode(this.revision) + (this.bucketAndKey.hashCode() * 31);
    }

    public String toString() {
        return "KvEntry{bucket='" + this.bucketAndKey.bucket + "', key='" + this.bucketAndKey.key + "', operation=" + this.op + ", revision=" + this.revision + ", delta=" + this.delta + ", dataLen=" + this.dataLen + ", created=" + this.created + '}';
    }

    public KeyValueEntry(Message message) {
        Headers headers = message.getHeaders();
        this.bucketAndKey = new NatsKeyValueUtil.BucketAndKey(message.getSubject());
        byte[] bArrExtractValue = extractValue(message.getData());
        this.value = bArrExtractValue;
        this.dataLen = calculateLength(bArrExtractValue, headers);
        this.created = message.metaData().timestamp();
        this.revision = message.metaData().streamSequence();
        this.delta = message.metaData().pendingCount();
        this.op = NatsKeyValueUtil.getOperation(headers);
    }
}
