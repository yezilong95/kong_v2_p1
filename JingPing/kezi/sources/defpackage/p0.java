package defpackage;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class p0 extends AccessibilityNodeProvider {
    public final o70 a;

    public p0(o70 o70Var) {
        this.a = o70Var;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        o0 o0VarU = this.a.u(i);
        if (o0VarU == null) {
            return null;
        }
        return o0VarU.a;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final List findAccessibilityNodeInfosByText(String str, int i) {
        this.a.getClass();
        return null;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo findFocus(int i) {
        o0 o0VarV = this.a.v(i);
        if (o0VarV == null) {
            return null;
        }
        return o0VarV.a;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final boolean performAction(int i, int i2, Bundle bundle) {
        return this.a.A(i, i2, bundle);
    }
}
