package androidx.work.impl.model;

import android.database.Cursor;
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

/* JADX INFO: loaded from: classes.dex */
public final class WorkNameDao_Impl implements WorkNameDao {
    private final pf0 __db;
    private final io __insertionAdapterOfWorkName;

    public WorkNameDao_Impl(pf0 pf0Var) {
        this.__db = pf0Var;
        this.__insertionAdapterOfWorkName = new io(pf0Var) { // from class: androidx.work.impl.model.WorkNameDao_Impl.1
            @Override // defpackage.aj0
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkName` (`name`,`work_spec_id`) VALUES (?,?)";
            }

            @Override // defpackage.io
            public void bind(wl0 wl0Var, WorkName workName) {
                if (workName.getName() == null) {
                    wl0Var.r(1);
                } else {
                    wl0Var.j(1, workName.getName());
                }
                if (workName.getWorkSpecId() == null) {
                    wl0Var.r(2);
                } else {
                    wl0Var.j(2, workName.getWorkSpecId());
                }
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.WorkNameDao
    public List<String> getNamesForWorkSpecId(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT name FROM workname WHERE work_spec_id=?");
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

    @Override // androidx.work.impl.model.WorkNameDao
    public List<String> getWorkSpecIdsWithName(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT work_spec_id FROM workname WHERE name=?");
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

    @Override // androidx.work.impl.model.WorkNameDao
    public void insert(WorkName workName) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkName.insert(workName);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
