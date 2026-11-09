package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class xm0 {
    public static WindowManager b;
    public static WeakReference c;
    public static final Handler a = new Handler(Looper.getMainLooper());
    public static final AtomicBoolean d = new AtomicBoolean(false);
    public static final HashSet e = new HashSet();

    public static void a() {
        HashSet hashSet = e;
        AtomicBoolean atomicBoolean = d;
        try {
            try {
                WeakReference weakReference = c;
                View view = weakReference != null ? (View) weakReference.get() : null;
                if (view != null) {
                    if (b != null) {
                        view.setOnTouchListener(null);
                        b.removeViewImmediate(view);
                    }
                    c.clear();
                }
                c = null;
                b = null;
                atomicBoolean.set(false);
                hashSet.clear();
            } catch (Exception e2) {
                e2.printStackTrace();
                c = null;
                b = null;
                atomicBoolean.set(false);
                hashSet.clear();
            }
        } catch (Throwable th) {
            c = null;
            b = null;
            atomicBoolean.set(false);
            hashSet.clear();
            throw th;
        }
    }
}
