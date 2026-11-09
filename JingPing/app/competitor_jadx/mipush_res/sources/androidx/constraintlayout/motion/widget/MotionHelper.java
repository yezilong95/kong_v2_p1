package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.kf0;

/* JADX INFO: loaded from: classes.dex */
public class MotionHelper extends ConstraintHelper implements kf0 {
    public boolean L;
    public boolean M;
    public float N;
    public View[] O;

    public MotionHelper(Context context) {
        super(context);
        this.L = false;
        this.M = false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionHelper);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MotionHelper_onShow) {
                    this.L = typedArrayObtainStyledAttributes.getBoolean(index, this.L);
                } else if (index == R$styleable.MotionHelper_onHide) {
                    this.M = typedArrayObtainStyledAttributes.getBoolean(index, this.M);
                }
            }
        }
    }

    public float getProgress() {
        return this.N;
    }

    public void setProgress(float f) {
        this.N = f;
        int i = 0;
        if (this.F > 0) {
            this.O = f((ConstraintLayout) getParent());
            while (i < this.F) {
                View view = this.O[i];
                i++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i < childCount) {
            boolean z = viewGroup.getChildAt(i) instanceof MotionHelper;
            i++;
        }
    }

    public MotionHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.L = false;
        this.M = false;
        g(attributeSet);
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.L = false;
        this.M = false;
        g(attributeSet);
    }
}
