package io.nats.client;

import io.nats.client.support.Validator;
import java.time.Duration;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class PublishOptions {
    public static final String PROP_PUBLISH_TIMEOUT = "io.nats.client.publish.timeout";
    public static final String PROP_STREAM_NAME = "io.nats.client.publish.stream";
    public static final long UNSET_LAST_SEQUENCE = -1;
    private final String expectedLastId;
    private final long expectedLastSeq;
    private final long expectedLastSubSeq;
    private final String expectedStream;
    private final MessageTtl messageTtl;
    private final String msgId;
    private final String stream;
    private final Duration streamTimeout;
    public static final Duration DEFAULT_TIMEOUT = Options.DEFAULT_CONNECTION_TIMEOUT;
    public static final String UNSET_STREAM = null;

    public static Builder builder() {
        return new Builder();
    }

    public String getExpectedLastMsgId() {
        return this.expectedLastId;
    }

    public long getExpectedLastSequence() {
        return this.expectedLastSeq;
    }

    public long getExpectedLastSubjectSequence() {
        return this.expectedLastSubSeq;
    }

    public String getExpectedStream() {
        return this.expectedStream;
    }

    public String getMessageId() {
        return this.msgId;
    }

    public String getMessageTtl() {
        MessageTtl messageTtl = this.messageTtl;
        if (messageTtl == null) {
            return null;
        }
        return messageTtl.getTtlString();
    }

    public String getStream() {
        return this.stream;
    }

    public Duration getStreamTimeout() {
        return this.streamTimeout;
    }

    public String toString() {
        return "PublishOptions{stream='" + this.stream + "', streamTimeout=" + this.streamTimeout + ", expectedStream='" + this.expectedStream + "', expectedLastId='" + this.expectedLastId + "', expectedLastSeq=" + this.expectedLastSeq + ", expectedLastSubSeq=" + this.expectedLastSubSeq + ", msgId='" + this.msgId + "', messageTtl=" + getMessageTtl() + '}';
    }

    private PublishOptions(Builder builder) {
        this.stream = builder.stream;
        this.streamTimeout = builder.streamTimeout;
        this.expectedStream = builder.expectedStream;
        this.expectedLastId = builder.expectedLastId;
        this.expectedLastSeq = builder.expectedLastSeq;
        this.expectedLastSubSeq = builder.expectedLastSubSeq;
        this.msgId = builder.msgId;
        this.messageTtl = builder.messageTtl;
    }

    public static class Builder {
        String expectedLastId;
        long expectedLastSeq;
        long expectedLastSubSeq;
        String expectedStream;
        MessageTtl messageTtl;
        String msgId;
        String stream;
        Duration streamTimeout;

        public Builder() {
            this.stream = PublishOptions.UNSET_STREAM;
            this.streamTimeout = PublishOptions.DEFAULT_TIMEOUT;
            this.expectedLastSeq = -1L;
            this.expectedLastSubSeq = -1L;
        }

        public PublishOptions build() {
            return new PublishOptions(this);
        }

        public Builder clearExpected() {
            this.expectedLastId = null;
            this.expectedLastSeq = -1L;
            this.expectedLastSubSeq = -1L;
            this.msgId = null;
            return this;
        }

        public Builder expectedLastMsgId(String str) {
            this.expectedLastId = Validator.emptyAsNull(str);
            return this;
        }

        public Builder expectedLastSequence(long j) {
            this.expectedLastSeq = Validator.validateGtEqMinus1(j, "Last Sequence");
            return this;
        }

        public Builder expectedLastSubjectSequence(long j) {
            this.expectedLastSubSeq = Validator.validateGtEqMinus1(j, "Last Subject Sequence");
            return this;
        }

        public Builder expectedStream(String str) {
            this.expectedStream = Validator.validateStreamName(str, false);
            return this;
        }

        public Builder messageId(String str) {
            this.msgId = Validator.emptyAsNull(str);
            return this;
        }

        public Builder messageTtl(MessageTtl messageTtl) {
            this.messageTtl = messageTtl;
            return this;
        }

        public Builder messageTtlCustom(String str) {
            this.messageTtl = Validator.nullOrEmpty(str) ? null : MessageTtl.custom(str);
            return this;
        }

        public Builder messageTtlNever() {
            this.messageTtl = MessageTtl.never();
            return this;
        }

        public Builder messageTtlSeconds(int i) {
            this.messageTtl = i < 1 ? null : MessageTtl.seconds(i);
            return this;
        }

        public Builder stream(String str) {
            this.stream = Validator.validateStreamName(str, false);
            return this;
        }

        public Builder streamTimeout(Duration duration) {
            this.streamTimeout = Validator.validateDurationNotRequiredGtOrEqZero(duration, PublishOptions.DEFAULT_TIMEOUT);
            return this;
        }

        public Builder(Properties properties) {
            this.stream = PublishOptions.UNSET_STREAM;
            this.streamTimeout = PublishOptions.DEFAULT_TIMEOUT;
            this.expectedLastSeq = -1L;
            this.expectedLastSubSeq = -1L;
            String property = properties.getProperty(PublishOptions.PROP_PUBLISH_TIMEOUT);
            if (property != null) {
                this.streamTimeout = Duration.parse(property);
            }
            String property2 = properties.getProperty(PublishOptions.PROP_STREAM_NAME);
            if (property2 != null) {
                this.stream = property2;
            }
        }
    }
}
