package androidx.work.impl;

import android.content.Context;
import androidx.work.impl.utils.PreferenceUtils;
import defpackage.g70;
import defpackage.oy;
import defpackage.ql0;

/* JADX INFO: loaded from: classes.dex */
public final class RescheduleMigration extends g70 {
    private final Context mContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RescheduleMigration(Context context, int i, int i2) {
        super(i, i2);
        oy.j(context, "mContext");
        this.mContext = context;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    @Override // defpackage.g70
    public void migrate(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        if (this.endVersion >= 10) {
            ql0Var.k(new Object[]{PreferenceUtils.KEY_RESCHEDULE_NEEDED, 1});
        } else {
            this.mContext.getSharedPreferences(PreferenceUtils.PREFERENCES_FILE_NAME, 0).edit().putBoolean(PreferenceUtils.KEY_RESCHEDULE_NEEDED, true).apply();
        }
    }
}
