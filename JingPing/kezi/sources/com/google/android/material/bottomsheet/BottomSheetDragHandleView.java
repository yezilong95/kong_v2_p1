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
import defpackage.h9;
import defpackage.i9;
import defpackage.j0;
import defpackage.j9;
import defpackage.xr0;
import defpackage.y50;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.java_websocket.WebSocketImpl;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetDragHandleView extends AppCompatImageView implements AccessibilityManager.AccessibilityStateChangeListener {
    public static final int n = R$style.Widget_Material3_BottomSheet_DragHandle;
    public final AccessibilityManager e;
    public BottomSheetBehavior f;
    public boolean g;
    public boolean h;
    public boolean i;
    public final String j;
    public final String k;
    public final String l;
    public final i9 m;

    public BottomSheetDragHandleView(Context context) {
        this(context, null);
    }

    private void setBottomSheetBehavior(BottomSheetBehavior<?> bottomSheetBehavior) {
        BottomSheetBehavior bottomSheetBehavior2 = this.f;
        i9 i9Var = this.m;
        if (bottomSheetBehavior2 != null) {
            bottomSheetBehavior2.Y.remove(i9Var);
            this.f.G(null);
        }
        this.f = bottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.G(this);
            d(this.f.M);
            ArrayList arrayList = this.f.Y;
            if (!arrayList.contains(i9Var)) {
                arrayList.add(i9Var);
            }
        }
        e();
    }

    public final boolean c() {
        if (!this.h) {
            return false;
        }
        AccessibilityManager accessibilityManager = this.e;
        if (accessibilityManager != null) {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(WebSocketImpl.RCVBUF);
            accessibilityEventObtain.getText().add(this.l);
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain);
        }
        BottomSheetBehavior bottomSheetBehavior = this.f;
        boolean z = bottomSheetBehavior.c;
        int i = bottomSheetBehavior.M;
        int i2 = 6;
        int i3 = 3;
        if (i == 4) {
            if (z) {
                i2 = i3;
            }
        } else if (i != 3) {
            if (!this.i) {
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
            this.i = true;
        } else if (i == 3) {
            this.i = false;
        }
        xr0.n(this, j0.g, this.i ? this.j : this.k, new h9(0, this));
    }

    public final void e() {
        this.h = this.g && this.f != null;
        int i = this.f == null ? 2 : 1;
        WeakHashMap weakHashMap = xr0.a;
        setImportantForAccessibility(i);
        setClickable(this.h);
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public final void onAccessibilityStateChanged(boolean z) {
        this.g = z;
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
        AccessibilityManager accessibilityManager = this.e;
        if (accessibilityManager != null) {
            accessibilityManager.addAccessibilityStateChangeListener(this);
            onAccessibilityStateChanged(accessibilityManager.isEnabled());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        AccessibilityManager accessibilityManager = this.e;
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
        super(y50.a(context, attributeSet, i, n), attributeSet, i);
        this.j = getResources().getString(R$string.bottomsheet_action_expand);
        this.k = getResources().getString(R$string.bottomsheet_action_collapse);
        this.l = getResources().getString(R$string.bottomsheet_drag_handle_clicked);
        this.m = new i9(this);
        this.e = (AccessibilityManager) getContext().getSystemService("accessibility");
        e();
        xr0.p(this, new j9(0, this));
    }
}
