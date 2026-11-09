package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public final class u70 implements View.OnClickListener {
    public final v70 b;
    public final int c;
    public final int d;

    public u70(Context context, v70 v70Var, XmlResourceParser xmlResourceParser) {
        this.c = -1;
        this.d = 17;
        this.b = v70Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.OnClick);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R$styleable.OnClick_targetId) {
                this.c = typedArrayObtainStyledAttributes.getResourceId(index, this.c);
            } else if (index == R$styleable.OnClick_clickAction) {
                this.d = typedArrayObtainStyledAttributes.getInt(index, this.d);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void a(MotionLayout motionLayout, int i, v70 v70Var) {
        int i2 = this.c;
        View viewFindViewById = motionLayout;
        if (i2 != -1) {
            viewFindViewById = motionLayout.findViewById(i2);
        }
        if (viewFindViewById == null) {
            Log.e("MotionScene", "OnClick could not find id " + i2);
            return;
        }
        int i3 = v70Var.d;
        int i4 = v70Var.c;
        if (i3 == -1) {
            viewFindViewById.setOnClickListener(this);
            return;
        }
        int i5 = this.d;
        int i6 = i5 & 1;
        if (((i6 != 0 && i == i3) | (i6 != 0 && i == i3) | ((i5 & 256) != 0 && i == i3) | ((i5 & 16) != 0 && i == i4)) || ((i5 & 4096) != 0 && i == i4)) {
            viewFindViewById.setOnClickListener(this);
        }
    }

    public final void b(MotionLayout motionLayout) {
        int i = this.c;
        if (i == -1) {
            return;
        }
        View viewFindViewById = motionLayout.findViewById(i);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(null);
            return;
        }
        Log.e("MotionScene", " (*)  could not find id " + i);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        v70 v70Var = this.b;
        w70 w70Var = v70Var.j;
        MotionLayout motionLayout = w70Var.a;
        if (motionLayout.z) {
            if (v70Var.d == -1) {
                int currentState = motionLayout.getCurrentState();
                if (currentState == -1) {
                    motionLayout.B(v70Var.c);
                    return;
                }
                v70 v70Var2 = new v70(w70Var, v70Var);
                v70Var2.d = currentState;
                v70Var2.c = v70Var.c;
                motionLayout.setTransition(v70Var2);
                motionLayout.o(1.0f);
                return;
            }
            v70 v70Var3 = w70Var.c;
            int i = this.d;
            int i2 = i & 1;
            boolean z = true;
            boolean z2 = false;
            boolean z3 = (i2 == 0 && (i & 256) == 0) ? false : true;
            int i3 = i & 16;
            if (i3 == 0 && (i & 4096) == 0) {
                z = false;
            }
            if (z3 && z) {
                if (v70Var3 != v70Var) {
                    motionLayout.setTransition(v70Var);
                }
                if (motionLayout.getCurrentState() != motionLayout.getEndState() && motionLayout.getProgress() <= 0.5f) {
                    z = false;
                    z2 = z3;
                }
            } else {
                z2 = z3;
            }
            if (v70Var != v70Var3) {
                int i4 = v70Var.c;
                int i5 = v70Var.d;
                if (i5 != -1) {
                    int i6 = motionLayout.v;
                    if (i6 != i5 && i6 != i4) {
                        return;
                    }
                } else if (motionLayout.v == i4) {
                    return;
                }
            }
            if (z2 && i2 != 0) {
                motionLayout.setTransition(v70Var);
                motionLayout.o(1.0f);
                return;
            }
            if (z && i3 != 0) {
                motionLayout.setTransition(v70Var);
                motionLayout.o(0.0f);
            } else if (z2 && (i & 256) != 0) {
                motionLayout.setTransition(v70Var);
                motionLayout.setProgress(1.0f);
            } else {
                if (!z || (i & 4096) == 0) {
                    return;
                }
                motionLayout.setTransition(v70Var);
                motionLayout.setProgress(0.0f);
            }
        }
    }
}
