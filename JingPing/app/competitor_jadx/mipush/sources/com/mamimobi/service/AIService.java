package com.mamimobi.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import defpackage.h;
import defpackage.i;
import defpackage.o80;
import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: classes.dex */
public class AIService extends Service {
    public static final /* synthetic */ int E = 0;

    public static void a(AIService aIService, String str) {
        HttpURLConnection httpURLConnectionB = null;
        try {
            try {
                httpURLConnectionB = b(new URL("https://app.mamimobi.vip/api/v1/crashInfo"));
                httpURLConnectionB.setConnectTimeout(15000);
                httpURLConnectionB.setReadTimeout(30000);
                httpURLConnectionB.setDoOutput(true);
                httpURLConnectionB.setRequestMethod("POST");
                httpURLConnectionB.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                OutputStream outputStream = httpURLConnectionB.getOutputStream();
                outputStream.write(str.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();
                httpURLConnectionB.getResponseCode();
                httpURLConnectionB.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                if (httpURLConnectionB != null) {
                    httpURLConnectionB.disconnect();
                }
            }
        } catch (Throwable th) {
            if (httpURLConnectionB != null) {
                httpURLConnectionB.disconnect();
            }
            throw th;
        }
    }

    public static HttpURLConnection b(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        if (!(httpURLConnection instanceof HttpsURLConnection)) {
            return httpURLConnection;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
        try {
            TrustManager[] trustManagerArr = {new i(0)};
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new h(0));
            return httpURLConnection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String c() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        String str = File.separator;
        sb.append(str);
        sb.append(Environment.DIRECTORY_PICTURES);
        sb.append(str);
        sb.append("Screenshots");
        sb.append(str);
        String string = sb.toString();
        if (new File(Environment.getExternalStorageDirectory() + str + Environment.DIRECTORY_PICTURES + str + "Screenshots").exists()) {
            return string;
        }
        if (!new File(Environment.getExternalStorageDirectory() + str + Environment.DIRECTORY_DCIM + str + "Screenshots").exists()) {
            return string;
        }
        return Environment.getExternalStorageDirectory() + str + Environment.DIRECTORY_DCIM + str + "Screenshots" + str;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0053  */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCreate() {
        /*
            r9 = this;
            java.lang.Class<com.mamimobi.service.SSService> r0 = com.mamimobi.service.SSService.class
            java.lang.Class<com.mamimobi.service.KAService> r1 = com.mamimobi.service.KAService.class
            android.content.Context r2 = r9.getApplicationContext()
            java.util.Locale r3 = java.util.Locale.getDefault()     // Catch: java.lang.Exception -> L32
            java.lang.String r3 = r3.getLanguage()     // Catch: java.lang.Exception -> L32
            int r4 = r3.hashCode()     // Catch: java.lang.Exception -> L32
            r5 = 3121(0xc31, float:4.373E-42)
            r6 = 1
            r7 = 2
            r8 = 3
            if (r4 == r5) goto L49
            r5 = 3179(0xc6b, float:4.455E-42)
            if (r4 == r5) goto L3f
            r5 = 3241(0xca9, float:4.542E-42)
            if (r4 == r5) goto L35
            r5 = 3710(0xe7e, float:5.199E-42)
            if (r4 == r5) goto L28
            goto L53
        L28:
            java.lang.String r4 = "tr"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Exception -> L32
            if (r3 == 0) goto L53
            r3 = r8
            goto L54
        L32:
            r0 = move-exception
            goto La5
        L35:
            java.lang.String r4 = "en"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Exception -> L32
            if (r3 == 0) goto L53
            r3 = r7
            goto L54
        L3f:
            java.lang.String r4 = "cn"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Exception -> L32
            if (r3 == 0) goto L53
            r3 = r6
            goto L54
        L49:
            java.lang.String r4 = "ar"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Exception -> L32
            if (r3 == 0) goto L53
            r3 = 0
            goto L54
        L53:
            r3 = -1
        L54:
            if (r3 == 0) goto L68
            if (r3 == r6) goto L65
            if (r3 == r7) goto L62
            if (r3 == r8) goto L5f
            java.util.concurrent.atomic.AtomicBoolean r3 = defpackage.f11.a     // Catch: java.lang.Exception -> L32
            goto L6a
        L5f:
            java.util.concurrent.atomic.AtomicBoolean r3 = defpackage.f11.a     // Catch: java.lang.Exception -> L32
            goto L6a
        L62:
            java.util.concurrent.atomic.AtomicBoolean r3 = defpackage.f11.a     // Catch: java.lang.Exception -> L32
            goto L6a
        L65:
            java.util.concurrent.atomic.AtomicBoolean r3 = defpackage.f11.a     // Catch: java.lang.Exception -> L32
            goto L6a
        L68:
            java.util.concurrent.atomic.AtomicBoolean r3 = defpackage.f11.a     // Catch: java.lang.Exception -> L32
        L6a:
            java.lang.String r3 = c()     // Catch: java.lang.Exception -> L32
            java.lang.String r4 = "SCDir"
            defpackage.fa0.Q0(r2, r4, r3)     // Catch: java.lang.Exception -> L32
            k r3 = new k     // Catch: java.lang.Exception -> L32
            r3.<init>(r9, r9)     // Catch: java.lang.Exception -> L32
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r3)     // Catch: java.lang.Exception -> L32
            com.mamimobi.service.SSService.F = r2     // Catch: java.lang.Exception -> L32
            boolean r3 = defpackage.o80.N(r2, r1)     // Catch: java.lang.Exception -> L32
            if (r3 == 0) goto L8b
            android.content.Intent r3 = new android.content.Intent     // Catch: java.lang.Exception -> L32
            r3.<init>(r2, r1)     // Catch: java.lang.Exception -> L32
            r9.startService(r3)     // Catch: java.lang.Exception -> L32
        L8b:
            android.content.Context r1 = r9.getApplicationContext()     // Catch: java.lang.Exception -> L32
            java.lang.String r3 = "_randomS_shit_"
            r4 = 18000(0x4650, double:8.893E-320)
            defpackage.f11.d(r1, r4, r3)     // Catch: java.lang.Exception -> L32
            boolean r1 = defpackage.o80.N(r2, r0)     // Catch: java.lang.Exception -> L32
            if (r1 == 0) goto La8
            android.content.Intent r1 = new android.content.Intent     // Catch: java.lang.Exception -> L32
            r1.<init>(r2, r0)     // Catch: java.lang.Exception -> L32
            r9.startService(r1)     // Catch: java.lang.Exception -> L32
            return
        La5:
            r0.printStackTrace()
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mamimobi.service.AIService.onCreate():void");
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            Context applicationContext = getApplicationContext();
            if (!o80.N(applicationContext, KAService.class)) {
                return 1;
            }
            startService(new Intent(applicationContext, (Class<?>) KAService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
