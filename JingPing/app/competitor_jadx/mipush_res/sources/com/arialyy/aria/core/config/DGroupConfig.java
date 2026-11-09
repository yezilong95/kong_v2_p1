package com.arialyy.aria.core.config;

import defpackage.di;
import defpackage.j31;
import defpackage.ou;
import defpackage.z91;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class DGroupConfig extends BaseTaskConfig implements Serializable {
    public int R;
    public boolean S;
    public int T;
    public int U;

    public DownloadConfig getSubConfig() {
        j31.q();
        throw null;
    }

    public int getSubMaxTaskNum() {
        return this.R;
    }

    public int getSubReTryInterval() {
        return this.U;
    }

    public int getSubReTryNum() {
        return this.T;
    }

    public boolean isSubFailAsStop() {
        return this.S;
    }

    public DGroupConfig setSubFailAsStop(boolean z) {
        this.S = z;
        j31.q();
        throw null;
    }

    public DGroupConfig setSubMaxTaskNum(int i) {
        this.R = i;
        j31.q();
        throw null;
    }

    public DGroupConfig setSubReTryInterval(int i) {
        this.U = i;
        throw null;
    }

    public DGroupConfig setSubReTryNum(int i) {
        this.T = i;
        throw null;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public DGroupConfig setMaxSpeed(int i) {
        super.setMaxSpeed(i);
        ou.z().R(new di(5));
        return this;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public DGroupConfig setMaxTaskNum(int i) {
        if (i <= 0) {
            z91.t(this.E, "组合任务最大任务数不能小于0");
            return this;
        }
        super.setMaxTaskNum(i);
        ou.z().R(new di(3));
        return this;
    }
}
