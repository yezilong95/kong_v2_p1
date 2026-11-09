package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.d;

/* JADX INFO: loaded from: classes.dex */
public final class uh {
    public static final SparseIntArray h;
    public boolean a;
    public int b;
    public String c;
    public int d;
    public int e;
    public float f;
    public float g;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        h = sparseIntArray;
        sparseIntArray.append(R$styleable.Motion_motionPathRotate, 1);
        sparseIntArray.append(R$styleable.Motion_pathMotionArc, 2);
        sparseIntArray.append(R$styleable.Motion_transitionEasing, 3);
        sparseIntArray.append(R$styleable.Motion_drawPath, 4);
        sparseIntArray.append(R$styleable.Motion_animate_relativeTo, 5);
        sparseIntArray.append(R$styleable.Motion_motionStagger, 6);
    }

    public final void a(uh uhVar) {
        this.a = uhVar.a;
        this.b = uhVar.b;
        this.c = uhVar.c;
        this.d = uhVar.d;
        this.e = uhVar.e;
        this.g = uhVar.g;
        this.f = uhVar.f;
    }

    public final void b(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Motion);
        this.a = true;
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            switch (h.get(index)) {
                case 1:
                    this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                    break;
                case 2:
                    this.d = typedArrayObtainStyledAttributes.getInt(index, this.d);
                    break;
                case 3:
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        this.c = typedArrayObtainStyledAttributes.getString(index);
                    } else {
                        this.c = vr.d[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                    }
                    break;
                case 4:
                    this.e = typedArrayObtainStyledAttributes.getInt(index, 0);
                    break;
                case 5:
                    this.b = d.j(typedArrayObtainStyledAttributes, index, this.b);
                    break;
                case 6:
                    this.f = typedArrayObtainStyledAttributes.getFloat(index, this.f);
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
