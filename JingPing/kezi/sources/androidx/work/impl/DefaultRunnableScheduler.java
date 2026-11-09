package androidx.work.impl;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.work.RunnableScheduler;
import defpackage.xj;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class DefaultRunnableScheduler implements RunnableScheduler {
    private final Handler mHandler;

    public DefaultRunnableScheduler() {
        Handler handler;
        Handler handlerB;
        Looper mainLooper = Looper.getMainLooper();
        if (Build.VERSION.SDK_INT >= 28) {
            handlerB = xj.b(mainLooper);
        } else {
            try {
                handler = (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(mainLooper, null, Boolean.TRUE);
            } catch (IllegalAccessException e) {
                e = e;
                Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
                handler = new Handler(mainLooper);
            } catch (InstantiationException e2) {
                e = e2;
                Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
                handler = new Handler(mainLooper);
            } catch (NoSuchMethodException e3) {
                e = e3;
                Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
                handler = new Handler(mainLooper);
            } catch (InvocationTargetException e4) {
                Throwable cause = e4.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                if (!(cause instanceof Error)) {
                    throw new RuntimeException(cause);
                }
                throw ((Error) cause);
            }
            handlerB = handler;
        }
        this.mHandler = handlerB;
    }

    @Override // androidx.work.RunnableScheduler
    public void cancel(Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    @Override // androidx.work.RunnableScheduler
    public void scheduleWithDelay(long j, Runnable runnable) {
        this.mHandler.postDelayed(runnable, j);
    }

    public DefaultRunnableScheduler(Handler handler) {
        this.mHandler = handler;
    }
}
