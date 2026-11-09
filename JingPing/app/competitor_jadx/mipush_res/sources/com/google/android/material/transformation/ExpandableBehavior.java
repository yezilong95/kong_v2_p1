package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.f51;
import defpackage.tu;
import defpackage.uu;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior<View> {
    public int E;

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public abstract boolean f(View view, View view2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        Object obj = (uu) view2;
        boolean z = ((FloatingActionButton) obj).S.a;
        if (z) {
            int i = this.E;
            if (i != 0 && i != 2) {
                return false;
            }
        } else if (this.E != 1) {
            return false;
        }
        this.E = z ? 1 : 2;
        w((View) obj, view, z, true);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        uu uuVar;
        boolean z;
        int i2;
        WeakHashMap weakHashMap = f51.a;
        if (!view.isLaidOut()) {
            ArrayList arrayListO = coordinatorLayout.o(view);
            int size = arrayListO.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    uuVar = null;
                    break;
                }
                View view2 = (View) arrayListO.get(i3);
                if (f(view, view2)) {
                    uuVar = (uu) view2;
                    break;
                }
                i3++;
            }
            if (uuVar != null && (!(z = ((FloatingActionButton) uuVar).S.a) ? this.E == 1 : !((i2 = this.E) != 0 && i2 != 2))) {
                int i4 = z ? 1 : 2;
                this.E = i4;
                view.getViewTreeObserver().addOnPreDrawListener(new tu(this, view, i4, uuVar));
            }
        }
        return false;
    }

    public abstract void w(View view, View view2, boolean z, boolean z2);

    public ExpandableBehavior() {
        this.E = 0;
    }
}
