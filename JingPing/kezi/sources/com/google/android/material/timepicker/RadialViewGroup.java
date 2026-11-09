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
import defpackage.hg;
import defpackage.ii0;
import defpackage.je0;
import defpackage.u50;
import defpackage.xr0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class RadialViewGroup extends ConstraintLayout {
    public final d r;
    public int s;
    public final u50 t;

    public RadialViewGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (view.getId() == -1) {
            WeakHashMap weakHashMap = xr0.a;
            view.setId(View.generateViewId());
        }
        Handler handler = getHandler();
        if (handler != null) {
            d dVar = this.r;
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
            int iRound = ((Integer) entry.getKey()).intValue() == 2 ? Math.round(this.s * 0.66f) : this.s;
            Iterator it = list.iterator();
            float size = 0.0f;
            while (it.hasNext()) {
                int id = ((View) it.next()).getId();
                int i3 = R$id.circle_center;
                hg hgVar = dVar.g(id).d;
                hgVar.x = i3;
                hgVar.y = iRound;
                hgVar.z = size;
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
            d dVar = this.r;
            handler.removeCallbacks(dVar);
            handler.post(dVar);
        }
    }

    @Override // android.view.View
    public final void setBackgroundColor(int i) {
        this.t.m(ColorStateList.valueOf(i));
    }

    /* JADX WARN: Type inference failed for: r5v3, types: [com.google.android.material.timepicker.d] */
    public RadialViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R$layout.material_radial_view_group, this);
        u50 u50Var = new u50();
        this.t = u50Var;
        je0 je0Var = new je0(0.5f);
        ii0 ii0VarG = u50Var.b.a.g();
        ii0VarG.e = je0Var;
        ii0VarG.f = je0Var;
        ii0VarG.g = je0Var;
        ii0VarG.h = je0Var;
        u50Var.setShapeAppearanceModel(ii0VarG.a());
        this.t.m(ColorStateList.valueOf(-1));
        u50 u50Var2 = this.t;
        WeakHashMap weakHashMap = xr0.a;
        setBackground(u50Var2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RadialViewGroup, i, 0);
        this.s = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.RadialViewGroup_materialCircleRadius, 0);
        this.r = new Runnable() { // from class: com.google.android.material.timepicker.d
            @Override // java.lang.Runnable
            public final void run() {
                this.b.o();
            }
        };
        typedArrayObtainStyledAttributes.recycle();
    }
}
