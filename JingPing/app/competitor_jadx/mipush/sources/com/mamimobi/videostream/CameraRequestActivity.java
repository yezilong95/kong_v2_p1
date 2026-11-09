package com.mamimobi.videostream;

import android.app.Activity;
import android.os.Bundle;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public class CameraRequestActivity extends Activity {
    public String E = null;

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("videoSourceType");
        this.E = stringExtra;
        if (stringExtra == null) {
            super.finish();
        }
        if (t40.o(this, "android.permission.CAMERA") != 0) {
            t40.a1(this, new String[]{"android.permission.CAMERA"});
            return;
        }
        if (this.E.equals("camerafront")) {
            VideoStreamService.b(this, VideoSourceType.CAMERA_FRONT);
            super.finish();
        } else if (this.E.equals("cameraback")) {
            VideoStreamService.b(this, VideoSourceType.CAMERA_BACK);
            super.finish();
        } else if (!this.E.equals("screenHelper")) {
            super.finish();
        } else {
            VideoStreamService.b(this, VideoSourceType.SCREENHELPER);
            super.finish();
        }
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 4499 && iArr.length > 0 && iArr[0] == 0) {
            if (this.E.equals("camerafront")) {
                VideoStreamService.b(this, VideoSourceType.CAMERA_FRONT);
            } else if (this.E.equals("cameraback")) {
                VideoStreamService.b(this, VideoSourceType.CAMERA_BACK);
            }
        }
        super.finish();
    }
}
