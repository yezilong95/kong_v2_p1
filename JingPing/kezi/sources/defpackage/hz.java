package defpackage;

import android.app.job.JobParameters;
import android.app.job.JobWorkItem;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public final class hz implements gz {
    public final JobWorkItem a;
    public final /* synthetic */ iz b;

    public hz(iz izVar, JobWorkItem jobWorkItem) {
        this.b = izVar;
        this.a = jobWorkItem;
    }

    @Override // defpackage.gz
    public final void a() {
        synchronized (this.b.b) {
            try {
                JobParameters jobParameters = this.b.c;
                if (jobParameters != null) {
                    jobParameters.completeWork(this.a);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // defpackage.gz
    public final Intent getIntent() {
        return this.a.getIntent();
    }
}
