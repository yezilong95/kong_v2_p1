package com.google.android.material.checkbox;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.google.android.material.R$attr;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.ah;
import defpackage.bd0;
import defpackage.bp0;
import defpackage.cd0;
import defpackage.fa0;
import defpackage.i01;
import defpackage.ip;
import defpackage.la0;
import defpackage.ou;
import defpackage.p4;
import defpackage.pd0;
import defpackage.qm;
import defpackage.s90;
import defpackage.t4;
import defpackage.u4;
import defpackage.v4;
import defpackage.vo0;
import defpackage.z2;
import defpackage.z9;
import defpackage.zg;
import defpackage.zn;
import io.nats.client.support.JsonUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCheckBox extends AppCompatCheckBox {
    public static final int f0 = R$style.Widget_MaterialComponents_CompoundButton_CheckBox;
    public static final int[] g0 = {R$attr.state_indeterminate};
    public static final int[] h0;
    public static final int[][] i0;
    public static final int j0;
    public final LinkedHashSet I;
    public final LinkedHashSet J;
    public ColorStateList K;
    public boolean L;
    public boolean M;
    public boolean N;
    public CharSequence O;
    public Drawable P;
    public Drawable Q;
    public boolean R;
    public ColorStateList S;
    public ColorStateList T;
    public PorterDuff.Mode U;
    public int V;
    public int[] W;
    public boolean a0;
    public CharSequence b0;
    public CompoundButton.OnCheckedChangeListener c0;
    public final v4 d0;
    public final z9 e0;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int E;

        public final String toString() {
            StringBuilder sb = new StringBuilder("MaterialCheckBox.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" CheckedState=");
            int i = this.E;
            return zn.o(sb, i != 1 ? i != 2 ? "unchecked" : "indeterminate" : "checked", JsonUtils.CLOSE);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Integer.valueOf(this.E));
        }
    }

    static {
        int i = R$attr.state_error;
        h0 = new int[]{i};
        i0 = new int[][]{new int[]{R.attr.state_enabled, i}, new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
        j0 = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", "android");
    }

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    private String getButtonStateDescription() {
        int i = this.V;
        return i == 1 ? getResources().getString(R$string.mtrl_checkbox_state_description_checked) : i == 0 ? getResources().getString(R$string.mtrl_checkbox_state_description_unchecked) : getResources().getString(R$string.mtrl_checkbox_state_description_indeterminate);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.K == null) {
            int iP = fa0.P(this, R$attr.colorControlActivated);
            int iP2 = fa0.P(this, R$attr.colorError);
            int iP3 = fa0.P(this, R$attr.colorSurface);
            int iP4 = fa0.P(this, R$attr.colorOnSurface);
            this.K = new ColorStateList(i0, new int[]{fa0.k0(iP3, 1.0f, iP2), fa0.k0(iP3, 1.0f, iP), fa0.k0(iP3, 0.54f, iP4), fa0.k0(iP3, 0.38f, iP4), fa0.k0(iP3, 0.38f, iP4)});
        }
        return this.K;
    }

    private ColorStateList getSuperButtonTintList() {
        ColorStateList colorStateList = this.S;
        return colorStateList != null ? colorStateList : super.getButtonTintList() != null ? super.getButtonTintList() : getSupportButtonTintList();
    }

    public final void a() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        z2 z2Var;
        this.P = qm.k(this.P, this.S, zg.b(this));
        this.Q = qm.k(this.Q, this.T, this.U);
        if (this.R) {
            v4 v4Var = this.d0;
            if (v4Var != null) {
                Drawable drawable = v4Var.E;
                z9 z9Var = this.e0;
                if (drawable != null) {
                    AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                    if (z9Var.a == null) {
                        z9Var.a = new p4(z9Var);
                    }
                    animatedVectorDrawable.unregisterAnimationCallback(z9Var.a);
                }
                ArrayList arrayList = v4Var.I;
                t4 t4Var = v4Var.F;
                if (arrayList != null && z9Var != null) {
                    arrayList.remove(z9Var);
                    if (v4Var.I.size() == 0 && (z2Var = v4Var.H) != null) {
                        t4Var.b.removeListener(z2Var);
                        v4Var.H = null;
                    }
                }
                Drawable drawable2 = v4Var.E;
                if (drawable2 != null) {
                    AnimatedVectorDrawable animatedVectorDrawable2 = (AnimatedVectorDrawable) drawable2;
                    if (z9Var.a == null) {
                        z9Var.a = new p4(z9Var);
                    }
                    animatedVectorDrawable2.registerAnimationCallback(z9Var.a);
                } else if (z9Var != null) {
                    if (v4Var.I == null) {
                        v4Var.I = new ArrayList();
                    }
                    if (!v4Var.I.contains(z9Var)) {
                        v4Var.I.add(z9Var);
                        if (v4Var.H == null) {
                            v4Var.H = new z2(1, v4Var);
                        }
                        t4Var.b.addListener(v4Var.H);
                    }
                }
            }
            Drawable drawable3 = this.P;
            if ((drawable3 instanceof AnimatedStateListDrawable) && v4Var != null) {
                ((AnimatedStateListDrawable) drawable3).addTransition(R$id.checked, R$id.unchecked, v4Var, false);
                ((AnimatedStateListDrawable) this.P).addTransition(R$id.indeterminate, R$id.unchecked, v4Var, false);
            }
        }
        Drawable drawable4 = this.P;
        if (drawable4 != null && (colorStateList2 = this.S) != null) {
            ip.h(drawable4, colorStateList2);
        }
        Drawable drawable5 = this.Q;
        if (drawable5 != null && (colorStateList = this.T) != null) {
            ip.h(drawable5, colorStateList);
        }
        super.setButtonDrawable(qm.i(this.P, this.Q, -1, -1));
        refreshDrawableState();
    }

    public void addOnCheckedStateChangedListener(bd0 bd0Var) {
        this.J.add(bd0Var);
    }

    public void addOnErrorChangedListener(cd0 cd0Var) {
        this.I.add(cd0Var);
    }

    @Override // android.widget.CompoundButton
    public Drawable getButtonDrawable() {
        return this.P;
    }

    public Drawable getButtonIconDrawable() {
        return this.Q;
    }

    public ColorStateList getButtonIconTintList() {
        return this.T;
    }

    public PorterDuff.Mode getButtonIconTintMode() {
        return this.U;
    }

    @Override // android.widget.CompoundButton
    public ColorStateList getButtonTintList() {
        return this.S;
    }

    public int getCheckedState() {
        return this.V;
    }

    public CharSequence getErrorAccessibilityLabel() {
        return this.O;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final boolean isChecked() {
        return this.V == 1;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.L && this.S == null && this.T == null) {
            setUseMaterialThemeColors(true);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (getCheckedState() == 2) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, g0);
        }
        if (this.N) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, h0);
        }
        this.W = qm.r(iArrOnCreateDrawableState);
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        Drawable drawableA;
        if (!this.M || !TextUtils.isEmpty(getText()) || (drawableA = ah.a(this)) == null) {
            super.onDraw(canvas);
            return;
        }
        int width = ((getWidth() - drawableA.getIntrinsicWidth()) / 2) * (fa0.d0(this) ? -1 : 1);
        int iSave = canvas.save();
        canvas.translate(width, 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(iSave);
        if (getBackground() != null) {
            Rect bounds = drawableA.getBounds();
            ip.f(getBackground(), bounds.left + width, bounds.top, bounds.right + width, bounds.bottom);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && this.N) {
            accessibilityNodeInfo.setText(((Object) accessibilityNodeInfo.getText()) + ", " + ((Object) this.O));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCheckedState(savedState.E);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.E = getCheckedState();
        return savedState;
    }

    public void removeOnCheckedStateChangedListener(bd0 bd0Var) {
        this.J.remove(bd0Var);
    }

    public void removeOnErrorChangedListener(cd0 cd0Var) {
        this.I.remove(cd0Var);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(qm.z(getContext(), i));
    }

    public void setButtonIconDrawable(Drawable drawable) {
        this.Q = drawable;
        a();
    }

    public void setButtonIconDrawableResource(int i) {
        setButtonIconDrawable(qm.z(getContext(), i));
    }

    public void setButtonIconTintList(ColorStateList colorStateList) {
        if (this.T == colorStateList) {
            return;
        }
        this.T = colorStateList;
        a();
    }

    public void setButtonIconTintMode(PorterDuff.Mode mode) {
        if (this.U == mode) {
            return;
        }
        this.U = mode;
        a();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintList(ColorStateList colorStateList) {
        if (this.S == colorStateList) {
            return;
        }
        this.S = colorStateList;
        a();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintMode(PorterDuff.Mode mode) {
        setSupportButtonTintMode(mode);
        a();
    }

    public void setCenterIfNoTextEnabled(boolean z) {
        this.M = z;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        setCheckedState(z ? 1 : 0);
    }

    public void setCheckedState(int i) {
        AutofillManager autofillManager;
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        if (this.V != i) {
            this.V = i;
            super.setChecked(i == 1);
            refreshDrawableState();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 30 && this.b0 == null) {
                super.setStateDescription(getButtonStateDescription());
            }
            if (this.a0) {
                return;
            }
            this.a0 = true;
            LinkedHashSet linkedHashSet = this.J;
            if (linkedHashSet != null) {
                Iterator it = linkedHashSet.iterator();
                if (it.hasNext()) {
                    throw zn.i(it);
                }
            }
            if (this.V != 2 && (onCheckedChangeListener = this.c0) != null) {
                onCheckedChangeListener.onCheckedChanged(this, isChecked());
            }
            if (i2 >= 26 && (autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class)) != null) {
                autofillManager.notifyValueChanged(this);
            }
            this.a0 = false;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
    }

    public void setErrorAccessibilityLabel(CharSequence charSequence) {
        this.O = charSequence;
    }

    public void setErrorAccessibilityLabelResource(int i) {
        setErrorAccessibilityLabel(i != 0 ? getResources().getText(i) : null);
    }

    public void setErrorShown(boolean z) {
        if (this.N == z) {
            return;
        }
        this.N = z;
        refreshDrawableState();
        Iterator it = this.I.iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.c0 = onCheckedChangeListener;
    }

    @Override // android.widget.CompoundButton, android.view.View
    public void setStateDescription(CharSequence charSequence) {
        this.b0 = charSequence;
        if (charSequence != null) {
            super.setStateDescription(charSequence);
        } else {
            if (Build.VERSION.SDK_INT < 30 || charSequence != null) {
                return;
            }
            super.setStateDescription(getButtonStateDescription());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.L = z;
        if (z) {
            zg.c(this, getMaterialThemeColorsTintList());
        } else {
            zg.c(this, null);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final void toggle() {
        setChecked(!isChecked());
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        this.P = drawable;
        this.R = false;
        a();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i) {
        int i2 = f0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.I = new LinkedHashSet();
        this.J = new LinkedHashSet();
        Context context2 = getContext();
        int i3 = R$drawable.mtrl_checkbox_button_checked_unchecked;
        v4 v4Var = new v4(context2);
        Resources resources = context2.getResources();
        Resources.Theme theme = context2.getTheme();
        ThreadLocal threadLocal = bp0.a;
        Drawable drawableA = vo0.a(resources, i3, theme);
        v4Var.E = drawableA;
        drawableA.setCallback(v4Var.J);
        new u4(v4Var.E.getConstantState());
        this.d0 = v4Var;
        this.e0 = new z9(this, 2);
        Context context3 = getContext();
        this.P = ah.a(this);
        this.S = getSuperButtonTintList();
        setSupportButtonTintList(null);
        ou ouVarE = i01.e(context3, attributeSet, R$styleable.MaterialCheckBox, i, i2, new int[0]);
        this.Q = ouVarE.A(R$styleable.MaterialCheckBox_buttonIcon);
        Drawable drawable = this.P;
        TypedArray typedArray = (TypedArray) ouVarE.G;
        if (drawable != null && s90.T(context3, R$attr.isMaterial3Theme, false)) {
            int resourceId = typedArray.getResourceId(R$styleable.MaterialCheckBox_android_button, 0);
            int resourceId2 = typedArray.getResourceId(R$styleable.MaterialCheckBox_buttonCompat, 0);
            if (resourceId == j0 && resourceId2 == 0) {
                super.setButtonDrawable((Drawable) null);
                this.P = qm.z(context3, R$drawable.mtrl_checkbox_button);
                this.R = true;
                if (this.Q == null) {
                    this.Q = qm.z(context3, R$drawable.mtrl_checkbox_button_icon);
                }
            }
        }
        this.T = la0.o(context3, ouVarE, R$styleable.MaterialCheckBox_buttonIconTint);
        this.U = fa0.p0(typedArray.getInt(R$styleable.MaterialCheckBox_buttonIconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.L = typedArray.getBoolean(R$styleable.MaterialCheckBox_useMaterialThemeColors, false);
        this.M = typedArray.getBoolean(R$styleable.MaterialCheckBox_centerIfNoTextEnabled, true);
        this.N = typedArray.getBoolean(R$styleable.MaterialCheckBox_errorShown, false);
        this.O = typedArray.getText(R$styleable.MaterialCheckBox_errorAccessibilityLabel);
        if (typedArray.hasValue(R$styleable.MaterialCheckBox_checkedState)) {
            setCheckedState(typedArray.getInt(R$styleable.MaterialCheckBox_checkedState, 0));
        }
        ouVarE.S();
        a();
    }
}
