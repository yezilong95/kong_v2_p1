package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.widget.Constraints;
import defpackage.b30;
import defpackage.q9;
import defpackage.th;
import defpackage.yh;
import defpackage.zh;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public int L;
    public int M;
    public q9 N;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        this.N = new q9();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.N.j0 = typedArrayObtainStyledAttributes.getBoolean(index, true);
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.N.k0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                }
            }
        }
        this.H = this.N;
        l();
    }

    public int getMargin() {
        return this.N.k0;
    }

    public int getType() {
        return this.L;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void h(c cVar, b30 b30Var, Constraints.LayoutParams layoutParams, SparseArray sparseArray) {
        super.h(cVar, b30Var, layoutParams, sparseArray);
        if (b30Var instanceof q9) {
            q9 q9Var = (q9) b30Var;
            boolean z = ((zh) b30Var.K).k0;
            th thVar = cVar.d;
            m(q9Var, thVar.b0, z);
            q9Var.j0 = thVar.j0;
            q9Var.k0 = thVar.c0;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void i(yh yhVar, boolean z) {
        m(yhVar, this.L, z);
    }

    public final void m(yh yhVar, int i, boolean z) {
        this.M = i;
        if (z) {
            int i2 = this.L;
            if (i2 == 5) {
                this.M = 1;
            } else if (i2 == 6) {
                this.M = 0;
            }
        } else {
            int i3 = this.L;
            if (i3 == 5) {
                this.M = 0;
            } else if (i3 == 6) {
                this.M = 1;
            }
        }
        if (yhVar instanceof q9) {
            ((q9) yhVar).i0 = this.M;
        }
    }

    public void setAllowsGoneWidget(boolean z) {
        this.N.j0 = z;
    }

    public void setDpMargin(int i) {
        this.N.k0 = (int) ((i * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i) {
        this.N.k0 = i;
    }

    public void setType(int i) {
        this.L = i;
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
