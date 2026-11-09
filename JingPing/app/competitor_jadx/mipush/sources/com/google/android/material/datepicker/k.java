package com.google.android.material.datepicker;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import defpackage.h31;
import defpackage.mn0;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class k extends mn0 {
    public final /* synthetic */ r a;
    public final /* synthetic */ MaterialButton b;
    public final /* synthetic */ l c;

    public k(l lVar, r rVar, MaterialButton materialButton) {
        this.c = lVar;
        this.a = rVar;
        this.b = materialButton;
    }

    @Override // defpackage.mn0
    public final void a(RecyclerView recyclerView, int i) {
        if (i == 0) {
            recyclerView.announceForAccessibility(this.b.getText());
        }
    }

    @Override // defpackage.mn0
    public final void b(RecyclerView recyclerView, int i, int i2) {
        l lVar = this.c;
        int iM0 = i < 0 ? ((LinearLayoutManager) lVar.E0.getLayoutManager()).M0() : ((LinearLayoutManager) lVar.E0.getLayoutManager()).N0();
        CalendarConstraints calendarConstraints = this.a.c;
        Calendar calendarC = h31.c(calendarConstraints.E.E);
        calendarC.add(2, iM0);
        lVar.A0 = new Month(calendarC);
        Calendar calendarC2 = h31.c(calendarConstraints.E.E);
        calendarC2.add(2, iM0);
        this.b.setText(new Month(calendarC2).n());
    }
}
