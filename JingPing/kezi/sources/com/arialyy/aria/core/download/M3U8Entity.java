package com.arialyy.aria.core.download;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.e2;
import defpackage.qi;

/* JADX INFO: loaded from: classes.dex */
public class M3U8Entity extends qi implements Parcelable {
    public static final Parcelable.Creator<M3U8Entity> CREATOR = new e2(7);
    public String c;
    public int d;
    public int e;
    public boolean f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeByte(this.f ? (byte) 1 : (byte) 0);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
    }
}
