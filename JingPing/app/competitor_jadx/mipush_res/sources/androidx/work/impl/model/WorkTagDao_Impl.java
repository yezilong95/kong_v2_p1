package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.impl.model.WorkTagDao;
import defpackage.bu;
import defpackage.kp0;
import defpackage.ky0;
import defpackage.l21;
import defpackage.np0;
import defpackage.pv0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class WorkTagDao_Impl implements WorkTagDao {
    private final kp0 __db;
    private final bu __insertionAdapterOfWorkTag;
    private final pv0 __preparedStmtOfDeleteByWorkSpecId;

    public WorkTagDao_Impl(kp0 kp0Var) {
        this.__db = kp0Var;
        this.__insertionAdapterOfWorkTag = new bu(kp0Var) { // from class: androidx.work.impl.model.WorkTagDao_Impl.1
            @Override // defpackage.pv0
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkTag` (`tag`,`work_spec_id`) VALUES (?,?)";
            }

            @Override // defpackage.bu
            public void bind(ky0 ky0Var, WorkTag workTag) {
                if (workTag.getTag() == null) {
                    ky0Var.h(1);
                } else {
                    ky0Var.e(1, workTag.getTag());
                }
                if (workTag.getWorkSpecId() == null) {
                    ky0Var.h(2);
                } else {
                    ky0Var.e(2, workTag.getWorkSpecId());
                }
            }
        };
        this.__preparedStmtOfDeleteByWorkSpecId = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkTagDao_Impl.2
            @Override // defpackage.pv0
            public String createQuery() {
                return "DELETE FROM worktag WHERE work_spec_id=?";
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.WorkTagDao
    public void deleteByWorkSpecId(String str) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfDeleteByWorkSpecId.acquire();
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
            this.__preparedStmtOfDeleteByWorkSpecId.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkTagDao
    public List<String> getTagsForWorkSpecId(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
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

    @Override // androidx.work.impl.model.WorkTagDao
    public List<String> getWorkSpecIdsWithTag(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT work_spec_id FROM worktag WHERE tag=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
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

    @Override // androidx.work.impl.model.WorkTagDao
    public void insert(WorkTag workTag) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkTag.insert(workTag);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkTagDao
    public void insertTags(String str, Set<String> set) {
        WorkTagDao.DefaultImpls.insertTags(this, str, set);
    }
}
