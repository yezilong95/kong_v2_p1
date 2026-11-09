package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.a80;
import defpackage.oy;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class MultiInstanceInvalidationService extends Service {
    public int b;
    public final LinkedHashMap c = new LinkedHashMap();
    public final a80 d = new a80(this);
    public final MultiInstanceInvalidationService$binder$1 e = new MultiInstanceInvalidationService$binder$1(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        oy.j(intent, "intent");
        return this.e;
    }
}
