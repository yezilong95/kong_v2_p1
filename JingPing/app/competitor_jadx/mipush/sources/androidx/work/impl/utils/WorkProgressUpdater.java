package androidx.work.impl.utils;

import android.content.Context;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.ProgressUpdater;
import androidx.work.WorkInfo;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkProgress;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import defpackage.ca0;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class WorkProgressUpdater implements ProgressUpdater {
    static final String TAG = Logger.tagWithPrefix("WorkProgressUpdater");
    final TaskExecutor mTaskExecutor;
    final WorkDatabase mWorkDatabase;

    public WorkProgressUpdater(WorkDatabase workDatabase, TaskExecutor taskExecutor) {
        this.mWorkDatabase = workDatabase;
        this.mTaskExecutor = taskExecutor;
    }

    @Override // androidx.work.ProgressUpdater
    public ca0 updateProgress(Context context, final UUID uuid, final Data data) {
        final SettableFuture settableFutureCreate = SettableFuture.create();
        this.mTaskExecutor.executeOnTaskThread(new Runnable() { // from class: androidx.work.impl.utils.WorkProgressUpdater.1
            @Override // java.lang.Runnable
            public void run() {
                String string = uuid.toString();
                Logger logger = Logger.get();
                String str = WorkProgressUpdater.TAG;
                logger.debug(str, "Updating progress for " + uuid + " (" + data + ")");
                WorkProgressUpdater.this.mWorkDatabase.beginTransaction();
                try {
                    WorkSpec workSpec = WorkProgressUpdater.this.mWorkDatabase.workSpecDao().getWorkSpec(string);
                    if (workSpec == null) {
                        throw new IllegalStateException("Calls to setProgressAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
                    }
                    if (workSpec.state == WorkInfo.State.RUNNING) {
                        WorkProgressUpdater.this.mWorkDatabase.workProgressDao().insert(new WorkProgress(string, data));
                    } else {
                        Logger.get().warning(str, "Ignoring setProgressAsync(...). WorkSpec (" + string + ") is not in a RUNNING state.");
                    }
                    settableFutureCreate.set(null);
                    WorkProgressUpdater.this.mWorkDatabase.setTransactionSuccessful();
                } catch (Throwable th) {
                    try {
                        Logger.get().error(WorkProgressUpdater.TAG, "Error updating Worker progress", th);
                        settableFutureCreate.setException(th);
                    } finally {
                        WorkProgressUpdater.this.mWorkDatabase.endTransaction();
                    }
                }
            }
        });
        return settableFutureCreate;
    }
}
