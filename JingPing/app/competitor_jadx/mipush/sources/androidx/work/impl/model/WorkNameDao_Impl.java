package androidx.work.impl.model;

import android.database.Cursor;
import defpackage.bu;
import defpackage.kp0;
import defpackage.ky0;
import defpackage.l21;
import defpackage.np0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WorkNameDao_Impl implements WorkNameDao {
    private final kp0 __db;
    private final bu __insertionAdapterOfWorkName;

    public WorkNameDao_Impl(kp0 kp0Var) {
        this.__db = kp0Var;
        this.__insertionAdapterOfWorkName = new bu(kp0Var) { // from class: androidx.work.impl.model.WorkNameDao_Impl.1
            @Override // defpackage.pv0
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkName` (`name`,`work_spec_id`) VALUES (?,?)";
            }

            @Override // defpackage.bu
            public void bind(ky0 ky0Var, WorkName workName) {
                if (workName.getName() == null) {
                    ky0Var.h(1);
                } else {
                    ky0Var.e(1, workName.getName());
                }
                if (workName.getWorkSpecId() == null) {
                    ky0Var.h(2);
                } else {
                    ky0Var.e(2, workName.getWorkSpecId());
                }
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.WorkNameDao
    public List<String> getNamesForWorkSpecId(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT name FROM workname WHERE work_spec_id=?");
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

    @Override // androidx.work.impl.model.WorkNameDao
    public List<String> getWorkSpecIdsWithName(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT work_spec_id FROM workname WHERE name=?");
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
