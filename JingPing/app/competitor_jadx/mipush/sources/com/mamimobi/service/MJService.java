package com.mamimobi.service;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import defpackage.j31;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"SpecifyJobSchedulerIdRange"})
public class MJService extends JobService {
    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        j31.g0(getApplicationContext());
        return false;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
