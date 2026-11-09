package androidx.work.impl.utils;

import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpecDao;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class CancelWorkRunnable implements Runnable {
    private final OperationImpl mOperation = new OperationImpl();

    public static CancelWorkRunnable forAll(final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() { // from class: androidx.work.impl.utils.CancelWorkRunnable.4
            @Override // androidx.work.impl.utils.CancelWorkRunnable
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    Iterator<String> it = workDatabase.workSpecDao().getAllUnfinishedWork().iterator();
                    while (it.hasNext()) {
                        cancel(workManagerImpl, it.next());
                    }
                    new PreferenceUtils(workManagerImpl.getWorkDatabase()).setLastCancelAllTimeMillis(System.currentTimeMillis());
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static CancelWorkRunnable forId(final UUID uuid, final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() { // from class: androidx.work.impl.utils.CancelWorkRunnable.1
            @Override // androidx.work.impl.utils.CancelWorkRunnable
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    cancel(workManagerImpl, uuid.toString());
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    reschedulePendingWorkers(workManagerImpl);
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static CancelWorkRunnable forName(final String str, final WorkManagerImpl workManagerImpl, final boolean z) {
        return new CancelWorkRunnable() { // from class: androidx.work.impl.utils.CancelWorkRunnable.3
            @Override // androidx.work.impl.utils.CancelWorkRunnable
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    Iterator<String> it = workDatabase.workSpecDao().getUnfinishedWorkWithName(str).iterator();
                    while (it.hasNext()) {
                        cancel(workManagerImpl, it.next());
                    }
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    if (z) {
                        reschedulePendingWorkers(workManagerImpl);
                    }
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static CancelWorkRunnable forTag(final String str, final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() { // from class: androidx.work.impl.utils.CancelWorkRunnable.2
            @Override // androidx.work.impl.utils.CancelWorkRunnable
            public void runInternal() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    Iterator<String> it = workDatabase.workSpecDao().getUnfinishedWorkWithTag(str).iterator();
                    while (it.hasNext()) {
                        cancel(workManagerImpl, it.next());
                    }
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    reschedulePendingWorkers(workManagerImpl);
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    private void iterativelyCancelWorkAndDependents(WorkDatabase workDatabase, String str) {
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        DependencyDao dependencyDao = workDatabase.dependencyDao();
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        while (!linkedList.isEmpty()) {
            String str2 = (String) linkedList.remove();
            WorkInfo.State state = workSpecDao.getState(str2);
            if (state != WorkInfo.State.SUCCEEDED && state != WorkInfo.State.FAILED) {
                workSpecDao.setState(WorkInfo.State.CANCELLED, str2);
            }
            linkedList.addAll(dependencyDao.getDependentWorkIds(str2));
        }
    }

    public void cancel(WorkManagerImpl workManagerImpl, String str) {
        iterativelyCancelWorkAndDependents(workManagerImpl.getWorkDatabase(), str);
        workManagerImpl.getProcessor().stopAndCancelWork(str);
        Iterator<Scheduler> it = workManagerImpl.getSchedulers().iterator();
        while (it.hasNext()) {
            it.next().cancel(str);
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public void reschedulePendingWorkers(WorkManagerImpl workManagerImpl) {
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            runInternal();
            this.mOperation.markState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.mOperation.markState(new Operation.State.FAILURE(th));
        }
    }

    public abstract void runInternal();
}
