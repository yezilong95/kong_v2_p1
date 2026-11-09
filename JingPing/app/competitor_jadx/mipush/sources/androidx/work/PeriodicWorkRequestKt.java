package androidx.work;

import androidx.work.PeriodicWorkRequest;
import defpackage.t40;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class PeriodicWorkRequestKt {
    public static final <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(long j, TimeUnit timeUnit) {
        t40.n(timeUnit, "repeatIntervalTimeUnit");
        t40.n1();
        throw null;
    }

    public static final <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(long j, TimeUnit timeUnit, long j2, TimeUnit timeUnit2) {
        t40.n(timeUnit, "repeatIntervalTimeUnit");
        t40.n(timeUnit2, "flexTimeIntervalUnit");
        t40.n1();
        throw null;
    }

    public static final <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(Duration duration) {
        t40.n(duration, "repeatInterval");
        t40.n1();
        throw null;
    }

    public static final <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(Duration duration, Duration duration2) {
        t40.n(duration, "repeatInterval");
        t40.n(duration2, "flexTimeInterval");
        t40.n1();
        throw null;
    }
}
