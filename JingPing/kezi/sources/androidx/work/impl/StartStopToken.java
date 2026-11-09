package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class StartStopToken {
    private final WorkGenerationalId id;

    public StartStopToken(WorkGenerationalId workGenerationalId) {
        oy.j(workGenerationalId, "id");
        this.id = workGenerationalId;
    }

    public final WorkGenerationalId getId() {
        return this.id;
    }
}
