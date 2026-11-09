package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;
import androidx.work.Logger;
import defpackage.t40;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class WakeLocks {
    private static final String TAG;

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("WakeLocks");
        t40.m(strTagWithPrefix, "tagWithPrefix(\"WakeLocks\")");
        TAG = strTagWithPrefix;
    }

    public static final void checkWakeLocks() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        WakeLocksHolder wakeLocksHolder = WakeLocksHolder.INSTANCE;
        synchronized (wakeLocksHolder) {
            linkedHashMap.putAll(wakeLocksHolder.getWakeLocks());
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            PowerManager.WakeLock wakeLock = (PowerManager.WakeLock) entry.getKey();
            String str = (String) entry.getValue();
            if (wakeLock != null && wakeLock.isHeld()) {
                Logger.get().warning(TAG, "WakeLock held for " + str);
            }
        }
    }

    public static final PowerManager.WakeLock newWakeLock(Context context, String str) {
        t40.n(context, "context");
        t40.n(str, "tag");
        Object systemService = context.getApplicationContext().getSystemService("power");
        t40.l(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        String strConcat = "WorkManager: ".concat(str);
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) systemService).newWakeLock(1, strConcat);
        WakeLocksHolder wakeLocksHolder = WakeLocksHolder.INSTANCE;
        synchronized (wakeLocksHolder) {
            wakeLocksHolder.getWakeLocks().put(wakeLockNewWakeLock, strConcat);
        }
        t40.m(wakeLockNewWakeLock, "wakeLock");
        return wakeLockNewWakeLock;
    }
}
