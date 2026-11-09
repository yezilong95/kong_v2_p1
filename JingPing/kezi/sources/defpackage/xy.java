package defpackage;

import java.nio.ByteBuffer;
import org.webrtc.JniCommon;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xy implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ ByteBuffer c;

    public /* synthetic */ xy(ByteBuffer byteBuffer, int i) {
        this.b = i;
        this.c = byteBuffer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                JniCommon.nativeFreeByteBuffer(this.c);
                break;
            default:
                JniCommon.nativeFreeByteBuffer(this.c);
                break;
        }
    }
}
