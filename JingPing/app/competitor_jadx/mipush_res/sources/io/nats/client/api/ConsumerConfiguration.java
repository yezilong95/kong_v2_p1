package io.nats.client.api;

import io.nats.client.PullSubscribeOptions;
import io.nats.client.PushSubscribeOptions;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonParseException;
import io.nats.client.support.JsonParser;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.NatsJetStreamClientError;
import io.nats.client.support.Validator;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ConsumerConfiguration implements JsonSerializable {
    public static final long DURATION_MIN_LONG = 1;
    public static final long DURATION_UNSET_LONG = 0;
    public static final int INTEGER_UNSET = -1;
    public static final long LONG_UNSET = -1;
    public static final int MAX_DELIVER_MIN = 1;
    public static final Duration MIN_IDLE_HEARTBEAT;
    public static final long MIN_IDLE_HEARTBEAT_MILLIS;
    public static final long MIN_IDLE_HEARTBEAT_NANOS;
    public static final int STANDARD_MIN = 0;
    public static final long ULONG_UNSET = 0;
    protected final AckPolicy ackPolicy;
    protected final Duration ackWait;
    protected final List<Duration> backoff;
    protected final String deliverGroup;
    protected final DeliverPolicy deliverPolicy;
    protected final String deliverSubject;
    protected final String description;
    protected final String durable;
    protected final List<String> filterSubjects;
    protected final Boolean flowControl;
    protected final Boolean headersOnly;
    protected final Duration idleHeartbeat;
    protected final Duration inactiveThreshold;
    protected final Integer maxAckPending;
    protected final Integer maxBatch;
    protected final Integer maxBytes;
    protected final Integer maxDeliver;
    protected final Duration maxExpires;
    protected final Integer maxPullWaiting;
    protected final Boolean memStorage;
    protected final Map<String, String> metadata;
    protected final String name;
    protected final Integer numReplicas;
    protected final ZonedDateTime pauseUntil;
    protected final List<String> priorityGroups;
    protected final PriorityPolicy priorityPolicy;
    protected final Long rateLimit;
    protected final ReplayPolicy replayPolicy;
    protected final String sampleFrequency;
    protected final Long startSeq;
    protected final ZonedDateTime startTime;

    @Deprecated
    public static final Duration DURATION_MIN = Duration.ofNanos(1);
    public static final DeliverPolicy DEFAULT_DELIVER_POLICY = DeliverPolicy.All;
    public static final AckPolicy DEFAULT_ACK_POLICY = AckPolicy.Explicit;
    public static final ReplayPolicy DEFAULT_REPLAY_POLICY = ReplayPolicy.Instant;
    public static final PriorityPolicy DEFAULT_PRIORITY_POLICY = PriorityPolicy.None;
    public static final Duration DURATION_UNSET = Duration.ZERO;

    public static class Builder {
        private AckPolicy ackPolicy;
        private Duration ackWait;
        private List<Duration> backoff;
        private String deliverGroup;
        private DeliverPolicy deliverPolicy;
        private String deliverSubject;
        private String description;
        private String durable;
        private List<String> filterSubjects;
        private Boolean flowControl;
        private Boolean headersOnly;
        private Duration idleHeartbeat;
        private Duration inactiveThreshold;
        private Integer maxAckPending;
        private Integer maxBatch;
        private Integer maxBytes;
        private Integer maxDeliver;
        private Duration maxExpires;
        private Integer maxPullWaiting;
        private Boolean memStorage;
        private Map<String, String> metadata;
        private String name;
        private Integer numReplicas;
        private ZonedDateTime pauseUntil;
        private List<String> priorityGroups;
        private PriorityPolicy priorityPolicy;
        private Long rateLimit;
        private ReplayPolicy replayPolicy;
        private String sampleFrequency;
        private Long startSeq;
        private ZonedDateTime startTime;

        public Builder() {
        }

        public Builder ackPolicy(AckPolicy ackPolicy) {
            this.ackPolicy = ackPolicy;
            return this;
        }

        public Builder ackWait(Duration duration) {
            this.ackWait = ConsumerConfiguration.normalize(duration);
            return this;
        }

        public Builder backoff(Duration... durationArr) {
            if (durationArr != null) {
                if (durationArr.length != 1 || durationArr[0] != null) {
                    this.backoff = new ArrayList();
                    for (Duration duration : durationArr) {
                        if (duration != null) {
                            if (duration.toNanos() < 1) {
                                throw new IllegalArgumentException("Backoff cannot be less than 1");
                            }
                            this.backoff.add(duration);
                        }
                    }
                    return this;
                }
            }
            this.backoff = null;
            return this;
        }

        public ConsumerConfiguration build() {
            Validator.validateMustMatchIfBothSupplied(this.name, this.durable, NatsJetStreamClientError.JsConsumerNameDurableMismatch);
            return new ConsumerConfiguration(this);
        }

        public PullSubscribeOptions buildPullSubscribeOptions() {
            return PullSubscribeOptions.builder().configuration(build()).build();
        }

        public PushSubscribeOptions buildPushSubscribeOptions() {
            return PushSubscribeOptions.builder().configuration(build()).build();
        }

        public Builder deliverGroup(String str) {
            this.deliverGroup = Validator.emptyAsNull(str);
            return this;
        }

        public Builder deliverPolicy(DeliverPolicy deliverPolicy) {
            this.deliverPolicy = deliverPolicy;
            return this;
        }

        public Builder deliverSubject(String str) {
            this.deliverSubject = Validator.emptyAsNull(str);
            return this;
        }

        public Builder description(String str) {
            this.description = Validator.emptyAsNull(str);
            return this;
        }

        public Builder durable(String str) {
            this.durable = Validator.validateDurable(str, false);
            return this;
        }

        public Builder filterSubject(String str) {
            if (Validator.nullOrEmpty(str)) {
                this.filterSubjects = null;
                return this;
            }
            this.filterSubjects = Collections.singletonList(str);
            return this;
        }

        public Builder filterSubjects(String... strArr) {
            return filterSubjects(Arrays.asList(strArr));
        }

        public Builder flowControl(Duration duration) {
            this.flowControl = Boolean.TRUE;
            return idleHeartbeat(duration);
        }

        public Builder headersOnly(Boolean bool) {
            this.headersOnly = bool;
            return this;
        }

        public Builder idleHeartbeat(Duration duration) {
            if (duration == null) {
                this.idleHeartbeat = null;
                return this;
            }
            long nanos = duration.toNanos();
            if (nanos <= 0) {
                this.idleHeartbeat = ConsumerConfiguration.DURATION_UNSET;
                return this;
            }
            long j = ConsumerConfiguration.MIN_IDLE_HEARTBEAT_NANOS;
            if (nanos >= j) {
                this.idleHeartbeat = duration;
                return this;
            }
            throw new IllegalArgumentException("Duration must be greater than or equal to " + j + " nanos.");
        }

        public Builder inactiveThreshold(Duration duration) {
            this.inactiveThreshold = ConsumerConfiguration.normalize(duration);
            return this;
        }

        public Builder json(String str) throws JsonParseException {
            return jsonValue(JsonParser.parse(str));
        }

        public Builder jsonValue(JsonValue jsonValue) {
            deliverPolicy(DeliverPolicy.get(JsonValueUtils.readString(jsonValue, ApiConstants.DELIVER_POLICY)));
            ackPolicy(AckPolicy.get(JsonValueUtils.readString(jsonValue, ApiConstants.ACK_POLICY)));
            replayPolicy(ReplayPolicy.get(JsonValueUtils.readString(jsonValue, ApiConstants.REPLAY_POLICY)));
            description(JsonValueUtils.readString(jsonValue, ApiConstants.DESCRIPTION));
            durable(JsonValueUtils.readString(jsonValue, ApiConstants.DURABLE_NAME));
            name(JsonValueUtils.readString(jsonValue, ApiConstants.NAME));
            deliverSubject(JsonValueUtils.readString(jsonValue, ApiConstants.DELIVER_SUBJECT));
            deliverGroup(JsonValueUtils.readString(jsonValue, ApiConstants.DELIVER_GROUP));
            sampleFrequency(JsonValueUtils.readString(jsonValue, ApiConstants.SAMPLE_FREQ));
            startTime(JsonValueUtils.readDate(jsonValue, ApiConstants.OPT_START_TIME));
            ackWait(JsonValueUtils.readNanos(jsonValue, ApiConstants.ACK_WAIT));
            maxExpires(JsonValueUtils.readNanos(jsonValue, ApiConstants.MAX_EXPIRES));
            inactiveThreshold(JsonValueUtils.readNanos(jsonValue, ApiConstants.INACTIVE_THRESHOLD));
            startSequence(JsonValueUtils.readLong(jsonValue, ApiConstants.OPT_START_SEQ));
            maxDeliver(JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_DELIVER, -1L));
            rateLimit(JsonValueUtils.readLong(jsonValue, ApiConstants.RATE_LIMIT_BPS));
            maxAckPending(JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_ACK_PENDING));
            maxPullWaiting(JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_WAITING));
            maxBatch(JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_BATCH));
            maxBytes(JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_BYTES));
            Integer integer = JsonValueUtils.readInteger(jsonValue, ApiConstants.NUM_REPLICAS);
            if (integer != null) {
                if (integer.intValue() == 0) {
                    this.numReplicas = 0;
                } else {
                    numReplicas(integer);
                }
            }
            pauseUntil(JsonValueUtils.readDate(jsonValue, ApiConstants.PAUSE_UNTIL));
            Duration nanos = JsonValueUtils.readNanos(jsonValue, ApiConstants.IDLE_HEARTBEAT);
            if (nanos != null) {
                if (JsonValueUtils.readBoolean(jsonValue, ApiConstants.FLOW_CONTROL, Boolean.FALSE).booleanValue()) {
                    flowControl(nanos);
                } else {
                    idleHeartbeat(nanos);
                }
            }
            headersOnly(JsonValueUtils.readBoolean(jsonValue, ApiConstants.HEADERS_ONLY, null));
            memStorage(JsonValueUtils.readBoolean(jsonValue, ApiConstants.MEM_STORAGE, null));
            backoff((Duration[]) JsonValueUtils.readNanosList(jsonValue, ApiConstants.BACKOFF, false).toArray(new Duration[0]));
            metadata(JsonValueUtils.readStringStringMap(jsonValue, ApiConstants.METADATA));
            String strEmptyAsNull = Validator.emptyAsNull(JsonValueUtils.readString(jsonValue, ApiConstants.FILTER_SUBJECT));
            if (strEmptyAsNull == null) {
                filterSubjects(JsonValueUtils.readOptionalStringList(jsonValue, ApiConstants.FILTER_SUBJECTS));
            } else {
                filterSubject(strEmptyAsNull);
            }
            priorityGroups(JsonValueUtils.readOptionalStringList(jsonValue, ApiConstants.PRIORITY_GROUPS));
            priorityPolicy(PriorityPolicy.get(JsonValueUtils.readString(jsonValue, ApiConstants.PRIORITY_POLICY)));
            return this;
        }

        public Builder maxAckPending(Long l) {
            this.maxAckPending = ConsumerConfiguration.normalize(l, 0);
            return this;
        }

        public Builder maxBatch(Long l) {
            this.maxBatch = ConsumerConfiguration.normalize(l, 0);
            return this;
        }

        public Builder maxBytes(Long l) {
            this.maxBytes = ConsumerConfiguration.normalize(l, 0);
            return this;
        }

        public Builder maxDeliver(Long l) {
            this.maxDeliver = ConsumerConfiguration.normalize(l, 1);
            return this;
        }

        public Builder maxExpires(Duration duration) {
            this.maxExpires = ConsumerConfiguration.normalize(duration);
            return this;
        }

        public Builder maxPullWaiting(Long l) {
            this.maxPullWaiting = ConsumerConfiguration.normalize(l, 0);
            return this;
        }

        public Builder memStorage(Boolean bool) {
            this.memStorage = bool;
            return this;
        }

        public Builder metadata(Map<String, String> map) {
            this.metadata = (map == null || map.isEmpty()) ? null : new HashMap(map);
            return this;
        }

        public Builder name(String str) {
            this.name = Validator.validateConsumerName(str, false);
            return this;
        }

        public Builder numReplicas(Integer num) {
            this.numReplicas = num == null ? null : Integer.valueOf(Validator.validateNumberOfReplicas(num.intValue()));
            return this;
        }

        public Builder pauseUntil(ZonedDateTime zonedDateTime) {
            this.pauseUntil = zonedDateTime;
            return this;
        }

        public Builder priorityGroups(String... strArr) {
            return priorityGroups(Arrays.asList(strArr));
        }

        public Builder priorityPolicy(PriorityPolicy priorityPolicy) {
            this.priorityPolicy = priorityPolicy;
            return this;
        }

        public Builder rateLimit(Long l) {
            this.rateLimit = ConsumerConfiguration.normalizeUlong(l);
            return this;
        }

        public Builder replayPolicy(ReplayPolicy replayPolicy) {
            this.replayPolicy = replayPolicy;
            return this;
        }

        public Builder sampleFrequency(String str) {
            this.sampleFrequency = Validator.emptyAsNull(str);
            return this;
        }

        public Builder startSequence(Long l) {
            this.startSeq = ConsumerConfiguration.normalizeUlong(l);
            return this;
        }

        public Builder startTime(ZonedDateTime zonedDateTime) {
            this.startTime = zonedDateTime;
            return this;
        }

        public Builder(ConsumerConfiguration consumerConfiguration) {
            if (consumerConfiguration != null) {
                this.deliverPolicy = consumerConfiguration.deliverPolicy;
                this.ackPolicy = consumerConfiguration.ackPolicy;
                this.replayPolicy = consumerConfiguration.replayPolicy;
                this.description = consumerConfiguration.description;
                this.durable = consumerConfiguration.durable;
                this.name = consumerConfiguration.name;
                this.deliverSubject = consumerConfiguration.deliverSubject;
                this.deliverGroup = consumerConfiguration.deliverGroup;
                this.sampleFrequency = consumerConfiguration.sampleFrequency;
                this.startTime = consumerConfiguration.startTime;
                this.ackWait = consumerConfiguration.ackWait;
                this.idleHeartbeat = consumerConfiguration.idleHeartbeat;
                this.maxExpires = consumerConfiguration.maxExpires;
                this.inactiveThreshold = consumerConfiguration.inactiveThreshold;
                this.startSeq = consumerConfiguration.startSeq;
                this.maxDeliver = consumerConfiguration.maxDeliver;
                this.rateLimit = consumerConfiguration.rateLimit;
                this.maxAckPending = consumerConfiguration.maxAckPending;
                this.maxPullWaiting = consumerConfiguration.maxPullWaiting;
                this.maxBatch = consumerConfiguration.maxBatch;
                this.maxBytes = consumerConfiguration.maxBytes;
                this.numReplicas = consumerConfiguration.numReplicas;
                this.pauseUntil = consumerConfiguration.pauseUntil;
                this.flowControl = consumerConfiguration.flowControl;
                this.headersOnly = consumerConfiguration.headersOnly;
                this.memStorage = consumerConfiguration.memStorage;
                if (consumerConfiguration.backoff != null) {
                    this.backoff = new ArrayList(consumerConfiguration.backoff);
                }
                if (consumerConfiguration.metadata != null) {
                    this.metadata = new HashMap(consumerConfiguration.metadata);
                }
                if (consumerConfiguration.filterSubjects != null) {
                    this.filterSubjects = new ArrayList(consumerConfiguration.filterSubjects);
                }
                if (consumerConfiguration.priorityGroups != null) {
                    this.priorityGroups = new ArrayList(consumerConfiguration.priorityGroups);
                }
                this.priorityPolicy = consumerConfiguration.priorityPolicy;
            }
        }

        public Builder ackWait(long j) {
            this.ackWait = ConsumerConfiguration.normalizeDuration(j);
            return this;
        }

        public PullSubscribeOptions buildPullSubscribeOptions(String str) {
            return PullSubscribeOptions.builder().configuration(build()).stream(str).build();
        }

        public PushSubscribeOptions buildPushSubscribeOptions(String str) {
            return PushSubscribeOptions.builder().configuration(build()).stream(str).build();
        }

        public Builder filterSubjects(List<String> list) {
            this.filterSubjects = new ArrayList();
            if (list != null) {
                for (String str : list) {
                    if (!Validator.nullOrEmpty(str)) {
                        this.filterSubjects.add(str);
                    }
                }
            }
            if (this.filterSubjects.isEmpty()) {
                this.filterSubjects = null;
            }
            return this;
        }

        public Builder inactiveThreshold(long j) {
            this.inactiveThreshold = ConsumerConfiguration.normalizeDuration(j);
            return this;
        }

        public Builder maxAckPending(long j) {
            this.maxAckPending = ConsumerConfiguration.normalize(Long.valueOf(j), 0);
            return this;
        }

        public Builder maxBatch(long j) {
            this.maxBatch = ConsumerConfiguration.normalize(Long.valueOf(j), 0);
            return this;
        }

        public Builder maxBytes(long j) {
            this.maxBytes = ConsumerConfiguration.normalize(Long.valueOf(j), 0);
            return this;
        }

        public Builder maxDeliver(long j) {
            this.maxDeliver = ConsumerConfiguration.normalize(Long.valueOf(j), 1);
            return this;
        }

        public Builder maxExpires(long j) {
            this.maxExpires = ConsumerConfiguration.normalizeDuration(j);
            return this;
        }

        public Builder maxPullWaiting(long j) {
            this.maxPullWaiting = ConsumerConfiguration.normalize(Long.valueOf(j), 0);
            return this;
        }

        public Builder priorityGroups(List<String> list) {
            this.priorityGroups = new ArrayList();
            if (list != null) {
                for (String str : list) {
                    if (!Validator.nullOrEmpty(str)) {
                        this.priorityGroups.add(str);
                    }
                }
            }
            if (this.priorityGroups.isEmpty()) {
                this.priorityGroups = null;
            }
            return this;
        }

        public Builder rateLimit(long j) {
            this.rateLimit = ConsumerConfiguration.normalizeUlong(Long.valueOf(j));
            return this;
        }

        public Builder startSequence(long j) {
            this.startSeq = ConsumerConfiguration.normalizeUlong(Long.valueOf(j));
            return this;
        }

        public Builder flowControl(long j) {
            this.flowControl = Boolean.TRUE;
            return idleHeartbeat(j);
        }

        public Builder idleHeartbeat(long j) {
            if (j <= 0) {
                this.idleHeartbeat = ConsumerConfiguration.DURATION_UNSET;
                return this;
            }
            long j2 = ConsumerConfiguration.MIN_IDLE_HEARTBEAT_MILLIS;
            if (j >= j2) {
                this.idleHeartbeat = Duration.ofMillis(j);
                return this;
            }
            throw new IllegalArgumentException("Duration must be greater than or equal to " + j2 + " milliseconds.");
        }

        public Builder backoff(long... jArr) {
            if (jArr == null) {
                this.backoff = null;
                return this;
            }
            this.backoff = new ArrayList();
            for (long j : jArr) {
                if (j >= 1) {
                    this.backoff.add(Duration.ofMillis(j));
                } else {
                    throw new IllegalArgumentException("Backoff cannot be less than 1");
                }
            }
            return this;
        }
    }

    static {
        Duration durationOfMillis = Duration.ofMillis(100L);
        MIN_IDLE_HEARTBEAT = durationOfMillis;
        MIN_IDLE_HEARTBEAT_NANOS = durationOfMillis.toNanos();
        MIN_IDLE_HEARTBEAT_MILLIS = durationOfMillis.toMillis();
    }

    public ConsumerConfiguration(ConsumerConfiguration consumerConfiguration) {
        this.deliverPolicy = consumerConfiguration.deliverPolicy;
        this.ackPolicy = consumerConfiguration.ackPolicy;
        this.replayPolicy = consumerConfiguration.replayPolicy;
        this.description = consumerConfiguration.description;
        this.durable = consumerConfiguration.durable;
        this.name = consumerConfiguration.name;
        this.deliverSubject = consumerConfiguration.deliverSubject;
        this.deliverGroup = consumerConfiguration.deliverGroup;
        this.sampleFrequency = consumerConfiguration.sampleFrequency;
        this.startTime = consumerConfiguration.startTime;
        this.ackWait = consumerConfiguration.ackWait;
        this.idleHeartbeat = consumerConfiguration.idleHeartbeat;
        this.maxExpires = consumerConfiguration.maxExpires;
        this.inactiveThreshold = consumerConfiguration.inactiveThreshold;
        this.startSeq = consumerConfiguration.startSeq;
        this.maxDeliver = consumerConfiguration.maxDeliver;
        this.rateLimit = consumerConfiguration.rateLimit;
        this.maxAckPending = consumerConfiguration.maxAckPending;
        this.maxPullWaiting = consumerConfiguration.maxPullWaiting;
        this.maxBatch = consumerConfiguration.maxBatch;
        this.maxBytes = consumerConfiguration.maxBytes;
        this.numReplicas = consumerConfiguration.numReplicas;
        this.pauseUntil = consumerConfiguration.pauseUntil;
        this.flowControl = consumerConfiguration.flowControl;
        this.headersOnly = consumerConfiguration.headersOnly;
        this.memStorage = consumerConfiguration.memStorage;
        this.backoff = consumerConfiguration.backoff == null ? null : new ArrayList(consumerConfiguration.backoff);
        this.metadata = consumerConfiguration.metadata == null ? null : new HashMap(consumerConfiguration.metadata);
        this.filterSubjects = consumerConfiguration.filterSubjects == null ? null : new ArrayList(consumerConfiguration.filterSubjects);
        this.priorityGroups = consumerConfiguration.priorityGroups != null ? new ArrayList(consumerConfiguration.priorityGroups) : null;
        this.priorityPolicy = consumerConfiguration.priorityPolicy;
    }

    public static DeliverPolicy GetOrDefault(DeliverPolicy deliverPolicy) {
        return deliverPolicy == null ? DEFAULT_DELIVER_POLICY : deliverPolicy;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static int getOrUnset(Integer num) {
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public static long getOrUnsetUlong(Long l) {
        if (l == null || l.longValue() < 0) {
            return 0L;
        }
        return l.longValue();
    }

    public static Integer normalize(Long l, int i) {
        if (l == null) {
            return null;
        }
        if (l.longValue() < i) {
            return -1;
        }
        if (l.longValue() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return Integer.valueOf(l.intValue());
    }

    public static Duration normalizeDuration(long j) {
        return j <= 0 ? DURATION_UNSET : Duration.ofMillis(j);
    }

    public static Long normalizeUlong(Long l) {
        if (l == null) {
            return null;
        }
        return Long.valueOf(l.longValue() > 0 ? l.longValue() : 0L);
    }

    public boolean ackPolicyWasSet() {
        return this.ackPolicy != null;
    }

    public boolean backoffWasSet() {
        return this.backoff != null;
    }

    public boolean deliverPolicyWasSet() {
        return this.deliverPolicy != null;
    }

    public boolean flowControlWasSet() {
        return this.flowControl != null;
    }

    public AckPolicy getAckPolicy() {
        return GetOrDefault(this.ackPolicy);
    }

    public Duration getAckWait() {
        return this.ackWait;
    }

    public List<Duration> getBackoff() {
        List<Duration> list = this.backoff;
        return list == null ? Collections.EMPTY_LIST : list;
    }

    public String getDeliverGroup() {
        return this.deliverGroup;
    }

    public DeliverPolicy getDeliverPolicy() {
        return GetOrDefault(this.deliverPolicy);
    }

    public String getDeliverSubject() {
        return this.deliverSubject;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDurable() {
        return this.durable;
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

    public Duration getIdleHeartbeat() {
        return this.idleHeartbeat;
    }

    public Duration getInactiveThreshold() {
        return this.inactiveThreshold;
    }

    public long getMaxAckPending() {
        return getOrUnset(this.maxAckPending);
    }

    public long getMaxBatch() {
        return getOrUnset(this.maxBatch);
    }

    public long getMaxBytes() {
        return getOrUnset(this.maxBytes);
    }

    public long getMaxDeliver() {
        return getOrUnset(this.maxDeliver);
    }

    public Duration getMaxExpires() {
        return this.maxExpires;
    }

    public long getMaxPullWaiting() {
        return getOrUnset(this.maxPullWaiting);
    }

    public Map<String, String> getMetadata() {
        Map<String, String> map = this.metadata;
        return map == null ? Collections.EMPTY_MAP : map;
    }

    public String getName() {
        return this.name;
    }

    public int getNumReplicas() {
        return getOrUnset(this.numReplicas);
    }

    public ZonedDateTime getPauseUntil() {
        return this.pauseUntil;
    }

    public List<String> getPriorityGroups() {
        return this.priorityGroups;
    }

    public PriorityPolicy getPriorityPolicy() {
        return GetOrDefault(this.priorityPolicy);
    }

    public long getRateLimit() {
        return getOrUnsetUlong(this.rateLimit);
    }

    public ReplayPolicy getReplayPolicy() {
        return GetOrDefault(this.replayPolicy);
    }

    public String getSampleFrequency() {
        return this.sampleFrequency;
    }

    public long getStartSequence() {
        return getOrUnsetUlong(this.startSeq);
    }

    public ZonedDateTime getStartTime() {
        return this.startTime;
    }

    public boolean hasMultipleFilterSubjects() {
        List<String> list = this.filterSubjects;
        return list != null && list.size() > 1;
    }

    public boolean headersOnlyWasSet() {
        return this.headersOnly != null;
    }

    public boolean isFlowControl() {
        return Boolean.TRUE.equals(this.flowControl);
    }

    public boolean isHeadersOnly() {
        Boolean bool = this.headersOnly;
        return bool != null && bool.booleanValue();
    }

    public boolean isMemStorage() {
        Boolean bool = this.memStorage;
        return bool != null && bool.booleanValue();
    }

    public boolean maxAckPendingWasSet() {
        return this.maxAckPending != null;
    }

    public boolean maxBatchWasSet() {
        return this.maxBatch != null;
    }

    public boolean maxBytesWasSet() {
        return this.maxBytes != null;
    }

    public boolean maxDeliverWasSet() {
        return this.maxDeliver != null;
    }

    public boolean maxPullWaitingWasSet() {
        return this.maxPullWaiting != null;
    }

    public boolean memStorageWasSet() {
        return this.memStorage != null;
    }

    public boolean metadataWasSet() {
        return this.metadata != null;
    }

    public boolean numReplicasWasSet() {
        return this.numReplicas != null;
    }

    public boolean priorityPolicyWasSet() {
        return this.priorityPolicy != null;
    }

    public boolean rateLimitWasSet() {
        return this.rateLimit != null;
    }

    public boolean replayPolicyWasSet() {
        return this.replayPolicy != null;
    }

    public boolean startSeqWasSet() {
        return this.startSeq != null;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.DESCRIPTION, this.description);
        JsonUtils.addField(sbBeginJson, ApiConstants.DURABLE_NAME, this.durable);
        JsonUtils.addField(sbBeginJson, ApiConstants.NAME, this.name);
        JsonUtils.addField(sbBeginJson, ApiConstants.DELIVER_SUBJECT, this.deliverSubject);
        JsonUtils.addField(sbBeginJson, ApiConstants.DELIVER_GROUP, this.deliverGroup);
        JsonUtils.addField(sbBeginJson, ApiConstants.DELIVER_POLICY, GetOrDefault(this.deliverPolicy).toString());
        JsonUtils.addFieldWhenGtZero(sbBeginJson, ApiConstants.OPT_START_SEQ, this.startSeq);
        JsonUtils.addField(sbBeginJson, ApiConstants.OPT_START_TIME, this.startTime);
        JsonUtils.addField(sbBeginJson, ApiConstants.ACK_POLICY, GetOrDefault(this.ackPolicy).toString());
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.ACK_WAIT, this.ackWait);
        JsonUtils.addFieldWhenGtZero(sbBeginJson, ApiConstants.MAX_DELIVER, this.maxDeliver);
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_ACK_PENDING, this.maxAckPending);
        JsonUtils.addField(sbBeginJson, ApiConstants.REPLAY_POLICY, GetOrDefault(this.replayPolicy).toString());
        JsonUtils.addField(sbBeginJson, ApiConstants.SAMPLE_FREQ, this.sampleFrequency);
        JsonUtils.addFieldWhenGtZero(sbBeginJson, ApiConstants.RATE_LIMIT_BPS, this.rateLimit);
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.IDLE_HEARTBEAT, this.idleHeartbeat);
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.FLOW_CONTROL, this.flowControl);
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_WAITING, this.maxPullWaiting);
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.HEADERS_ONLY, this.headersOnly);
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_BATCH, this.maxBatch);
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_BYTES, this.maxBytes);
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.MAX_EXPIRES, this.maxExpires);
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.INACTIVE_THRESHOLD, this.inactiveThreshold);
        JsonUtils.addDurations(sbBeginJson, ApiConstants.BACKOFF, this.backoff);
        JsonUtils.addField(sbBeginJson, ApiConstants.NUM_REPLICAS, this.numReplicas);
        JsonUtils.addField(sbBeginJson, ApiConstants.PAUSE_UNTIL, this.pauseUntil);
        JsonUtils.addField(sbBeginJson, ApiConstants.MEM_STORAGE, this.memStorage);
        JsonUtils.addField(sbBeginJson, ApiConstants.METADATA, this.metadata);
        List<String> list = this.filterSubjects;
        if (list != null) {
            if (list.size() > 1) {
                JsonUtils.addStrings(sbBeginJson, ApiConstants.FILTER_SUBJECTS, this.filterSubjects);
            } else if (this.filterSubjects.size() == 1) {
                JsonUtils.addField(sbBeginJson, ApiConstants.FILTER_SUBJECT, this.filterSubjects.get(0));
            }
        }
        JsonUtils.addStrings(sbBeginJson, ApiConstants.PRIORITY_GROUPS, this.priorityGroups);
        PriorityPolicy priorityPolicy = this.priorityPolicy;
        if (priorityPolicy != null && priorityPolicy != DEFAULT_PRIORITY_POLICY) {
            JsonUtils.addField(sbBeginJson, ApiConstants.PRIORITY_POLICY, priorityPolicy.toString());
        }
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return "ConsumerConfiguration " + toJson();
    }

    public static AckPolicy GetOrDefault(AckPolicy ackPolicy) {
        return ackPolicy == null ? DEFAULT_ACK_POLICY : ackPolicy;
    }

    public static Builder builder(ConsumerConfiguration consumerConfiguration) {
        return consumerConfiguration == null ? new Builder() : new Builder(consumerConfiguration);
    }

    public static Duration getOrUnset(Duration duration) {
        return duration == null ? DURATION_UNSET : duration;
    }

    public static ReplayPolicy GetOrDefault(ReplayPolicy replayPolicy) {
        return replayPolicy == null ? DEFAULT_REPLAY_POLICY : replayPolicy;
    }

    public static PriorityPolicy GetOrDefault(PriorityPolicy priorityPolicy) {
        return priorityPolicy == null ? DEFAULT_PRIORITY_POLICY : priorityPolicy;
    }

    public static Duration normalize(Duration duration) {
        if (duration == null) {
            return null;
        }
        return duration.toNanos() <= 0 ? DURATION_UNSET : duration;
    }

    public ConsumerConfiguration(Builder builder) {
        this.deliverPolicy = builder.deliverPolicy;
        this.ackPolicy = builder.ackPolicy;
        this.replayPolicy = builder.replayPolicy;
        this.description = builder.description;
        this.durable = builder.durable;
        this.name = builder.name;
        this.startTime = builder.startTime;
        this.ackWait = builder.ackWait;
        this.sampleFrequency = builder.sampleFrequency;
        this.deliverSubject = builder.deliverSubject;
        this.deliverGroup = builder.deliverGroup;
        this.idleHeartbeat = builder.idleHeartbeat;
        this.maxExpires = builder.maxExpires;
        this.inactiveThreshold = builder.inactiveThreshold;
        this.startSeq = builder.startSeq;
        this.maxDeliver = builder.maxDeliver;
        this.rateLimit = builder.rateLimit;
        this.maxAckPending = builder.maxAckPending;
        this.maxPullWaiting = builder.maxPullWaiting;
        this.maxBatch = builder.maxBatch;
        this.maxBytes = builder.maxBytes;
        this.numReplicas = builder.numReplicas;
        this.pauseUntil = builder.pauseUntil;
        this.flowControl = builder.flowControl;
        this.headersOnly = builder.headersOnly;
        this.memStorage = builder.memStorage;
        this.backoff = builder.backoff;
        this.metadata = builder.metadata;
        this.filterSubjects = builder.filterSubjects;
        this.priorityGroups = builder.priorityGroups;
        this.priorityPolicy = builder.priorityPolicy;
    }
}
