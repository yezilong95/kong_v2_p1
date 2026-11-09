package com.arialyy.aria.core.download;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import com.arialyy.aria.core.common.AbsNormalEntity;
import defpackage.e2;
import defpackage.hk;
import defpackage.oy;
import defpackage.qi;
import defpackage.re;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class DownloadEntity extends AbsNormalEntity implements Parcelable, Cloneable {
    public static final Parcelable.Creator<DownloadEntity> CREATOR = new e2(4);
    public M3U8Entity A;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;

    @Override // com.arialyy.aria.core.common.AbsNormalEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDisposition() {
        if (TextUtils.isEmpty(this.y)) {
            return ExtensionRequestData.EMPTY_VALUE;
        }
        String str = this.y;
        int i = re.a;
        return new String(Base64.decode(str.getBytes(), 0));
    }

    @Override // com.arialyy.aria.core.common.AbsNormalEntity
    public String getFilePath() {
        return this.v;
    }

    public String getGroupHash() {
        return this.w;
    }

    @Override // com.arialyy.aria.core.common.AbsEntity
    public String getKey() {
        return getUrl();
    }

    public M3U8Entity getM3U8Entity() {
        if (TextUtils.isEmpty(this.v)) {
            oy.s("DownloadEntity", "文件保存路径为空，获取m3u8实体之前需要设置文件保存路径");
            return null;
        }
        if (this.A == null) {
            this.A = (M3U8Entity) qi.findFirst(M3U8Entity.class, "filePath=?", this.v);
        }
        return this.A;
    }

    public String getMd5Code() {
        return this.x;
    }

    public String getRealUrl() {
        return isRedirect() ? getRedirectUrl() : getUrl();
    }

    public String getServerFileName() {
        return this.z;
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
        return m3U8Entity.f ? 8 : 7;
    }

    public void setDisposition(String str) {
        this.y = str;
    }

    public DownloadEntity setFilePath(String str) {
        this.v = str;
        return this;
    }

    public void setGroupHash(String str) {
        this.w = str;
    }

    public void setMd5Code(String str) {
        this.x = str;
    }

    public void setServerFileName(String str) {
        this.z = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DownloadEntity{downloadPath='");
        sb.append(this.v);
        sb.append("', groupHash='");
        sb.append(this.w);
        sb.append("', fileName='");
        sb.append(getFileName());
        sb.append("', md5Code='");
        sb.append(this.x);
        sb.append("', disposition='");
        sb.append(this.y);
        sb.append("', serverFileName='");
        return hk.k(sb, this.z, "'}");
    }

    @Override // com.arialyy.aria.core.common.AbsNormalEntity, com.arialyy.aria.core.common.AbsEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeString(this.x);
        parcel.writeString(this.y);
        parcel.writeString(this.z);
        parcel.writeParcelable(this.A, i);
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public DownloadEntity m1clone() throws CloneNotSupportedException {
        return (DownloadEntity) super.clone();
    }
}
