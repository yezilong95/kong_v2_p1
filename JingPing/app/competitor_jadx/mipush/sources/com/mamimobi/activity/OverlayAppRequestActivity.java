package com.mamimobi.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import defpackage.f11;

/* JADX INFO: loaded from: classes.dex */
public class OverlayAppRequestActivity extends Activity {
    @Override // android.app.Activity
    public final void finish() {
        f11.f = Boolean.FALSE;
        super.finishAndRemoveTask();
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
        } catch (Exception unused) {
        }
        if (Settings.canDrawOverlays(this)) {
            finish();
            return;
        }
        startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 2);
    }
}
