package androidx.work;

import androidx.work.WorkRequest;
import defpackage.t40;
import defpackage.tm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class OneTimeWorkRequest extends WorkRequest {
    public static final Companion Companion = new Companion(null);

    public static final class Builder extends WorkRequest.Builder<Builder, OneTimeWorkRequest> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Class<? extends ListenableWorker> cls) {
            super(cls);
            t40.n(cls, "workerClass");
            getWorkSpec$work_runtime_release().inputMergerClassName = OverwritingInputMerger.class.getName();
        }

        @Override // androidx.work.WorkRequest.Builder
        public Builder getThisObject$work_runtime_release() {
            return this;
        }

        public final Builder setInputMerger(Class<? extends InputMerger> cls) {
            t40.n(cls, "inputMerger");
            getWorkSpec$work_runtime_release().inputMergerClassName = cls.getName();
            return this;
        }

        @Override // androidx.work.WorkRequest.Builder
        public OneTimeWorkRequest buildInternal$work_runtime_release() {
            if (getBackoffCriteriaSet$work_runtime_release() && getWorkSpec$work_runtime_release().constraints.requiresDeviceIdle()) {
                throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
            }
            return new OneTimeWorkRequest(this);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(tm tmVar) {
            this();
        }

        public final OneTimeWorkRequest from(Class<? extends ListenableWorker> cls) {
            t40.n(cls, "workerClass");
            return new Builder(cls).build();
        }

        private Companion() {
        }

        public final List<OneTimeWorkRequest> from(List<? extends Class<? extends ListenableWorker>> list) {
            t40.n(list, "workerClasses");
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new Builder((Class) it.next()).build());
            }
            return arrayList;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneTimeWorkRequest(Builder builder) {
        super(builder.getId$work_runtime_release(), builder.getWorkSpec$work_runtime_release(), builder.getTags$work_runtime_release());
        t40.n(builder, "builder");
    }

    public static final OneTimeWorkRequest from(Class<? extends ListenableWorker> cls) {
        return Companion.from(cls);
    }

    public static final List<OneTimeWorkRequest> from(List<? extends Class<? extends ListenableWorker>> list) {
        return Companion.from(list);
    }
}
