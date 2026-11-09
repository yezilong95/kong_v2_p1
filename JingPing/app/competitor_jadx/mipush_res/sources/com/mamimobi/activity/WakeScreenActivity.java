package com.mamimobi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import defpackage.uv0;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class WakeScreenActivity extends Activity {
    public static WeakReference E;

    public static void a() {
        if (b()) {
            ((WakeScreenActivity) E.get()).finish();
        }
    }

    public static boolean b() {
        WeakReference weakReference = E;
        return (weakReference == null || weakReference.get() == null || ((WakeScreenActivity) E.get()).isFinishing()) ? false : true;
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        E = new WeakReference(this);
        ((PowerManager) getSystemService("power")).newWakeLock(268435466, "WakeScreenActivity:WAKE_LOCK").acquire(3000L);
        getWindow().addFlags(2621592);
        new Handler(Looper.getMainLooper()).post(new uv0(8, this));
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        E = null;
    }
}
