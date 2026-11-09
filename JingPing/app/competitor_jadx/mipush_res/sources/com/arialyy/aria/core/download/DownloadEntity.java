package com.arialyy.aria.core.download;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import com.arialyy.aria.core.common.AbsNormalEntity;
import defpackage.km;
import defpackage.lg;
import defpackage.y3;
import defpackage.z91;
import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public class DownloadEntity extends AbsNormalEntity implements Parcelable, Cloneable {
    public static final Parcelable.Creator<DownloadEntity> CREATOR = new y3(4);
    public String Y;
    public String Z;
    public String a0;
    public String b0;
    public String c0;
    public M3U8Entity d0;

    @Override // com.arialyy.aria.core.common.AbsNormalEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDisposition() {
        if (TextUtils.isEmpty(this.b0)) {
            return "";
        }
        String str = this.b0;
        int i = lg.a;
        return new String(Base64.decode(str.getBytes(), 0));
    }

    @Override // com.arialyy.aria.core.common.AbsNormalEntity
    public String getFilePath() {
        return this.Y;
    }

    public String getGroupHash() {
        return this.Z;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public String getKey() {
        return getUrl();
    }

    public M3U8Entity getM3U8Entity() {
        if (TextUtils.isEmpty(this.Y)) {
            z91.t("DownloadEntity", "文件保存路径为空，获取m3u8实体之前需要设置文件保存路径");
            return null;
        }
        if (this.d0 == null) {
            this.d0 = (M3U8Entity) km.findFirst(M3U8Entity.class, "filePath=?", this.Y);
        }
        return this.d0;
    }

    public String getMd5Code() {
        return this.a0;
    }

    public String getRealUrl() {
        return isRedirect() ? getRedirectUrl() : getUrl();
    }

    public String getServerFileName() {
        return this.c0;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public int getTaskType() {
        if (getUrl() == null) {
            return 0;
        }
        if (!getUrl().startsWith("http")) {
            if (getUrl().startsWith("ftp")) {
                return 3;
            }
            return getUrl().startsWith("sftp") ? 12 : 0;
        }
        M3U8Entity m3U8Entity = getM3U8Entity();
        if (m3U8Entity == null) {
            return 1;
        }
        return m3U8Entity.I ? 8 : 7;
    }

    public void setDisposition(String str) {
        this.b0 = str;
    }

    public DownloadEntity setFilePath(String str) {
        this.Y = str;
        return this;
    }

    public void setGroupHash(String str) {
        this.Z = str;
    }

    public void setMd5Code(String str) {
        this.a0 = str;
    }

    public void setServerFileName(String str) {
        this.c0 = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DownloadEntity{downloadPath='");
        sb.append(this.Y);
        sb.append("', groupHash='");
        sb.append(this.Z);
        sb.append("', fileName='");
        sb.append(getFileName());
        sb.append("', md5Code='");
        sb.append(this.a0);
        sb.append("', disposition='");
        sb.append(this.b0);
        sb.append("', serverFileName='");
        return zn.o(sb, this.c0, "'}");
    }

    @Override // com.arialyy.aria.core.common.AbsNormalEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.Y);
        parcel.writeString(this.Z);
        parcel.writeString(this.a0);
        parcel.writeString(this.b0);
        parcel.writeString(this.c0);
        parcel.writeParcelable(this.d0, i);
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public DownloadEntity m1clone() throws CloneNotSupportedException {
        return (DownloadEntity) super.clone();
    }
}
