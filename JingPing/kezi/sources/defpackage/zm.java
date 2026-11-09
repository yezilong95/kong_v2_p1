package defpackage;

import org.webrtc.EglThread;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zm implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ EglThread c;

    public /* synthetic */ zm(EglThread eglThread, int i) {
        this.b = i;
        this.c = eglThread;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                this.c.lambda$onRenderWindowOpen$2();
                break;
            default:
                this.c.lambda$onRenderWindowClose$3();
                break;
        }
    }
}
