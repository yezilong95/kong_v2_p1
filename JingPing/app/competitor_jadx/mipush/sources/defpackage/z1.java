package defpackage;

import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import com.google.android.material.search.SearchBar;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class z1 implements AccessibilityManager.TouchExplorationStateChangeListener {
    public final fb a;

    public z1(fb fbVar) {
        this.a = fbVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof z1) {
            return this.a.equals(((z1) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean z) {
        fb fbVar = this.a;
        switch (fbVar.E) {
            case 1:
                jq jqVar = (jq) fbVar.F;
                AutoCompleteTextView autoCompleteTextView = jqVar.h;
                if (autoCompleteTextView != null && !j31.z(autoCompleteTextView)) {
                    int i = z ? 2 : 1;
                    WeakHashMap weakHashMap = f51.a;
                    jqVar.d.setImportantForAccessibility(i);
                    break;
                }
                break;
            default:
                int i2 = SearchBar.N0;
                ((SearchBar) fbVar.F).setFocusableInTouchMode(z);
                break;
        }
    }
}
