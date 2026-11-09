package com.arialyy.aria.core.config;

import defpackage.oy;
import defpackage.po;
import defpackage.yo;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class UploadConfig extends BaseTaskConfig implements Serializable {
    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public UploadConfig setMaxSpeed(int i) {
        super.setMaxSpeed(i);
        po.x().O(new yo(28));
        return this;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public UploadConfig setMaxTaskNum(int i) {
        if (i <= 0) {
            oy.s(this.b, "上传任务最大任务数不能小于0");
            return this;
        }
        super.setMaxTaskNum(i);
        po.x().O(new yo(27));
        return this;
    }
}
