package androidx.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.fi0;
import defpackage.oy;
import defpackage.p10;
import defpackage.u10;

/* JADX INFO: loaded from: classes.dex */
public class LifecycleService extends Service implements u10 {
    private final fi0 dispatcher = new fi0(this);

    @Override // defpackage.u10
    public p10 getLifecycle() {
        return this.dispatcher.a;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        oy.j(intent, "intent");
        fi0 fi0Var = this.dispatcher;
        fi0Var.getClass();
        fi0Var.a(Lifecycle$Event.ON_START);
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        fi0 fi0Var = this.dispatcher;
        fi0Var.getClass();
        fi0Var.a(Lifecycle$Event.ON_CREATE);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        fi0 fi0Var = this.dispatcher;
        fi0Var.getClass();
        fi0Var.a(Lifecycle$Event.ON_STOP);
        fi0Var.a(Lifecycle$Event.ON_DESTROY);
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        fi0 fi0Var = this.dispatcher;
        fi0Var.getClass();
        fi0Var.a(Lifecycle$Event.ON_START);
        super.onStart(intent, i);
    }
}
