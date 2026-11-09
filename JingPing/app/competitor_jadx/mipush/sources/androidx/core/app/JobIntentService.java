package androidx.core.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import defpackage.h50;
import defpackage.i50;
import defpackage.j50;
import defpackage.m50;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class JobIntentService extends Service {
    public static final HashMap J = new HashMap();
    public m50 E;
    public i50 F;
    public h50 G;
    public boolean H = false;
    public final ArrayList I;

    public JobIntentService() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.I = null;
        } else {
            this.I = new ArrayList();
        }
    }

    public final void a(boolean z) {
        if (this.G == null) {
            this.G = new h50(this);
            i50 i50Var = this.F;
            if (i50Var != null && z) {
                synchronized (i50Var) {
                    try {
                        if (!i50Var.c) {
                            i50Var.c = true;
                            i50Var.b.acquire(600000L);
                            i50Var.a.release();
                        }
                    } finally {
                    }
                }
            }
            this.G.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public abstract void b();

    public final void c() {
        ArrayList arrayList = this.I;
        if (arrayList != null) {
            synchronized (arrayList) {
                try {
                    this.G = null;
                    ArrayList arrayList2 = this.I;
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        a(false);
                    } else if (!this.H) {
                        this.F.a();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        m50 m50Var = this.E;
        if (m50Var != null) {
            return m50Var.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            this.E = new m50(this);
            this.F = null;
            return;
        }
        this.E = null;
        ComponentName componentName = new ComponentName(this, getClass());
        HashMap map = J;
        i50 i50Var = (i50) map.get(componentName);
        if (i50Var == null) {
            if (i >= 26) {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            i50Var = new i50(this, componentName);
            map.put(componentName, i50Var);
        }
        this.F = i50Var;
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        ArrayList arrayList = this.I;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.H = true;
                this.F.a();
            }
        }
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        if (this.I == null) {
            return 2;
        }
        synchronized (this.F) {
        }
        synchronized (this.I) {
            ArrayList arrayList = this.I;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new j50(this, intent, i2));
            a(true);
        }
        return 3;
    }
}
