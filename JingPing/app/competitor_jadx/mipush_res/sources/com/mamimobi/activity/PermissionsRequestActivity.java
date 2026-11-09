package com.mamimobi.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import com.mamimobi.AccessService;
import defpackage.f11;
import defpackage.t40;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class PermissionsRequestActivity extends Activity {
    public boolean E = false;

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AtomicBoolean atomicBoolean = AccessService.w0;
        atomicBoolean.set(true);
        String[] stringArrayExtra = getIntent().getStringArrayExtra("extra_permissions");
        if (stringArrayExtra == null || stringArrayExtra.length == 0) {
            AtomicBoolean atomicBoolean2 = f11.a;
            atomicBoolean.set(false);
            finishAndRemoveTask();
            return;
        }
        for (String str : stringArrayExtra) {
            if (t40.o(this, str) != 0) {
                requestPermissions(stringArrayExtra, 151);
                return;
            }
        }
        AtomicBoolean atomicBoolean3 = f11.a;
        AccessService.w0.set(false);
        finishAndRemoveTask();
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 151) {
            if (iArr != null && iArr.length != 0) {
                for (int i2 : iArr) {
                    if (i2 == 0) {
                    }
                }
                AtomicBoolean atomicBoolean = f11.a;
                AccessService.w0.set(false);
                finishAndRemoveTask();
                return;
            }
            if (this.E) {
                AtomicBoolean atomicBoolean2 = f11.a;
                AccessService.w0.set(false);
                finishAndRemoveTask();
                return;
            }
            this.E = true;
            String[] strArr2 = Build.VERSION.SDK_INT >= 33 ? new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.POST_NOTIFICATIONS"} : new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
            for (String str : strArr2) {
                if (t40.o(this, str) != 0) {
                    requestPermissions(strArr2, 151);
                    return;
                }
            }
        }
    }
}
