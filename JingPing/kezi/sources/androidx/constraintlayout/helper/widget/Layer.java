package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.mg;

/* JADX INFO: loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    public float i;
    public float j;
    public float k;
    public ConstraintLayout l;
    public float m;
    public float n;
    public float o;
    public float p;
    public float q;
    public float r;
    public float s;
    public float t;
    public final boolean u;
    public View[] v;
    public float w;
    public float x;
    public boolean y;
    public boolean z;

    public Layer(Context context) {
        super(context);
        this.i = Float.NaN;
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.m = 1.0f;
        this.n = 1.0f;
        this.o = Float.NaN;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = true;
        this.v = null;
        this.w = 0.0f;
        this.x = 0.0f;
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
                    this.y = true;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_elevation) {
                    this.z = true;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void j() {
        n();
        this.o = Float.NaN;
        this.p = Float.NaN;
        mg mgVar = ((ConstraintLayout.LayoutParams) getLayoutParams()).l0;
        mgVar.z(0);
        mgVar.w(0);
        m();
        layout(((int) this.s) - getPaddingLeft(), ((int) this.t) - getPaddingTop(), getPaddingRight() + ((int) this.q), getPaddingBottom() + ((int) this.r));
        if (Float.isNaN(this.k)) {
            return;
        }
        o();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void k(ConstraintLayout constraintLayout) {
        this.l = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.k = rotation;
        } else {
            if (Float.isNaN(this.k)) {
                return;
            }
            this.k = rotation;
        }
    }

    public final void m() {
        if (this.l == null) {
            return;
        }
        if (this.u || Float.isNaN(this.o) || Float.isNaN(this.p)) {
            if (!Float.isNaN(this.i) && !Float.isNaN(this.j)) {
                this.p = this.j;
                this.o = this.i;
                return;
            }
            View[] viewArrF = f(this.l);
            int left = viewArrF[0].getLeft();
            int top = viewArrF[0].getTop();
            int right = viewArrF[0].getRight();
            int bottom = viewArrF[0].getBottom();
            for (int i = 0; i < this.c; i++) {
                View view = viewArrF[i];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.q = right;
            this.r = bottom;
            this.s = left;
            this.t = top;
            if (Float.isNaN(this.i)) {
                this.o = (left + right) / 2;
            } else {
                this.o = this.i;
            }
            if (Float.isNaN(this.j)) {
                this.p = (top + bottom) / 2;
            } else {
                this.p = this.j;
            }
        }
    }

    public final void n() {
        int i;
        if (this.l == null || (i = this.c) == 0) {
            return;
        }
        View[] viewArr = this.v;
        if (viewArr == null || viewArr.length != i) {
            this.v = new View[i];
        }
        for (int i2 = 0; i2 < this.c; i2++) {
            this.v[i2] = this.l.d(this.b[i2]);
        }
    }

    public final void o() {
        if (this.l == null) {
            return;
        }
        if (this.v == null) {
            n();
        }
        m();
        double radians = Math.toRadians(this.k);
        float fSin = (float) Math.sin(radians);
        float fCos = (float) Math.cos(radians);
        float f = this.m;
        float f2 = f * fCos;
        float f3 = this.n;
        float f4 = (-f3) * fSin;
        float f5 = f * fSin;
        float f6 = f3 * fCos;
        for (int i = 0; i < this.c; i++) {
            View view = this.v[i];
            int right = (view.getRight() + view.getLeft()) / 2;
            int bottom = (view.getBottom() + view.getTop()) / 2;
            float f7 = right - this.o;
            float f8 = bottom - this.p;
            float f9 = (((f4 * f8) + (f2 * f7)) - f7) + this.w;
            float f10 = (((f6 * f8) + (f7 * f5)) - f8) + this.x;
            view.setTranslationX(f9);
            view.setTranslationY(f10);
            view.setScaleY(this.n);
            view.setScaleX(this.m);
            view.setRotation(this.k);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.l = (ConstraintLayout) getParent();
        if (this.y || this.z) {
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i = 0; i < this.c; i++) {
                View viewD = this.l.d(this.b[i]);
                if (viewD != null) {
                    if (this.y) {
                        viewD.setVisibility(visibility);
                    }
                    if (this.z && elevation > 0.0f) {
                        viewD.setTranslationZ(viewD.getTranslationZ() + elevation);
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
        this.i = f;
        o();
    }

    @Override // android.view.View
    public void setPivotY(float f) {
        this.j = f;
        o();
    }

    @Override // android.view.View
    public void setRotation(float f) {
        this.k = f;
        o();
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.m = f;
        o();
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        this.n = f;
        o();
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        this.w = f;
        o();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        this.x = f;
        o();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        c();
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = Float.NaN;
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.m = 1.0f;
        this.n = 1.0f;
        this.o = Float.NaN;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = true;
        this.v = null;
        this.w = 0.0f;
        this.x = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = Float.NaN;
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.m = 1.0f;
        this.n = 1.0f;
        this.o = Float.NaN;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = true;
        this.v = null;
        this.w = 0.0f;
        this.x = 0.0f;
    }
}
