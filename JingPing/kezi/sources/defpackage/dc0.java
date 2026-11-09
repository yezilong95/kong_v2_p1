package defpackage;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class dc0 extends yn {
    final /* synthetic */ ec0 this$0;

    public static final class a extends yn {
        final /* synthetic */ ec0 this$0;

        public a(ec0 ec0Var) {
            this.this$0 = ec0Var;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            oy.j(activity, "activity");
            this.this$0.a();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            oy.j(activity, "activity");
            ec0 ec0Var = this.this$0;
            int i = ec0Var.b + 1;
            ec0Var.b = i;
            if (i == 1 && ec0Var.e) {
                ec0Var.g.e(Lifecycle$Event.ON_START);
                ec0Var.e = false;
            }
        }
    }

    public dc0(ec0 ec0Var) {
        this.this$0 = ec0Var;
    }

    @Override // defpackage.yn, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        oy.j(activity, "activity");
        if (Build.VERSION.SDK_INT < 29) {
            int i = pe0.c;
            Fragment fragmentFindFragmentByTag = activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
            oy.h(fragmentFindFragmentByTag, "null cannot be cast to non-null type androidx.lifecycle.ReportFragment");
            ((pe0) fragmentFindFragmentByTag).b = this.this$0.i;
        }
    }

    @Override // defpackage.yn, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        oy.j(activity, "activity");
        ec0 ec0Var = this.this$0;
        int i = ec0Var.c - 1;
        ec0Var.c = i;
        if (i == 0) {
            Handler handler = ec0Var.f;
            oy.g(handler);
            handler.postDelayed(ec0Var.h, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        oy.j(activity, "activity");
        cc0.a(activity, new a(this.this$0));
    }

    @Override // defpackage.yn, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        oy.j(activity, "activity");
        ec0 ec0Var = this.this$0;
        int i = ec0Var.b - 1;
        ec0Var.b = i;
        if (i == 0 && ec0Var.d) {
            ec0Var.g.e(Lifecycle$Event.ON_STOP);
            ec0Var.e = true;
        }
    }
}
