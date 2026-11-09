package defpackage;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public class pe0 extends Fragment {
    public static final /* synthetic */ int c = 0;
    public d90 b;

    public static final class a implements Application.ActivityLifecycleCallbacks {
        public static final oe0 Companion = new oe0();

        public static final void registerIn(Activity activity) {
            Companion.getClass();
            oy.j(activity, "activity");
            activity.registerActivityLifecycleCallbacks(new a());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            oy.j(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            oy.j(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            oy.j(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            oy.j(activity, "activity");
            int i = pe0.c;
            ne0.a(activity, Lifecycle$Event.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            oy.j(activity, "activity");
            int i = pe0.c;
            ne0.a(activity, Lifecycle$Event.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            oy.j(activity, "activity");
            int i = pe0.c;
            ne0.a(activity, Lifecycle$Event.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            oy.j(activity, "activity");
            int i = pe0.c;
            ne0.a(activity, Lifecycle$Event.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            oy.j(activity, "activity");
            int i = pe0.c;
            ne0.a(activity, Lifecycle$Event.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            oy.j(activity, "activity");
            int i = pe0.c;
            ne0.a(activity, Lifecycle$Event.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            oy.j(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            oy.j(activity, "activity");
            oy.j(bundle, "bundle");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            oy.j(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            oy.j(activity, "activity");
        }
    }

    public final void a(Lifecycle$Event lifecycle$Event) {
        if (Build.VERSION.SDK_INT < 29) {
            Activity activity = getActivity();
            oy.i(activity, "getActivity(...)");
            ne0.a(activity, lifecycle$Event);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(Lifecycle$Event.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        a(Lifecycle$Event.ON_DESTROY);
        this.b = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(Lifecycle$Event.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        d90 d90Var = this.b;
        if (d90Var != null) {
            ((ec0) d90Var.b).a();
        }
        a(Lifecycle$Event.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        d90 d90Var = this.b;
        if (d90Var != null) {
            ec0 ec0Var = (ec0) d90Var.b;
            int i = ec0Var.b + 1;
            ec0Var.b = i;
            if (i == 1 && ec0Var.e) {
                ec0Var.g.e(Lifecycle$Event.ON_START);
                ec0Var.e = false;
            }
        }
        a(Lifecycle$Event.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        a(Lifecycle$Event.ON_STOP);
    }
}
