package defpackage;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;

/* JADX INFO: loaded from: classes.dex */
public final class vm0 extends AccessibilityService.GestureResultCallback {
    public final /* synthetic */ int a;

    public /* synthetic */ vm0(int i) {
        this.a = i;
    }

    @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
    public void onCancelled(GestureDescription gestureDescription) {
        switch (this.a) {
            case 0:
                wm0.f(true);
                break;
            case 1:
            case 2:
            default:
                super.onCancelled(gestureDescription);
                break;
            case 3:
                d91.a.set(false);
                break;
            case 4:
                d91.a.set(false);
                break;
        }
    }

    @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
    public void onCompleted(GestureDescription gestureDescription) {
        switch (this.a) {
            case 0:
                wm0.f(true);
                break;
            case 1:
            case 2:
            default:
                super.onCompleted(gestureDescription);
                break;
            case 3:
                d91.a.set(false);
                break;
            case 4:
                d91.a.set(false);
                break;
        }
    }

    public vm0(wm0 wm0Var) {
        this.a = 0;
    }
}
