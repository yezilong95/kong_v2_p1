package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.Validator;

/* JADX INFO: loaded from: classes.dex */
public class StreamInfoOptions implements JsonSerializable {
    private final boolean deletedDetails;
    private final String subjectsFilter;

    public static class Builder {
        private boolean deletedDetails;
        private String subjectsFilter;

        public Builder allSubjects() {
            this.subjectsFilter = NatsConstants.GREATER_THAN;
            return this;
        }

        public StreamInfoOptions build() {
            return new StreamInfoOptions(this.subjectsFilter, this.deletedDetails);
        }

        public Builder deletedDetails() {
            this.deletedDetails = true;
            return this;
        }

        public Builder filterSubjects(String str) {
            this.subjectsFilter = Validator.emptyAsNull(str);
            return this;
        }
    }

    public static StreamInfoOptions allSubjects() {
        return new Builder().allSubjects().build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static StreamInfoOptions deletedDetails() {
        return new Builder().deletedDetails().build();
    }

    public static StreamInfoOptions filterSubjects(String str) {
        return new Builder().filterSubjects(str).build();
    }

    public String getSubjectsFilter() {
        return this.subjectsFilter;
    }

    public boolean isDeletedDetails() {
        return this.deletedDetails;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.SUBJECTS_FILTER, this.subjectsFilter);
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.DELETED_DETAILS, Boolean.valueOf(this.deletedDetails));
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    private StreamInfoOptions(String str, boolean z) {
        this.subjectsFilter = str;
        this.deletedDetails = z;
    }
}
