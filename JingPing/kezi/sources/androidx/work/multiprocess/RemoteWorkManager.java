package androidx.work.multiprocess;

import android.content.Context;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ForegroundInfo;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkQuery;
import androidx.work.WorkRequest;
import androidx.work.impl.WorkManagerImpl;
import defpackage.x20;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class RemoteWorkManager {
    public static RemoteWorkManager getInstance(Context context) {
        RemoteWorkManager remoteWorkManager = WorkManagerImpl.getInstance(context).getRemoteWorkManager();
        if (remoteWorkManager != null) {
            return remoteWorkManager;
        }
        throw new IllegalStateException("Unable to initialize RemoteWorkManager");
    }

    public final RemoteWorkContinuation beginUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, OneTimeWorkRequest oneTimeWorkRequest) {
        return beginUniqueWork(str, existingWorkPolicy, Collections.singletonList(oneTimeWorkRequest));
    }

    public abstract RemoteWorkContinuation beginUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> list);

    public final RemoteWorkContinuation beginWith(OneTimeWorkRequest oneTimeWorkRequest) {
        return beginWith(Collections.singletonList(oneTimeWorkRequest));
    }

    public abstract RemoteWorkContinuation beginWith(List<OneTimeWorkRequest> list);

    public abstract x20 cancelAllWork();

    public abstract x20 cancelAllWorkByTag(String str);

    public abstract x20 cancelUniqueWork(String str);

    public abstract x20 cancelWorkById(UUID uuid);

    public abstract x20 enqueue(WorkContinuation workContinuation);

    public abstract x20 enqueue(WorkRequest workRequest);

    public abstract x20 enqueue(List<WorkRequest> list);

    public abstract x20 enqueueUniquePeriodicWork(String str, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, PeriodicWorkRequest periodicWorkRequest);

    public final x20 enqueueUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, OneTimeWorkRequest oneTimeWorkRequest) {
        return enqueueUniqueWork(str, existingWorkPolicy, Collections.singletonList(oneTimeWorkRequest));
    }

    public abstract x20 enqueueUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> list);

    public abstract x20 getWorkInfos(WorkQuery workQuery);

    public abstract x20 setForegroundAsync(String str, ForegroundInfo foregroundInfo);

    public abstract x20 setProgress(UUID uuid, Data data);
}
