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
public final class ql extends b0 {
    public final /* synthetic */ int d;
    public final Object e;
    public final Object f;

    public ql(be0 be0Var) {
        this.d = 1;
        this.f = new WeakHashMap();
        this.e = be0Var;
    }

    @Override // defpackage.b0
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
                        WeakHashMap weakHashMap = xr0.a;
                        Gravity.getAbsoluteGravity(iH, drawerLayout.getLayoutDirection());
                    }
                }
                break;
            default:
                b0 b0Var = (b0) ((WeakHashMap) obj).get(view);
                if (b0Var == null) {
                }
                break;
        }
        return accessibilityDelegate.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // defpackage.b0
    public o70 b(View view) {
        switch (this.d) {
            case 1:
                b0 b0Var = (b0) ((WeakHashMap) this.f).get(view);
                if (b0Var == null) {
                }
                break;
        }
        return super.b(view);
    }

    @Override // defpackage.b0
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 0:
                super.c(view, accessibilityEvent);
                accessibilityEvent.setClassName("androidx.drawerlayout.widget.DrawerLayout");
                break;
            default:
                b0 b0Var = (b0) ((WeakHashMap) this.f).get(view);
                if (b0Var == null) {
                    super.c(view, accessibilityEvent);
                } else {
                    b0Var.c(view, accessibilityEvent);
                }
                break;
        }
    }

    @Override // defpackage.b0
    public final void d(View view, o0 o0Var) {
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        Object obj = this.e;
        switch (this.d) {
            case 0:
                boolean z = DrawerLayout.G;
                AccessibilityNodeInfo accessibilityNodeInfo = o0Var.a;
                if (z) {
                    accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                } else {
                    AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(accessibilityNodeInfo);
                    accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoObtain);
                    o0Var.b = -1;
                    accessibilityNodeInfo.setSource(view);
                    WeakHashMap weakHashMap = xr0.a;
                    Object parentForAccessibility = view.getParentForAccessibility();
                    if (parentForAccessibility instanceof View) {
                        accessibilityNodeInfo.setParent((View) parentForAccessibility);
                    }
                    Rect rect = (Rect) obj;
                    accessibilityNodeInfoObtain.getBoundsInScreen(rect);
                    accessibilityNodeInfo.setBoundsInScreen(rect);
                    accessibilityNodeInfo.setVisibleToUser(accessibilityNodeInfoObtain.isVisibleToUser());
                    accessibilityNodeInfo.setPackageName(accessibilityNodeInfoObtain.getPackageName());
                    o0Var.i(accessibilityNodeInfoObtain.getClassName());
                    accessibilityNodeInfo.setContentDescription(accessibilityNodeInfoObtain.getContentDescription());
                    accessibilityNodeInfo.setEnabled(accessibilityNodeInfoObtain.isEnabled());
                    accessibilityNodeInfo.setFocused(accessibilityNodeInfoObtain.isFocused());
                    accessibilityNodeInfo.setAccessibilityFocused(accessibilityNodeInfoObtain.isAccessibilityFocused());
                    accessibilityNodeInfo.setSelected(accessibilityNodeInfoObtain.isSelected());
                    o0Var.a(accessibilityNodeInfoObtain.getActions());
                    ViewGroup viewGroup = (ViewGroup) view;
                    int childCount = viewGroup.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childAt = viewGroup.getChildAt(i);
                        if (DrawerLayout.i(childAt)) {
                            accessibilityNodeInfo.addChild(childAt);
                        }
                    }
                }
                o0Var.i("androidx.drawerlayout.widget.DrawerLayout");
                accessibilityNodeInfo.setFocusable(false);
                accessibilityNodeInfo.setFocused(false);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) j0.e.a);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) j0.f.a);
                break;
            default:
                be0 be0Var = (be0) obj;
                boolean zK = be0Var.d.K();
                AccessibilityNodeInfo accessibilityNodeInfo2 = o0Var.a;
                if (!zK) {
                    RecyclerView recyclerView = be0Var.d;
                    if (recyclerView.getLayoutManager() != null) {
                        recyclerView.getLayoutManager().S(view, o0Var);
                        b0 b0Var = (b0) ((WeakHashMap) this.f).get(view);
                        if (b0Var != null) {
                            b0Var.d(view, o0Var);
                        } else {
                            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo2);
                        }
                    }
                }
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo2);
                break;
        }
    }

    @Override // defpackage.b0
    public void e(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 1:
                b0 b0Var = (b0) ((WeakHashMap) this.f).get(view);
                if (b0Var == null) {
                    super.e(view, accessibilityEvent);
                } else {
                    b0Var.e(view, accessibilityEvent);
                }
                break;
            default:
                super.e(view, accessibilityEvent);
                break;
        }
    }

    @Override // defpackage.b0
    public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 0:
                if (DrawerLayout.G || DrawerLayout.i(view)) {
                }
                break;
            default:
                b0 b0Var = (b0) ((WeakHashMap) this.f).get(viewGroup);
                if (b0Var == null) {
                }
                break;
        }
        return this.a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // defpackage.b0
    public boolean g(View view, int i, Bundle bundle) {
        switch (this.d) {
            case 1:
                be0 be0Var = (be0) this.e;
                if (!be0Var.d.K()) {
                    RecyclerView recyclerView = be0Var.d;
                    if (recyclerView.getLayoutManager() != null) {
                        b0 b0Var = (b0) ((WeakHashMap) this.f).get(view);
                        if (b0Var == null ? !super.g(view, i, bundle) : !b0Var.g(view, i, bundle)) {
                            l lVar = recyclerView.getLayoutManager().b.c;
                        }
                    }
                }
                break;
        }
        return super.g(view, i, bundle);
    }

    @Override // defpackage.b0
    public void h(View view, int i) {
        switch (this.d) {
            case 1:
                b0 b0Var = (b0) ((WeakHashMap) this.f).get(view);
                if (b0Var == null) {
                    super.h(view, i);
                } else {
                    b0Var.h(view, i);
                }
                break;
            default:
                super.h(view, i);
                break;
        }
    }

    @Override // defpackage.b0
    public void i(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 1:
                b0 b0Var = (b0) ((WeakHashMap) this.f).get(view);
                if (b0Var == null) {
                    super.i(view, accessibilityEvent);
                } else {
                    b0Var.i(view, accessibilityEvent);
                }
                break;
            default:
                super.i(view, accessibilityEvent);
                break;
        }
    }

    public ql(DrawerLayout drawerLayout) {
        this.d = 0;
        this.f = drawerLayout;
        this.e = new Rect();
    }
}
