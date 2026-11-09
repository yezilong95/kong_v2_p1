package androidx.work.impl;

import defpackage.ey0;
import defpackage.t40;
import defpackage.ye0;

/* JADX INFO: loaded from: classes.dex */
public final class Migration_7_8 extends ye0 {
    public static final Migration_7_8 INSTANCE = new Migration_7_8();

    private Migration_7_8() {
        super(7, 8);
    }

    @Override // defpackage.ye0
    public void migrate(ey0 ey0Var) {
        t40.n(ey0Var, "db");
        ey0Var.d("\n    CREATE INDEX IF NOT EXISTS `index_WorkSpec_period_start_time` ON `workspec`(`period_start_time`)\n    ");
    }
}
