package androidx.work.impl.utils;

import defpackage.oy;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public final class DurationApi26Impl {
    public static final long toMillisCompat(Duration duration) {
        oy.j(duration, "<this>");
        return duration.toMillis();
    }
}
