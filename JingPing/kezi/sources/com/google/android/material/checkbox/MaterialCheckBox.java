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
import defpackage.cf0;
import defpackage.d1;
import defpackage.d8;
import defpackage.ef;
import defpackage.ff;
import defpackage.hk;
import defpackage.hl;
import defpackage.k3;
import defpackage.k9;
import defpackage.l50;
import defpackage.m50;
import defpackage.nv0;
import defpackage.o3;
import defpackage.p3;
import defpackage.po;
import defpackage.q3;
import defpackage.rp0;
import defpackage.un0;
import defpackage.we0;
import defpackage.xi;
import defpackage.y50;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCheckBox extends AppCompatCheckBox {
    public static final int[] B;
    public static final int[][] C;
    public static final int D;
    public final LinkedHashSet f;
    public final LinkedHashSet g;
    public ColorStateList h;
    public boolean i;
    public boolean j;
    public boolean k;
    public CharSequence l;
    public Drawable m;
    public Drawable n;
    public boolean o;
    public ColorStateList p;
    public ColorStateList q;
    public PorterDuff.Mode r;
    public int s;
    public int[] t;
    public boolean u;
    public CharSequence v;
    public CompoundButton.OnCheckedChangeListener w;
    public final q3 x;
    public final d8 y;
    public static final int z = R$style.Widget_MaterialComponents_CompoundButton_CheckBox;
    public static final int[] A = {R$attr.state_indeterminate};

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int b;

        public final String toString() {
            StringBuilder sb = new StringBuilder("MaterialCheckBox.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" CheckedState=");
            int i = this.b;
            return hk.k(sb, i != 1 ? i != 2 ? "unchecked" : "indeterminate" : "checked", "}");
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Integer.valueOf(this.b));
        }
    }

    static {
        int i = R$attr.state_error;
        B = new int[]{i};
        C = new int[][]{new int[]{R.attr.state_enabled, i}, new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
        D = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", "android");
    }

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    private String getButtonStateDescription() {
        int i = this.s;
        return i == 1 ? getResources().getString(R$string.mtrl_checkbox_state_description_checked) : i == 0 ? getResources().getString(R$string.mtrl_checkbox_state_description_unchecked) : getResources().getString(R$string.mtrl_checkbox_state_description_indeterminate);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.h == null) {
            int iQ = k9.q(this, R$attr.colorControlActivated);
            int iQ2 = k9.q(this, R$attr.colorError);
            int iQ3 = k9.q(this, R$attr.colorSurface);
            int iQ4 = k9.q(this, R$attr.colorOnSurface);
            this.h = new ColorStateList(C, new int[]{k9.x(iQ3, 1.0f, iQ2), k9.x(iQ3, 1.0f, iQ), k9.x(iQ3, 0.54f, iQ4), k9.x(iQ3, 0.38f, iQ4), k9.x(iQ3, 0.38f, iQ4)});
        }
        return this.h;
    }

    private ColorStateList getSuperButtonTintList() {
        ColorStateList colorStateList = this.p;
        return colorStateList != null ? colorStateList : super.getButtonTintList() != null ? super.getButtonTintList() : getSupportButtonTintList();
    }

    public final void a() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        d1 d1Var;
        this.m = k9.l(this.m, this.p, ef.b(this));
        this.n = k9.l(this.n, this.q, this.r);
        if (this.o) {
            q3 q3Var = this.x;
            if (q3Var != null) {
                Drawable drawable = q3Var.b;
                d8 d8Var = this.y;
                if (drawable != null) {
                    AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                    if (d8Var.a == null) {
                        d8Var.a = new k3(d8Var);
                    }
                    animatedVectorDrawable.unregisterAnimationCallback(d8Var.a);
                }
                ArrayList arrayList = q3Var.f;
                o3 o3Var = q3Var.c;
                if (arrayList != null && d8Var != null) {
                    arrayList.remove(d8Var);
                    if (q3Var.f.size() == 0 && (d1Var = q3Var.e) != null) {
                        o3Var.b.removeListener(d1Var);
                        q3Var.e = null;
                    }
                }
                Drawable drawable2 = q3Var.b;
                if (drawable2 != null) {
                    AnimatedVectorDrawable animatedVectorDrawable2 = (AnimatedVectorDrawable) drawable2;
                    if (d8Var.a == null) {
                        d8Var.a = new k3(d8Var);
                    }
                    animatedVectorDrawable2.registerAnimationCallback(d8Var.a);
                } else if (d8Var != null) {
                    if (q3Var.f == null) {
                        q3Var.f = new ArrayList();
                    }
                    if (!q3Var.f.contains(d8Var)) {
                        q3Var.f.add(d8Var);
                        if (q3Var.e == null) {
                            q3Var.e = new d1(1, q3Var);
                        }
                        o3Var.b.addListener(q3Var.e);
                    }
                }
            }
            Drawable drawable3 = this.m;
            if ((drawable3 instanceof AnimatedStateListDrawable) && q3Var != null) {
                ((AnimatedStateListDrawable) drawable3).addTransition(R$id.checked, R$id.unchecked, q3Var, false);
                ((AnimatedStateListDrawable) this.m).addTransition(R$id.indeterminate, R$id.unchecked, q3Var, false);
            }
        }
        Drawable drawable4 = this.m;
        if (drawable4 != null && (colorStateList2 = this.p) != null) {
            hl.h(drawable4, colorStateList2);
        }
        Drawable drawable5 = this.n;
        if (drawable5 != null && (colorStateList = this.q) != null) {
            hl.h(drawable5, colorStateList);
        }
        super.setButtonDrawable(k9.k(this.m, this.n, -1, -1));
        refreshDrawableState();
    }

    public void addOnCheckedStateChangedListener(l50 l50Var) {
        this.g.add(l50Var);
    }

    public void addOnErrorChangedListener(m50 m50Var) {
        this.f.add(m50Var);
    }

    @Override // android.widget.CompoundButton
    public Drawable getButtonDrawable() {
        return this.m;
    }

    public Drawable getButtonIconDrawable() {
        return this.n;
    }

    public ColorStateList getButtonIconTintList() {
        return this.q;
    }

    public PorterDuff.Mode getButtonIconTintMode() {
        return this.r;
    }

    @Override // android.widget.CompoundButton
    public ColorStateList getButtonTintList() {
        return this.p;
    }

    public int getCheckedState() {
        return this.s;
    }

    public CharSequence getErrorAccessibilityLabel() {
        return this.l;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final boolean isChecked() {
        return this.s == 1;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.i && this.p == null && this.q == null) {
            setUseMaterialThemeColors(true);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (getCheckedState() == 2) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, A);
        }
        if (this.k) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, B);
        }
        this.t = k9.o(iArrOnCreateDrawableState);
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        Drawable drawableA;
        if (!this.j || !TextUtils.isEmpty(getText()) || (drawableA = ff.a(this)) == null) {
            super.onDraw(canvas);
            return;
        }
        int width = ((getWidth() - drawableA.getIntrinsicWidth()) / 2) * (rp0.I(this) ? -1 : 1);
        int iSave = canvas.save();
        canvas.translate(width, 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(iSave);
        if (getBackground() != null) {
            Rect bounds = drawableA.getBounds();
            hl.f(getBackground(), bounds.left + width, bounds.top, bounds.right + width, bounds.bottom);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && this.k) {
            accessibilityNodeInfo.setText(((Object) accessibilityNodeInfo.getText()) + ", " + ((Object) this.l));
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
        setCheckedState(savedState.b);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.b = getCheckedState();
        return savedState;
    }

    public void removeOnCheckedStateChangedListener(l50 l50Var) {
        this.g.remove(l50Var);
    }

    public void removeOnErrorChangedListener(m50 m50Var) {
        this.f.remove(m50Var);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(rp0.y(getContext(), i));
    }

    public void setButtonIconDrawable(Drawable drawable) {
        this.n = drawable;
        a();
    }

    public void setButtonIconDrawableResource(int i) {
        setButtonIconDrawable(rp0.y(getContext(), i));
    }

    public void setButtonIconTintList(ColorStateList colorStateList) {
        if (this.q == colorStateList) {
            return;
        }
        this.q = colorStateList;
        a();
    }

    public void setButtonIconTintMode(PorterDuff.Mode mode) {
        if (this.r == mode) {
            return;
        }
        this.r = mode;
        a();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintList(ColorStateList colorStateList) {
        if (this.p == colorStateList) {
            return;
        }
        this.p = colorStateList;
        a();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintMode(PorterDuff.Mode mode) {
        setSupportButtonTintMode(mode);
        a();
    }

    public void setCenterIfNoTextEnabled(boolean z2) {
        this.j = z2;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z2) {
        setCheckedState(z2 ? 1 : 0);
    }

    public void setCheckedState(int i) {
        AutofillManager autofillManager;
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        if (this.s != i) {
            this.s = i;
            super.setChecked(i == 1);
            refreshDrawableState();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 30 && this.v == null) {
                super.setStateDescription(getButtonStateDescription());
            }
            if (this.u) {
                return;
            }
            this.u = true;
            LinkedHashSet linkedHashSet = this.g;
            if (linkedHashSet != null) {
                Iterator it = linkedHashSet.iterator();
                if (it.hasNext()) {
                    throw hk.e(it);
                }
            }
            if (this.s != 2 && (onCheckedChangeListener = this.w) != null) {
                onCheckedChangeListener.onCheckedChanged(this, isChecked());
            }
            if (i2 >= 26 && (autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class)) != null) {
                autofillManager.notifyValueChanged(this);
            }
            this.u = false;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
    }

    public void setErrorAccessibilityLabel(CharSequence charSequence) {
        this.l = charSequence;
    }

    public void setErrorAccessibilityLabelResource(int i) {
        setErrorAccessibilityLabel(i != 0 ? getResources().getText(i) : null);
    }

    public void setErrorShown(boolean z2) {
        if (this.k == z2) {
            return;
        }
        this.k = z2;
        refreshDrawableState();
        Iterator it = this.f.iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.w = onCheckedChangeListener;
    }

    @Override // android.widget.CompoundButton, android.view.View
    public void setStateDescription(CharSequence charSequence) {
        this.v = charSequence;
        if (charSequence != null) {
            super.setStateDescription(charSequence);
        } else {
            if (Build.VERSION.SDK_INT < 30 || charSequence != null) {
                return;
            }
            super.setStateDescription(getButtonStateDescription());
        }
    }

    public void setUseMaterialThemeColors(boolean z2) {
        this.i = z2;
        if (z2) {
            ef.c(this, getMaterialThemeColorsTintList());
        } else {
            ef.c(this, null);
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
        this.m = drawable;
        this.o = false;
        a();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i) {
        int i2 = z;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.f = new LinkedHashSet();
        this.g = new LinkedHashSet();
        Context context2 = getContext();
        int i3 = R$drawable.mtrl_checkbox_button_checked_unchecked;
        q3 q3Var = new q3(context2);
        Resources resources = context2.getResources();
        Resources.Theme theme = context2.getTheme();
        ThreadLocal threadLocal = cf0.a;
        Drawable drawableA = we0.a(resources, i3, theme);
        q3Var.b = drawableA;
        drawableA.setCallback(q3Var.g);
        new p3(q3Var.b.getConstantState());
        this.x = q3Var;
        this.y = new d8(this, 2);
        Context context3 = getContext();
        this.m = ff.a(this);
        this.p = getSuperButtonTintList();
        setSupportButtonTintList(null);
        po poVarE = un0.e(context3, attributeSet, R$styleable.MaterialCheckBox, i, i2, new int[0]);
        this.n = poVarE.y(R$styleable.MaterialCheckBox_buttonIcon);
        Drawable drawable = this.m;
        TypedArray typedArray = (TypedArray) poVarE.d;
        if (drawable != null && nv0.K(context3, R$attr.isMaterial3Theme, false)) {
            int resourceId = typedArray.getResourceId(R$styleable.MaterialCheckBox_android_button, 0);
            int resourceId2 = typedArray.getResourceId(R$styleable.MaterialCheckBox_buttonCompat, 0);
            if (resourceId == D && resourceId2 == 0) {
                super.setButtonDrawable((Drawable) null);
                this.m = rp0.y(context3, R$drawable.mtrl_checkbox_button);
                this.o = true;
                if (this.n == null) {
                    this.n = rp0.y(context3, R$drawable.mtrl_checkbox_button_icon);
                }
            }
        }
        this.q = xi.r(context3, poVarE, R$styleable.MaterialCheckBox_buttonIconTint);
        this.r = rp0.O(typedArray.getInt(R$styleable.MaterialCheckBox_buttonIconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.i = typedArray.getBoolean(R$styleable.MaterialCheckBox_useMaterialThemeColors, false);
        this.j = typedArray.getBoolean(R$styleable.MaterialCheckBox_centerIfNoTextEnabled, true);
        this.k = typedArray.getBoolean(R$styleable.MaterialCheckBox_errorShown, false);
        this.l = typedArray.getText(R$styleable.MaterialCheckBox_errorAccessibilityLabel);
        if (typedArray.hasValue(R$styleable.MaterialCheckBox_checkedState)) {
            setCheckedState(typedArray.getInt(R$styleable.MaterialCheckBox_checkedState, 0));
        }
        poVarE.P();
        a();
    }
}
