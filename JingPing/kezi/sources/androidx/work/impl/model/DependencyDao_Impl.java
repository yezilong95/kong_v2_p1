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
public final class DependencyDao_Impl implements DependencyDao {
    private final pf0 __db;
    private final io __insertionAdapterOfDependency;

    public DependencyDao_Impl(pf0 pf0Var) {
        this.__db = pf0Var;
        this.__insertionAdapterOfDependency = new io(pf0Var) { // from class: androidx.work.impl.model.DependencyDao_Impl.1
            @Override // defpackage.aj0
            public String createQuery() {
                return "INSERT OR IGNORE INTO `Dependency` (`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
            }

            @Override // defpackage.io
            public void bind(wl0 wl0Var, Dependency dependency) {
                if (dependency.getWorkSpecId() == null) {
                    wl0Var.r(1);
                } else {
                    wl0Var.j(1, dependency.getWorkSpecId());
                }
                if (dependency.getPrerequisiteId() == null) {
                    wl0Var.r(2);
                } else {
                    wl0Var.j(2, dependency.getPrerequisiteId());
                }
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.DependencyDao
    public List<String> getDependentWorkIds(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT work_spec_id FROM dependency WHERE prerequisite_id=?");
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

    @Override // androidx.work.impl.model.DependencyDao
    public List<String> getPrerequisites(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT prerequisite_id FROM dependency WHERE work_spec_id=?");
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

    @Override // androidx.work.impl.model.DependencyDao
    public boolean hasCompletedAllPrerequisites(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        boolean z = false;
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            if (cursorB.moveToFirst()) {
                z = cursorB.getInt(0) != 0;
            }
            return z;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.DependencyDao
    public boolean hasDependents(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT COUNT(*)>0 FROM dependency WHERE prerequisite_id=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        boolean z = false;
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            if (cursorB.moveToFirst()) {
                z = cursorB.getInt(0) != 0;
            }
            return z;
        } finally {
            cursorB.close();
            sf0VarC.D();
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
