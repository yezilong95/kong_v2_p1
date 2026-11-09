package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class zo extends o70 {
    public final /* synthetic */ ap f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zo(ap apVar) {
        super(3);
        this.f = apVar;
    }

    @Override // defpackage.o70
    public final boolean A(int i, int i2, Bundle bundle) {
        int i3;
        ap apVar = this.f;
        View view = apVar.i;
        if (i == -1) {
            WeakHashMap weakHashMap = xr0.a;
            return view.performAccessibilityAction(i2, bundle);
        }
        if (i2 == 1) {
            return apVar.w(i);
        }
        if (i2 == 2) {
            return apVar.j(i);
        }
        if (i2 != 64) {
            if (i2 != 128) {
                return apVar.s(i, i2, bundle);
            }
            if (apVar.k != i) {
                return false;
            }
            apVar.k = Integer.MIN_VALUE;
            view.invalidate();
            apVar.x(i, 65536);
            return true;
        }
        AccessibilityManager accessibilityManager = apVar.h;
        if (!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled() || (i3 = apVar.k) == i) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            apVar.k = Integer.MIN_VALUE;
            apVar.i.invalidate();
            apVar.x(i3, 65536);
        }
        apVar.k = i;
        view.invalidate();
        apVar.x(i, 32768);
        return true;
    }

    @Override // defpackage.o70
    public final o0 u(int i) {
        return new o0(AccessibilityNodeInfo.obtain(this.f.r(i).a));
    }

    @Override // defpackage.o70
    public final o0 v(int i) {
        ap apVar = this.f;
        int i2 = i == 2 ? apVar.k : apVar.l;
        if (i2 == Integer.MIN_VALUE) {
            return null;
        }
        return u(i2);
    }
}
