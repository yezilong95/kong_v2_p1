package androidx.work;

import android.annotation.SuppressLint;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.DurationApi26Impl;
import defpackage.t40;
import defpackage.tm;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class PeriodicWorkRequest extends WorkRequest {
    public static final Companion Companion = new Companion(null);

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_FLEX_MILLIS = 300000;

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_INTERVAL_MILLIS = 900000;

    public static final class Builder extends WorkRequest.Builder<Builder, PeriodicWorkRequest> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Class<? extends ListenableWorker> cls, long j, TimeUnit timeUnit) {
            super(cls);
            t40.n(cls, "workerClass");
            t40.n(timeUnit, "repeatIntervalTimeUnit");
            getWorkSpec$work_runtime_release().setPeriodic(timeUnit.toMillis(j));
        }

        @Override // androidx.work.WorkRequest.Builder
        public Builder getThisObject$work_runtime_release() {
            return this;
        }

        @Override // androidx.work.WorkRequest.Builder
        public PeriodicWorkRequest buildInternal$work_runtime_release() {
            if (getBackoffCriteriaSet$work_runtime_release() && getWorkSpec$work_runtime_release().constraints.requiresDeviceIdle()) {
                throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
            }
            if (getWorkSpec$work_runtime_release().expedited) {
                throw new IllegalArgumentException("PeriodicWorkRequests cannot be expedited");
            }
            return new PeriodicWorkRequest(this);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Class<? extends ListenableWorker> cls, Duration duration) {
            super(cls);
            t40.n(cls, "workerClass");
            t40.n(duration, "repeatInterval");
            getWorkSpec$work_runtime_release().setPeriodic(DurationApi26Impl.toMillisCompat(duration));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Class<? extends ListenableWorker> cls, long j, TimeUnit timeUnit, long j2, TimeUnit timeUnit2) {
            super(cls);
            t40.n(cls, "workerClass");
            t40.n(timeUnit, "repeatIntervalTimeUnit");
            t40.n(timeUnit2, "flexIntervalTimeUnit");
            getWorkSpec$work_runtime_release().setPeriodic(timeUnit.toMillis(j), timeUnit2.toMillis(j2));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Class<? extends ListenableWorker> cls, Duration duration, Duration duration2) {
            super(cls);
            t40.n(cls, "workerClass");
            t40.n(duration, "repeatInterval");
            t40.n(duration2, "flexInterval");
            getWorkSpec$work_runtime_release().setPeriodic(DurationApi26Impl.toMillisCompat(duration), DurationApi26Impl.toMillisCompat(duration2));
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(tm tmVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeriodicWorkRequest(Builder builder) {
        super(builder.getId$work_runtime_release(), builder.getWorkSpec$work_runtime_release(), builder.getTags$work_runtime_release());
        t40.n(builder, "builder");
    }
}
