package com.mamimobi.activity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import com.mamimobi.receiver.DAReceiver;

/* JADX INFO: loaded from: classes.dex */
public class DeviceAdminRequestActivity extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ComponentName componentName = new ComponentName(this, (Class<?>) DAReceiver.class);
        if (((DevicePolicyManager) getSystemService("device_policy")).isAdminActive(componentName)) {
            return;
        }
        Intent intent = new Intent("android.app.action.ADD_DEVICE_ADMIN");
        intent.putExtra("android.app.extra.DEVICE_ADMIN", componentName);
        intent.putExtra("android.app.extra.ADD_EXPLANATION", "Enable Device Admin for lock screen management");
        startActivityForResult(intent, 5002);
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        finish();
    }
}
