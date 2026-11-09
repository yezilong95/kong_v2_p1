package androidx.work.impl;

import android.content.Context;
import androidx.work.impl.utils.IdGeneratorKt;
import androidx.work.impl.utils.PreferenceUtils;
import defpackage.ey0;
import defpackage.t40;
import defpackage.ye0;

/* JADX INFO: loaded from: classes.dex */
public final class WorkMigration9To10 extends ye0 {
    private final Context context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WorkMigration9To10(Context context) {
        super(9, 10);
        t40.n(context, "context");
        this.context = context;
    }

    @Override // defpackage.ye0
    public void migrate(ey0 ey0Var) {
        t40.n(ey0Var, "db");
        ey0Var.d(PreferenceUtils.CREATE_PREFERENCE);
        PreferenceUtils.migrateLegacyPreferences(this.context, ey0Var);
        IdGeneratorKt.migrateLegacyIdGenerator(this.context, ey0Var);
    }
}
