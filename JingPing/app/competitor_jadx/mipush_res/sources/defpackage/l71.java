package defpackage;

import android.os.Handler;
import java.nio.ByteBuffer;
import org.webrtc.DataChannel;

/* JADX INFO: loaded from: classes.dex */
public final class l71 implements DataChannel.Observer {
    public final /* synthetic */ DataChannel a;
    public final /* synthetic */ m71 b;

    public l71(m71 m71Var, DataChannel dataChannel) {
        this.b = m71Var;
        this.a = dataChannel;
    }

    @Override // org.webrtc.DataChannel.Observer
    public final void onMessage(DataChannel.Buffer buffer) {
        ByteBuffer byteBuffer = buffer.data;
        if (byteBuffer != null) {
            byte[] bArr = new byte[byteBuffer.remaining()];
            buffer.data.get(bArr);
            if ("ping".equals(new String(bArr))) {
                try {
                    this.a.send(new DataChannel.Buffer(ByteBuffer.wrap("pong".getBytes()), false));
                } catch (Exception unused) {
                }
            }
        }
    }

    @Override // org.webrtc.DataChannel.Observer
    public final void onStateChange() {
        DataChannel dataChannel = this.a;
        DataChannel.State state = dataChannel.state();
        DataChannel.State state2 = DataChannel.State.OPEN;
        o71 o71Var = this.b.c;
        if (state == state2) {
            g71 g71Var = o71Var.s;
            Handler handler = o71Var.r;
            if (g71Var != null) {
                handler.removeCallbacks(g71Var);
                o71Var.s = null;
            }
            g71 g71Var2 = new g71(o71Var);
            o71Var.s = g71Var2;
            handler.postDelayed(g71Var2, 25000L);
            return;
        }
        if (dataChannel.state() == DataChannel.State.CLOSED) {
            o71 o71Var2 = o71.v;
            g71 g71Var3 = o71Var.s;
            if (g71Var3 != null) {
                o71Var.r.removeCallbacks(g71Var3);
                o71Var.s = null;
            }
        }
    }

    @Override // org.webrtc.DataChannel.Observer
    public final void onBufferedAmountChange(long j) {
    }
}
