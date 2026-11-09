package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.Filterable;
import android.widget.ListAdapter;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.ListPopupWindow;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.n5;
import defpackage.u40;
import defpackage.u50;
import defpackage.un0;
import defpackage.xi;
import defpackage.y50;
import java.util.Locale;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    public final ListPopupWindow f;
    public final AccessibilityManager g;
    public final Rect h;
    public final int i;
    public final float j;
    public ColorStateList k;
    public int l;
    public ColorStateList m;

    public MaterialAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public static void a(MaterialAutoCompleteTextView materialAutoCompleteTextView, Object obj) {
        materialAutoCompleteTextView.setText(materialAutoCompleteTextView.convertSelectionToString(obj), false);
    }

    public final TextInputLayout b() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    @Override // android.widget.AutoCompleteTextView
    public final void dismissDropDown() {
        AccessibilityManager accessibilityManager = this.g;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.dismissDropDown();
        } else {
            this.f.dismiss();
        }
    }

    public ColorStateList getDropDownBackgroundTintList() {
        return this.k;
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout textInputLayoutB = b();
        return (textInputLayoutB == null || !textInputLayoutB.F) ? super.getHint() : textInputLayoutB.getHint();
    }

    public float getPopupElevation() {
        return this.j;
    }

    public int getSimpleItemSelectedColor() {
        return this.l;
    }

    public ColorStateList getSimpleItemSelectedRippleColor() {
        return this.m;
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayoutB = b();
        if (textInputLayoutB != null && textInputLayoutB.F && super.getHint() == null) {
            String str = Build.MANUFACTURER;
            if ((str != null ? str.toLowerCase(Locale.ENGLISH) : ExtensionRequestData.EMPTY_VALUE).equals("meizu")) {
                setHint(ExtensionRequestData.EMPTY_VALUE);
            }
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f.dismiss();
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            int measuredWidth = getMeasuredWidth();
            ListAdapter adapter = getAdapter();
            TextInputLayout textInputLayoutB = b();
            int measuredWidth2 = 0;
            if (adapter != null && textInputLayoutB != null) {
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
                ListPopupWindow listPopupWindow = this.f;
                int iMin = Math.min(adapter.getCount(), Math.max(0, !listPopupWindow.A.isShowing() ? -1 : listPopupWindow.d.getSelectedItemPosition()) + 15);
                View view = null;
                int iMax = 0;
                for (int iMax2 = Math.max(0, iMin - 15); iMax2 < iMin; iMax2++) {
                    int itemViewType = adapter.getItemViewType(iMax2);
                    if (itemViewType != measuredWidth2) {
                        view = null;
                        measuredWidth2 = itemViewType;
                    }
                    view = adapter.getView(iMax2, view, textInputLayoutB);
                    if (view.getLayoutParams() == null) {
                        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                    }
                    view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                    iMax = Math.max(iMax, view.getMeasuredWidth());
                }
                Drawable background = listPopupWindow.A.getBackground();
                if (background != null) {
                    Rect rect = this.h;
                    background.getPadding(rect);
                    iMax += rect.left + rect.right;
                }
                measuredWidth2 = textInputLayoutB.getEndIconView().getMeasuredWidth() + iMax;
            }
            setMeasuredDimension(Math.min(Math.max(measuredWidth, measuredWidth2), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z) {
        AccessibilityManager accessibilityManager = this.g;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.onWindowFocusChanged(z);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public <T extends ListAdapter & Filterable> void setAdapter(T t) {
        super.setAdapter(t);
        this.f.n(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundDrawable(Drawable drawable) {
        super.setDropDownBackgroundDrawable(drawable);
        ListPopupWindow listPopupWindow = this.f;
        if (listPopupWindow != null) {
            listPopupWindow.l(drawable);
        }
    }

    public void setDropDownBackgroundTint(int i) {
        setDropDownBackgroundTintList(ColorStateList.valueOf(i));
    }

    public void setDropDownBackgroundTintList(ColorStateList colorStateList) {
        this.k = colorStateList;
        Drawable dropDownBackground = getDropDownBackground();
        if (dropDownBackground instanceof u50) {
            ((u50) dropDownBackground).m(this.k);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        this.f.setOnItemSelectedListener(getOnItemSelectedListener());
    }

    @Override // android.widget.TextView
    public void setRawInputType(int i) {
        super.setRawInputType(i);
        TextInputLayout textInputLayoutB = b();
        if (textInputLayoutB != null) {
            textInputLayoutB.s();
        }
    }

    public void setSimpleItemSelectedColor(int i) {
        this.l = i;
        if (getAdapter() instanceof u40) {
            ((u40) getAdapter()).a();
        }
    }

    public void setSimpleItemSelectedRippleColor(ColorStateList colorStateList) {
        this.m = colorStateList;
        if (getAdapter() instanceof u40) {
            ((u40) getAdapter()).a();
        }
    }

    public void setSimpleItems(int i) {
        setSimpleItems(getResources().getStringArray(i));
    }

    @Override // android.widget.AutoCompleteTextView
    public final void showDropDown() {
        AccessibilityManager accessibilityManager = this.g;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.showDropDown();
        } else {
            this.f.f();
        }
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    public void setSimpleItems(String[] strArr) {
        setAdapter(new u40(this, getContext(), this.i, strArr));
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(y50.a(context, attributeSet, i, 0), attributeSet, i);
        this.h = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.MaterialAutoCompleteTextView, i, R$style.Widget_AppCompat_AutoCompleteTextView, new int[0]);
        if (typedArrayD.hasValue(R$styleable.MaterialAutoCompleteTextView_android_inputType) && typedArrayD.getInt(R$styleable.MaterialAutoCompleteTextView_android_inputType, 0) == 0) {
            setKeyListener(null);
        }
        this.i = typedArrayD.getResourceId(R$styleable.MaterialAutoCompleteTextView_simpleItemLayout, R$layout.mtrl_auto_complete_simple_item);
        this.j = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialAutoCompleteTextView_android_popupElevation, R$dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        if (typedArrayD.hasValue(R$styleable.MaterialAutoCompleteTextView_dropDownBackgroundTint)) {
            this.k = ColorStateList.valueOf(typedArrayD.getColor(R$styleable.MaterialAutoCompleteTextView_dropDownBackgroundTint, 0));
        }
        this.l = typedArrayD.getColor(R$styleable.MaterialAutoCompleteTextView_simpleItemSelectedColor, 0);
        this.m = xi.s(context2, typedArrayD, R$styleable.MaterialAutoCompleteTextView_simpleItemSelectedRippleColor);
        this.g = (AccessibilityManager) context2.getSystemService("accessibility");
        ListPopupWindow listPopupWindow = new ListPopupWindow(context2);
        this.f = listPopupWindow;
        listPopupWindow.z = true;
        listPopupWindow.A.setFocusable(true);
        listPopupWindow.p = this;
        listPopupWindow.A.setInputMethodMode(2);
        listPopupWindow.n(getAdapter());
        listPopupWindow.setOnItemClickListener(new n5(1, this));
        if (typedArrayD.hasValue(R$styleable.MaterialAutoCompleteTextView_simpleItems)) {
            setSimpleItems(typedArrayD.getResourceId(R$styleable.MaterialAutoCompleteTextView_simpleItems, 0));
        }
        typedArrayD.recycle();
    }
}
