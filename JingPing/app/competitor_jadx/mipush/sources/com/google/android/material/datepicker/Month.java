package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.h31;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new n();
    public final Calendar E;
    public final int F;
    public final int G;
    public final int H;
    public final int I;
    public final long J;
    public String K;

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarC = h31.c(calendar);
        this.E = calendarC;
        this.F = calendarC.get(2);
        this.G = calendarC.get(1);
        this.H = calendarC.getMaximum(7);
        this.I = calendarC.getActualMaximum(5);
        this.J = calendarC.getTimeInMillis();
    }

    public static Month l(int i, int i2) {
        Calendar calendarG = h31.g(null);
        calendarG.set(1, i);
        calendarG.set(2, i2);
        return new Month(calendarG);
    }

    public static Month m(long j) {
        Calendar calendarG = h31.g(null);
        calendarG.setTimeInMillis(j);
        return new Month(calendarG);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Month month) {
        return this.E.compareTo(month.E);
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
        return this.F == month.F && this.G == month.G;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.F), Integer.valueOf(this.G)});
    }

    public final String n() {
        if (this.K == null) {
            this.K = h31.b("yMMMM", Locale.getDefault()).format(new Date(this.E.getTimeInMillis()));
        }
        return this.K;
    }

    public final int o(Month month) {
        if (!(this.E instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        return (month.F - this.F) + ((month.G - this.G) * 12);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.G);
        parcel.writeInt(this.F);
    }
}
