package com.mamimobi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import defpackage.fa0;
import defpackage.j31;
import defpackage.lm0;
import defpackage.o71;
import defpackage.t40;
import io.nats.client.support.ApiConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PKReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            o71 o71VarF = o71.f(context);
            lm0 lm0Var = o71VarF.e;
            String strV = t40.V(context);
            if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ApiConstants.ID, strV);
                jSONObject.put(ApiConstants.ACTION, "package_added");
                if (o71VarF.e() != null) {
                    jSONObject.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject.toString().getBytes());
            } else if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(ApiConstants.ID, strV);
                jSONObject2.put(ApiConstants.ACTION, "package_removed");
                if (o71VarF.e() != null) {
                    jSONObject2.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject2.toString().getBytes());
            } else if ("android.intent.action.PACKAGE_REPLACED".equals(intent.getAction())) {
                Uri data = intent.getData();
                if (data != null) {
                    String schemeSpecificPart = data.getSchemeSpecificPart();
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(ApiConstants.ID, strV);
                    jSONObject3.put(ApiConstants.ACTION, "package_replaced");
                    jSONObject3.put("package", schemeSpecificPart);
                    if (o71VarF.e() != null) {
                        jSONObject3.put("clientid", o71VarF.e());
                    }
                    lm0Var.c(jSONObject3.toString().getBytes());
                    if (context.getPackageName().equals(schemeSpecificPart)) {
                        fa0.R0(context, "PerFinishAll", false);
                    }
                }
            } else if ("android.intent.action.PACKAGE_CHANGED".equals(intent.getAction())) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put(ApiConstants.ID, strV);
                jSONObject4.put(ApiConstants.ACTION, "package_changed");
                if (o71VarF.e() != null) {
                    jSONObject4.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject4.toString().getBytes());
            }
        } catch (Exception unused) {
        }
        j31.g0(context.getApplicationContext());
    }
}
