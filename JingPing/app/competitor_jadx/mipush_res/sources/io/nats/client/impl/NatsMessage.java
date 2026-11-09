package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Subscription;
import io.nats.client.support.ByteArrayBuilder;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.Status;
import io.nats.client.support.Validator;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class NatsMessage implements Message {
    private static final Headers EMPTY_READ_ONLY = new Headers(null, true, null);
    protected static final String NOT_A_JET_STREAM_MESSAGE = "Message is not a JetStream message";
    protected int controlLineLength;
    protected byte[] data;
    protected int dataLen;
    protected boolean flushImmediatelyAfterPublish;
    protected int headerLen;
    protected Headers headers;
    protected AckType lastAck;
    protected NatsMessage next;
    ByteArrayBuilder protocolBab;
    protected String replyTo;
    protected String sid;
    protected int sizeInBytes;
    protected String subject;
    protected NatsSubscription subscription;

    public static class Builder {
        private byte[] data;
        private Headers headers;
        private String replyTo;
        private String subject;

        public NatsMessage build() {
            return new NatsMessage(this.subject, this.replyTo, this.headers, this.data);
        }

        public Builder data(String str) {
            if (str != null) {
                this.data = str.getBytes(StandardCharsets.UTF_8);
            }
            return this;
        }

        public Builder headers(Headers headers) {
            this.headers = headers;
            return this;
        }

        public Builder replyTo(String str) {
            this.replyTo = str;
            return this;
        }

        public Builder subject(String str) {
            this.subject = str;
            return this;
        }

        @Deprecated
        public Builder utf8mode(boolean z) {
            return this;
        }

        public Builder data(String str, Charset charset) {
            this.data = str.getBytes(charset);
            return this;
        }

        public Builder data(byte[] bArr) {
            this.data = bArr;
            return this;
        }
    }

    public NatsMessage() {
        this((byte[]) null);
    }

    public static Builder builder() {
        return new Builder();
    }

    private String dataToString() {
        byte[] bArr = this.data;
        if (bArr.length == 0) {
            return "<no data>";
        }
        String str = new String(bArr, StandardCharsets.UTF_8);
        int iIndexOf = str.indexOf("io.nats.jetstream.api");
        if (iIndexOf != -1) {
            return str.substring(iIndexOf, str.indexOf(34, iIndexOf));
        }
        if (str.length() <= 27) {
            return str;
        }
        return str.substring(0, 27) + "...";
    }

    private String headersToString() {
        return hasHeaders() ? new String(this.headers.getSerialized(), StandardCharsets.US_ASCII).replace("\r", Marker.ANY_NON_NULL_MARKER).replace("\n", Marker.ANY_NON_NULL_MARKER) : "";
    }

    private String nextToString() {
        return this.next == null ? "No" : "Yes";
    }

    private String protocolBytesToString() {
        ByteArrayBuilder byteArrayBuilder = this.protocolBab;
        if (byteArrayBuilder == null) {
            return null;
        }
        return byteArrayBuilder.toString();
    }

    private String replyToString() {
        String str = this.replyTo;
        return str == null ? "<no reply>" : str;
    }

    @Override // io.nats.client.Message
    public void ack() {
    }

    @Override // io.nats.client.Message
    public void ackSync(Duration duration) throws InterruptedException, TimeoutException {
    }

    public void calculate() {
        String str = this.replyTo;
        int length = str == null ? 0 : str.length();
        Headers headers = this.headers;
        if (headers == null) {
            this.headerLen = 0;
        } else if (headers.isEmpty()) {
            this.headers = EMPTY_READ_ONLY;
            this.headerLen = 0;
        } else {
            Headers headers2 = this.headers.isReadOnly() ? this.headers : new Headers(this.headers, true, null);
            this.headers = headers2;
            this.headerLen = headers2.serializedLength();
        }
        int i = this.headerLen + this.dataLen;
        int length2 = (this.subject.length() * 2) + 32 + length + i;
        Charset charset = StandardCharsets.UTF_8;
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(length2, charset);
        if (this.headerLen > 0) {
            byteArrayBuilder.append(NatsConstants.HPUB_SP_BYTES, 0, NatsConstants.HPUB_SP_BYTES_LEN);
        } else {
            byteArrayBuilder.append(NatsConstants.PUB_SP_BYTES, 0, NatsConstants.PUB_SP_BYTES_LEN);
        }
        byteArrayBuilder.append(this.subject.getBytes(charset)).append(NatsConstants.SP);
        if (length > 0) {
            byteArrayBuilder.append(this.replyTo.getBytes(charset)).append(NatsConstants.SP);
        }
        int i2 = this.headerLen;
        if (i2 > 0) {
            byteArrayBuilder.append(Integer.toString(i2).getBytes(StandardCharsets.US_ASCII)).append(NatsConstants.SP);
        }
        byteArrayBuilder.append(Integer.toString(i).getBytes(StandardCharsets.US_ASCII));
        this.protocolBab = byteArrayBuilder;
        int length3 = byteArrayBuilder.length() + 2;
        this.controlLineLength = length3;
        this.sizeInBytes = length3 + i + 2;
    }

    @Override // io.nats.client.Message
    public long consumeByteCount() {
        String str = this.subject;
        if (str == null) {
            return 0L;
        }
        int length = str.length() + this.headerLen + this.dataLen;
        return length + (this.replyTo == null ? 0 : r1.length());
    }

    public int copyNotEmptyHeaders(int i, byte[] bArr) {
        calculate();
        if (this.headerLen > 0) {
            return this.headers.serializeToArray(i, bArr);
        }
        return 0;
    }

    @Override // io.nats.client.Message
    public Connection getConnection() {
        NatsSubscription natsSubscription = this.subscription;
        if (natsSubscription == null) {
            return null;
        }
        return natsSubscription.connection;
    }

    public int getControlLineLength() {
        calculate();
        return this.controlLineLength;
    }

    @Override // io.nats.client.Message
    public byte[] getData() {
        return this.data;
    }

    @Override // io.nats.client.Message
    public Headers getHeaders() {
        return this.headers;
    }

    public NatsSubscription getNatsSubscription() {
        return this.subscription;
    }

    public ByteArrayBuilder getProtocolBab() {
        calculate();
        return this.protocolBab;
    }

    public byte[] getProtocolBytes() {
        calculate();
        return this.protocolBab.toByteArray();
    }

    @Override // io.nats.client.Message
    public String getReplyTo() {
        return this.replyTo;
    }

    @Override // io.nats.client.Message
    public String getSID() {
        return this.sid;
    }

    public long getSizeInBytes() {
        calculate();
        return this.sizeInBytes;
    }

    @Override // io.nats.client.Message
    public Status getStatus() {
        return null;
    }

    @Override // io.nats.client.Message
    public String getSubject() {
        return this.subject;
    }

    @Override // io.nats.client.Message
    public Subscription getSubscription() {
        return this.subscription;
    }

    @Override // io.nats.client.Message
    public boolean hasHeaders() {
        Headers headers = this.headers;
        return (headers == null || headers.isEmpty()) ? false : true;
    }

    @Override // io.nats.client.Message
    public void inProgress() {
    }

    @Override // io.nats.client.Message
    public boolean isJetStream() {
        return false;
    }

    public boolean isProtocol() {
        return false;
    }

    public boolean isProtocolFilterOnStop() {
        return false;
    }

    @Override // io.nats.client.Message
    public boolean isStatusMessage() {
        return false;
    }

    @Override // io.nats.client.Message
    public boolean isUtf8mode() {
        return false;
    }

    @Override // io.nats.client.Message
    public AckType lastAck() {
        return this.lastAck;
    }

    @Override // io.nats.client.Message
    public NatsJetStreamMetaData metaData() {
        throw new IllegalStateException(NOT_A_JET_STREAM_MESSAGE);
    }

    @Override // io.nats.client.Message
    public void nak() {
    }

    @Override // io.nats.client.Message
    public void nakWithDelay(long j) {
    }

    public void setSubscription(NatsSubscription natsSubscription) {
        this.subscription = natsSubscription;
    }

    @Override // io.nats.client.Message
    public void term() {
    }

    public String toDetailString() {
        return "NatsMessage:\n  subject='" + this.subject + "'\n  replyTo='" + replyToString() + "'\n  data=" + dataToString() + "\n  headers=" + headersToString() + "\n  sid='" + this.sid + "'\n  protocolBytes=" + protocolBytesToString() + "\n  sizeInBytes=" + this.sizeInBytes + "\n  headerLen=" + this.headerLen + "\n  dataLen=" + this.dataLen + "\n  subscription=" + this.subscription + "\n  next=" + nextToString();
    }

    public String toString() {
        if (this.subject == null) {
            return getClass().getSimpleName() + " | " + protocolBytesToString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" |");
        sb.append(this.subject);
        sb.append("|");
        sb.append(replyToString());
        sb.append("|");
        return zn.o(sb, dataToString(), "|");
    }

    public NatsMessage(byte[] bArr) {
        bArr = bArr == null ? NatsConstants.EMPTY_BODY : bArr;
        this.data = bArr;
        this.dataLen = bArr.length;
    }

    @Override // io.nats.client.Message
    public void nakWithDelay(Duration duration) {
    }

    @Deprecated
    public NatsMessage(String str, String str2, byte[] bArr, boolean z) {
        this(str, str2, (Headers) null, bArr);
    }

    @Deprecated
    public NatsMessage(String str, String str2, Headers headers, byte[] bArr, boolean z) {
        this(str, str2, headers, bArr);
    }

    public NatsMessage(String str, String str2, byte[] bArr) {
        this(str, str2, (Headers) null, bArr);
    }

    public NatsMessage(String str, String str2, Headers headers, byte[] bArr) {
        this(bArr);
        this.subject = Validator.validateSubject(str, true);
        this.replyTo = Validator.validateReplyTo(str2, false);
        this.headers = headers;
    }

    public NatsMessage(Message message) {
        this(message.getData());
        this.subject = message.getSubject();
        this.replyTo = message.getReplyTo();
        this.headers = message.getHeaders();
    }
}
