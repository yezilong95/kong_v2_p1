package defpackage;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import java.lang.reflect.InvocationTargetException;
import kotlin.Result;
import kotlin.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class v20 {
    private static volatile Choreographer choreographer;

    static {
        Object objM3constructorimpl;
        try {
            objM3constructorimpl = Result.m3constructorimpl(new u20(a(Looper.getMainLooper()), false));
        } catch (Throwable th) {
            objM3constructorimpl = Result.m3constructorimpl(a.a(th));
        }
        if (Result.m8isFailureimpl(objM3constructorimpl)) {
            objM3constructorimpl = null;
        }
    }

    public static final Handler a(Looper looper) throws IllegalAccessException, InvocationTargetException {
        if (Build.VERSION.SDK_INT >= 28) {
            Object objInvoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
            t40.l(objInvoke, "null cannot be cast to non-null type android.os.Handler");
            return (Handler) objInvoke;
        }
        try {
            return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
        } catch (NoSuchMethodException unused) {
            return new Handler(looper);
        }
    }
}
