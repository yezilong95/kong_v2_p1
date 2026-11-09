package defpackage;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import com.mamimobi.AccessService;

/* JADX INFO: loaded from: classes.dex */
public final class tk0 extends AccessibilityService.GestureResultCallback {
    public final /* synthetic */ long a;

    public tk0(long j) {
        this.a = j;
    }

    @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
    public final void onCancelled(GestureDescription gestureDescription) {
        uk0.d.getClass();
        AccessService.j0.set(false);
    }

    @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
    public final void onCompleted(GestureDescription gestureDescription) {
        if (!uk0.c.isEmpty()) {
            uk0.a.execute(new f(19, this));
        } else {
            uk0.d.getClass();
            AccessService.j0.set(false);
        }
    }
}
