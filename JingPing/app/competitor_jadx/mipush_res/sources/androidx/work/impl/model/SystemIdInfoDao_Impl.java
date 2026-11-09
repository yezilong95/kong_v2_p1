package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.impl.model.SystemIdInfoDao;
import defpackage.bu;
import defpackage.kp0;
import defpackage.ky0;
import defpackage.l21;
import defpackage.np0;
import defpackage.pv0;
import defpackage.qm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SystemIdInfoDao_Impl implements SystemIdInfoDao {
    private final kp0 __db;
    private final bu __insertionAdapterOfSystemIdInfo;
    private final pv0 __preparedStmtOfRemoveSystemIdInfo;
    private final pv0 __preparedStmtOfRemoveSystemIdInfo_1;

    public SystemIdInfoDao_Impl(kp0 kp0Var) {
        this.__db = kp0Var;
        this.__insertionAdapterOfSystemIdInfo = new bu(kp0Var) { // from class: androidx.work.impl.model.SystemIdInfoDao_Impl.1
            @Override // defpackage.pv0
            public String createQuery() {
                return "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`generation`,`system_id`) VALUES (?,?,?)";
            }

            @Override // defpackage.bu
            public void bind(ky0 ky0Var, SystemIdInfo systemIdInfo) {
                String str = systemIdInfo.workSpecId;
                if (str == null) {
                    ky0Var.h(1);
                } else {
                    ky0Var.e(1, str);
                }
                ky0Var.n(2, systemIdInfo.getGeneration());
                ky0Var.n(3, systemIdInfo.systemId);
            }
        };
        this.__preparedStmtOfRemoveSystemIdInfo = new pv0(kp0Var) { // from class: androidx.work.impl.model.SystemIdInfoDao_Impl.2
            @Override // defpackage.pv0
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=? AND generation=?";
            }
        };
        this.__preparedStmtOfRemoveSystemIdInfo_1 = new pv0(kp0Var) { // from class: androidx.work.impl.model.SystemIdInfoDao_Impl.3
            @Override // defpackage.pv0
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=?";
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public SystemIdInfo getSystemIdInfo(String str, int i) throws IOException {
        np0 np0VarW = np0.w(2, "SELECT * FROM SystemIdInfo WHERE work_spec_id=? AND generation=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        np0VarW.n(2, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            int iU = qm.u(cursorD0, "work_spec_id");
            int iU2 = qm.u(cursorD0, "generation");
            int iU3 = qm.u(cursorD0, "system_id");
            SystemIdInfo systemIdInfo = null;
            String string = null;
            if (cursorD0.moveToFirst()) {
                if (!cursorD0.isNull(iU)) {
                    string = cursorD0.getString(iU);
                }
                systemIdInfo = new SystemIdInfo(string, cursorD0.getInt(iU2), cursorD0.getInt(iU3));
            }
            return systemIdInfo;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public List<String> getWorkSpecIds() throws IOException {
        np0 np0VarW = np0.w(0, "SELECT DISTINCT work_spec_id FROM SystemIdInfo");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                arrayList.add(cursorD0.isNull(0) ? null : cursorD0.getString(0));
            }
            return arrayList;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public void insertSystemIdInfo(SystemIdInfo systemIdInfo) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSystemIdInfo.insert(systemIdInfo);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public void removeSystemIdInfo(String str, int i) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfRemoveSystemIdInfo.acquire();
        if (str == null) {
            ky0VarAcquire.h(1);
        } else {
            ky0VarAcquire.e(1, str);
        }
        ky0VarAcquire.n(2, i);
        this.__db.beginTransaction();
        try {
            ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfRemoveSystemIdInfo.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public void removeSystemIdInfo(String str) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfRemoveSystemIdInfo_1.acquire();
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
            this.__preparedStmtOfRemoveSystemIdInfo_1.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public SystemIdInfo getSystemIdInfo(WorkGenerationalId workGenerationalId) {
        return SystemIdInfoDao.DefaultImpls.getSystemIdInfo(this, workGenerationalId);
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public void removeSystemIdInfo(WorkGenerationalId workGenerationalId) {
        SystemIdInfoDao.DefaultImpls.removeSystemIdInfo(this, workGenerationalId);
    }
}
