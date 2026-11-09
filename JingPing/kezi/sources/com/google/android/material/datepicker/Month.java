package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.fq0;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new n();
    public final Calendar b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final long g;
    public String h;

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarC = fq0.c(calendar);
        this.b = calendarC;
        this.c = calendarC.get(2);
        this.d = calendarC.get(1);
        this.e = calendarC.getMaximum(7);
        this.f = calendarC.getActualMaximum(5);
        this.g = calendarC.getTimeInMillis();
    }

    public static Month l(int i, int i2) {
        Calendar calendarG = fq0.g(null);
        calendarG.set(1, i);
        calendarG.set(2, i2);
        return new Month(calendarG);
    }

    public static Month m(long j) {
        Calendar calendarG = fq0.g(null);
        calendarG.setTimeInMillis(j);
        return new Month(calendarG);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Month month) {
        return this.b.compareTo(month.b);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        return this.c == month.c && this.d == month.d;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d)});
    }

    public final String n() {
        if (this.h == null) {
            this.h = fq0.b("yMMMM", Locale.getDefault()).format(new Date(this.b.getTimeInMillis()));
        }
        return this.h;
    }

    public final int o(Month month) {
        if (!(this.b instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        return (month.c - this.c) + ((month.d - this.d) * 12);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.d);
        parcel.writeInt(this.c);
    }
}
