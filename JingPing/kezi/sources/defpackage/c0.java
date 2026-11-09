package defpackage;

import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import com.google.android.material.search.SearchBar;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c0 implements AccessibilityManager.TouchExplorationStateChangeListener {
    public final h9 a;

    public c0(h9 h9Var) {
        this.a = h9Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c0) {
            return this.a.equals(((c0) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean z) {
        h9 h9Var = this.a;
        switch (h9Var.b) {
            case 1:
                gm gmVar = (gm) h9Var.c;
                AutoCompleteTextView autoCompleteTextView = gmVar.h;
                if (autoCompleteTextView != null && !oy.F(autoCompleteTextView)) {
                    int i = z ? 2 : 1;
                    WeakHashMap weakHashMap = xr0.a;
                    gmVar.d.setImportantForAccessibility(i);
                    break;
                }
                break;
            default:
                int i2 = SearchBar.k0;
                ((SearchBar) h9Var.c).setFocusableInTouchMode(z);
                break;
        }
    }
}
