package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonParseException;
import io.nats.client.support.JsonParser;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.Validator;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class OrderedConsumerConfiguration implements JsonSerializable {
    private String consumerNamePrefix;
    private DeliverPolicy deliverPolicy;
    private final List<String> filterSubjects;
    private Boolean headersOnly;
    private ReplayPolicy replayPolicy;
    private Long startSequence;
    private ZonedDateTime startTime;

    public OrderedConsumerConfiguration() {
        this.startSequence = -1L;
        ArrayList arrayList = new ArrayList();
        this.filterSubjects = arrayList;
        arrayList.add(NatsConstants.GREATER_THAN);
    }

    public OrderedConsumerConfiguration consumerNamePrefix(String str) {
        this.consumerNamePrefix = Validator.emptyAsNull(str);
        return this;
    }

    public OrderedConsumerConfiguration deliverPolicy(DeliverPolicy deliverPolicy) {
        this.deliverPolicy = deliverPolicy;
        return this;
    }

    public OrderedConsumerConfiguration filterSubject(String str) {
        return filterSubjects(Collections.singletonList(str));
    }

    public OrderedConsumerConfiguration filterSubjects(String... strArr) {
        return strArr == null ? filterSubjects((List<String>) null) : filterSubjects(Arrays.asList(strArr));
    }

    public String getConsumerNamePrefix() {
        return this.consumerNamePrefix;
    }

    public DeliverPolicy getDeliverPolicy() {
        return this.deliverPolicy;
    }

    public String getFilterSubject() {
        List<String> list = this.filterSubjects;
        if (list == null || list.size() != 1) {
            return null;
        }
        return this.filterSubjects.get(0);
    }

    public List<String> getFilterSubjects() {
        return this.filterSubjects;
    }

    public Boolean getHeadersOnly() {
        return this.headersOnly;
    }

    public ReplayPolicy getReplayPolicy() {
        return this.replayPolicy;
    }

    public Long getStartSequence() {
        return this.startSequence;
    }

    public ZonedDateTime getStartTime() {
        return this.startTime;
    }

    public boolean hasMultipleFilterSubjects() {
        List<String> list = this.filterSubjects;
        return list != null && list.size() > 1;
    }

    public OrderedConsumerConfiguration headersOnly(Boolean bool) {
        this.headersOnly = (bool == null || !bool.booleanValue()) ? null : Boolean.TRUE;
        return this;
    }

    public OrderedConsumerConfiguration replayPolicy(ReplayPolicy replayPolicy) {
        this.replayPolicy = replayPolicy;
        return this;
    }

    public OrderedConsumerConfiguration startSequence(long j) {
        if (j < 1) {
            j = -1;
        }
        this.startSequence = Long.valueOf(j);
        return this;
    }

    public OrderedConsumerConfiguration startTime(ZonedDateTime zonedDateTime) {
        this.startTime = zonedDateTime;
        return this;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addStrings(sbBeginJson, ApiConstants.FILTER_SUBJECTS, this.filterSubjects);
        DeliverPolicy deliverPolicy = this.deliverPolicy;
        if (deliverPolicy != null) {
            JsonUtils.addField(sbBeginJson, ApiConstants.DELIVER_POLICY, deliverPolicy.toString());
        }
        JsonUtils.addFieldWhenGtZero(sbBeginJson, ApiConstants.OPT_START_SEQ, this.startSequence);
        JsonUtils.addField(sbBeginJson, ApiConstants.OPT_START_TIME, this.startTime);
        ReplayPolicy replayPolicy = this.replayPolicy;
        if (replayPolicy != null) {
            JsonUtils.addField(sbBeginJson, ApiConstants.REPLAY_POLICY, replayPolicy.toString());
        }
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.HEADERS_ONLY, this.headersOnly);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public OrderedConsumerConfiguration filterSubjects(List<String> list) {
        this.filterSubjects.clear();
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String strEmptyAsNull = Validator.emptyAsNull(it.next());
                if (strEmptyAsNull != null) {
                    this.filterSubjects.add(strEmptyAsNull);
                }
            }
        }
        if (this.filterSubjects.isEmpty()) {
            this.filterSubjects.add(NatsConstants.GREATER_THAN);
        }
        return this;
    }

    public OrderedConsumerConfiguration(String str) throws JsonParseException {
        this(JsonParser.parse(str));
    }

    public OrderedConsumerConfiguration(JsonValue jsonValue) throws JsonParseException {
        this();
        filterSubjects(JsonValueUtils.readStringList(jsonValue, ApiConstants.FILTER_SUBJECTS));
        deliverPolicy(DeliverPolicy.get(JsonValueUtils.readString(jsonValue, ApiConstants.DELIVER_POLICY)));
        startSequence(JsonValueUtils.readLong(jsonValue, ApiConstants.OPT_START_SEQ, -1L));
        startTime(JsonValueUtils.readDate(jsonValue, ApiConstants.OPT_START_TIME));
        replayPolicy(ReplayPolicy.get(JsonValueUtils.readString(jsonValue, ApiConstants.REPLAY_POLICY)));
        headersOnly(JsonValueUtils.readBoolean(jsonValue, ApiConstants.HEADERS_ONLY, null));
    }
}
