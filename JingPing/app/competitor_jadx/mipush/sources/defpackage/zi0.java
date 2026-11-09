package defpackage;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

/* JADX INFO: loaded from: classes.dex */
public final class zi0 implements OnBackAnimationCallback {
    public final /* synthetic */ b10 a;
    public final /* synthetic */ b10 b;
    public final /* synthetic */ a10 c;
    public final /* synthetic */ a10 d;

    public zi0(b10 b10Var, b10 b10Var2, a10 a10Var, a10 a10Var2) {
        this.a = b10Var;
        this.b = b10Var2;
        this.c = a10Var;
        this.d = a10Var2;
    }

    public final void onBackCancelled() {
        this.d.invoke();
    }

    public final void onBackInvoked() {
        this.c.invoke();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        t40.n(backEvent, "backEvent");
        this.b.invoke(new l9(backEvent));
    }

    public final void onBackStarted(BackEvent backEvent) {
        t40.n(backEvent, "backEvent");
        this.a.invoke(new l9(backEvent));
    }
}
