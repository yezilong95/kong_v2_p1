package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import com.google.android.material.search.SearchBar;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class rc implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ rc(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        AccessibilityManager accessibilityManager;
        Object obj = this.F;
        switch (this.E) {
            case 1:
                xt xtVar = (xt) obj;
                if (xtVar.b0 != null && (accessibilityManager = xtVar.a0) != null) {
                    WeakHashMap weakHashMap = f51.a;
                    if (xtVar.isAttachedToWindow()) {
                        accessibilityManager.addTouchExplorationStateChangeListener(new z1(xtVar.b0));
                    }
                    break;
                }
                break;
            case 2:
                View view2 = (View) obj;
                view2.removeOnAttachStateChangeListener(this);
                WeakHashMap weakHashMap2 = f51.a;
                r41.c(view2);
                break;
            case 3:
                SearchBar searchBar = (SearchBar) obj;
                searchBar.L0.addTouchExplorationStateChangeListener(new z1(searchBar.M0));
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        AccessibilityManager accessibilityManager;
        switch (this.E) {
            case 0:
                uc ucVar = (uc) this.F;
                ViewTreeObserver viewTreeObserver = ucVar.b0;
                if (viewTreeObserver != null) {
                    if (!viewTreeObserver.isAlive()) {
                        ucVar.b0 = view.getViewTreeObserver();
                    }
                    ucVar.b0.removeGlobalOnLayoutListener(ucVar.M);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            case 1:
                xt xtVar = (xt) this.F;
                fb fbVar = xtVar.b0;
                if (fbVar != null && (accessibilityManager = xtVar.a0) != null) {
                    accessibilityManager.removeTouchExplorationStateChangeListener(new z1(fbVar));
                    break;
                }
                break;
            case 2:
                break;
            case 3:
                SearchBar searchBar = (SearchBar) this.F;
                searchBar.L0.removeTouchExplorationStateChangeListener(new z1(searchBar.M0));
                break;
            default:
                zw0 zw0Var = (zw0) this.F;
                ViewTreeObserver viewTreeObserver2 = zw0Var.S;
                if (viewTreeObserver2 != null) {
                    if (!viewTreeObserver2.isAlive()) {
                        zw0Var.S = view.getViewTreeObserver();
                    }
                    zw0Var.S.removeGlobalOnLayoutListener(zw0Var.M);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
        }
    }

    private final void a(View view) {
    }

    private final void b(View view) {
    }

    private final void c(View view) {
    }
}
