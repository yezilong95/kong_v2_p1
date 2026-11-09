package defpackage;

import android.os.Process;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class w6 implements Thread.UncaughtExceptionHandler {
    public Thread.UncaughtExceptionHandler a;
    public ExecutorService b;

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.a;
        th.printStackTrace();
        thread.getName();
        this.b.execute(new v6(0, th));
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }
}
