package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import defpackage.y3;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DateValidatorPointBackward implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<DateValidatorPointBackward> CREATOR = new y3(2);
    public final long E;

    public DateValidatorPointBackward(long j) {
        this.E = j;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public final boolean e(long j) {
        return j <= this.E;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DateValidatorPointBackward) && this.E == ((DateValidatorPointBackward) obj).E;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.E)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.E);
    }
}
