package defpackage;

import org.webrtc.SurfaceTextureHelper;
import org.webrtc.SurfaceViewRenderer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class my0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ Object H;

    public /* synthetic */ my0(Object obj, int i, int i2, int i3) {
        this.E = i3;
        this.H = obj;
        this.F = i;
        this.G = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ((SurfaceTextureHelper) this.H).lambda$setTextureSize$2(this.F, this.G);
                break;
            default:
                ((SurfaceViewRenderer) this.H).lambda$onFrameResolutionChanged$0(this.F, this.G);
                break;
        }
    }
}
