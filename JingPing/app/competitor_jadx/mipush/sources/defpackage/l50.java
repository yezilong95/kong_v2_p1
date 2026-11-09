package defpackage;

import android.app.job.JobParameters;
import android.app.job.JobWorkItem;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public final class l50 implements k50 {
    public final JobWorkItem a;
    public final /* synthetic */ m50 b;

    public l50(m50 m50Var, JobWorkItem jobWorkItem) {
        this.b = m50Var;
        this.a = jobWorkItem;
    }

    @Override // defpackage.k50
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

    @Override // defpackage.k50
    public final Intent getIntent() {
        return this.a.getIntent();
    }
}
