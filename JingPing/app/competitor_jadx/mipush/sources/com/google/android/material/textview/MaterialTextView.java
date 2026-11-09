package com.google.android.material.textview;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import defpackage.la0;
import defpackage.pd0;
import defpackage.s90;

/* JADX INFO: loaded from: classes.dex */
public class MaterialTextView extends AppCompatTextView {
    public MaterialTextView(Context context) {
        this(context, null);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (s90.T(context, R$attr.textAppearanceLineHeightEnabled, true)) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
            Context context2 = getContext();
            int[] iArr = {R$styleable.MaterialTextAppearance_android_lineHeight, R$styleable.MaterialTextAppearance_lineHeight};
            int iR = -1;
            for (int i2 = 0; i2 < 2 && iR < 0; i2++) {
                iR = la0.r(context2, typedArrayObtainStyledAttributes, iArr[i2], -1);
            }
            typedArrayObtainStyledAttributes.recycle();
            if (iR >= 0) {
                setLineHeight(iR);
            }
        }
    }

    public MaterialTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MaterialTextView(Context context, AttributeSet attributeSet, int i) {
        super(pd0.a(context, attributeSet, i, 0), attributeSet, i);
        Context context2 = getContext();
        if (s90.T(context2, R$attr.textAppearanceLineHeightEnabled, true)) {
            Resources.Theme theme = context2.getTheme();
            TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, R$styleable.MaterialTextView, i, 0);
            int[] iArr = {R$styleable.MaterialTextView_android_lineHeight, R$styleable.MaterialTextView_lineHeight};
            int iR = -1;
            for (int i2 = 0; i2 < 2 && iR < 0; i2++) {
                iR = la0.r(context2, typedArrayObtainStyledAttributes, iArr[i2], -1);
            }
            typedArrayObtainStyledAttributes.recycle();
            if (iR != -1) {
                return;
            }
            TypedArray typedArrayObtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, R$styleable.MaterialTextView, i, 0);
            int resourceId = typedArrayObtainStyledAttributes2.getResourceId(R$styleable.MaterialTextView_android_textAppearance, -1);
            typedArrayObtainStyledAttributes2.recycle();
            if (resourceId != -1) {
                TypedArray typedArrayObtainStyledAttributes3 = theme.obtainStyledAttributes(resourceId, R$styleable.MaterialTextAppearance);
                Context context3 = getContext();
                int[] iArr2 = {R$styleable.MaterialTextAppearance_android_lineHeight, R$styleable.MaterialTextAppearance_lineHeight};
                int iR2 = -1;
                for (int i3 = 0; i3 < 2 && iR2 < 0; i3++) {
                    iR2 = la0.r(context3, typedArrayObtainStyledAttributes3, iArr2[i3], -1);
                }
                typedArrayObtainStyledAttributes3.recycle();
                if (iR2 >= 0) {
                    setLineHeight(iR2);
                }
            }
        }
    }
}
