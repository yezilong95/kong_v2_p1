package org.webrtc;

/* JADX INFO: loaded from: classes.dex */
public interface StatsObserver {
    @CalledByNative
    void onComplete(StatsReport[] statsReportArr);
}
