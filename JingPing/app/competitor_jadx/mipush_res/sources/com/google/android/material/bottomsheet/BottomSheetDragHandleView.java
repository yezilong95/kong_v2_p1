package com.google.android.material.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.c;
import com.google.android.material.R$attr;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import defpackage.e2;
import defpackage.f51;
import defpackage.fb;
import defpackage.gb;
import defpackage.hb;
import defpackage.pd0;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.java_websocket.WebSocketImpl;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetDragHandleView extends AppCompatImageView implements AccessibilityManager.AccessibilityStateChangeListener {
    public static final int Q = R$style.Widget_Material3_BottomSheet_DragHandle;
    public final AccessibilityManager H;
    public BottomSheetBehavior I;
    public boolean J;
    public boolean K;
    public boolean L;
    public final String M;
    public final String N;
    public final String O;
    public final gb P;

    public BottomSheetDragHandleView(Context context) {
        this(context, null);
    }

    private void setBottomSheetBehavior(BottomSheetBehavior<?> bottomSheetBehavior) {
        BottomSheetBehavior bottomSheetBehavior2 = this.I;
        gb gbVar = this.P;
        if (bottomSheetBehavior2 != null) {
            bottomSheetBehavior2.B0.remove(gbVar);
            this.I.G(null);
        }
        this.I = bottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.G(this);
            d(this.I.p0);
            ArrayList arrayList = this.I.B0;
            if (!arrayList.contains(gbVar)) {
                arrayList.add(gbVar);
            }
        }
        e();
    }

    public final boolean c() {
        if (!this.K) {
            return false;
        }
        AccessibilityManager accessibilityManager = this.H;
        if (accessibilityManager != null) {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(WebSocketImpl.RCVBUF);
            accessibilityEventObtain.getText().add(this.O);
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain);
        }
        BottomSheetBehavior bottomSheetBehavior = this.I;
        boolean z = bottomSheetBehavior.F;
        int i = bottomSheetBehavior.p0;
        int i2 = 6;
        int i3 = 3;
        if (i == 4) {
            if (z) {
                i2 = i3;
            }
        } else if (i != 3) {
            if (!this.L) {
                i3 = 4;
            }
            i2 = i3;
        } else if (z) {
            i2 = 4;
        }
        bottomSheetBehavior.I(i2);
        return true;
    }

    public final void d(int i) {
        if (i == 4) {
            this.L = true;
        } else if (i == 3) {
            this.L = false;
        }
        f51.m(this, e2.g, this.L ? this.M : this.N, new fb(0, this));
    }

    public final void e() {
        this.K = this.J && this.I != null;
        int i = this.I == null ? 2 : 1;
        WeakHashMap weakHashMap = f51.a;
        setImportantForAccessibility(i);
        setClickable(this.K);
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public final void onAccessibilityStateChanged(boolean z) {
        this.J = z;
        e();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        BottomSheetBehavior<?> bottomSheetBehavior;
        super.onAttachedToWindow();
        View view = this;
        while (true) {
            Object parent = view.getParent();
            bottomSheetBehavior = null;
            view = parent instanceof View ? (View) parent : null;
            if (view == null) {
                break;
            }
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof c) {
                CoordinatorLayout.Behavior behavior = ((c) layoutParams).a;
                if (behavior instanceof BottomSheetBehavior) {
                    bottomSheetBehavior = (BottomSheetBehavior) behavior;
                    break;
                }
            }
        }
        setBottomSheetBehavior(bottomSheetBehavior);
        AccessibilityManager accessibilityManager = this.H;
        if (accessibilityManager != null) {
            accessibilityManager.addAccessibilityStateChangeListener(this);
            onAccessibilityStateChanged(accessibilityManager.isEnabled());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        AccessibilityManager accessibilityManager = this.H;
        if (accessibilityManager != null) {
            accessibilityManager.removeAccessibilityStateChangeListener(this);
        }
        setBottomSheetBehavior(null);
        super.onDetachedFromWindow();
    }

    public BottomSheetDragHandleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomSheetDragHandleStyle);
    }

    public BottomSheetDragHandleView(Context context, AttributeSet attributeSet, int i) {
        super(pd0.a(context, attributeSet, i, Q), attributeSet, i);
        this.M = getResources().getString(R$string.bottomsheet_action_expand);
        this.N = getResources().getString(R$string.bottomsheet_action_collapse);
        this.O = getResources().getString(R$string.bottomsheet_drag_handle_clicked);
        this.P = new gb(this);
        this.H = (AccessibilityManager) getContext().getSystemService("accessibility");
        e();
        f51.o(this, new hb(0, this));
    }
}
