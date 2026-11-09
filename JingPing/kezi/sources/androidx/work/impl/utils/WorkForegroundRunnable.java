package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import defpackage.l6;
import defpackage.x20;

/* JADX INFO: loaded from: classes.dex */
public class WorkForegroundRunnable implements Runnable {
    static final String TAG = Logger.tagWithPrefix("WorkForegroundRunnable");
    final Context mContext;
    final ForegroundUpdater mForegroundUpdater;
    final SettableFuture<Void> mFuture = SettableFuture.create();
    final TaskExecutor mTaskExecutor;
    final WorkSpec mWorkSpec;
    final ListenableWorker mWorker;

    @SuppressLint({"LambdaLast"})
    public WorkForegroundRunnable(Context context, WorkSpec workSpec, ListenableWorker listenableWorker, ForegroundUpdater foregroundUpdater, TaskExecutor taskExecutor) {
        this.mContext = context;
        this.mWorkSpec = workSpec;
        this.mWorker = listenableWorker;
        this.mForegroundUpdater = foregroundUpdater;
        this.mTaskExecutor = taskExecutor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$run$0(SettableFuture settableFuture) {
        if (this.mFuture.isCancelled()) {
            settableFuture.cancel(true);
        } else {
            settableFuture.setFuture(this.mWorker.getForegroundInfoAsync());
        }
    }

    public x20 getFuture() {
        return this.mFuture;
    }

    @Override // java.lang.Runnable
    @SuppressLint({"UnsafeExperimentalUsageError"})
    public void run() {
        if (!this.mWorkSpec.expedited || Build.VERSION.SDK_INT >= 31) {
            this.mFuture.set(null);
            return;
        }
        final SettableFuture settableFutureCreate = SettableFuture.create();
        this.mTaskExecutor.getMainThreadExecutor().execute(new l6(this, 14, settableFutureCreate));
        settableFutureCreate.addListener(new Runnable() { // from class: androidx.work.impl.utils.WorkForegroundRunnable.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                if (WorkForegroundRunnable.this.mFuture.isCancelled()) {
                    return;
                }
                try {
                    ForegroundInfo foregroundInfo = (ForegroundInfo) settableFutureCreate.get();
                    if (foregroundInfo == null) {
                        throw new IllegalStateException("Worker was marked important (" + WorkForegroundRunnable.this.mWorkSpec.workerClassName + ") but did not provide ForegroundInfo");
                    }
                    Logger.get().debug(WorkForegroundRunnable.TAG, "Updating notification for " + WorkForegroundRunnable.this.mWorkSpec.workerClassName);
                    WorkForegroundRunnable workForegroundRunnable = WorkForegroundRunnable.this;
                    workForegroundRunnable.mFuture.setFuture(workForegroundRunnable.mForegroundUpdater.setForegroundAsync(workForegroundRunnable.mContext, workForegroundRunnable.mWorker.getId(), foregroundInfo));
                } catch (Throwable th) {
                    WorkForegroundRunnable.this.mFuture.setException(th);
                }
            }
        }, this.mTaskExecutor.getMainThreadExecutor());
    }
}
