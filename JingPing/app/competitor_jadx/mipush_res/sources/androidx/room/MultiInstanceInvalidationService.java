package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.t40;
import defpackage.uf0;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class MultiInstanceInvalidationService extends Service {
    public int E;
    public final LinkedHashMap F = new LinkedHashMap();
    public final uf0 G = new uf0(this);
    public final MultiInstanceInvalidationService$binder$1 H = new MultiInstanceInvalidationService$binder$1(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        t40.n(intent, "intent");
        return this.H;
    }
}
