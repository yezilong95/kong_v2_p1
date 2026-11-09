package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import org.webrtc.CapturerObserver;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;

/* JADX INFO: loaded from: classes.dex */
public final class qr0 implements VideoCapturer {
    public CapturerObserver E;
    public final Handler F;
    public final vh0 J;
    public boolean G = false;
    public int H = 12;
    public final Object I = new Object();
    public final f L = new f(22, this);
    public final m2 K = new m2();

    public qr0(Context context) {
        this.J = new vh0(context);
        HandlerThread handlerThread = new HandlerThread("capture-thread");
        handlerThread.start();
        this.F = new Handler(handlerThread.getLooper());
    }

    @Override // org.webrtc.VideoCapturer
    public final void dispose() {
        this.E = null;
        m2 m2Var = this.K;
        m2Var.e();
        m2Var.e();
        m2Var.a = null;
        m2Var.b = null;
        m2Var.e = null;
        m2Var.f = null;
        vh0 vh0Var = this.J;
        Bitmap bitmap = vh0Var.a;
        if (bitmap != null) {
            bitmap.recycle();
            vh0Var.a = null;
        }
        vh0Var.b = null;
        vh0Var.c = null;
        vh0Var.d = null;
        vh0Var.e = null;
        vh0Var.j = null;
        vh0Var.g = null;
        vh0Var.h = null;
        vh0Var.f = null;
    }

    @Override // org.webrtc.VideoCapturer
    public final void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
        this.E = capturerObserver;
    }

    @Override // org.webrtc.VideoCapturer
    public final boolean isScreencast() {
        return true;
    }

    @Override // org.webrtc.VideoCapturer
    public final void startCapture(int i, int i2, int i3) {
        synchronized (this.I) {
            this.H = i3;
            this.G = true;
            this.F.post(this.L);
        }
    }

    @Override // org.webrtc.VideoCapturer
    public final void stopCapture() {
        synchronized (this.I) {
            this.G = false;
            this.F.removeCallbacks(this.L);
            this.F.removeCallbacksAndMessages(null);
        }
    }

    @Override // org.webrtc.VideoCapturer
    public final void changeCaptureFormat(int i, int i2, int i3) {
    }
}
