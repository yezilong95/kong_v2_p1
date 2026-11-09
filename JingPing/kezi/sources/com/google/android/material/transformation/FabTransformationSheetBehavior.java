package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.c;
import com.google.android.material.R$animator;
import defpackage.e5;
import defpackage.x70;
import defpackage.xr0;
import defpackage.yo;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    public HashMap j;

    public FabTransformationSheetBehavior() {
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    public final e5 D(Context context, boolean z) {
        int i = z ? R$animator.mtrl_fab_transformation_sheet_expand_spec : R$animator.mtrl_fab_transformation_sheet_collapse_spec;
        e5 e5Var = new e5(12, false);
        e5Var.c = x70.b(context, i);
        e5Var.d = new yo(11);
        return e5Var;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    public final void w(View view, View view2, boolean z, boolean z2) {
        ViewParent parent = view2.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                this.j = new HashMap(childCount);
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                boolean z3 = (childAt.getLayoutParams() instanceof c) && (((c) childAt.getLayoutParams()).a instanceof FabTransformationScrimBehavior);
                if (childAt != view2 && !z3) {
                    if (z) {
                        this.j.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        WeakHashMap weakHashMap = xr0.a;
                        childAt.setImportantForAccessibility(4);
                    } else {
                        HashMap map = this.j;
                        if (map != null && map.containsKey(childAt)) {
                            int iIntValue = ((Integer) this.j.get(childAt)).intValue();
                            WeakHashMap weakHashMap2 = xr0.a;
                            childAt.setImportantForAccessibility(iIntValue);
                        }
                    }
                }
            }
            if (!z) {
                this.j = null;
            }
        }
        super.w(view, view2, z, z2);
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
