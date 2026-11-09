package defpackage;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

/* JADX INFO: loaded from: classes.dex */
public final class oc0 implements OnBackAnimationCallback {
    public final /* synthetic */ mc0 a;
    public final /* synthetic */ pc0 b;

    public oc0(pc0 pc0Var, mc0 mc0Var) {
        this.b = pc0Var;
        this.a = mc0Var;
    }

    public final void onBackCancelled() {
        if (this.b.a != null) {
            this.a.d();
        }
    }

    public final void onBackInvoked() {
        this.a.a();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        if (this.b.a != null) {
            this.a.b(new l9(backEvent));
        }
    }

    public final void onBackStarted(BackEvent backEvent) {
        if (this.b.a != null) {
            this.a.c(new l9(backEvent));
        }
    }
}
