package com.google.android.material.slider;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import com.google.android.material.R$string;
import defpackage.ap;
import defpackage.j0;
import defpackage.o0;
import defpackage.rp0;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class d extends ap {
    public final BaseSlider q;
    public final Rect r;

    public d(BaseSlider baseSlider) {
        super(baseSlider);
        this.r = new Rect();
        this.q = baseSlider;
    }

    @Override // defpackage.ap
    public final int n(float f, float f2) {
        int i = 0;
        while (true) {
            BaseSlider baseSlider = this.q;
            if (i >= baseSlider.getValues().size()) {
                return -1;
            }
            Rect rect = this.r;
            baseSlider.u(i, rect);
            if (rect.contains((int) f, (int) f2)) {
                return i;
            }
            i++;
        }
    }

    @Override // defpackage.ap
    public final void o(ArrayList arrayList) {
        for (int i = 0; i < this.q.getValues().size(); i++) {
            arrayList.add(Integer.valueOf(i));
        }
    }

    @Override // defpackage.ap
    public final boolean s(int i, int i2, Bundle bundle) {
        BaseSlider baseSlider = this.q;
        if (!baseSlider.isEnabled()) {
            return false;
        }
        if (i2 != 4096 && i2 != 8192) {
            if (i2 != 16908349 || bundle == null || !bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                return false;
            }
            float f = bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE");
            int i3 = BaseSlider.j0;
            if (!baseSlider.s(i, f)) {
                return false;
            }
            baseSlider.v();
            baseSlider.postInvalidate();
            p(i);
            return true;
        }
        int i4 = BaseSlider.j0;
        float fRound = baseSlider.O;
        if (fRound == 0.0f) {
            fRound = 1.0f;
        }
        if ((baseSlider.K - baseSlider.J) / fRound > 20) {
            fRound *= Math.round(r1 / r4);
        }
        if (i2 == 8192) {
            fRound = -fRound;
        }
        if (baseSlider.j()) {
            fRound = -fRound;
        }
        if (!baseSlider.s(i, rp0.m(baseSlider.getValues().get(i).floatValue() + fRound, baseSlider.getValueFrom(), baseSlider.getValueTo()))) {
            return false;
        }
        baseSlider.v();
        baseSlider.postInvalidate();
        p(i);
        return true;
    }

    @Override // defpackage.ap
    public final void u(int i, o0 o0Var) {
        o0Var.b(j0.o);
        BaseSlider baseSlider = this.q;
        List<Float> values = baseSlider.getValues();
        float fFloatValue = values.get(i).floatValue();
        float valueFrom = baseSlider.getValueFrom();
        float valueTo = baseSlider.getValueTo();
        if (baseSlider.isEnabled()) {
            if (fFloatValue > valueFrom) {
                o0Var.a(8192);
            }
            if (fFloatValue < valueTo) {
                o0Var.a(4096);
            }
        }
        AccessibilityNodeInfo.RangeInfo rangeInfoObtain = AccessibilityNodeInfo.RangeInfo.obtain(1, valueFrom, valueTo, fFloatValue);
        AccessibilityNodeInfo accessibilityNodeInfo = o0Var.a;
        accessibilityNodeInfo.setRangeInfo(rangeInfoObtain);
        o0Var.i(SeekBar.class.getName());
        StringBuilder sb = new StringBuilder();
        if (baseSlider.getContentDescription() != null) {
            sb.append(baseSlider.getContentDescription());
            sb.append(",");
        }
        String strE = baseSlider.e(fFloatValue);
        String string = baseSlider.getContext().getString(R$string.material_slider_value);
        if (values.size() > 1) {
            string = i == baseSlider.getValues().size() - 1 ? baseSlider.getContext().getString(R$string.material_slider_range_end) : i == 0 ? baseSlider.getContext().getString(R$string.material_slider_range_start) : ExtensionRequestData.EMPTY_VALUE;
        }
        Locale locale = Locale.US;
        sb.append(string + ", " + strE);
        accessibilityNodeInfo.setContentDescription(sb.toString());
        Rect rect = this.r;
        baseSlider.u(i, rect);
        accessibilityNodeInfo.setBoundsInParent(rect);
    }
}
