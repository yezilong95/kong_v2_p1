package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$styleable;
import defpackage.f51;
import defpackage.jo0;
import defpackage.ld0;
import defpackage.th;
import defpackage.xu0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class RadialViewGroup extends ConstraintLayout {
    public final d U;
    public int V;
    public final ld0 W;

    public RadialViewGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (view.getId() == -1) {
            WeakHashMap weakHashMap = f51.a;
            view.setId(View.generateViewId());
        }
        Handler handler = getHandler();
        if (handler != null) {
            d dVar = this.U;
            handler.removeCallbacks(dVar);
            handler.post(dVar);
        }
    }

    public void o() {
        androidx.constraintlayout.widget.d dVar = new androidx.constraintlayout.widget.d();
        dVar.d(this);
        HashMap map = new HashMap();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getId() != R$id.circle_center && !"skip".equals(childAt.getTag())) {
                int i2 = (Integer) childAt.getTag(R$id.material_clock_level);
                if (i2 == null) {
                    i2 = 1;
                }
                if (!map.containsKey(i2)) {
                    map.put(i2, new ArrayList());
                }
                ((List) map.get(i2)).add(childAt);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            List list = (List) entry.getValue();
            int iRound = ((Integer) entry.getKey()).intValue() == 2 ? Math.round(this.V * 0.66f) : this.V;
            Iterator it = list.iterator();
            float size = 0.0f;
            while (it.hasNext()) {
                int id = ((View) it.next()).getId();
                int i3 = R$id.circle_center;
                th thVar = dVar.g(id).d;
                thVar.x = i3;
                thVar.y = iRound;
                thVar.z = size;
                size += 360.0f / list.size();
            }
        }
        dVar.b(this);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        o();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        Handler handler = getHandler();
        if (handler != null) {
            d dVar = this.U;
            handler.removeCallbacks(dVar);
            handler.post(dVar);
        }
    }

    @Override // android.view.View
    public final void setBackgroundColor(int i) {
        this.W.m(ColorStateList.valueOf(i));
    }

    /* JADX WARN: Type inference failed for: r5v3, types: [com.google.android.material.timepicker.d] */
    public RadialViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R$layout.material_radial_view_group, this);
        ld0 ld0Var = new ld0();
        this.W = ld0Var;
        jo0 jo0Var = new jo0(0.5f);
        xu0 xu0VarG = ld0Var.E.a.g();
        xu0VarG.e = jo0Var;
        xu0VarG.f = jo0Var;
        xu0VarG.g = jo0Var;
        xu0VarG.h = jo0Var;
        ld0Var.setShapeAppearanceModel(xu0VarG.a());
        this.W.m(ColorStateList.valueOf(-1));
        ld0 ld0Var2 = this.W;
        WeakHashMap weakHashMap = f51.a;
        setBackground(ld0Var2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RadialViewGroup, i, 0);
        this.V = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.RadialViewGroup_materialCircleRadius, 0);
        this.U = new Runnable() { // from class: com.google.android.material.timepicker.d
            @Override // java.lang.Runnable
            public final void run() {
                this.E.o();
            }
        };
        typedArrayObtainStyledAttributes.recycle();
    }
}
