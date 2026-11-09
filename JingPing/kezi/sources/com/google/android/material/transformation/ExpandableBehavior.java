package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.wo;
import defpackage.xo;
import defpackage.xr0;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior<View> {
    public int b;

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public abstract boolean f(View view, View view2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        Object obj = (xo) view2;
        boolean z = ((FloatingActionButton) obj).p.a;
        if (z) {
            int i = this.b;
            if (i != 0 && i != 2) {
                return false;
            }
        } else if (this.b != 1) {
            return false;
        }
        this.b = z ? 1 : 2;
        w((View) obj, view, z, true);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        xo xoVar;
        boolean z;
        int i2;
        WeakHashMap weakHashMap = xr0.a;
        if (!view.isLaidOut()) {
            ArrayList arrayListO = coordinatorLayout.o(view);
            int size = arrayListO.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    xoVar = null;
                    break;
                }
                View view2 = (View) arrayListO.get(i3);
                if (f(view, view2)) {
                    xoVar = (xo) view2;
                    break;
                }
                i3++;
            }
            if (xoVar != null && (!(z = ((FloatingActionButton) xoVar).p.a) ? this.b == 1 : !((i2 = this.b) != 0 && i2 != 2))) {
                int i4 = z ? 1 : 2;
                this.b = i4;
                view.getViewTreeObserver().addOnPreDrawListener(new wo(this, view, i4, xoVar));
            }
        }
        return false;
    }

    public abstract void w(View view, View view2, boolean z, boolean z2);

    public ExpandableBehavior() {
        this.b = 0;
    }
}
