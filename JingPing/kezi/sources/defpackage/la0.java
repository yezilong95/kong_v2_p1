package defpackage;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

/* JADX INFO: loaded from: classes.dex */
public final class la0 implements OnBackAnimationCallback {
    public final /* synthetic */ pu a;
    public final /* synthetic */ pu b;
    public final /* synthetic */ ou c;
    public final /* synthetic */ ou d;

    public la0(pu puVar, pu puVar2, ou ouVar, ou ouVar2) {
        this.a = puVar;
        this.b = puVar2;
        this.c = ouVar;
        this.d = ouVar2;
    }

    public final void onBackCancelled() {
        this.d.invoke();
    }

    public final void onBackInvoked() {
        this.c.invoke();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        oy.j(backEvent, "backEvent");
        this.b.invoke(new r7(backEvent));
    }

    public final void onBackStarted(BackEvent backEvent) {
        oy.j(backEvent, "backEvent");
        this.a.invoke(new r7(backEvent));
    }
}
