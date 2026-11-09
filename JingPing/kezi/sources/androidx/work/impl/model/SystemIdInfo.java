package androidx.work.impl.model;

import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class SystemIdInfo {
    private final int generation;
    public final int systemId;
    public final String workSpecId;

    public SystemIdInfo(String str, int i, int i2) {
        oy.j(str, "workSpecId");
        this.workSpecId = str;
        this.generation = i;
        this.systemId = i2;
    }

    public static /* synthetic */ SystemIdInfo copy$default(SystemIdInfo systemIdInfo, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = systemIdInfo.workSpecId;
        }
        if ((i3 & 2) != 0) {
            i = systemIdInfo.generation;
        }
        if ((i3 & 4) != 0) {
            i2 = systemIdInfo.systemId;
        }
        return systemIdInfo.copy(str, i, i2);
    }

    public final String component1() {
        return this.workSpecId;
    }

    public final int component2() {
        return this.generation;
    }

    public final int component3() {
        return this.systemId;
    }

    public final SystemIdInfo copy(String str, int i, int i2) {
        oy.j(str, "workSpecId");
        return new SystemIdInfo(str, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SystemIdInfo)) {
            return false;
        }
        SystemIdInfo systemIdInfo = (SystemIdInfo) obj;
        return oy.c(this.workSpecId, systemIdInfo.workSpecId) && this.generation == systemIdInfo.generation && this.systemId == systemIdInfo.systemId;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public int hashCode() {
        return Integer.hashCode(this.systemId) + ((Integer.hashCode(this.generation) + (this.workSpecId.hashCode() * 31)) * 31);
    }

    public String toString() {
        return "SystemIdInfo(workSpecId=" + this.workSpecId + ", generation=" + this.generation + ", systemId=" + this.systemId + ')';
    }
}
