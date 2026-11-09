package androidx.work;

import android.annotation.SuppressLint;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.DurationApi26Impl;
import defpackage.ec0;
import defpackage.t40;
import defpackage.tm;
import io.nats.client.support.ApiConstants;
import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* JADX INFO: loaded from: classes.dex */
public abstract class WorkRequest {
    public static final Companion Companion = new Companion(null);
    public static final long DEFAULT_BACKOFF_DELAY_MILLIS = 30000;

    @SuppressLint({"MinMaxConstant"})
    public static final long MAX_BACKOFF_MILLIS = 18000000;

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_BACKOFF_MILLIS = 10000;
    private final UUID id;
    private final Set<String> tags;
    private final WorkSpec workSpec;

    public static final class Companion {
        public /* synthetic */ Companion(tm tmVar) {
            this();
        }

        private Companion() {
        }
    }

    public WorkRequest(UUID uuid, WorkSpec workSpec, Set<String> set) {
        t40.n(uuid, ApiConstants.ID);
        t40.n(workSpec, "workSpec");
        t40.n(set, ApiConstants.TAGS);
        this.id = uuid;
        this.workSpec = workSpec;
        this.tags = set;
    }

    public UUID getId() {
        return this.id;
    }

    public final String getStringId() {
        String string = getId().toString();
        t40.m(string, "id.toString()");
        return string;
    }

    public final Set<String> getTags() {
        return this.tags;
    }

    public final WorkSpec getWorkSpec() {
        return this.workSpec;
    }

    public static abstract class Builder<B extends Builder<B, ?>, W extends WorkRequest> {
        private boolean backoffCriteriaSet;
        private UUID id;
        private final Set<String> tags;
        private WorkSpec workSpec;
        private final Class<? extends ListenableWorker> workerClass;

        public Builder(Class<? extends ListenableWorker> cls) {
            t40.n(cls, "workerClass");
            this.workerClass = cls;
            UUID uuidRandomUUID = UUID.randomUUID();
            t40.m(uuidRandomUUID, "randomUUID()");
            this.id = uuidRandomUUID;
            String string = this.id.toString();
            t40.m(string, "id.toString()");
            this.workSpec = new WorkSpec(string, cls.getName());
            String[] strArr = {cls.getName()};
            LinkedHashSet linkedHashSet = new LinkedHashSet(ec0.k0(1));
            linkedHashSet.add(strArr[0]);
            this.tags = linkedHashSet;
        }

        public final B addTag(String str) {
            t40.n(str, "tag");
            this.tags.add(str);
            return (B) getThisObject$work_runtime_release();
        }

        public final W build() {
            W w = (W) buildInternal$work_runtime_release();
            Constraints constraints = this.workSpec.constraints;
            boolean z = constraints.hasContentUriTriggers() || constraints.requiresBatteryNotLow() || constraints.requiresCharging() || constraints.requiresDeviceIdle();
            WorkSpec workSpec = this.workSpec;
            if (workSpec.expedited) {
                if (z) {
                    throw new IllegalArgumentException("Expedited jobs only support network and storage constraints");
                }
                if (workSpec.initialDelay > 0) {
                    throw new IllegalArgumentException("Expedited jobs cannot be delayed");
                }
            }
            UUID uuidRandomUUID = UUID.randomUUID();
            t40.m(uuidRandomUUID, "randomUUID()");
            setId(uuidRandomUUID);
            return w;
        }

        public abstract W buildInternal$work_runtime_release();

        public final boolean getBackoffCriteriaSet$work_runtime_release() {
            return this.backoffCriteriaSet;
        }

        public final UUID getId$work_runtime_release() {
            return this.id;
        }

        public final Set<String> getTags$work_runtime_release() {
            return this.tags;
        }

        public abstract B getThisObject$work_runtime_release();

        public final WorkSpec getWorkSpec$work_runtime_release() {
            return this.workSpec;
        }

        public final Class<? extends ListenableWorker> getWorkerClass$work_runtime_release() {
            return this.workerClass;
        }

        public final B keepResultsForAtLeast(long j, TimeUnit timeUnit) {
            t40.n(timeUnit, "timeUnit");
            this.workSpec.minimumRetentionDuration = timeUnit.toMillis(j);
            return (B) getThisObject$work_runtime_release();
        }

        public final B setBackoffCriteria(BackoffPolicy backoffPolicy, long j, TimeUnit timeUnit) {
            t40.n(backoffPolicy, "backoffPolicy");
            t40.n(timeUnit, "timeUnit");
            this.backoffCriteriaSet = true;
            WorkSpec workSpec = this.workSpec;
            workSpec.backoffPolicy = backoffPolicy;
            workSpec.setBackoffDelayDuration(timeUnit.toMillis(j));
            return (B) getThisObject$work_runtime_release();
        }

        public final void setBackoffCriteriaSet$work_runtime_release(boolean z) {
            this.backoffCriteriaSet = z;
        }

        public final B setConstraints(Constraints constraints) {
            t40.n(constraints, CryptoServicesPermission.CONSTRAINTS);
            this.workSpec.constraints = constraints;
            return (B) getThisObject$work_runtime_release();
        }

        @SuppressLint({"MissingGetterMatchingBuilder"})
        public B setExpedited(OutOfQuotaPolicy outOfQuotaPolicy) {
            t40.n(outOfQuotaPolicy, "policy");
            WorkSpec workSpec = this.workSpec;
            workSpec.expedited = true;
            workSpec.outOfQuotaPolicy = outOfQuotaPolicy;
            return (B) getThisObject$work_runtime_release();
        }

        public final B setId(UUID uuid) {
            t40.n(uuid, ApiConstants.ID);
            this.id = uuid;
            String string = uuid.toString();
            t40.m(string, "id.toString()");
            this.workSpec = new WorkSpec(string, this.workSpec);
            return (B) getThisObject$work_runtime_release();
        }

        public final void setId$work_runtime_release(UUID uuid) {
            t40.n(uuid, "<set-?>");
            this.id = uuid;
        }

        public B setInitialDelay(long j, TimeUnit timeUnit) {
            t40.n(timeUnit, "timeUnit");
            this.workSpec.initialDelay = timeUnit.toMillis(j);
            if (Long.MAX_VALUE - System.currentTimeMillis() > this.workSpec.initialDelay) {
                return (B) getThisObject$work_runtime_release();
            }
            throw new IllegalArgumentException("The given initial delay is too large and will cause an overflow!");
        }

        public final B setInitialRunAttemptCount(int i) {
            this.workSpec.runAttemptCount = i;
            return (B) getThisObject$work_runtime_release();
        }

        public final B setInitialState(WorkInfo.State state) {
            t40.n(state, ApiConstants.STATE);
            this.workSpec.state = state;
            return (B) getThisObject$work_runtime_release();
        }

        public final B setInputData(Data data) {
            t40.n(data, "inputData");
            this.workSpec.input = data;
            return (B) getThisObject$work_runtime_release();
        }

        public final B setLastEnqueueTime(long j, TimeUnit timeUnit) {
            t40.n(timeUnit, "timeUnit");
            this.workSpec.lastEnqueueTime = timeUnit.toMillis(j);
            return (B) getThisObject$work_runtime_release();
        }

        public final B setScheduleRequestedAt(long j, TimeUnit timeUnit) {
            t40.n(timeUnit, "timeUnit");
            this.workSpec.scheduleRequestedAt = timeUnit.toMillis(j);
            return (B) getThisObject$work_runtime_release();
        }

        public final void setWorkSpec$work_runtime_release(WorkSpec workSpec) {
            t40.n(workSpec, "<set-?>");
            this.workSpec = workSpec;
        }

        public final B keepResultsForAtLeast(Duration duration) {
            t40.n(duration, "duration");
            this.workSpec.minimumRetentionDuration = DurationApi26Impl.toMillisCompat(duration);
            return (B) getThisObject$work_runtime_release();
        }

        public final B setBackoffCriteria(BackoffPolicy backoffPolicy, Duration duration) {
            t40.n(backoffPolicy, "backoffPolicy");
            t40.n(duration, "duration");
            this.backoffCriteriaSet = true;
            WorkSpec workSpec = this.workSpec;
            workSpec.backoffPolicy = backoffPolicy;
            workSpec.setBackoffDelayDuration(DurationApi26Impl.toMillisCompat(duration));
            return (B) getThisObject$work_runtime_release();
        }

        public B setInitialDelay(Duration duration) {
            t40.n(duration, "duration");
            this.workSpec.initialDelay = DurationApi26Impl.toMillisCompat(duration);
            if (Long.MAX_VALUE - System.currentTimeMillis() > this.workSpec.initialDelay) {
                return (B) getThisObject$work_runtime_release();
            }
            throw new IllegalArgumentException("The given initial delay is too large and will cause an overflow!");
        }
    }
}
