package com.arialyy.aria.core.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsGroupEntity extends AbsEntity implements Parcelable {
    public String T;
    public String U;
    public String V;
    public List W = new ArrayList();

    @Override // com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAlias() {
        return this.U;
    }

    public String getDirPath() {
        return this.V;
    }

    public String getGroupHash() {
        return this.T;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public String getKey() {
        return this.T;
    }

    public List<String> getUrls() {
        return this.W;
    }

    public void setAlias(String str) {
        this.U = str;
    }

    public void setDirPath(String str) {
        this.V = str;
    }

    public void setUrls(List<String> list) {
        this.W = list;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.T);
        parcel.writeString(this.U);
    }
}
