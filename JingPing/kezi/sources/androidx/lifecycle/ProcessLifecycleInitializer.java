package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import defpackage.cy;
import defpackage.dc0;
import defpackage.ec0;
import defpackage.oy;
import defpackage.po;
import defpackage.q10;
import defpackage.r10;
import java.util.HashSet;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements cy {
    @Override // defpackage.cy
    public final Object create(Context context) {
        oy.j(context, "context");
        po poVarC = po.C(context);
        oy.i(poVarC, "getInstance(...)");
        if (!((HashSet) poVarC.d).contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
        }
        if (!r10.a.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            oy.h(applicationContext, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new q10());
        }
        ec0 ec0Var = ec0.j;
        ec0Var.getClass();
        ec0Var.f = new Handler();
        ec0Var.g.e(Lifecycle$Event.ON_CREATE);
        Context applicationContext2 = context.getApplicationContext();
        oy.h(applicationContext2, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext2).registerActivityLifecycleCallbacks(new dc0(ec0Var));
        return ec0Var;
    }

    @Override // defpackage.cy
    public final List dependencies() {
        return EmptyList.INSTANCE;
    }
}
