package com.arialyy.aria.core.common;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsNormalEntity extends AbsEntity implements Parcelable {
    public String T;
    public String U;
    public boolean V;
    public boolean W;
    public String X;

    public AbsNormalEntity() {
        this.V = false;
        this.W = false;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFileName() {
        return this.U;
    }

    public abstract String getFilePath();

    public String getRedirectUrl() {
        return this.X;
    }

    public String getUrl() {
        return this.T;
    }

    public boolean isGroupChild() {
        return this.V;
    }

    public boolean isRedirect() {
        return this.W;
    }

    public void setFileName(String str) {
        this.U = str;
    }

    public void setGroupChild(boolean z) {
        this.V = z;
    }

    public void setRedirect(boolean z) {
        this.W = z;
    }

    public void setRedirectUrl(String str) {
        this.X = str;
    }

    public void setTaskType(int i) {
    }

    public void setUrl(String str) {
        this.T = str;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.T);
        parcel.writeString(this.U);
        parcel.writeByte(this.V ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.W ? (byte) 1 : (byte) 0);
        parcel.writeString(this.X);
    }

    public AbsNormalEntity(Parcel parcel) {
        super(parcel);
        this.V = false;
        this.W = false;
        this.T = parcel.readString();
        this.U = parcel.readString();
        this.V = parcel.readByte() != 0;
        this.W = parcel.readByte() != 0;
        this.X = parcel.readString();
    }
}
