package defpackage;

import android.os.Handler;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.LifecycleService;

/* JADX INFO: loaded from: classes.dex */
public final class fi0 {
    public final w10 a;
    public final Handler b = new Handler();
    public ei0 c;

    public fi0(LifecycleService lifecycleService) {
        this.a = new w10(lifecycleService);
    }

    public final void a(Lifecycle$Event lifecycle$Event) {
        ei0 ei0Var = this.c;
        if (ei0Var != null) {
            ei0Var.run();
        }
        ei0 ei0Var2 = new ei0(this.a, lifecycle$Event);
        this.c = ei0Var2;
        this.b.postAtFrontOfQueue(ei0Var2);
    }
}
