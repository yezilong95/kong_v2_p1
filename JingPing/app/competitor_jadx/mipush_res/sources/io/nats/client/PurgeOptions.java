package io.nats.client;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.Validator;

/* JADX INFO: loaded from: classes.dex */
public class PurgeOptions implements JsonSerializable {
    protected final long keep;
    protected final long seq;
    protected final String subject;

    public static class Builder {
        private String subject;
        private long seq = -1;
        private long keep = -1;

        public PurgeOptions build() {
            Validator.validateSubject(this.subject, false);
            long j = this.seq;
            if (j <= 0 || this.keep <= 0) {
                return new PurgeOptions(this.subject, j, this.keep);
            }
            throw new IllegalArgumentException("seq and keep are mutually exclusive.");
        }

        public Builder keep(long j) {
            this.keep = j;
            return this;
        }

        public Builder sequence(long j) {
            this.seq = j;
            return this;
        }

        public Builder subject(String str) {
            this.subject = Validator.validateSubject(str, false);
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static PurgeOptions subject(String str) {
        return new Builder().subject(str).build();
    }

    public long getKeep() {
        return this.keep;
    }

    public long getSequence() {
        return this.seq;
    }

    public String getSubject() {
        return this.subject;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.FILTER, this.subject);
        JsonUtils.addField(sbBeginJson, ApiConstants.SEQ, Long.valueOf(this.seq));
        JsonUtils.addField(sbBeginJson, ApiConstants.KEEP, Long.valueOf(this.keep));
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    private PurgeOptions(String str, long j, long j2) {
        this.subject = str;
        this.seq = j;
        this.keep = j2;
    }
}
