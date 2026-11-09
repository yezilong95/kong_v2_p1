package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.R$string;
import defpackage.fk0;
import defpackage.h31;
import defpackage.i5;
import defpackage.ub;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class o extends BaseAdapter {
    public static final int K = h31.g(null).getMaximum(4);
    public static final int L = (h31.g(null).getMaximum(7) + h31.g(null).getMaximum(5)) - 1;
    public final Month E;
    public final DateSelector F;
    public Collection G;
    public ub H;
    public final CalendarConstraints I;
    public final DayViewDecorator J;

    public o(Month month, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator) {
        this.E = month;
        this.F = dateSelector;
        this.I = calendarConstraints;
        this.J = dayViewDecorator;
        this.G = dateSelector.g();
    }

    public final int a() {
        int firstDayOfWeek = this.I.I;
        Month month = this.E;
        Calendar calendar = month.E;
        int i = calendar.get(7);
        if (firstDayOfWeek <= 0) {
            firstDayOfWeek = calendar.getFirstDayOfWeek();
        }
        int i2 = i - firstDayOfWeek;
        return i2 < 0 ? i2 + month.H : i2;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Long getItem(int i) {
        if (i < a() || i > c()) {
            return null;
        }
        int iA = (i - a()) + 1;
        Calendar calendarC = h31.c(this.E.E);
        calendarC.set(5, iA);
        return Long.valueOf(calendarC.getTimeInMillis());
    }

    public final int c() {
        return (a() + this.E.I) - 1;
    }

    public final void d(TextView textView, long j, int i) {
        boolean z;
        boolean z2;
        i5 i5Var;
        if (textView == null) {
            return;
        }
        Context context = textView.getContext();
        boolean z3 = false;
        boolean z4 = h31.f().getTimeInMillis() == j;
        DateSelector dateSelector = this.F;
        Iterator it = dateSelector.d().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            Object obj = ((fk0) it.next()).a;
            if (obj != null && ((Long) obj).longValue() == j) {
                z = true;
                break;
            }
        }
        Iterator it2 = dateSelector.d().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z2 = false;
                break;
            }
            Object obj2 = ((fk0) it2.next()).b;
            if (obj2 != null && ((Long) obj2).longValue() == j) {
                z2 = true;
                break;
            }
        }
        Calendar calendarF = h31.f();
        Calendar calendarG = h31.g(null);
        calendarG.setTimeInMillis(j);
        String str = calendarF.get(1) == calendarG.get(1) ? h31.b("MMMMEEEEd", Locale.getDefault()).format(new Date(j)) : h31.b("yMMMMEEEEd", Locale.getDefault()).format(new Date(j));
        if (z4) {
            str = String.format(context.getString(R$string.mtrl_picker_today_description), str);
        }
        if (z) {
            str = String.format(context.getString(R$string.mtrl_picker_start_date_description), str);
        } else if (z2) {
            str = String.format(context.getString(R$string.mtrl_picker_end_date_description), str);
        }
        textView.setContentDescription(str);
        if (this.I.G.e(j)) {
            textView.setEnabled(true);
            Iterator it3 = dateSelector.g().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                if (h31.a(j) == h31.a(((Long) it3.next()).longValue())) {
                    z3 = true;
                    break;
                }
            }
            textView.setSelected(z3);
            i5Var = z3 ? (i5) this.H.b : h31.f().getTimeInMillis() == j ? (i5) this.H.c : (i5) this.H.a;
        } else {
            textView.setEnabled(false);
            i5Var = (i5) this.H.g;
        }
        if (this.J == null || i == -1) {
            i5Var.n(textView);
            return;
        }
        int i2 = this.E.G;
        i5Var.n(textView);
        textView.setCompoundDrawables(null, null, null, null);
        textView.setContentDescription(str);
    }

    public final void e(MaterialCalendarGridView materialCalendarGridView, long j) {
        Month monthM = Month.m(j);
        Month month = this.E;
        if (monthM.equals(month)) {
            Calendar calendarC = h31.c(month.E);
            calendarC.setTimeInMillis(j);
            int i = calendarC.get(5);
            d((TextView) materialCalendarGridView.getChildAt((materialCalendarGridView.a().a() + (i - 1)) - materialCalendarGridView.getFirstVisiblePosition()), j, i);
        }
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return L;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i / this.E.H;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005c  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            ub r1 = r5.H
            if (r1 != 0) goto Lf
            ub r1 = new ub
            r1.<init>(r0)
            r5.H = r1
        Lf:
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L26
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = com.google.android.material.R$layout.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L26:
            int r7 = r5.a()
            int r7 = r6 - r7
            if (r7 < 0) goto L5c
            com.google.android.material.datepicker.Month r8 = r5.E
            int r2 = r8.I
            if (r7 < r2) goto L35
            goto L5c
        L35:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r4, r3)
            r0.setText(r8)
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L65
        L5c:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
            r7 = -1
        L65:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L6c
            return r0
        L6c:
            long r1 = r6.longValue()
            r5.d(r0, r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.o.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return true;
    }
}
