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
import defpackage.ef;
import defpackage.k9;
import defpackage.un0;
import defpackage.xi;
import defpackage.y50;

/* JADX INFO: loaded from: classes.dex */
public class MaterialRadioButton extends AppCompatRadioButton {
    public static final int h = R$style.Widget_MaterialComponents_CompoundButton_RadioButton;
    public static final int[][] i = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public ColorStateList f;
    public boolean g;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f == null) {
            int iQ = k9.q(this, R$attr.colorControlActivated);
            int iQ2 = k9.q(this, R$attr.colorOnSurface);
            int iQ3 = k9.q(this, R$attr.colorSurface);
            this.f = new ColorStateList(i, new int[]{k9.x(iQ3, 1.0f, iQ), k9.x(iQ3, 0.54f, iQ2), k9.x(iQ3, 0.38f, iQ2), k9.x(iQ3, 0.38f, iQ2)});
        }
        return this.f;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.g && ef.a(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.g = z;
        if (z) {
            ef.c(this, getMaterialThemeColorsTintList());
        } else {
            ef.c(this, null);
        }
    }

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialRadioButton(Context context, AttributeSet attributeSet, int i2) {
        int i3 = h;
        super(y50.a(context, attributeSet, i2, i3), attributeSet, i2);
        Context context2 = getContext();
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.MaterialRadioButton, i2, i3, new int[0]);
        if (typedArrayD.hasValue(R$styleable.MaterialRadioButton_buttonTint)) {
            ef.c(this, xi.s(context2, typedArrayD, R$styleable.MaterialRadioButton_buttonTint));
        }
        this.g = typedArrayD.getBoolean(R$styleable.MaterialRadioButton_useMaterialThemeColors, false);
        typedArrayD.recycle();
    }
}
