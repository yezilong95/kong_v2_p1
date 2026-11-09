package defpackage;

import org.webrtc.RenderSynchronizer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lo0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ RenderSynchronizer F;

    public /* synthetic */ lo0(RenderSynchronizer renderSynchronizer, int i) {
        this.E = i;
        this.F = renderSynchronizer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                this.F.lambda$registerListener$1();
                break;
            default:
                this.F.lambda$new$0();
                break;
        }
    }
}
