package com.google.android.material.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import defpackage.aa0;
import defpackage.pf;
import defpackage.qf;
import defpackage.t01;
import defpackage.u01;

/* JADX INFO: loaded from: classes.dex */
class TimePickerView extends ConstraintLayout {
    public static final /* synthetic */ int W = 0;
    public final Chip U;
    public final ClockHandView V;

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void addOnRotateListener(qf qfVar) {
        this.V.addOnRotateListener(qfVar);
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            this.U.sendAccessibilityEvent(8);
        }
    }

    public void setOnActionUpListener(pf pfVar) {
        this.V.setOnActionUpListener(pfVar);
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        g gVar = new g(this);
        LayoutInflater.from(context).inflate(R$layout.material_timepicker, this);
        ((MaterialButtonToggleGroup) findViewById(R$id.material_clock_period_toggle)).addOnButtonCheckedListener(new f());
        Chip chip = (Chip) findViewById(R$id.material_minute_tv);
        Chip chip2 = (Chip) findViewById(R$id.material_hour_tv);
        this.U = chip2;
        this.V = (ClockHandView) findViewById(R$id.material_clock_hand);
        aa0 aa0Var = new aa0(1, new GestureDetector(getContext(), new h(this)));
        chip.setOnTouchListener(aa0Var);
        chip2.setOnTouchListener(aa0Var);
        chip.setTag(R$id.selection_type, 12);
        chip2.setTag(R$id.selection_type, 10);
        chip.setOnClickListener(gVar);
        chip2.setOnClickListener(gVar);
        chip.setAccessibilityClassName("android.view.View");
        chip2.setAccessibilityClassName("android.view.View");
    }

    public void setOnDoubleTapListener(t01 t01Var) {
    }

    public void setOnPeriodChangeListener(u01 u01Var) {
    }
}
