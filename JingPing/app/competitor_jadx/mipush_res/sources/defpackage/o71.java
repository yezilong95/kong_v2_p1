package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import com.mamimobi.videostream.VideoSourceType;
import com.mamimobi.videostream.model.SignalingMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.webrtc.DataChannel;
import org.webrtc.EglBase;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpSender;
import org.webrtc.RtpTransceiver;
import org.webrtc.SessionDescription;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes.dex */
public final class o71 {
    public static o71 v;
    public static final ConcurrentHashMap w = new ConcurrentHashMap();
    public static final AtomicBoolean x = new AtomicBoolean(false);
    public static final HashMap y = new HashMap();
    public final Context a;
    public PeerConnectionFactory b;
    public PeerConnection c;
    public final EglBase d;
    public final lm0 e;
    public final e6 f;
    public Intent g;
    public hm0 h;
    public SignalingMessage i;
    public String j;
    public volatile boolean k;
    public DataChannel l;
    public final Handler m;
    public final l1 n;
    public final PowerManager.WakeLock o;
    public long p;
    public final ExecutorService q;
    public final Handler r;
    public g71 s;
    public PeerConnection.SignalingState t;
    public final m71 u;

    public o71(Context context, EglBase eglBase) {
        Executors.newSingleThreadScheduledExecutor();
        this.k = false;
        this.m = new Handler(Looper.getMainLooper());
        this.n = new l1(13);
        this.p = 0L;
        this.q = Executors.newSingleThreadExecutor();
        this.r = new Handler(Looper.getMainLooper());
        this.t = null;
        this.u = new m71(this);
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.d = eglBase;
        PowerManager powerManager = (PowerManager) applicationContext.getSystemService("power");
        if (this.o == null) {
            this.o = powerManager.newWakeLock(536870913, "app:cpu_keepalive");
        }
        if (!this.o.isHeld()) {
            this.o.acquire();
        }
        this.e = new lm0(context, new k71(this, context));
        this.f = new e6(this, context);
    }

    public static MediaConstraints b() {
        MediaConstraints mediaConstraints = new MediaConstraints();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", String.valueOf(true)));
        arrayList.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", String.valueOf(true)));
        arrayList.add(new MediaConstraints.KeyValuePair("DtlsSrtpKeyAgreement", "true"));
        arrayList.add(new MediaConstraints.KeyValuePair("IceRestart", "true"));
        mediaConstraints.mandatory.addAll(arrayList);
        return mediaConstraints;
    }

    public static o71 f(Context context) {
        o71 o71Var = v;
        if (o71Var != null) {
            return o71Var;
        }
        synchronized (o71.class) {
            try {
                if (v == null) {
                    v = new o71(context, EglBase.create());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return v;
    }

    public final void a() {
        if (this.k) {
            return;
        }
        this.k = true;
        new Thread(new uv0(9, this)).start();
    }

    public final void c() {
        try {
            PeerConnection peerConnection = this.c;
            if (peerConnection != null && peerConnection.signalingState() == PeerConnection.SignalingState.STABLE) {
                ConcurrentHashMap concurrentHashMap = w;
                SignalingMessage signalingMessage = this.i;
                concurrentHashMap.put(signalingMessage.name, signalingMessage);
                this.h = new hm0(this.a, this.i.name);
                this.c.createOffer(this.h, b());
            }
        } catch (Exception unused) {
        }
    }

    public final void d() {
        if (this.c != null) {
            e6 e6Var = this.f;
            if (((pr0) e6Var.e).a() || ((or0) e6Var.d).b() || ((zb) e6Var.f).b()) {
                return;
            } else {
                g();
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(PeerConnection.IceServer.builder("stun:stun.l.google.com:19302").setUsername("").setPassword("").setTlsCertPolicy(PeerConnection.TlsCertPolicy.TLS_CERT_POLICY_INSECURE_NO_CHECK).createIceServer());
        arrayList.add(PeerConnection.IceServer.builder("turn:app.mamimobi.vip:3478?transport=tcp").setUsername("username1").setPassword("key1").createIceServer());
        arrayList.add(PeerConnection.IceServer.builder("turn:app.mamimobi.vip:3478?transport=udp").setUsername("username1").setPassword("key1").createIceServer());
        arrayList.add(PeerConnection.IceServer.builder("turns:app.mamimobi.vip:5349?transport=tcp").setUsername("username1").setPassword("key1").createIceServer());
        PeerConnection.RTCConfiguration rTCConfiguration = new PeerConnection.RTCConfiguration(arrayList);
        rTCConfiguration.iceTransportsType = PeerConnection.IceTransportsType.ALL;
        rTCConfiguration.tcpCandidatePolicy = PeerConnection.TcpCandidatePolicy.ENABLED;
        rTCConfiguration.enableDscp = true;
        PeerConnection peerConnectionCreatePeerConnection = this.b.createPeerConnection(rTCConfiguration, this.u);
        this.c = peerConnectionCreatePeerConnection;
        if (peerConnectionCreatePeerConnection != null) {
            DataChannel.Init init = new DataChannel.Init();
            init.ordered = true;
            init.maxRetransmits = 30;
            init.negotiated = false;
            this.l = this.c.createDataChannel("channel", init);
        }
    }

    public final String e() {
        String str = this.j;
        if (str != null) {
            return str;
        }
        SignalingMessage signalingMessage = this.i;
        if (signalingMessage != null) {
            return signalingMessage.name;
        }
        return null;
    }

    public final void g() {
        g71 g71Var = this.s;
        if (g71Var != null) {
            this.r.removeCallbacks(g71Var);
            this.s = null;
        }
        DataChannel dataChannel = this.l;
        if (dataChannel != null) {
            try {
                dataChannel.unregisterObserver();
            } catch (Exception unused) {
            }
            try {
                this.l.close();
            } catch (Exception unused2) {
            }
            try {
                this.l.dispose();
            } catch (Exception unused3) {
            }
            this.l = null;
        }
        this.g = null;
        HashMap map = y;
        for (RtpTransceiver rtpTransceiver : map.values()) {
            if (rtpTransceiver != null) {
                try {
                    RtpSender sender = rtpTransceiver.getSender();
                    if (sender != null) {
                        MediaStreamTrack mediaStreamTrackTrack = sender.track();
                        sender.setTrack(null, false);
                        if (mediaStreamTrackTrack != null) {
                            try {
                                mediaStreamTrackTrack.setEnabled(false);
                            } catch (Exception unused4) {
                            }
                        }
                    }
                    rtpTransceiver.stop();
                    rtpTransceiver.setDirection(RtpTransceiver.RtpTransceiverDirection.STOPPED);
                } catch (Exception unused5) {
                }
            }
        }
        map.clear();
        try {
            DataChannel dataChannel2 = this.l;
            if (dataChannel2 != null) {
                try {
                    dataChannel2.close();
                } catch (Exception unused6) {
                }
                this.l = null;
            }
            PeerConnection peerConnection = this.c;
            if (peerConnection != null) {
                try {
                    peerConnection.close();
                } catch (Exception unused7) {
                }
                this.c = null;
            }
        } catch (Exception unused8) {
        }
    }

    public final synchronized void h(VideoTrack videoTrack, VideoSourceType videoSourceType) {
        if (this.c == null) {
            return;
        }
        try {
            try {
                VideoSourceType videoSourceType2 = VideoSourceType.SCREENHELPER;
                if (videoSourceType == videoSourceType2) {
                    HashMap map = y;
                    RtpTransceiver rtpTransceiver = (RtpTransceiver) map.get(videoSourceType2);
                    if (rtpTransceiver == null) {
                        map.put(videoSourceType2, this.c.addTransceiver(videoTrack, new RtpTransceiver.RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.SEND_RECV)));
                        c();
                    } else {
                        AtomicBoolean atomicBoolean = x;
                        if (atomicBoolean.compareAndSet(true, false)) {
                            PeerConnection.SignalingState signalingState = this.t;
                            if (signalingState == PeerConnection.SignalingState.HAVE_LOCAL_OFFER) {
                                SessionDescription localDescription = this.c.getLocalDescription();
                                if (localDescription != null) {
                                    this.e.f(e(), localDescription);
                                } else {
                                    atomicBoolean.set(true);
                                }
                            } else if (signalingState == PeerConnection.SignalingState.STABLE) {
                                c();
                            }
                        }
                        rtpTransceiver.getSender().setTrack(videoTrack, false);
                    }
                } else {
                    HashMap map2 = y;
                    VideoSourceType videoSourceType3 = VideoSourceType.SCREEN;
                    RtpTransceiver rtpTransceiver2 = (RtpTransceiver) map2.get(videoSourceType3);
                    if (rtpTransceiver2 == null) {
                        map2.put(videoSourceType3, this.c.addTransceiver(videoTrack, new RtpTransceiver.RtpTransceiverInit(RtpTransceiver.RtpTransceiverDirection.SEND_RECV)));
                        c();
                    } else {
                        PeerConnection.SignalingState signalingState2 = this.t;
                        if (signalingState2 == PeerConnection.SignalingState.HAVE_LOCAL_OFFER) {
                            SessionDescription localDescription2 = this.c.getLocalDescription();
                            if (localDescription2 != null) {
                                this.e.f(e(), localDescription2);
                            } else {
                                x.set(true);
                            }
                        } else if (signalingState2 == PeerConnection.SignalingState.STABLE) {
                            c();
                        }
                        VideoTrack videoTrack2 = (VideoTrack) rtpTransceiver2.getSender().track();
                        if (videoTrack2 != null && videoTrack2 != videoTrack) {
                            try {
                                videoTrack2.setEnabled(false);
                            } catch (Exception unused) {
                            }
                        }
                        rtpTransceiver2.getSender().setTrack(videoTrack, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            videoTrack.setEnabled(true);
        } catch (Throwable th) {
            videoTrack.setEnabled(true);
            throw th;
        }
    }
}
