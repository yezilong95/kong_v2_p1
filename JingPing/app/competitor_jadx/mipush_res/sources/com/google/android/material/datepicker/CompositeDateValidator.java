package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {
    public final f E;
    public final ArrayList F;
    public static final c G = new c();
    public static final d H = new d();
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new e();

    public CompositeDateValidator(ArrayList arrayList, f fVar) {
        this.F = arrayList;
        this.E = fVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public final boolean e(long j) {
        return this.E.a(this.F, j);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompositeDateValidator)) {
            return false;
        }
        CompositeDateValidator compositeDateValidator = (CompositeDateValidator) obj;
        return this.F.equals(compositeDateValidator.F) && this.E.getId() == compositeDateValidator.E.getId();
    }

    public final int hashCode() {
        return this.F.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.F);
        parcel.writeInt(this.E.getId());
    }
}
