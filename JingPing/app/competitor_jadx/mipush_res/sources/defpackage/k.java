package defpackage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import com.mamimobi.receiver.RSReceiver;
import com.mamimobi.service.AIService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class k implements Thread.UncaughtExceptionHandler {
    public final /* synthetic */ int a = 1;
    public Object b;
    public Object c;

    public /* synthetic */ k() {
    }

    public String a(Thread thread, String str) {
        String str2;
        StringBuilder sb = new StringBuilder("Thread: ");
        sb.append(thread.getName());
        sb.append("\nTime: ");
        sb.append(System.currentTimeMillis());
        sb.append("\nStack Trace:\n");
        sb.append(str);
        sb.append("\n\nDevice Info:\nDeviceId: ");
        AIService aIService = (AIService) this.c;
        sb.append(t40.V(aIService));
        sb.append("\nPkgName: ");
        sb.append(aIService.getPackageName());
        sb.append("\nVersion: ");
        try {
            str2 = aIService.getPackageManager().getPackageInfo(aIService.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            str2 = "";
        }
        sb.append(str2);
        sb.append("\nModel: ");
        sb.append(Build.MODEL);
        sb.append("\nAndroid Version: ");
        sb.append(Build.VERSION.RELEASE);
        sb.append("\nSDK: ");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("\n");
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Thread$UncaughtExceptionHandler] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r6v11, types: [int] */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.lang.Thread] */
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        switch (this.a) {
            case 0:
                AIService aIService = (AIService) this.c;
                AIService aIService2 = (AIService) this.b;
                try {
                    StringWriter stringWriter = new StringWriter();
                    th.printStackTrace(new PrintWriter(stringWriter));
                    String strA = a(thread, stringWriter.toString());
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    new Thread(new j(this, strA, countDownLatch, 0)).start();
                    countDownLatch.await(2000L, TimeUnit.MILLISECONDS);
                    break;
                } catch (Exception unused) {
                }
                try {
                    f11.d(aIService2, 180000L, "Error");
                    aIService.sendBroadcast(new Intent(aIService.getApplicationContext(), (Class<?>) RSReceiver.class).setAction("RestartSensor"));
                    if (Build.VERSION.SDK_INT >= 26) {
                        t40.j1(aIService2, new Intent(aIService2, (Class<?>) AIService.class));
                        return;
                    } else {
                        aIService.startService(new Intent(aIService2, (Class<?>) AIService.class));
                        return;
                    }
                } catch (Exception unused2) {
                    return;
                }
            default:
                ?? r0 = (Thread.UncaughtExceptionHandler) this.b;
                th.printStackTrace();
                thread.getName();
                ((ExecutorService) this.c).execute(new f(3, th));
                try {
                    try {
                        Thread.sleep(1000L);
                    } finally {
                        r0.uncaughtException(thread, th);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                    }
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
        }
    }

    public k(AIService aIService, AIService aIService2) {
        this.c = aIService;
        this.b = aIService2;
    }
}
