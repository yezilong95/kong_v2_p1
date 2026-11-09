package defpackage;

import android.content.Context;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class zs0 extends RecyclerView {
    public final /* synthetic */ ViewPager2 x0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zs0(ViewPager2 viewPager2, Context context) {
        super(context);
        this.x0 = viewPager2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final CharSequence getAccessibilityClassName() {
        this.x0.u.getClass();
        return super.getAccessibilityClassName();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        ViewPager2 viewPager2 = this.x0;
        accessibilityEvent.setFromIndex(viewPager2.e);
        accessibilityEvent.setToIndex(viewPager2.e);
        accessibilityEvent.setSource((ViewPager2) viewPager2.u.d);
        accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.x0.s && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return this.x0.s && super.onTouchEvent(motionEvent);
    }
}
