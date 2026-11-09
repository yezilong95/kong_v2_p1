package com.mamimobi.utils;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import defpackage.o71;

/* JADX INFO: loaded from: classes.dex */
public class AppInitWorker extends Worker {
    public AppInitWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // androidx.work.Worker
    public final ListenableWorker.Result doWork() {
        try {
            o71.f(getApplicationContext()).a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListenableWorker.Result.success();
    }
}
