package defpackage;

import org.webrtc.EglBase10Impl;
import org.webrtc.EglBase14Impl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class tm implements Runnable {
    public final /* synthetic */ int b;

    public /* synthetic */ tm(int i) {
        this.b = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                EglBase10Impl.EglConnection.lambda$new$1();
                break;
            default:
                EglBase14Impl.EglConnection.lambda$new$1();
                break;
        }
    }
}
