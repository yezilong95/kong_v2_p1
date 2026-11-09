package com.google.android.material.radiobutton;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.fa0;
import defpackage.i01;
import defpackage.la0;
import defpackage.pd0;
import defpackage.zg;

/* JADX INFO: loaded from: classes.dex */
public class MaterialRadioButton extends AppCompatRadioButton {
    public static final int K = R$style.Widget_MaterialComponents_CompoundButton_RadioButton;
    public static final int[][] L = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public ColorStateList I;
    public boolean J;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.I == null) {
            int iP = fa0.P(this, R$attr.colorControlActivated);
            int iP2 = fa0.P(this, R$attr.colorOnSurface);
            int iP3 = fa0.P(this, R$attr.colorSurface);
            this.I = new ColorStateList(L, new int[]{fa0.k0(iP3, 1.0f, iP), fa0.k0(iP3, 0.54f, iP2), fa0.k0(iP3, 0.38f, iP2), fa0.k0(iP3, 0.38f, iP2)});
        }
        return this.I;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.J && zg.a(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.J = z;
        if (z) {
            zg.c(this, getMaterialThemeColorsTintList());
        } else {
            zg.c(this, null);
        }
    }

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialRadioButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = K;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.MaterialRadioButton, i, i2, new int[0]);
        if (typedArrayD.hasValue(R$styleable.MaterialRadioButton_buttonTint)) {
            zg.c(this, la0.p(context2, typedArrayD, R$styleable.MaterialRadioButton_buttonTint));
        }
        this.J = typedArrayD.getBoolean(R$styleable.MaterialRadioButton_useMaterialThemeColors, false);
        typedArrayD.recycle();
    }
}
