package defpackage;

import android.os.Handler;
import android.os.Looper;
import com.mamimobi.videostream.model.CandidateMessage;
import com.mamimobi.videostream.model.MessageType;
import com.mamimobi.videostream.model.SignalingMessage;
import com.mamimobi.videostream.model.WebCandidate;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.RtpReceiver;
import org.webrtc.RtpTransceiver;

/* JADX INFO: loaded from: classes.dex */
public final class m71 implements PeerConnection.Observer {
    public final Handler a = new Handler(Looper.getMainLooper());
    public uv0 b;
    public final /* synthetic */ o71 c;

    public m71(o71 o71Var) {
        this.c = o71Var;
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onDataChannel(DataChannel dataChannel) {
        this.c.l = dataChannel;
        dataChannel.registerObserver(new l71(this, dataChannel));
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onIceCandidate(IceCandidate iceCandidate) {
        o71 o71Var = this.c;
        lm0 lm0Var = o71Var.e;
        String str = o71Var.i.name;
        lm0Var.getClass();
        CandidateMessage candidateMessage = new CandidateMessage();
        candidateMessage.id = MessageType.CANDIDATE.getType();
        candidateMessage.name = str;
        WebCandidate webCandidate = new WebCandidate();
        webCandidate.sdpMid = iceCandidate.sdpMid;
        webCandidate.sdpMLineIndex = iceCandidate.sdpMLineIndex;
        webCandidate.candidate = iceCandidate.sdp;
        candidateMessage.candidate = webCandidate;
        lm0Var.e(candidateMessage);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
        o71 o71Var = this.c;
        String str = o71Var.j;
        lm0 lm0Var = o71Var.e;
        if (str != null) {
            lm0Var.d(str, iceConnectionState);
        } else {
            SignalingMessage signalingMessage = o71Var.i;
            if (signalingMessage != null) {
                lm0Var.d(signalingMessage.name, iceConnectionState);
            }
        }
        int i = n71.a[iceConnectionState.ordinal()];
        Handler handler = this.a;
        if (i == 1) {
            uv0 uv0Var = this.b;
            if (uv0Var != null) {
                handler.removeCallbacks(uv0Var);
            }
            uv0 uv0Var2 = new uv0(10, this);
            this.b = uv0Var2;
            handler.postDelayed(uv0Var2, 5000L);
            return;
        }
        if (i == 2) {
            uv0 uv0Var3 = this.b;
            if (uv0Var3 != null) {
                handler.removeCallbacks(uv0Var3);
                this.b = null;
            }
            o71Var.g();
            return;
        }
        if (i != 3) {
            return;
        }
        g71 g71Var = o71Var.s;
        Handler handler2 = o71Var.r;
        if (g71Var != null) {
            handler2.removeCallbacks(g71Var);
            o71Var.s = null;
        }
        g71 g71Var2 = new g71(o71Var);
        o71Var.s = g71Var2;
        handler2.postDelayed(g71Var2, 25000L);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onRenegotiationNeeded() {
        o71.x.set(true);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onSignalingChange(PeerConnection.SignalingState signalingState) {
        this.c.t = signalingState;
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onAddStream(MediaStream mediaStream) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onIceConnectionReceivingChange(boolean z) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onRemoveStream(MediaStream mediaStream) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onTrack(RtpTransceiver rtpTransceiver) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public final void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
    }
}
