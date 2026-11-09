package com.google.android.material.slider;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import com.google.android.material.R$string;
import defpackage.e2;
import defpackage.j2;
import defpackage.s90;
import defpackage.wu;
import io.nats.client.Options;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class e extends wu {
    public final BaseSlider q;
    public final Rect r;

    public e(BaseSlider baseSlider) {
        super(baseSlider);
        this.r = new Rect();
        this.q = baseSlider;
    }

    @Override // defpackage.wu
    public final int n(float f, float f2) {
        int i = 0;
        while (true) {
            BaseSlider baseSlider = this.q;
            if (i >= baseSlider.getValues().size()) {
                return -1;
            }
            Rect rect = this.r;
            baseSlider.w(i, rect);
            if (rect.contains((int) f, (int) f2)) {
                return i;
            }
            i++;
        }
    }

    @Override // defpackage.wu
    public final void o(ArrayList arrayList) {
        for (int i = 0; i < this.q.getValues().size(); i++) {
            arrayList.add(Integer.valueOf(i));
        }
    }

    @Override // defpackage.wu
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
            int i3 = BaseSlider.Y0;
            if (!baseSlider.u(i, f)) {
                return false;
            }
            baseSlider.x();
            baseSlider.postInvalidate();
            p(i);
            return true;
        }
        int i4 = BaseSlider.Y0;
        float fRound = baseSlider.C0;
        if (fRound == 0.0f) {
            fRound = 1.0f;
        }
        if ((baseSlider.y0 - baseSlider.x0) / fRound > 20) {
            fRound *= Math.round(r1 / r4);
        }
        if (i2 == 8192) {
            fRound = -fRound;
        }
        if (baseSlider.l()) {
            fRound = -fRound;
        }
        if (!baseSlider.u(i, s90.l(baseSlider.getValues().get(i).floatValue() + fRound, baseSlider.getValueFrom(), baseSlider.getValueTo()))) {
            return false;
        }
        baseSlider.x();
        baseSlider.postInvalidate();
        p(i);
        return true;
    }

    @Override // defpackage.wu
    public final void u(int i, j2 j2Var) {
        j2Var.b(e2.o);
        BaseSlider baseSlider = this.q;
        List<Float> values = baseSlider.getValues();
        float fFloatValue = values.get(i).floatValue();
        float valueFrom = baseSlider.getValueFrom();
        float valueTo = baseSlider.getValueTo();
        if (baseSlider.isEnabled()) {
            if (fFloatValue > valueFrom) {
                j2Var.a(8192);
            }
            if (fFloatValue < valueTo) {
                j2Var.a(Options.DEFAULT_MAX_CONTROL_LINE);
            }
        }
        AccessibilityNodeInfo.RangeInfo rangeInfoObtain = AccessibilityNodeInfo.RangeInfo.obtain(1, valueFrom, valueTo, fFloatValue);
        AccessibilityNodeInfo accessibilityNodeInfo = j2Var.a;
        accessibilityNodeInfo.setRangeInfo(rangeInfoObtain);
        j2Var.i(SeekBar.class.getName());
        StringBuilder sb = new StringBuilder();
        if (baseSlider.getContentDescription() != null) {
            sb.append(baseSlider.getContentDescription());
            sb.append(",");
        }
        String strG = baseSlider.g(fFloatValue);
        String string = baseSlider.getContext().getString(R$string.material_slider_value);
        if (values.size() > 1) {
            string = i == baseSlider.getValues().size() - 1 ? baseSlider.getContext().getString(R$string.material_slider_range_end) : i == 0 ? baseSlider.getContext().getString(R$string.material_slider_range_start) : "";
        }
        Locale locale = Locale.US;
        sb.append(string + ", " + strG);
        accessibilityNodeInfo.setContentDescription(sb.toString());
        Rect rect = this.r;
        baseSlider.w(i, rect);
        accessibilityNodeInfo.setBoundsInParent(rect);
    }
}
