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
import defpackage.fo0;
import defpackage.go0;
import defpackage.td;
import defpackage.ud;
import defpackage.v20;

/* JADX INFO: loaded from: classes.dex */
class TimePickerView extends ConstraintLayout {
    public static final /* synthetic */ int t = 0;
    public final Chip r;
    public final ClockHandView s;

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void addOnRotateListener(ud udVar) {
        this.s.addOnRotateListener(udVar);
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            this.r.sendAccessibilityEvent(8);
        }
    }

    public void setOnActionUpListener(td tdVar) {
        this.s.setOnActionUpListener(tdVar);
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        g gVar = new g(this);
        LayoutInflater.from(context).inflate(R$layout.material_timepicker, this);
        ((MaterialButtonToggleGroup) findViewById(R$id.material_clock_period_toggle)).addOnButtonCheckedListener(new f());
        Chip chip = (Chip) findViewById(R$id.material_minute_tv);
        Chip chip2 = (Chip) findViewById(R$id.material_hour_tv);
        this.r = chip2;
        this.s = (ClockHandView) findViewById(R$id.material_clock_hand);
        v20 v20Var = new v20(1, new GestureDetector(getContext(), new h(this)));
        chip.setOnTouchListener(v20Var);
        chip2.setOnTouchListener(v20Var);
        chip.setTag(R$id.selection_type, 12);
        chip2.setTag(R$id.selection_type, 10);
        chip.setOnClickListener(gVar);
        chip2.setOnClickListener(gVar);
        chip.setAccessibilityClassName("android.view.View");
        chip2.setAccessibilityClassName("android.view.View");
    }

    public void setOnDoubleTapListener(fo0 fo0Var) {
    }

    public void setOnPeriodChangeListener(go0 go0Var) {
    }
}
