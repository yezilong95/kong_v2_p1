package defpackage;

import org.webrtc.SurfaceTextureHelper;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xl0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ SurfaceTextureHelper c;

    public /* synthetic */ xl0(SurfaceTextureHelper surfaceTextureHelper, int i) {
        this.b = i;
        this.c = surfaceTextureHelper;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                this.c.lambda$stopListening$1();
                break;
            case 1:
                this.c.lambda$dispose$6();
                break;
            case 2:
                this.c.lambda$returnTextureFrame$5();
                break;
            default:
                this.c.lambda$forceFrame$3();
                break;
        }
    }
}
