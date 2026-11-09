package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.Data;
import defpackage.aj0;
import defpackage.io;
import defpackage.k9;
import defpackage.pf0;
import defpackage.sf0;
import defpackage.wl0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WorkProgressDao_Impl implements WorkProgressDao {
    private final pf0 __db;
    private final io __insertionAdapterOfWorkProgress;
    private final aj0 __preparedStmtOfDelete;
    private final aj0 __preparedStmtOfDeleteAll;

    public WorkProgressDao_Impl(pf0 pf0Var) {
        this.__db = pf0Var;
        this.__insertionAdapterOfWorkProgress = new io(pf0Var) { // from class: androidx.work.impl.model.WorkProgressDao_Impl.1
            @Override // defpackage.aj0
            public String createQuery() {
                return "INSERT OR REPLACE INTO `WorkProgress` (`work_spec_id`,`progress`) VALUES (?,?)";
            }

            @Override // defpackage.io
            public void bind(wl0 wl0Var, WorkProgress workProgress) throws Throwable {
                if (workProgress.getWorkSpecId() == null) {
                    wl0Var.r(1);
                } else {
                    wl0Var.j(1, workProgress.getWorkSpecId());
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workProgress.getProgress());
                if (byteArrayInternal == null) {
                    wl0Var.r(2);
                } else {
                    wl0Var.B(byteArrayInternal, 2);
                }
            }
        };
        this.__preparedStmtOfDelete = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkProgressDao_Impl.2
            @Override // defpackage.aj0
            public String createQuery() {
                return "DELETE from WorkProgress where work_spec_id=?";
            }
        };
        this.__preparedStmtOfDeleteAll = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkProgressDao_Impl.3
            @Override // defpackage.aj0
            public String createQuery() {
                return "DELETE FROM WorkProgress";
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void delete(String str) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfDelete.acquire();
        if (str == null) {
            wl0VarAcquire.r(1);
        } else {
            wl0VarAcquire.j(1, str);
        }
        this.__db.beginTransaction();
        try {
            wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public Data getProgressForWorkSpecId(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT progress FROM WorkProgress WHERE work_spec_id=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            Data dataFromByteArray = null;
            if (cursorB.moveToFirst()) {
                byte[] blob = cursorB.isNull(0) ? null : cursorB.getBlob(0);
                if (blob != null) {
                    dataFromByteArray = Data.fromByteArray(blob);
                }
            }
            return dataFromByteArray;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void insert(WorkProgress workProgress) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkProgress.insert(workProgress);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
