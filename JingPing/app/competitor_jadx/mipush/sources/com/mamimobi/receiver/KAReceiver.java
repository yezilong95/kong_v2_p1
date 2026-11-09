package com.mamimobi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.mamimobi.service.MyVpnService;
import defpackage.j31;
import defpackage.lm0;
import defpackage.o71;
import defpackage.qm;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes.dex */
public class KAReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        lm0 lm0Var = o71.f(context).e;
        String str = lm0Var.e;
        if (str != null) {
            try {
                lm0Var.b(str);
            } catch (URISyntaxException unused) {
            }
        }
        qm.B0(context);
        try {
            Intent intent2 = new Intent(context, (Class<?>) MyVpnService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent2);
            } else {
                context.startService(intent2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        j31.f0(context);
    }
}
