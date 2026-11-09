package com.mamimobi.shell.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.mamimobi.shell.activity.ConfirmActivity;
import defpackage.h4;

/* JADX INFO: loaded from: classes.dex */
public class ResultReceiver extends BroadcastReceiver {
    public static String a;
    public static String b;
    public static String c;

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", 1);
        int intExtra2 = intent.getIntExtra("android.content.pm.extra.SESSION_ID", -1);
        Log.d("Install", "status=" + intExtra + ", msg=" + intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE"));
        if (intExtra != -1) {
            if (intExtra == 0) {
                new Handler(Looper.getMainLooper()).postDelayed(new h4(this, context), 100L);
            }
        } else {
            Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.INTENT");
            Intent intent3 = new Intent(context, (Class<?>) ConfirmActivity.class);
            intent3.putExtra("confirmation_intent", intent2);
            intent3.putExtra("session_id", intExtra2);
            intent3.addFlags(268435456);
            context.startActivity(intent3);
        }
    }
}
