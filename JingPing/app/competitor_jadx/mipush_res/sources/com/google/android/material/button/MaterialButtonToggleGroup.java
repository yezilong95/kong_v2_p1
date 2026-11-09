package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.timepicker.f;
import defpackage.f51;
import defpackage.fa0;
import defpackage.hb;
import defpackage.i01;
import defpackage.o0;
import defpackage.p50;
import defpackage.pd0;
import defpackage.uc0;
import defpackage.vc0;
import defpackage.wc0;
import defpackage.xu0;
import defpackage.zu0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    public static final int O = R$style.Widget_MaterialComponents_MaterialButtonToggleGroup;
    public final ArrayList E;
    public final p50 F;
    public final LinkedHashSet G;
    public final uc0 H;
    public Integer[] I;
    public boolean J;
    public boolean K;
    public boolean L;
    public final int M;
    public HashSet N;

    public MaterialButtonToggleGroup(Context context) {
        this(context, null);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (c(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (c(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if ((getChildAt(i2) instanceof MaterialButton) && c(i2)) {
                i++;
            }
        }
        return i;
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            WeakHashMap weakHashMap = f51.a;
            materialButton.setId(View.generateViewId());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.setOnPressedChangeListenerInternal(this.F);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    public final void a() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i);
            int iMin = Math.min(materialButton.getStrokeWidth(), ((MaterialButton) getChildAt(i - 1)).getStrokeWidth());
            ViewGroup.LayoutParams layoutParams = materialButton.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
            if (getOrientation() == 0) {
                layoutParams2.setMarginEnd(0);
                layoutParams2.setMarginStart(-iMin);
                layoutParams2.topMargin = 0;
            } else {
                layoutParams2.bottomMargin = 0;
                layoutParams2.topMargin = -iMin;
                layoutParams2.setMarginStart(0);
            }
            materialButton.setLayoutParams(layoutParams2);
        }
        if (getChildCount() == 0 || firstVisibleChildIndex == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) ((MaterialButton) getChildAt(firstVisibleChildIndex)).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams3.topMargin = 0;
            layoutParams3.bottomMargin = 0;
        } else {
            layoutParams3.setMarginEnd(0);
            layoutParams3.setMarginStart(0);
            layoutParams3.leftMargin = 0;
            layoutParams3.rightMargin = 0;
        }
    }

    public void addOnButtonCheckedListener(wc0 wc0Var) {
        this.G.add(wc0Var);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e("MButtonToggleGroup", "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        b(materialButton.getId(), materialButton.S);
        zu0 shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.E.add(new vc0(shapeAppearanceModel.e, shapeAppearanceModel.h, shapeAppearanceModel.f, shapeAppearanceModel.g));
        materialButton.setEnabled(isEnabled());
        f51.o(materialButton, new hb(2, this));
    }

    public final void b(int i, boolean z) {
        if (i == -1) {
            Log.e("MButtonToggleGroup", "Button ID is not valid: " + i);
            return;
        }
        HashSet hashSet = new HashSet(this.N);
        if (z && !hashSet.contains(Integer.valueOf(i))) {
            if (this.K && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i));
        } else {
            if (z || !hashSet.contains(Integer.valueOf(i))) {
                return;
            }
            if (!this.L || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i));
            }
        }
        d(hashSet);
    }

    public final boolean c(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    public final void d(Set set) {
        HashSet hashSet = this.N;
        this.N = new HashSet(set);
        for (int i = 0; i < getChildCount(); i++) {
            int id = ((MaterialButton) getChildAt(i)).getId();
            boolean zContains = set.contains(Integer.valueOf(id));
            View viewFindViewById = findViewById(id);
            if (viewFindViewById instanceof MaterialButton) {
                this.J = true;
                ((MaterialButton) viewFindViewById).setChecked(zContains);
                this.J = false;
            }
            if (hashSet.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                set.contains(Integer.valueOf(id));
                Iterator it = this.G.iterator();
                while (it.hasNext()) {
                    ((f) ((wc0) it.next())).a();
                }
            }
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.H);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put((MaterialButton) getChildAt(i), Integer.valueOf(i));
        }
        this.I = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final void e() {
        vc0 vc0Var;
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i);
            if (materialButton.getVisibility() != 8) {
                xu0 xu0VarG = materialButton.getShapeAppearanceModel().g();
                vc0 vc0Var2 = (vc0) this.E.get(i);
                if (firstVisibleChildIndex != lastVisibleChildIndex) {
                    boolean z = getOrientation() == 0;
                    o0 o0Var = vc0.e;
                    if (i == firstVisibleChildIndex) {
                        vc0Var = z ? fa0.d0(this) ? new vc0(o0Var, o0Var, vc0Var2.b, vc0Var2.c) : new vc0(vc0Var2.a, vc0Var2.d, o0Var, o0Var) : new vc0(vc0Var2.a, o0Var, vc0Var2.b, o0Var);
                    } else if (i == lastVisibleChildIndex) {
                        vc0Var = z ? fa0.d0(this) ? new vc0(vc0Var2.a, vc0Var2.d, o0Var, o0Var) : new vc0(o0Var, o0Var, vc0Var2.b, vc0Var2.c) : new vc0(o0Var, vc0Var2.d, o0Var, vc0Var2.c);
                    } else {
                        vc0Var2 = null;
                    }
                    vc0Var2 = vc0Var;
                }
                if (vc0Var2 == null) {
                    xu0VarG.c(0.0f);
                } else {
                    xu0VarG.e = vc0Var2.a;
                    xu0VarG.h = vc0Var2.d;
                    xu0VarG.f = vc0Var2.b;
                    xu0VarG.g = vc0Var2.c;
                }
                materialButton.setShapeAppearanceModel(xu0VarG.a());
            }
        }
    }

    public int getCheckedButtonId() {
        if (!this.K || this.N.isEmpty()) {
            return -1;
        }
        return ((Integer) this.N.iterator().next()).intValue();
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            int id = ((MaterialButton) getChildAt(i)).getId();
            if (this.N.contains(Integer.valueOf(id))) {
                arrayList.add(Integer.valueOf(id));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.I;
        if (numArr != null && i2 < numArr.length) {
            return numArr[i2].intValue();
        }
        Log.w("MButtonToggleGroup", "Child order wasn't updated");
        return i2;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.M;
        if (i != -1) {
            d(Collections.singleton(Integer.valueOf(i)));
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) p50.B(1, getVisibleButtonCount(), this.K ? 1 : 2).F);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        e();
        a();
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.E.remove(iIndexOfChild);
        }
        e();
        a();
    }

    public void removeOnButtonCheckedListener(wc0 wc0Var) {
        this.G.remove(wc0Var);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i = 0; i < getChildCount(); i++) {
            ((MaterialButton) getChildAt(i)).setEnabled(z);
        }
    }

    public void setSelectionRequired(boolean z) {
        this.L = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.K != z) {
            this.K = z;
            d(new HashSet());
        }
        for (int i = 0; i < getChildCount(); i++) {
            ((MaterialButton) getChildAt(i)).setA11yClassName((this.K ? RadioButton.class : ToggleButton.class).getName());
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i) {
        int i2 = O;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.E = new ArrayList();
        this.F = new p50(25, this);
        this.G = new LinkedHashSet();
        this.H = new uc0(this);
        this.J = false;
        this.N = new HashSet();
        TypedArray typedArrayD = i01.d(getContext(), attributeSet, R$styleable.MaterialButtonToggleGroup, i, i2, new int[0]);
        setSingleSelection(typedArrayD.getBoolean(R$styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.M = typedArrayD.getResourceId(R$styleable.MaterialButtonToggleGroup_checkedButton, -1);
        this.L = typedArrayD.getBoolean(R$styleable.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        setEnabled(typedArrayD.getBoolean(R$styleable.MaterialButtonToggleGroup_android_enabled, true));
        typedArrayD.recycle();
        WeakHashMap weakHashMap = f51.a;
        setImportantForAccessibility(1);
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
