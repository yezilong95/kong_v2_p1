package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import defpackage.f40;
import defpackage.ou;
import defpackage.rl0;
import defpackage.sl0;
import defpackage.t40;
import defpackage.t80;
import defpackage.u80;
import java.util.HashSet;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements f40 {
    @Override // defpackage.f40
    public final Object create(Context context) {
        t40.n(context, "context");
        ou ouVarE = ou.E(context);
        t40.m(ouVarE, "getInstance(...)");
        if (!((HashSet) ouVarE.G).contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
        }
        if (!u80.a.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            t40.l(applicationContext, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new t80());
        }
        sl0 sl0Var = sl0.M;
        sl0Var.getClass();
        sl0Var.I = new Handler();
        sl0Var.J.e(Lifecycle$Event.ON_CREATE);
        Context applicationContext2 = context.getApplicationContext();
        t40.l(applicationContext2, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext2).registerActivityLifecycleCallbacks(new rl0(sl0Var));
        return sl0Var;
    }

    @Override // defpackage.f40
    public final List dependencies() {
        return EmptyList.INSTANCE;
    }
}
