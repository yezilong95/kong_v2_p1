package io.nats.client.api;

import io.nats.client.JetStreamOptions;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class SourceBase implements JsonSerializable {
    private final External external;
    private final String filterSubject;
    private final String name;
    private final long startSeq;
    private final ZonedDateTime startTime;
    private final List<SubjectTransform> subjectTransforms;

    public SourceBase(JsonValue jsonValue) {
        this.name = JsonValueUtils.readString(jsonValue, ApiConstants.NAME);
        this.startSeq = JsonValueUtils.readLong(jsonValue, ApiConstants.OPT_START_SEQ, 0L);
        this.startTime = JsonValueUtils.readDate(jsonValue, ApiConstants.OPT_START_TIME);
        this.filterSubject = JsonValueUtils.readString(jsonValue, ApiConstants.FILTER_SUBJECT);
        this.external = External.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.EXTERNAL));
        this.subjectTransforms = SubjectTransform.optionalListOf(JsonValueUtils.readValue(jsonValue, ApiConstants.SUBJECT_TRANSFORMS));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SourceBase sourceBase = (SourceBase) obj;
        if (this.startSeq == sourceBase.startSeq && Objects.equals(this.name, sourceBase.name) && Objects.equals(this.startTime, sourceBase.startTime) && Objects.equals(this.filterSubject, sourceBase.filterSubject) && Objects.equals(this.external, sourceBase.external)) {
            return Validator.listsAreEquivalent(this.subjectTransforms, sourceBase.subjectTransforms);
        }
        return false;
    }

    public External getExternal() {
        return this.external;
    }

    public String getFilterSubject() {
        return this.filterSubject;
    }

    public String getName() {
        return this.name;
    }

    public String getSourceName() {
        return this.name;
    }

    public long getStartSeq() {
        return this.startSeq;
    }

    public ZonedDateTime getStartTime() {
        return this.startTime;
    }

    public List<SubjectTransform> getSubjectTransforms() {
        return this.subjectTransforms;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (Long.hashCode(this.startSeq) + ((str != null ? str.hashCode() : 0) * 31)) * 31;
        ZonedDateTime zonedDateTime = this.startTime;
        int iHashCode2 = (iHashCode + (zonedDateTime != null ? zonedDateTime.hashCode() : 0)) * 31;
        String str2 = this.filterSubject;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        External external = this.external;
        int iHashCode4 = (iHashCode3 + (external != null ? external.hashCode() : 0)) * 31;
        List<SubjectTransform> list = this.subjectTransforms;
        return iHashCode4 + (list != null ? list.hashCode() : 0);
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.NAME, this.name);
        JsonUtils.addFieldWhenGreaterThan(sbBeginJson, ApiConstants.OPT_START_SEQ, Long.valueOf(this.startSeq), 0L);
        JsonUtils.addField(sbBeginJson, ApiConstants.OPT_START_TIME, this.startTime);
        JsonUtils.addField(sbBeginJson, ApiConstants.FILTER_SUBJECT, this.filterSubject);
        JsonUtils.addField(sbBeginJson, ApiConstants.EXTERNAL, this.external);
        JsonUtils.addJsons(sbBeginJson, ApiConstants.SUBJECT_TRANSFORMS, this.subjectTransforms);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return JsonUtils.toKey(getClass()) + toJson();
    }

    public static abstract class SourceBaseBuilder<T> {
        External external;
        String filterSubject;
        String name;
        long startSeq;
        ZonedDateTime startTime;
        List<SubjectTransform> subjectTransforms;

        public SourceBaseBuilder() {
            this.subjectTransforms = new ArrayList();
        }

        public T domain(String str) {
            String strConvertDomainToPrefix = JetStreamOptions.convertDomainToPrefix(str);
            this.external = strConvertDomainToPrefix == null ? null : External.builder().api(strConvertDomainToPrefix).build();
            return getThis();
        }

        public T external(External external) {
            this.external = external;
            return getThis();
        }

        public T filterSubject(String str) {
            this.filterSubject = str;
            return getThis();
        }

        public abstract T getThis();

        public T name(String str) {
            this.name = str;
            return getThis();
        }

        public T sourceName(String str) {
            this.name = str;
            return getThis();
        }

        public T startSeq(long j) {
            this.startSeq = j;
            return getThis();
        }

        public T startTime(ZonedDateTime zonedDateTime) {
            this.startTime = zonedDateTime;
            return getThis();
        }

        public T subjectTransforms(SubjectTransform... subjectTransformArr) {
            this.subjectTransforms = subjectTransformArr == null ? null : Arrays.asList(subjectTransformArr);
            return getThis();
        }

        public SourceBaseBuilder(SourceBase sourceBase) {
            this.subjectTransforms = new ArrayList();
            this.name = sourceBase.name;
            this.startSeq = sourceBase.startSeq;
            this.startTime = sourceBase.startTime;
            this.filterSubject = sourceBase.filterSubject;
            this.external = sourceBase.external;
            this.subjectTransforms = sourceBase.getSubjectTransforms();
        }

        public T subjectTransforms(List<SubjectTransform> list) {
            this.subjectTransforms = list;
            return getThis();
        }
    }

    public SourceBase(SourceBaseBuilder<?> sourceBaseBuilder) {
        this.name = sourceBaseBuilder.name;
        this.startSeq = sourceBaseBuilder.startSeq;
        this.startTime = sourceBaseBuilder.startTime;
        this.filterSubject = sourceBaseBuilder.filterSubject;
        this.external = sourceBaseBuilder.external;
        this.subjectTransforms = sourceBaseBuilder.subjectTransforms;
    }
}
