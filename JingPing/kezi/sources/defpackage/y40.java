package defpackage;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

/* JADX INFO: loaded from: classes.dex */
public final class y40 implements OnBackAnimationCallback {
    public final /* synthetic */ w40 a;
    public final /* synthetic */ z40 b;

    public y40(z40 z40Var, w40 w40Var) {
        this.b = z40Var;
        this.a = w40Var;
    }

    public final void onBackCancelled() {
        if (this.b.a != null) {
            this.a.d();
        }
    }

    public final void onBackInvoked() {
        this.a.b();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        if (this.b.a != null) {
            this.a.c(new r7(backEvent));
        }
    }

    public final void onBackStarted(BackEvent backEvent) {
        if (this.b.a != null) {
            this.a.a(new r7(backEvent));
        }
    }
}
