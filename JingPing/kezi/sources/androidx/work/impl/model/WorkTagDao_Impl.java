package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.impl.model.WorkTagDao;
import defpackage.aj0;
import defpackage.io;
import defpackage.k9;
import defpackage.pf0;
import defpackage.sf0;
import defpackage.wl0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class WorkTagDao_Impl implements WorkTagDao {
    private final pf0 __db;
    private final io __insertionAdapterOfWorkTag;
    private final aj0 __preparedStmtOfDeleteByWorkSpecId;

    public WorkTagDao_Impl(pf0 pf0Var) {
        this.__db = pf0Var;
        this.__insertionAdapterOfWorkTag = new io(pf0Var) { // from class: androidx.work.impl.model.WorkTagDao_Impl.1
            @Override // defpackage.aj0
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkTag` (`tag`,`work_spec_id`) VALUES (?,?)";
            }

            @Override // defpackage.io
            public void bind(wl0 wl0Var, WorkTag workTag) {
                if (workTag.getTag() == null) {
                    wl0Var.r(1);
                } else {
                    wl0Var.j(1, workTag.getTag());
                }
                if (workTag.getWorkSpecId() == null) {
                    wl0Var.r(2);
                } else {
                    wl0Var.j(2, workTag.getWorkSpecId());
                }
            }
        };
        this.__preparedStmtOfDeleteByWorkSpecId = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkTagDao_Impl.2
            @Override // defpackage.aj0
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
        wl0 wl0VarAcquire = this.__preparedStmtOfDeleteByWorkSpecId.acquire();
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
            this.__preparedStmtOfDeleteByWorkSpecId.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkTagDao
    public List<String> getTagsForWorkSpecId(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
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

    @Override // androidx.work.impl.model.WorkTagDao
    public List<String> getWorkSpecIdsWithTag(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT work_spec_id FROM worktag WHERE tag=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
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
