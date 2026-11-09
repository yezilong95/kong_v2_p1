package androidx.work;

import androidx.work.OneTimeWorkRequest;
import defpackage.jq0;
import defpackage.oy;
import defpackage.tz;

/* JADX INFO: loaded from: classes.dex */
public final class OneTimeWorkRequestKt {
    public static final <W extends ListenableWorker> OneTimeWorkRequest.Builder OneTimeWorkRequestBuilder() {
        oy.R();
        throw null;
    }

    public static final OneTimeWorkRequest.Builder setInputMerger(OneTimeWorkRequest.Builder builder, tz tzVar) {
        oy.j(builder, "<this>");
        oy.j(tzVar, "inputMerger");
        return builder.setInputMerger(jq0.x(tzVar));
    }
}
