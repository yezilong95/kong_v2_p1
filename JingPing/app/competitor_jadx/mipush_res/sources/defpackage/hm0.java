package defpackage;

import android.content.Context;
import java.io.IOException;
import java.util.ArrayList;
import org.webrtc.MediaConstraints;
import org.webrtc.PeerConnection;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

/* JADX INFO: loaded from: classes.dex */
public final class hm0 implements SdpObserver {
    public final PeerConnection a;
    public final String b;
    public final lm0 c;

    public hm0(Context context, String str) {
        this.b = str;
        o71 o71VarF = o71.f(context);
        this.a = o71VarF.c;
        this.c = o71VarF.e;
    }

    @Override // org.webrtc.SdpObserver
    public final void onCreateSuccess(SessionDescription sessionDescription) {
        this.a.setLocalDescription(this, sessionDescription);
    }

    @Override // org.webrtc.SdpObserver
    public final void onSetSuccess() {
        PeerConnection peerConnection = this.a;
        if (peerConnection.signalingState() == PeerConnection.SignalingState.HAVE_REMOTE_OFFER) {
            MediaConstraints mediaConstraints = new MediaConstraints();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", String.valueOf(true)));
            arrayList.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", String.valueOf(true)));
            arrayList.add(new MediaConstraints.KeyValuePair("DtlsSrtpKeyAgreement", "true"));
            mediaConstraints.mandatory.addAll(arrayList);
            peerConnection.createAnswer(this, mediaConstraints);
            return;
        }
        if (peerConnection.signalingState() != PeerConnection.SignalingState.HAVE_LOCAL_OFFER) {
            if (peerConnection.signalingState() == PeerConnection.SignalingState.STABLE) {
                peerConnection.getLocalDescription();
            }
        } else {
            try {
                this.c.f(this.b, peerConnection.getLocalDescription());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // org.webrtc.SdpObserver
    public final void onCreateFailure(String str) {
    }

    @Override // org.webrtc.SdpObserver
    public final void onSetFailure(String str) {
    }
}
