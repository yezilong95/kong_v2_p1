package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.c;
import com.google.android.material.R$animator;
import defpackage.di;
import defpackage.f51;
import defpackage.i6;
import defpackage.rf0;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    public HashMap M;

    public FabTransformationSheetBehavior() {
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    public final i6 D(Context context, boolean z) {
        int i = z ? R$animator.mtrl_fab_transformation_sheet_expand_spec : R$animator.mtrl_fab_transformation_sheet_collapse_spec;
        i6 i6Var = new i6(14, false);
        i6Var.F = rf0.b(context, i);
        i6Var.G = new di(25);
        return i6Var;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    public final void w(View view, View view2, boolean z, boolean z2) {
        ViewParent parent = view2.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                this.M = new HashMap(childCount);
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                boolean z3 = (childAt.getLayoutParams() instanceof c) && (((c) childAt.getLayoutParams()).a instanceof FabTransformationScrimBehavior);
                if (childAt != view2 && !z3) {
                    if (z) {
                        this.M.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        WeakHashMap weakHashMap = f51.a;
                        childAt.setImportantForAccessibility(4);
                    } else {
                        HashMap map = this.M;
                        if (map != null && map.containsKey(childAt)) {
                            int iIntValue = ((Integer) this.M.get(childAt)).intValue();
                            WeakHashMap weakHashMap2 = f51.a;
                            childAt.setImportantForAccessibility(iIntValue);
                        }
                    }
                }
            }
            if (!z) {
                this.M = null;
            }
        }
        super.w(view, view2, z, z2);
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
