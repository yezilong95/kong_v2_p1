package androidx.work.impl.utils;

import android.os.Build;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import androidx.work.impl.workers.ConstraintTrackingWorkerKt;
import defpackage.oy;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class EnqueueUtilsKt {
    public static final WorkSpec tryDelegateConstrainedWorkSpec(WorkSpec workSpec) throws Throwable {
        oy.j(workSpec, "workSpec");
        Constraints constraints = workSpec.constraints;
        String str = workSpec.workerClassName;
        if (oy.c(str, ConstraintTrackingWorker.class.getName()) || !(constraints.requiresBatteryNotLow() || constraints.requiresStorageNotLow())) {
            return workSpec;
        }
        Data dataBuild = new Data.Builder().putAll(workSpec.input).putString(ConstraintTrackingWorkerKt.ARGUMENT_CLASS_NAME, str).build();
        oy.i(dataBuild, "Builder().putAll(workSpe…ame)\n            .build()");
        return WorkSpec.copy$default(workSpec, null, null, ConstraintTrackingWorker.class.getName(), null, dataBuild, null, 0L, 0L, 0L, null, 0, null, 0L, 0L, 0L, 0L, false, null, 0, 0, 1048555, null);
    }

    private static final boolean usesScheduler(List<? extends Scheduler> list, String str) {
        try {
            Class<?> cls = Class.forName(str);
            if (list != null && list.isEmpty()) {
                return false;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (cls.isAssignableFrom(((Scheduler) it.next()).getClass())) {
                    return true;
                }
            }
        } catch (ClassNotFoundException unused) {
        }
        return false;
    }

    public static final WorkSpec wrapInConstraintTrackingWorkerIfNeeded(List<? extends Scheduler> list, WorkSpec workSpec) {
        oy.j(list, "schedulers");
        oy.j(workSpec, "workSpec");
        return Build.VERSION.SDK_INT < 26 ? tryDelegateConstrainedWorkSpec(workSpec) : workSpec;
    }
}
