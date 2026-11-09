package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.time.Duration;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class SourceInfoBase {
    protected final Duration active;
    protected final Error error;
    protected final External external;
    protected JsonValue jv;
    protected final long lag;
    protected final String name;
    protected final List<SubjectTransform> subjectTransforms;

    public SourceInfoBase(JsonValue jsonValue) {
        this.jv = jsonValue;
        this.name = JsonValueUtils.readString(jsonValue, ApiConstants.NAME);
        this.lag = JsonValueUtils.readLong(jsonValue, ApiConstants.LAG, 0L);
        this.active = JsonValueUtils.readNanos(jsonValue, ApiConstants.ACTIVE, Duration.ZERO);
        this.external = External.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.EXTERNAL));
        this.subjectTransforms = SubjectTransform.optionalListOf(JsonValueUtils.readValue(jsonValue, ApiConstants.SUBJECT_TRANSFORMS));
        this.error = Error.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.ERROR));
    }

    public Duration getActive() {
        return this.active;
    }

    public Error getError() {
        return this.error;
    }

    public External getExternal() {
        return this.external;
    }

    public long getLag() {
        return this.lag;
    }

    public String getName() {
        return this.name;
    }

    public List<SubjectTransform> getSubjectTransforms() {
        return this.subjectTransforms;
    }
}
