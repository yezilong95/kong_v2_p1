package defpackage;

import androidx.work.impl.background.systemalarm.DelayMetCommandHandler;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ck implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ DelayMetCommandHandler c;

    public /* synthetic */ ck(DelayMetCommandHandler delayMetCommandHandler, int i) {
        this.b = i;
        this.c = delayMetCommandHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                this.c.stopWork();
                break;
            default:
                this.c.startWork();
                break;
        }
    }
}
