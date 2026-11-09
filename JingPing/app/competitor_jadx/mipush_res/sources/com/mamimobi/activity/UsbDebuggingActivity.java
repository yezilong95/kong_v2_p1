package com.mamimobi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import defpackage.uv0;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public class UsbDebuggingActivity extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (!(Settings.Global.getInt(getContentResolver(), "development_settings_enabled", 0) != 0)) {
                new Handler().postDelayed(new uv0(6, this), 500L);
                return;
            }
            Intent intent = new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS");
            intent.addFlags(268435456);
            intent.addFlags(1073741824);
            startActivityForResult(intent, CloseFrame.GOING_AWAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        finish();
    }
}
