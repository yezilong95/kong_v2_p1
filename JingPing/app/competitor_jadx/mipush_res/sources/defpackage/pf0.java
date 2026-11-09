package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.SparseArray;
import android.util.Xml;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.d;
import io.nats.client.support.Status;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class pf0 {
    public final int a;
    public final boolean b;
    public int c;
    public int d;
    public final int e;
    public final String f;
    public final int g;
    public int h;
    public final float i;
    public final qf0 j;
    public final ArrayList k;
    public l11 l;
    public final ArrayList m;
    public final int n;
    public final boolean o;
    public final int p;
    public final int q;
    public final int r;

    public pf0(qf0 qf0Var, pf0 pf0Var) {
        this.a = -1;
        this.b = false;
        this.c = -1;
        this.d = -1;
        this.e = 0;
        this.f = null;
        this.g = -1;
        this.h = Status.BAD_REQUEST_CODE;
        this.i = 0.0f;
        this.k = new ArrayList();
        this.l = null;
        this.m = new ArrayList();
        this.n = 0;
        this.o = false;
        this.p = -1;
        this.q = 0;
        this.r = 0;
        this.j = qf0Var;
        if (pf0Var != null) {
            this.p = pf0Var.p;
            this.e = pf0Var.e;
            this.f = pf0Var.f;
            this.g = pf0Var.g;
            this.h = pf0Var.h;
            this.k = pf0Var.k;
            this.i = pf0Var.i;
            this.q = pf0Var.q;
        }
    }

    public pf0(qf0 qf0Var, Context context, XmlResourceParser xmlResourceParser) {
        this.a = -1;
        this.b = false;
        this.c = -1;
        this.d = -1;
        this.e = 0;
        this.f = null;
        this.g = -1;
        this.h = Status.BAD_REQUEST_CODE;
        this.i = 0.0f;
        this.k = new ArrayList();
        this.l = null;
        this.m = new ArrayList();
        this.n = 0;
        this.o = false;
        this.p = -1;
        this.q = 0;
        this.r = 0;
        this.h = qf0Var.j;
        this.q = qf0Var.k;
        this.j = qf0Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.Transition);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            int i2 = R$styleable.Transition_constraintSetEnd;
            SparseArray sparseArray = qf0Var.g;
            if (index == i2) {
                this.c = typedArrayObtainStyledAttributes.getResourceId(index, this.c);
                if ("layout".equals(context.getResources().getResourceTypeName(this.c))) {
                    d dVar = new d();
                    dVar.h(context, this.c);
                    sparseArray.append(this.c, dVar);
                }
            } else if (index == R$styleable.Transition_constraintSetStart) {
                this.d = typedArrayObtainStyledAttributes.getResourceId(index, this.d);
                if ("layout".equals(context.getResources().getResourceTypeName(this.d))) {
                    d dVar2 = new d();
                    dVar2.h(context, this.d);
                    sparseArray.append(this.d, dVar2);
                }
            } else if (index == R$styleable.Transition_motionInterpolator) {
                int i3 = typedArrayObtainStyledAttributes.peekValue(index).type;
                if (i3 == 1) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                    this.g = resourceId;
                    if (resourceId != -1) {
                        this.e = -2;
                    }
                } else if (i3 == 3) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f = string;
                    if (string.indexOf("/") > 0) {
                        this.g = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                        this.e = -2;
                    } else {
                        this.e = -1;
                    }
                } else {
                    this.e = typedArrayObtainStyledAttributes.getInteger(index, this.e);
                }
            } else if (index == R$styleable.Transition_duration) {
                this.h = typedArrayObtainStyledAttributes.getInt(index, this.h);
            } else if (index == R$styleable.Transition_staggered) {
                this.i = typedArrayObtainStyledAttributes.getFloat(index, this.i);
            } else if (index == R$styleable.Transition_autoTransition) {
                this.n = typedArrayObtainStyledAttributes.getInteger(index, this.n);
            } else if (index == R$styleable.Transition_android_id) {
                this.a = typedArrayObtainStyledAttributes.getResourceId(index, this.a);
            } else if (index == R$styleable.Transition_transitionDisable) {
                this.o = typedArrayObtainStyledAttributes.getBoolean(index, this.o);
            } else if (index == R$styleable.Transition_pathMotionArc) {
                this.p = typedArrayObtainStyledAttributes.getInteger(index, -1);
            } else if (index == R$styleable.Transition_layoutDuringTransition) {
                this.q = typedArrayObtainStyledAttributes.getInteger(index, 0);
            } else if (index == R$styleable.Transition_transitionFlags) {
                this.r = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        if (this.d == -1) {
            this.b = true;
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
