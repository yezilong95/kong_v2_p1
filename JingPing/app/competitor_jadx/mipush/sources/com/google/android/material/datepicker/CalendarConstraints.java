package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.h31;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();
    public final Month E;
    public final Month F;
    public final DateValidator G;
    public final Month H;
    public final int I;
    public final int J;
    public final int K;

    public interface DateValidator extends Parcelable {
        boolean e(long j);
    }

    public CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, int i) {
        Objects.requireNonNull(month, "start cannot be null");
        Objects.requireNonNull(month2, "end cannot be null");
        Objects.requireNonNull(dateValidator, "validator cannot be null");
        this.E = month;
        this.F = month2;
        this.H = month3;
        this.I = i;
        this.G = dateValidator;
        if (month3 != null && month.E.compareTo(month3.E) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3 != null && month3.E.compareTo(month2.E) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        if (i < 0 || i > h31.g(null).getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        }
        this.K = month.o(month2) + 1;
        this.J = (month2.G - month.G) + 1;
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
        return this.E.equals(calendarConstraints.E) && this.F.equals(calendarConstraints.F) && Objects.equals(this.H, calendarConstraints.H) && this.I == calendarConstraints.I && this.G.equals(calendarConstraints.G);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.E, this.F, this.H, Integer.valueOf(this.I), this.G});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.E, 0);
        parcel.writeParcelable(this.F, 0);
        parcel.writeParcelable(this.H, 0);
        parcel.writeParcelable(this.G, 0);
        parcel.writeInt(this.I);
    }
}
