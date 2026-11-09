package androidx.work.impl.model;

import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class Dependency {
    private final String prerequisiteId;
    private final String workSpecId;

    public Dependency(String str, String str2) {
        oy.j(str, "workSpecId");
        oy.j(str2, "prerequisiteId");
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
