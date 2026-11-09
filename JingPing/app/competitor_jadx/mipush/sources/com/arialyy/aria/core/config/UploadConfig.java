package com.arialyy.aria.core.config;

import defpackage.er0;
import defpackage.ou;
import defpackage.z91;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class UploadConfig extends BaseTaskConfig implements Serializable {
    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public UploadConfig setMaxSpeed(int i) {
        super.setMaxSpeed(i);
        ou.z().R(new er0());
        return this;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public UploadConfig setMaxTaskNum(int i) {
        if (i <= 0) {
            z91.t(this.E, "上传任务最大任务数不能小于0");
            return this;
        }
        super.setMaxTaskNum(i);
        ou.z().R(new er0());
        return this;
    }
}
