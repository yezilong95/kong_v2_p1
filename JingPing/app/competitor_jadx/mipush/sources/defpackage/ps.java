package defpackage;

import org.webrtc.EglThread;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ps implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ EglThread F;

    public /* synthetic */ ps(EglThread eglThread, int i) {
        this.E = i;
        this.F = eglThread;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                this.F.lambda$onRenderWindowOpen$2();
                break;
            default:
                this.F.lambda$onRenderWindowClose$3();
                break;
        }
    }
}
