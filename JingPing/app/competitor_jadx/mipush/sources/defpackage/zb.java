package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.MediaStreamTrack;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes.dex */
public final class zb {
    public final CameraEnumerator a;
    public SurfaceTextureHelper b;
    public CameraVideoCapturer c;
    public VideoSource d;
    public VideoTrack e;
    public HandlerThread f;
    public Handler g;

    public zb(Context context) {
        Context applicationContext = context.getApplicationContext();
        String lowerCase = Build.BRAND.toLowerCase();
        CameraEnumerator camera1Enumerator = (lowerCase.contains("oppo") || lowerCase.contains("oneplus") || !Camera2Enumerator.isSupported(applicationContext)) ? new Camera1Enumerator(true) : new Camera2Enumerator(applicationContext);
        this.a = camera1Enumerator;
        for (String str : camera1Enumerator.getDeviceNames()) {
            if (!camera1Enumerator.isFrontFacing(str)) {
                camera1Enumerator.isBackFacing(str);
            }
        }
        HandlerThread handlerThread = new HandlerThread("CameraThread");
        this.f = handlerThread;
        handlerThread.start();
        this.g = new Handler(this.f.getLooper());
    }

    public final void a() {
        CameraVideoCapturer cameraVideoCapturer = this.c;
        if (cameraVideoCapturer != null) {
            try {
                cameraVideoCapturer.stopCapture();
            } catch (Exception unused) {
            }
            try {
                this.c.dispose();
            } catch (Exception unused2) {
            }
            this.c = null;
        }
        VideoSource videoSource = this.d;
        if (videoSource != null) {
            try {
                videoSource.dispose();
            } catch (Exception unused3) {
            }
            this.d = null;
        }
        VideoTrack videoTrack = this.e;
        if (videoTrack != null) {
            try {
                videoTrack.dispose();
            } catch (Exception unused4) {
            }
            this.e = null;
        }
        SurfaceTextureHelper surfaceTextureHelper = this.b;
        if (surfaceTextureHelper != null) {
            try {
                surfaceTextureHelper.dispose();
            } catch (Exception unused5) {
            }
            this.b = null;
        }
    }

    public final boolean b() {
        try {
            VideoTrack videoTrack = this.e;
            if (videoTrack == null || videoTrack.state() == MediaStreamTrack.State.ENDED) {
                return false;
            }
            return this.e.state() == MediaStreamTrack.State.LIVE;
        } catch (IllegalStateException | Exception unused) {
            return false;
        }
    }
}
