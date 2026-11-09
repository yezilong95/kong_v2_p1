package androidx.work.impl.model;

import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class Dependency {
    private final String prerequisiteId;
    private final String workSpecId;

    public Dependency(String str, String str2) {
        t40.n(str, "workSpecId");
        t40.n(str2, "prerequisiteId");
        this.workSpecId = str;
        this.prerequisiteId = str2;
    }

    public final String getPrerequisiteId() {
        return this.prerequisiteId;
    }

    public final String getWorkSpecId() {
        return this.workSpecId;
    }
}
