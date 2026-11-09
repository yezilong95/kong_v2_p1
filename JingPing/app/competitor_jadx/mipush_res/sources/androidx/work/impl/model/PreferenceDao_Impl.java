package androidx.work.impl.model;

import android.database.Cursor;
import defpackage.bu;
import defpackage.ea0;
import defpackage.kp0;
import defpackage.ky0;
import defpackage.l21;
import defpackage.np0;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class PreferenceDao_Impl implements PreferenceDao {
    private final kp0 __db;
    private final bu __insertionAdapterOfPreference;

    public PreferenceDao_Impl(kp0 kp0Var) {
        this.__db = kp0Var;
        this.__insertionAdapterOfPreference = new bu(kp0Var) { // from class: androidx.work.impl.model.PreferenceDao_Impl.1
            @Override // defpackage.pv0
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
            }

            @Override // defpackage.bu
            public void bind(ky0 ky0Var, Preference preference) {
                if (preference.getKey() == null) {
                    ky0Var.h(1);
                } else {
                    ky0Var.e(1, preference.getKey());
                }
                if (preference.getValue() == null) {
                    ky0Var.h(2);
                } else {
                    ky0Var.n(2, preference.getValue().longValue());
                }
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public Long getLongValue(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT long_value FROM Preference where `key`=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            Long lValueOf = null;
            if (cursorD0.moveToFirst() && !cursorD0.isNull(0)) {
                lValueOf = Long.valueOf(cursorD0.getLong(0));
            }
            return lValueOf;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public ea0 getObservableLongValue(String str) {
        final np0 np0VarW = np0.w(1, "SELECT long_value FROM Preference where `key`=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        return this.__db.getInvalidationTracker().a(new String[]{"Preference"}, false, new Callable<Long>() { // from class: androidx.work.impl.model.PreferenceDao_Impl.2
            public void finalize() {
                np0VarW.x();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                Cursor cursorD0 = l21.D0(PreferenceDao_Impl.this.__db, np0VarW, false);
                try {
                    Long lValueOf = null;
                    if (cursorD0.moveToFirst() && !cursorD0.isNull(0)) {
                        lValueOf = Long.valueOf(cursorD0.getLong(0));
                    }
                    return lValueOf;
                } finally {
                    cursorD0.close();
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
