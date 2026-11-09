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
import defpackage.e;
import defpackage.e50;
import defpackage.f50;
import defpackage.g50;
import defpackage.ii0;
import defpackage.j9;
import defpackage.ki0;
import defpackage.o70;
import defpackage.rp0;
import defpackage.un0;
import defpackage.xr0;
import defpackage.y50;
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
    public static final int l = R$style.Widget_MaterialComponents_MaterialButtonToggleGroup;
    public final ArrayList b;
    public final o70 c;
    public final LinkedHashSet d;
    public final e50 e;
    public Integer[] f;
    public boolean g;
    public boolean h;
    public boolean i;
    public final int j;
    public HashSet k;

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
            WeakHashMap weakHashMap = xr0.a;
            materialButton.setId(View.generateViewId());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.setOnPressedChangeListenerInternal(this.c);
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

    public void addOnButtonCheckedListener(g50 g50Var) {
        this.d.add(g50Var);
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
        b(materialButton.getId(), materialButton.p);
        ki0 shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.b.add(new f50(shapeAppearanceModel.e, shapeAppearanceModel.h, shapeAppearanceModel.f, shapeAppearanceModel.g));
        materialButton.setEnabled(isEnabled());
        xr0.p(materialButton, new j9(2, this));
    }

    public final void b(int i, boolean z) {
        if (i == -1) {
            Log.e("MButtonToggleGroup", "Button ID is not valid: " + i);
            return;
        }
        HashSet hashSet = new HashSet(this.k);
        if (z && !hashSet.contains(Integer.valueOf(i))) {
            if (this.h && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i));
        } else {
            if (z || !hashSet.contains(Integer.valueOf(i))) {
                return;
            }
            if (!this.i || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i));
            }
        }
        d(hashSet);
    }

    public final boolean c(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    public final void d(Set set) {
        HashSet hashSet = this.k;
        this.k = new HashSet(set);
        for (int i = 0; i < getChildCount(); i++) {
            int id = ((MaterialButton) getChildAt(i)).getId();
            boolean zContains = set.contains(Integer.valueOf(id));
            View viewFindViewById = findViewById(id);
            if (viewFindViewById instanceof MaterialButton) {
                this.g = true;
                ((MaterialButton) viewFindViewById).setChecked(zContains);
                this.g = false;
            }
            if (hashSet.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                set.contains(Integer.valueOf(id));
                Iterator it = this.d.iterator();
                while (it.hasNext()) {
                    ((f) ((g50) it.next())).a();
                }
            }
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.e);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put((MaterialButton) getChildAt(i), Integer.valueOf(i));
        }
        this.f = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final void e() {
        f50 f50Var;
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i);
            if (materialButton.getVisibility() != 8) {
                ii0 ii0VarG = materialButton.getShapeAppearanceModel().g();
                f50 f50Var2 = (f50) this.b.get(i);
                if (firstVisibleChildIndex != lastVisibleChildIndex) {
                    boolean z = getOrientation() == 0;
                    e eVar = f50.e;
                    if (i == firstVisibleChildIndex) {
                        f50Var = z ? rp0.I(this) ? new f50(eVar, eVar, f50Var2.b, f50Var2.c) : new f50(f50Var2.a, f50Var2.d, eVar, eVar) : new f50(f50Var2.a, eVar, f50Var2.b, eVar);
                    } else if (i == lastVisibleChildIndex) {
                        f50Var = z ? rp0.I(this) ? new f50(f50Var2.a, f50Var2.d, eVar, eVar) : new f50(eVar, eVar, f50Var2.b, f50Var2.c) : new f50(eVar, f50Var2.d, eVar, f50Var2.c);
                    } else {
                        f50Var2 = null;
                    }
                    f50Var2 = f50Var;
                }
                if (f50Var2 == null) {
                    ii0VarG.c(0.0f);
                } else {
                    ii0VarG.e = f50Var2.a;
                    ii0VarG.h = f50Var2.d;
                    ii0VarG.f = f50Var2.b;
                    ii0VarG.g = f50Var2.c;
                }
                materialButton.setShapeAppearanceModel(ii0VarG.a());
            }
        }
    }

    public int getCheckedButtonId() {
        if (!this.h || this.k.isEmpty()) {
            return -1;
        }
        return ((Integer) this.k.iterator().next()).intValue();
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            int id = ((MaterialButton) getChildAt(i)).getId();
            if (this.k.contains(Integer.valueOf(id))) {
                arrayList.add(Integer.valueOf(id));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.f;
        if (numArr != null && i2 < numArr.length) {
            return numArr[i2].intValue();
        }
        Log.w("MButtonToggleGroup", "Child order wasn't updated");
        return i2;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.j;
        if (i != -1) {
            d(Collections.singleton(Integer.valueOf(i)));
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) o70.y(1, getVisibleButtonCount(), this.h ? 1 : 2).c);
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
            this.b.remove(iIndexOfChild);
        }
        e();
        a();
    }

    public void removeOnButtonCheckedListener(g50 g50Var) {
        this.d.remove(g50Var);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i = 0; i < getChildCount(); i++) {
            ((MaterialButton) getChildAt(i)).setEnabled(z);
        }
    }

    public void setSelectionRequired(boolean z) {
        this.i = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.h != z) {
            this.h = z;
            d(new HashSet());
        }
        for (int i = 0; i < getChildCount(); i++) {
            ((MaterialButton) getChildAt(i)).setA11yClassName((this.h ? RadioButton.class : ToggleButton.class).getName());
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i) {
        int i2 = l;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.b = new ArrayList();
        this.c = new o70(26, this);
        this.d = new LinkedHashSet();
        this.e = new e50(this);
        this.g = false;
        this.k = new HashSet();
        TypedArray typedArrayD = un0.d(getContext(), attributeSet, R$styleable.MaterialButtonToggleGroup, i, i2, new int[0]);
        setSingleSelection(typedArrayD.getBoolean(R$styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.j = typedArrayD.getResourceId(R$styleable.MaterialButtonToggleGroup_checkedButton, -1);
        this.i = typedArrayD.getBoolean(R$styleable.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        setEnabled(typedArrayD.getBoolean(R$styleable.MaterialButtonToggleGroup_android_enabled, true));
        typedArrayD.recycle();
        WeakHashMap weakHashMap = xr0.a;
        setImportantForAccessibility(1);
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
