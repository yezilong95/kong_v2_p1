package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import io.nats.client.Options;
import io.nats.client.support.NatsJetStreamConstants;

/* JADX INFO: loaded from: classes.dex */
public final class of0 implements View.OnClickListener {
    public final pf0 E;
    public final int F;
    public final int G;

    public of0(Context context, pf0 pf0Var, XmlResourceParser xmlResourceParser) {
        this.F = -1;
        this.G = 17;
        this.E = pf0Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.OnClick);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R$styleable.OnClick_targetId) {
                this.F = typedArrayObtainStyledAttributes.getResourceId(index, this.F);
            } else if (index == R$styleable.OnClick_clickAction) {
                this.G = typedArrayObtainStyledAttributes.getInt(index, this.G);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void a(MotionLayout motionLayout, int i, pf0 pf0Var) {
        int i2 = this.F;
        View viewFindViewById = motionLayout;
        if (i2 != -1) {
            viewFindViewById = motionLayout.findViewById(i2);
        }
        if (viewFindViewById == null) {
            Log.e("MotionScene", "OnClick could not find id " + i2);
            return;
        }
        int i3 = pf0Var.d;
        int i4 = pf0Var.c;
        if (i3 == -1) {
            viewFindViewById.setOnClickListener(this);
            return;
        }
        int i5 = this.G;
        int i6 = i5 & 1;
        if (((i6 != 0 && i == i3) | (i6 != 0 && i == i3) | ((i5 & NatsJetStreamConstants.MAX_PULL_SIZE) != 0 && i == i3) | ((i5 & 16) != 0 && i == i4)) || ((i5 & Options.DEFAULT_MAX_CONTROL_LINE) != 0 && i == i4)) {
            viewFindViewById.setOnClickListener(this);
        }
    }

    public final void b(MotionLayout motionLayout) {
        int i = this.F;
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
        pf0 pf0Var = this.E;
        qf0 qf0Var = pf0Var.j;
        MotionLayout motionLayout = qf0Var.a;
        if (motionLayout.f0) {
            if (pf0Var.d == -1) {
                int currentState = motionLayout.getCurrentState();
                if (currentState == -1) {
                    motionLayout.B(pf0Var.c);
                    return;
                }
                pf0 pf0Var2 = new pf0(qf0Var, pf0Var);
                pf0Var2.d = currentState;
                pf0Var2.c = pf0Var.c;
                motionLayout.setTransition(pf0Var2);
                motionLayout.o(1.0f);
                return;
            }
            pf0 pf0Var3 = qf0Var.c;
            int i = this.G;
            int i2 = i & 1;
            boolean z = true;
            boolean z2 = false;
            boolean z3 = (i2 == 0 && (i & NatsJetStreamConstants.MAX_PULL_SIZE) == 0) ? false : true;
            int i3 = i & 16;
            if (i3 == 0 && (i & Options.DEFAULT_MAX_CONTROL_LINE) == 0) {
                z = false;
            }
            if (z3 && z) {
                if (pf0Var3 != pf0Var) {
                    motionLayout.setTransition(pf0Var);
                }
                if (motionLayout.getCurrentState() != motionLayout.getEndState() && motionLayout.getProgress() <= 0.5f) {
                    z = false;
                    z2 = z3;
                }
            } else {
                z2 = z3;
            }
            if (pf0Var != pf0Var3) {
                int i4 = pf0Var.c;
                int i5 = pf0Var.d;
                if (i5 != -1) {
                    int i6 = motionLayout.b0;
                    if (i6 != i5 && i6 != i4) {
                        return;
                    }
                } else if (motionLayout.b0 == i4) {
                    return;
                }
            }
            if (z2 && i2 != 0) {
                motionLayout.setTransition(pf0Var);
                motionLayout.o(1.0f);
                return;
            }
            if (z && i3 != 0) {
                motionLayout.setTransition(pf0Var);
                motionLayout.o(0.0f);
            } else if (z2 && (i & NatsJetStreamConstants.MAX_PULL_SIZE) != 0) {
                motionLayout.setTransition(pf0Var);
                motionLayout.setProgress(1.0f);
            } else {
                if (!z || (i & Options.DEFAULT_MAX_CONTROL_LINE) == 0) {
                    return;
                }
                motionLayout.setTransition(pf0Var);
                motionLayout.setProgress(0.0f);
            }
        }
    }
}
