package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.VirtualLayout;
import androidx.constraintlayout.widget.c;
import defpackage.b30;
import defpackage.cx;
import defpackage.yh;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public cx N;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public final void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        this.N = new cx();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_android_orientation) {
                    this.N.L0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_padding) {
                    cx cxVar = this.N;
                    int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                    cxVar.i0 = dimensionPixelSize;
                    cxVar.j0 = dimensionPixelSize;
                    cxVar.k0 = dimensionPixelSize;
                    cxVar.l0 = dimensionPixelSize;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingStart) {
                    cx cxVar2 = this.N;
                    int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                    cxVar2.k0 = dimensionPixelSize2;
                    cxVar2.m0 = dimensionPixelSize2;
                    cxVar2.n0 = dimensionPixelSize2;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.N.l0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.N.m0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.N.i0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.N.n0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.N.j0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.N.J0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.N.t0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.N.u0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.N.v0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.N.x0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.N.w0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.N.y0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.N.z0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.N.B0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.N.D0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.N.C0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.N.E0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.N.A0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.N.H0 = typedArrayObtainStyledAttributes.getInt(index, 2);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.N.I0 = typedArrayObtainStyledAttributes.getInt(index, 2);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.N.F0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.N.G0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.N.K0 = typedArrayObtainStyledAttributes.getInt(index, -1);
                }
            }
        }
        this.H = this.N;
        l();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void h(c cVar, b30 b30Var, Constraints.LayoutParams layoutParams, SparseArray sparseArray) {
        super.h(cVar, b30Var, layoutParams, sparseArray);
        if (b30Var instanceof cx) {
            cx cxVar = (cx) b30Var;
            int i = layoutParams.R;
            if (i != -1) {
                cxVar.L0 = i;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void i(yh yhVar, boolean z) {
        cx cxVar = this.N;
        int i = cxVar.k0;
        if (i > 0 || cxVar.l0 > 0) {
            if (z) {
                cxVar.m0 = cxVar.l0;
                cxVar.n0 = i;
            } else {
                cxVar.m0 = i;
                cxVar.n0 = cxVar.l0;
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
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m(defpackage.cx r35, int r36, int r37) {
        /*
            Method dump skipped, instruction units count: 1432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.helper.widget.Flow.m(cx, int, int):void");
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onMeasure(int i, int i2) {
        m(this.N, i, i2);
    }

    public void setFirstHorizontalBias(float f) {
        this.N.B0 = f;
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i) {
        this.N.v0 = i;
        requestLayout();
    }

    public void setFirstVerticalBias(float f) {
        this.N.C0 = f;
        requestLayout();
    }

    public void setFirstVerticalStyle(int i) {
        this.N.w0 = i;
        requestLayout();
    }

    public void setHorizontalAlign(int i) {
        this.N.H0 = i;
        requestLayout();
    }

    public void setHorizontalBias(float f) {
        this.N.z0 = f;
        requestLayout();
    }

    public void setHorizontalGap(int i) {
        this.N.F0 = i;
        requestLayout();
    }

    public void setHorizontalStyle(int i) {
        this.N.t0 = i;
        requestLayout();
    }

    public void setMaxElementsWrap(int i) {
        this.N.K0 = i;
        requestLayout();
    }

    public void setOrientation(int i) {
        this.N.L0 = i;
        requestLayout();
    }

    public void setPadding(int i) {
        cx cxVar = this.N;
        cxVar.i0 = i;
        cxVar.j0 = i;
        cxVar.k0 = i;
        cxVar.l0 = i;
        requestLayout();
    }

    public void setPaddingBottom(int i) {
        this.N.j0 = i;
        requestLayout();
    }

    public void setPaddingLeft(int i) {
        this.N.m0 = i;
        requestLayout();
    }

    public void setPaddingRight(int i) {
        this.N.n0 = i;
        requestLayout();
    }

    public void setPaddingTop(int i) {
        this.N.i0 = i;
        requestLayout();
    }

    public void setVerticalAlign(int i) {
        this.N.I0 = i;
        requestLayout();
    }

    public void setVerticalBias(float f) {
        this.N.A0 = f;
        requestLayout();
    }

    public void setVerticalGap(int i) {
        this.N.G0 = i;
        requestLayout();
    }

    public void setVerticalStyle(int i) {
        this.N.u0 = i;
        requestLayout();
    }

    public void setWrapMode(int i) {
        this.N.J0 = i;
        requestLayout();
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Flow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
