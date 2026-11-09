package androidx.work;

import androidx.work.WorkRequest;
import defpackage.aj;
import defpackage.ce;
import defpackage.oy;
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
            oy.j(cls, "workerClass");
            getWorkSpec$work_runtime_release().inputMergerClassName = OverwritingInputMerger.class.getName();
        }

        @Override // androidx.work.WorkRequest.Builder
        public Builder getThisObject$work_runtime_release() {
            return this;
        }

        public final Builder setInputMerger(Class<? extends InputMerger> cls) {
            oy.j(cls, "inputMerger");
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
        public /* synthetic */ Companion(aj ajVar) {
            this();
        }

        public final OneTimeWorkRequest from(Class<? extends ListenableWorker> cls) {
            oy.j(cls, "workerClass");
            return new Builder(cls).build();
        }

        private Companion() {
        }

        public final List<OneTimeWorkRequest> from(List<? extends Class<? extends ListenableWorker>> list) {
            oy.j(list, "workerClasses");
            ArrayList arrayList = new ArrayList(ce.Z(list));
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
        oy.j(builder, "builder");
    }

    public static final OneTimeWorkRequest from(Class<? extends ListenableWorker> cls) {
        return Companion.from(cls);
    }

    public static final List<OneTimeWorkRequest> from(List<? extends Class<? extends ListenableWorker>> list) {
        return Companion.from(list);
    }
}
