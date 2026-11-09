package androidx.work.impl.model;

import defpackage.t40;
import io.nats.client.support.ApiConstants;

/* JADX INFO: loaded from: classes.dex */
public final class WorkName {
    private final String name;
    private final String workSpecId;

    public WorkName(String str, String str2) {
        t40.n(str, ApiConstants.NAME);
        t40.n(str2, "workSpecId");
        this.name = str;
        this.workSpecId = str2;
    }

    public final String getName() {
        return this.name;
    }

    public final String getWorkSpecId() {
        return this.workSpecId;
    }
}
