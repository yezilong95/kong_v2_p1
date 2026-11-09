package defpackage;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: classes.dex */
public abstract class of {
    public static final Handler a;

    static {
        HandlerThread handlerThread = new HandlerThread("ClickHandlerThread");
        handlerThread.start();
        a = new Handler(handlerThread.getLooper());
    }

    public static void a(Runnable runnable) {
        a.post(runnable);
    }
}
