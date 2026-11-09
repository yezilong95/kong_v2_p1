package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.d;

/* JADX INFO: loaded from: classes.dex */
public final class vh {
    public boolean a;
    public int b;
    public int c;
    public float d;
    public float e;

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PropertySet);
        this.a = true;
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R$styleable.PropertySet_android_alpha) {
                this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
            } else if (index == R$styleable.PropertySet_android_visibility) {
                int i2 = typedArrayObtainStyledAttributes.getInt(index, this.b);
                this.b = i2;
                this.b = d.d[i2];
            } else if (index == R$styleable.PropertySet_visibilityMode) {
                this.c = typedArrayObtainStyledAttributes.getInt(index, this.c);
            } else if (index == R$styleable.PropertySet_motionProgress) {
                this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
