package com.arialyy.aria.core.download;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.arialyy.aria.core.common.AbsGroupEntity;
import defpackage.e2;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DownloadGroupEntity extends AbsGroupEntity {
    public static final Parcelable.Creator<DownloadGroupEntity> CREATOR = new e2(5);
    public List u;

    @Override // com.arialyy.aria.core.common.AbsGroupEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<DownloadEntity> getSubEntities() {
        if (this.u == null) {
            this.u = new ArrayList();
        }
        return this.u;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public int getTaskType() {
        if (getSubEntities() == null || getSubEntities().isEmpty() || TextUtils.isEmpty(getSubEntities().get(0).getUrl())) {
            return 0;
        }
        return (this.q.startsWith("ftp") || this.q.startsWith("sftp")) ? 4 : 2;
    }

    public void setGroupHash(String str) {
        this.q = str;
    }

    public void setSubEntities(List<DownloadEntity> list) {
        this.u = list;
    }

    @Override // com.arialyy.aria.core.common.AbsGroupEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.u);
    }
}
