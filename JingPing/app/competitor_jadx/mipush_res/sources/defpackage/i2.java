package defpackage;

import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public final class i2 {
    public final AccessibilityNodeInfo.CollectionItemInfo a;

    public i2(AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo) {
        this.a = collectionItemInfo;
    }

    public static i2 a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        return new i2(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
    }
}
