package defpackage;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.ScrollingTabContainerView;

/* JADX INFO: loaded from: classes.dex */
public final class ur0 extends LinearLayout {
    public final /* synthetic */ ScrollingTabContainerView E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ur0(ScrollingTabContainerView scrollingTabContainerView, Context context) {
        super(context, null, R$attr.actionBarTabStyle);
        this.E = scrollingTabContainerView;
        ou ouVarP = ou.P(context, null, new int[]{R.attr.background}, R$attr.actionBarTabStyle);
        if (((TypedArray) ouVarP.G).hasValue(0)) {
            setBackgroundDrawable(ouVarP.A(0));
        }
        ouVarP.S();
        setGravity(8388627);
        throw null;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.app.ActionBar$Tab");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.app.ActionBar$Tab");
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ScrollingTabContainerView scrollingTabContainerView = this.E;
        if (scrollingTabContainerView.I > 0) {
            int measuredWidth = getMeasuredWidth();
            int i3 = scrollingTabContainerView.I;
            if (measuredWidth > i3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
            }
        }
    }

    @Override // android.view.View
    public final void setSelected(boolean z) {
        boolean z2 = isSelected() != z;
        super.setSelected(z);
        if (z2 && z) {
            sendAccessibilityEvent(4);
        }
    }
}
