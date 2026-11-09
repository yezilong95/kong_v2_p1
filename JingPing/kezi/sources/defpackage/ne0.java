package defpackage;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;
import androidx.lifecycle.Lifecycle$Event;
import defpackage.pe0;

/* JADX INFO: loaded from: classes.dex */
public abstract class ne0 {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, Lifecycle$Event lifecycle$Event) {
        oy.j(lifecycle$Event, "event");
        if (activity instanceof u10) {
            p10 lifecycle = ((u10) activity).getLifecycle();
            if (lifecycle instanceof w10) {
                ((w10) lifecycle).e(lifecycle$Event);
            }
        }
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            pe0.a.Companion.getClass();
            activity.registerActivityLifecycleCallbacks(new pe0.a());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new pe0(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }
}
