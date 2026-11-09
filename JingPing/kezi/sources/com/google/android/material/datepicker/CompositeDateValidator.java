package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {
    public final f b;
    public final ArrayList c;
    public static final c d = new c();
    public static final d e = new d();
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new e();

    public CompositeDateValidator(ArrayList arrayList, f fVar) {
        this.c = arrayList;
        this.b = fVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompositeDateValidator)) {
            return false;
        }
        CompositeDateValidator compositeDateValidator = (CompositeDateValidator) obj;
        return this.c.equals(compositeDateValidator.c) && this.b.getId() == compositeDateValidator.b.getId();
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public final boolean f(long j) {
        return this.b.a(this.c, j);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.c);
        parcel.writeInt(this.b.getId());
    }
}
