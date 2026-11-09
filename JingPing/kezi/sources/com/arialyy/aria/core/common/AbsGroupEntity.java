package com.arialyy.aria.core.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsGroupEntity extends AbsEntity implements Parcelable {
    public String q;
    public String r;
    public String s;
    public List t = new ArrayList();

    @Override // com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAlias() {
        return this.r;
    }

    public String getDirPath() {
        return this.s;
    }

    public String getGroupHash() {
        return this.q;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public String getKey() {
        return this.q;
    }

    public List<String> getUrls() {
        return this.t;
    }

    public void setAlias(String str) {
        this.r = str;
    }

    public void setDirPath(String str) {
        this.s = str;
    }

    public void setUrls(List<String> list) {
        this.t = list;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
    }
}
