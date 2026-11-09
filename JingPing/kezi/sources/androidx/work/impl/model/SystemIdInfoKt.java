package androidx.work.impl.model;

import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class SystemIdInfoKt {
    public static final SystemIdInfo systemIdInfo(WorkGenerationalId workGenerationalId, int i) {
        oy.j(workGenerationalId, "generationalId");
        return new SystemIdInfo(workGenerationalId.getWorkSpecId(), workGenerationalId.getGeneration(), i);
    }
}
