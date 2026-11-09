package io.nats.client.api;

import io.nats.client.JetStreamApiException;
import io.nats.client.Message;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValueUtils;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class PublishAck extends ApiResponse<PublishAck> {
    private final String domain;
    private final boolean duplicate;
    private final long seq;
    private final String stream;

    public PublishAck(Message message) throws IOException, JetStreamApiException {
        super(message);
        throwOnHasError();
        String string = JsonValueUtils.readString(this.jv, ApiConstants.STREAM);
        this.stream = string;
        if (string == null) {
            throw new IOException("Invalid JetStream ack.");
        }
        long j = JsonValueUtils.readLong(this.jv, ApiConstants.SEQ, -1L);
        this.seq = j;
        if (j < 0) {
            throw new IOException("Invalid JetStream ack.");
        }
        this.domain = JsonValueUtils.readString(this.jv, ApiConstants.DOMAIN);
        this.duplicate = JsonValueUtils.readBoolean(this.jv, ApiConstants.DUPLICATE);
    }

    public String getDomain() {
        return this.domain;
    }

    public long getSeqno() {
        return this.seq;
    }

    public String getStream() {
        return this.stream;
    }

    public boolean isDuplicate() {
        return this.duplicate;
    }
}
