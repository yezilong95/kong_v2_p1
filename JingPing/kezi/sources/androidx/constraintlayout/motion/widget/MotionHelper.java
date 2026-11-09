package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.q70;

/* JADX INFO: loaded from: classes.dex */
public class MotionHelper extends ConstraintHelper implements q70 {
    public boolean i;
    public boolean j;
    public float k;
    public View[] l;

    public MotionHelper(Context context) {
        super(context);
        this.i = false;
        this.j = false;
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
                    this.i = typedArrayObtainStyledAttributes.getBoolean(index, this.i);
                } else if (index == R$styleable.MotionHelper_onHide) {
                    this.j = typedArrayObtainStyledAttributes.getBoolean(index, this.j);
                }
            }
        }
    }

    public float getProgress() {
        return this.k;
    }

    public void setProgress(float f) {
        this.k = f;
        int i = 0;
        if (this.c > 0) {
            this.l = f((ConstraintLayout) getParent());
            while (i < this.c) {
                View view = this.l[i];
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
        this.i = false;
        this.j = false;
        g(attributeSet);
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = false;
        this.j = false;
        g(attributeSet);
    }
}
