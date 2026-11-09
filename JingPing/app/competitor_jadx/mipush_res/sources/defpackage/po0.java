package defpackage;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public class po0 extends Fragment {
    public static final /* synthetic */ int F = 0;
    public z8 E;

    public static final class a implements Application.ActivityLifecycleCallbacks {
        public static final oo0 Companion = new oo0();

        public static final void registerIn(Activity activity) {
            Companion.getClass();
            t40.n(activity, "activity");
            activity.registerActivityLifecycleCallbacks(new a());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            t40.n(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            t40.n(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            t40.n(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            t40.n(activity, "activity");
            int i = po0.F;
            no0.a(activity, Lifecycle$Event.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            t40.n(activity, "activity");
            int i = po0.F;
            no0.a(activity, Lifecycle$Event.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            t40.n(activity, "activity");
            int i = po0.F;
            no0.a(activity, Lifecycle$Event.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            t40.n(activity, "activity");
            int i = po0.F;
            no0.a(activity, Lifecycle$Event.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            t40.n(activity, "activity");
            int i = po0.F;
            no0.a(activity, Lifecycle$Event.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            t40.n(activity, "activity");
            int i = po0.F;
            no0.a(activity, Lifecycle$Event.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            t40.n(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            t40.n(activity, "activity");
            t40.n(bundle, "bundle");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            t40.n(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            t40.n(activity, "activity");
        }
    }

    public final void a(Lifecycle$Event lifecycle$Event) {
        if (Build.VERSION.SDK_INT < 29) {
            Activity activity = getActivity();
            t40.m(activity, "getActivity(...)");
            no0.a(activity, lifecycle$Event);
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
        this.E = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(Lifecycle$Event.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        z8 z8Var = this.E;
        if (z8Var != null) {
            ((sl0) z8Var.F).b();
        }
        a(Lifecycle$Event.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        z8 z8Var = this.E;
        if (z8Var != null) {
            sl0 sl0Var = (sl0) z8Var.F;
            int i = sl0Var.E + 1;
            sl0Var.E = i;
            if (i == 1 && sl0Var.H) {
                sl0Var.J.e(Lifecycle$Event.ON_START);
                sl0Var.H = false;
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
