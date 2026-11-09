package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;

/* JADX INFO: loaded from: classes.dex */
public class MessageDeleteRequest implements JsonSerializable {
    private final boolean erase;
    private final long sequence;

    public static class Builder {
        private long seq = -1;
        private boolean erase = true;

        public MessageDeleteRequest build() {
            return new MessageDeleteRequest(this.seq, this.erase);
        }

        public Builder erase() {
            this.erase = true;
            return this;
        }

        public Builder noErase() {
            this.erase = false;
            return this;
        }

        public Builder sequence(long j) {
            this.seq = j;
            return this;
        }
    }

    public MessageDeleteRequest(long j, boolean z) {
        this.sequence = j;
        this.erase = z;
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getSequence() {
        return this.sequence;
    }

    public boolean isErase() {
        return this.erase;
    }

    public boolean isNoErase() {
        return !this.erase;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.SEQ, Long.valueOf(this.sequence));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.NO_ERASE, Boolean.valueOf(isNoErase()));
        return JsonUtils.endJson(sbBeginJson).toString();
    }
}
