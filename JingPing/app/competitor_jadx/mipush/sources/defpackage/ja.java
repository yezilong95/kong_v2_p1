package defpackage;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;

/* JADX INFO: loaded from: classes.dex */
public final class ja extends AccessibilityService.GestureResultCallback {
    public final /* synthetic */ ru a;

    public ja(ru ruVar) {
        this.a = ruVar;
    }

    @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
    public final void onCompleted(GestureDescription gestureDescription) {
        super.onCompleted(gestureDescription);
        this.a.b();
    }
}
