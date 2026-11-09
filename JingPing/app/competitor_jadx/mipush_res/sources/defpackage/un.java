package defpackage;

import androidx.work.impl.background.systemalarm.DelayMetCommandHandler;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class un implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ DelayMetCommandHandler F;

    public /* synthetic */ un(DelayMetCommandHandler delayMetCommandHandler, int i) {
        this.E = i;
        this.F = delayMetCommandHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                this.F.stopWork();
                break;
            default:
                this.F.startWork();
                break;
        }
    }
}
