package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import com.google.android.material.search.SearchBar;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class wa implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ wa(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        AccessibilityManager accessibilityManager;
        Object obj = this.c;
        switch (this.b) {
            case 1:
                fo foVar = (fo) obj;
                if (foVar.v != null && (accessibilityManager = foVar.u) != null) {
                    WeakHashMap weakHashMap = xr0.a;
                    if (foVar.isAttachedToWindow()) {
                        accessibilityManager.addTouchExplorationStateChangeListener(new c0(foVar.v));
                    }
                    break;
                }
                break;
            case 2:
                View view2 = (View) obj;
                view2.removeOnAttachStateChangeListener(this);
                WeakHashMap weakHashMap2 = xr0.a;
                jr0.c(view2);
                break;
            case 3:
                SearchBar searchBar = (SearchBar) obj;
                searchBar.i0.addTouchExplorationStateChangeListener(new c0(searchBar.j0));
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        AccessibilityManager accessibilityManager;
        switch (this.b) {
            case 0:
                za zaVar = (za) this.c;
                ViewTreeObserver viewTreeObserver = zaVar.y;
                if (viewTreeObserver != null) {
                    if (!viewTreeObserver.isAlive()) {
                        zaVar.y = view.getViewTreeObserver();
                    }
                    zaVar.y.removeGlobalOnLayoutListener(zaVar.j);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            case 1:
                fo foVar = (fo) this.c;
                h9 h9Var = foVar.v;
                if (h9Var != null && (accessibilityManager = foVar.u) != null) {
                    accessibilityManager.removeTouchExplorationStateChangeListener(new c0(h9Var));
                    break;
                }
                break;
            case 2:
                break;
            case 3:
                SearchBar searchBar = (SearchBar) this.c;
                searchBar.i0.removeTouchExplorationStateChangeListener(new c0(searchBar.j0));
                break;
            default:
                lk0 lk0Var = (lk0) this.c;
                ViewTreeObserver viewTreeObserver2 = lk0Var.p;
                if (viewTreeObserver2 != null) {
                    if (!viewTreeObserver2.isAlive()) {
                        lk0Var.p = view.getViewTreeObserver();
                    }
                    lk0Var.p.removeGlobalOnLayoutListener(lk0Var.j);
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
