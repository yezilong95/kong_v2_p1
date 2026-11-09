package com.mamimobi.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.ma0;
import defpackage.qm;

/* JADX INFO: loaded from: classes.dex */
public class KAService extends Service {
    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        qm.B0(this);
        ma0.m0(this);
        return 2;
    }
}
