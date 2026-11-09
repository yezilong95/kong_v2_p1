package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import defpackage.t40;
import io.nats.client.support.ApiConstants;

/* JADX INFO: loaded from: classes.dex */
public final class StartStopToken {
    private final WorkGenerationalId id;

    public StartStopToken(WorkGenerationalId workGenerationalId) {
        t40.n(workGenerationalId, ApiConstants.ID);
        this.id = workGenerationalId;
    }

    public final WorkGenerationalId getId() {
        return this.id;
    }
}
