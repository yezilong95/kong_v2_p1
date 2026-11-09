package defpackage;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public abstract class aa {
    public int a;
    public int b;
    public int[] c;
    public int d;
    public int e;
    public int f;
    public int g;

    public aa(Context context, AttributeSet attributeSet, int i, int i2) {
        this.c = new int[0];
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.mtrl_progress_track_thickness);
        int[] iArr = R$styleable.BaseProgressIndicator;
        i01.a(context, attributeSet, i, i2);
        i01.b(context, attributeSet, iArr, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.a = la0.r(context, typedArrayObtainStyledAttributes, R$styleable.BaseProgressIndicator_trackThickness, dimensionPixelSize);
        this.b = Math.min(la0.r(context, typedArrayObtainStyledAttributes, R$styleable.BaseProgressIndicator_trackCornerRadius, 0), this.a / 2);
        this.e = typedArrayObtainStyledAttributes.getInt(R$styleable.BaseProgressIndicator_showAnimationBehavior, 0);
        this.f = typedArrayObtainStyledAttributes.getInt(R$styleable.BaseProgressIndicator_hideAnimationBehavior, 0);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.BaseProgressIndicator_indicatorTrackGapSize, 0);
        if (!typedArrayObtainStyledAttributes.hasValue(R$styleable.BaseProgressIndicator_indicatorColor)) {
            this.c = new int[]{fa0.O(context, R$attr.colorPrimary, -1)};
        } else if (typedArrayObtainStyledAttributes.peekValue(R$styleable.BaseProgressIndicator_indicatorColor).type != 1) {
            this.c = new int[]{typedArrayObtainStyledAttributes.getColor(R$styleable.BaseProgressIndicator_indicatorColor, -1)};
        } else {
            int[] intArray = context.getResources().getIntArray(typedArrayObtainStyledAttributes.getResourceId(R$styleable.BaseProgressIndicator_indicatorColor, -1));
            this.c = intArray;
            if (intArray.length == 0) {
                throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
            }
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BaseProgressIndicator_trackColor)) {
            this.d = typedArrayObtainStyledAttributes.getColor(R$styleable.BaseProgressIndicator_trackColor, -1);
        } else {
            this.d = this.c[0];
            TypedArray typedArrayObtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(new int[]{R.attr.disabledAlpha});
            float f = typedArrayObtainStyledAttributes2.getFloat(0, 0.2f);
            typedArrayObtainStyledAttributes2.recycle();
            this.d = fa0.q(this.d, (int) (f * 255.0f));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void a() {
        if (this.g < 0) {
            throw new IllegalArgumentException("indicatorTrackGapSize must be >= 0.");
        }
    }
}
