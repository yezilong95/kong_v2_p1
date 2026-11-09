package com.google.android.material.datepicker;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import defpackage.fq0;
import defpackage.qd0;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class k extends qd0 {
    public final /* synthetic */ r a;
    public final /* synthetic */ MaterialButton b;
    public final /* synthetic */ l c;

    public k(l lVar, r rVar, MaterialButton materialButton) {
        this.c = lVar;
        this.a = rVar;
        this.b = materialButton;
    }

    @Override // defpackage.qd0
    public final void a(RecyclerView recyclerView, int i) {
        if (i == 0) {
            recyclerView.announceForAccessibility(this.b.getText());
        }
    }

    @Override // defpackage.qd0
    public final void b(RecyclerView recyclerView, int i, int i2) {
        l lVar = this.c;
        int iJ0 = i < 0 ? ((LinearLayoutManager) lVar.b0.getLayoutManager()).J0() : ((LinearLayoutManager) lVar.b0.getLayoutManager()).K0();
        CalendarConstraints calendarConstraints = this.a.c;
        Calendar calendarC = fq0.c(calendarConstraints.b.b);
        calendarC.add(2, iJ0);
        lVar.X = new Month(calendarC);
        Calendar calendarC2 = fq0.c(calendarConstraints.b.b);
        calendarC2.add(2, iJ0);
        this.b.setText(new Month(calendarC2).n());
    }
}
