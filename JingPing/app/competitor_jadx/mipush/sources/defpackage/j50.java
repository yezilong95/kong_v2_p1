package defpackage;

import android.content.Intent;
import androidx.core.app.JobIntentService;

/* JADX INFO: loaded from: classes.dex */
public final class j50 implements k50 {
    public final Intent a;
    public final int b;
    public final /* synthetic */ JobIntentService c;

    public j50(JobIntentService jobIntentService, Intent intent, int i) {
        this.c = jobIntentService;
        this.a = intent;
        this.b = i;
    }

    @Override // defpackage.k50
    public final void a() {
        this.c.stopSelf(this.b);
    }

    @Override // defpackage.k50
    public final Intent getIntent() {
        return this.a;
    }
}
