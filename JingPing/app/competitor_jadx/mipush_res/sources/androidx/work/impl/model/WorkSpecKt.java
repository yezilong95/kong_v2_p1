package androidx.work.impl.model;

import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class WorkSpecKt {
    public static final WorkGenerationalId generationalId(WorkSpec workSpec) {
        t40.n(workSpec, "<this>");
        return new WorkGenerationalId(workSpec.id, workSpec.getGeneration());
    }
}
