package com.google.android.material.timepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class TimeModel implements Parcelable {
    public static final Parcelable.Creator<TimeModel> CREATOR = new e();
    public final int b;
    public final int c;
    public final int d;
    public final int e;

    public TimeModel(Parcel parcel) {
        int i = parcel.readInt();
        int i2 = parcel.readInt();
        int i3 = parcel.readInt();
        int i4 = parcel.readInt();
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.b = i4;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeModel)) {
            return false;
        }
        TimeModel timeModel = (TimeModel) obj;
        return this.c == timeModel.c && this.d == timeModel.d && this.b == timeModel.b && this.e == timeModel.e;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.b);
    }
}
