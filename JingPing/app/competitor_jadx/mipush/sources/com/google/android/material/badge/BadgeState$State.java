package com.google.android.material.badge;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.y3;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class BadgeState$State implements Parcelable {
    public static final Parcelable.Creator<BadgeState$State> CREATOR = new y3(1);
    public int E;
    public Integer F;
    public Integer G;
    public Integer H;
    public Integer I;
    public Integer J;
    public Integer K;
    public Integer L;
    public String N;
    public Locale R;
    public String S;
    public CharSequence T;
    public int U;
    public int V;
    public Integer W;
    public Integer Y;
    public Integer Z;
    public Integer a0;
    public Integer b0;
    public Integer c0;
    public Integer d0;
    public Integer e0;
    public Integer f0;
    public Integer g0;
    public Boolean h0;
    public int M = 255;
    public int O = -2;
    public int P = -2;
    public int Q = -2;
    public Boolean X = Boolean.TRUE;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.E);
        parcel.writeSerializable(this.F);
        parcel.writeSerializable(this.G);
        parcel.writeSerializable(this.H);
        parcel.writeSerializable(this.I);
        parcel.writeSerializable(this.J);
        parcel.writeSerializable(this.K);
        parcel.writeSerializable(this.L);
        parcel.writeInt(this.M);
        parcel.writeString(this.N);
        parcel.writeInt(this.O);
        parcel.writeInt(this.P);
        parcel.writeInt(this.Q);
        String str = this.S;
        parcel.writeString(str != null ? str.toString() : null);
        CharSequence charSequence = this.T;
        parcel.writeString(charSequence != null ? charSequence.toString() : null);
        parcel.writeInt(this.U);
        parcel.writeSerializable(this.W);
        parcel.writeSerializable(this.Y);
        parcel.writeSerializable(this.Z);
        parcel.writeSerializable(this.a0);
        parcel.writeSerializable(this.b0);
        parcel.writeSerializable(this.c0);
        parcel.writeSerializable(this.d0);
        parcel.writeSerializable(this.g0);
        parcel.writeSerializable(this.e0);
        parcel.writeSerializable(this.f0);
        parcel.writeSerializable(this.X);
        parcel.writeSerializable(this.R);
        parcel.writeSerializable(this.h0);
    }
}
