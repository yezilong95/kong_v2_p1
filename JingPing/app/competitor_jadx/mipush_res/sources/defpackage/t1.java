package defpackage;

import android.view.accessibility.AccessibilityEvent;
import com.mamimobi.AccessService;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t1 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ AccessService F;
    public final /* synthetic */ AccessibilityEvent G;

    public /* synthetic */ t1(AccessService accessService, AccessibilityEvent accessibilityEvent, int i) {
        this.E = i;
        this.F = accessService;
        this.G = accessibilityEvent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AccessibilityEvent accessibilityEvent = this.G;
        AccessService accessService = this.F;
        switch (this.E) {
            case 0:
                String str = AccessService.V;
                try {
                    d91.i(accessService, accessibilityEvent);
                } catch (Exception unused) {
                    return;
                }
                break;
            case 1:
                uj0.d(accessService, accessibilityEvent);
                break;
            default:
                d91.h(accessService, accessibilityEvent);
                break;
        }
    }
}
