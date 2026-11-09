package com.arialyy.aria.core.config;

import defpackage.nv0;
import defpackage.oy;
import defpackage.po;
import defpackage.qm;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class DGroupConfig extends BaseTaskConfig implements Serializable {
    public int o;
    public boolean p;
    public int q;
    public int r;

    public DownloadConfig getSubConfig() {
        nv0.q();
        throw null;
    }

    public int getSubMaxTaskNum() {
        return this.o;
    }

    public int getSubReTryInterval() {
        return this.r;
    }

    public int getSubReTryNum() {
        return this.q;
    }

    public boolean isSubFailAsStop() {
        return this.p;
    }

    public DGroupConfig setSubFailAsStop(boolean z) {
        this.p = z;
        nv0.q();
        throw null;
    }

    public DGroupConfig setSubMaxTaskNum(int i) {
        this.o = i;
        nv0.q();
        throw null;
    }

    public DGroupConfig setSubReTryInterval(int i) {
        this.r = i;
        throw null;
    }

    public DGroupConfig setSubReTryNum(int i) {
        this.q = i;
        throw null;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public DGroupConfig setMaxSpeed(int i) {
        super.setMaxSpeed(i);
        po.x().O(new qm());
        return this;
    }

    @Override // com.arialyy.aria.core.config.BaseTaskConfig
    public DGroupConfig setMaxTaskNum(int i) {
        if (i <= 0) {
            oy.s(this.b, "组合任务最大任务数不能小于0");
            return this;
        }
        super.setMaxTaskNum(i);
        po.x().O(new qm());
        return this;
    }
}
