package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        ArrayList arrayList = parcel.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader());
        int i = parcel.readInt();
        f fVar = CompositeDateValidator.H;
        if (i != 2 && i == 1) {
            fVar = CompositeDateValidator.G;
        }
        arrayList.getClass();
        return new CompositeDateValidator(arrayList, fVar);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new CompositeDateValidator[i];
    }
}
