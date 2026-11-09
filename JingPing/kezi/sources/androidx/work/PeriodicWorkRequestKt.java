package androidx.work;

import androidx.work.PeriodicWorkRequest;
import defpackage.oy;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class PeriodicWorkRequestKt {
    public static final <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(long j, TimeUnit timeUnit) {
        oy.j(timeUnit, "repeatIntervalTimeUnit");
        oy.R();
        throw null;
    }

    public static final <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(long j, TimeUnit timeUnit, long j2, TimeUnit timeUnit2) {
        oy.j(timeUnit, "repeatIntervalTimeUnit");
        oy.j(timeUnit2, "flexTimeIntervalUnit");
        oy.R();
        throw null;
    }

    public static final <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(Duration duration) {
        oy.j(duration, "repeatInterval");
        oy.R();
        throw null;
    }

    public static final <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(Duration duration, Duration duration2) {
        oy.j(duration, "repeatInterval");
        oy.j(duration2, "flexTimeInterval");
        oy.R();
        throw null;
    }
}
