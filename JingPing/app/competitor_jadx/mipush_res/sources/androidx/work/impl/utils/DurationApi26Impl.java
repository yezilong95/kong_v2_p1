package androidx.work.impl.utils;

import defpackage.t40;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public final class DurationApi26Impl {
    public static final long toMillisCompat(Duration duration) {
        t40.n(duration, "<this>");
        return duration.toMillis();
    }
}
