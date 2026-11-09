package defpackage;

import android.app.job.JobParameters;
import android.app.job.JobServiceEngine;
import androidx.core.app.JobIntentService;

/* JADX INFO: loaded from: classes.dex */
public final class iz extends JobServiceEngine {
    public final JobIntentService a;
    public final Object b;
    public JobParameters c;

    public iz(JobIntentService jobIntentService) {
        super(jobIntentService);
        this.b = new Object();
        this.a = jobIntentService;
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        this.c = jobParameters;
        this.a.a(false);
        return true;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        dz dzVar = this.a.d;
        if (dzVar != null) {
            dzVar.cancel(false);
        }
        synchronized (this.b) {
            this.c = null;
        }
        return true;
    }
}
