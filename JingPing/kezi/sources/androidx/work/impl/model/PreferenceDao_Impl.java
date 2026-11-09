package androidx.work.impl.model;

import android.database.Cursor;
import defpackage.b30;
import defpackage.io;
import defpackage.k9;
import defpackage.pf0;
import defpackage.sf0;
import defpackage.wl0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class PreferenceDao_Impl implements PreferenceDao {
    private final pf0 __db;
    private final io __insertionAdapterOfPreference;

    public PreferenceDao_Impl(pf0 pf0Var) {
        this.__db = pf0Var;
        this.__insertionAdapterOfPreference = new io(pf0Var) { // from class: androidx.work.impl.model.PreferenceDao_Impl.1
            @Override // defpackage.aj0
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
            }

            @Override // defpackage.io
            public void bind(wl0 wl0Var, Preference preference) {
                if (preference.getKey() == null) {
                    wl0Var.r(1);
                } else {
                    wl0Var.j(1, preference.getKey());
                }
                if (preference.getValue() == null) {
                    wl0Var.r(2);
                } else {
                    wl0Var.x(preference.getValue().longValue(), 2);
                }
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public Long getLongValue(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT long_value FROM Preference where `key`=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            Long lValueOf = null;
            if (cursorB.moveToFirst() && !cursorB.isNull(0)) {
                lValueOf = Long.valueOf(cursorB.getLong(0));
            }
            return lValueOf;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public b30 getObservableLongValue(String str) {
        final sf0 sf0VarC = sf0.C(1, "SELECT long_value FROM Preference where `key`=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        return this.__db.getInvalidationTracker().a(new String[]{"Preference"}, false, new Callable<Long>() { // from class: androidx.work.impl.model.PreferenceDao_Impl.2
            public void finalize() {
                sf0VarC.D();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                Cursor cursorB = k9.B(PreferenceDao_Impl.this.__db, sf0VarC, false);
                try {
                    Long lValueOf = null;
                    if (cursorB.moveToFirst() && !cursorB.isNull(0)) {
                        lValueOf = Long.valueOf(cursorB.getLong(0));
                    }
                    return lValueOf;
                } finally {
                    cursorB.close();
                }
            }
        });
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public void insertPreference(Preference preference) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPreference.insert(preference);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
