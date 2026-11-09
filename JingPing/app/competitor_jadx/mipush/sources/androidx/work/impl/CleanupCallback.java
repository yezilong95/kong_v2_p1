package androidx.work.impl;

import defpackage.ey0;
import defpackage.ip0;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class CleanupCallback extends ip0 {
    public static final CleanupCallback INSTANCE = new CleanupCallback();

    private CleanupCallback() {
    }

    private final String getPruneSQL() {
        return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (last_enqueue_time + minimum_retention_duration) < " + getPruneDate() + " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    }

    public final long getPruneDate() {
        return System.currentTimeMillis() - WorkDatabaseKt.PRUNE_THRESHOLD_MILLIS;
    }

    @Override // defpackage.ip0
    public void onOpen(ey0 ey0Var) {
        t40.n(ey0Var, "db");
        ey0Var.b();
        try {
            ey0Var.d(getPruneSQL());
            ey0Var.p();
        } finally {
            ey0Var.a();
        }
    }
}
