package defpackage;

import java.nio.ByteBuffer;
import org.webrtc.JniCommon;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d50 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ ByteBuffer F;

    public /* synthetic */ d50(ByteBuffer byteBuffer, int i) {
        this.E = i;
        this.F = byteBuffer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                JniCommon.nativeFreeByteBuffer(this.F);
                break;
            default:
                JniCommon.nativeFreeByteBuffer(this.F);
                break;
        }
    }
}
