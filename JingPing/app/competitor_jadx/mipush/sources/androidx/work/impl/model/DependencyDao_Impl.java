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
public final class DependencyDao_Impl implements DependencyDao {
    private final kp0 __db;
    private final bu __insertionAdapterOfDependency;

    public DependencyDao_Impl(kp0 kp0Var) {
        this.__db = kp0Var;
        this.__insertionAdapterOfDependency = new bu(kp0Var) { // from class: androidx.work.impl.model.DependencyDao_Impl.1
            @Override // defpackage.pv0
            public String createQuery() {
                return "INSERT OR IGNORE INTO `Dependency` (`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
            }

            @Override // defpackage.bu
            public void bind(ky0 ky0Var, Dependency dependency) {
                if (dependency.getWorkSpecId() == null) {
                    ky0Var.h(1);
                } else {
                    ky0Var.e(1, dependency.getWorkSpecId());
                }
                if (dependency.getPrerequisiteId() == null) {
                    ky0Var.h(2);
                } else {
                    ky0Var.e(2, dependency.getPrerequisiteId());
                }
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.DependencyDao
    public List<String> getDependentWorkIds(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT work_spec_id FROM dependency WHERE prerequisite_id=?");
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

    @Override // androidx.work.impl.model.DependencyDao
    public List<String> getPrerequisites(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT prerequisite_id FROM dependency WHERE work_spec_id=?");
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

    @Override // androidx.work.impl.model.DependencyDao
    public boolean hasCompletedAllPrerequisites(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        boolean z = false;
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            if (cursorD0.moveToFirst()) {
                z = cursorD0.getInt(0) != 0;
            }
            return z;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.DependencyDao
    public boolean hasDependents(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT COUNT(*)>0 FROM dependency WHERE prerequisite_id=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        boolean z = false;
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            if (cursorD0.moveToFirst()) {
                z = cursorD0.getInt(0) != 0;
            }
            return z;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.DependencyDao
    public void insertDependency(Dependency dependency) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDependency.insert(dependency);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
