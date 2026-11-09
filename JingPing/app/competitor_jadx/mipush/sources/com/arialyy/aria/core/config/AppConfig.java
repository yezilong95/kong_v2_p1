package com.arialyy.aria.core.config;

import defpackage.j31;
import defpackage.k;
import defpackage.r9;
import defpackage.z91;
import java.io.Serializable;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class AppConfig extends r9 implements Serializable {
    public boolean E;
    public int F;
    public boolean G = true;
    public boolean H = false;
    public boolean I = false;

    public int getLogLevel() {
        return this.F;
    }

    public boolean getUseAriaCrashHandler() {
        return this.E;
    }

    public boolean isNetCheck() {
        return this.G;
    }

    public boolean isNotNetRetry() {
        return this.I;
    }

    public boolean isUseBroadcast() {
        return this.H;
    }

    public AppConfig setLogLevel(int i) {
        this.F = i;
        z91.a0 = i;
        j31.q();
        throw null;
    }

    public AppConfig setNetCheck(boolean z) {
        this.G = z;
        j31.q();
        throw null;
    }

    public AppConfig setNotNetRetry(boolean z) {
        this.I = z;
        j31.q();
        throw null;
    }

    public AppConfig setUseAriaCrashHandler(boolean z) {
        this.E = z;
        if (z) {
            k kVar = new k();
            kVar.b = Thread.getDefaultUncaughtExceptionHandler();
            kVar.c = Executors.newSingleThreadExecutor();
            Thread.setDefaultUncaughtExceptionHandler(kVar);
        } else {
            Thread.setDefaultUncaughtExceptionHandler(null);
        }
        j31.q();
        throw null;
    }

    public AppConfig setUseBroadcast(boolean z) {
        this.H = z;
        j31.q();
        throw null;
    }
}
