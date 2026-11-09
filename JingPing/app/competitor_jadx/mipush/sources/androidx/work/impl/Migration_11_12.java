package androidx.work.impl;

import defpackage.ey0;
import defpackage.t40;
import defpackage.ye0;

/* JADX INFO: loaded from: classes.dex */
public final class Migration_11_12 extends ye0 {
    public static final Migration_11_12 INSTANCE = new Migration_11_12();

    private Migration_11_12() {
        super(11, 12);
    }

    @Override // defpackage.ye0
    public void migrate(ey0 ey0Var) {
        t40.n(ey0Var, "db");
        ey0Var.d("ALTER TABLE workspec ADD COLUMN `out_of_quota_policy` INTEGER NOT NULL DEFAULT 0");
    }
}
