package defpackage;

import org.webrtc.SurfaceTextureHelper;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ly0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ SurfaceTextureHelper F;

    public /* synthetic */ ly0(SurfaceTextureHelper surfaceTextureHelper, int i) {
        this.E = i;
        this.F = surfaceTextureHelper;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                this.F.lambda$stopListening$1();
                break;
            case 1:
                this.F.lambda$dispose$6();
                break;
            case 2:
                this.F.lambda$returnTextureFrame$5();
                break;
            default:
                this.F.lambda$forceFrame$3();
                break;
        }
    }
}
