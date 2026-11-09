package defpackage;

import org.webrtc.RenderSynchronizer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class le0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ RenderSynchronizer c;

    public /* synthetic */ le0(RenderSynchronizer renderSynchronizer, int i) {
        this.b = i;
        this.c = renderSynchronizer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                this.c.lambda$registerListener$1();
                break;
            default:
                this.c.lambda$new$0();
                break;
        }
    }
}
