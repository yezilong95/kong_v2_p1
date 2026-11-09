package androidx.work.impl.model;

import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class WorkTag {
    private final String tag;
    private final String workSpecId;

    public WorkTag(String str, String str2) {
        t40.n(str, "tag");
        t40.n(str2, "workSpecId");
        this.tag = str;
        this.workSpecId = str2;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getWorkSpecId() {
        return this.workSpecId;
    }
}
