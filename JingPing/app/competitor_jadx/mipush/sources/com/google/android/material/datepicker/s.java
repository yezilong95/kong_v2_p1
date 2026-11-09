package com.google.android.material.datepicker;

import android.view.View;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class s implements View.OnClickListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ t F;

    public s(t tVar, int i) {
        this.F = tVar;
        this.E = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        t tVar = this.F;
        Month monthL = Month.l(this.E, tVar.c.A0.F);
        l lVar = tVar.c;
        CalendarConstraints calendarConstraints = lVar.y0;
        Month month = calendarConstraints.E;
        Calendar calendar = month.E;
        Calendar calendar2 = monthL.E;
        if (calendar2.compareTo(calendar) < 0) {
            monthL = month;
        } else {
            Month month2 = calendarConstraints.F;
            if (calendar2.compareTo(month2.E) > 0) {
                monthL = month2;
            }
        }
        lVar.D(monthL);
        lVar.E(1);
    }
}
