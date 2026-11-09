package androidx.work.impl;

import defpackage.g70;
import defpackage.oy;
import defpackage.ql0;

/* JADX INFO: loaded from: classes.dex */
public final class Migration_12_13 extends g70 {
    public static final Migration_12_13 INSTANCE = new Migration_12_13();

    private Migration_12_13() {
        super(12, 13);
    }

    @Override // defpackage.g70
    public void migrate(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        ql0Var.i("UPDATE workspec SET required_network_type = 0 WHERE required_network_type IS NULL ");
        ql0Var.i("UPDATE workspec SET content_uri_triggers = x'' WHERE content_uri_triggers is NULL");
    }
}
