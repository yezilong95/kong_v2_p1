package org.webrtc;

import org.webrtc.Camera1Session;
import org.webrtc.TextureBufferImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ b(Object obj, int i, Object obj2) {
        this.b = i;
        this.c = obj;
        this.d = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                ((Camera1Session.AnonymousClass2) this.c).lambda$onPreviewFrame$1((byte[]) this.d);
                break;
            case 1:
                ((Camera1Session.AnonymousClass2) this.c).lambda$onPreviewFrame$0((byte[]) this.d);
                break;
            default:
                ((TextureBufferImpl) this.c).lambda$new$0((TextureBufferImpl.RefCountMonitor) this.d);
                break;
        }
    }
}
