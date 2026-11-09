package org.webrtc;

import org.webrtc.VideoDecoder;
import org.webrtc.VideoEncoder;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements VideoDecoder.Callback, VideoEncoder.Callback {
    public final /* synthetic */ long a;

    public /* synthetic */ e(long j) {
        this.a = j;
    }

    @Override // org.webrtc.VideoDecoder.Callback
    public void onDecodedFrame(VideoFrame videoFrame, Integer num, Integer num2) {
        VideoDecoderWrapper.nativeOnDecodedFrame(this.a, videoFrame, num, num2);
    }

    @Override // org.webrtc.VideoEncoder.Callback
    public void onEncodedFrame(EncodedImage encodedImage, VideoEncoder.CodecSpecificInfo codecSpecificInfo) {
        VideoEncoderWrapper.nativeOnEncodedFrame(this.a, encodedImage);
    }
}
