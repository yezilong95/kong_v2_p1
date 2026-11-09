package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.R$string;
import defpackage.e4;
import defpackage.ea;
import defpackage.fq0;
import defpackage.lb0;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class o extends BaseAdapter {
    public static final int h = fq0.g(null).getMaximum(4);
    public static final int i = (fq0.g(null).getMaximum(7) + fq0.g(null).getMaximum(5)) - 1;
    public final Month b;
    public final DateSelector c;
    public Collection d;
    public ea e;
    public final CalendarConstraints f;
    public final DayViewDecorator g;

    public o(Month month, DateSelector dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator) {
        this.b = month;
        this.c = dateSelector;
        this.f = calendarConstraints;
        this.g = dayViewDecorator;
        this.d = dateSelector.j();
    }

    public final int a() {
        int firstDayOfWeek = this.f.f;
        Month month = this.b;
        Calendar calendar = month.b;
        int i2 = calendar.get(7);
        if (firstDayOfWeek <= 0) {
            firstDayOfWeek = calendar.getFirstDayOfWeek();
        }
        int i3 = i2 - firstDayOfWeek;
        return i3 < 0 ? i3 + month.e : i3;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Long getItem(int i2) {
        if (i2 < a() || i2 > c()) {
            return null;
        }
        int iA = (i2 - a()) + 1;
        Calendar calendarC = fq0.c(this.b.b);
        calendarC.set(5, iA);
        return Long.valueOf(calendarC.getTimeInMillis());
    }

    public final int c() {
        return (a() + this.b.f) - 1;
    }

    public final void d(TextView textView, long j, int i2) {
        boolean z;
        boolean z2;
        e4 e4Var;
        if (textView == null) {
            return;
        }
        Context context = textView.getContext();
        boolean z3 = false;
        boolean z4 = fq0.f().getTimeInMillis() == j;
        DateSelector dateSelector = this.c;
        Iterator it = dateSelector.e().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            Object obj = ((lb0) it.next()).a;
            if (obj != null && ((Long) obj).longValue() == j) {
                z = true;
                break;
            }
        }
        Iterator it2 = dateSelector.e().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z2 = false;
                break;
            }
            Object obj2 = ((lb0) it2.next()).b;
            if (obj2 != null && ((Long) obj2).longValue() == j) {
                z2 = true;
                break;
            }
        }
        Calendar calendarF = fq0.f();
        Calendar calendarG = fq0.g(null);
        calendarG.setTimeInMillis(j);
        String str = calendarF.get(1) == calendarG.get(1) ? fq0.b("MMMMEEEEd", Locale.getDefault()).format(new Date(j)) : fq0.b("yMMMMEEEEd", Locale.getDefault()).format(new Date(j));
        if (z4) {
            str = String.format(context.getString(R$string.mtrl_picker_today_description), str);
        }
        if (z) {
            str = String.format(context.getString(R$string.mtrl_picker_start_date_description), str);
        } else if (z2) {
            str = String.format(context.getString(R$string.mtrl_picker_end_date_description), str);
        }
        textView.setContentDescription(str);
        if (this.f.d.f(j)) {
            textView.setEnabled(true);
            Iterator it3 = dateSelector.j().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                if (fq0.a(j) == fq0.a(((Long) it3.next()).longValue())) {
                    z3 = true;
                    break;
                }
            }
            textView.setSelected(z3);
            e4Var = z3 ? this.e.b : fq0.f().getTimeInMillis() == j ? this.e.c : this.e.a;
        } else {
            textView.setEnabled(false);
            e4Var = this.e.g;
        }
        if (this.g == null || i2 == -1) {
            e4Var.k(textView);
            return;
        }
        int i3 = this.b.d;
        e4Var.k(textView);
        textView.setCompoundDrawables(null, null, null, null);
        textView.setContentDescription(str);
    }

    public final void e(MaterialCalendarGridView materialCalendarGridView, long j) {
        Month monthM = Month.m(j);
        Month month = this.b;
        if (monthM.equals(month)) {
            Calendar calendarC = fq0.c(month.b);
            calendarC.setTimeInMillis(j);
            int i2 = calendarC.get(5);
            d((TextView) materialCalendarGridView.getChildAt((materialCalendarGridView.a().a() + (i2 - 1)) - materialCalendarGridView.getFirstVisiblePosition()), j, i2);
        }
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return i;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i2) {
        return i2 / this.b.e;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005c  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            ea r1 = r5.e
            if (r1 != 0) goto Lf
            ea r1 = new ea
            r1.<init>(r0)
            r5.e = r1
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
            com.google.android.material.datepicker.Month r8 = r5.b
            int r2 = r8.f
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
