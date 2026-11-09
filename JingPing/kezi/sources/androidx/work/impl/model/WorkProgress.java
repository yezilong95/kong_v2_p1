package androidx.work.impl.model;

import androidx.work.Data;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class WorkProgress {
    private final Data progress;
    private final String workSpecId;

    public WorkProgress(String str, Data data) {
        oy.j(str, "workSpecId");
        oy.j(data, "progress");
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
