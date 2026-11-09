package com.arialyy.aria.core.download;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.km;
import defpackage.y3;

/* JADX INFO: loaded from: classes.dex */
public class M3U8Entity extends km implements Parcelable {
    public static final Parcelable.Creator<M3U8Entity> CREATOR = new y3(7);
    public String F;
    public int G;
    public int H;
    public boolean I;
    public String J;
    public String K;
    public String L;
    public String M;
    public String N;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.F);
        parcel.writeInt(this.G);
        parcel.writeInt(this.H);
        parcel.writeByte(this.I ? (byte) 1 : (byte) 0);
        parcel.writeString(this.J);
        parcel.writeString(this.K);
        parcel.writeString(this.L);
        parcel.writeString(this.M);
        parcel.writeString(this.N);
    }
}
