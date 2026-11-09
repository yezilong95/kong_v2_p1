package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.FlowLayout;
import defpackage.bc;
import defpackage.nc;
import defpackage.o50;
import defpackage.o70;
import defpackage.oc;
import defpackage.pc;
import defpackage.qc;
import defpackage.un0;
import defpackage.xr0;
import defpackage.y50;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChipGroup extends FlowLayout {
    public static final int l = R$style.Widget_MaterialComponents_ChipGroup;
    public int f;
    public int g;
    public pc h;
    public final bc i;
    public final int j;
    public final qc k;

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public ChipGroup(Context context) {
        this(context, null);
    }

    private int getVisibleChipCount() {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if ((getChildAt(i2) instanceof Chip) && getChildAt(i2).getVisibility() == 0) {
                i++;
            }
        }
        return i;
    }

    @Override // com.google.android.material.internal.FlowLayout
    public final boolean a() {
        return this.d;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public int getCheckedChipId() {
        return this.i.c();
    }

    public List<Integer> getCheckedChipIds() {
        return this.i.b(this);
    }

    public int getChipSpacingHorizontal() {
        return this.f;
    }

    public int getChipSpacingVertical() {
        return this.g;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.j;
        if (i != -1) {
            bc bcVar = this.i;
            o50 o50Var = (o50) bcVar.a.get(Integer.valueOf(i));
            if (o50Var != null && bcVar.a(o50Var)) {
                bcVar.d();
            }
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) o70.y(getRowCount(), this.d ? getVisibleChipCount() : -1, this.i.d ? 1 : 2).c);
    }

    public void setChipSpacing(int i) {
        setChipSpacingHorizontal(i);
        setChipSpacingVertical(i);
    }

    public void setChipSpacingHorizontal(int i) {
        if (this.f != i) {
            this.f = i;
            setItemSpacing(i);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int i) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(i));
    }

    public void setChipSpacingResource(int i) {
        setChipSpacing(getResources().getDimensionPixelOffset(i));
    }

    public void setChipSpacingVertical(int i) {
        if (this.g != i) {
            this.g = i;
            setLineSpacing(i);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int i) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(i));
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setDividerDrawableVertical(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setFlexWrap(int i) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    @Deprecated
    public void setOnCheckedChangeListener(oc ocVar) {
        if (ocVar == null) {
            setOnCheckedStateChangeListener(null);
        } else {
            setOnCheckedStateChangeListener(new nc(this));
        }
    }

    public void setOnCheckedStateChangeListener(pc pcVar) {
        this.h = pcVar;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.k.a = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z) {
        this.i.e = z;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Override // com.google.android.material.internal.FlowLayout
    public void setSingleLine(boolean z) {
        super.setSingleLine(z);
    }

    public void setSingleSelection(boolean z) {
        bc bcVar = this.i;
        if (bcVar.d != z) {
            bcVar.d = z;
            boolean zIsEmpty = bcVar.b.isEmpty();
            Iterator it = bcVar.a.values().iterator();
            while (it.hasNext()) {
                bcVar.e((o50) it.next(), false);
            }
            if (zIsEmpty) {
                return;
            }
            bcVar.d();
        }
    }

    public ChipGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.chipGroupStyle);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public void setSingleLine(int i) {
        setSingleLine(getResources().getBoolean(i));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ChipGroup(Context context, AttributeSet attributeSet, int i) {
        int i2 = l;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        bc bcVar = new bc();
        this.i = bcVar;
        qc qcVar = new qc(this);
        this.k = qcVar;
        TypedArray typedArrayD = un0.d(getContext(), attributeSet, R$styleable.ChipGroup, i, i2, new int[0]);
        int dimensionPixelOffset = typedArrayD.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacing, 0);
        setChipSpacingHorizontal(typedArrayD.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingHorizontal, dimensionPixelOffset));
        setChipSpacingVertical(typedArrayD.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingVertical, dimensionPixelOffset));
        setSingleLine(typedArrayD.getBoolean(R$styleable.ChipGroup_singleLine, false));
        setSingleSelection(typedArrayD.getBoolean(R$styleable.ChipGroup_singleSelection, false));
        setSelectionRequired(typedArrayD.getBoolean(R$styleable.ChipGroup_selectionRequired, false));
        this.j = typedArrayD.getResourceId(R$styleable.ChipGroup_checkedChip, -1);
        typedArrayD.recycle();
        bcVar.setOnCheckedStateChangeListener(new nc(this));
        super.setOnHierarchyChangeListener(qcVar);
        WeakHashMap weakHashMap = xr0.a;
        setImportantForAccessibility(1);
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
