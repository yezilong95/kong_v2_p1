package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.impl.model.SystemIdInfoDao;
import defpackage.aj0;
import defpackage.io;
import defpackage.jq0;
import defpackage.k9;
import defpackage.pf0;
import defpackage.sf0;
import defpackage.wl0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SystemIdInfoDao_Impl implements SystemIdInfoDao {
    private final pf0 __db;
    private final io __insertionAdapterOfSystemIdInfo;
    private final aj0 __preparedStmtOfRemoveSystemIdInfo;
    private final aj0 __preparedStmtOfRemoveSystemIdInfo_1;

    public SystemIdInfoDao_Impl(pf0 pf0Var) {
        this.__db = pf0Var;
        this.__insertionAdapterOfSystemIdInfo = new io(pf0Var) { // from class: androidx.work.impl.model.SystemIdInfoDao_Impl.1
            @Override // defpackage.aj0
            public String createQuery() {
                return "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`generation`,`system_id`) VALUES (?,?,?)";
            }

            @Override // defpackage.io
            public void bind(wl0 wl0Var, SystemIdInfo systemIdInfo) {
                String str = systemIdInfo.workSpecId;
                if (str == null) {
                    wl0Var.r(1);
                } else {
                    wl0Var.j(1, str);
                }
                wl0Var.x(systemIdInfo.getGeneration(), 2);
                wl0Var.x(systemIdInfo.systemId, 3);
            }
        };
        this.__preparedStmtOfRemoveSystemIdInfo = new aj0(pf0Var) { // from class: androidx.work.impl.model.SystemIdInfoDao_Impl.2
            @Override // defpackage.aj0
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=? AND generation=?";
            }
        };
        this.__preparedStmtOfRemoveSystemIdInfo_1 = new aj0(pf0Var) { // from class: androidx.work.impl.model.SystemIdInfoDao_Impl.3
            @Override // defpackage.aj0
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=?";
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public SystemIdInfo getSystemIdInfo(String str, int i) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(2, "SELECT * FROM SystemIdInfo WHERE work_spec_id=? AND generation=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        sf0VarC.x(i, 2);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            int iS = jq0.s(cursorB, "work_spec_id");
            int iS2 = jq0.s(cursorB, "generation");
            int iS3 = jq0.s(cursorB, "system_id");
            SystemIdInfo systemIdInfo = null;
            String string = null;
            if (cursorB.moveToFirst()) {
                if (!cursorB.isNull(iS)) {
                    string = cursorB.getString(iS);
                }
                systemIdInfo = new SystemIdInfo(string, cursorB.getInt(iS2), cursorB.getInt(iS3));
            }
            return systemIdInfo;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public List<String> getWorkSpecIds() throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(0, "SELECT DISTINCT work_spec_id FROM SystemIdInfo");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                arrayList.add(cursorB.isNull(0) ? null : cursorB.getString(0));
            }
            return arrayList;
        } finally {
            cursorB.close();
            sf0VarC.D();
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
        wl0 wl0VarAcquire = this.__preparedStmtOfRemoveSystemIdInfo.acquire();
        if (str == null) {
            wl0VarAcquire.r(1);
        } else {
            wl0VarAcquire.j(1, str);
        }
        wl0VarAcquire.x(i, 2);
        this.__db.beginTransaction();
        try {
            wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfRemoveSystemIdInfo.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.SystemIdInfoDao
    public void removeSystemIdInfo(String str) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfRemoveSystemIdInfo_1.acquire();
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
            this.__preparedStmtOfRemoveSystemIdInfo_1.release(wl0VarAcquire);
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
