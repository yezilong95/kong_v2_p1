package androidx.work.impl;

import android.content.Context;
import androidx.work.impl.utils.PreferenceUtils;
import defpackage.ey0;
import defpackage.t40;
import defpackage.ye0;

/* JADX INFO: loaded from: classes.dex */
public final class RescheduleMigration extends ye0 {
    private final Context mContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RescheduleMigration(Context context, int i, int i2) {
        super(i, i2);
        t40.n(context, "mContext");
        this.mContext = context;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    @Override // defpackage.ye0
    public void migrate(ey0 ey0Var) {
        t40.n(ey0Var, "db");
        if (this.endVersion >= 10) {
            ey0Var.o(new Object[]{PreferenceUtils.KEY_RESCHEDULE_NEEDED, 1});
        } else {
            this.mContext.getSharedPreferences(PreferenceUtils.PREFERENCES_FILE_NAME, 0).edit().putBoolean(PreferenceUtils.KEY_RESCHEDULE_NEEDED, true).apply();
        }
    }
}
