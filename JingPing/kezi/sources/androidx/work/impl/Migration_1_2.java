package androidx.work.impl;

import defpackage.g70;
import defpackage.oy;
import defpackage.ql0;

/* JADX INFO: loaded from: classes.dex */
public final class Migration_1_2 extends g70 {
    public static final Migration_1_2 INSTANCE = new Migration_1_2();

    private Migration_1_2() {
        super(1, 2);
    }

    @Override // defpackage.g70
    public void migrate(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        ql0Var.i("\n    CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id`\n    INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`)\n    REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )\n    ");
        ql0Var.i("\n    INSERT INTO SystemIdInfo(work_spec_id, system_id)\n    SELECT work_spec_id, alarm_id AS system_id FROM alarmInfo\n    ");
        ql0Var.i("DROP TABLE IF EXISTS alarmInfo");
        ql0Var.i("\n                INSERT OR IGNORE INTO worktag(tag, work_spec_id)\n                SELECT worker_class_name AS tag, id AS work_spec_id FROM workspec\n                ");
    }
}
