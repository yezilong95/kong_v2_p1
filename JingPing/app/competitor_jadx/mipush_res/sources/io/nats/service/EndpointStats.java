package io.nats.service;

import defpackage.zt;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class EndpointStats implements JsonSerializable {
    private final long averageProcessingTime;
    private final JsonValue data;
    private final String lastError;
    private final String name;
    private final long numErrors;
    private final long numRequests;
    private final long processingTime;
    private final String queueGroup;
    private final ZonedDateTime started;
    private final String subject;

    public EndpointStats(String str, String str2, String str3, long j, long j2, long j3, String str4, JsonValue jsonValue, ZonedDateTime zonedDateTime) {
        this.name = str;
        this.subject = str2;
        this.queueGroup = str3;
        this.numRequests = j;
        this.numErrors = j2;
        this.processingTime = j3;
        this.averageProcessingTime = j < 1 ? 0L : j3 / j;
        this.lastError = str4;
        this.data = jsonValue;
        this.started = zonedDateTime;
    }

    public static List<EndpointStats> listOf(JsonValue jsonValue) {
        return JsonValueUtils.listOf(jsonValue, new zt(0));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EndpointStats endpointStats = (EndpointStats) obj;
        if (this.numRequests == endpointStats.numRequests && this.numErrors == endpointStats.numErrors && this.processingTime == endpointStats.processingTime && this.averageProcessingTime == endpointStats.averageProcessingTime && Objects.equals(this.name, endpointStats.name) && Objects.equals(this.subject, endpointStats.subject) && Objects.equals(this.queueGroup, endpointStats.queueGroup) && Objects.equals(this.lastError, endpointStats.lastError) && Objects.equals(this.data, endpointStats.data)) {
            return Objects.equals(this.started, endpointStats.started);
        }
        return false;
    }

    public long getAverageProcessingTime() {
        return this.averageProcessingTime;
    }

    public JsonValue getData() {
        return this.data;
    }

    public String getDataAsJson() {
        JsonValue jsonValue = this.data;
        if (jsonValue == null) {
            return null;
        }
        return jsonValue.toJson();
    }

    public String getLastError() {
        return this.lastError;
    }

    public String getName() {
        return this.name;
    }

    public long getNumErrors() {
        return this.numErrors;
    }

    public long getNumRequests() {
        return this.numRequests;
    }

    public long getProcessingTime() {
        return this.processingTime;
    }

    public String getQueueGroup() {
        return this.queueGroup;
    }

    public ZonedDateTime getStarted() {
        return this.started;
    }

    public String getSubject() {
        return this.subject;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.subject;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.queueGroup;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        long j = this.numRequests;
        int i = (iHashCode3 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.numErrors;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.processingTime;
        int i3 = (i2 + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        long j4 = this.averageProcessingTime;
        int i4 = (i3 + ((int) (j4 ^ (j4 >>> 32)))) * 31;
        String str4 = this.lastError;
        int iHashCode4 = (i4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        JsonValue jsonValue = this.data;
        int iHashCode5 = (iHashCode4 + (jsonValue != null ? jsonValue.hashCode() : 0)) * 31;
        ZonedDateTime zonedDateTime = this.started;
        return iHashCode5 + (zonedDateTime != null ? zonedDateTime.hashCode() : 0);
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.NAME, this.name);
        JsonUtils.addField(sbBeginJson, ApiConstants.SUBJECT, this.subject);
        JsonUtils.addField(sbBeginJson, ApiConstants.QUEUE_GROUP, this.queueGroup);
        JsonUtils.addFieldWhenGtZero(sbBeginJson, ApiConstants.NUM_REQUESTS, Long.valueOf(this.numRequests));
        JsonUtils.addFieldWhenGtZero(sbBeginJson, ApiConstants.NUM_ERRORS, Long.valueOf(this.numErrors));
        JsonUtils.addFieldWhenGtZero(sbBeginJson, ApiConstants.PROCESSING_TIME, Long.valueOf(this.processingTime));
        JsonUtils.addFieldWhenGtZero(sbBeginJson, ApiConstants.AVERAGE_PROCESSING_TIME, Long.valueOf(this.averageProcessingTime));
        JsonUtils.addField(sbBeginJson, ApiConstants.LAST_ERROR, this.lastError);
        JsonUtils.addField(sbBeginJson, ApiConstants.DATA, this.data);
        JsonUtils.addField(sbBeginJson, ApiConstants.STARTED, this.started);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return JsonUtils.toKey(getClass()) + toJson();
    }

    public EndpointStats(JsonValue jsonValue) {
        this.name = JsonValueUtils.readString(jsonValue, ApiConstants.NAME);
        this.subject = JsonValueUtils.readString(jsonValue, ApiConstants.SUBJECT);
        this.queueGroup = JsonValueUtils.readString(jsonValue, ApiConstants.QUEUE_GROUP);
        this.numRequests = JsonValueUtils.readLong(jsonValue, ApiConstants.NUM_REQUESTS, 0L);
        this.numErrors = JsonValueUtils.readLong(jsonValue, ApiConstants.NUM_ERRORS, 0L);
        this.processingTime = JsonValueUtils.readLong(jsonValue, ApiConstants.PROCESSING_TIME, 0L);
        this.averageProcessingTime = JsonValueUtils.readLong(jsonValue, ApiConstants.AVERAGE_PROCESSING_TIME, 0L);
        this.lastError = JsonValueUtils.readString(jsonValue, ApiConstants.LAST_ERROR);
        this.data = JsonValueUtils.readValue(jsonValue, ApiConstants.DATA);
        this.started = JsonValueUtils.readDate(jsonValue, ApiConstants.STARTED);
    }
}
