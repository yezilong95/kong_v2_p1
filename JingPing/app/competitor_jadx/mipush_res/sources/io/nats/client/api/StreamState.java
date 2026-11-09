package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class StreamState {
    private final long bytes;
    private final long consumerCount;
    private final long deletedCount;
    private final List<Long> deletedStreamSequences;
    private final long firstSeq;
    private final ZonedDateTime firstTime;
    private final long lastSeq;
    private final ZonedDateTime lastTime;
    private final LostStreamData lostStreamData;
    private final long msgs;
    private final long subjectCount;
    private final List<Subject> subjects = new ArrayList();
    private final Map<String, Long> subjectMap = new HashMap();

    public StreamState(JsonValue jsonValue) {
        Map<String, JsonValue> map;
        this.msgs = JsonValueUtils.readLong(jsonValue, ApiConstants.MESSAGES, 0L);
        this.bytes = JsonValueUtils.readLong(jsonValue, ApiConstants.BYTES, 0L);
        this.firstSeq = JsonValueUtils.readLong(jsonValue, ApiConstants.FIRST_SEQ, 0L);
        this.lastSeq = JsonValueUtils.readLong(jsonValue, ApiConstants.LAST_SEQ, 0L);
        this.consumerCount = JsonValueUtils.readLong(jsonValue, ApiConstants.CONSUMER_COUNT, 0L);
        this.firstTime = JsonValueUtils.readDate(jsonValue, ApiConstants.FIRST_TS);
        this.lastTime = JsonValueUtils.readDate(jsonValue, ApiConstants.LAST_TS);
        this.subjectCount = JsonValueUtils.readLong(jsonValue, ApiConstants.NUM_SUBJECTS, 0L);
        this.deletedCount = JsonValueUtils.readLong(jsonValue, ApiConstants.NUM_DELETED, 0L);
        this.deletedStreamSequences = JsonValueUtils.readLongList(jsonValue, ApiConstants.DELETED);
        this.lostStreamData = LostStreamData.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.LOST));
        JsonValue value = JsonValueUtils.readValue(jsonValue, ApiConstants.SUBJECTS);
        if (value == null || (map = value.map) == null) {
            return;
        }
        for (String str : map.keySet()) {
            Long l = JsonValueUtils.getLong(value.map.get(str));
            if (l != null) {
                this.subjects.add(new Subject(str, l.longValue()));
                this.subjectMap.put(str, l);
            }
        }
    }

    public long getByteCount() {
        return this.bytes;
    }

    public long getConsumerCount() {
        return this.consumerCount;
    }

    public List<Long> getDeleted() {
        return this.deletedStreamSequences;
    }

    public long getDeletedCount() {
        return this.deletedCount;
    }

    public long getFirstSequence() {
        return this.firstSeq;
    }

    public ZonedDateTime getFirstTime() {
        return this.firstTime;
    }

    public long getLastSequence() {
        return this.lastSeq;
    }

    public ZonedDateTime getLastTime() {
        return this.lastTime;
    }

    public LostStreamData getLostStreamData() {
        return this.lostStreamData;
    }

    public long getMsgCount() {
        return this.msgs;
    }

    public long getSubjectCount() {
        return this.subjectCount;
    }

    public Map<String, Long> getSubjectMap() {
        return this.subjectMap;
    }

    public List<Subject> getSubjects() {
        return this.subjects;
    }

    public String toString() {
        return "StreamState{msgs=" + this.msgs + ", bytes=" + this.bytes + ", firstSeq=" + this.firstSeq + ", lastSeq=" + this.lastSeq + ", consumerCount=" + this.consumerCount + ", firstTime=" + this.firstTime + ", lastTime=" + this.lastTime + ", subjectCount=" + this.subjectCount + ", subjects=" + this.subjects + ", deletedCount=" + this.deletedCount + ", deleteds=" + this.deletedStreamSequences + ", lostStreamData=" + this.lostStreamData + '}';
    }
}
