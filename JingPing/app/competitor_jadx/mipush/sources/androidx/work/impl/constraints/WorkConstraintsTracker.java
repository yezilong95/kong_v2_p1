package androidx.work.impl.constraints;

import androidx.work.impl.model.WorkSpec;

/* JADX INFO: loaded from: classes.dex */
public interface WorkConstraintsTracker {
    void replace(Iterable<WorkSpec> iterable);

    void reset();
}
