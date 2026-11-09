package defpackage;

import java.nio.ByteBuffer;
import org.webrtc.DataChannel;
import org.webrtc.PeerConnection;

/* JADX INFO: loaded from: classes.dex */
public final class g71 implements Runnable {
    public final /* synthetic */ o71 E;

    public g71(o71 o71Var) {
        this.E = o71Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DataChannel dataChannel;
        o71 o71Var = this.E;
        PeerConnection peerConnection = o71Var.c;
        if (peerConnection != null && ((peerConnection.iceConnectionState() == PeerConnection.IceConnectionState.CONNECTED || o71Var.c.iceConnectionState() == PeerConnection.IceConnectionState.COMPLETED) && (dataChannel = o71Var.l) != null && dataChannel.state() == DataChannel.State.OPEN)) {
            try {
                o71Var.l.send(new DataChannel.Buffer(ByteBuffer.wrap("ping".getBytes()), false));
            } catch (Exception unused) {
            }
        }
        PeerConnection peerConnection2 = o71Var.c;
        if (peerConnection2 != null) {
            if (peerConnection2.iceConnectionState() == PeerConnection.IceConnectionState.CONNECTED || o71Var.c.iceConnectionState() == PeerConnection.IceConnectionState.COMPLETED) {
                o71Var.r.postDelayed(this, 25000L);
            }
        }
    }
}
