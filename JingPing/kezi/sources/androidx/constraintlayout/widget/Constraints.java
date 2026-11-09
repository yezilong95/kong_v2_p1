package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import defpackage.hg;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class Constraints extends ViewGroup {
    public d b;

    public Constraints(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public d getConstraintSet() {
        if (this.b == null) {
            this.b = new d();
        }
        d dVar = this.b;
        dVar.getClass();
        int childCount = getChildCount();
        HashMap map = dVar.c;
        map.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (dVar.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!map.containsKey(Integer.valueOf(id))) {
                map.put(Integer.valueOf(id), new c());
            }
            c cVar = (c) map.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                ConstraintHelper constraintHelper = (ConstraintHelper) childAt;
                cVar.c(id, layoutParams);
                if (constraintHelper instanceof Barrier) {
                    hg hgVar = cVar.d;
                    hgVar.d0 = 1;
                    Barrier barrier = (Barrier) constraintHelper;
                    hgVar.b0 = barrier.getType();
                    hgVar.e0 = barrier.getReferencedIds();
                    hgVar.c0 = barrier.getMargin();
                }
            }
            cVar.c(id, layoutParams);
        }
        return this.b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ConstraintLayout.LayoutParams(layoutParams);
    }

    public Constraints(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.v("Constraints", " ################# init");
        super.setVisibility(8);
    }

    public Constraints(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Log.v("Constraints", " ################# init");
        super.setVisibility(8);
    }

    public static class LayoutParams extends ConstraintLayout.LayoutParams {
        public final float m0;
        public final boolean n0;
        public final float o0;
        public final float p0;
        public final float q0;
        public final float r0;
        public final float s0;
        public final float t0;
        public final float u0;
        public final float v0;
        public final float w0;
        public final float x0;
        public final float y0;

        public LayoutParams() {
            this.m0 = 1.0f;
            this.n0 = false;
            this.o0 = 0.0f;
            this.p0 = 0.0f;
            this.q0 = 0.0f;
            this.r0 = 0.0f;
            this.s0 = 1.0f;
            this.t0 = 1.0f;
            this.u0 = 0.0f;
            this.v0 = 0.0f;
            this.w0 = 0.0f;
            this.x0 = 0.0f;
            this.y0 = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.m0 = 1.0f;
            this.n0 = false;
            this.o0 = 0.0f;
            this.p0 = 0.0f;
            this.q0 = 0.0f;
            this.r0 = 0.0f;
            this.s0 = 1.0f;
            this.t0 = 1.0f;
            this.u0 = 0.0f;
            this.v0 = 0.0f;
            this.w0 = 0.0f;
            this.x0 = 0.0f;
            this.y0 = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintSet);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintSet_android_alpha) {
                    this.m0 = typedArrayObtainStyledAttributes.getFloat(index, this.m0);
                } else if (index == R$styleable.ConstraintSet_android_elevation) {
                    this.o0 = typedArrayObtainStyledAttributes.getFloat(index, this.o0);
                    this.n0 = true;
                } else if (index == R$styleable.ConstraintSet_android_rotationX) {
                    this.q0 = typedArrayObtainStyledAttributes.getFloat(index, this.q0);
                } else if (index == R$styleable.ConstraintSet_android_rotationY) {
                    this.r0 = typedArrayObtainStyledAttributes.getFloat(index, this.r0);
                } else if (index == R$styleable.ConstraintSet_android_rotation) {
                    this.p0 = typedArrayObtainStyledAttributes.getFloat(index, this.p0);
                } else if (index == R$styleable.ConstraintSet_android_scaleX) {
                    this.s0 = typedArrayObtainStyledAttributes.getFloat(index, this.s0);
                } else if (index == R$styleable.ConstraintSet_android_scaleY) {
                    this.t0 = typedArrayObtainStyledAttributes.getFloat(index, this.t0);
                } else if (index == R$styleable.ConstraintSet_android_transformPivotX) {
                    this.u0 = typedArrayObtainStyledAttributes.getFloat(index, this.u0);
                } else if (index == R$styleable.ConstraintSet_android_transformPivotY) {
                    this.v0 = typedArrayObtainStyledAttributes.getFloat(index, this.v0);
                } else if (index == R$styleable.ConstraintSet_android_translationX) {
                    this.w0 = typedArrayObtainStyledAttributes.getFloat(index, this.w0);
                } else if (index == R$styleable.ConstraintSet_android_translationY) {
                    this.x0 = typedArrayObtainStyledAttributes.getFloat(index, this.x0);
                } else if (index == R$styleable.ConstraintSet_android_translationZ) {
                    this.y0 = typedArrayObtainStyledAttributes.getFloat(index, this.y0);
                }
            }
        }
    }
}
