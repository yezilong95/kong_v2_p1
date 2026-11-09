package org.webrtc;

import org.webrtc.VideoDecoder;

/* JADX INFO: loaded from: classes.dex */
class VideoDecoderWrapper {
    @CalledByNative
    public static VideoDecoder.Callback createDecoderCallback(long j) {
        return new e(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnDecodedFrame(long j, VideoFrame videoFrame, Integer num, Integer num2);
}
