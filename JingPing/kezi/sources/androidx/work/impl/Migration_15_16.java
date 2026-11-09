package androidx.work.impl;

import defpackage.g70;
import defpackage.oy;
import defpackage.ql0;

/* JADX INFO: loaded from: classes.dex */
public final class Migration_15_16 extends g70 {
    public static final Migration_15_16 INSTANCE = new Migration_15_16();

    private Migration_15_16() {
        super(15, 16);
    }

    @Override // defpackage.g70
    public void migrate(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        ql0Var.i("DELETE FROM SystemIdInfo WHERE work_spec_id IN (SELECT work_spec_id FROM SystemIdInfo LEFT JOIN WorkSpec ON work_spec_id = id WHERE WorkSpec.id IS NULL)");
        ql0Var.i("ALTER TABLE `WorkSpec` ADD COLUMN `generation` INTEGER NOT NULL DEFAULT 0");
        ql0Var.i("CREATE TABLE IF NOT EXISTS `_new_SystemIdInfo` (\n            `work_spec_id` TEXT NOT NULL, \n            `generation` INTEGER NOT NULL DEFAULT 0, \n            `system_id` INTEGER NOT NULL, \n            PRIMARY KEY(`work_spec_id`, `generation`), \n            FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) \n                ON UPDATE CASCADE ON DELETE CASCADE )");
        ql0Var.i("INSERT INTO `_new_SystemIdInfo` (`work_spec_id`,`system_id`) SELECT `work_spec_id`,`system_id` FROM `SystemIdInfo`");
        ql0Var.i("DROP TABLE `SystemIdInfo`");
        ql0Var.i("ALTER TABLE `_new_SystemIdInfo` RENAME TO `SystemIdInfo`");
    }
}
