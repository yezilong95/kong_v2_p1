package org.webrtc;

/* JADX INFO: loaded from: classes.dex */
public interface RefCounted {
    @CalledByNative
    void release();

    @CalledByNative
    void retain();
}
