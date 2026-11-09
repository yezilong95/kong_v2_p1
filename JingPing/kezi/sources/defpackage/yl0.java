package defpackage;

import org.webrtc.SurfaceTextureHelper;
import org.webrtc.SurfaceViewRenderer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class yl0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ yl0(Object obj, int i, int i2, int i3) {
        this.b = i3;
        this.e = obj;
        this.c = i;
        this.d = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                ((SurfaceTextureHelper) this.e).lambda$setTextureSize$2(this.c, this.d);
                break;
            default:
                ((SurfaceViewRenderer) this.e).lambda$onFrameResolutionChanged$0(this.c, this.d);
                break;
        }
    }
}
