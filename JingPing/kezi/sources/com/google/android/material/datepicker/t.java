package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import defpackage.e4;
import defpackage.ea;
import defpackage.fq0;
import defpackage.lv0;
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
        return this.c.V.g;
    }

    @Override // androidx.recyclerview.widget.g
    public final void d(androidx.recyclerview.widget.n nVar, int i) {
        l lVar = this.c;
        int i2 = lVar.V.b.d + i;
        String str = String.format(Locale.getDefault(), "%d", Integer.valueOf(i2));
        TextView textView = ((lv0) nVar).t;
        textView.setText(str);
        Context context = textView.getContext();
        textView.setContentDescription(fq0.f().get(1) == i2 ? String.format(context.getString(R$string.mtrl_picker_navigate_to_current_year_description), Integer.valueOf(i2)) : String.format(context.getString(R$string.mtrl_picker_navigate_to_year_description), Integer.valueOf(i2)));
        ea eaVar = lVar.Z;
        Calendar calendarF = fq0.f();
        e4 e4Var = calendarF.get(1) == i2 ? eaVar.f : eaVar.d;
        Iterator it = lVar.U.j().iterator();
        while (it.hasNext()) {
            calendarF.setTimeInMillis(((Long) it.next()).longValue());
            if (calendarF.get(1) == i2) {
                e4Var = eaVar.e;
            }
        }
        e4Var.k(textView);
        textView.setOnClickListener(new s(this, i2));
    }

    @Override // androidx.recyclerview.widget.g
    public final androidx.recyclerview.widget.n e(ViewGroup viewGroup, int i) {
        return new lv0((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_year, viewGroup, false));
    }
}
