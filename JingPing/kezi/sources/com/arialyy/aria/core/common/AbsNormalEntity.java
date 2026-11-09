package com.arialyy.aria.core.common;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsNormalEntity extends AbsEntity implements Parcelable {
    public String q;
    public String r;
    public boolean s;
    public boolean t;
    public String u;

    public AbsNormalEntity() {
        this.s = false;
        this.t = false;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFileName() {
        return this.r;
    }

    public abstract String getFilePath();

    public String getRedirectUrl() {
        return this.u;
    }

    public String getUrl() {
        return this.q;
    }

    public boolean isGroupChild() {
        return this.s;
    }

    public boolean isRedirect() {
        return this.t;
    }

    public void setFileName(String str) {
        this.r = str;
    }

    public void setGroupChild(boolean z) {
        this.s = z;
    }

    public void setRedirect(boolean z) {
        this.t = z;
    }

    public void setRedirectUrl(String str) {
        this.u = str;
    }

    public void setTaskType(int i) {
    }

    public void setUrl(String str) {
        this.q = str;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        parcel.writeByte(this.s ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.t ? (byte) 1 : (byte) 0);
        parcel.writeString(this.u);
    }

    public AbsNormalEntity(Parcel parcel) {
        super(parcel);
        this.s = false;
        this.t = false;
        this.q = parcel.readString();
        this.r = parcel.readString();
        this.s = parcel.readByte() != 0;
        this.t = parcel.readByte() != 0;
        this.u = parcel.readString();
    }
}
