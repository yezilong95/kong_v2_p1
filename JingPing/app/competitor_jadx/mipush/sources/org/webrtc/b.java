package org.webrtc;

import org.webrtc.Camera1Session;
import org.webrtc.TextureBufferImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;
    public final /* synthetic */ Object G;

    public /* synthetic */ b(Object obj, int i, Object obj2) {
        this.E = i;
        this.F = obj;
        this.G = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ((Camera1Session.AnonymousClass2) this.F).lambda$onPreviewFrame$1((byte[]) this.G);
                break;
            case 1:
                ((Camera1Session.AnonymousClass2) this.F).lambda$onPreviewFrame$0((byte[]) this.G);
                break;
            default:
                ((TextureBufferImpl) this.F).lambda$new$0((TextureBufferImpl.RefCountMonitor) this.G);
                break;
        }
    }
}
