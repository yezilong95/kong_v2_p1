package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes.dex */
public class MessageGetRequest implements JsonSerializable {
    private final String lastBySubject;
    private final String nextBySubject;
    private final long sequence;
    private final ZonedDateTime startTime;

    private MessageGetRequest(long j, String str, String str2, ZonedDateTime zonedDateTime) {
        this.sequence = j;
        this.lastBySubject = str;
        this.nextBySubject = str2;
        this.startTime = zonedDateTime;
    }

    public static MessageGetRequest firstForStartTime(ZonedDateTime zonedDateTime) {
        return new MessageGetRequest(-1L, null, null, zonedDateTime);
    }

    public static MessageGetRequest firstForStartTimeAndSubject(ZonedDateTime zonedDateTime, String str) {
        return new MessageGetRequest(-1L, null, str, zonedDateTime);
    }

    public static MessageGetRequest firstForSubject(String str) {
        return new MessageGetRequest(-1L, null, str, null);
    }

    public static MessageGetRequest forSequence(long j) {
        return new MessageGetRequest(j, null, null, null);
    }

    @Deprecated
    public static byte[] lastBySubjectBytes(String str) {
        return lastForSubject(str).serialize();
    }

    public static MessageGetRequest lastForSubject(String str) {
        return new MessageGetRequest(-1L, str, null, null);
    }

    public static MessageGetRequest nextForSubject(long j, String str) {
        return new MessageGetRequest(j, null, str, null);
    }

    @Deprecated
    public static byte[] seqBytes(long j) {
        return forSequence(j).serialize();
    }

    public String getLastBySubject() {
        return this.lastBySubject;
    }

    public String getNextBySubject() {
        return this.nextBySubject;
    }

    public long getSequence() {
        return this.sequence;
    }

    public boolean isLastBySubject() {
        return this.lastBySubject != null;
    }

    public boolean isNextBySubject() {
        return this.nextBySubject != null;
    }

    public boolean isSequenceOnly() {
        return this.sequence > 0 && this.nextBySubject == null;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.SEQ, Long.valueOf(this.sequence));
        JsonUtils.addField(sbBeginJson, ApiConstants.LAST_BY_SUBJECT, this.lastBySubject);
        JsonUtils.addField(sbBeginJson, ApiConstants.NEXT_BY_SUBJECT, this.nextBySubject);
        JsonUtils.addField(sbBeginJson, ApiConstants.START_TIME, this.startTime);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    @Deprecated
    public MessageGetRequest(long j) {
        this(j, null, null, null);
    }

    @Deprecated
    public MessageGetRequest(String str) {
        this(-1L, str, null, null);
    }
}
