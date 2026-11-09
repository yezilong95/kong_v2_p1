package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.fq0;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();
    public final Month b;
    public final Month c;
    public final DateValidator d;
    public final Month e;
    public final int f;
    public final int g;
    public final int h;

    public interface DateValidator extends Parcelable {
        boolean f(long j);
    }

    public CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, int i) {
        Objects.requireNonNull(month, "start cannot be null");
        Objects.requireNonNull(month2, "end cannot be null");
        Objects.requireNonNull(dateValidator, "validator cannot be null");
        this.b = month;
        this.c = month2;
        this.e = month3;
        this.f = i;
        this.d = dateValidator;
        if (month3 != null && month.b.compareTo(month3.b) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3 != null && month3.b.compareTo(month2.b) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        if (i < 0 || i > fq0.g(null).getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        }
        this.h = month.o(month2) + 1;
        this.g = (month2.d - month.d) + 1;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        return this.b.equals(calendarConstraints.b) && this.c.equals(calendarConstraints.c) && Objects.equals(this.e, calendarConstraints.e) && this.f == calendarConstraints.f && this.d.equals(calendarConstraints.d);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.b, this.c, this.e, Integer.valueOf(this.f), this.d});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.b, 0);
        parcel.writeParcelable(this.c, 0);
        parcel.writeParcelable(this.e, 0);
        parcel.writeParcelable(this.d, 0);
        parcel.writeInt(this.f);
    }
}
