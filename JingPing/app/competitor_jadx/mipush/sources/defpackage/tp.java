package defpackage;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class tp extends y1 {
    public final /* synthetic */ int d;
    public final Object e;
    public final Object f;

    public tp(xn0 xn0Var) {
        this.d = 1;
        this.f = new WeakHashMap();
        this.e = xn0Var;
    }

    @Override // defpackage.y1
    public final boolean a(View view, AccessibilityEvent accessibilityEvent) {
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        Object obj = this.f;
        switch (this.d) {
            case 0:
                if (accessibilityEvent.getEventType() == 32) {
                    accessibilityEvent.getText();
                    DrawerLayout drawerLayout = (DrawerLayout) obj;
                    View viewF = drawerLayout.f();
                    if (viewF != null) {
                        int iH = drawerLayout.h(viewF);
                        drawerLayout.getClass();
                        WeakHashMap weakHashMap = f51.a;
                        Gravity.getAbsoluteGravity(iH, drawerLayout.getLayoutDirection());
                    }
                }
                break;
            default:
                y1 y1Var = (y1) ((WeakHashMap) obj).get(view);
                if (y1Var == null) {
                }
                break;
        }
        return accessibilityDelegate.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // defpackage.y1
    public z8 b(View view) {
        switch (this.d) {
            case 1:
                y1 y1Var = (y1) ((WeakHashMap) this.f).get(view);
                if (y1Var == null) {
                }
                break;
        }
        return super.b(view);
    }

    @Override // defpackage.y1
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 0:
                super.c(view, accessibilityEvent);
                accessibilityEvent.setClassName("androidx.drawerlayout.widget.DrawerLayout");
                break;
            default:
                y1 y1Var = (y1) ((WeakHashMap) this.f).get(view);
                if (y1Var == null) {
                    super.c(view, accessibilityEvent);
                } else {
                    y1Var.c(view, accessibilityEvent);
                }
                break;
        }
    }

    @Override // defpackage.y1
    public final void d(View view, j2 j2Var) {
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        Object obj = this.e;
        switch (this.d) {
            case 0:
                boolean z = DrawerLayout.m0;
                AccessibilityNodeInfo accessibilityNodeInfo = j2Var.a;
                if (z) {
                    accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                } else {
                    AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(accessibilityNodeInfo);
                    accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoObtain);
                    j2Var.b = -1;
                    accessibilityNodeInfo.setSource(view);
                    WeakHashMap weakHashMap = f51.a;
                    Object parentForAccessibility = view.getParentForAccessibility();
                    if (parentForAccessibility instanceof View) {
                        accessibilityNodeInfo.setParent((View) parentForAccessibility);
                    }
                    Rect rect = (Rect) obj;
                    accessibilityNodeInfoObtain.getBoundsInScreen(rect);
                    accessibilityNodeInfo.setBoundsInScreen(rect);
                    accessibilityNodeInfo.setVisibleToUser(accessibilityNodeInfoObtain.isVisibleToUser());
                    accessibilityNodeInfo.setPackageName(accessibilityNodeInfoObtain.getPackageName());
                    j2Var.i(accessibilityNodeInfoObtain.getClassName());
                    accessibilityNodeInfo.setContentDescription(accessibilityNodeInfoObtain.getContentDescription());
                    accessibilityNodeInfo.setEnabled(accessibilityNodeInfoObtain.isEnabled());
                    accessibilityNodeInfo.setFocused(accessibilityNodeInfoObtain.isFocused());
                    accessibilityNodeInfo.setAccessibilityFocused(accessibilityNodeInfoObtain.isAccessibilityFocused());
                    accessibilityNodeInfo.setSelected(accessibilityNodeInfoObtain.isSelected());
                    j2Var.a(accessibilityNodeInfoObtain.getActions());
                    ViewGroup viewGroup = (ViewGroup) view;
                    int childCount = viewGroup.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childAt = viewGroup.getChildAt(i);
                        if (DrawerLayout.i(childAt)) {
                            accessibilityNodeInfo.addChild(childAt);
                        }
                    }
                }
                j2Var.i("androidx.drawerlayout.widget.DrawerLayout");
                accessibilityNodeInfo.setFocusable(false);
                accessibilityNodeInfo.setFocused(false);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) e2.e.a);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) e2.f.a);
                break;
            default:
                xn0 xn0Var = (xn0) obj;
                boolean zK = xn0Var.d.K();
                AccessibilityNodeInfo accessibilityNodeInfo2 = j2Var.a;
                if (!zK) {
                    RecyclerView recyclerView = xn0Var.d;
                    if (recyclerView.getLayoutManager() != null) {
                        recyclerView.getLayoutManager().V(view, j2Var);
                        y1 y1Var = (y1) ((WeakHashMap) this.f).get(view);
                        if (y1Var != null) {
                            y1Var.d(view, j2Var);
                        } else {
                            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo2);
                        }
                    }
                }
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo2);
                break;
        }
    }

    @Override // defpackage.y1
    public void e(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 1:
                y1 y1Var = (y1) ((WeakHashMap) this.f).get(view);
                if (y1Var == null) {
                    super.e(view, accessibilityEvent);
                } else {
                    y1Var.e(view, accessibilityEvent);
                }
                break;
            default:
                super.e(view, accessibilityEvent);
                break;
        }
    }

    @Override // defpackage.y1
    public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 0:
                if (DrawerLayout.m0 || DrawerLayout.i(view)) {
                }
                break;
            default:
                y1 y1Var = (y1) ((WeakHashMap) this.f).get(viewGroup);
                if (y1Var == null) {
                }
                break;
        }
        return this.a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // defpackage.y1
    public boolean g(View view, int i, Bundle bundle) {
        switch (this.d) {
            case 1:
                xn0 xn0Var = (xn0) this.e;
                if (!xn0Var.d.K()) {
                    RecyclerView recyclerView = xn0Var.d;
                    if (recyclerView.getLayoutManager() != null) {
                        y1 y1Var = (y1) ((WeakHashMap) this.f).get(view);
                        if (y1Var == null ? !super.g(view, i, bundle) : !y1Var.g(view, i, bundle)) {
                            l lVar = recyclerView.getLayoutManager().b.F;
                        }
                    }
                }
                break;
        }
        return super.g(view, i, bundle);
    }

    @Override // defpackage.y1
    public void h(View view, int i) {
        switch (this.d) {
            case 1:
                y1 y1Var = (y1) ((WeakHashMap) this.f).get(view);
                if (y1Var == null) {
                    super.h(view, i);
                } else {
                    y1Var.h(view, i);
                }
                break;
            default:
                super.h(view, i);
                break;
        }
    }

    @Override // defpackage.y1
    public void i(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 1:
                y1 y1Var = (y1) ((WeakHashMap) this.f).get(view);
                if (y1Var == null) {
                    super.i(view, accessibilityEvent);
                } else {
                    y1Var.i(view, accessibilityEvent);
                }
                break;
            default:
                super.i(view, accessibilityEvent);
                break;
        }
    }

    public tp(DrawerLayout drawerLayout) {
        this.d = 0;
        this.f = drawerLayout;
        this.e = new Rect();
    }
}
