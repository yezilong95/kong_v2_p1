package com.arialyy.aria.core.upload;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.arialyy.aria.core.common.AbsNormalEntity;
import defpackage.y3;

/* JADX INFO: loaded from: classes.dex */
public class UploadEntity extends AbsNormalEntity implements Parcelable {
    public static final Parcelable.Creator<UploadEntity> CREATOR = new y3(13);
    public String Y;
    public String Z = "";

    @Override // com.arialyy.aria.core.common.AbsNormalEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.arialyy.aria.core.common.AbsNormalEntity
    public String getFilePath() {
        return this.Y;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public String getKey() {
        return this.Y;
    }

    public String getResponseStr() {
        return this.Z;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public int getTaskType() {
        if (TextUtils.isEmpty(getUrl())) {
            return 0;
        }
        return getUrl().startsWith("ftp") ? 6 : 5;
    }

    public void setFilePath(String str) {
        this.Y = str;
    }

    public void setResponseStr(String str) {
        this.Z = str;
    }

    @Override // com.arialyy.aria.core.common.AbsNormalEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.Y);
    }
}
