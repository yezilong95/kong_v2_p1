package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.widget.Constraints;
import defpackage.hg;
import defpackage.mg;
import defpackage.ng;
import defpackage.v7;
import defpackage.vv;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public int i;
    public int j;
    public v7 k;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        this.k = new v7();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.k.j0 = typedArrayObtainStyledAttributes.getBoolean(index, true);
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.k.k0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                }
            }
        }
        this.e = this.k;
        l();
    }

    public int getMargin() {
        return this.k.k0;
    }

    public int getType() {
        return this.i;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void h(c cVar, vv vvVar, Constraints.LayoutParams layoutParams, SparseArray sparseArray) {
        super.h(cVar, vvVar, layoutParams, sparseArray);
        if (vvVar instanceof v7) {
            v7 v7Var = (v7) vvVar;
            boolean z = ((ng) vvVar.K).k0;
            hg hgVar = cVar.d;
            m(v7Var, hgVar.b0, z);
            v7Var.j0 = hgVar.j0;
            v7Var.k0 = hgVar.c0;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void i(mg mgVar, boolean z) {
        m(mgVar, this.i, z);
    }

    public final void m(mg mgVar, int i, boolean z) {
        this.j = i;
        if (z) {
            int i2 = this.i;
            if (i2 == 5) {
                this.j = 1;
            } else if (i2 == 6) {
                this.j = 0;
            }
        } else {
            int i3 = this.i;
            if (i3 == 5) {
                this.j = 0;
            } else if (i3 == 6) {
                this.j = 1;
            }
        }
        if (mgVar instanceof v7) {
            ((v7) mgVar).i0 = this.j;
        }
    }

    public void setAllowsGoneWidget(boolean z) {
        this.k.j0 = z;
    }

    public void setDpMargin(int i) {
        this.k.k0 = (int) ((i * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i) {
        this.k.k0 = i;
    }

    public void setType(int i) {
        this.i = i;
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }
}
