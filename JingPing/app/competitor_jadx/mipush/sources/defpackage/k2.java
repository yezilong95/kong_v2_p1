package defpackage;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class k2 extends AccessibilityNodeProvider {
    public final z8 a;

    public k2(z8 z8Var) {
        this.a = z8Var;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        j2 j2VarV = this.a.v(i);
        if (j2VarV == null) {
            return null;
        }
        return j2VarV.a;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final List findAccessibilityNodeInfosByText(String str, int i) {
        this.a.getClass();
        return null;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo findFocus(int i) {
        j2 j2VarX = this.a.x(i);
        if (j2VarX == null) {
            return null;
        }
        return j2VarX.a;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final boolean performAction(int i, int i2, Bundle bundle) {
        return this.a.C(i, i2, bundle);
    }
}
