package androidx.work.impl;

import defpackage.g70;
import defpackage.oy;
import defpackage.ql0;

/* JADX INFO: loaded from: classes.dex */
public final class Migration_7_8 extends g70 {
    public static final Migration_7_8 INSTANCE = new Migration_7_8();

    private Migration_7_8() {
        super(7, 8);
    }

    @Override // defpackage.g70
    public void migrate(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        ql0Var.i("\n    CREATE INDEX IF NOT EXISTS `index_WorkSpec_period_start_time` ON `workspec`(`period_start_time`)\n    ");
    }
}
