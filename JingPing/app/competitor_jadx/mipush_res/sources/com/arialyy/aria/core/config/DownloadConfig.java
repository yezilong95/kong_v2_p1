package com.arialyy.aria.core.config;

import defpackage.di;
import defpackage.j31;
import defpackage.ou;
import defpackage.z91;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class DownloadConfig extends BaseTaskConfig implements Serializable {
    public int R;
    public boolean S;
    public boolean T;

    public int getThreadNum() {
        return this.R;
    }

    public boolean isUseBlock() {
        return this.S;
    }

    public boolean isUseHeadRequest() {
        return this.T;
    }

    public DownloadConfig setThreadNum(int i) {
        this.R = i;
        j31.q();
        throw null;
    }

    public DownloadConfig setUseBlock(boolean z) {
        this.S = z;
        j31.q();
        throw null;
    }

    public DownloadConfig setUseHeadRequest(boolean z) {
        this.T = z;
        j31.q();
        throw null;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public DownloadConfig setMaxSpeed(int i) {
        super.setMaxSpeed(i);
        ou.z().R(new di(5));
        return this;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public DownloadConfig setMaxTaskNum(int i) {
        if (i <= 0) {
            z91.t(this.E, "下载任务最大任务数不能小于0");
            return this;
        }
        super.setMaxTaskNum(i);
        ou.z().R(new di(4));
        return this;
    }
}
