package com.google.android.material.datepicker;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import defpackage.h31;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class h implements View.OnClickListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ r F;
    public final /* synthetic */ l G;

    public /* synthetic */ h(l lVar, r rVar, int i) {
        this.E = i;
        this.G = lVar;
        this.F = rVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.E) {
            case 0:
                l lVar = this.G;
                int iN0 = ((LinearLayoutManager) lVar.E0.getLayoutManager()).N0() - 1;
                if (iN0 >= 0) {
                    Calendar calendarC = h31.c(this.F.c.E.E);
                    calendarC.add(2, iN0);
                    lVar.D(new Month(calendarC));
                }
                break;
            default:
                l lVar2 = this.G;
                int iM0 = ((LinearLayoutManager) lVar2.E0.getLayoutManager()).M0() + 1;
                if (iM0 < lVar2.E0.getAdapter().a()) {
                    Calendar calendarC2 = h31.c(this.F.c.E.E);
                    calendarC2.add(2, iM0);
                    lVar2.D(new Month(calendarC2));
                }
                break;
        }
    }
}
