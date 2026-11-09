package androidx.work.impl.model;

import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class WorkName {
    private final String name;
    private final String workSpecId;

    public WorkName(String str, String str2) {
        oy.j(str, "name");
        oy.j(str2, "workSpecId");
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
