package androidx.work;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import androidx.work.impl.WorkManagerImpl;
import defpackage.b30;
import defpackage.x20;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AddedAbstractMethod"})
public abstract class WorkManager {

    public enum UpdateResult {
        NOT_APPLIED,
        APPLIED_IMMEDIATELY,
        APPLIED_FOR_NEXT_RUN
    }

    @Deprecated
    public static WorkManager getInstance() {
        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance();
        if (workManagerImpl != null) {
            return workManagerImpl;
        }
        throw new IllegalStateException("WorkManager is not initialized properly.  The most likely cause is that you disabled WorkManagerInitializer in your manifest but forgot to call WorkManager#initialize in your Application#onCreate or a ContentProvider.");
    }

    public static void initialize(Context context, Configuration configuration) {
        WorkManagerImpl.initialize(context, configuration);
    }

    public static boolean isInitialized() {
        return WorkManagerImpl.isInitialized();
    }

    public final WorkContinuation beginUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, OneTimeWorkRequest oneTimeWorkRequest) {
        return beginUniqueWork(str, existingWorkPolicy, Collections.singletonList(oneTimeWorkRequest));
    }

    public abstract WorkContinuation beginUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> list);

    public final WorkContinuation beginWith(OneTimeWorkRequest oneTimeWorkRequest) {
        return beginWith(Collections.singletonList(oneTimeWorkRequest));
    }

    public abstract WorkContinuation beginWith(List<OneTimeWorkRequest> list);

    public abstract Operation cancelAllWork();

    public abstract Operation cancelAllWorkByTag(String str);

    public abstract Operation cancelUniqueWork(String str);

    public abstract Operation cancelWorkById(UUID uuid);

    public abstract PendingIntent createCancelPendingIntent(UUID uuid);

    public final Operation enqueue(WorkRequest workRequest) {
        return enqueue(Collections.singletonList(workRequest));
    }

    public abstract Operation enqueue(List<? extends WorkRequest> list);

    public abstract Operation enqueueUniquePeriodicWork(String str, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, PeriodicWorkRequest periodicWorkRequest);

    public Operation enqueueUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, OneTimeWorkRequest oneTimeWorkRequest) {
        return enqueueUniqueWork(str, existingWorkPolicy, Collections.singletonList(oneTimeWorkRequest));
    }

    public abstract Operation enqueueUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> list);

    public abstract Configuration getConfiguration();

    public abstract x20 getLastCancelAllTimeMillis();

    public abstract b30 getLastCancelAllTimeMillisLiveData();

    public abstract x20 getWorkInfoById(UUID uuid);

    public abstract b30 getWorkInfoByIdLiveData(UUID uuid);

    public abstract x20 getWorkInfos(WorkQuery workQuery);

    public abstract x20 getWorkInfosByTag(String str);

    public abstract b30 getWorkInfosByTagLiveData(String str);

    public abstract x20 getWorkInfosForUniqueWork(String str);

    public abstract b30 getWorkInfosForUniqueWorkLiveData(String str);

    public abstract b30 getWorkInfosLiveData(WorkQuery workQuery);

    public abstract Operation pruneWork();

    public abstract x20 updateWork(WorkRequest workRequest);

    public static WorkManager getInstance(Context context) {
        return WorkManagerImpl.getInstance(context);
    }
}
