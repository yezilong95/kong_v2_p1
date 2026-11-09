package com.google.android.material.switchmaterial;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.bn;
import defpackage.k9;
import defpackage.lr0;
import defpackage.un0;
import defpackage.xr0;
import defpackage.y50;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwitchMaterial extends SwitchCompat {
    public static final int b0 = R$style.Widget_MaterialComponents_CompoundButton_Switch;
    public static final int[][] c0 = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public final bn U;
    public ColorStateList V;
    public ColorStateList W;
    public boolean a0;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.V == null) {
            int iQ = k9.q(this, R$attr.colorSurface);
            int iQ2 = k9.q(this, R$attr.colorControlActivated);
            float dimension = getResources().getDimension(R$dimen.mtrl_switch_thumb_elevation);
            bn bnVar = this.U;
            if (bnVar.a) {
                float fI = 0.0f;
                for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
                    WeakHashMap weakHashMap = xr0.a;
                    fI += lr0.i((View) parent);
                }
                dimension += fI;
            }
            int iB = bnVar.b(iQ, dimension);
            this.V = new ColorStateList(c0, new int[]{k9.x(iQ, 1.0f, iQ2), iB, k9.x(iQ, 0.38f, iQ2), iB});
        }
        return this.V;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.W == null) {
            int iQ = k9.q(this, R$attr.colorSurface);
            int iQ2 = k9.q(this, R$attr.colorControlActivated);
            int iQ3 = k9.q(this, R$attr.colorOnSurface);
            this.W = new ColorStateList(c0, new int[]{k9.x(iQ, 0.54f, iQ2), k9.x(iQ, 0.32f, iQ3), k9.x(iQ, 0.12f, iQ2), k9.x(iQ, 0.12f, iQ3)});
        }
        return this.W;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a0 && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.a0 && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.a0 = z;
        if (z) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        } else {
            setThumbTintList(null);
            setTrackTintList(null);
        }
    }

    public SwitchMaterial(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SwitchMaterial(Context context, AttributeSet attributeSet, int i) {
        int i2 = b0;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        this.U = new bn(context2);
        int[] iArr = R$styleable.SwitchMaterial;
        un0.a(context2, attributeSet, i, i2);
        un0.b(context2, attributeSet, iArr, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.a0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SwitchMaterial_useMaterialThemeColors, false);
        typedArrayObtainStyledAttributes.recycle();
    }
}
