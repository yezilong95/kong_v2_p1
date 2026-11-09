package com.mamimobi.videostream;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import defpackage.uv0;

/* JADX INFO: loaded from: classes.dex */
public class UninstallProxyActivity extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("packageName");
        if (stringExtra != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                Intent intent = new Intent("android.intent.action.UNINSTALL_PACKAGE");
                intent.setData(Uri.parse("package:".concat(stringExtra)));
                intent.putExtra("android.intent.extra.RETURN_RESULT", true);
                startActivity(intent);
            } else {
                Intent intent2 = new Intent("android.intent.action.DELETE");
                intent2.setData(Uri.parse("package:".concat(stringExtra)));
                intent2.putExtra("android.intent.extra.RETURN_RESULT", true);
                startActivity(intent2);
            }
        }
        new Handler(Looper.getMainLooper()).postDelayed(new uv0(5, this), 500L);
    }
}
