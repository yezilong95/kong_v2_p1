package defpackage;

import org.webrtc.MediaStreamTrack;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes.dex */
public final class pr0 {
    public qr0 a;
    public qr0 b;
    public VideoSource c;
    public VideoTrack d;

    public final boolean a() {
        try {
            VideoTrack videoTrack = this.d;
            if (videoTrack == null || videoTrack.state() == MediaStreamTrack.State.ENDED) {
                return false;
            }
            return this.d.state() == MediaStreamTrack.State.LIVE;
        } catch (IllegalStateException | Exception unused) {
            return false;
        }
    }
}
