package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.RectF;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public final class zo0 {
    public static final float[][] v = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    public static final float[][] w = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public float g;
    public float h;
    public float i;
    public float j;
    public boolean k = false;
    public final float[] l = new float[2];
    public float m;
    public float n;
    public final MotionLayout o;
    public final float p;
    public final float q;
    public final boolean r;
    public final float s;
    public final int t;
    public final float u;

    public zo0(Context context, MotionLayout motionLayout, XmlResourceParser xmlResourceParser) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = 0.5f;
        this.h = 0.5f;
        this.i = 0.0f;
        this.j = 1.0f;
        this.p = 4.0f;
        this.q = 1.2f;
        this.r = true;
        this.s = 1.0f;
        this.t = 0;
        this.u = 10.0f;
        this.o = motionLayout;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.OnSwipe);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R$styleable.OnSwipe_touchAnchorId) {
                this.d = typedArrayObtainStyledAttributes.getResourceId(index, this.d);
            } else if (index == R$styleable.OnSwipe_touchAnchorSide) {
                int i2 = typedArrayObtainStyledAttributes.getInt(index, this.a);
                this.a = i2;
                float[] fArr = v[i2];
                this.h = fArr[0];
                this.g = fArr[1];
            } else if (index == R$styleable.OnSwipe_dragDirection) {
                int i3 = typedArrayObtainStyledAttributes.getInt(index, this.b);
                this.b = i3;
                float[] fArr2 = w[i3];
                this.i = fArr2[0];
                this.j = fArr2[1];
            } else if (index == R$styleable.OnSwipe_maxVelocity) {
                this.p = typedArrayObtainStyledAttributes.getFloat(index, this.p);
            } else if (index == R$styleable.OnSwipe_maxAcceleration) {
                this.q = typedArrayObtainStyledAttributes.getFloat(index, this.q);
            } else if (index == R$styleable.OnSwipe_moveWhenScrollAtTop) {
                this.r = typedArrayObtainStyledAttributes.getBoolean(index, this.r);
            } else if (index == R$styleable.OnSwipe_dragScale) {
                this.s = typedArrayObtainStyledAttributes.getFloat(index, this.s);
            } else if (index == R$styleable.OnSwipe_dragThreshold) {
                this.u = typedArrayObtainStyledAttributes.getFloat(index, this.u);
            } else if (index == R$styleable.OnSwipe_touchRegionId) {
                this.e = typedArrayObtainStyledAttributes.getResourceId(index, this.e);
            } else if (index == R$styleable.OnSwipe_onTouchUp) {
                this.c = typedArrayObtainStyledAttributes.getInt(index, this.c);
            } else if (index == R$styleable.OnSwipe_nestedScrollFlags) {
                this.t = typedArrayObtainStyledAttributes.getInteger(index, 0);
            } else if (index == R$styleable.OnSwipe_limitBoundsTo) {
                this.f = typedArrayObtainStyledAttributes.getResourceId(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final RectF a(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i = this.e;
        if (i == -1 || (viewFindViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public final void b(boolean z) {
        float[][] fArr = v;
        float[][] fArr2 = w;
        if (z) {
            fArr2[4] = fArr2[3];
            fArr2[5] = fArr2[2];
            fArr[5] = fArr[2];
            fArr[6] = fArr[1];
        } else {
            fArr2[4] = fArr2[2];
            fArr2[5] = fArr2[3];
            fArr[5] = fArr[1];
            fArr[6] = fArr[2];
        }
        float[] fArr3 = fArr[this.a];
        this.h = fArr3[0];
        this.g = fArr3[1];
        float[] fArr4 = fArr2[this.b];
        this.i = fArr4[0];
        this.j = fArr4[1];
    }

    public final String toString() {
        return this.i + " , " + this.j;
    }
}
