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
import defpackage.ed0;
import defpackage.f51;
import defpackage.i01;
import defpackage.ie;
import defpackage.je;
import defpackage.ke;
import defpackage.p50;
import defpackage.pd0;
import defpackage.wd;
import defpackage.z8;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChipGroup extends FlowLayout {
    public static final int O = R$style.Widget_MaterialComponents_ChipGroup;
    public int I;
    public int J;
    public je K;
    public final wd L;
    public final int M;
    public final ke N;

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
        return this.G;
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
        return this.L.c();
    }

    public List<Integer> getCheckedChipIds() {
        return this.L.b(this);
    }

    public int getChipSpacingHorizontal() {
        return this.I;
    }

    public int getChipSpacingVertical() {
        return this.J;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.M;
        if (i != -1) {
            wd wdVar = this.L;
            ed0 ed0Var = (ed0) wdVar.a.get(Integer.valueOf(i));
            if (ed0Var != null && wdVar.a(ed0Var)) {
                wdVar.d();
            }
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) p50.B(getRowCount(), this.G ? getVisibleChipCount() : -1, this.L.d ? 1 : 2).F);
    }

    public void setChipSpacing(int i) {
        setChipSpacingHorizontal(i);
        setChipSpacingVertical(i);
    }

    public void setChipSpacingHorizontal(int i) {
        if (this.I != i) {
            this.I = i;
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
        if (this.J != i) {
            this.J = i;
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
    public void setOnCheckedChangeListener(ie ieVar) {
        if (ieVar == null) {
            setOnCheckedStateChangeListener(null);
        } else {
            setOnCheckedStateChangeListener(new z8(10, this));
        }
    }

    public void setOnCheckedStateChangeListener(je jeVar) {
        this.K = jeVar;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.N.a = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z) {
        this.L.e = z;
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
        wd wdVar = this.L;
        if (wdVar.d != z) {
            wdVar.d = z;
            boolean zIsEmpty = wdVar.b.isEmpty();
            Iterator it = wdVar.a.values().iterator();
            while (it.hasNext()) {
                wdVar.e((ed0) it.next(), false);
            }
            if (zIsEmpty) {
                return;
            }
            wdVar.d();
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
        int i2 = O;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        wd wdVar = new wd();
        this.L = wdVar;
        ke keVar = new ke(this);
        this.N = keVar;
        TypedArray typedArrayD = i01.d(getContext(), attributeSet, R$styleable.ChipGroup, i, i2, new int[0]);
        int dimensionPixelOffset = typedArrayD.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacing, 0);
        setChipSpacingHorizontal(typedArrayD.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingHorizontal, dimensionPixelOffset));
        setChipSpacingVertical(typedArrayD.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingVertical, dimensionPixelOffset));
        setSingleLine(typedArrayD.getBoolean(R$styleable.ChipGroup_singleLine, false));
        setSingleSelection(typedArrayD.getBoolean(R$styleable.ChipGroup_singleSelection, false));
        setSelectionRequired(typedArrayD.getBoolean(R$styleable.ChipGroup_selectionRequired, false));
        this.M = typedArrayD.getResourceId(R$styleable.ChipGroup_checkedChip, -1);
        typedArrayD.recycle();
        wdVar.setOnCheckedStateChangeListener(new p50(11, this));
        super.setOnHierarchyChangeListener(keVar);
        WeakHashMap weakHashMap = f51.a;
        setImportantForAccessibility(1);
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
