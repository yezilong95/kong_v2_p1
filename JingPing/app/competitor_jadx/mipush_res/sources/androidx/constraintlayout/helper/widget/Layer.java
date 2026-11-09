package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.yh;

/* JADX INFO: loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    public float L;
    public float M;
    public float N;
    public ConstraintLayout O;
    public float P;
    public float Q;
    public float R;
    public float S;
    public float T;
    public float U;
    public float V;
    public float W;
    public final boolean a0;
    public View[] b0;
    public float c0;
    public float d0;
    public boolean e0;
    public boolean f0;

    public Layer(Context context) {
        super(context);
        this.L = Float.NaN;
        this.M = Float.NaN;
        this.N = Float.NaN;
        this.P = 1.0f;
        this.Q = 1.0f;
        this.R = Float.NaN;
        this.S = Float.NaN;
        this.T = Float.NaN;
        this.U = Float.NaN;
        this.V = Float.NaN;
        this.W = Float.NaN;
        this.a0 = true;
        this.b0 = null;
        this.c0 = 0.0f;
        this.d0 = 0.0f;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_android_visibility) {
                    this.e0 = true;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_elevation) {
                    this.f0 = true;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void j() {
        n();
        this.R = Float.NaN;
        this.S = Float.NaN;
        yh yhVar = ((ConstraintLayout.LayoutParams) getLayoutParams()).l0;
        yhVar.z(0);
        yhVar.w(0);
        m();
        layout(((int) this.V) - getPaddingLeft(), ((int) this.W) - getPaddingTop(), getPaddingRight() + ((int) this.T), getPaddingBottom() + ((int) this.U));
        if (Float.isNaN(this.N)) {
            return;
        }
        o();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void k(ConstraintLayout constraintLayout) {
        this.O = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.N = rotation;
        } else {
            if (Float.isNaN(this.N)) {
                return;
            }
            this.N = rotation;
        }
    }

    public final void m() {
        if (this.O == null) {
            return;
        }
        if (this.a0 || Float.isNaN(this.R) || Float.isNaN(this.S)) {
            if (!Float.isNaN(this.L) && !Float.isNaN(this.M)) {
                this.S = this.M;
                this.R = this.L;
                return;
            }
            View[] viewArrF = f(this.O);
            int left = viewArrF[0].getLeft();
            int top = viewArrF[0].getTop();
            int right = viewArrF[0].getRight();
            int bottom = viewArrF[0].getBottom();
            for (int i = 0; i < this.F; i++) {
                View view = viewArrF[i];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.T = right;
            this.U = bottom;
            this.V = left;
            this.W = top;
            if (Float.isNaN(this.L)) {
                this.R = (left + right) / 2;
            } else {
                this.R = this.L;
            }
            if (Float.isNaN(this.M)) {
                this.S = (top + bottom) / 2;
            } else {
                this.S = this.M;
            }
        }
    }

    public final void n() {
        int i;
        if (this.O == null || (i = this.F) == 0) {
            return;
        }
        View[] viewArr = this.b0;
        if (viewArr == null || viewArr.length != i) {
            this.b0 = new View[i];
        }
        for (int i2 = 0; i2 < this.F; i2++) {
            this.b0[i2] = this.O.e(this.E[i2]);
        }
    }

    public final void o() {
        if (this.O == null) {
            return;
        }
        if (this.b0 == null) {
            n();
        }
        m();
        double radians = Math.toRadians(this.N);
        float fSin = (float) Math.sin(radians);
        float fCos = (float) Math.cos(radians);
        float f = this.P;
        float f2 = f * fCos;
        float f3 = this.Q;
        float f4 = (-f3) * fSin;
        float f5 = f * fSin;
        float f6 = f3 * fCos;
        for (int i = 0; i < this.F; i++) {
            View view = this.b0[i];
            int right = (view.getRight() + view.getLeft()) / 2;
            int bottom = (view.getBottom() + view.getTop()) / 2;
            float f7 = right - this.R;
            float f8 = bottom - this.S;
            float f9 = (((f4 * f8) + (f2 * f7)) - f7) + this.c0;
            float f10 = (((f6 * f8) + (f7 * f5)) - f8) + this.d0;
            view.setTranslationX(f9);
            view.setTranslationY(f10);
            view.setScaleY(this.Q);
            view.setScaleX(this.P);
            view.setRotation(this.N);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.O = (ConstraintLayout) getParent();
        if (this.e0 || this.f0) {
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i = 0; i < this.F; i++) {
                View viewE = this.O.e(this.E[i]);
                if (viewE != null) {
                    if (this.e0) {
                        viewE.setVisibility(visibility);
                    }
                    if (this.f0 && elevation > 0.0f) {
                        viewE.setTranslationZ(viewE.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        c();
    }

    @Override // android.view.View
    public void setPivotX(float f) {
        this.L = f;
        o();
    }

    @Override // android.view.View
    public void setPivotY(float f) {
        this.M = f;
        o();
    }

    @Override // android.view.View
    public void setRotation(float f) {
        this.N = f;
        o();
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.P = f;
        o();
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        this.Q = f;
        o();
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        this.c0 = f;
        o();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        this.d0 = f;
        o();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        c();
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.L = Float.NaN;
        this.M = Float.NaN;
        this.N = Float.NaN;
        this.P = 1.0f;
        this.Q = 1.0f;
        this.R = Float.NaN;
        this.S = Float.NaN;
        this.T = Float.NaN;
        this.U = Float.NaN;
        this.V = Float.NaN;
        this.W = Float.NaN;
        this.a0 = true;
        this.b0 = null;
        this.c0 = 0.0f;
        this.d0 = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.L = Float.NaN;
        this.M = Float.NaN;
        this.N = Float.NaN;
        this.P = 1.0f;
        this.Q = 1.0f;
        this.R = Float.NaN;
        this.S = Float.NaN;
        this.T = Float.NaN;
        this.U = Float.NaN;
        this.V = Float.NaN;
        this.W = Float.NaN;
        this.a0 = true;
        this.b0 = null;
        this.c0 = 0.0f;
        this.d0 = 0.0f;
    }
}
