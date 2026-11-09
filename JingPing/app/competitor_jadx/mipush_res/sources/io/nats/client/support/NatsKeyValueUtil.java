package io.nats.client.support;

import defpackage.zn;
import io.nats.client.Message;
import io.nats.client.MessageTtl;
import io.nats.client.PublishOptions;
import io.nats.client.api.KeyValueOperation;
import io.nats.client.impl.Headers;

/* JADX INFO: loaded from: classes.dex */
public abstract class NatsKeyValueUtil {
    public static final String KV_OPERATION_HEADER_KEY = "KV-Operation";
    public static final String KV_STREAM_PREFIX = "KV_";
    public static final int KV_STREAM_PREFIX_LEN = 3;
    public static final String KV_SUBJECT_PREFIX = "$KV.";
    public static final String KV_SUBJECT_SUFFIX = ".>";

    public static class BucketAndKey {
        public final String bucket;
        public final String key;

        public BucketAndKey(Message message) {
            this(message.getSubject());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BucketAndKey bucketAndKey = (BucketAndKey) obj;
            if (this.bucket.equals(bucketAndKey.bucket)) {
                return this.key.equals(bucketAndKey.key);
            }
            return false;
        }

        public int hashCode() {
            return this.key.hashCode() + (this.bucket.hashCode() * 31);
        }

        public BucketAndKey(String str) {
            String[] strArrSplit = str.split("\\Q.\\E", 3);
            this.bucket = strArrSplit[1];
            this.key = strArrSplit[2];
        }
    }

    private NatsKeyValueUtil() {
    }

    public static String extractBucketName(String str) {
        return str.substring(KV_STREAM_PREFIX_LEN);
    }

    public static Headers getDeleteHeaders() {
        return new Headers().put(KV_OPERATION_HEADER_KEY, KeyValueOperation.DELETE.getHeaderValue());
    }

    public static String getNatsMarkerReasonHeader(Headers headers) {
        if (headers == null) {
            return null;
        }
        return headers.getFirst(NatsJetStreamConstants.NATS_MARKER_REASON_HDR);
    }

    public static KeyValueOperation getOperation(Headers headers) {
        String natsMarkerReasonHeader;
        String operationHeader = getOperationHeader(headers);
        KeyValueOperation keyValueOperationInstance = operationHeader != null ? KeyValueOperation.instance(operationHeader) : null;
        if (keyValueOperationInstance == null && (natsMarkerReasonHeader = getNatsMarkerReasonHeader(headers)) != null) {
            keyValueOperationInstance = KeyValueOperation.instanceByMarkerReason(natsMarkerReasonHeader);
        }
        return keyValueOperationInstance == null ? KeyValueOperation.PUT : keyValueOperationInstance;
    }

    public static String getOperationHeader(Headers headers) {
        if (headers == null) {
            return null;
        }
        return headers.getFirst(KV_OPERATION_HEADER_KEY);
    }

    public static PublishOptions getPublishOptions(long j, MessageTtl messageTtl) {
        boolean z;
        PublishOptions.Builder builder = PublishOptions.builder();
        boolean z2 = false;
        if (j > -1) {
            builder.expectedLastSubjectSequence(j);
            z = false;
        } else {
            z = true;
        }
        if (messageTtl != null) {
            builder.messageTtl(messageTtl);
        } else {
            z2 = z;
        }
        if (z2) {
            return null;
        }
        return builder.build();
    }

    public static Headers getPurgeHeaders() {
        return new Headers().put(KV_OPERATION_HEADER_KEY, KeyValueOperation.PURGE.getHeaderValue()).put(NatsJetStreamConstants.ROLLUP_HDR, NatsJetStreamConstants.ROLLUP_HDR_SUBJECT);
    }

    public static boolean hasPrefix(String str) {
        return str.startsWith(KV_STREAM_PREFIX);
    }

    public static String toKeyPrefix(String str) {
        return zn.n(KV_SUBJECT_PREFIX, str, NatsConstants.DOT);
    }

    public static String toStreamName(String str) {
        return zn.v(KV_STREAM_PREFIX, str);
    }

    public static String toStreamSubject(String str) {
        return zn.n(KV_SUBJECT_PREFIX, str, ".>");
    }

    public static String trimPrefix(String str) {
        return str.startsWith(KV_STREAM_PREFIX) ? str.substring(3) : str;
    }
}
