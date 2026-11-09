package androidx.work.impl.model;

import androidx.work.Data;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class WorkProgress {
    private final Data progress;
    private final String workSpecId;

    public WorkProgress(String str, Data data) {
        t40.n(str, "workSpecId");
        t40.n(data, "progress");
        this.workSpecId = str;
        this.progress = data;
    }

    public final Data getProgress() {
        return this.progress;
    }

    public final String getWorkSpecId() {
        return this.workSpecId;
    }
}
