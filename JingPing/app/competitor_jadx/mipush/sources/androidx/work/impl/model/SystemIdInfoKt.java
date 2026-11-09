package androidx.work.impl.model;

import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class SystemIdInfoKt {
    public static final SystemIdInfo systemIdInfo(WorkGenerationalId workGenerationalId, int i) {
        t40.n(workGenerationalId, "generationalId");
        return new SystemIdInfo(workGenerationalId.getWorkSpecId(), workGenerationalId.getGeneration(), i);
    }
}
