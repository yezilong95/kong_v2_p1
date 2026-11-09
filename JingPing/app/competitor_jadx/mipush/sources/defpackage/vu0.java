package defpackage;

import android.os.Handler;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.LifecycleService;

/* JADX INFO: loaded from: classes.dex */
public final class vu0 {
    public final z80 a;
    public final Handler b = new Handler();
    public uu0 c;

    public vu0(LifecycleService lifecycleService) {
        this.a = new z80(lifecycleService);
    }

    public final void a(Lifecycle$Event lifecycle$Event) {
        uu0 uu0Var = this.c;
        if (uu0Var != null) {
            uu0Var.run();
        }
        uu0 uu0Var2 = new uu0(this.a, lifecycle$Event);
        this.c = uu0Var2;
        this.b.postAtFrontOfQueue(uu0Var2);
    }
}
