package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.VirtualLayout;
import androidx.constraintlayout.widget.c;
import defpackage.mg;
import defpackage.pq;
import defpackage.vv;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public pq k;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public final void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        this.k = new pq();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_android_orientation) {
                    this.k.L0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_padding) {
                    pq pqVar = this.k;
                    int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                    pqVar.i0 = dimensionPixelSize;
                    pqVar.j0 = dimensionPixelSize;
                    pqVar.k0 = dimensionPixelSize;
                    pqVar.l0 = dimensionPixelSize;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingStart) {
                    pq pqVar2 = this.k;
                    int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                    pqVar2.k0 = dimensionPixelSize2;
                    pqVar2.m0 = dimensionPixelSize2;
                    pqVar2.n0 = dimensionPixelSize2;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.k.l0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.k.m0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.k.i0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.k.n0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.k.j0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.k.J0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.k.t0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.k.u0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.k.v0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.k.x0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.k.w0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.k.y0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.k.z0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.k.B0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.k.D0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.k.C0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.k.E0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.k.A0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.k.H0 = typedArrayObtainStyledAttributes.getInt(index, 2);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.k.I0 = typedArrayObtainStyledAttributes.getInt(index, 2);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.k.F0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.k.G0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.k.K0 = typedArrayObtainStyledAttributes.getInt(index, -1);
                }
            }
        }
        this.e = this.k;
        l();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void h(c cVar, vv vvVar, Constraints.LayoutParams layoutParams, SparseArray sparseArray) {
        super.h(cVar, vvVar, layoutParams, sparseArray);
        if (vvVar instanceof pq) {
            pq pqVar = (pq) vvVar;
            int i = layoutParams.R;
            if (i != -1) {
                pqVar.L0 = i;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void i(mg mgVar, boolean z) {
        pq pqVar = this.k;
        int i = pqVar.k0;
        if (i > 0 || pqVar.l0 > 0) {
            if (z) {
                pqVar.m0 = pqVar.l0;
                pqVar.n0 = i;
            } else {
                pqVar.m0 = i;
                pqVar.n0 = pqVar.l0;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0567  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0584  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x00da A[EDGE_INSN: B:337:0x00da->B:63:0x00da BREAK  A[LOOP:1: B:57:0x00c6->B:62:0x00d6], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0115  */
    @Override // androidx.constraintlayout.widget.VirtualLayout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(defpackage.pq r35, int r36, int r37) {
        /*
            Method dump skipped, instruction units count: 1432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.helper.widget.Flow.m(pq, int, int):void");
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onMeasure(int i, int i2) {
        m(this.k, i, i2);
    }

    public void setFirstHorizontalBias(float f) {
        this.k.B0 = f;
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i) {
        this.k.v0 = i;
        requestLayout();
    }

    public void setFirstVerticalBias(float f) {
        this.k.C0 = f;
        requestLayout();
    }

    public void setFirstVerticalStyle(int i) {
        this.k.w0 = i;
        requestLayout();
    }

    public void setHorizontalAlign(int i) {
        this.k.H0 = i;
        requestLayout();
    }

    public void setHorizontalBias(float f) {
        this.k.z0 = f;
        requestLayout();
    }

    public void setHorizontalGap(int i) {
        this.k.F0 = i;
        requestLayout();
    }

    public void setHorizontalStyle(int i) {
        this.k.t0 = i;
        requestLayout();
    }

    public void setMaxElementsWrap(int i) {
        this.k.K0 = i;
        requestLayout();
    }

    public void setOrientation(int i) {
        this.k.L0 = i;
        requestLayout();
    }

    public void setPadding(int i) {
        pq pqVar = this.k;
        pqVar.i0 = i;
        pqVar.j0 = i;
        pqVar.k0 = i;
        pqVar.l0 = i;
        requestLayout();
    }

    public void setPaddingBottom(int i) {
        this.k.j0 = i;
        requestLayout();
    }

    public void setPaddingLeft(int i) {
        this.k.m0 = i;
        requestLayout();
    }

    public void setPaddingRight(int i) {
        this.k.n0 = i;
        requestLayout();
    }

    public void setPaddingTop(int i) {
        this.k.i0 = i;
        requestLayout();
    }

    public void setVerticalAlign(int i) {
        this.k.I0 = i;
        requestLayout();
    }

    public void setVerticalBias(float f) {
        this.k.A0 = f;
        requestLayout();
    }

    public void setVerticalGap(int i) {
        this.k.G0 = i;
        requestLayout();
    }

    public void setVerticalStyle(int i) {
        this.k.u0 = i;
        requestLayout();
    }

    public void setWrapMode(int i) {
        this.k.J0 = i;
        requestLayout();
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Flow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
