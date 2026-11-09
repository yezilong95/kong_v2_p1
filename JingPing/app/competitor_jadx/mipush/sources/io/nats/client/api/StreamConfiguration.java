package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonParseException;
import io.nats.client.support.JsonParser;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class StreamConfiguration implements JsonSerializable {
    private final boolean allowDirect;
    private final boolean allowMessageTtl;
    private final boolean allowRollup;
    private final CompressionOption compressionOption;
    private final ConsumerLimits consumerLimits;
    private final boolean denyDelete;
    private final boolean denyPurge;
    private final String description;
    private final boolean discardNewPerSubject;
    private final DiscardPolicy discardPolicy;
    private final Duration duplicateWindow;
    private final long firstSequence;
    private final Duration maxAge;
    private final long maxBytes;
    private final long maxConsumers;
    private final int maxMsgSize;
    private final long maxMsgs;
    private final long maxMsgsPerSubject;
    private final Map<String, String> metadata;
    private final Mirror mirror;
    private final boolean mirrorDirect;
    private final String name;
    private final boolean noAck;
    private final Placement placement;
    private final int replicas;
    private final Republish republish;
    private final RetentionPolicy retentionPolicy;
    private final boolean sealed;
    private final List<Source> sources;
    private final StorageType storageType;
    private final Duration subjectDeleteMarkerTtl;
    private final SubjectTransform subjectTransform;
    private final List<String> subjects;
    private final String templateOwner;

    public static class Builder {
        private boolean allowDirect;
        private boolean allowMessageTtl;
        private boolean allowRollup;
        private CompressionOption compressionOption;
        private ConsumerLimits consumerLimits;
        private boolean denyDelete;
        private boolean denyPurge;
        private String description;
        private boolean discardNewPerSubject;
        private DiscardPolicy discardPolicy;
        private Duration duplicateWindow;
        private long firstSequence;
        private Duration maxAge;
        private long maxBytes;
        private long maxConsumers;
        private int maxMsgSize;
        private long maxMsgs;
        private long maxMsgsPerSubject;
        private Map<String, String> metadata;
        private Mirror mirror;
        private boolean mirrorDirect;
        private String name;
        private boolean noAck;
        private Placement placement;
        private int replicas;
        private Republish republish;
        private RetentionPolicy retentionPolicy;
        private boolean sealed;
        private final List<Source> sources;
        private StorageType storageType;
        private Duration subjectDeleteMarkerTtl;
        private SubjectTransform subjectTransform;
        private final List<String> subjects;
        private String templateOwner;

        public Builder() {
            this.name = null;
            this.description = null;
            this.subjects = new ArrayList();
            this.retentionPolicy = RetentionPolicy.Limits;
            this.compressionOption = CompressionOption.None;
            this.maxConsumers = -1L;
            this.maxMsgs = -1L;
            this.maxMsgsPerSubject = -1L;
            this.maxBytes = -1L;
            this.maxAge = Duration.ZERO;
            this.maxMsgSize = -1;
            this.storageType = StorageType.File;
            this.replicas = 1;
            this.noAck = false;
            this.templateOwner = null;
            this.discardPolicy = DiscardPolicy.Old;
            this.duplicateWindow = Duration.ZERO;
            this.placement = null;
            this.republish = null;
            this.subjectTransform = null;
            this.consumerLimits = null;
            this.mirror = null;
            this.sources = new ArrayList();
            this.sealed = false;
            this.allowRollup = false;
            this.allowDirect = false;
            this.mirrorDirect = false;
            this.denyDelete = false;
            this.denyPurge = false;
            this.discardNewPerSubject = false;
            this.firstSequence = 1L;
            this.allowMessageTtl = false;
        }

        public Builder addSource(Source source) {
            if (source != null && !this.sources.contains(source)) {
                this.sources.add(source);
            }
            return this;
        }

        public Builder addSources(Source... sourceArr) {
            return addSources(Arrays.asList(sourceArr));
        }

        public Builder addSubjects(String... strArr) {
            return strArr != null ? addSubjects(Arrays.asList(strArr)) : this;
        }

        public Builder allowDirect(boolean z) {
            this.allowDirect = z;
            return this;
        }

        public Builder allowMessageTtl() {
            this.allowMessageTtl = true;
            return this;
        }

        public Builder allowRollup(boolean z) {
            this.allowRollup = z;
            return this;
        }

        public StreamConfiguration build() {
            return new StreamConfiguration(this);
        }

        public Builder compressionOption(CompressionOption compressionOption) {
            if (compressionOption == null) {
                compressionOption = CompressionOption.None;
            }
            this.compressionOption = compressionOption;
            return this;
        }

        public Builder consumerLimits(ConsumerLimits consumerLimits) {
            this.consumerLimits = consumerLimits;
            return this;
        }

        public Builder denyDelete(boolean z) {
            this.denyDelete = z;
            return this;
        }

        public Builder denyPurge(boolean z) {
            this.denyPurge = z;
            return this;
        }

        public Builder description(String str) {
            this.description = str;
            return this;
        }

        public Builder discardNewPerSubject(boolean z) {
            this.discardNewPerSubject = z;
            return this;
        }

        public Builder discardPolicy(DiscardPolicy discardPolicy) {
            if (discardPolicy == null) {
                discardPolicy = DiscardPolicy.Old;
            }
            this.discardPolicy = discardPolicy;
            return this;
        }

        public Builder duplicateWindow(Duration duration) {
            this.duplicateWindow = Validator.validateDurationNotRequiredGtOrEqZero(duration, Duration.ZERO);
            return this;
        }

        public Builder firstSequence(long j) {
            if (j <= 1) {
                j = 1;
            }
            this.firstSequence = j;
            return this;
        }

        public Builder maxAge(Duration duration) {
            this.maxAge = Validator.validateDurationNotRequiredGtOrEqZero(duration, Duration.ZERO);
            return this;
        }

        public Builder maxBytes(long j) {
            this.maxBytes = Validator.validateMaxBytes(j);
            return this;
        }

        public Builder maxConsumers(long j) {
            this.maxConsumers = Validator.validateMaxConsumers(j);
            return this;
        }

        public Builder maxMessages(long j) {
            this.maxMsgs = Validator.validateMaxMessages(j);
            return this;
        }

        public Builder maxMessagesPerSubject(long j) {
            this.maxMsgsPerSubject = Validator.validateMaxMessagesPerSubject(j);
            return this;
        }

        @Deprecated
        public Builder maxMsgSize(long j) {
            this.maxMsgSize = (int) Validator.validateMaxMessageSize(j);
            return this;
        }

        public Builder maximumMessageSize(int i) {
            this.maxMsgSize = (int) Validator.validateMaxMessageSize(i);
            return this;
        }

        public Builder metadata(Map<String, String> map) {
            this.metadata = map;
            return this;
        }

        public Builder mirror(Mirror mirror) {
            this.mirror = mirror;
            return this;
        }

        public Builder mirrorDirect(boolean z) {
            this.mirrorDirect = z;
            return this;
        }

        public Builder name(String str) {
            this.name = Validator.validateStreamName(str, false);
            return this;
        }

        public Builder noAck(boolean z) {
            this.noAck = z;
            return this;
        }

        public Builder placement(Placement placement) {
            this.placement = placement;
            return this;
        }

        public Builder replicas(int i) {
            this.replicas = Validator.validateNumberOfReplicas(i);
            return this;
        }

        public Builder republish(Republish republish) {
            this.republish = republish;
            return this;
        }

        public Builder retentionPolicy(RetentionPolicy retentionPolicy) {
            if (retentionPolicy == null) {
                retentionPolicy = RetentionPolicy.Limits;
            }
            this.retentionPolicy = retentionPolicy;
            return this;
        }

        public Builder seal() {
            this.sealed = true;
            return this;
        }

        public Builder sealed(boolean z) {
            this.sealed = z;
            return this;
        }

        public Builder sources(Source... sourceArr) {
            this.sources.clear();
            return addSources(sourceArr);
        }

        public Builder storageType(StorageType storageType) {
            if (storageType == null) {
                storageType = StorageType.File;
            }
            this.storageType = storageType;
            return this;
        }

        public Builder subjectDeleteMarkerTtl(Duration duration) {
            this.subjectDeleteMarkerTtl = Validator.validateDurationNotRequiredGtOrEqSeconds(1L, duration, null, "Subject Delete Marker Ttl");
            return this;
        }

        public Builder subjectTransform(SubjectTransform subjectTransform) {
            this.subjectTransform = subjectTransform;
            return this;
        }

        public Builder subjects(String... strArr) {
            this.subjects.clear();
            return addSubjects(strArr);
        }

        public Builder templateOwner(String str) {
            this.templateOwner = Validator.emptyAsNull(str);
            return this;
        }

        public Builder addSources(Collection<Source> collection) {
            if (collection != null) {
                for (Source source : collection) {
                    if (source != null && !this.sources.contains(source)) {
                        this.sources.add(source);
                    }
                }
            }
            return this;
        }

        public Builder addSubjects(Collection<String> collection) {
            if (collection != null) {
                for (String str : collection) {
                    if (str != null && !this.subjects.contains(str)) {
                        this.subjects.add(str);
                    }
                }
            }
            return this;
        }

        public Builder allowMessageTtl(boolean z) {
            this.allowMessageTtl = z;
            return this;
        }

        public Builder duplicateWindow(long j) {
            this.duplicateWindow = Validator.validateDurationNotRequiredGtOrEqZero(j);
            return this;
        }

        public Builder maxAge(long j) {
            this.maxAge = Validator.validateDurationNotRequiredGtOrEqZero(j);
            return this;
        }

        public Builder subjectDeleteMarkerTtl(long j) {
            this.subjectDeleteMarkerTtl = j <= 0 ? null : Validator.validateDurationGtOrEqSeconds(1L, j, "Subject Delete Marker Ttl");
            return this;
        }

        public Builder sources(Collection<Source> collection) {
            this.sources.clear();
            return addSources(collection);
        }

        public Builder subjects(Collection<String> collection) {
            this.subjects.clear();
            return addSubjects(collection);
        }

        public Builder(StreamConfiguration streamConfiguration) {
            this.name = null;
            this.description = null;
            this.subjects = new ArrayList();
            this.retentionPolicy = RetentionPolicy.Limits;
            this.compressionOption = CompressionOption.None;
            this.maxConsumers = -1L;
            this.maxMsgs = -1L;
            this.maxMsgsPerSubject = -1L;
            this.maxBytes = -1L;
            this.maxAge = Duration.ZERO;
            this.maxMsgSize = -1;
            this.storageType = StorageType.File;
            this.replicas = 1;
            this.noAck = false;
            this.templateOwner = null;
            this.discardPolicy = DiscardPolicy.Old;
            this.duplicateWindow = Duration.ZERO;
            this.placement = null;
            this.republish = null;
            this.subjectTransform = null;
            this.consumerLimits = null;
            this.mirror = null;
            this.sources = new ArrayList();
            this.sealed = false;
            this.allowRollup = false;
            this.allowDirect = false;
            this.mirrorDirect = false;
            this.denyDelete = false;
            this.denyPurge = false;
            this.discardNewPerSubject = false;
            this.firstSequence = 1L;
            this.allowMessageTtl = false;
            if (streamConfiguration != null) {
                this.name = streamConfiguration.name;
                this.description = streamConfiguration.description;
                subjects(streamConfiguration.subjects);
                this.retentionPolicy = streamConfiguration.retentionPolicy;
                this.compressionOption = streamConfiguration.compressionOption;
                this.maxConsumers = streamConfiguration.maxConsumers;
                this.maxMsgs = streamConfiguration.maxMsgs;
                this.maxMsgsPerSubject = streamConfiguration.maxMsgsPerSubject;
                this.maxBytes = streamConfiguration.maxBytes;
                this.maxAge = streamConfiguration.maxAge;
                this.maxMsgSize = streamConfiguration.maxMsgSize;
                this.storageType = streamConfiguration.storageType;
                this.replicas = streamConfiguration.replicas;
                this.noAck = streamConfiguration.noAck;
                this.templateOwner = streamConfiguration.templateOwner;
                this.discardPolicy = streamConfiguration.discardPolicy;
                this.duplicateWindow = streamConfiguration.duplicateWindow;
                this.placement = streamConfiguration.placement;
                this.republish = streamConfiguration.republish;
                this.subjectTransform = streamConfiguration.subjectTransform;
                this.consumerLimits = streamConfiguration.consumerLimits;
                this.mirror = streamConfiguration.mirror;
                sources(streamConfiguration.sources);
                this.sealed = streamConfiguration.sealed;
                this.allowRollup = streamConfiguration.allowRollup;
                this.allowDirect = streamConfiguration.allowDirect;
                this.mirrorDirect = streamConfiguration.mirrorDirect;
                this.denyDelete = streamConfiguration.denyDelete;
                this.denyPurge = streamConfiguration.denyPurge;
                this.discardNewPerSubject = streamConfiguration.discardNewPerSubject;
                if (streamConfiguration.metadata != null) {
                    this.metadata = new HashMap(streamConfiguration.metadata);
                }
                this.firstSequence = streamConfiguration.firstSequence;
                this.allowMessageTtl = streamConfiguration.allowMessageTtl;
                this.subjectDeleteMarkerTtl = streamConfiguration.subjectDeleteMarkerTtl;
            }
        }
    }

    public StreamConfiguration(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.subjects = builder.subjects;
        this.retentionPolicy = builder.retentionPolicy;
        this.compressionOption = builder.compressionOption;
        this.maxConsumers = builder.maxConsumers;
        this.maxMsgs = builder.maxMsgs;
        this.maxMsgsPerSubject = builder.maxMsgsPerSubject;
        this.maxBytes = builder.maxBytes;
        this.maxAge = builder.maxAge;
        this.maxMsgSize = builder.maxMsgSize;
        this.storageType = builder.storageType;
        this.replicas = builder.replicas;
        this.noAck = builder.noAck;
        this.templateOwner = builder.templateOwner;
        this.discardPolicy = builder.discardPolicy;
        this.duplicateWindow = builder.duplicateWindow;
        this.placement = builder.placement;
        this.republish = builder.republish;
        this.subjectTransform = builder.subjectTransform;
        this.consumerLimits = builder.consumerLimits;
        this.mirror = builder.mirror;
        this.sources = builder.sources;
        this.sealed = builder.sealed;
        this.allowRollup = builder.allowRollup;
        this.allowDirect = builder.allowDirect;
        this.mirrorDirect = builder.mirrorDirect;
        this.denyDelete = builder.denyDelete;
        this.denyPurge = builder.denyPurge;
        this.discardNewPerSubject = builder.discardNewPerSubject;
        this.metadata = builder.metadata;
        this.firstSequence = builder.firstSequence;
        this.allowMessageTtl = builder.allowMessageTtl;
        this.subjectDeleteMarkerTtl = builder.subjectDeleteMarkerTtl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static StreamConfiguration instance(JsonValue jsonValue) {
        return new Builder().retentionPolicy(RetentionPolicy.get(JsonValueUtils.readString(jsonValue, ApiConstants.RETENTION))).compressionOption(CompressionOption.get(JsonValueUtils.readString(jsonValue, ApiConstants.COMPRESSION))).storageType(StorageType.get(JsonValueUtils.readString(jsonValue, ApiConstants.STORAGE))).discardPolicy(DiscardPolicy.get(JsonValueUtils.readString(jsonValue, ApiConstants.DISCARD))).name(JsonValueUtils.readString(jsonValue, ApiConstants.NAME)).description(JsonValueUtils.readString(jsonValue, ApiConstants.DESCRIPTION)).maxConsumers(JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_CONSUMERS, -1L)).maxMessages(JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_MSGS, -1L)).maxMessagesPerSubject(JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_MSGS_PER_SUB, -1L)).maxBytes(JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_BYTES, -1L)).maxAge(JsonValueUtils.readNanos(jsonValue, ApiConstants.MAX_AGE)).maximumMessageSize(JsonValueUtils.readInteger(jsonValue, ApiConstants.MAX_MSG_SIZE, -1)).replicas(JsonValueUtils.readInteger(jsonValue, ApiConstants.NUM_REPLICAS, 1)).noAck(JsonValueUtils.readBoolean(jsonValue, ApiConstants.NO_ACK)).templateOwner(JsonValueUtils.readString(jsonValue, ApiConstants.TEMPLATE_OWNER)).duplicateWindow(JsonValueUtils.readNanos(jsonValue, ApiConstants.DUPLICATE_WINDOW)).subjects(JsonValueUtils.readStringList(jsonValue, ApiConstants.SUBJECTS)).placement(Placement.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.PLACEMENT))).republish(Republish.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.REPUBLISH))).subjectTransform(SubjectTransform.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.SUBJECT_TRANSFORM))).consumerLimits(ConsumerLimits.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.CONSUMER_LIMITS))).mirror(Mirror.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.MIRROR))).sources(Source.optionalListOf(JsonValueUtils.readValue(jsonValue, ApiConstants.SOURCES))).sealed(JsonValueUtils.readBoolean(jsonValue, ApiConstants.SEALED)).allowRollup(JsonValueUtils.readBoolean(jsonValue, ApiConstants.ALLOW_ROLLUP_HDRS)).allowDirect(JsonValueUtils.readBoolean(jsonValue, ApiConstants.ALLOW_DIRECT)).mirrorDirect(JsonValueUtils.readBoolean(jsonValue, ApiConstants.MIRROR_DIRECT)).denyDelete(JsonValueUtils.readBoolean(jsonValue, ApiConstants.DENY_DELETE)).denyPurge(JsonValueUtils.readBoolean(jsonValue, ApiConstants.DENY_PURGE)).discardNewPerSubject(JsonValueUtils.readBoolean(jsonValue, ApiConstants.DISCARD_NEW_PER_SUBJECT)).metadata(JsonValueUtils.readStringStringMap(jsonValue, ApiConstants.METADATA)).firstSequence(JsonValueUtils.readLong(jsonValue, ApiConstants.FIRST_SEQ, 1L)).allowMessageTtl(JsonValueUtils.readBoolean(jsonValue, ApiConstants.ALLOW_MSG_TTL)).subjectDeleteMarkerTtl(JsonValueUtils.readNanos(jsonValue, ApiConstants.SUBJECT_DELETE_MARKER_TTL)).build();
    }

    public boolean getAllowDirect() {
        return this.allowDirect;
    }

    public boolean getAllowRollup() {
        return this.allowRollup;
    }

    public CompressionOption getCompressionOption() {
        return this.compressionOption;
    }

    public ConsumerLimits getConsumerLimits() {
        return this.consumerLimits;
    }

    public boolean getDenyDelete() {
        return this.denyDelete;
    }

    public boolean getDenyPurge() {
        return this.denyPurge;
    }

    public String getDescription() {
        return this.description;
    }

    public DiscardPolicy getDiscardPolicy() {
        return this.discardPolicy;
    }

    public Duration getDuplicateWindow() {
        return this.duplicateWindow;
    }

    public long getFirstSequence() {
        return this.firstSequence;
    }

    public Duration getMaxAge() {
        return this.maxAge;
    }

    public long getMaxBytes() {
        return this.maxBytes;
    }

    public long getMaxConsumers() {
        return this.maxConsumers;
    }

    @Deprecated
    public long getMaxMsgSize() {
        return this.maxMsgSize;
    }

    public long getMaxMsgs() {
        return this.maxMsgs;
    }

    public long getMaxMsgsPerSubject() {
        return this.maxMsgsPerSubject;
    }

    public int getMaximumMessageSize() {
        return this.maxMsgSize;
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    public Mirror getMirror() {
        return this.mirror;
    }

    public boolean getMirrorDirect() {
        return this.mirrorDirect;
    }

    public String getName() {
        return this.name;
    }

    public boolean getNoAck() {
        return this.noAck;
    }

    public Placement getPlacement() {
        return this.placement;
    }

    public int getReplicas() {
        return this.replicas;
    }

    public Republish getRepublish() {
        return this.republish;
    }

    public RetentionPolicy getRetentionPolicy() {
        return this.retentionPolicy;
    }

    public boolean getSealed() {
        return this.sealed;
    }

    public List<Source> getSources() {
        return this.sources;
    }

    public StorageType getStorageType() {
        return this.storageType;
    }

    public Duration getSubjectDeleteMarkerTtl() {
        return this.subjectDeleteMarkerTtl;
    }

    public SubjectTransform getSubjectTransform() {
        return this.subjectTransform;
    }

    public List<String> getSubjects() {
        return this.subjects;
    }

    public String getTemplateOwner() {
        return this.templateOwner;
    }

    public boolean isAllowMessageTtl() {
        return this.allowMessageTtl;
    }

    public boolean isDiscardNewPerSubject() {
        return this.discardNewPerSubject;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.NAME, this.name);
        JsonUtils.addField(sbBeginJson, ApiConstants.DESCRIPTION, this.description);
        JsonUtils.addStrings(sbBeginJson, ApiConstants.SUBJECTS, this.subjects);
        JsonUtils.addField(sbBeginJson, ApiConstants.RETENTION, this.retentionPolicy.toString());
        JsonUtils.addEnumWhenNot(sbBeginJson, ApiConstants.COMPRESSION, this.compressionOption, CompressionOption.None);
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_CONSUMERS, Long.valueOf(this.maxConsumers));
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_MSGS, Long.valueOf(this.maxMsgs));
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_MSGS_PER_SUB, Long.valueOf(this.maxMsgsPerSubject));
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_BYTES, Long.valueOf(this.maxBytes));
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.MAX_AGE, this.maxAge);
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_MSG_SIZE, Integer.valueOf(this.maxMsgSize));
        JsonUtils.addField(sbBeginJson, ApiConstants.STORAGE, this.storageType.toString());
        JsonUtils.addField(sbBeginJson, ApiConstants.NUM_REPLICAS, Integer.valueOf(this.replicas));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.NO_ACK, Boolean.valueOf(this.noAck));
        JsonUtils.addField(sbBeginJson, ApiConstants.TEMPLATE_OWNER, this.templateOwner);
        JsonUtils.addField(sbBeginJson, ApiConstants.DISCARD, this.discardPolicy.toString());
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.DUPLICATE_WINDOW, this.duplicateWindow);
        Placement placement = this.placement;
        if (placement != null && placement.hasData()) {
            JsonUtils.addField(sbBeginJson, ApiConstants.PLACEMENT, this.placement);
        }
        JsonUtils.addField(sbBeginJson, ApiConstants.REPUBLISH, this.republish);
        JsonUtils.addField(sbBeginJson, ApiConstants.SUBJECT_TRANSFORM, this.subjectTransform);
        JsonUtils.addField(sbBeginJson, ApiConstants.CONSUMER_LIMITS, this.consumerLimits);
        JsonUtils.addField(sbBeginJson, ApiConstants.MIRROR, this.mirror);
        JsonUtils.addJsons(sbBeginJson, ApiConstants.SOURCES, this.sources);
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.SEALED, Boolean.valueOf(this.sealed));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.ALLOW_ROLLUP_HDRS, Boolean.valueOf(this.allowRollup));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.ALLOW_DIRECT, Boolean.valueOf(this.allowDirect));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.MIRROR_DIRECT, Boolean.valueOf(this.mirrorDirect));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.DENY_DELETE, Boolean.valueOf(this.denyDelete));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.DENY_PURGE, Boolean.valueOf(this.denyPurge));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.DISCARD_NEW_PER_SUBJECT, Boolean.valueOf(this.discardNewPerSubject));
        JsonUtils.addField(sbBeginJson, ApiConstants.METADATA, this.metadata);
        JsonUtils.addFieldWhenGreaterThan(sbBeginJson, ApiConstants.FIRST_SEQ, Long.valueOf(this.firstSequence), 1L);
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.ALLOW_MSG_TTL, Boolean.valueOf(this.allowMessageTtl));
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.SUBJECT_DELETE_MARKER_TTL, this.subjectDeleteMarkerTtl);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return toJson();
    }

    public static Builder builder(StreamConfiguration streamConfiguration) {
        return new Builder(streamConfiguration);
    }

    public static StreamConfiguration instance(String str) throws JsonParseException {
        return instance(JsonParser.parse(str));
    }
}
