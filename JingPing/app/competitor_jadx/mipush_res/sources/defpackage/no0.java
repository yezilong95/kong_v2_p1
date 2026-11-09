package defpackage;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;
import androidx.lifecycle.Lifecycle$Event;
import defpackage.po0;

/* JADX INFO: loaded from: classes.dex */
public abstract class no0 {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, Lifecycle$Event lifecycle$Event) {
        t40.n(lifecycle$Event, "event");
        if (activity instanceof x80) {
            s80 lifecycle = ((x80) activity).getLifecycle();
            if (lifecycle instanceof z80) {
                ((z80) lifecycle).e(lifecycle$Event);
            }
        }
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            po0.a.Companion.getClass();
            activity.registerActivityLifecycleCallbacks(new po0.a());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new po0(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }
}
