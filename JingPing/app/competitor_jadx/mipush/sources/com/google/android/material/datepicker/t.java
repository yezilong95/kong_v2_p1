package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import defpackage.h31;
import defpackage.i5;
import defpackage.ub;
import defpackage.x91;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class t extends androidx.recyclerview.widget.g {
    public final l c;

    public t(l lVar) {
        this.c = lVar;
    }

    @Override // androidx.recyclerview.widget.g
    public final int a() {
        return this.c.y0.J;
    }

    @Override // androidx.recyclerview.widget.g
    public final void d(androidx.recyclerview.widget.n nVar, int i) {
        l lVar = this.c;
        int i2 = lVar.y0.E.G + i;
        String str = String.format(Locale.getDefault(), "%d", Integer.valueOf(i2));
        TextView textView = ((x91) nVar).t;
        textView.setText(str);
        Context context = textView.getContext();
        textView.setContentDescription(h31.f().get(1) == i2 ? String.format(context.getString(R$string.mtrl_picker_navigate_to_current_year_description), Integer.valueOf(i2)) : String.format(context.getString(R$string.mtrl_picker_navigate_to_year_description), Integer.valueOf(i2)));
        ub ubVar = lVar.C0;
        Calendar calendarF = h31.f();
        i5 i5Var = (i5) (calendarF.get(1) == i2 ? ubVar.f : ubVar.d);
        Iterator it = lVar.x0.g().iterator();
        while (it.hasNext()) {
            calendarF.setTimeInMillis(((Long) it.next()).longValue());
            if (calendarF.get(1) == i2) {
                i5Var = (i5) ubVar.e;
            }
        }
        i5Var.n(textView);
        textView.setOnClickListener(new s(this, i2));
    }

    @Override // androidx.recyclerview.widget.g
    public final androidx.recyclerview.widget.n e(ViewGroup viewGroup, int i) {
        return new x91((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_year, viewGroup, false));
    }
}
