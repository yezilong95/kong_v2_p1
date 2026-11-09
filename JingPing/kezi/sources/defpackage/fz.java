package defpackage;

import android.content.Intent;
import androidx.core.app.JobIntentService;

/* JADX INFO: loaded from: classes.dex */
public final class fz implements gz {
    public final Intent a;
    public final int b;
    public final /* synthetic */ JobIntentService c;

    public fz(JobIntentService jobIntentService, Intent intent, int i) {
        this.c = jobIntentService;
        this.a = intent;
        this.b = i;
    }

    @Override // defpackage.gz
    public final void a() {
        this.c.stopSelf(this.b);
    }

    @Override // defpackage.gz
    public final Intent getIntent() {
        return this.a;
    }
}
