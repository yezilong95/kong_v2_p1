package androidx.work.impl.utils;

import androidx.work.WorkInfo;
import androidx.work.WorkQuery;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import defpackage.x20;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class StatusRunnable<T> implements Runnable {
    private final SettableFuture<T> mFuture = SettableFuture.create();

    public static StatusRunnable<List<WorkInfo>> forStringIds(final WorkManagerImpl workManagerImpl, final List<String> list) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.1
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return (List) WorkSpec.WORK_INFO_MAPPER.apply(workManagerImpl.getWorkDatabase().workSpecDao().getWorkStatusPojoForIds(list));
            }
        };
    }

    public static StatusRunnable<List<WorkInfo>> forTag(final WorkManagerImpl workManagerImpl, final String str) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.3
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return (List) WorkSpec.WORK_INFO_MAPPER.apply(workManagerImpl.getWorkDatabase().workSpecDao().getWorkStatusPojoForTag(str));
            }
        };
    }

    public static StatusRunnable<WorkInfo> forUUID(final WorkManagerImpl workManagerImpl, final UUID uuid) {
        return new StatusRunnable<WorkInfo>() { // from class: androidx.work.impl.utils.StatusRunnable.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.work.impl.utils.StatusRunnable
            public WorkInfo runInternal() {
                WorkSpec.WorkInfoPojo workStatusPojoForId = workManagerImpl.getWorkDatabase().workSpecDao().getWorkStatusPojoForId(uuid.toString());
                if (workStatusPojoForId != null) {
                    return workStatusPojoForId.toWorkInfo();
                }
                return null;
            }
        };
    }

    public static StatusRunnable<List<WorkInfo>> forUniqueWork(final WorkManagerImpl workManagerImpl, final String str) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.4
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return (List) WorkSpec.WORK_INFO_MAPPER.apply(workManagerImpl.getWorkDatabase().workSpecDao().getWorkStatusPojoForName(str));
            }
        };
    }

    public static StatusRunnable<List<WorkInfo>> forWorkQuerySpec(final WorkManagerImpl workManagerImpl, final WorkQuery workQuery) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.5
            @Override // androidx.work.impl.utils.StatusRunnable
            public List<WorkInfo> runInternal() {
                return (List) WorkSpec.WORK_INFO_MAPPER.apply(workManagerImpl.getWorkDatabase().rawWorkInfoDao().getWorkInfoPojos(RawQueries.toRawQuery(workQuery)));
            }
        };
    }

    public x20 getFuture() {
        return this.mFuture;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.mFuture.set(runInternal());
        } catch (Throwable th) {
            this.mFuture.setException(th);
        }
    }

    public abstract T runInternal();
}
