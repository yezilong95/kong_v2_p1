package defpackage;

import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public final class n0 {
    public final AccessibilityNodeInfo.CollectionItemInfo a;

    public n0(AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo) {
        this.a = collectionItemInfo;
    }

    public static n0 a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        return new n0(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
    }
}
