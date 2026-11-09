package androidx.work.impl;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.Configuration;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.R;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkQuery;
import androidx.work.WorkRequest;
import androidx.work.WorkerParameters;
import androidx.work.impl.background.greedy.GreedyScheduler;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.CancelWorkRunnable;
import androidx.work.impl.utils.ForceStopRunnable;
import androidx.work.impl.utils.LiveDataUtils;
import androidx.work.impl.utils.PreferenceUtils;
import androidx.work.impl.utils.PruneWorkRunnable;
import androidx.work.impl.utils.RawQueries;
import androidx.work.impl.utils.StartWorkRunnable;
import androidx.work.impl.utils.StatusRunnable;
import androidx.work.impl.utils.StopWorkRunnable;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import androidx.work.multiprocess.RemoteWorkManager;
import defpackage.ca0;
import defpackage.ea0;
import defpackage.j10;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class WorkManagerImpl extends WorkManager {
    public static final int MAX_PRE_JOB_SCHEDULER_API_LEVEL = 22;
    public static final int MIN_JOB_SCHEDULER_API_LEVEL = 23;
    public static final String REMOTE_WORK_MANAGER_CLIENT = "androidx.work.multiprocess.RemoteWorkManagerClient";
    private Configuration mConfiguration;
    private Context mContext;
    private boolean mForceStopRunnableCompleted;
    private PreferenceUtils mPreferenceUtils;
    private Processor mProcessor;
    private volatile RemoteWorkManager mRemoteWorkManager;
    private BroadcastReceiver.PendingResult mRescheduleReceiverResult;
    private List<Scheduler> mSchedulers;
    private final Trackers mTrackers;
    private WorkDatabase mWorkDatabase;
    private TaskExecutor mWorkTaskExecutor;
    private static final String TAG = Logger.tagWithPrefix("WorkManagerImpl");
    private static WorkManagerImpl sDelegatedInstance = null;
    private static WorkManagerImpl sDefaultInstance = null;
    private static final Object sLock = new Object();

    public static class Api24Impl {
        private Api24Impl() {
        }

        public static boolean isDeviceProtectedStorage(Context context) {
            return context.isDeviceProtectedStorage();
        }
    }

    public WorkManagerImpl(Context context, Configuration configuration, TaskExecutor taskExecutor) {
        this(context, configuration, taskExecutor, context.getResources().getBoolean(R.bool.workmanager_test_configuration));
    }

    @Deprecated
    public static WorkManagerImpl getInstance() {
        synchronized (sLock) {
            try {
                WorkManagerImpl workManagerImpl = sDelegatedInstance;
                if (workManagerImpl != null) {
                    return workManagerImpl;
                }
                return sDefaultInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void initialize(Context context, Configuration configuration) {
        synchronized (sLock) {
            try {
                WorkManagerImpl workManagerImpl = sDelegatedInstance;
                if (workManagerImpl != null && sDefaultInstance != null) {
                    throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class level Javadoc for more information.");
                }
                if (workManagerImpl == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (sDefaultInstance == null) {
                        sDefaultInstance = new WorkManagerImpl(applicationContext, configuration, new WorkManagerTaskExecutor(configuration.getTaskExecutor()));
                    }
                    sDelegatedInstance = sDefaultInstance;
                }
            } finally {
            }
        }
    }

    private void internalInit(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase, List<Scheduler> list, Processor processor) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mConfiguration = configuration;
        this.mWorkTaskExecutor = taskExecutor;
        this.mWorkDatabase = workDatabase;
        this.mSchedulers = list;
        this.mProcessor = processor;
        this.mPreferenceUtils = new PreferenceUtils(workDatabase);
        this.mForceStopRunnableCompleted = false;
        if (Api24Impl.isDeviceProtectedStorage(applicationContext)) {
            throw new IllegalStateException("Cannot initialize WorkManager in direct boot mode");
        }
        this.mWorkTaskExecutor.executeOnTaskThread(new ForceStopRunnable(applicationContext, this));
    }

    public static boolean isInitialized() {
        return getInstance() != null;
    }

    public static void setDelegate(WorkManagerImpl workManagerImpl) {
        synchronized (sLock) {
            sDelegatedInstance = workManagerImpl;
        }
    }

    private void tryInitializeMultiProcessSupport() {
        try {
            this.mRemoteWorkManager = (RemoteWorkManager) Class.forName(REMOTE_WORK_MANAGER_CLIENT).getConstructor(Context.class, WorkManagerImpl.class).newInstance(this.mContext, this);
        } catch (Throwable th) {
            Logger.get().debug(TAG, "Unable to initialize multi-process support", th);
        }
    }

    @Override // androidx.work.WorkManager
    public WorkContinuation beginUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("beginUniqueWork needs at least one OneTimeWorkRequest.");
        }
        return new WorkContinuationImpl(this, str, existingWorkPolicy, list);
    }

    @Override // androidx.work.WorkManager
    public WorkContinuation beginWith(List<OneTimeWorkRequest> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("beginWith needs at least one OneTimeWorkRequest.");
        }
        return new WorkContinuationImpl(this, list);
    }

    @Override // androidx.work.WorkManager
    public Operation cancelAllWork() {
        CancelWorkRunnable cancelWorkRunnableForAll = CancelWorkRunnable.forAll(this);
        this.mWorkTaskExecutor.executeOnTaskThread(cancelWorkRunnableForAll);
        return cancelWorkRunnableForAll.getOperation();
    }

    @Override // androidx.work.WorkManager
    public Operation cancelAllWorkByTag(String str) {
        CancelWorkRunnable cancelWorkRunnableForTag = CancelWorkRunnable.forTag(str, this);
        this.mWorkTaskExecutor.executeOnTaskThread(cancelWorkRunnableForTag);
        return cancelWorkRunnableForTag.getOperation();
    }

    @Override // androidx.work.WorkManager
    public Operation cancelUniqueWork(String str) {
        CancelWorkRunnable cancelWorkRunnableForName = CancelWorkRunnable.forName(str, this, true);
        this.mWorkTaskExecutor.executeOnTaskThread(cancelWorkRunnableForName);
        return cancelWorkRunnableForName.getOperation();
    }

    @Override // androidx.work.WorkManager
    public Operation cancelWorkById(UUID uuid) {
        CancelWorkRunnable cancelWorkRunnableForId = CancelWorkRunnable.forId(uuid, this);
        this.mWorkTaskExecutor.executeOnTaskThread(cancelWorkRunnableForId);
        return cancelWorkRunnableForId.getOperation();
    }

    @Override // androidx.work.WorkManager
    public PendingIntent createCancelPendingIntent(UUID uuid) {
        return PendingIntent.getService(this.mContext, 0, SystemForegroundDispatcher.createCancelWorkIntent(this.mContext, uuid.toString()), Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728);
    }

    public List<Scheduler> createSchedulers(Context context, Configuration configuration, Trackers trackers) {
        return Arrays.asList(Schedulers.createBestAvailableBackgroundScheduler(context, this), new GreedyScheduler(context, configuration, trackers, this));
    }

    public WorkContinuationImpl createWorkContinuationForUniquePeriodicWork(String str, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, PeriodicWorkRequest periodicWorkRequest) {
        return new WorkContinuationImpl(this, str, existingPeriodicWorkPolicy == ExistingPeriodicWorkPolicy.KEEP ? ExistingWorkPolicy.KEEP : ExistingWorkPolicy.REPLACE, Collections.singletonList(periodicWorkRequest));
    }

    @Override // androidx.work.WorkManager
    public Operation enqueue(List<? extends WorkRequest> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
        }
        return new WorkContinuationImpl(this, list).enqueue();
    }

    @Override // androidx.work.WorkManager
    public Operation enqueueUniquePeriodicWork(String str, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, PeriodicWorkRequest periodicWorkRequest) {
        return existingPeriodicWorkPolicy == ExistingPeriodicWorkPolicy.UPDATE ? WorkerUpdater.enqueueUniquelyNamedPeriodic(this, str, periodicWorkRequest) : createWorkContinuationForUniquePeriodicWork(str, existingPeriodicWorkPolicy, periodicWorkRequest).enqueue();
    }

    @Override // androidx.work.WorkManager
    public Operation enqueueUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, List<OneTimeWorkRequest> list) {
        return new WorkContinuationImpl(this, str, existingWorkPolicy, list).enqueue();
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    @Override // androidx.work.WorkManager
    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    @Override // androidx.work.WorkManager
    public ca0 getLastCancelAllTimeMillis() {
        final SettableFuture settableFutureCreate = SettableFuture.create();
        final PreferenceUtils preferenceUtils = this.mPreferenceUtils;
        this.mWorkTaskExecutor.executeOnTaskThread(new Runnable() { // from class: androidx.work.impl.WorkManagerImpl.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    settableFutureCreate.set(Long.valueOf(preferenceUtils.getLastCancelAllTimeMillis()));
                } catch (Throwable th) {
                    settableFutureCreate.setException(th);
                }
            }
        });
        return settableFutureCreate;
    }

    @Override // androidx.work.WorkManager
    public ea0 getLastCancelAllTimeMillisLiveData() {
        return this.mPreferenceUtils.getLastCancelAllTimeMillisLiveData();
    }

    public PreferenceUtils getPreferenceUtils() {
        return this.mPreferenceUtils;
    }

    public Processor getProcessor() {
        return this.mProcessor;
    }

    public RemoteWorkManager getRemoteWorkManager() {
        if (this.mRemoteWorkManager == null) {
            synchronized (sLock) {
                try {
                    if (this.mRemoteWorkManager == null) {
                        tryInitializeMultiProcessSupport();
                        if (this.mRemoteWorkManager == null && !TextUtils.isEmpty(this.mConfiguration.getDefaultProcessName())) {
                            throw new IllegalStateException("Invalid multiprocess configuration. Define an `implementation` dependency on :work:work-multiprocess library");
                        }
                    }
                } finally {
                }
            }
        }
        return this.mRemoteWorkManager;
    }

    public List<Scheduler> getSchedulers() {
        return this.mSchedulers;
    }

    public Trackers getTrackers() {
        return this.mTrackers;
    }

    public WorkDatabase getWorkDatabase() {
        return this.mWorkDatabase;
    }

    @Override // androidx.work.WorkManager
    public ca0 getWorkInfoById(UUID uuid) {
        StatusRunnable<WorkInfo> statusRunnableForUUID = StatusRunnable.forUUID(this, uuid);
        this.mWorkTaskExecutor.getSerialTaskExecutor().execute(statusRunnableForUUID);
        return statusRunnableForUUID.getFuture();
    }

    @Override // androidx.work.WorkManager
    public ea0 getWorkInfoByIdLiveData(UUID uuid) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForIds(Collections.singletonList(uuid.toString())), new j10() { // from class: androidx.work.impl.WorkManagerImpl.2
            @Override // defpackage.j10
            public WorkInfo apply(List<WorkSpec.WorkInfoPojo> list) {
                if (list == null || list.size() <= 0) {
                    return null;
                }
                return list.get(0).toWorkInfo();
            }
        }, this.mWorkTaskExecutor);
    }

    @Override // androidx.work.WorkManager
    public ca0 getWorkInfos(WorkQuery workQuery) {
        StatusRunnable<List<WorkInfo>> statusRunnableForWorkQuerySpec = StatusRunnable.forWorkQuerySpec(this, workQuery);
        this.mWorkTaskExecutor.getSerialTaskExecutor().execute(statusRunnableForWorkQuerySpec);
        return statusRunnableForWorkQuerySpec.getFuture();
    }

    public ea0 getWorkInfosById(List<String> list) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForIds(list), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @Override // androidx.work.WorkManager
    public ca0 getWorkInfosByTag(String str) {
        StatusRunnable<List<WorkInfo>> statusRunnableForTag = StatusRunnable.forTag(this, str);
        this.mWorkTaskExecutor.getSerialTaskExecutor().execute(statusRunnableForTag);
        return statusRunnableForTag.getFuture();
    }

    @Override // androidx.work.WorkManager
    public ea0 getWorkInfosByTagLiveData(String str) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForTag(str), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @Override // androidx.work.WorkManager
    public ca0 getWorkInfosForUniqueWork(String str) {
        StatusRunnable<List<WorkInfo>> statusRunnableForUniqueWork = StatusRunnable.forUniqueWork(this, str);
        this.mWorkTaskExecutor.getSerialTaskExecutor().execute(statusRunnableForUniqueWork);
        return statusRunnableForUniqueWork.getFuture();
    }

    @Override // androidx.work.WorkManager
    public ea0 getWorkInfosForUniqueWorkLiveData(String str) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.workSpecDao().getWorkStatusPojoLiveDataForName(str), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    @Override // androidx.work.WorkManager
    public ea0 getWorkInfosLiveData(WorkQuery workQuery) {
        return LiveDataUtils.dedupedMappedLiveDataFor(this.mWorkDatabase.rawWorkInfoDao().getWorkInfoPojosLiveData(RawQueries.toRawQuery(workQuery)), WorkSpec.WORK_INFO_MAPPER, this.mWorkTaskExecutor);
    }

    public TaskExecutor getWorkTaskExecutor() {
        return this.mWorkTaskExecutor;
    }

    public void onForceStopRunnableCompleted() {
        synchronized (sLock) {
            try {
                this.mForceStopRunnableCompleted = true;
                BroadcastReceiver.PendingResult pendingResult = this.mRescheduleReceiverResult;
                if (pendingResult != null) {
                    pendingResult.finish();
                    this.mRescheduleReceiverResult = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.work.WorkManager
    public Operation pruneWork() {
        PruneWorkRunnable pruneWorkRunnable = new PruneWorkRunnable(this);
        this.mWorkTaskExecutor.executeOnTaskThread(pruneWorkRunnable);
        return pruneWorkRunnable.getOperation();
    }

    public void rescheduleEligibleWork() {
        SystemJobScheduler.cancelAll(getApplicationContext());
        getWorkDatabase().workSpecDao().resetScheduledState();
        Schedulers.schedule(getConfiguration(), getWorkDatabase(), getSchedulers());
    }

    public void setReschedulePendingResult(BroadcastReceiver.PendingResult pendingResult) {
        synchronized (sLock) {
            try {
                this.mRescheduleReceiverResult = pendingResult;
                if (this.mForceStopRunnableCompleted) {
                    pendingResult.finish();
                    this.mRescheduleReceiverResult = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void startWork(StartStopToken startStopToken) {
        startWork(startStopToken, null);
    }

    public void stopForegroundWork(WorkGenerationalId workGenerationalId) {
        this.mWorkTaskExecutor.executeOnTaskThread(new StopWorkRunnable(this, new StartStopToken(workGenerationalId), true));
    }

    public void stopWork(StartStopToken startStopToken) {
        this.mWorkTaskExecutor.executeOnTaskThread(new StopWorkRunnable(this, startStopToken, false));
    }

    @Override // androidx.work.WorkManager
    public ca0 updateWork(WorkRequest workRequest) {
        return WorkerUpdater.updateWorkImpl(this, workRequest);
    }

    public void startWork(StartStopToken startStopToken, WorkerParameters.RuntimeExtras runtimeExtras) {
        this.mWorkTaskExecutor.executeOnTaskThread(new StartWorkRunnable(this, startStopToken, runtimeExtras));
    }

    public WorkManagerImpl(Context context, Configuration configuration, TaskExecutor taskExecutor, boolean z) {
        this(context, configuration, taskExecutor, WorkDatabase.create(context.getApplicationContext(), taskExecutor.getSerialTaskExecutor(), z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static WorkManagerImpl getInstance(Context context) {
        WorkManagerImpl workManagerImpl;
        synchronized (sLock) {
            try {
                workManagerImpl = getInstance();
                if (workManagerImpl == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext instanceof Configuration.Provider) {
                        initialize(applicationContext, ((Configuration.Provider) applicationContext).getWorkManagerConfiguration());
                        workManagerImpl = getInstance(applicationContext);
                    } else {
                        throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return workManagerImpl;
    }

    public WorkManagerImpl(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase) {
        Context applicationContext = context.getApplicationContext();
        Logger.setLogger(new Logger.LogcatLogger(configuration.getMinimumLoggingLevel()));
        Trackers trackers = new Trackers(applicationContext, taskExecutor);
        this.mTrackers = trackers;
        List<Scheduler> listCreateSchedulers = createSchedulers(applicationContext, configuration, trackers);
        internalInit(context, configuration, taskExecutor, workDatabase, listCreateSchedulers, new Processor(context, configuration, taskExecutor, workDatabase, listCreateSchedulers));
    }

    public WorkManagerImpl(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase, List<Scheduler> list, Processor processor) {
        this(context, configuration, taskExecutor, workDatabase, list, processor, new Trackers(context.getApplicationContext(), taskExecutor));
    }

    public WorkManagerImpl(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase, List<Scheduler> list, Processor processor, Trackers trackers) {
        this.mTrackers = trackers;
        internalInit(context, configuration, taskExecutor, workDatabase, list, processor);
    }
}
