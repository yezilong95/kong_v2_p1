package androidx.work;

import androidx.work.OneTimeWorkRequest;
import defpackage.i60;
import defpackage.t40;
import defpackage.z91;

/* JADX INFO: loaded from: classes.dex */
public final class OneTimeWorkRequestKt {
    public static final <W extends ListenableWorker> OneTimeWorkRequest.Builder OneTimeWorkRequestBuilder() {
        t40.n1();
        throw null;
    }

    public static final OneTimeWorkRequest.Builder setInputMerger(OneTimeWorkRequest.Builder builder, i60 i60Var) {
        t40.n(builder, "<this>");
        t40.n(i60Var, "inputMerger");
        return builder.setInputMerger(z91.w(i60Var));
    }
}
