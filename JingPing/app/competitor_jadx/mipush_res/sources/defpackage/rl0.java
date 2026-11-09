package defpackage;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class rl0 extends qt {
    final /* synthetic */ sl0 this$0;

    public static final class a extends qt {
        final /* synthetic */ sl0 this$0;

        public a(sl0 sl0Var) {
            this.this$0 = sl0Var;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            t40.n(activity, "activity");
            this.this$0.b();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            t40.n(activity, "activity");
            sl0 sl0Var = this.this$0;
            int i = sl0Var.E + 1;
            sl0Var.E = i;
            if (i == 1 && sl0Var.H) {
                sl0Var.J.e(Lifecycle$Event.ON_START);
                sl0Var.H = false;
            }
        }
    }

    public rl0(sl0 sl0Var) {
        this.this$0 = sl0Var;
    }

    @Override // defpackage.qt, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        t40.n(activity, "activity");
        if (Build.VERSION.SDK_INT < 29) {
            int i = po0.F;
            Fragment fragmentFindFragmentByTag = activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
            t40.l(fragmentFindFragmentByTag, "null cannot be cast to non-null type androidx.lifecycle.ReportFragment");
            ((po0) fragmentFindFragmentByTag).E = this.this$0.L;
        }
    }

    @Override // defpackage.qt, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        t40.n(activity, "activity");
        sl0 sl0Var = this.this$0;
        int i = sl0Var.F - 1;
        sl0Var.F = i;
        if (i == 0) {
            Handler handler = sl0Var.I;
            t40.k(handler);
            handler.postDelayed(sl0Var.K, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        t40.n(activity, "activity");
        ql0.a(activity, new a(this.this$0));
    }

    @Override // defpackage.qt, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        t40.n(activity, "activity");
        sl0 sl0Var = this.this$0;
        int i = sl0Var.E - 1;
        sl0Var.E = i;
        if (i == 0 && sl0Var.G) {
            sl0Var.J.e(Lifecycle$Event.ON_STOP);
            sl0Var.H = true;
        }
    }
}
