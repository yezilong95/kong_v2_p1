package org.webrtc;

import org.webrtc.Camera2Session;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements VideoSink {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ a(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // org.webrtc.VideoSink
    public final void onFrame(VideoFrame videoFrame) {
        switch (this.E) {
            case 0:
                ((Camera1Session) this.F).lambda$listenForTextureFrames$0(videoFrame);
                break;
            default:
                ((Camera2Session.CaptureSessionCallback) this.F).lambda$onConfigured$0(videoFrame);
                break;
        }
    }
}
