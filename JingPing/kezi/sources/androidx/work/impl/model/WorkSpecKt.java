package androidx.work.impl.model;

import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class WorkSpecKt {
    public static final WorkGenerationalId generationalId(WorkSpec workSpec) {
        oy.j(workSpec, "<this>");
        return new WorkGenerationalId(workSpec.id, workSpec.getGeneration());
    }
}
