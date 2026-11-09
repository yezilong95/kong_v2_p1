package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import io.nats.client.Options;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class vu extends z8 {
    public final /* synthetic */ wu O;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public vu(wu wuVar) {
        super(3);
        this.O = wuVar;
    }

    @Override // defpackage.z8
    public final boolean C(int i, int i2, Bundle bundle) {
        int i3;
        wu wuVar = this.O;
        View view = wuVar.i;
        if (i == -1) {
            WeakHashMap weakHashMap = f51.a;
            return view.performAccessibilityAction(i2, bundle);
        }
        if (i2 == 1) {
            return wuVar.w(i);
        }
        if (i2 == 2) {
            return wuVar.j(i);
        }
        if (i2 != 64) {
            if (i2 != 128) {
                return wuVar.s(i, i2, bundle);
            }
            if (wuVar.k != i) {
                return false;
            }
            wuVar.k = Integer.MIN_VALUE;
            view.invalidate();
            wuVar.x(i, Options.DEFAULT_BUFFER_SIZE);
            return true;
        }
        AccessibilityManager accessibilityManager = wuVar.h;
        if (!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled() || (i3 = wuVar.k) == i) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            wuVar.k = Integer.MIN_VALUE;
            wuVar.i.invalidate();
            wuVar.x(i3, Options.DEFAULT_BUFFER_SIZE);
        }
        wuVar.k = i;
        view.invalidate();
        wuVar.x(i, 32768);
        return true;
    }

    @Override // defpackage.z8
    public final j2 v(int i) {
        return new j2(AccessibilityNodeInfo.obtain(this.O.r(i).a));
    }

    @Override // defpackage.z8
    public final j2 x(int i) {
        wu wuVar = this.O;
        int i2 = i == 2 ? wuVar.k : wuVar.l;
        if (i2 == Integer.MIN_VALUE) {
            return null;
        }
        return v(i2);
    }
}
