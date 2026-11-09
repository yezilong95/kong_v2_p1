package androidx.work.impl.workers;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTrackerImpl;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.futures.SettableFuture;
import defpackage.ca0;
import defpackage.e;
import defpackage.l21;
import defpackage.p7;
import defpackage.t40;
import defpackage.zn;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ConstraintTrackingWorker extends ListenableWorker implements WorkConstraintsCallback {
    private volatile boolean areConstraintsUnmet;
    private ListenableWorker delegate;
    private final SettableFuture<ListenableWorker.Result> future;
    private final Object lock;
    private final WorkerParameters workerParameters;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        t40.n(context, "appContext");
        t40.n(workerParameters, "workerParameters");
        this.workerParameters = workerParameters;
        this.lock = new Object();
        this.future = SettableFuture.create();
    }

    private final void setupAndRunConstraintTrackingWork() {
        if (this.future.isCancelled()) {
            return;
        }
        String string = getInputData().getString(ConstraintTrackingWorkerKt.ARGUMENT_CLASS_NAME);
        Logger logger = Logger.get();
        t40.m(logger, "get()");
        if (string == null || string.length() == 0) {
            logger.error(ConstraintTrackingWorkerKt.TAG, "No worker to delegate to.");
            SettableFuture<ListenableWorker.Result> settableFuture = this.future;
            t40.m(settableFuture, "future");
            ConstraintTrackingWorkerKt.setFailed(settableFuture);
            return;
        }
        ListenableWorker listenableWorkerCreateWorkerWithDefaultFallback = getWorkerFactory().createWorkerWithDefaultFallback(getApplicationContext(), string, this.workerParameters);
        this.delegate = listenableWorkerCreateWorkerWithDefaultFallback;
        if (listenableWorkerCreateWorkerWithDefaultFallback == null) {
            logger.debug(ConstraintTrackingWorkerKt.TAG, "No worker to delegate to.");
            SettableFuture<ListenableWorker.Result> settableFuture2 = this.future;
            t40.m(settableFuture2, "future");
            ConstraintTrackingWorkerKt.setFailed(settableFuture2);
            return;
        }
        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(getApplicationContext());
        t40.m(workManagerImpl, "getInstance(applicationContext)");
        WorkSpecDao workSpecDao = workManagerImpl.getWorkDatabase().workSpecDao();
        String string2 = getId().toString();
        t40.m(string2, "id.toString()");
        WorkSpec workSpec = workSpecDao.getWorkSpec(string2);
        if (workSpec == null) {
            SettableFuture<ListenableWorker.Result> settableFuture3 = this.future;
            t40.m(settableFuture3, "future");
            ConstraintTrackingWorkerKt.setFailed(settableFuture3);
            return;
        }
        Trackers trackers = workManagerImpl.getTrackers();
        t40.m(trackers, "workManagerImpl.trackers");
        WorkConstraintsTrackerImpl workConstraintsTrackerImpl = new WorkConstraintsTrackerImpl(trackers, this);
        workConstraintsTrackerImpl.replace(l21.g0(workSpec));
        String string3 = getId().toString();
        t40.m(string3, "id.toString()");
        if (!workConstraintsTrackerImpl.areAllConstraintsMet(string3)) {
            logger.debug(ConstraintTrackingWorkerKt.TAG, "Constraints not met for delegate " + string + ". Requesting retry.");
            SettableFuture<ListenableWorker.Result> settableFuture4 = this.future;
            t40.m(settableFuture4, "future");
            ConstraintTrackingWorkerKt.setRetry(settableFuture4);
            return;
        }
        logger.debug(ConstraintTrackingWorkerKt.TAG, "Constraints met for delegate ".concat(string));
        try {
            ListenableWorker listenableWorker = this.delegate;
            t40.k(listenableWorker);
            ca0 ca0VarStartWork = listenableWorker.startWork();
            t40.m(ca0VarStartWork, "delegate!!.startWork()");
            ca0VarStartWork.addListener(new p7(this, 4, ca0VarStartWork), getBackgroundExecutor());
        } catch (Throwable th) {
            logger.debug(ConstraintTrackingWorkerKt.TAG, zn.n("Delegated worker ", string, " threw exception in startWork."), th);
            synchronized (this.lock) {
                try {
                    if (!this.areConstraintsUnmet) {
                        SettableFuture<ListenableWorker.Result> settableFuture5 = this.future;
                        t40.m(settableFuture5, "future");
                        ConstraintTrackingWorkerKt.setFailed(settableFuture5);
                    } else {
                        logger.debug(ConstraintTrackingWorkerKt.TAG, "Constraints were unmet, Retrying.");
                        SettableFuture<ListenableWorker.Result> settableFuture6 = this.future;
                        t40.m(settableFuture6, "future");
                        ConstraintTrackingWorkerKt.setRetry(settableFuture6);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAndRunConstraintTrackingWork$lambda$2(ConstraintTrackingWorker constraintTrackingWorker, ca0 ca0Var) {
        t40.n(constraintTrackingWorker, "this$0");
        t40.n(ca0Var, "$innerFuture");
        synchronized (constraintTrackingWorker.lock) {
            try {
                if (constraintTrackingWorker.areConstraintsUnmet) {
                    SettableFuture<ListenableWorker.Result> settableFuture = constraintTrackingWorker.future;
                    t40.m(settableFuture, "future");
                    ConstraintTrackingWorkerKt.setRetry(settableFuture);
                } else {
                    constraintTrackingWorker.future.setFuture(ca0Var);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startWork$lambda$0(ConstraintTrackingWorker constraintTrackingWorker) {
        t40.n(constraintTrackingWorker, "this$0");
        constraintTrackingWorker.setupAndRunConstraintTrackingWork();
    }

    public final ListenableWorker getDelegate() {
        return this.delegate;
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(List<WorkSpec> list) {
        t40.n(list, "workSpecs");
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(List<WorkSpec> list) {
        t40.n(list, "workSpecs");
        Logger.get().debug(ConstraintTrackingWorkerKt.TAG, "Constraints changed for " + list);
        synchronized (this.lock) {
            this.areConstraintsUnmet = true;
        }
    }

    @Override // androidx.work.ListenableWorker
    public void onStopped() {
        super.onStopped();
        ListenableWorker listenableWorker = this.delegate;
        if (listenableWorker == null || listenableWorker.isStopped()) {
            return;
        }
        listenableWorker.stop();
    }

    @Override // androidx.work.ListenableWorker
    public ca0 startWork() {
        getBackgroundExecutor().execute(new e(11, this));
        SettableFuture<ListenableWorker.Result> settableFuture = this.future;
        t40.m(settableFuture, "future");
        return settableFuture;
    }
}
