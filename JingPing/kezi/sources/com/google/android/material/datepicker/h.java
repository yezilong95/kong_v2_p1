package com.google.android.material.datepicker;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import defpackage.fq0;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class h implements View.OnClickListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ r c;
    public final /* synthetic */ l d;

    public /* synthetic */ h(l lVar, r rVar, int i) {
        this.b = i;
        this.d = lVar;
        this.c = rVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.b) {
            case 0:
                l lVar = this.d;
                int iK0 = ((LinearLayoutManager) lVar.b0.getLayoutManager()).K0() - 1;
                if (iK0 >= 0) {
                    Calendar calendarC = fq0.c(this.c.c.b.b);
                    calendarC.add(2, iK0);
                    lVar.D(new Month(calendarC));
                }
                break;
            default:
                l lVar2 = this.d;
                int iJ0 = ((LinearLayoutManager) lVar2.b0.getLayoutManager()).J0() + 1;
                if (iJ0 < lVar2.b0.getAdapter().a()) {
                    Calendar calendarC2 = fq0.c(this.c.c.b.b);
                    calendarC2.add(2, iJ0);
                    lVar2.D(new Month(calendarC2));
                }
                break;
        }
    }
}
