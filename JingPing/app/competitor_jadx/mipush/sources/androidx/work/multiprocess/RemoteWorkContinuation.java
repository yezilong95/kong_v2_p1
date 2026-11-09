package androidx.work.multiprocess;

import androidx.work.OneTimeWorkRequest;
import defpackage.ca0;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class RemoteWorkContinuation {
    public static RemoteWorkContinuation combine(List<RemoteWorkContinuation> list) {
        return list.get(0).combineInternal(list);
    }

    public abstract RemoteWorkContinuation combineInternal(List<RemoteWorkContinuation> list);

    public abstract ca0 enqueue();

    public final RemoteWorkContinuation then(OneTimeWorkRequest oneTimeWorkRequest) {
        return then(Collections.singletonList(oneTimeWorkRequest));
    }

    public abstract RemoteWorkContinuation then(List<OneTimeWorkRequest> list);
}
