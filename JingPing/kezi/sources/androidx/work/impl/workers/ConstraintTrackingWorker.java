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
import defpackage.hk;
import defpackage.l6;
import defpackage.oy;
import defpackage.x20;
import defpackage.xi;
import defpackage.z1;
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
        oy.j(context, "appContext");
        oy.j(workerParameters, "workerParameters");
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
        oy.i(logger, "get()");
        if (string == null || string.length() == 0) {
            logger.error(ConstraintTrackingWorkerKt.TAG, "No worker to delegate to.");
            SettableFuture<ListenableWorker.Result> settableFuture = this.future;
            oy.i(settableFuture, "future");
            ConstraintTrackingWorkerKt.setFailed(settableFuture);
            return;
        }
        ListenableWorker listenableWorkerCreateWorkerWithDefaultFallback = getWorkerFactory().createWorkerWithDefaultFallback(getApplicationContext(), string, this.workerParameters);
        this.delegate = listenableWorkerCreateWorkerWithDefaultFallback;
        if (listenableWorkerCreateWorkerWithDefaultFallback == null) {
            logger.debug(ConstraintTrackingWorkerKt.TAG, "No worker to delegate to.");
            SettableFuture<ListenableWorker.Result> settableFuture2 = this.future;
            oy.i(settableFuture2, "future");
            ConstraintTrackingWorkerKt.setFailed(settableFuture2);
            return;
        }
        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(getApplicationContext());
        oy.i(workManagerImpl, "getInstance(applicationContext)");
        WorkSpecDao workSpecDao = workManagerImpl.getWorkDatabase().workSpecDao();
        String string2 = getId().toString();
        oy.i(string2, "id.toString()");
        WorkSpec workSpec = workSpecDao.getWorkSpec(string2);
        if (workSpec == null) {
            SettableFuture<ListenableWorker.Result> settableFuture3 = this.future;
            oy.i(settableFuture3, "future");
            ConstraintTrackingWorkerKt.setFailed(settableFuture3);
            return;
        }
        Trackers trackers = workManagerImpl.getTrackers();
        oy.i(trackers, "workManagerImpl.trackers");
        WorkConstraintsTrackerImpl workConstraintsTrackerImpl = new WorkConstraintsTrackerImpl(trackers, this);
        workConstraintsTrackerImpl.replace(xi.G(workSpec));
        String string3 = getId().toString();
        oy.i(string3, "id.toString()");
        if (!workConstraintsTrackerImpl.areAllConstraintsMet(string3)) {
            logger.debug(ConstraintTrackingWorkerKt.TAG, "Constraints not met for delegate " + string + ". Requesting retry.");
            SettableFuture<ListenableWorker.Result> settableFuture4 = this.future;
            oy.i(settableFuture4, "future");
            ConstraintTrackingWorkerKt.setRetry(settableFuture4);
            return;
        }
        logger.debug(ConstraintTrackingWorkerKt.TAG, "Constraints met for delegate ".concat(string));
        try {
            ListenableWorker listenableWorker = this.delegate;
            oy.g(listenableWorker);
            x20 x20VarStartWork = listenableWorker.startWork();
            oy.i(x20VarStartWork, "delegate!!.startWork()");
            x20VarStartWork.addListener(new l6(this, 2, x20VarStartWork), getBackgroundExecutor());
        } catch (Throwable th) {
            logger.debug(ConstraintTrackingWorkerKt.TAG, hk.j("Delegated worker ", string, " threw exception in startWork."), th);
            synchronized (this.lock) {
                try {
                    if (!this.areConstraintsUnmet) {
                        SettableFuture<ListenableWorker.Result> settableFuture5 = this.future;
                        oy.i(settableFuture5, "future");
                        ConstraintTrackingWorkerKt.setFailed(settableFuture5);
                    } else {
                        logger.debug(ConstraintTrackingWorkerKt.TAG, "Constraints were unmet, Retrying.");
                        SettableFuture<ListenableWorker.Result> settableFuture6 = this.future;
                        oy.i(settableFuture6, "future");
                        ConstraintTrackingWorkerKt.setRetry(settableFuture6);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAndRunConstraintTrackingWork$lambda$2(ConstraintTrackingWorker constraintTrackingWorker, x20 x20Var) {
        oy.j(constraintTrackingWorker, "this$0");
        oy.j(x20Var, "$innerFuture");
        synchronized (constraintTrackingWorker.lock) {
            try {
                if (constraintTrackingWorker.areConstraintsUnmet) {
                    SettableFuture<ListenableWorker.Result> settableFuture = constraintTrackingWorker.future;
                    oy.i(settableFuture, "future");
                    ConstraintTrackingWorkerKt.setRetry(settableFuture);
                } else {
                    constraintTrackingWorker.future.setFuture(x20Var);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startWork$lambda$0(ConstraintTrackingWorker constraintTrackingWorker) {
        oy.j(constraintTrackingWorker, "this$0");
        constraintTrackingWorker.setupAndRunConstraintTrackingWork();
    }

    public final ListenableWorker getDelegate() {
        return this.delegate;
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(List<WorkSpec> list) {
        oy.j(list, "workSpecs");
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(List<WorkSpec> list) {
        oy.j(list, "workSpecs");
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
    public x20 startWork() {
        getBackgroundExecutor().execute(new z1(5, this));
        SettableFuture<ListenableWorker.Result> settableFuture = this.future;
        oy.i(settableFuture, "future");
        return settableFuture;
    }
}
