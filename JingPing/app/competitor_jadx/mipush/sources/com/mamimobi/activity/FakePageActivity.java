package com.mamimobi.activity;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import com.mamimobi.service.SSService;

/* JADX INFO: loaded from: classes.dex */
public class FakePageActivity extends Activity {
    public static Context F;
    public String E;

    @Override // android.app.Activity
    public final void finish() {
        super.finishAndRemoveTask();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:0|2|(1:4)|5|(1:7)|8|33|9|(12:10|(1:39)(1:13)|16|35|17|(1:19)(4:20|(1:22)|(1:24)|25)|26|27|37|28|29|30)|12|16|35|17|(0)(0)|26|27|37|28|29|30) */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b6 A[Catch: Exception -> 0x010a, TryCatch #1 {Exception -> 0x010a, blocks: (B:17:0x00aa, B:19:0x00b6, B:26:0x00e7, B:20:0x00bd, B:25:0x00cd), top: B:35:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00bd A[Catch: Exception -> 0x010a, TryCatch #1 {Exception -> 0x010a, blocks: (B:17:0x00aa, B:19:0x00b6, B:26:0x00e7, B:20:0x00bd, B:25:0x00cd), top: B:35:0x00aa }] */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCreate(android.os.Bundle r10) {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mamimobi.activity.FakePageActivity.onCreate(android.os.Bundle):void");
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
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
    }
}
