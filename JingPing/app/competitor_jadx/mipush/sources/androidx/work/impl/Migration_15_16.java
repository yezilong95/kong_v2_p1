package androidx.work.impl;

import defpackage.ey0;
import defpackage.t40;
import defpackage.ye0;

/* JADX INFO: loaded from: classes.dex */
public final class Migration_15_16 extends ye0 {
    public static final Migration_15_16 INSTANCE = new Migration_15_16();

    private Migration_15_16() {
        super(15, 16);
    }

    @Override // defpackage.ye0
    public void migrate(ey0 ey0Var) {
        t40.n(ey0Var, "db");
        ey0Var.d("DELETE FROM SystemIdInfo WHERE work_spec_id IN (SELECT work_spec_id FROM SystemIdInfo LEFT JOIN WorkSpec ON work_spec_id = id WHERE WorkSpec.id IS NULL)");
        ey0Var.d("ALTER TABLE `WorkSpec` ADD COLUMN `generation` INTEGER NOT NULL DEFAULT 0");
        ey0Var.d("CREATE TABLE IF NOT EXISTS `_new_SystemIdInfo` (\n            `work_spec_id` TEXT NOT NULL, \n            `generation` INTEGER NOT NULL DEFAULT 0, \n            `system_id` INTEGER NOT NULL, \n            PRIMARY KEY(`work_spec_id`, `generation`), \n            FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) \n                ON UPDATE CASCADE ON DELETE CASCADE )");
        ey0Var.d("INSERT INTO `_new_SystemIdInfo` (`work_spec_id`,`system_id`) SELECT `work_spec_id`,`system_id` FROM `SystemIdInfo`");
        ey0Var.d("DROP TABLE `SystemIdInfo`");
        ey0Var.d("ALTER TABLE `_new_SystemIdInfo` RENAME TO `SystemIdInfo`");
    }
}
