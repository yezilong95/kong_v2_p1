package defpackage;

import android.accessibilityservice.GestureDescription;
import android.app.NotificationChannel;
import android.graphics.Path;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class oj0 {
    public static /* synthetic */ GestureDescription.StrokeDescription b(Path path, long j, long j2) {
        return new GestureDescription.StrokeDescription(path, j, j2, false);
    }

    public static /* synthetic */ NotificationChannel c() {
        return new NotificationChannel("video_stream_channel", "Service", 2);
    }
}
