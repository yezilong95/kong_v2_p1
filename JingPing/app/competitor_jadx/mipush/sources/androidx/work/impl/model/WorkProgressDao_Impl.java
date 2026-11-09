package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.Data;
import defpackage.bu;
import defpackage.kp0;
import defpackage.ky0;
import defpackage.l21;
import defpackage.np0;
import defpackage.pv0;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WorkProgressDao_Impl implements WorkProgressDao {
    private final kp0 __db;
    private final bu __insertionAdapterOfWorkProgress;
    private final pv0 __preparedStmtOfDelete;
    private final pv0 __preparedStmtOfDeleteAll;

    public WorkProgressDao_Impl(kp0 kp0Var) {
        this.__db = kp0Var;
        this.__insertionAdapterOfWorkProgress = new bu(kp0Var) { // from class: androidx.work.impl.model.WorkProgressDao_Impl.1
            @Override // defpackage.pv0
            public String createQuery() {
                return "INSERT OR REPLACE INTO `WorkProgress` (`work_spec_id`,`progress`) VALUES (?,?)";
            }

            @Override // defpackage.bu
            public void bind(ky0 ky0Var, WorkProgress workProgress) throws Throwable {
                if (workProgress.getWorkSpecId() == null) {
                    ky0Var.h(1);
                } else {
                    ky0Var.e(1, workProgress.getWorkSpecId());
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workProgress.getProgress());
                if (byteArrayInternal == null) {
                    ky0Var.h(2);
                } else {
                    ky0Var.s(2, byteArrayInternal);
                }
            }
        };
        this.__preparedStmtOfDelete = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkProgressDao_Impl.2
            @Override // defpackage.pv0
            public String createQuery() {
                return "DELETE from WorkProgress where work_spec_id=?";
            }
        };
        this.__preparedStmtOfDeleteAll = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkProgressDao_Impl.3
            @Override // defpackage.pv0
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
        ky0 ky0VarAcquire = this.__preparedStmtOfDelete.acquire();
        if (str == null) {
            ky0VarAcquire.h(1);
        } else {
            ky0VarAcquire.e(1, str);
        }
        this.__db.beginTransaction();
        try {
            ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public Data getProgressForWorkSpecId(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT progress FROM WorkProgress WHERE work_spec_id=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            Data dataFromByteArray = null;
            if (cursorD0.moveToFirst()) {
                byte[] blob = cursorD0.isNull(0) ? null : cursorD0.getBlob(0);
                if (blob != null) {
                    dataFromByteArray = Data.fromByteArray(blob);
                }
            }
            return dataFromByteArray;
        } finally {
            cursorD0.close();
            np0VarW.x();
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
