package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.Preference;
import defpackage.ea0;
import defpackage.ey0;
import defpackage.j10;
import defpackage.n11;
import defpackage.t40;
import defpackage.ti0;
import defpackage.ud0;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceUtils {
    public static final String CREATE_PREFERENCE = "CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))";
    public static final String INSERT_PREFERENCE = "INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)";
    public static final String KEY_LAST_CANCEL_ALL_TIME_MS = "last_cancel_all_time_ms";
    private static final String KEY_LAST_FORCE_STOP_MS = "last_force_stop_ms";
    public static final String KEY_RESCHEDULE_NEEDED = "reschedule_needed";
    public static final String PREFERENCES_FILE_NAME = "androidx.work.util.preferences";
    private final WorkDatabase mWorkDatabase;

    public PreferenceUtils(WorkDatabase workDatabase) {
        this.mWorkDatabase = workDatabase;
    }

    public static void migrateLegacyPreferences(Context context, ey0 ey0Var) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        if (sharedPreferences.contains(KEY_RESCHEDULE_NEEDED) || sharedPreferences.contains(KEY_LAST_CANCEL_ALL_TIME_MS)) {
            long j = sharedPreferences.getLong(KEY_LAST_CANCEL_ALL_TIME_MS, 0L);
            long j2 = sharedPreferences.getBoolean(KEY_RESCHEDULE_NEEDED, false) ? 1L : 0L;
            ey0Var.b();
            try {
                ey0Var.o(new Object[]{KEY_LAST_CANCEL_ALL_TIME_MS, Long.valueOf(j)});
                ey0Var.o(new Object[]{KEY_RESCHEDULE_NEEDED, Long.valueOf(j2)});
                sharedPreferences.edit().clear().apply();
                ey0Var.p();
            } finally {
                ey0Var.a();
            }
        }
    }

    public long getLastCancelAllTimeMillis() {
        Long longValue = this.mWorkDatabase.preferenceDao().getLongValue(KEY_LAST_CANCEL_ALL_TIME_MS);
        if (longValue != null) {
            return longValue.longValue();
        }
        return 0L;
    }

    public ea0 getLastCancelAllTimeMillisLiveData() {
        ea0 observableLongValue = this.mWorkDatabase.preferenceDao().getObservableLongValue(KEY_LAST_CANCEL_ALL_TIME_MS);
        j10 j10Var = new j10() { // from class: androidx.work.impl.utils.PreferenceUtils.1
            @Override // defpackage.j10
            public Long apply(Long l) {
                return Long.valueOf(l != null ? l.longValue() : 0L);
            }
        };
        t40.n(observableLongValue, "<this>");
        ud0 ud0Var = new ud0();
        final n11 n11Var = new n11(ud0Var, j10Var);
        ud0Var.h(observableLongValue, new ti0() { // from class: o11
            public final boolean equals(Object obj) {
                if (!(obj instanceof ti0) || !(obj instanceof o11)) {
                    return false;
                }
                return n11Var.equals(n11Var);
            }

            public final int hashCode() {
                return n11Var.hashCode();
            }

            @Override // defpackage.ti0
            public final /* synthetic */ void onChanged(Object obj) {
                n11Var.invoke(obj);
            }
        });
        return ud0Var;
    }

    public long getLastForceStopEventMillis() {
        Long longValue = this.mWorkDatabase.preferenceDao().getLongValue(KEY_LAST_FORCE_STOP_MS);
        if (longValue != null) {
            return longValue.longValue();
        }
        return 0L;
    }

    public boolean getNeedsReschedule() {
        Long longValue = this.mWorkDatabase.preferenceDao().getLongValue(KEY_RESCHEDULE_NEEDED);
        return longValue != null && longValue.longValue() == 1;
    }

    public void setLastCancelAllTimeMillis(long j) {
        this.mWorkDatabase.preferenceDao().insertPreference(new Preference(KEY_LAST_CANCEL_ALL_TIME_MS, Long.valueOf(j)));
    }

    public void setLastForceStopEventMillis(long j) {
        this.mWorkDatabase.preferenceDao().insertPreference(new Preference(KEY_LAST_FORCE_STOP_MS, Long.valueOf(j)));
    }

    public void setNeedsReschedule(boolean z) {
        this.mWorkDatabase.preferenceDao().insertPreference(new Preference(KEY_RESCHEDULE_NEEDED, z));
    }
}
