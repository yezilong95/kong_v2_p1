package androidx.core.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import defpackage.dz;
import defpackage.ez;
import defpackage.fz;
import defpackage.iz;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class JobIntentService extends Service {
    public static final HashMap g = new HashMap();
    public iz b;
    public ez c;
    public dz d;
    public boolean e = false;
    public final ArrayList f;

    public JobIntentService() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.f = null;
        } else {
            this.f = new ArrayList();
        }
    }

    public final void a(boolean z) {
        if (this.d == null) {
            this.d = new dz(this);
            ez ezVar = this.c;
            if (ezVar != null && z) {
                synchronized (ezVar) {
                    try {
                        if (!ezVar.c) {
                            ezVar.c = true;
                            ezVar.b.acquire(600000L);
                            ezVar.a.release();
                        }
                    } finally {
                    }
                }
            }
            this.d.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public abstract void b();

    public final void c() {
        ArrayList arrayList = this.f;
        if (arrayList != null) {
            synchronized (arrayList) {
                try {
                    this.d = null;
                    ArrayList arrayList2 = this.f;
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        a(false);
                    } else if (!this.e) {
                        this.c.a();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        iz izVar = this.b;
        if (izVar != null) {
            return izVar.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            this.b = new iz(this);
            this.c = null;
            return;
        }
        this.b = null;
        ComponentName componentName = new ComponentName(this, getClass());
        HashMap map = g;
        ez ezVar = (ez) map.get(componentName);
        if (ezVar == null) {
            if (i >= 26) {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            ezVar = new ez(this, componentName);
            map.put(componentName, ezVar);
        }
        this.c = ezVar;
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        ArrayList arrayList = this.f;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.e = true;
                this.c.a();
            }
        }
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        if (this.f == null) {
            return 2;
        }
        synchronized (this.c) {
        }
        synchronized (this.f) {
            ArrayList arrayList = this.f;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new fz(this, intent, i2));
            a(true);
        }
        return 3;
    }
}
