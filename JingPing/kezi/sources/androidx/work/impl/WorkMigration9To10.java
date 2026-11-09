package androidx.work.impl;

import android.content.Context;
import androidx.work.impl.utils.IdGeneratorKt;
import androidx.work.impl.utils.PreferenceUtils;
import defpackage.g70;
import defpackage.oy;
import defpackage.ql0;

/* JADX INFO: loaded from: classes.dex */
public final class WorkMigration9To10 extends g70 {
    private final Context context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WorkMigration9To10(Context context) {
        super(9, 10);
        oy.j(context, "context");
        this.context = context;
    }

    @Override // defpackage.g70
    public void migrate(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        ql0Var.i(PreferenceUtils.CREATE_PREFERENCE);
        PreferenceUtils.migrateLegacyPreferences(this.context, ql0Var);
        IdGeneratorKt.migrateLegacyIdGenerator(this.context, ql0Var);
    }
}
