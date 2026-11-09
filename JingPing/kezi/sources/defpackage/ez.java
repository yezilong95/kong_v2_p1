package defpackage;

import android.content.ComponentName;
import android.os.PowerManager;
import androidx.core.app.JobIntentService;

/* JADX INFO: loaded from: classes.dex */
public final class ez {
    public final PowerManager.WakeLock a;
    public final PowerManager.WakeLock b;
    public boolean c;

    public ez(JobIntentService jobIntentService, ComponentName componentName) {
        jobIntentService.getApplicationContext();
        PowerManager powerManager = (PowerManager) jobIntentService.getSystemService("power");
        PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
        this.a = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
        PowerManager.WakeLock wakeLockNewWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
        this.b = wakeLockNewWakeLock2;
        wakeLockNewWakeLock2.setReferenceCounted(false);
    }

    public final void a() {
        synchronized (this) {
            try {
                if (this.c) {
                    this.c = false;
                    this.b.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
