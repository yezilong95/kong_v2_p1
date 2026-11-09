package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.JetStreamApiException;
import io.nats.client.Message;
import io.nats.client.api.ApiResponse;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValueUtils;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
class ListRequestEngine extends ApiResponse<ListRequestEngine> {
    private static final String OFFSET_JSON_START = "{\"offset\":";
    protected int lastOffset;
    protected int limit;
    protected int total;

    public ListRequestEngine() {
        this.total = Integer.MAX_VALUE;
        this.limit = 0;
        this.lastOffset = 0;
    }

    private byte[] noFilterJson() {
        return (OFFSET_JSON_START + nextOffset() + JsonUtils.CLOSE).getBytes(StandardCharsets.UTF_8);
    }

    public boolean hasMore() {
        return this.total > nextOffset();
    }

    public byte[] internalNextJson() {
        if (hasMore()) {
            return noFilterJson();
        }
        return null;
    }

    public int nextOffset() {
        return this.lastOffset + this.limit;
    }

    public byte[] internalNextJson(String str, String str2) {
        if (!hasMore()) {
            return null;
        }
        if (str2 == null) {
            return noFilterJson();
        }
        StringBuilder sb = new StringBuilder(OFFSET_JSON_START);
        sb.append(nextOffset());
        sb.append(",\"");
        sb.append(str);
        sb.append("\":\"");
        return zn.o(sb, str2, "\"}").getBytes(StandardCharsets.UTF_8);
    }

    public ListRequestEngine(Message message) throws JetStreamApiException {
        super(message);
        this.total = Integer.MAX_VALUE;
        this.limit = 0;
        this.lastOffset = 0;
        if (!hasError()) {
            this.total = JsonValueUtils.readInteger(this.jv, ApiConstants.TOTAL, -1);
            this.limit = JsonValueUtils.readInteger(this.jv, ApiConstants.LIMIT, 0);
            this.lastOffset = JsonValueUtils.readInteger(this.jv, ApiConstants.OFFSET, 0);
            return;
        }
        throw new JetStreamApiException(this);
    }
}
