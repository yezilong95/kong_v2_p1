package defpackage;

import org.webrtc.PeerConnection;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class n71 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[PeerConnection.IceConnectionState.values().length];
        a = iArr;
        try {
            iArr[PeerConnection.IceConnectionState.DISCONNECTED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[PeerConnection.IceConnectionState.FAILED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[PeerConnection.IceConnectionState.CONNECTED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
