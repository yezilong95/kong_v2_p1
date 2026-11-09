package org.webrtc;

/* JADX INFO: loaded from: classes.dex */
public interface VideoSink {
    @CalledByNative
    void onFrame(VideoFrame videoFrame);
}
