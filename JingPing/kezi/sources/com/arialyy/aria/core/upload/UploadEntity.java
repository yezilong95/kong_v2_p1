package com.arialyy.aria.core.upload;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.arialyy.aria.core.common.AbsNormalEntity;
import defpackage.e2;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class UploadEntity extends AbsNormalEntity implements Parcelable {
    public static final Parcelable.Creator<UploadEntity> CREATOR = new e2(13);
    public String v;
    public String w = ExtensionRequestData.EMPTY_VALUE;

    @Override // com.arialyy.aria.core.common.AbsNormalEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.arialyy.aria.core.common.AbsNormalEntity
    public String getFilePath() {
        return this.v;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public String getKey() {
        return this.v;
    }

    public String getResponseStr() {
        return this.w;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public int getTaskType() {
        if (TextUtils.isEmpty(getUrl())) {
            return 0;
        }
        return getUrl().startsWith("ftp") ? 6 : 5;
    }

    public void setFilePath(String str) {
        this.v = str;
    }

    public void setResponseStr(String str) {
        this.w = str;
    }

    @Override // com.arialyy.aria.core.common.AbsNormalEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.v);
    }
}
