package org.webrtc;

import org.webrtc.Camera2Session;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements VideoSink {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ a(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // org.webrtc.VideoSink
    public final void onFrame(VideoFrame videoFrame) {
        switch (this.b) {
            case 0:
                ((Camera1Session) this.c).lambda$listenForTextureFrames$0(videoFrame);
                break;
            default:
                ((Camera2Session.CaptureSessionCallback) this.c).lambda$onConfigured$0(videoFrame);
                break;
        }
    }
}
