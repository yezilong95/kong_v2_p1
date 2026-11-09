package androidx.work.impl;

import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class WorkRequestHolder extends WorkRequest {
    public WorkRequestHolder(UUID uuid, WorkSpec workSpec, Set<String> set) {
        super(uuid, workSpec, set);
    }
}
