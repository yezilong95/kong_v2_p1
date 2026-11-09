package com.arialyy.aria.core.config;

import defpackage.nv0;
import defpackage.oy;
import defpackage.w6;
import defpackage.w7;
import java.io.Serializable;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class AppConfig extends w7 implements Serializable {
    public boolean b;
    public int c;
    public boolean d = true;
    public boolean e = false;
    public boolean f = false;

    public int getLogLevel() {
        return this.c;
    }

    public boolean getUseAriaCrashHandler() {
        return this.b;
    }

    public boolean isNetCheck() {
        return this.d;
    }

    public boolean isNotNetRetry() {
        return this.f;
    }

    public boolean isUseBroadcast() {
        return this.e;
    }

    public AppConfig setLogLevel(int i) {
        this.c = i;
        oy.h = i;
        nv0.q();
        throw null;
    }

    public AppConfig setNetCheck(boolean z) {
        this.d = z;
        nv0.q();
        throw null;
    }

    public AppConfig setNotNetRetry(boolean z) {
        this.f = z;
        nv0.q();
        throw null;
    }

    public AppConfig setUseAriaCrashHandler(boolean z) {
        this.b = z;
        if (z) {
            w6 w6Var = new w6();
            w6Var.a = Thread.getDefaultUncaughtExceptionHandler();
            w6Var.b = Executors.newSingleThreadExecutor();
            Thread.setDefaultUncaughtExceptionHandler(w6Var);
        } else {
            Thread.setDefaultUncaughtExceptionHandler(null);
        }
        nv0.q();
        throw null;
    }

    public AppConfig setUseBroadcast(boolean z) {
        this.e = z;
        nv0.q();
        throw null;
    }
}
