package androidx.work;

import android.content.Context;
import androidx.work.Configuration;
import defpackage.f40;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WorkManagerInitializer implements f40 {
    private static final String TAG = Logger.tagWithPrefix("WrkMgrInitializer");

    @Override // defpackage.f40
    public List<Class<? extends f40>> dependencies() {
        return Collections.EMPTY_LIST;
    }

    @Override // defpackage.f40
    public WorkManager create(Context context) {
        Logger.get().debug(TAG, "Initializing WorkManager with default configuration.");
        WorkManager.initialize(context, new Configuration.Builder().build());
        return WorkManager.getInstance(context);
    }
}
