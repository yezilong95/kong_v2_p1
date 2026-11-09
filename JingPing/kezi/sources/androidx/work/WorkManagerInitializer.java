package androidx.work;

import android.content.Context;
import androidx.work.Configuration;
import defpackage.cy;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WorkManagerInitializer implements cy {
    private static final String TAG = Logger.tagWithPrefix("WrkMgrInitializer");

    @Override // defpackage.cy
    public List<Class<? extends cy>> dependencies() {
        return Collections.EMPTY_LIST;
    }

    @Override // defpackage.cy
    public WorkManager create(Context context) {
        Logger.get().debug(TAG, "Initializing WorkManager with default configuration.");
        WorkManager.initialize(context, new Configuration.Builder().build());
        return WorkManager.getInstance(context);
    }
}
