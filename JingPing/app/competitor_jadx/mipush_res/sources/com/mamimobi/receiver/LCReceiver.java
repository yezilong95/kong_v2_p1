package com.mamimobi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mamimobi.AccessService;
import defpackage.ma0;

/* JADX INFO: loaded from: classes.dex */
public class LCReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                ma0.J(context, AccessService.h(context, context.getPackageName()));
            }
        } catch (Exception unused) {
        }
    }
}
