package com.arialyy.aria.core.config;

import defpackage.j31;
import defpackage.lg;
import defpackage.r9;
import defpackage.z91;
import io.nats.client.Options;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTaskConfig extends r9 implements Serializable {
    public final String E;
    public int F;
    public long G;
    public int H;
    public int I;
    public int J;
    public int K;
    public boolean L;
    public String M;
    public int N;
    public int O;
    public String P;
    public String Q;
    public int oldMaxTaskNum;

    public BaseTaskConfig() {
        Class<?> cls = getClass();
        int i = lg.a;
        this.E = cls.getName().split("\\.")[r0.length - 1];
        this.F = 8192;
        this.G = 1000L;
        this.oldMaxTaskNum = 2;
        this.H = 2;
        this.I = 10;
        this.J = 2000;
        this.K = Options.DEFAULT_MAX_MESSAGES_IN_OUTGOING_QUEUE;
        this.L = false;
        this.M = "wait";
        this.N = 20000;
        this.O = 0;
    }

    public int getBuffSize() {
        return this.F;
    }

    public String getCaName() {
        return this.Q;
    }

    public String getCaPath() {
        return this.P;
    }

    public int getConnectTimeOut() {
        return this.K;
    }

    public int getIOTimeOut() {
        return this.N;
    }

    public int getMaxSpeed() {
        return this.O;
    }

    public int getMaxTaskNum() {
        return this.H;
    }

    public String getQueueMod() {
        return this.M;
    }

    public int getReTryInterval() {
        return this.J;
    }

    public int getReTryNum() {
        return this.I;
    }

    public long getUpdateInterval() {
        return this.G;
    }

    public boolean isConvertSpeed() {
        return this.L;
    }

    public BaseTaskConfig setBuffSize(int i) {
        this.F = i;
        j31.q();
        throw null;
    }

    public r9 setCaName(String str) {
        this.Q = str;
        j31.q();
        throw null;
    }

    public r9 setCaPath(String str) {
        this.P = str;
        j31.q();
        throw null;
    }

    public BaseTaskConfig setConnectTimeOut(int i) {
        this.K = i;
        j31.q();
        throw null;
    }

    public BaseTaskConfig setConvertSpeed(boolean z) {
        this.L = z;
        j31.q();
        throw null;
    }

    public BaseTaskConfig setIOTimeOut(int i) {
        this.N = i;
        j31.q();
        throw null;
    }

    public BaseTaskConfig setMaxSpeed(int i) {
        this.O = i;
        j31.q();
        throw null;
    }

    public BaseTaskConfig setMaxTaskNum(int i) {
        this.oldMaxTaskNum = this.H;
        this.H = i;
        j31.q();
        throw null;
    }

    public BaseTaskConfig setQueueMod(String str) {
        this.M = str;
        j31.q();
        throw null;
    }

    public BaseTaskConfig setReTryInterval(int i) {
        this.J = i;
        j31.q();
        throw null;
    }

    public BaseTaskConfig setReTryNum(int i) {
        this.I = i;
        j31.q();
        throw null;
    }

    public BaseTaskConfig setUpdateInterval(long j) {
        if (j <= 0) {
            z91.a0(5, "Configuration", "进度更新间隔不能小于0");
            return this;
        }
        this.G = j;
        j31.q();
        throw null;
    }
}
