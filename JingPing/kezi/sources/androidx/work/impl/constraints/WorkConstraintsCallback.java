package androidx.work.impl.constraints;

import androidx.work.impl.model.WorkSpec;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface WorkConstraintsCallback {
    void onAllConstraintsMet(List<WorkSpec> list);

    void onAllConstraintsNotMet(List<WorkSpec> list);
}
