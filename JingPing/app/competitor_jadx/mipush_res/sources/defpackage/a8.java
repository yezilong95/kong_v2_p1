package defpackage;

import android.content.Context;
import io.nats.client.support.NatsConstants;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class a8 {
    public static volatile a8 k;
    public volatile w7 a;
    public volatile Context b;
    public volatile ExecutorService e;
    public volatile ExecutorService f;
    public String i;
    public volatile String c = null;
    public volatile String d = null;
    public volatile boolean g = false;
    public volatile long h = 0;
    public final HashSet j = new HashSet();

    public static HttpURLConnection a(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        if (!(httpURLConnection instanceof HttpsURLConnection)) {
            return httpURLConnection;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
        try {
            TrustManager[] trustManagerArr = {new i(1)};
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new h(1));
            return httpURLConnection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static a8 e() {
        if (k != null) {
            return k;
        }
        synchronized (a8.class) {
            try {
                if (k == null) {
                    k = new a8();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return k;
    }

    public static void h(OutputStream outputStream, String str, File file) throws IOException {
        outputStream.write(("--" + str + "\r\nContent-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\nContent-Type: video/mp4\r\n\r\n").getBytes("UTF-8"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i == -1) {
                    bufferedInputStream.close();
                    outputStream.write(NatsConstants.CRLF.getBytes("UTF-8"));
                    return;
                }
                outputStream.write(bArr, 0, i);
            }
        } catch (Throwable th) {
            try {
                bufferedInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static void i(OutputStream outputStream, String str, String str2, String str3) throws IOException {
        outputStream.write(("--" + str + "\r\nContent-Disposition: form-data; name=\"" + str2 + "\"\r\n\r\n" + str3 + NatsConstants.CRLF).getBytes("UTF-8"));
    }

    public final void b(String str, String str2) {
        d();
        synchronized (this.j) {
            this.j.add(str);
        }
        this.f.execute(new y7(this, str, str2, 1));
    }

    public final void c() {
        if (this.e == null || this.e.isShutdown()) {
            synchronized (this) {
                this.e = Executors.newSingleThreadExecutor(new z7("Recorder-Thread", 0));
            }
        }
    }

    public final void d() {
        if (this.f == null || this.f.isShutdown()) {
            synchronized (this) {
                this.f = Executors.newSingleThreadExecutor(new z7("Upload-Thread", 0));
            }
        }
    }

    public final void f() {
        File[] fileArrListFiles;
        File externalFilesDir = this.b.getExternalFilesDir(null);
        if (externalFilesDir == null || (fileArrListFiles = externalFilesDir.listFiles(new x7())) == null) {
            return;
        }
        d();
        for (File file : fileArrListFiles) {
            String absolutePath = file.getAbsolutePath();
            String name = file.getName();
            int iLastIndexOf = name.lastIndexOf(95);
            String strSubstring = iLastIndexOf > 0 ? name.substring(0, iLastIndexOf) : "unknown";
            synchronized (this.j) {
                try {
                    if (!this.j.contains(absolutePath)) {
                        this.j.add(absolutePath);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.f.execute(new y7(this, absolutePath, strSubstring, 0));
        }
    }

    public final void g(String str, String str2) {
        File file;
        for (int i = 0; i < 3 && !Thread.currentThread().isInterrupted(); i++) {
            HttpURLConnection httpURLConnectionA = null;
            try {
                try {
                    file = new File(str);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (httpURLConnectionA != null) {
                    }
                }
                if (file.exists() && file.length() != 0) {
                    String str3 = "----FormBoundary" + System.currentTimeMillis();
                    String strV = t40.V(this.b);
                    httpURLConnectionA = a(new URL("https://app.mamimobi.vip/api/v1/appRecorder"));
                    httpURLConnectionA.setConnectTimeout(15000);
                    httpURLConnectionA.setReadTimeout(30000);
                    httpURLConnectionA.setDoOutput(true);
                    httpURLConnectionA.setRequestMethod("POST");
                    httpURLConnectionA.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str3);
                    httpURLConnectionA.setChunkedStreamingMode(0);
                    OutputStream outputStream = httpURLConnectionA.getOutputStream();
                    i(outputStream, str3, "deviceId", strV);
                    i(outputStream, str3, "pkg", str2);
                    i(outputStream, str3, "timestamp", String.valueOf(System.currentTimeMillis()));
                    i(outputStream, str3, "sessionId", this.i);
                    h(outputStream, str3, file);
                    outputStream.write(("--" + str3 + "--\r\n").getBytes());
                    outputStream.flush();
                    outputStream.close();
                    if (httpURLConnectionA.getResponseCode() == 200 && httpURLConnectionA.getInputStream() != null) {
                        httpURLConnectionA.getInputStream().close();
                        httpURLConnectionA.disconnect();
                        synchronized (this.j) {
                            this.j.remove(str);
                        }
                        new File(str).delete();
                        return;
                    }
                    httpURLConnectionA.disconnect();
                }
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException unused) {
                    return;
                }
            } catch (Throwable th) {
                if (httpURLConnectionA != null) {
                    httpURLConnectionA.disconnect();
                }
                throw th;
            }
        }
    }
}
