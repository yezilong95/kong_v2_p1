package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.Preference;
import defpackage.b30;
import defpackage.ba0;
import defpackage.bp0;
import defpackage.e60;
import defpackage.oy;
import defpackage.ql0;
import defpackage.xu;

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

    public static void migrateLegacyPreferences(Context context, ql0 ql0Var) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        if (sharedPreferences.contains(KEY_RESCHEDULE_NEEDED) || sharedPreferences.contains(KEY_LAST_CANCEL_ALL_TIME_MS)) {
            long j = sharedPreferences.getLong(KEY_LAST_CANCEL_ALL_TIME_MS, 0L);
            long j2 = sharedPreferences.getBoolean(KEY_RESCHEDULE_NEEDED, false) ? 1L : 0L;
            ql0Var.f();
            try {
                ql0Var.k(new Object[]{KEY_LAST_CANCEL_ALL_TIME_MS, Long.valueOf(j)});
                ql0Var.k(new Object[]{KEY_RESCHEDULE_NEEDED, Long.valueOf(j2)});
                sharedPreferences.edit().clear().apply();
                ql0Var.l();
            } finally {
                ql0Var.e();
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

    public b30 getLastCancelAllTimeMillisLiveData() {
        b30 observableLongValue = this.mWorkDatabase.preferenceDao().getObservableLongValue(KEY_LAST_CANCEL_ALL_TIME_MS);
        xu xuVar = new xu() { // from class: androidx.work.impl.utils.PreferenceUtils.1
            @Override // defpackage.xu
            public Long apply(Long l) {
                return Long.valueOf(l != null ? l.longValue() : 0L);
            }
        };
        oy.j(observableLongValue, "<this>");
        e60 e60Var = new e60();
        final bp0 bp0Var = new bp0(e60Var, xuVar);
        e60Var.j(observableLongValue, new ba0() { // from class: cp0
            public final boolean equals(Object obj) {
                if (!(obj instanceof ba0) || !(obj instanceof cp0)) {
                    return false;
                }
                return bp0Var.equals(bp0Var);
            }

            public final int hashCode() {
                return bp0Var.hashCode();
            }

            @Override // defpackage.ba0
            public final /* synthetic */ void onChanged(Object obj) {
                bp0Var.invoke(obj);
            }
        });
        return e60Var;
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
