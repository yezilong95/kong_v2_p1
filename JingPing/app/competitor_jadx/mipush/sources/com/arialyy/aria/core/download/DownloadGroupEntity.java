package com.arialyy.aria.core.download;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.arialyy.aria.core.common.AbsGroupEntity;
import defpackage.y3;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DownloadGroupEntity extends AbsGroupEntity {
    public static final Parcelable.Creator<DownloadGroupEntity> CREATOR = new y3(5);
    public List X;

    @Override // com.arialyy.aria.core.common.AbsGroupEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<DownloadEntity> getSubEntities() {
        if (this.X == null) {
            this.X = new ArrayList();
        }
        return this.X;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public int getTaskType() {
        if (getSubEntities() == null || getSubEntities().isEmpty() || TextUtils.isEmpty(getSubEntities().get(0).getUrl())) {
            return 0;
        }
        return (this.T.startsWith("ftp") || this.T.startsWith("sftp")) ? 4 : 2;
    }

    public void setGroupHash(String str) {
        this.T = str;
    }

    public void setSubEntities(List<DownloadEntity> list) {
        this.X = list;
    }

    @Override // com.arialyy.aria.core.common.AbsGroupEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.X);
    }
}
