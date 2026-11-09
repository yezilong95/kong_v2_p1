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
import defpackage.a10;
import defpackage.b10;
import defpackage.b91;
import defpackage.ca0;
import defpackage.j;
import defpackage.l21;
import defpackage.t40;
import defpackage.y21;
import defpackage.zn;
import io.nats.client.support.ApiConstants;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class WorkerUpdater {
    public static final Operation enqueueUniquelyNamedPeriodic(final WorkManagerImpl workManagerImpl, final String str, final WorkRequest workRequest) {
        t40.n(workManagerImpl, "<this>");
        t40.n(str, ApiConstants.NAME);
        t40.n(workRequest, "workRequest");
        final OperationImpl operationImpl = new OperationImpl();
        workManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor().execute(new b91(workManagerImpl, str, operationImpl, new a10() { // from class: androidx.work.impl.WorkerUpdater$enqueueUniquelyNamedPeriodic$enqueueNew$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // defpackage.a10
            public /* bridge */ /* synthetic */ Object invoke() {
                m0invoke();
                return y21.a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m0invoke() {
                new EnqueueRunnable(new WorkContinuationImpl(workManagerImpl, str, ExistingWorkPolicy.KEEP, l21.g0(workRequest)), operationImpl).run();
            }
        }, workRequest));
        return operationImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enqueueUniquelyNamedPeriodic$lambda$3(WorkManagerImpl workManagerImpl, String str, OperationImpl operationImpl, a10 a10Var, WorkRequest workRequest) {
        t40.n(workManagerImpl, "$this_enqueueUniquelyNamedPeriodic");
        t40.n(str, "$name");
        t40.n(operationImpl, "$operation");
        t40.n(a10Var, "$enqueueNew");
        t40.n(workRequest, "$workRequest");
        WorkSpecDao workSpecDao = workManagerImpl.getWorkDatabase().workSpecDao();
        List<WorkSpec.IdAndState> workSpecIdAndStatesForName = workSpecDao.getWorkSpecIdAndStatesForName(str);
        if (workSpecIdAndStatesForName.size() > 1) {
            failWorkTypeChanged(operationImpl, "Can't apply UPDATE policy to the chains of work.");
            return;
        }
        WorkSpec.IdAndState idAndState = workSpecIdAndStatesForName.isEmpty() ? null : workSpecIdAndStatesForName.get(0);
        if (idAndState == null) {
            a10Var.invoke();
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
            a10Var.invoke();
            return;
        }
        WorkSpec workSpecCopy$default = WorkSpec.copy$default(workRequest.getWorkSpec(), idAndState.id, null, null, null, null, null, 0L, 0L, 0L, null, 0, null, 0L, 0L, 0L, 0L, false, null, 0, 0, 1048574, null);
        try {
            Processor processor = workManagerImpl.getProcessor();
            t40.m(processor, "processor");
            WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
            t40.m(workDatabase, "workDatabase");
            Configuration configuration = workManagerImpl.getConfiguration();
            t40.m(configuration, "configuration");
            List<Scheduler> schedulers = workManagerImpl.getSchedulers();
            t40.m(schedulers, "schedulers");
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
            throw new IllegalArgumentException(zn.n("Worker with ", str, " doesn't exist"));
        }
        if (workSpec2.state.isFinished()) {
            return WorkManager.UpdateResult.NOT_APPLIED;
        }
        if (workSpec2.isPeriodic() ^ workSpec.isPeriodic()) {
            WorkerUpdater$updateWorkImpl$type$1 workerUpdater$updateWorkImpl$type$1 = new b10() { // from class: androidx.work.impl.WorkerUpdater$updateWorkImpl$type$1
                @Override // defpackage.b10
                public final String invoke(WorkSpec workSpec3) {
                    t40.n(workSpec3, "spec");
                    return workSpec3.isPeriodic() ? "Periodic" : "OneTime";
                }
            };
            StringBuilder sb = new StringBuilder("Can't update ");
            sb.append((String) workerUpdater$updateWorkImpl$type$1.invoke((Object) workSpec2));
            sb.append(" Worker to ");
            throw new UnsupportedOperationException(zn.o(sb, (String) workerUpdater$updateWorkImpl$type$1.invoke((Object) workSpec), " Worker. Update operation must preserve worker's type."));
        }
        final boolean zIsEnqueued = processor.isEnqueued(str);
        if (!zIsEnqueued) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((Scheduler) it.next()).cancel(str);
            }
        }
        workDatabase.runInTransaction(new Runnable() { // from class: f91
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
        t40.n(workDatabase, "$workDatabase");
        t40.n(workSpec, "$newWorkSpec");
        t40.n(workSpec2, "$oldWorkSpec");
        t40.n(list, "$schedulers");
        t40.n(str, "$workSpecId");
        t40.n(set, "$tags");
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
        t40.n(workManagerImpl, "$this_updateWorkImpl");
        t40.n(workRequest, "$workRequest");
        if (settableFuture.isCancelled()) {
            return;
        }
        try {
            Processor processor = workManagerImpl.getProcessor();
            t40.m(processor, "processor");
            WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
            t40.m(workDatabase, "workDatabase");
            Configuration configuration = workManagerImpl.getConfiguration();
            t40.m(configuration, "configuration");
            List<Scheduler> schedulers = workManagerImpl.getSchedulers();
            t40.m(schedulers, "schedulers");
            settableFuture.set(updateWorkImpl(processor, workDatabase, configuration, schedulers, workRequest.getWorkSpec(), workRequest.getTags()));
        } catch (Throwable th) {
            settableFuture.setException(th);
        }
    }

    public static final ca0 updateWorkImpl(WorkManagerImpl workManagerImpl, WorkRequest workRequest) {
        t40.n(workManagerImpl, "<this>");
        t40.n(workRequest, "workRequest");
        SettableFuture settableFutureCreate = SettableFuture.create();
        workManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor().execute(new j(settableFutureCreate, workManagerImpl, workRequest, 8));
        t40.m(settableFutureCreate, "future");
        return settableFutureCreate;
    }
}
