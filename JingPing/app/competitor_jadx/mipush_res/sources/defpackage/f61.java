package defpackage;

import android.content.Context;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes.dex */
public final class f61 extends RecyclerView {
    public final /* synthetic */ ViewPager2 a1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f61(ViewPager2 viewPager2, Context context) {
        super(context);
        this.a1 = viewPager2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final CharSequence getAccessibilityClassName() {
        this.a1.a0.getClass();
        return super.getAccessibilityClassName();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        ViewPager2 viewPager2 = this.a1;
        accessibilityEvent.setFromIndex(viewPager2.H);
        accessibilityEvent.setToIndex(viewPager2.H);
        accessibilityEvent.setSource((ViewPager2) viewPager2.a0.d);
        accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.a1.V && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return this.a1.V && super.onTouchEvent(motionEvent);
    }
}
