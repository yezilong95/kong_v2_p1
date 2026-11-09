package com.google.android.material.timepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class TimeModel implements Parcelable {
    public static final Parcelable.Creator<TimeModel> CREATOR = new e();
    public final int E;
    public final int F;
    public final int G;
    public final int H;

    public TimeModel(Parcel parcel) {
        int i = parcel.readInt();
        int i2 = parcel.readInt();
        int i3 = parcel.readInt();
        int i4 = parcel.readInt();
        this.F = i;
        this.G = i2;
        this.H = i3;
        this.E = i4;
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
        return this.F == timeModel.F && this.G == timeModel.G && this.E == timeModel.E && this.H == timeModel.H;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.E), Integer.valueOf(this.F), Integer.valueOf(this.G), Integer.valueOf(this.H)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.F);
        parcel.writeInt(this.G);
        parcel.writeInt(this.H);
        parcel.writeInt(this.E);
    }
}
