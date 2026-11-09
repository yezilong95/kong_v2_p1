package com.google.android.material.datepicker;

import android.view.View;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class s implements View.OnClickListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ t c;

    public s(t tVar, int i) {
        this.c = tVar;
        this.b = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        t tVar = this.c;
        Month monthL = Month.l(this.b, tVar.c.X.c);
        l lVar = tVar.c;
        CalendarConstraints calendarConstraints = lVar.V;
        Month month = calendarConstraints.b;
        Calendar calendar = month.b;
        Calendar calendar2 = monthL.b;
        if (calendar2.compareTo(calendar) < 0) {
            monthL = month;
        } else {
            Month month2 = calendarConstraints.c;
            if (calendar2.compareTo(month2.b) > 0) {
                monthL = month2;
            }
        }
        lVar.D(monthL);
        lVar.E(1);
    }
}
