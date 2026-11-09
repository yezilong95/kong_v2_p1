package com.google.android.material.datepicker;

import android.R;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import defpackage.h31;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class r extends androidx.recyclerview.widget.g {
    public final CalendarConstraints c;
    public final DateSelector d;
    public final DayViewDecorator e;
    public final i f;
    public final int g;

    public r(ContextThemeWrapper contextThemeWrapper, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator, i iVar) {
        Month month = calendarConstraints.E;
        Month month2 = calendarConstraints.H;
        if (month.E.compareTo(month2.E) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (month2.E.compareTo(calendarConstraints.F.E) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.g = (contextThemeWrapper.getResources().getDimensionPixelSize(R$dimen.mtrl_calendar_day_height) * o.K) + (m.G(contextThemeWrapper, R.attr.windowFullscreen) ? contextThemeWrapper.getResources().getDimensionPixelSize(R$dimen.mtrl_calendar_day_height) : 0);
        this.c = calendarConstraints;
        this.d = dateSelector;
        this.e = dayViewDecorator;
        this.f = iVar;
        if (this.a.a()) {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }
        this.b = true;
    }

    @Override // androidx.recyclerview.widget.g
    public final int a() {
        return this.c.K;
    }

    @Override // androidx.recyclerview.widget.g
    public final long b(int i) {
        Calendar calendarC = h31.c(this.c.E.E);
        calendarC.add(2, i);
        return new Month(calendarC).E.getTimeInMillis();
    }

    @Override // androidx.recyclerview.widget.g
    public final void d(androidx.recyclerview.widget.n nVar, int i) {
        q qVar = (q) nVar;
        CalendarConstraints calendarConstraints = this.c;
        Calendar calendarC = h31.c(calendarConstraints.E.E);
        calendarC.add(2, i);
        Month month = new Month(calendarC);
        qVar.t.setText(month.n());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) qVar.u.findViewById(R$id.month_grid);
        if (materialCalendarGridView.a() == null || !month.equals(materialCalendarGridView.a().E)) {
            o oVar = new o(month, this.d, calendarConstraints, this.e);
            materialCalendarGridView.setNumColumns(month.H);
            materialCalendarGridView.setAdapter((ListAdapter) oVar);
        } else {
            materialCalendarGridView.invalidate();
            o oVarA = materialCalendarGridView.a();
            Iterator it = oVarA.G.iterator();
            while (it.hasNext()) {
                oVarA.e(materialCalendarGridView, ((Long) it.next()).longValue());
            }
            DateSelector dateSelector = oVarA.F;
            if (dateSelector != null) {
                Iterator it2 = dateSelector.g().iterator();
                while (it2.hasNext()) {
                    oVarA.e(materialCalendarGridView, ((Long) it2.next()).longValue());
                }
                oVarA.G = dateSelector.g();
            }
        }
        materialCalendarGridView.setOnItemClickListener(new p(this, materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.g
    public final androidx.recyclerview.widget.n e(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!m.G(viewGroup.getContext(), R.attr.windowFullscreen)) {
            return new q(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.g));
        return new q(linearLayout, true);
    }
}
