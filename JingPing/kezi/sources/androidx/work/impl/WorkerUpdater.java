package androidx.work.impl;

import androidx.work.Configuration;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.WorkerUpdater;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.utils.EnqueueRunnable;
import androidx.work.impl.utils.EnqueueUtilsKt;
import androidx.work.impl.utils.futures.SettableFuture;
import defpackage.bq0;
import defpackage.hk;
import defpackage.ou;
import defpackage.oy;
import defpackage.pu;
import defpackage.x20;
import defpackage.xi;
import defpackage.xm;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class WorkerUpdater {
    public static final Operation enqueueUniquelyNamedPeriodic(final WorkManagerImpl workManagerImpl, final String str, final WorkRequest workRequest) {
        oy.j(workManagerImpl, "<this>");
        oy.j(str, "name");
        oy.j(workRequest, "workRequest");
        final OperationImpl operationImpl = new OperationImpl();
        final ou ouVar = new ou() { // from class: androidx.work.impl.WorkerUpdater$enqueueUniquelyNamedPeriodic$enqueueNew$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // defpackage.ou
            public /* bridge */ /* synthetic */ Object invoke() {
                m0invoke();
                return bq0.a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m0invoke() {
                new EnqueueRunnable(new WorkContinuationImpl(workManagerImpl, str, ExistingWorkPolicy.KEEP, xi.G(workRequest)), operationImpl).run();
            }
        };
        workManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor().execute(new Runnable() { // from class: hv0
            @Override // java.lang.Runnable
            public final void run() {
                WorkerUpdater.enqueueUniquelyNamedPeriodic$lambda$3(workManagerImpl, str, operationImpl, ouVar, workRequest);
            }
        });
        return operationImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enqueueUniquelyNamedPeriodic$lambda$3(WorkManagerImpl workManagerImpl, String str, OperationImpl operationImpl, ou ouVar, WorkRequest workRequest) {
        oy.j(workManagerImpl, "$this_enqueueUniquelyNamedPeriodic");
        oy.j(str, "$name");
        oy.j(operationImpl, "$operation");
        oy.j(ouVar, "$enqueueNew");
        oy.j(workRequest, "$workRequest");
        WorkSpecDao workSpecDao = workManagerImpl.getWorkDatabase().workSpecDao();
        List<WorkSpec.IdAndState> workSpecIdAndStatesForName = workSpecDao.getWorkSpecIdAndStatesForName(str);
        if (workSpecIdAndStatesForName.size() > 1) {
            failWorkTypeChanged(operationImpl, "Can't apply UPDATE policy to the chains of work.");
            return;
        }
        WorkSpec.IdAndState idAndState = workSpecIdAndStatesForName.isEmpty() ? null : workSpecIdAndStatesForName.get(0);
        if (idAndState == null) {
            ouVar.invoke();
            return;
        }
        WorkSpec workSpec = workSpecDao.getWorkSpec(idAndState.id);
        if (workSpec == null) {
            operationImpl.markState(new Operation.State.FAILURE(new IllegalStateException("WorkSpec with " + idAndState.id + ", that matches a name \"" + str + "\", wasn't found")));
            return;
        }
        if (!workSpec.isPeriodic()) {
            failWorkTypeChanged(operationImpl, "Can't update OneTimeWorker to Periodic Worker. Update operation must preserve worker's type.");
            return;
        }
        if (idAndState.state == WorkInfo.State.CANCELLED) {
            workSpecDao.delete(idAndState.id);
            ouVar.invoke();
            return;
        }
        WorkSpec workSpecCopy$default = WorkSpec.copy$default(workRequest.getWorkSpec(), idAndState.id, null, null, null, null, null, 0L, 0L, 0L, null, 0, null, 0L, 0L, 0L, 0L, false, null, 0, 0, 1048574, null);
        try {
            Processor processor = workManagerImpl.getProcessor();
            oy.i(processor, "processor");
            WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
            oy.i(workDatabase, "workDatabase");
            Configuration configuration = workManagerImpl.getConfiguration();
            oy.i(configuration, "configuration");
            List<Scheduler> schedulers = workManagerImpl.getSchedulers();
            oy.i(schedulers, "schedulers");
            updateWorkImpl(processor, workDatabase, configuration, schedulers, workSpecCopy$default, workRequest.getTags());
            operationImpl.markState(Operation.SUCCESS);
        } catch (Throwable th) {
            operationImpl.markState(new Operation.State.FAILURE(th));
        }
    }

    private static final void failWorkTypeChanged(OperationImpl operationImpl, String str) {
        operationImpl.markState(new Operation.State.FAILURE(new UnsupportedOperationException(str)));
    }

    private static final WorkManager.UpdateResult updateWorkImpl(Processor processor, final WorkDatabase workDatabase, Configuration configuration, final List<? extends Scheduler> list, final WorkSpec workSpec, final Set<String> set) {
        final String str = workSpec.id;
        final WorkSpec workSpec2 = workDatabase.workSpecDao().getWorkSpec(str);
        if (workSpec2 == null) {
            throw new IllegalArgumentException(hk.j("Worker with ", str, " doesn't exist"));
        }
        if (workSpec2.state.isFinished()) {
            return WorkManager.UpdateResult.NOT_APPLIED;
        }
        if (workSpec2.isPeriodic() ^ workSpec.isPeriodic()) {
            WorkerUpdater$updateWorkImpl$type$1 workerUpdater$updateWorkImpl$type$1 = new pu() { // from class: androidx.work.impl.WorkerUpdater$updateWorkImpl$type$1
                @Override // defpackage.pu
                public final String invoke(WorkSpec workSpec3) {
                    oy.j(workSpec3, "spec");
                    return workSpec3.isPeriodic() ? "Periodic" : "OneTime";
                }
            };
            StringBuilder sb = new StringBuilder("Can't update ");
            sb.append((String) workerUpdater$updateWorkImpl$type$1.invoke((Object) workSpec2));
            sb.append(" Worker to ");
            throw new UnsupportedOperationException(hk.k(sb, (String) workerUpdater$updateWorkImpl$type$1.invoke((Object) workSpec), " Worker. Update operation must preserve worker's type."));
        }
        final boolean zIsEnqueued = processor.isEnqueued(str);
        if (!zIsEnqueued) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((Scheduler) it.next()).cancel(str);
            }
        }
        workDatabase.runInTransaction(new Runnable() { // from class: gv0
            @Override // java.lang.Runnable
            public final void run() {
                WorkerUpdater.updateWorkImpl$lambda$1(workDatabase, workSpec, workSpec2, list, str, set, zIsEnqueued);
            }
        });
        if (!zIsEnqueued) {
            Schedulers.schedule(configuration, workDatabase, list);
        }
        return zIsEnqueued ? WorkManager.UpdateResult.APPLIED_FOR_NEXT_RUN : WorkManager.UpdateResult.APPLIED_IMMEDIATELY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWorkImpl$lambda$1(WorkDatabase workDatabase, WorkSpec workSpec, WorkSpec workSpec2, List list, String str, Set set, boolean z) {
        oy.j(workDatabase, "$workDatabase");
        oy.j(workSpec, "$newWorkSpec");
        oy.j(workSpec2, "$oldWorkSpec");
        oy.j(list, "$schedulers");
        oy.j(str, "$workSpecId");
        oy.j(set, "$tags");
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkTagDao workTagDao = workDatabase.workTagDao();
        workSpecDao.updateWorkSpec(EnqueueUtilsKt.wrapInConstraintTrackingWorkerIfNeeded(list, WorkSpec.copy$default(workSpec, null, workSpec2.state, null, null, null, null, 0L, 0L, 0L, null, workSpec2.runAttemptCount, null, 0L, workSpec2.lastEnqueueTime, 0L, 0L, false, null, 0, workSpec2.getGeneration() + 1, 515069, null)));
        workTagDao.deleteByWorkSpecId(str);
        workTagDao.insertTags(str, set);
        if (z) {
            return;
        }
        workSpecDao.markWorkSpecScheduled(str, -1L);
        workDatabase.workProgressDao().delete(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWorkImpl$lambda$2(SettableFuture settableFuture, WorkManagerImpl workManagerImpl, WorkRequest workRequest) {
        oy.j(workManagerImpl, "$this_updateWorkImpl");
        oy.j(workRequest, "$workRequest");
        if (settableFuture.isCancelled()) {
            return;
        }
        try {
            Processor processor = workManagerImpl.getProcessor();
            oy.i(processor, "processor");
            WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
            oy.i(workDatabase, "workDatabase");
            Configuration configuration = workManagerImpl.getConfiguration();
            oy.i(configuration, "configuration");
            List<Scheduler> schedulers = workManagerImpl.getSchedulers();
            oy.i(schedulers, "schedulers");
            settableFuture.set(updateWorkImpl(processor, workDatabase, configuration, schedulers, workRequest.getWorkSpec(), workRequest.getTags()));
        } catch (Throwable th) {
            settableFuture.setException(th);
        }
    }

    public static final x20 updateWorkImpl(WorkManagerImpl workManagerImpl, WorkRequest workRequest) {
        oy.j(workManagerImpl, "<this>");
        oy.j(workRequest, "workRequest");
        SettableFuture settableFutureCreate = SettableFuture.create();
        workManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor().execute(new xm(settableFutureCreate, workManagerImpl, workRequest, 3));
        oy.i(settableFutureCreate, "future");
        return settableFutureCreate;
    }
}
