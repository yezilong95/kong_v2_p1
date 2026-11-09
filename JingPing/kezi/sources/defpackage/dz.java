package defpackage;

import android.app.job.JobParameters;
import android.app.job.JobWorkItem;
import android.os.AsyncTask;
import androidx.core.app.JobIntentService;

/* JADX INFO: loaded from: classes.dex */
public final class dz extends AsyncTask {
    public final /* synthetic */ JobIntentService a;

    public dz(JobIntentService jobIntentService) {
        this.a = jobIntentService;
    }

    @Override // android.os.AsyncTask
    public final Object doInBackground(Object[] objArr) {
        gz hzVar;
        while (true) {
            JobIntentService jobIntentService = this.a;
            iz izVar = jobIntentService.b;
            if (izVar != null) {
                synchronized (izVar.b) {
                    try {
                        JobParameters jobParameters = izVar.c;
                        if (jobParameters != null) {
                            JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                            if (jobWorkItemDequeueWork != null) {
                                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(izVar.a.getClassLoader());
                                hzVar = new hz(izVar, jobWorkItemDequeueWork);
                            }
                        }
                    } finally {
                    }
                }
            } else {
                synchronized (jobIntentService.f) {
                    try {
                        hzVar = jobIntentService.f.size() > 0 ? (gz) jobIntentService.f.remove(0) : null;
                    } finally {
                    }
                }
            }
            if (hzVar == null) {
                return null;
            }
            JobIntentService jobIntentService2 = this.a;
            hzVar.getIntent();
            jobIntentService2.b();
            hzVar.a();
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
