package defpackage;

import android.app.job.JobParameters;
import android.app.job.JobWorkItem;
import android.os.AsyncTask;
import androidx.core.app.JobIntentService;

/* JADX INFO: loaded from: classes.dex */
public final class h50 extends AsyncTask {
    public final /* synthetic */ JobIntentService a;

    public h50(JobIntentService jobIntentService) {
        this.a = jobIntentService;
    }

    @Override // android.os.AsyncTask
    public final Object doInBackground(Object[] objArr) {
        k50 l50Var;
        while (true) {
            JobIntentService jobIntentService = this.a;
            m50 m50Var = jobIntentService.E;
            if (m50Var != null) {
                synchronized (m50Var.b) {
                    try {
                        JobParameters jobParameters = m50Var.c;
                        if (jobParameters != null) {
                            JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                            if (jobWorkItemDequeueWork != null) {
                                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(m50Var.a.getClassLoader());
                                l50Var = new l50(m50Var, jobWorkItemDequeueWork);
                            }
                        }
                    } finally {
                    }
                }
            } else {
                synchronized (jobIntentService.I) {
                    try {
                        l50Var = jobIntentService.I.size() > 0 ? (k50) jobIntentService.I.remove(0) : null;
                    } finally {
                    }
                }
            }
            if (l50Var == null) {
                return null;
            }
            JobIntentService jobIntentService2 = this.a;
            l50Var.getIntent();
            jobIntentService2.b();
            l50Var.a();
        }
    }

    @Override // android.os.AsyncTask
    public final void onCancelled(Object obj) {
        this.a.c();
    }

    @Override // android.os.AsyncTask
    public final void onPostExecute(Object obj) {
        this.a.c();
    }
}
