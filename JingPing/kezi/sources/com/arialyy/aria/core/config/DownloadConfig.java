package com.arialyy.aria.core.config;

import defpackage.nv0;
import defpackage.oy;
import defpackage.po;
import defpackage.qm;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class DownloadConfig extends BaseTaskConfig implements Serializable {
    public int o;
    public boolean p;
    public boolean q;

    public int getThreadNum() {
        return this.o;
    }

    public boolean isUseBlock() {
        return this.p;
    }

    public boolean isUseHeadRequest() {
        return this.q;
    }

    public DownloadConfig setThreadNum(int i) {
        this.o = i;
        nv0.q();
        throw null;
    }

    public DownloadConfig setUseBlock(boolean z) {
        this.p = z;
        nv0.q();
        throw null;
    }

    public DownloadConfig setUseHeadRequest(boolean z) {
        this.q = z;
        nv0.q();
        throw null;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public DownloadConfig setMaxSpeed(int i) {
        super.setMaxSpeed(i);
        po.x().O(new qm());
        return this;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public DownloadConfig setMaxTaskNum(int i) {
        if (i <= 0) {
            oy.s(this.b, "下载任务最大任务数不能小于0");
            return this;
        }
        super.setMaxTaskNum(i);
        po.x().O(new qm());
        return this;
    }
}
