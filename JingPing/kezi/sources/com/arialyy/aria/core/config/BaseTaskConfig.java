package com.arialyy.aria.core.config;

import defpackage.nv0;
import defpackage.oy;
import defpackage.re;
import defpackage.w7;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTaskConfig extends w7 implements Serializable {
    public final String b;
    public int c;
    public long d;
    public int e;
    public int f;
    public int g;
    public int h;
    public boolean i;
    public String j;
    public int k;
    public int l;
    public String m;
    public String n;
    public int oldMaxTaskNum;

    public BaseTaskConfig() {
        Class<?> cls = getClass();
        int i = re.a;
        this.b = cls.getName().split("\\.")[r0.length - 1];
        this.c = 8192;
        this.d = 1000L;
        this.oldMaxTaskNum = 2;
        this.e = 2;
        this.f = 10;
        this.g = 2000;
        this.h = 5000;
        this.i = false;
        this.j = "wait";
        this.k = 20000;
        this.l = 0;
    }

    public int getBuffSize() {
        return this.c;
    }

    public String getCaName() {
        return this.n;
    }

    public String getCaPath() {
        return this.m;
    }

    public int getConnectTimeOut() {
        return this.h;
    }

    public int getIOTimeOut() {
        return this.k;
    }

    public int getMaxSpeed() {
        return this.l;
    }

    public int getMaxTaskNum() {
        return this.e;
    }

    public String getQueueMod() {
        return this.j;
    }

    public int getReTryInterval() {
        return this.g;
    }

    public int getReTryNum() {
        return this.f;
    }

    public long getUpdateInterval() {
        return this.d;
    }

    public boolean isConvertSpeed() {
        return this.i;
    }

    public BaseTaskConfig setBuffSize(int i) {
        this.c = i;
        nv0.q();
        throw null;
    }

    public w7 setCaName(String str) {
        this.n = str;
        nv0.q();
        throw null;
    }

    public w7 setCaPath(String str) {
        this.m = str;
        nv0.q();
        throw null;
    }

    public BaseTaskConfig setConnectTimeOut(int i) {
        this.h = i;
        nv0.q();
        throw null;
    }

    public BaseTaskConfig setConvertSpeed(boolean z) {
        this.i = z;
        nv0.q();
        throw null;
    }

    public BaseTaskConfig setIOTimeOut(int i) {
        this.k = i;
        nv0.q();
        throw null;
    }

    public BaseTaskConfig setMaxSpeed(int i) {
        this.l = i;
        nv0.q();
        throw null;
    }

    public BaseTaskConfig setMaxTaskNum(int i) {
        this.oldMaxTaskNum = this.e;
        this.e = i;
        nv0.q();
        throw null;
    }

    public BaseTaskConfig setQueueMod(String str) {
        this.j = str;
        nv0.q();
        throw null;
    }

    public BaseTaskConfig setReTryInterval(int i) {
        this.g = i;
        nv0.q();
        throw null;
    }

    public BaseTaskConfig setReTryNum(int i) {
        this.f = i;
        nv0.q();
        throw null;
    }

    public BaseTaskConfig setUpdateInterval(long j) {
        if (j <= 0) {
            oy.J(5, "Configuration", "进度更新间隔不能小于0");
            return this;
        }
        this.d = j;
        nv0.q();
        throw null;
    }
}
