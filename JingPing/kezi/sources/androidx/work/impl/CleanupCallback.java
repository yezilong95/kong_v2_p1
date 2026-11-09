package androidx.work.impl;

import defpackage.nf0;
import defpackage.oy;
import defpackage.ql0;

/* JADX INFO: loaded from: classes.dex */
public final class CleanupCallback extends nf0 {
    public static final CleanupCallback INSTANCE = new CleanupCallback();

    private CleanupCallback() {
    }

    private final String getPruneSQL() {
        return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (last_enqueue_time + minimum_retention_duration) < " + getPruneDate() + " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    }

    public final long getPruneDate() {
        return System.currentTimeMillis() - WorkDatabaseKt.PRUNE_THRESHOLD_MILLIS;
    }

    @Override // defpackage.nf0
    public void onOpen(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        ql0Var.f();
        try {
            ql0Var.i(getPruneSQL());
            ql0Var.l();
        } finally {
            ql0Var.e();
        }
    }
}
