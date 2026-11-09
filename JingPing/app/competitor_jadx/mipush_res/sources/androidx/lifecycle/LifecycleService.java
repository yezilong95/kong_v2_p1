package androidx.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.s80;
import defpackage.t40;
import defpackage.vu0;
import defpackage.x80;

/* JADX INFO: loaded from: classes.dex */
public class LifecycleService extends Service implements x80 {
    private final vu0 dispatcher = new vu0(this);

    @Override // defpackage.x80
    public s80 getLifecycle() {
        return this.dispatcher.a;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        t40.n(intent, "intent");
        vu0 vu0Var = this.dispatcher;
        vu0Var.getClass();
        vu0Var.a(Lifecycle$Event.ON_START);
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        vu0 vu0Var = this.dispatcher;
        vu0Var.getClass();
        vu0Var.a(Lifecycle$Event.ON_CREATE);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        vu0 vu0Var = this.dispatcher;
        vu0Var.getClass();
        vu0Var.a(Lifecycle$Event.ON_STOP);
        vu0Var.a(Lifecycle$Event.ON_DESTROY);
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        vu0 vu0Var = this.dispatcher;
        vu0Var.getClass();
        vu0Var.a(Lifecycle$Event.ON_START);
        super.onStart(intent, i);
    }
}
